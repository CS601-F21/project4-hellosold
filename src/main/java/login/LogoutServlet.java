package login;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

/**
 * Handles a request to sign out.
 * @author Li Liu
 */
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // log out by invalidating the session
        String sessionId = req.getSession().getId();
        Map<String, Map<String, String>> sessionMap = (Map<String, Map<String, String>>) req.getServletContext().getAttribute("data");
        sessionMap.remove(sessionId);
//        req.getSession().invalidate();
        resp.getWriter().println(LoginServerConstants.PAGE_HEADER);
        resp.getWriter().println("<h1>Thanks for the whole semester, see you in Spring!</h1>");
        resp.getWriter().println(LoginServerConstants.PAGE_FOOTER);
    }

}
