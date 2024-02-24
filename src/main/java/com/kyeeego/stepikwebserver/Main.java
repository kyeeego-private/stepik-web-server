package com.kyeeego.stepikwebserver;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Main {
    public static void main(String[] args) throws Exception {
        var mirrorServlet = new MirrorServlet();

        var srv = new Server(8081);
        var ctx = new ServletContextHandler(ServletContextHandler.SESSIONS);
        srv.setHandler(ctx);
        ctx.addServlet(new ServletHolder(mirrorServlet), "/mirror");

        srv.start();
        srv.join();
    }

}
