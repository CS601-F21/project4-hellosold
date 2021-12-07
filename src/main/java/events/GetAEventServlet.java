package events;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jdbc.DBCPDataSource;
import jdbc.JDBCUtility;
import login.LoginServerConstants;
import login.NavigationBarConstants;
import org.eclipse.jetty.http.HttpStatus;
import tickets.BuyTicketServletConstants;
import utilities.Utilities;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

/**
 * This class handles get request to retrieve details for a specific event.
 * And handles post request to allow the user to purchase tickets for an event, and set a limit 6.
 * When a user already buy 6 tickets of this event, then the purchase is not allowed.
 * If not, the purchase only success when the remaining tickets is satisfied.
 *
 * @author Li Liu
 */
public class GetAEventServlet extends HttpServlet {
    /**
     * Handle get methods of getting detail information of a event.
     * @param req request
     * @param resp response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // determine whether the user is already authenticated
        Object clientInfoObj = req.getSession().getAttribute(LoginServerConstants.CLIENT_INFO_KEY);

        if (clientInfoObj != null) {
            // already authed, no need to log in, go to the edit profile page
            resp.setStatus(HttpStatus.OK_200);
            PrintWriter pw = resp.getWriter();
            pw.println(EventServletConstants.PAGE_HEADER);
            pw.println(NavigationBarConstants.NAVI_STYLE);
            pw.println(EventServletConstants.NAVI_BODY);
            pw.println("<body>\n");
            pw.println("<h1>Event Information</h1>");
            parseRequest(req, resp.getWriter());
            pw.println(EventServletConstants.PAGE_FOOTER);
            return;
        }

        // not logged in, require user to log in
        Utilities.printRequireLogInPage(resp);
    }

    /**
     * Parse a get request.
     * if the path of request is events/event_id
     * for example: events/1 -> show the details of event which id is 1
     *
     * if the path of request id event/event_id/buy
     * go to the buy tickets page
     *
     * @param req request
     * @param writer writer
     */
    private void parseRequest(HttpServletRequest req, PrintWriter writer) {
        String path = req.getPathInfo();
        System.out.println("path is:" + path);
        String[] pathSplits = path.split("/");
        if (pathSplits.length == 2) {
            handleGetEvent(writer, pathSplits[1]);
        } else {
            writer.println("<p>Recourse is not available.</p>");
        }
    }


    /**
     * A helper method to get a event by providing the event id.
     * @param writer printWriter
     * @param id event id
     */
    private void handleGetEvent(PrintWriter writer, String id) {
        if (Utilities.isDigit(id)) {
            int eventId = Integer.parseInt(id);
            // Get all information of the event by event_id
            try (Connection con = DBCPDataSource.getConnection()) {
                Event event = JDBCUtility.executeGetAEvent(con, eventId);
                if (event == null) {
                    writer.println("<p>Something went wrong of the database.</p>");
                } else {
                    writer.println("<p> Title: " + event.getTitle() + "</p>\n");
                    writer.println("<p> Date: " + event.getDate() + "</p>\n");
                    writer.println("<p> Time: " + event.getTime() + "</p>\n");
                    writer.println("<p> Publish by: " + event.getSponsor() + "</p>\n");
                    writer.println("<p> Place: " + event.getPlace() + "</p>\n");
                    writer.println("<p> Description: " + event.getDescription() + "</p>\n");
                    writer.println("<p> Remaining tickets: " + event.getTickets() + "</p>\n");

//                    writer.println("<a href=events/" + eventId +"/buy><button>" + "BUY</button></a>\n");
                    writer.println("<form action=\"/events/" + eventId +"\" method=\"post\">\n" +
                            //"    <label for=\"buy\">Buy some tickets:</label></form><br/>\n" +
                            "    <input type=\"text\" id=\"num\" name=\"num\" />\n" +
                            "    <input type=\"submit\" value=\"BUY\"/>\n" +
                            "</form>");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            writer.println("<p>Resource is not available.</p>");
        }
   }


    /**
     * Handle buy tickets request.
     * @param req request
     * @param resp response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // get user id
        Map<String, String> data = (Map<String, String>) req.getServletContext().getAttribute("data");
        int userId = Integer.parseInt(data.get("id"));

        PrintWriter writer = resp.getWriter();
        // get event id
        int eventId = getEventId(req);

        // get num of tickets to buy
        // input num should be no greater than 6
        String num = req.getParameter("num");
        System.out.println("num is: " + num);
        int tickets = 0;
        if (validInputNum(num)) {
            tickets = Integer.parseInt(num);
        } else {
            writer.println("<p>One user can only buy at most 6 tickets.</p>");
        }

        if (eventId != 0) {
            // check total purchased
            int total = getTotalPurchased(userId, eventId);
            if (total == 6) {
                writer.println("<p>Already purchased 6 tickets.</p>");
                return;
            } else if (total + tickets > 6) {
                writer.println("<p>Exceed limits of 6.</p>");
                return;
            }

            // buy tickets
            try (Connection con = DBCPDataSource.getConnection()) {
                int result = JDBCUtility.executeBuyTicket(con, userId, eventId, tickets);
                if (result == 0) {
                    writer.println("<p>No enough tickets left.</p>");
                    return;
                }
                writer.println(BuyTicketServletConstants.POST_PAGE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            writer.println("<p>Resource is not available.</p>");
        }

    }

    /**
     * A helper method to test whether the input number is valid.
     * Allow a user to buy at most 6 tickets of a event.
     * @param tickets tickets
     * @return true if is valid, false otherwise
     */
    private boolean validInputNum(String tickets) {
        int num = 0;
        boolean isDigit = Utilities.isDigit(tickets);
        if (isDigit) {
            num = Integer.parseInt(tickets);
            return num > 0 && num <= 6;
        }
        return false;
    }

    /**
     * A helper method to get the eventId.
     * @param req request
     * @return eventId
     */
    private int getEventId(HttpServletRequest req) {
        String eventId = "";
        int resultId = 0;
        String[] path = req.getPathInfo().split("/");
        if (path.length == 2) {
            eventId = path[1];
        }
        System.out.println("event is " + eventId);
        if (Utilities.isDigit(eventId)) {
            resultId = Integer.parseInt(eventId);
        }
        return resultId;
    }

    /**
     * A helper method to get total number tickets that has been purchased.
     * @param userId userId
     * @param eventId eventId
     * @return num of tickets
     */
    private int getTotalPurchased(int userId, int eventId) {
        int total = Integer.MIN_VALUE;
        try (Connection con = DBCPDataSource.getConnection()) {
            total = JDBCUtility.executedGetNumOfTickets(con, userId, eventId);
            return total;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }
}
