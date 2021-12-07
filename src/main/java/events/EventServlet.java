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

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

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
        Object clientInfoObj = req.getSession().getAttribute(LoginServerConstants.CLIENT_INFO_KEY);

        if (clientInfoObj != null) {
            // already authed, no need to log in, go to the edit profile page
            resp.setStatus(HttpStatus.OK_200);
            PrintWriter pw = resp.getWriter();
            pw.println(EventServletConstants.PAGE_HEADER);
            pw.println(NavigationBarConstants.NAVI_STYLE);
            pw.println(EventServletConstants.NAVI_BODY);
            pw.println("<h2>Events</h2>");
            // print and get all events
            getAndPrintAllEvents(pw);
            pw.println("<a href=\"events/add\">Add A Event.</a>");
            pw.println(EventServletConstants.PAGE_FOOTER);
            return;
        }

        // not logged in, let the user log in
        resp.setStatus(HttpStatus.OK_200);
        PrintWriter writer = resp.getWriter();
        writer.println(LoginServerConstants.PAGE_HEADER);
        writer.println("<h1>Please log in</h1>");
        writer.println(LoginServerConstants.PAGE_FOOTER);
    }

    /**
     * A helper method to get and print all events.
     * @param pw printWriter
     */
    private void getAndPrintAllEvents(PrintWriter pw) {
        pw.println(EventServletConstants.TABLE_STYLE);
        pw.println(EventServletConstants.BODY_OPEN_TAG);
        try (Connection con = DBCPDataSource.getConnection()) {
            ArrayList<Event> events = JDBCUtility.executeSelectEvents(con);
            pw.println("<table>");
            pw.println("  <tr>");
            pw.println("    <th>Title</th>");
            pw.println("    <th>Date</th>");
            pw.println("    <th>Time</th>");
            pw.println("    <th>Tickets</th>");
            pw.println("    <th>More</th>");
            pw.println("  </tr>");
            for (Event e : events) {
                pw.println("  <tr>");
                pw.println("    <th>" + e.getTitle() + "</th>");
                pw.println("    <th>" + e.getDate() + "</th>");
                pw.println("    <th>" + e.getTime() + "</th>");
                pw.println("    <th>" + e.getTickets() + "</th>");
                pw.println("    <th><a href=\"events/" + e.getId()  + "\"><button>More</button></a></th>");
                pw.println("  </tr>");
                pw.flush();
            }
            pw.println("</table>");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
