package com.kyeeego.stepikwebserver.servlets;

import com.google.gson.Gson;
import com.kyeeego.stepikwebserver.models.dto.ResourceReqDto;
import com.kyeeego.stepikwebserver.resources.ResourceServer;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ResourceServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        var path = new Gson().fromJson(req.getReader(), ResourceReqDto.class);
        ResourceServer.instance().readFromFile(path.getPath());

        res.setStatus(HttpServletResponse.SC_CREATED);
    }
}
