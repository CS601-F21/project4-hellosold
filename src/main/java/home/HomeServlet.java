package home;

import events.Event;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jdbc.DBCPDataSource;
import jdbc.JDBCUtility;
import login.LoginServerConstants;
import org.eclipse.jetty.http.HttpStatus;
import tickets.Ticket;
import tickets.TransferTicket;
import utilities.Utilities;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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
            writer.println(HomeServletConstants.HOME_STYLE);
            writer.println(HomeServletConstants.NAVI_BODY);
            writer.println("<h1>Hello, " + name + "</h1>");
            getAndPrintPurchasedTickets(userId, writer);
            getAndPrintALlTransferTickets(userId, writer);
            writer.println(HomeServletConstants.PAGE_FOOTER);
            return;
        }
        Utilities.printRequireLogInPage(resp);
    }

    /**
     * A helper method to get and print all events for which the user has purchased tickets.
     * @param userId userId
     * @param writer wroter
     */
    private void getAndPrintPurchasedTickets(int userId, PrintWriter writer) {
        List<Ticket> tickets = assembleTickets(userId);
        Event event;
        if (tickets.size() != 0) {
            writer.println("<h2>Tickets:</h2>");
            for (Ticket t : tickets) {
                event = t.getEvent();
                writer.println("  <h4>Title: " + event.getTitle() + "</h4>");
                writer.println("    <p> Date: " + event.getDate() + "</p>");
                writer.println("    <p> Time: " + event.getTime() + "</p>");
                writer.println("    <p> Place: " + event.getPlace() + "</p>");
                writer.println("    <p> Tickets left: " + event.getTickets() + "</p>");
                writer.println("    <p> Owned tickets: " + t.getTickets() + "</p>");
                writer.println("<hr/>");
            }

        }
    }

    /**
     * Sum of tickets for all events that the user has purchased.
     * @param userId user id
     * @return list of tickets
     */
    private List<Ticket> assembleTickets(int userId) {
        List<Ticket> tickets = new ArrayList<>();
        // get tickets from database
        try (Connection con = DBCPDataSource.getConnection()) {
            tickets = JDBCUtility.executeGetAllPurchasedTickets(con, userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;
    }

    /**
     * A helper to get and print all transfer tickets.
     * @param userId userId
     * @param writer writer
     */
    private void getAndPrintALlTransferTickets(int userId, PrintWriter writer) {
        try (Connection con = DBCPDataSource.getConnection()) {
            List<TransferTicket> transTik = JDBCUtility.executeGetAllTransfers(con, userId);
            List<TransferTicket> ticks = assembleTransTickets(transTik);
            if (ticks.size() != 0) {
                writer.println("<h2>Transfer tickets: </h2>");
                for (TransferTicket ticket : ticks) {
                    writer.println("<h4>Title: " + ticket.getEventTitle() + "</h4>");
                    writer.println("  <p> Transfer to user: " + ticket.getToUser() + "</p>");
                    writer.println("  <p> Number of Tickets: " + ticket.getNum() + "</p>");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sum all tickets that a user given to other users.
     * @param tickets tickets
     * @return list of trans tickets
     */
    private List<TransferTicket> assembleTransTickets(List<TransferTicket> tickets) {
        List<TransferTicket> result = new ArrayList<>();
        if (tickets.size() != 0) {
            Map<String, Integer> map = new HashMap<>();
            Map<String, String> nameMap = new HashMap<>();
            for (TransferTicket ticket : tickets) {
                String title = ticket.getEventTitle();
                if (map.containsKey(title)) {
                    map.put(title, map.get(title) + ticket.getNum());
                } else {
                    map.put(title, ticket.getNum());
                }
                nameMap.put(title, ticket.getToUser());
            }
            for (String t : map.keySet()) {
                result.add(new TransferTicket(nameMap.get(t), t, map.get(t)));
            }
        }
        return result;
    }
}
