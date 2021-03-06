package tickets;


import events.EventServletConstants;
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
import java.util.List;
import java.util.Map;


/**
 * This class handles request of transferring tickets to user's friend.
 * When a user wants to send some tickets to his/her friend, the other user's name, email, event's title and
 * the number of tickets that the user would like to give are required.
 *
 * When successfully transfer, redirect to the user to home page.
 *
 * @author Li Liu
 */
public class TransferTicketServlet extends HttpServlet {

    /**
     * Get the page of transfer tickets.
     * @param req request
     * @param resp response
     * @throws IOException IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // determine whether the user is already authenticated
        String sessionId = req.getSession().getId();
        Map<String, String> data = Utilities.isLoggedIn(req, sessionId);
        PrintWriter writer = resp.getWriter();

        if (data!= null) {
            // already authed, no need to log in, go to the edit profile page
            resp.setStatus(HttpStatus.OK_200);
            writer.println(TransferTicketServletConstants.PAGE_HEADER);
            writer.println(TransferTicketServletConstants.STYLE);
            writer.println(TransferTicketServletConstants.BODY_OPEN_TAG);
            writer.println(TransferTicketServletConstants.NAVI_BODY);
            writer.println(TransferTicketServletConstants.MESSAGE);
            writer.println(TransferTicketServletConstants.GET_INPUT);
            getAndPrintAllEventTitle(writer);
            writer.println(TransferTicketServletConstants.SUBMIT_BUTTON);
            writer.println(EventServletConstants.PAGE_FOOTER);
            return;
        }
        // redirect to landing page
        Utilities.printRequireLogInPage(resp);
    }

    /**
     * Get and print all event title.
     * @param writer writer
     */
    private void getAndPrintAllEventTitle(PrintWriter writer) {
        try(Connection con = DBCPDataSource.getConnection()) {
            List<String> titles = JDBCUtility.selectAllEventTitle(con);
            if (titles.size() != 0) {
                for (String t : titles)
                    writer.println("<input type=\"radio\" id=\"1\" name=\"title\" value=\"" + t + "\"/>\n" +
                            "    <label class=\"label2\">" + t + "</label></br>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Handle transfer request.
     * @param req request
     * @param resp response
     * @throws IOException IOException
     */
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // get user id
        int userId = Utilities.getUserId(req);
        PrintWriter writer = resp.getWriter();

        // get event title, name, email and num from payload
        String eventTitle = req.getParameter(EventServletConstants.TITLE);
        String toName = req.getParameter(LoginServerConstants.NAME_KEY);
        String toEmail = req.getParameter(LoginServerConstants.EMAIL_KEY);
        int num = Integer.parseInt(req.getParameter(EventServletConstants.NUM));

        try (Connection con = DBCPDataSource.getConnection()) {
            resp.setStatus(HttpStatus.OK_200);
            int toId = JDBCUtility.executeFindUser(con, toName, toEmail);
            if (toId == 0)
                writer.println("<p>User doesn't exist, please check make sure the input name and email are correct.</p>");
            else {
                //Connection con, int toUser, String eventTitle, int num, int fromUser
                boolean result = JDBCUtility.executeTransfer(con, toId, eventTitle, num, userId);
                if (result) {
                    writer.println(TransferTicketServletConstants.POST_PAGE);
                } else {
                    writer.println("<p>You don't have enough tickets.</p>");
                }
            }
        } catch (SQLException e) {
            resp.setStatus(HttpStatus.INTERNAL_SERVER_ERROR_500);
            writer.println(e.getMessage());
        }
    }

}
