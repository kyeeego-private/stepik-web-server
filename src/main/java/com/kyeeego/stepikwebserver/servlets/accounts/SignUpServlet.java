package com.kyeeego.stepikwebserver.servlets.accounts;

import com.google.gson.Gson;
import com.kyeeego.stepikwebserver.services.AccountService;
import com.kyeeego.stepikwebserver.models.dto.User;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class SignUpServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        var user = new Gson().fromJson(req.getReader(), User.class);
        AccountService.instance().register(user.login(), user.password());
    }
}
