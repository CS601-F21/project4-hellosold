package events;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jdbc.DBCPDataSource;
import jdbc.JDBCUtility;
import login.LoginServerConstants;
import org.eclipse.jetty.http.HttpStatus;
import utilities.Utilities;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Map;

/**
 * This class handles get request to retrieve details for a specific event.
 * And handles post request to allow the user to purchase tickets for an event, and set a limit 6.
 * When a user already buy 6 tickets of this event, then the purchase is not allowed.
 * If not, the purchase only success when the remaining tickets is satisfied.
 *
 * Handle post request to edit/delete an event.
 *
 * @author Li Liu
 */
public class GetAEventServlet extends HttpServlet {
    /**
     * Handle get methods of getting detail information of a event.
     * @param req request
     * @param resp response
     * @throws IOException IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // determine whether the user is already authenticated
        String sessionId = req.getSession().getId();
        Map<String, String> data = Utilities.isLoggedIn(req, sessionId);

        if (data != null) {
            // already authed, no need to log in, go to the edit profile page
            resp.setStatus(HttpStatus.OK_200);
            parseRequest(req, resp);
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
     * events/1/edit -> show the details of event which id is 1 and allow the user to change the parameters of this
     * event
     *
     * if the path of request id event/event_id/buy
     * go to the buy tickets page
     *
     * @param req request
     */
    private void parseRequest(HttpServletRequest req, HttpServletResponse response) {
        PrintWriter writer;
        try {
            writer = response.getWriter();
            String path = req.getPathInfo();
            System.out.println("line 73, path is:" + path);
            String[] pathSplits = path.split("/");
            if (pathSplits.length == 2) {
                handleGetEvent(writer, pathSplits[1]);
            } else if (path.contains("/edit")) {
                handleEditEvent(req, response);
            } else {
                writer.println("<p>Recourse is not available.</p>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * A helper method to get details of an event by providing the event id.
     * Show the title, date, time, place, createdBy, description and remaining tickets, poster of this event.
     *
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
                    writer.println(EventServletConstants.PAGE_HEADER);
                    writer.println(EventServletConstants.GET_EVENT_STYLE);
                    writer.println(EventServletConstants.BODY_OPEN_TAG);
                    writer.println(EventServletConstants.NAVI_BODY);
                    writer.println("<h2>Event Information:</h2>");
                    writer.println("<label>Title:</label><br/>");
                    writer.println("<p>" + event.getTitle() + "</p>");
                    writer.println("<label>Date:</label><br/>");
                    writer.println("<p>" + event.getDate() + "</p>");
                    writer.println("<label>Time:</label><br/>");
                    writer.println("<p>" + event.getTime() + "</p>");
                    writer.println("<label>Publish by: </label><br/>");
                    writer.println("<p>" + event.getSponsor() + "</p>");
                    writer.println("<label> Place: </label><br/>");
                    writer.println("<p>" + event.getPlace() + "</p>");
                    writer.println("<label> Description: </label><br/>");
                    writer.println("<p>" + event.getDescription() + "</p>");
                    writer.println("<label> Remaining tickets: </label><br/>");
                    writer.println("<p>" + event.getTickets() + "</p>");
                    writer.println("<label>Image:</label><br/>");
                    writer.println("<img src=\"https://61ec-67-169-155-8.ngrok.io/" + event.getImagePath() +
                            "\" alt=\"poster\" width=\"500px\" height=\"300px\"/>");
                    writer.println("<hr/>Please input the number of tickets that you want to buy:<br/>\n");
                    writer.println("<form action=\"/events/" + eventId + "\" method=\"post\">\n" +
                            "    <input type=\"number\" id=\"num\" name=\"num\" required=\"required\"/>" +
                            "    <input type=\"submit\" value=\"BUY\"/>\n" +
                            "</form>");
                    writer.println(EventServletConstants.PAGE_FOOTER);
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
     * First check the input number of tickets that the user wants to buy is no greater than 6.
     * If the number is grater than 6, let the user to change the number.
     *
     * Then check if there are enough tickets left for this event, if there's enough tickets left, go update tables.
     * @param req request
     * @param resp response
     * @throws IOException IOException
     */
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        parsePath(req, resp);
    }

    /**
     * Handle buy tickets events.
     * @param request request
     * @param response response
     * @throws IOException IOException
     */
    private void handleBuyEvent(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // get user id
        int userId = Utilities.getUserId(request);
        PrintWriter writer = response.getWriter();

        // get event id
        int eventId = getEventId(request);

        // get num of tickets to buy
        // input num should be no greater than 6
        String num = request.getParameter("num");
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
                response.sendRedirect("/home");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            writer.println("<p>Resource is not available.</p>");
        }
    }

    /**
     * Handle get request of edit events.
     * Return all the details of this event.
     * @param request request
     * @param response response
     */
    private void handleEditEvent(HttpServletRequest request, HttpServletResponse response) {
        String path = request.getPathInfo();
        int eventId = Integer.parseInt(path.split("/")[2]);
        System.out.println("Edit event id is: " + eventId);
        try {
            getEditEventPage(response, eventId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Get and print details of this event.
     * @param response response
     * @param eventId eventId
     * @throws SQLException SQL exception
     */
    private void getEditEventPage(HttpServletResponse response, int eventId) throws SQLException {
        try (Connection connection = DBCPDataSource.getConnection()) {
            Event event = JDBCUtility.executeGetEvent(connection, eventId);
            if (event == null) {
                return;
            }
            PrintWriter writer = response.getWriter();
            writer.println(EventServletConstants.PAGE_HEADER);
            writer.println(EventServletConstants.Edit_EVENT_STYLE);
            writer.println(EventServletConstants.BODY_OPEN_TAG);
            writer.println(EventServletConstants.NAVI_BODY);
            writer.println("<h2>Edit a event:</h2>");
            writer.println("<form action=\"/events/edit/" + eventId + "\" method=\"post\">\n" +
                    "    <label for=\"title\">Title:</label><br/>\n" +
                    "    <input type=\"text\" id=\"title\" name=\"title\" value=\"" + event.getTitle() + "\"/><br/><br/>");
            writer.println("<label for=\"date\">Date:</label><br/>\n" +
                    "    <input type=\"date\" id=\"date\" name=\"date\" value=\"" + event.getDate() + "\"/><br/><br/>");
            writer.println("<label for=\"time\">Time:</label><br/>\n" +
                    "    <input type=\"time\" id=\"time\" name=\"time\" min=\"00:00:00\" max=\"24:00:00\" " +
                    "value=\"" + event.getTime() + "\"/>\n" +
                    "    <br/><br/>");
            writer.println("<label for=\"place\">Place:</label><br/>\n" +
                    "    <input type=\"text\" id=\"place\" name=\"place\" value=\"" + event.getPlace() + "\"/> " +
                    "<br/><br/>");
            writer.println("<label for=\"ticket\">Total tickets:</label><br/>\n" +
                    "    <input type=\"number\" id=\"ticket\" name=\"ticket\" value=\"" + event.getTickets() + "\"/> " +
                    "<br/><br/>");
            writer.println("<label for=\"description\">Description:</label><br/>\n" +
                    "    <textarea type=\"text\" id=\"description\" name=\"description\" rows=\"5\" cols=\"40\">" +
                    event.getDescription() + "</textarea>\n<br/><br/>");
            writer.println("    <input type=\"submit\" value=\"Edit\"/> <br/>\n" +
                    "</form>");
            writer.println(EventServletConstants.PAGE_FOOTER);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handle post requests of deleting an event.
     * @param request request
     * @param response response
     */
    private void handleDeleteEvent(HttpServletRequest request, HttpServletResponse response) {
        // parse request path, find out the eventId
        String path = request.getPathInfo();
        int idx = path.indexOf("/delete");
        int eventID = Integer.parseInt(path.substring(idx + 8));
        System.out.println("eventId is: " + eventID);

        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // delete event from the database
        try (Connection connection = DBCPDataSource.getConnection()) {
            JDBCUtility.executeDeleteEvent(connection, eventID);
            response.setStatus(HttpStatus.OK_200);
            writer.println(LoginServerConstants.PAGE_HEADER);
            writer.println("<h1>Delete event!</h1>");
            writer.println(LoginServerConstants.PAGE_FOOTER);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR_500);
            writer.println(LoginServerConstants.PAGE_HEADER);
            writer.println("<h1>Cannot delete such event, some users still hold the tickets.</h1>");
            writer.println(LoginServerConstants.PAGE_FOOTER);
        }
    }

    /**
     * A helper method to test whether the input number is valid.
     * Allow a user to buy at most 6 tickets of a event.
     * @param tickets tickets
     * @return true if is valid, false otherwise
     */
    private boolean validInputNum(String tickets) {
        int num;
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
        System.out.println("event id is " + eventId);
        if (Utilities.isDigit(eventId)) {
            resultId = Integer.parseInt(eventId);
        }
        return resultId;
    }

    /**
     * A helper method to parse the post requests.
     * If the path contains "/edit", then call method to handle edit request.
     * If the path contians "/delete", then call method to handle deletion request.
     * @param request request
     * @param response response
     * @throws IOException IOException
     */
    private void parsePath(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String path = request.getPathInfo();
        if (path.contains("/edit")) {
            handlePostEditEvent(request, response);
        } else if (path.contains("/delete")) {
            handleDeleteEvent(request, response);
        } else {
            handleBuyEvent(request, response);
        }
    }

    /**
     * Handle post request of editing some existing event.
     * Get all input from the user, then update events table.
     * @param request request
     * @param response response
     */
    private void handlePostEditEvent(HttpServletRequest request, HttpServletResponse response) {
        // get eventId
        String path = request.getPathInfo();
        int eventId = Integer.parseInt(path.split("/")[2]);
        // get parameter from request
        String title = request.getParameter(EventServletConstants.TITLE);
        java.sql.Date date = java.sql.Date.valueOf(request.getParameter(AddEventServletConstants.DATE));
        Time time = Time.valueOf(request.getParameter(AddEventServletConstants.TIME));
        String place = request.getParameter("place");
        int num = Integer.parseInt(request.getParameter("ticket"));
        String description = request.getParameter(EventServletConstants.DESCRIPTION);

        // update events table
        try(Connection connection = DBCPDataSource.getConnection()) {
            response.setStatus(HttpStatus.OK_200);
            JDBCUtility.executeUpdateEvent(connection, eventId, title, description, date, time, place, num);
            response.sendRedirect("/events/" + eventId);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
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
