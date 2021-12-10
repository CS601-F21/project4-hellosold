package events;


import jakarta.servlet.MultipartConfigElement;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import jdbc.DBCPDataSource;
import jdbc.JDBCUtility;
import login.LoginServerConstants;
import login.NavigationBarConstants;
import org.eclipse.jetty.http.HttpStatus;
import org.eclipse.jetty.server.Request;
import utilities.Utilities;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Time;


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
     * @throws IOException ioexception
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // determine whether the user is already authenticated
        Object clientInfoObj = req.getSession().getAttribute(LoginServerConstants.CLIENT_INFO_KEY);

        if (clientInfoObj != null) {
            // already authed, no need to log in, go to the edit profile page
            resp.setStatus(HttpStatus.OK_200);
            PrintWriter pw = resp.getWriter();
            pw.println(EventServletConstants.PAGE_HEADER);
            pw.println(NavigationBarConstants.NAVI_STYLE);
            pw.println(AddEventServletConstants.INPUT_STYLE);
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
     * @throws IOException ioexception
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // get user id
        int userId = Utilities.getUserId(req);
        PrintWriter writer = resp.getWriter();
        // store image
        String filePath = uploadImage(req);
        if (filePath == null) {
            writer.println(AddEventServletConstants.ALERT);
            return;
        }

        String description = req.getParameter(AddEventServletConstants.DESCRIPTION);
        String title = req.getParameter(AddEventServletConstants.TITLE);
        String place = req.getParameter(AddEventServletConstants.PLACE);
        int ticket = Integer.parseInt(req.getParameter(AddEventServletConstants.TICKET));
        java.sql.Date date = java.sql.Date.valueOf(req.getParameter(AddEventServletConstants.DATE));
        Time time = Time.valueOf(req.getParameter(AddEventServletConstants.TIME) + ":00");

        try (Connection con = DBCPDataSource.getConnection()) {
            JDBCUtility.executeAddEvent(con, userId, description, date, time, place, title, ticket, filePath);
            resp.setStatus(HttpStatus.OK_200);

            writer.println("<h1>Added a new event.");
            writer.println("<a href=https://61ec-67-169-155-8.ngrok.io/events>See all events</a>");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handle request for uploading images.
     * Store image at the local director /images, then return the name of the image file.
     * https://stackoverflow.com/questions/69094964/embedded-jetty-11-handling-file-upload-and-file-upload-progress
     * https://www.theserverside.com/blog/Coffee-Talk-Java-News-Stories-and-Opinions/Java-File-Upload-Servlet-Ajax-Example
     * @param request request
     * @return image path
     */
    private String uploadImage(HttpServletRequest request) {
        // upload image and then store image under directory /temp
        if (request.getContentType().startsWith("multipart/")) {
            request.setAttribute(Request.__MULTIPART_CONFIG_ELEMENT,
                    new MultipartConfigElement(AddEventServletConstants.PATH));
        }
        try {
            Part filePart = request.getPart(AddEventServletConstants.FILE);
            String fileName = filePart.getSubmittedFileName();
            filePart.write(AddEventServletConstants.PATH + fileName);
            return fileName;
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
