package com.kyeeego.stepikwebserver.servlets;

import com.kyeeego.stepikwebserver.services.AccountService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class AdminServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.getWriter().println(AccountService.instance().getUsersLimit());
        res.setContentType("text/plain;charset=utf-8");
        res.setStatus(HttpServletResponse.SC_OK);
    }
}
