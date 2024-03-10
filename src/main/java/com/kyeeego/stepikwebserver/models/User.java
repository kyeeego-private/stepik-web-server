package com.kyeeego.stepikwebserver.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private long id;

    private String login;
    private String password;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
