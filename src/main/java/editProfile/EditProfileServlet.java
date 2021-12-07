package editProfile;

import jakarta.servlet.ServletContext;
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
import java.util.Map;

public class EditProfileServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // determine whether the user is already authenticated
        Object clientInfoObj = req.getSession().getAttribute(LoginServerConstants.CLIENT_INFO_KEY);

        if (clientInfoObj != null) {
            // already authed, no need to log in, go to the edit profile page
            resp.setStatus(HttpStatus.OK_200);
            resp.getWriter().println(EditProfileServerConstants.PAGE_HEADER);
            resp.getWriter().println(NavigationBarConstants.NAVI_STYLE);
            resp.getWriter().println(EditProfileServerConstants.NAVI_BODY);
            resp.getWriter().println(EditProfileServerConstants.PAGE_BODY);
            resp.getWriter().println(EditProfileServerConstants.PAGE_FOOTER);
            return;
        }
        Utilities.printRequireLogInPage(resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> data = (Map<String, String>) req.getServletContext().getAttribute("data");
        String email = data.get("email");

        // get parameter
        String name = req.getParameter("name");
        String gender = req.getParameter("gender");
        String location = req.getParameter("location");

        try (Connection con = DBCPDataSource.getConnection()) {
            JDBCUtility.editUserProfile(con, name, email, gender, location);
            ServletContext context = req.getServletContext();
            data.put("name", name);
            data.put("gender", gender);
            data.put("location", location);
            context.setAttribute("data", data);
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
