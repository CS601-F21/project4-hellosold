package tickets;

public class TransferTicketServletConstants {
    public static final String PAGE_HEADER = "<!DOCTYPE html>\n" +
            "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
            "<head>\n" +
            "  <title>Transfer Tickets</title>\n" +
            "</head>\n";

    public static final String PAGE_FOOTER = "\n" +
            "</body>\n" +
            "</html>";
    public static String NAVI_BODY = "<ul>\n" +
            "    <li><a href=\"/home\">Home</a></li>\n" +
            "    <li><a href=\"/editprofile\">Edit Profile</a></li>\n" +
            "    <li><a href=\"/events\">Events</a></li>\n" +
            "    <li><a class=\"active\" href=\"/tickets\">Tickets</a></li>\n" +
            "    <li><a href=\"/logout\">Logout</a></li>\n" +
            "</ul>";

    public static String MESSAGE = "<h2>Transfer tickets</h2>" +
            "<h3>Please select one event and fill in this form.</h3>";

    public static String BODY_OPEN_TAG = "<body>\n" + "\n";

    public static String GET_INPUT = "<form action=\"/tickets\" method=\"post\">\n" +
            "\n" +
            "    <label for=\"name\"><b>Name: </b></label>\n" +
            "    <input type=\"text\" placeholder=\"John\" name=\"name\" id=\"name\" required /><br /><br />\n" +
            "\n" +
            "    <label><b>Email: </b></label>\n" +
            "    <input type=\"email\" name=\"email\" id=\"email\" placeholder=\"example@email.com\" required /><br /><br />\n" +
            "\n" +
            "    <label for=\"num\"><b>Number: </b></label>\n" +
            "    <input type=\"number\" placeholder=\"number of tickets\" name=\"num\" id=\"num\" required /><br /><br />\n" +
            "\n";

    public static String SUBMIT_BUTTON = "    <button type=\"submit\" class=\"btn\">Submit</button>\n" +
            "\n" +
            "</form>";

    public static String BUTTON_STYLE = "<style>\n" +
            ".btn {\n" +
            "  border: none;\n" +
            "  color: white;\n" +
            "  padding: 15px 32px;\n" +
            "  text-align: center;\n" +
            "  text-decoration: none;\n" +
            "  display: inline-block;\n" +
            "  font-size: 16px;\n" +
            "  margin: 4px 2px;\n" +
            "  cursor: pointer;\n" +
            "  background-color: #bbded6;\n" +
            "}\n" +
            "</style>";

    public static String POST_PAGE = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "\n" +
            "<head>\n" +
            "    <title>HTML Redirect</title>\n" +
            "    <meta http-equiv=\"refresh\"\n" +
            "          content=\"5; url = https://61ec-67-169-155-8.ngrok.io/home\" />\n" +
            "</head>\n" +
            "\n" +
            "<body>\n" +
            "<h1 style=\"text-align:center;color:green;\">\n" +
            "    Successfully share tickets with friends!\n" +
            "</h1>\n" +
            "\n" +
            "<p style=\"text-align:center;\">\n" +
            "    If your browser supports Refresh,\n" +
            "    you'll be redirected to \n" +
            "    Homepage, in 5 seconds.\n" +
            "</p>\n" +
            "\n" +
            "</body>\n" +
            "\n" +
            "</html>";
}
