package login;

import editProfile.EditProfileServlet;
import events.AddEventServlet;
import events.EventServlet;
import events.GetAEventServlet;
import home.HomeServlet;
import jdbc.DBCPDataSource;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import tickets.TransferTicketServlet;
import utilities.Config;
import utilities.Utilities;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * To start ser
 */
public class LoginServer {
    public static final int PORT = 8080;

    public static void main(String[] args) {
        try {
            startup();
        } catch (Exception e) {
            // catch generic Exception as that is what is thrown by server start method
            e.printStackTrace();
        }
    }

    /**
     * A helper method to start the server.
     *
     * Links for helping serving static files with embedded Jetty
     * https://newbedev.com/serving-static-files-with-embedded-jetty
     * https://stackoverflow.com/questions/10284584/serving-static-files-with-embedded-jetty
     *
     * @throws Exception -- generic Exception thrown by server start method
     */
    public static void startup() throws Exception {
        // read the client id and secret from a config file
        Config config = Utilities.readConfig();

        // create a new server
        Server server = new Server(PORT);

        // make the config information available across servlets by setting an
        // attribute in the context
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setAttribute(LoginServerConstants.CONFIG_KEY, config);
        context.setContextPath("/");

        // get static files
        DefaultServlet defaultServlet = new DefaultServlet();
        ServletHolder holderPwd = new ServletHolder("default", defaultServlet);
        holderPwd.setInitParameter("resourceBase", "images");

        context.addServlet(holderPwd, "/*");

        // the default path will direct to a landing page with
        // "Login with Slack" button
        context.addServlet(LandingServlet.class, "/landing");

        context.addServlet(HomeServlet.class, "/home");

        // Once authenticated, Slack will redirect the user
        // back to /login
        context.addServlet(LoginServlet.class, "/login");

        // handle logout
        context.addServlet(LogoutServlet.class, "/logout");

        // handle edit profile
        context.addServlet(EditProfileServlet.class, "/editprofile");

        // handle event
        context.addServlet(EventServlet.class, "/events");
        context.addServlet(AddEventServlet.class, "/events/add");
        context.addServlet(GetAEventServlet.class, "/events/*");

        // handle transfer tickets
        context.addServlet(TransferTicketServlet.class, "/tickets");

        // start it up!
        server.setHandler(context);
        server.start();
    }


}
