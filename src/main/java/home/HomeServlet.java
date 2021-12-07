package home;

import events.Event;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jdbc.DBCPDataSource;
import jdbc.JDBCUtility;
import login.LoginServerConstants;
import login.NavigationBarConstants;
import org.eclipse.jetty.http.HttpStatus;
import tickets.Ticket;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Home page will show all the purchased tickets, and display details for all events that the user has purchased
 * tickets.
 *
 * @author Li Liu
 */
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // determine whether the user is already authenticated
        Object clientInfoObj = req.getSession().getAttribute(LoginServerConstants.CLIENT_INFO_KEY);
        PrintWriter writer = resp.getWriter();

        if (clientInfoObj != null) {
            // already authed, no need to log in, go to the edit profile page
            Map<String, String> data = (Map<String, String>) req.getServletContext().getAttribute("data");
            String id = String.valueOf(data.get("id"));
            int userId = Integer.parseInt(id);
            String name = data.get("name");

            resp.setStatus(HttpStatus.OK_200);
            writer.println(HomeServletConstants.PAGE_HEADER);
            writer.println(NavigationBarConstants.NAVI_STYLE);
            writer.println(HomeServletConstants.NAVI_BODY);
            writer.println("<h1>Hello, " + name + "</h1>");
            getAndPrintPurchasedTickets(userId, writer);
            writer.println(HomeServletConstants.PAGE_FOOTER);
            return;
        }
        resp.setStatus(HttpStatus.OK_200);
        writer.println(LoginServerConstants.PAGE_HEADER);
        writer.println("<h1>Please log in</h1>");
        writer.println(LoginServerConstants.PAGE_FOOTER);
    }

    /**
     * A helper method to get and print all events for which the user has purchased tickets.
     * @param userId userId
     * @param writer wroter
     */
    private void getAndPrintPurchasedTickets(int userId, PrintWriter writer) {
        try (Connection con = DBCPDataSource.getConnection()) {
            List<Ticket> tickets = JDBCUtility.executeGetAllPurchasedTickets(con, userId);
            if (tickets.size() != 0) {
                writer.println("<h2>Tickets:<h2>");
                Event event;
                for (Ticket t : tickets) {
                    event = t.getEvent();
                    writer.println("  <h4>Title:" + event.getTitle() + "</h4>\n");
                    writer.println("    <p> Date: " + event.getDate() + "</p>\n");
                    writer.println("    <p> Time: " + event.getTime() + "</p>\n");
                    writer.println("    <p> Place: " + event.getPlace() + "</p>\n");
                    writer.println("    <p> Tickets left: " + event.getTickets() + "</p>\n");
                    writer.println("    <p> Description: " + event.getDescription() + "</p>\n");
                    writer.println("    <p> Owned tickets: " + t.getTickets() + "</p>\n");
                }
                writer.println("<br />");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
