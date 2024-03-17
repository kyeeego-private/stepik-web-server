package com.kyeeego.stepikwebserver;

import com.kyeeego.stepikwebserver.db.Repository;
import com.kyeeego.stepikwebserver.services.Connection;
import com.kyeeego.stepikwebserver.servlets.ChatHtmlServlet;
import com.kyeeego.stepikwebserver.servlets.accounts.SignInServlet;
import com.kyeeego.stepikwebserver.servlets.accounts.SignUpServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.websocket.server.config.JettyWebSocketServletContainerInitializer;

public class Main {
    public static void main(String[] args) throws Exception {

        Repository.instance();

        var srv = new Server(8081);
        var ctx = new ServletContextHandler(ServletContextHandler.SESSIONS);

        JettyWebSocketServletContainerInitializer.configure(ctx, (servletContext, container) -> {
            container.addMapping("/chatws", Connection.class);
        });

        srv.setHandler(ctx);

        ctx.addServlet(new ServletHolder(new SignUpServlet()), "/signup");
        ctx.addServlet(new ServletHolder(new SignInServlet()), "/signin");
        ctx.addServlet(new ServletHolder(new ChatHtmlServlet()), "/chat");

        srv.start();
        srv.join();
    }

}
