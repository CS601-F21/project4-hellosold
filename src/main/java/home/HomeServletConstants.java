package home;

/**
 * Constants that would be used for HomeServlet class.
 *
 * @author Li Liu
 */
public class HomeServletConstants {
    public static final String PAGE_HEADER = """
            <!DOCTYPE html>
            <html xmlns="http://www.w3.org/1999/xhtml">
            <head>
              <title>Home</title>
            </head>
            <body>
            """;

    public static final String PAGE_FOOTER = """

            </body>
            </html>""";

    public static final String NAVI_BODY = """
            <ul>
                <li><a class="active" href="/">Home</a></li>
                <li><a href="/editprofile">Edit Profile</a></li>
                <li><a href="/events">Events</a></li>
                <li><a href="/tickets">Transfer Tickets</a></li>
                <li><a href="/logout">Logout</a></li>
            </ul>""";
    public static final String HOME_STYLE = """
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
                color: #d9d9f3;
            }
            h4 {
                color:#9dd3a8;
            }
            </style>""";

}
