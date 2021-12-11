package editProfile;

import jakarta.servlet.ServletContext;
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
import java.util.Map;

/**
 * This class handles editprofile requests.
 *
 * @author Li Liu
 */
public class EditProfileServlet extends HttpServlet {

    /**
     * Get method of path /editprofile
     * @param req request
     * @param resp response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // determine whether the user is already authenticated
        String sessionId = req.getSession().getId();
        Map<String, String> data = Utilities.isLoggedIn(req, sessionId);

        if (data != null) {
            // already authed, no need to log in, go to the edit profile page
            resp.setStatus(HttpStatus.OK_200);
            resp.getWriter().println(EditProfileServerConstants.PAGE_HEADER);
            resp.getWriter().println(EditProfileServerConstants.STYLE);
            resp.getWriter().println(EditProfileServerConstants.NAVI_BODY);
            resp.getWriter().println(EditProfileServerConstants.EDIT_BODY);
            resp.getWriter().println(EditProfileServerConstants.PAGE_FOOTER);
            return;
        }
        Utilities.printRequireLogInPage(resp);
    }

    /**
     * Post method of /editprofile
     * @param req request
     * @param resp response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String sessionId = req.getSession().getId();
        Map<String, String> data = Utilities.isLoggedIn(req, sessionId);
        if (data == null) {
            System.err.println("Something went wrong.");
            return;
        }
        String email = data.get(LoginServerConstants.EMAIL_KEY);

        // get parameter
        String name = req.getParameter(LoginServerConstants.NAME_KEY);
        String gender = req.getParameter(EditProfileServerConstants.GENDER);
        String location = req.getParameter(EditProfileServerConstants.LOCATION);

        try (Connection con = DBCPDataSource.getConnection()) {
            JDBCUtility.editUserProfile(con, name, email, gender, location);
            // update info store in the servletContext
            ServletContext context = req.getServletContext();
            data.put("name", name);
            data.put("gender", gender);
            data.put("location", location);
            context.setAttribute("data", data);
            resp.setStatus(HttpStatus.OK_200);

            // get the page after update user profile
            PrintWriter writer = resp.getWriter();
            writer.println(EditProfileServerConstants.PAGE_HEADER);
            writer.println(EditProfileServerConstants.POST_STYLE);
            writer.println(EditProfileServerConstants.NAVI_BODY);
            writer.println(EditProfileServerConstants.POST_BODY);
            writer.println("<h1>" + name + "</h1>");
            writer.println("<p>" + location + "</p>");
            writer.println("<p>" + gender + "</p>");
            writer.println(EditProfileServerConstants.POST_FOOTER);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
