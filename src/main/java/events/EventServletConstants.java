package events;

/**
 * Constants used in eventServlet class.
 *
 * @author Li Liu
 */
public class EventServletConstants {
    public static final String PAGE_HEADER = """
            <!DOCTYPE html>
            <html xmlns="http://www.w3.org/1999/xhtml">
            <head>
              <title>Event PAGES</title>
            </head>
            """;

    public static final String PAGE_FOOTER = """

            </body>
            </html>""";

    public static final String NAVI_BODY = """
            <ul>
                <li><a href="/home">Home</a></li>
                <li><a href="/editprofile">Edit Profile</a></li>
                <li><a class="active" href="/events">Events</a></li>
                <li><a href="/tickets">Transfer Tickets</a></li>
                <li><a href="/logout">Logout</a></li>
            </ul>""";

    public static final String EVENTS_STYLE = """
            <style>
            ul {
              list-style-type: none;
              margin: 0;
              padding: 0;
              overflow: hidden;
              background-color: #bbded6;
            }

            li {
              float: left;
            }

            li a {
              display: block;
              color: white;
              text-align: center;
              padding: 14px 16px;
              text-decoration: none;
            }

            li a:hover:not(.active) {
              background-color: #bbded6;
            }

            .active {
              background-color: #8ac6d1;
            }

            table {
              border-collapse: collapse;
              width: 100%;
            }

            th, td {
              padding: 8px;
              text-align: left;
              border-bottom: 1px solid #DDD;
            }

            tr:hover {background-color: #D6EEEE;}
            button {
              border: none;
              color: white;
              padding: 10px 12px;
              text-align: center;
              text-decoration: none;
              display: inline-block;
              font-size: 12px;
              margin: 4px 2px;
              transition-duration: 0.4s;
              cursor: pointer;
              background-color: black;
              color: black;
              border: 2px solid #fae3d9;  border-radius: 12px;  background-color: #ffb6b9;
              color: white;}.button1 {
              background-color: #bbded6;
              color: black;
              border: 2px solid #bbded6;
              border-radius: 12px;  color: white;}.button2 {
              background-color: #A9A9A9;
              color: black;
              border: 2px solid #A9A9A9;
              border-radius: 12px;  color: white;}

              a.one:link {color:#ffb6b9;}
              a.one:visited {color:#0000ff;}
              a.one:hover {color:#ffcc00;}
            </style>""";

    public static final String Edit_EVENT_STYLE = """
            <style>
            ul {
              list-style-type: none;
              margin: 0;
              padding: 0;
              overflow: hidden;
              background-color: #bbded6;
            }

            li {
              float: left;
            }

            li a {
              display: block;
              color: white;
              text-align: center;
              padding: 14px 16px;
              text-decoration: none;
            }

            li a:hover:not(.active) {
              background-color: #bbded6;
            }

            .active {
              background-color: #8ac6d1;
            }

            input[type=text] {
              width: 30%;
              padding: 12px 20px;
              margin: 8px 0;
              box-sizing: border-box;
              border: 2px solid #ccc;
              border-radius: 4px;
            }

            input[type=date] {
              width: 10%;
              padding: 12px 20px;
              margin: 8px 0;
              box-sizing: border-box;
              border: 2px solid #ccc;
              border-radius: 4px;
            }
            input[type=time] {
              width: 10%;
              padding: 12px 20px;
              margin: 8px 0;
              box-sizing: border-box;
              border: 2px solid #ccc;
              border-radius: 4px;
            }

            input[type=number] {
              width: 30%;
              padding: 12px 20px;
              margin: 8px 0;
              box-sizing: border-box;
              border: 2px solid #ccc;
              border-radius: 4px;
            }

            textarea {
              width: 30%;
              height: 150px;
              padding: 12px 20px;
              box-sizing: border-box;
              border: 2px solid #ccc;
              border-radius: 4px;
              background-color: #f8f8f8;
              font-size: 16px;
              resize: none;
            }
            label {
                color: #ffb6b9;
                font-weight: bold;
                display: block;
                width: 150px;
                float: left;
            }
            input[type=submit] {
              width: 10%;
              padding: 12px 20px;
              margin: 8px 0;
              box-sizing: border-box;
              border: 2px solid #ccc;
              border-radius: 4px;
              background-color:#bbded6;
            }
            </style>""";

    public static final String GET_EVENT_STYLE = """
            <style>

            ul {
              list-style-type: none;
              margin: 0;
              padding: 0;
              overflow: hidden;
              background-color: #bbded6;
            }

            li {
              float: left;
            }

            li a {
              display: block;
              color: white;
              text-align: center;
              padding: 14px 16px;
              text-decoration: none;
            }

            li a:hover:not(.active) {
              background-color: #bbded6;
            }

            .active {
              background-color: #8ac6d1;
            }
            h2 {
              font-family: verdana;
              font-size: 200%;
            }

            input[type=number] {
              width: 30%;
              padding: 12px 20px;
              margin: 8px 0;
              box-sizing: border-box;
              border: 2px solid #ccc;
              border-radius: 4px;
            }

            label {
                color: #8ac6d1;
                font-weight: bold;
                display: block;
                width: 150px;
                float: left;
            }
            input[type=submit] {
              width: 10%;
              padding: 12px 20px;
              margin: 8px 0;
              box-sizing: border-box;
              border: 2px solid #ccc;
              border-radius: 4px;
              background-color:#bbded6;
            }
            p {
              width: 60%;
              border: 2px solid #fae3d9;
              padding: 14px;
              border-radius: 4px;
            }
            </style>""";

    public static final String BODY_OPEN_TAG = """
            <body>

            """;
    public static final String EDIT_BUTTON = "    <button class=\"button button1\" type=\"submit\" name=\"edit\" " +
            "value=\"edit\">Edit</button>\n" +
            "</form></th>";

    public static final String DELETE_BUTTON = "    <button class=\"button\" type=\"submit\" name=\"delete\" " +
            "value=\"delete\">Delete</button>\n" +
            "</form></th>";

    public static final String EDIT_BUTTON_INACTIVE = "  <th><button class=\"button button2\" type=\"submit\" " +
            "name=\"edit\" value=\"edit\">Edit</button></th>";

    public static final String DELETE_BUTTON_INACTIVE = "   <th><button class=\"button button2\" type=\"submit\" " +
            "name=\"delete\" value=\"delete\">Delete</button></th>";


    public static final String sponsor = "user_id";
    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    public static final String DATE = "event_date";
    public static final String TIME = "event_time";
    public static final String PLACE = "event_place";
    public static final String NUM_TICKET = "num_ticket";
    public static final String NUM = "num";
    public static final String IMAGE = "image";
    public static final String USER_ID = "user_id";
}
