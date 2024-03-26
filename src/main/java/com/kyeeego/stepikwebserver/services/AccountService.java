package com.kyeeego.stepikwebserver.services;

import com.kyeeego.stepikwebserver.db.Repository;
import com.kyeeego.stepikwebserver.models.User;
import lombok.Getter;
import lombok.Setter;

public class AccountService {

    private static AccountService accountService;

    public static AccountService instance() {
        if (accountService == null) {
            accountService = new AccountService();
        }

        return accountService;
    }

    private AccountService() {
    }

    @Getter
    @Setter
    private int usersLimit = 10;

    public void register(String login, String password) {
        if (Repository.instance().getUserByLogin(login) == null) {
            Repository.instance().save(new User(login, password));
        }
    }

    public boolean logIn(String login, String password) {
        var user = Repository.instance().getUserByLogin(login);
        return user != null && user.getPassword().equals(password);
    }

}
