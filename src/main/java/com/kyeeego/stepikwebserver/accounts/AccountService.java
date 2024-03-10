package com.kyeeego.stepikwebserver.accounts;

import com.kyeeego.stepikwebserver.db.Repository;
import com.kyeeego.stepikwebserver.models.User;

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


    public void register(String login, String password) {
        if (Repository.instance().getUserByLogin(login) == null) {
            Repository.instance().save(new User(login, password));
        }
    }

    public boolean logIn(String login, String password) {
        var user = Repository.instance().getUserByLogin(login);
        return user != null && user.getPassword().equals(password);
    }

    public User getByLogin(String login) {
        return Repository.instance().getUserByLogin(login);
    }
}
