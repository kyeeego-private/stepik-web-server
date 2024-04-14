package com.kyeeego.stepikwebserver;

import com.kyeeego.stepikwebserver.db.Repository;
import com.kyeeego.stepikwebserver.jmx.AccountServiceController;
import com.kyeeego.stepikwebserver.jmx.resources.ResourceServerController;
import com.kyeeego.stepikwebserver.services.Connection;
import com.kyeeego.stepikwebserver.servlets.AdminServlet;
import com.kyeeego.stepikwebserver.servlets.ChatHtmlServlet;
import com.kyeeego.stepikwebserver.servlets.ResourceServlet;
import com.kyeeego.stepikwebserver.servlets.accounts.SignInServlet;
import com.kyeeego.stepikwebserver.servlets.accounts.SignUpServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.websocket.server.config.JettyWebSocketServletContainerInitializer;

import javax.management.ObjectName;
import javax.xml.transform.sax.SAXResult;
import java.io.IOException;
import java.lang.management.ManagementFactory;

public class Main {


    public static void main(String[] args) throws IOException {
        var srv = new SocketServer();
        srv.listen(5050);
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void oldMain(String[] args) throws Exception {

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
        ctx.addServlet(new ServletHolder(new AdminServlet()), "/admin");
        ctx.addServlet(new ServletHolder(new ResourceServlet()), "/resources");

        var mbs = ManagementFactory.getPlatformMBeanServer();

        var accountServiceController = new AccountServiceController();
        var resourceServerController = new ResourceServerController();

        var objName = new ObjectName("Admin:type=AccountServiceController.usersLimit");
        var resObjName = new ObjectName("Admin:type=ResourceServerController");

        mbs.registerMBean(accountServiceController, objName);
        mbs.registerMBean(resourceServerController, resObjName);

        srv.start();
        srv.join();
    }

}
