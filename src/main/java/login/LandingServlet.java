package login;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.eclipse.jetty.http.HttpStatus;
import utilities.Config;
import utilities.LoginUtilities;
import utilities.Utilities;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Landing page that allows a user to request to login with Slack.
 */
public class LandingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        // retrieve the ID of this session
        String sessionId = req.getSession(true).getId(); // cookie

        // determine whether the user is already authenticated
        Map<String, String> data = Utilities.isLoggedIn(req, sessionId);
        if (data != null) {
            // already authed, no need to log in
            resp.getWriter().println(LoginServerConstants.PAGE_HEADER);
            resp.getWriter().println("<h1>You have already been authenticated</h1>");
            resp.getWriter().println("<a href=\"/home\">Go to Home</a>");
            resp.getWriter().println(LoginServerConstants.PAGE_FOOTER);
            return;
        }

        // retrieve the config info from the context
        Config config = (Config) req.getServletContext().getAttribute(LoginServerConstants.CONFIG_KEY);

        /** From the OpenID spec:
         * state
         * RECOMMENDED. Opaque value used to maintain state between the request and the callback.
         * Typically, Cross-Site Request Forgery (CSRF, XSRF) mitigation is done by cryptographically
         * binding the value of this parameter with a browser cookie.
         *
         * Use the session ID for this purpose.
         */
        String state = sessionId;

        /** From the Open ID spec:
         * nonce
         * OPTIONAL. String value used to associate a Client session with an ID Token, and to mitigate
         * replay attacks. The value is passed through unmodified from the Authentication Request to
         * the ID Token. Sufficient entropy MUST be present in the nonce values used to prevent attackers
         * from guessing values. For implementation notes, see Section 15.5.2.
         */
        String nonce = LoginUtilities.generateNonce(state);

        // Generate url for request to Slack
        String url = LoginUtilities.generateSlackAuthorizeURL(config.getClient_id(),
                state,
                nonce,
                config.getRedirect_uri());

        resp.setStatus(HttpStatus.OK_200);
        PrintWriter writer = resp.getWriter();
        writer.println(LoginServerConstants.LANDING_PAGE);
        writer.println("<a href=\""+url+"\"><img src=\"" + LoginServerConstants.BUTTON_URL +"\"/></a>\n</div>");
        writer.println(LoginServerConstants.PAGE_FOOTER);
    }


}
