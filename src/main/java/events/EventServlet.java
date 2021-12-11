package events;

import jakarta.servlet.ServletException;
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
import java.util.ArrayList;
import java.util.Map;

/**
 * Handle get all events request.
 * url="https://61ec-67-169-155-8.ngrok.io/events"
 *
 * @author Li Liu
 *
 */
public class EventServlet extends HttpServlet {

    /**
     * Handle get request.
     * First check whether user is logged in or not, if user is logged in, get all events from the database.
     * If the user is not logged in, ask to log in.
     * @param req request
     * @param resp response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // determine whether the user is already authenticated
        String sessionId = req.getSession().getId();
        Map<String, String> data = Utilities.isLoggedIn(req, sessionId);

        if (data != null) {
            // already authed, no need to log in, go to the edit profile page
            resp.setStatus(HttpStatus.OK_200);
            int userId = Integer.parseInt(data.get("id"));
            System.out.println("userId: " + userId);
            PrintWriter pw = resp.getWriter();
            pw.println(EventServletConstants.PAGE_HEADER);
            pw.println(EventServletConstants.EVENTS_STYLE);
            pw.println(EventServletConstants.BODY_OPEN_TAG);
            pw.println(EventServletConstants.NAVI_BODY);
            pw.println("<h2>Events</h2>");
            // print and get all events
            getAndPrintAllEvents(req, resp, userId,pw);
            pw.println("<br/><a class=\"one\" href=\"events/add\">Create a new event.</a>");
            pw.println(EventServletConstants.PAGE_FOOTER);
            return;
        }

        // not logged in, let the user log in
        Utilities.printRequireLogInPage(resp);
    }

    /**
     * A helper method to get and print all events.
     * @param pw printWriter
     */
    private void getAndPrintAllEvents(HttpServletRequest req, HttpServletResponse response, int userId,
                                      PrintWriter pw) {
        try (Connection con = DBCPDataSource.getConnection()) {
            ArrayList<Event> events = JDBCUtility.executeSelectEvents(con);
            pw.println("<table>");
            pw.println("  <tr>");
            pw.println("    <th>Title</th>");
            pw.println("    <th>Date</th>");
            pw.println("    <th>Time</th>");
            pw.println("    <th>Tickets</th>");
            pw.println("    <th>More</th>");
            pw.println("    <th>Edit</th>");
            pw.println("    <th>Delete</th>");
            pw.println("  </tr>");
            for (Event e : events) {
                pw.println("  <tr>");
                pw.println("    <th>" + e.getTitle() + "</th>");
                pw.println("    <th>" + e.getDate() + "</th>");
                pw.println("    <th>" + e.getTime() + "</th>");
                pw.println("    <th>" + e.getTickets() + "</th>");
                pw.println("    <th><a href=\"events/" + e.getId()  + "\"><button>More</button></a></th>");
                if (e.getUserId() == userId) {
                    pw.println("    <th><form method=\"get\" action=\"events/edit/" + e.getId()  + "\">\n"
                            + EventServletConstants.EDIT_BUTTON);
                    pw.println("    <th><form method=\"post\" action=\"events/delete/" + e.getId()  + "\">\n"
                            + EventServletConstants.DELETE_BUTTON);
                } else {
                    pw.println(EventServletConstants.EDIT_BUTTON_INACTIVE);
                    pw.println(EventServletConstants.DELETE_BUTTON_INACTIVE);
                }
                pw.println("  </tr>");
                pw.flush();
            }
            pw.println("</table>");
        } catch (SQLException e) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR_500);
            e.printStackTrace();
        }

    }

}
