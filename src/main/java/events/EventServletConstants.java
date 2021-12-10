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
            "  <title>Event PAGES</title>\n" +
            "</head>\n";

    public static final String PAGE_FOOTER = "\n" +
            "</body>\n" +
            "</html>";

    public static String NAVI_BODY = "<ul>\n" +
            "    <li><a href=\"/home\">Home</a></li>\n" +
            "    <li><a href=\"/editprofile\">Edit Profile</a></li>\n" +
            "    <li><a class=\"active\" href=\"/events\">Events</a></li>\n" +
            "    <li><a href=\"/tickets\">Transfer Tickets</a></li>\n" +
            "    <li><a href=\"/logout\">Logout</a></li>\n" +
            "</ul>";

    public static String EVENTS_STYLE = "<style>\n" +
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
            "  background-color: black;\n" +
            "  color: black;\n" +
            "  border: 2px solid #fae3d9;  border-radius: 12px;  background-color: #ffb6b9;\n" +
            "  color: white;}.button1 {\n" +
            "  background-color: #bbded6;\n" +
            "  color: black;\n" +
            "  border: 2px solid #bbded6;\n" +
            "  border-radius: 12px;  color: white;}.button2 {\n" +
            "  background-color: #A9A9A9;\n" +
            "  color: black;\n" +
            "  border: 2px solid #A9A9A9;\n" +
            "  border-radius: 12px;  color: white;}\n" +
            "\n" +
            "  a.one:link {color:#ffb6b9;}\n" +
            "  a.one:visited {color:#0000ff;}\n" +
            "  a.one:hover {color:#ffcc00;}\n" +
            "</style>";

    public static String Edit_EVENT_STYLE = "<style>\n" +
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
            "input[type=text] {\n" +
            "  width: 30%;\n" +
            "  padding: 12px 20px;\n" +
            "  margin: 8px 0;\n" +
            "  box-sizing: border-box;\n" +
            "  border: 2px solid #ccc;\n" +
            "  border-radius: 4px;\n" +
            "}\n" +
            "\n" +
            "input[type=date] {\n" +
            "  width: 10%;\n" +
            "  padding: 12px 20px;\n" +
            "  margin: 8px 0;\n" +
            "  box-sizing: border-box;\n" +
            "  border: 2px solid #ccc;\n" +
            "  border-radius: 4px;\n" +
            "}\n" +
            "input[type=time] {\n" +
            "  width: 10%;\n" +
            "  padding: 12px 20px;\n" +
            "  margin: 8px 0;\n" +
            "  box-sizing: border-box;\n" +
            "  border: 2px solid #ccc;\n" +
            "  border-radius: 4px;\n" +
            "}\n" +
            "\n" +
            "input[type=number] {\n" +
            "  width: 30%;\n" +
            "  padding: 12px 20px;\n" +
            "  margin: 8px 0;\n" +
            "  box-sizing: border-box;\n" +
            "  border: 2px solid #ccc;\n" +
            "  border-radius: 4px;\n" +
            "}\n" +
            "\n" +
            "textarea {\n" +
            "  width: 30%;\n" +
            "  height: 150px;\n" +
            "  padding: 12px 20px;\n" +
            "  box-sizing: border-box;\n" +
            "  border: 2px solid #ccc;\n" +
            "  border-radius: 4px;\n" +
            "  background-color: #f8f8f8;\n" +
            "  font-size: 16px;\n" +
            "  resize: none;\n" +
            "}\n" +
            "label {\n" +
            "    color: #ffb6b9;\n" +
            "    font-weight: bold;\n" +
            "    display: block;\n" +
            "    width: 150px;\n" +
            "    float: left;\n" +
            "}\n" +
            "input[type=submit] {\n" +
            "  width: 10%;\n" +
            "  padding: 12px 20px;\n" +
            "  margin: 8px 0;\n" +
            "  box-sizing: border-box;\n" +
            "  border: 2px solid #ccc;\n" +
            "  border-radius: 4px;\n" +
            "  background-color:#bbded6;\n" +
            "}\n" +
            "</style>";

    public static String GET_EVENT_STYLE = "<style>\n" +
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
            "h2 {\n" +
            "  font-family: verdana;\n" +
            "  font-size: 200%;\n" +
            "}\n" +
            "\n" +
            "input[type=number] {\n" +
            "  width: 30%;\n" +
            "  padding: 12px 20px;\n" +
            "  margin: 8px 0;\n" +
            "  box-sizing: border-box;\n" +
            "  border: 2px solid #ccc;\n" +
            "  border-radius: 4px;\n" +
            "}\n" +
            "\n" +
            "label {\n" +
            "    color: #8ac6d1;\n" +
            "    font-weight: bold;\n" +
            "    display: block;\n" +
            "    width: 150px;\n" +
            "    float: left;\n" +
            "}\n" +
            "input[type=submit] {\n" +
            "  width: 10%;\n" +
            "  padding: 12px 20px;\n" +
            "  margin: 8px 0;\n" +
            "  box-sizing: border-box;\n" +
            "  border: 2px solid #ccc;\n" +
            "  border-radius: 4px;\n" +
            "  background-color:#bbded6;\n" +
            "}\n" +
            "p {\n" +
            "  width: 60%;\n" +
            "  border: 2px solid #fae3d9;\n" +
            "  padding: 14px;\n" +
            "  border-radius: 4px;\n" +
            "}\n" +
            "</style>";

    public static String BODY_OPEN_TAG = "<body>\n" + "\n";
    public static String EDIT_BUTTON = "    <button class=\"button button1\" type=\"submit\" name=\"edit\" " +
            "value=\"edit\">Edit</button>\n" +
            "</form></th>";

    public static String DELETE_BUTTON = "    <button class=\"button\" type=\"submit\" name=\"delete\" " +
            "value=\"delete\">Delete</button>\n" +
            "</form></th>";

    public static String EDIT_BUTTON_INACTIVE = "  <th><button class=\"button button2\" type=\"submit\" " +
            "name=\"edit\" value=\"edit\">Edit</button></th>";

    public static String DELETE_BUTTON_INACTIVE = "   <th><button class=\"button button2\" type=\"submit\" " +
            "name=\"delete\" value=\"delete\">Delete</button></th>";


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
