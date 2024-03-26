package com.kyeeego.stepikwebserver.jmx;

import com.kyeeego.stepikwebserver.services.AccountService;

public class AccountServiceController implements AccountServiceControllerMBean {
    @Override
    public int getUsersLimit() {
        return AccountService.instance().getUsersLimit();
    }

    @Override
    public void setUsersLimit(int usersLimit) {
        AccountService.instance().setUsersLimit(usersLimit);
    }
}
