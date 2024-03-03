package com.kyeeego.stepikwebserver.servlets.accounts;

import com.google.gson.Gson;
import com.kyeeego.stepikwebserver.accounts.AccountService;
import com.kyeeego.stepikwebserver.accounts.User;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class SignInServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        var user = new Gson().fromJson(req.getReader(), User.class);

        res.setContentType("text/plain;charset=utf-8");

        if (!AccountService.instance().isRegistered(user.login())
                || !AccountService.instance().getByLogin(user.login()).password()
                .equals(user.password())) {
            res.getWriter().println("Unauthorized");
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        res.getWriter().println("Authorized: login");
        res.setStatus(HttpServletResponse.SC_OK);
    }
}
