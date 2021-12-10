package tickets;

/**
 * Constants used for TransferTicketsServlet class.
 *
 * @author Li Liu
 */
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
            "    <li><a class=\"active\" href=\"/tickets\">Transfer Tickets</a></li>\n" +
            "    <li><a href=\"/logout\">Logout</a></li>\n" +
            "</ul>";

    public static String STYLE = "<style>\n" +
            "\n" +
            "ul {\n" +
            "  list-style-type: none;\n" +
            "  margin: 0;\n" +
            "  padding: 0;\n" +
            "  overflow: hidden;\n" +
            "  background-color: #bbded6;\n" +
            "}\n" +
            "\n" +
            "li {\n" +
            "  float: left;\n" +
            "}\n" +
            "\n" +
            "li a {\n" +
            "  display: block;\n" +
            "  color: white;\n" +
            "  text-align: center;\n" +
            "  padding: 14px 16px;\n" +
            "  text-decoration: none;\n" +
            "}\n" +
            "\n" +
            "li a:hover:not(.active) {\n" +
            "  background-color: #bbded6;\n" +
            "}\n" +
            "\n" +
            ".active {\n" +
            "  background-color: #8ac6d1;\n" +
            "}\n" +
            "\n" +
            "\n" +
            "input[type=number] {\n" +
            "  width: 30%;\n" +
            "  padding: 12px 20px;\n" +
            "  margin: 8px 0;\n" +
            "  box-sizing: border-box;\n" +
            "  border: 2px solid #ccc;\n" +
            "  border-radius: 4px;\n" +
            "}\n" +
            "input[type=email] {\n" +
            "  width: 30%;\n" +
            "  padding: 12px 20px;\n" +
            "  margin: 8px 0;\n" +
            "  box-sizing: border-box;\n" +
            "  border: 2px solid #ccc;\n" +
            "  border-radius: 4px;\n" +
            "}\n" +
            "input[type=text] {\n" +
            "  width: 30%;\n" +
            "  padding: 12px 20px;\n" +
            "  margin: 8px 0;\n" +
            "  box-sizing: border-box;\n" +
            "  border: 2px solid #ccc;\n" +
            "  border-radius: 4px;\n" +
            "}\n" +
            "\n" +
            "\n" +
            ".label1 {\n" +
            "    color: #8ac6d1;\n" +
            "    font-weight: bold;\n" +
            "    display: block;\n" +
            "    width: 150px;\n" +
            "    float: left;\n" +
            "}\n" +
            "\n" +
            ".label2 {\n" +
            "    font-weight: 400;\n" +
            "    font-color:\n" +
            "    width: 150px;\n" +
            "}\n" +
            "\n" +
            "\n" +
            "input[type=submit] {\n" +
            "  width: 10%;\n" +
            "  padding: 12px 20px;\n" +
            "  margin: 8px 0;\n" +
            "  box-sizing: border-box;\n" +
            "  border: 2px solid #ccc;\n" +
            "  border-radius: 4px;\n" +
            "  background-color:#bbded6;\n" +
            "}\n" +
            "\n" +
            "</style>";

    public static String MESSAGE = "<h2>Transfer tickets</h2>" +
            "<h3>Please select one event and fill in this form.</h3>";

    public static String BODY_OPEN_TAG = "<body>\n" + "\n";

    public static String GET_INPUT = "<form action=\"/tickets\" method=\"post\">\n" +
            "    <label class=\"label1\" for=\"name\"><b>Name: </b></label><br/>\n" +
            "    <input type=\"text\" placeholder=\"John\" name=\"name\" id=\"name\" required=\"required\" /><br /><br />\n" +
            "\n" +
            "    <label class=\"label1\"><b>Email: </b></label><br/>\n" +
            "    <input type=\"email\" name=\"email\" id=\"email\" placeholder=\"example@email.com\" required=\"required\" /><br /><br />\n" +
            "\n" +
            "    <label class=\"label1\" for=\"num\"><b>Number: </b></label><br/>\n" +
            "    <input type=\"number\" placeholder=\"number of tickets\" name=\"num\" id=\"num\" required /><br " +
            "/><br />";

    public static String SUBMIT_BUTTON = "    <input type=\"submit\" value=\"Send\"/>\n" +
            "\n" +
            "</form>";

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
            "<h1 style=\"text-align:center;color:#8ac6d1;\">\n" +
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
