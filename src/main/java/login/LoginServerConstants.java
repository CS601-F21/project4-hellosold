package login;

public class LoginServerConstants {
    public static final String PAGE_HEADER = "<!DOCTYPE html>\n" +
            "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
            "<head>\n" +
            "  <title>Log in with Slack</title>\n" +
            "</head>\n" +
            "<body>\n" +
            "\n";

    public static final String PAGE_FOOTER = "\n" +
            "</body>\n" +
            "</html>";

    public static String NAVI_BODY = "<ul>\n" +
            "    <li><a class=\"active\" href=\"/\">Home</a></li>\n" +
            "    <li><a href=\"/editprofile\">Edit Profile</a></li>\n" +
            "    <li><a href=\"/events\">Events</a></li>\n" +
            "    <li><a href=\"/tickets\">Transfer Tickets</a></li>\n" +
            "    <li><a href=\"/logout\">Logout</a></li>\n" +
            "</ul>";

    public static String REDIRECT_TO_LOG_IN = "    <meta http-equiv=\"refresh\"\n" +
            "          content=\"5; url = https://61ec-67-169-155-8.ngrok.io/landing\" ></meta>\n" +
            "<h1 style=\"text-align:center;color:#ffb6b9;\">\n" +
            "    Please log in.\n" +
            "</h1>\n" +
            "\n" +
            "<p style=\"text-align:center;\">\n" +
            "    If your browser supports Refresh,\n" +
            "    you'll be redirected to Landing Page, in 5 seconds.\n" +
            "</p>";

    public static String LANDING_PAGE = "<!DOCTYPE html>\n" +
            "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
            "<style>\n" +
            "h1{\n" +
            "    display: flex;\n" +
            "    flex-direction: column;\n" +
            "    justify-content: center;\n" +
            "    text-align: center;\n" +
            "}\n" +
            ".div1{\n" +
            "    text-align: center;\n" +
            "}\n" +
            "</style>\n" +
            "<head>\n" +
            "    <title>Welcome!</title>\n" +
            "</head>\n" +
            "\n" +
            "<body>\n" +
            "<h1>Welcome to the Naive Ticket Purchase Web Application.</h1>\n" +
            "<div class=\"div1\">\n" +
            "<img src=\"https://www.abc4.com/wp-content/uploads/sites/4/2018/12/movie-popcorn-entertainment_1530120399830_382133_ver1.0_47007319_ver1.0.jpg\"\n" +
            "     alt=\"tickets-image\"\n" +
            "     style=\"width:600px\"/>\n" +
            "</div>\n" +
            "\n" +
            "<div class=\"div1\">";

    public static final String HOST = "slack.com";
    public static final String AUTH_PATH = "openid/connect/authorize";
    public static final String TOKEN_PATH = "api/openid.connect.token";
    public static final String RESPONSE_TYPE_KEY = "response_type";
    public static final String RESPONSE_TYPE_VALUE= "code";
    public static final String CODE_KEY= "code";
    public static final String SCOPE_KEY = "scope";
    public static final String SCOPE_VALUE = "openid%20profile%20email";
    public static final String CLIENT_ID_KEY = "client_id";
    public static final String CLIENT_SECRET_KEY = "client_secret";
    public static final String STATE_KEY = "state";
    public static final String NONCE_KEY = "nonce";
    public static final String REDIRECT_URI_KEY = "redirect_uri";
    public static final String OK_KEY = "ok";


    public static final String CONFIG_KEY = "config_key";
    public static final String CLIENT_INFO_KEY = "client_info_key";
    public static final String BUTTON_URL = "https://platform.slack-edge.com/img/sign_in_with_slack@2x.png";

    public static final String IS_AUTHED_KEY = "is_authed";
    public static final String NAME_KEY = "name";
    public static final String EMAIL_KEY = "email";

}
