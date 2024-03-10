package com.kyeeego.stepikwebserver;

import com.kyeeego.stepikwebserver.db.Repository;
import com.kyeeego.stepikwebserver.servlets.accounts.SignInServlet;
import com.kyeeego.stepikwebserver.servlets.accounts.SignUpServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Main {
    public static void main(String[] args) throws Exception {

        Repository.instance();

        var srv = new Server(8081);
        var ctx = new ServletContextHandler(ServletContextHandler.SESSIONS);
        srv.setHandler(ctx);

        ctx.addServlet(new ServletHolder(new SignUpServlet()), "/signup");
        ctx.addServlet(new ServletHolder(new SignInServlet()), "/signin");

        srv.start();
        srv.join();
    }

}
