package events;

/**
 * Constants used in eventServlet class.
 *
 * @author Li Liu
 */
public class EventServletConstants {
    public static final String PAGE_HEADER = "<!DOCTYPE html>\n" +
            "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
            "<head>\n" +
            "  <title>Log in with Slack</title>\n" +
            "</head>\n";

    public static final String PAGE_FOOTER = "\n" +
            "</body>\n" +
            "</html>";

    public static String NAVI_BODY = "<ul>\n" +
            "    <li><a href=\"/home\">Home</a></li>\n" +
            "    <li><a href=\"/editprofile\">Edit Profile</a></li>\n" +
            "    <li><a class=\"active\" href=\"/events\">Events</a></li>\n" +
            "    <li><a href=\"/tickets\">Tickets</a></li>\n" +
            "    <li><a href=\"/logout\">Logout</a></li>\n" +
            "</ul>";

    public static String TABLE_STYLE = "<style>\n" +
            "table {\n" +
            "  border-collapse: collapse;\n" +
            "  width: 100%;\n" +
            "}\n" +
            "\n" +
            "th, td {\n" +
            "  padding: 8px;\n" +
            "  text-align: left;\n" +
            "  border-bottom: 1px solid #DDD;\n" +
            "}\n" +
            "\n" +
            "tr:hover {background-color: #D6EEEE;}\n" +
            "button {\n" +
            "  border: none;\n" +
            "  color: white;\n" +
            "  padding: 10px 12px;\n" +
            "  text-align: center;\n" +
            "  text-decoration: none;\n" +
            "  display: inline-block;\n" +
            "  font-size: 12px;\n" +
            "  margin: 4px 2px;\n" +
            "  transition-duration: 0.4s;\n" +
            "  cursor: pointer;\n" +
            "  background-color: black; \n" +
            "  color: black; \n" +
            "  border: 2px solid #fae3d9;" +
            "  border-radius: 12px;" +
            "  background-color: #ffb6b9;\n" +
            "  color: white;" +
            "}" +
            ".button1 {\n" +
            "  background-color: #bbded6; \n" +
            "  color: black; \n" +
            "  border: 2px solid #bbded6;\n" +
            "  border-radius: 12px;" +
            "  color: white;" +
            "}" +
            "\n</style>";

    public static String BODY_OPEN_TAG = "<body>\n" + "\n";
    public static String EDIT_BUTTON = "    <button class=\"button button1\" type=\"submit\" name=\"edit\" " +
            "value=\"edit\">Edit</button>\n" +
            "</form></th>";

    public static String DELETE_BUTTON = "    <button class=\"button\" type=\"submit\" name=\"delete\" " +
            "value=\"delete\">Delete</button>\n" +
            "</form></th>";

    public static String sponsor = "user_id";
    public static String TITLE = "title";
    public static String DESCRIPTION = "description";
    public static String DATE = "event_date";
    public static String TIME = "event_time";
    public static String PLACE = "event_place";
    public static String NUM_TICKET = "num_ticket";
    public static String NUM = "num";
    public static String IMAGE = "image";
    public static String USER_ID = "user_id";
}
