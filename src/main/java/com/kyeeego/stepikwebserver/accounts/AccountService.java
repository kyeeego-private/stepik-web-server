package com.kyeeego.stepikwebserver.accounts;

import java.util.HashMap;
import java.util.Map;

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

    private final Map<String, User> usersByLogin = new HashMap<>();

    public void register(String login, String password) {
        if (!usersByLogin.containsKey(login)) {
            usersByLogin.put(login, new User(login, password));
        }
    }

    public User getByLogin(String login) {
        return usersByLogin.get(login);
    }

    public boolean isRegistered(String login) {
        return usersByLogin.containsKey(login);
    }
}
