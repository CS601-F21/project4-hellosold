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
import utilities.Utilities;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Map;

/**
 * Allow the user to create a new event by entering all appropriate details.
 * @author Li Liu
 */
public class AddEventServlet extends HttpServlet {

    /**
     * Handle get request.
     * Return a page contains boxes that waiting for user to input.
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
            pw.println(EventServletConstants.BODY_OPEN_TAG);
            pw.println(AddEventServletConstants.ADD_BODY);
            pw.println(EventServletConstants.PAGE_FOOTER);
            return;
        }

        // not logged in, let the user to log in
        Utilities.printRequireLogInPage(resp);
    }

    /**
     * Handle post request.
     * Let user to create a new event by entering all appropriate detail.
     * @param req request
     * @param resp response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object data = req.getServletContext().getAttribute("data");
        String id = String.valueOf(((Map<String, String>) data).get("id"));
        int userId = Integer.parseInt(id);
        System.out.println("id is: " + userId);

        String description = req.getParameter("description");
        String title = req.getParameter("title");
        String place = req.getParameter("place");
        int ticket = Integer.parseInt(req.getParameter("ticket"));
        java.sql.Date date = java.sql.Date.valueOf(req.getParameter("date"));
        Time time = Time.valueOf(req.getParameter("time") + ":00");


        try (Connection con = DBCPDataSource.getConnection()) {
            JDBCUtility.executeAddEvent(con, userId, description, date, time, place, title, ticket);
            resp.setStatus(HttpStatus.OK_200);
            PrintWriter writer = resp.getWriter();
            writer.println("<h1>Added a new event.");
            writer.println("<a href=https://61ec-67-169-155-8.ngrok.io/events>See all events</a>");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
