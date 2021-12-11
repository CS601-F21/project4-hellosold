package login;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.eclipse.jetty.http.HttpStatus;
import utilities.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Implements logic for the /login path where Slack will redirect requests after
 * the user has entered their auth info.
 */
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // retrieve the ID of this session
        String sessionId = req.getSession(true).getId();

        // determine whether the user is already authenticated
        Map<String, String> userInfo = Utilities.isLoggedIn(req, sessionId);
        if (userInfo != null) {
            // already authed, no need to log in
            resp.getWriter().println(LoginServerConstants.PAGE_HEADER);
            resp.getWriter().println("<h1>You have already been authenticated</h1>");
            resp.getWriter().println("<a href=\"/home\">Go to Home</a>");
            resp.getWriter().println(LoginServerConstants.PAGE_FOOTER);
            return;
        }

        // retrieve the config info from the context
        Config config = (Config) req.getServletContext().getAttribute(LoginServerConstants.CONFIG_KEY);

        // retrieve the code provided by Slack
        String code = req.getParameter(LoginServerConstants.CODE_KEY);

        // generate the url to use to exchange the code for a token:
        // After the user successfully grants your app permission to access their Slack profile,
        // they'll be redirected back to your service along with the typical code that signifies
        // a temporary access code. Exchange that code for a real access token using the
        // /openid.connect.token method.
        String url = LoginUtilities.generateSlackTokenURL(config.getClient_id(), config.getClient_secret(), code,
                config.getRedirect_uri());

        // Make the request to the token API
        String responseString = HTTPFetcher.doGet(url, null);
        Map<String, Object> response = LoginUtilities.jsonStrToMap(responseString);

        UserInfo clientInfo = LoginUtilities.verifyTokenResponse(response, sessionId);

        if (clientInfo == null) {
            resp.setStatus(HttpStatus.OK_200);
            resp.getWriter().println(LoginServerConstants.PAGE_HEADER);
            resp.getWriter().println("<h1>Oops, login unsuccessful</h1>");
            resp.getWriter().println(LoginServerConstants.PAGE_FOOTER);
        } else {
            req.getSession().setAttribute(LoginServerConstants.CLIENT_INFO_KEY, clientInfo);
            resp.setStatus(HttpStatus.OK_200);

            // store user info into servlet context
            ServletContext context = req.getServletContext();
            Map<String, Map<String, String>> sessionMap = (Map<String, Map<String, String>>) context.getAttribute(
                    "data");
            if (sessionMap == null) {
                Map<String, Map<String, String>> data = new HashMap<>();
                Map<String, String>  userData = new HashMap<>();
                userData.put("id", String.valueOf(clientInfo.getId()));
                userData.put("name", clientInfo.getName());
                userData.put("email", clientInfo.getEmail());
                data.put(sessionId, userData);
                context.setAttribute("data", data);
            } else {
                Map<String, String>  userData = new HashMap<>();
                userData.put("id", String.valueOf(clientInfo.getId()));
                userData.put("name", clientInfo.getName());
                userData.put("email", clientInfo.getEmail());
                sessionMap.put(sessionId, userData);
                context.setAttribute("data", sessionMap);
            }
            resp.sendRedirect("/home");
        }
    }
}
