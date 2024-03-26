package com.kyeeego.stepikwebserver.db;

import com.kyeeego.stepikwebserver.models.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.concurrent.atomic.AtomicReference;

public class Repository {

    private SessionFactory sessionFactory;

    private static Repository self;

    private Repository() {
        var registry =
                new StandardServiceRegistryBuilder()
                        .build();
        try {
            sessionFactory =
                    new MetadataSources(registry)
                            .addAnnotatedClasses(User.class)
                            .buildMetadata()
                            .buildSessionFactory();
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
            e.printStackTrace();
        }
    }

    public static Repository instance() {
        if (self == null) {
            self = new Repository();
        }

        return self;
    }

    public User getUserByLogin(String login) {
        var res = new AtomicReference<User>();
        sessionFactory.inTransaction(
                s -> res.set((User) s.createQuery("from User u where u.login='" + login + "'")
                        .getResultList()
                        .stream()
                        .findFirst()
                        .orElse(null))

        );

        return res.get();
    }

    public void save(User user) {
        sessionFactory.inTransaction(s -> {
            s.persist(user);
        });
    }

}
