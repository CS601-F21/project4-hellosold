package editProfile;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jdbc.DBCPDataSource;
import jdbc.JDBCUtility;
import login.LoginServerConstants;
import org.eclipse.jetty.http.HttpStatus;
import utilities.Config;
import utilities.LoginUtilities;
import utilities.UserInfo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

public class EditProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // determine whether the user is already authenticated
        Object clientInfoObj = req.getSession().getAttribute(LoginServerConstants.CLIENT_INFO_KEY);

        if (clientInfoObj != null) {
            // already authed, no need to log in, go to the edit profile page
            resp.setStatus(HttpStatus.OK_200);
            resp.getWriter().println(EditProfileServerConstants.PAGE_HEADER);
            resp.getWriter().println(EditProfileServerConstants.PAGE_BODY);
            resp.getWriter().println(EditProfileServerConstants.PAGE_FOOTER);
            return;
        }

        resp.setStatus(HttpStatus.OK_200);
        PrintWriter writer = resp.getWriter();
        writer.println(LoginServerConstants.PAGE_HEADER);
        writer.println("<h1>Please log in</h1>");
        writer.println(LoginServerConstants.PAGE_FOOTER);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object clientInfoObj = req.getSession().getAttribute(LoginServerConstants.CLIENT_INFO_KEY);
        String email = ((UserInfo) clientInfoObj).getEmail();

        String name = req.getParameter("name");
        String gender = req.getParameter("gender");
        String location = req.getParameter("location");

        try (Connection con = DBCPDataSource.getConnection()) {
            JDBCUtility.editUserProfile(con, name, email, gender, location);
            resp.setStatus(HttpStatus.OK_200);
            PrintWriter writer = resp.getWriter();
            writer.println("name is: " + name);
            writer.println("gender is: " + gender);
            writer.println("location is: " + location);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
