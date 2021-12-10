package home;

/**
 * Constants that would be used for HomeServlet class.
 *
 * @author Li Liu
 */
public class HomeServletConstants {
    public static final String PAGE_HEADER = "<!DOCTYPE html>\n" +
            "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
            "<head>\n" +
            "  <title>Home</title>\n" +
            "</head>\n" +
            "<body>\n";

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

}
