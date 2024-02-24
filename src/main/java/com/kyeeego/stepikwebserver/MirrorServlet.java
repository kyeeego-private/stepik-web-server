package com.kyeeego.stepikwebserver;


import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;

public class MirrorServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        var ctx = new HashMap<String, Object>();
        ctx.put("value", req.getParameter("key"));

        res.getWriter().println(PageGenerator.instance().getPage("template.html", ctx));

        res.setContentType("text/html;charset=utf-8");
        res.setStatus(HttpServletResponse.SC_OK);
    }
}
