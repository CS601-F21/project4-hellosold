package editProfile;

public class EditProfileServerConstants {
    public static final String PAGE_HEADER = "<!DOCTYPE html>\n" +
            "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
            "<head>\n" +
            "  <title>Edit User Profile</title>\n" +
            "<style>\n" +
            "        body {\n" +
            "          font-family: Arial, Helvetica, sans-serif;\n" +
            "          background-color: #fae3d9\n" +
            "        }\n" +
            "\n" +
            "        * {\n" +
            "          box-sizing: border-box;\n" +
            "        }\n" +
            "\n" +
            "        /* Add padding to containers */\n" +
            "        .container {\n" +
            "          padding: 16px;\n" +
            "          background-color: white;\n" +
            "        }\n" +
            "\n" +
            "        /* Full-width input fields */\n" +
            "        input[type=text] {\n" +
            "          width: 100%;\n" +
            "          padding: 15px;\n" +
            "          margin: 5px 0 22px 0;\n" +
            "          display: inline-block;\n" +
            "          border: none;\n" +
            "          background: #f1f1f1;\n" +
            "        }\n" +
            "\n" +
            "        /* Overwrite default styles of hr */\n" +
            "        hr {\n" +
            "          border: 1px solid #f1f1f1;\n" +
            "          margin-bottom: 25px;\n" +
            "        }\n" +
            "\n" +
            "        /* Set a style for the submit button */\n" +
            "        .subbtn {\n" +
            "          background-color: #8ac6d1;\n" +
            "          color: white;\n" +
            "          padding: 16px 20px;\n" +
            "          margin: 8px 0;\n" +
            "          border: none;\n" +
            "          cursor: pointer;\n" +
            "          width: 100%;\n" +
            "          opacity: 0.9;\n" +
            "        }\n" +
            "\n" +
            "        .subbtn:hover {\n" +
            "          opacity: 1;\n" +
            "        }\n" +
            "\n" +
            "    </style>" +
            "</head>\n" +
            "<body>\n" +
            "\n";

    public static String NAVI_BODY = "<ul>\n" +
            "    <li><a href=\"/\">Home</a></li>\n" +
            "    <li><a class=\"active\" href=\"/editprofile\">Edit Profile</a></li>\n" +
            "    <li><a href=\"/events\">Events</a></li>\n" +
            "    <li><a href=\"/tickets\">Tickets</a></li>\n" +
            "    <li><a href=\"/logout\">Logout</a></li>\n" +
            "</ul>";

    public static  final String PAGE_BODY = "<form action=\"/editprofile\" method=\"post\">\n" +
            "    <div class=\"container\">\n" +
            "        <h1>Edit Profile</h1>\n" +
            "        <p>Please fill in this form to change account information.</p>\n" +
            "        <hr/>\n" +
            "\n" +
            "        <label for=\"name\"><b>Name</b></label>\n" +
            "        <input type=\"text\" placeholder=\"Enter Name\" name=\"name\" id=\"name\" required=\"required\" " +
            "/>\n" +
            "\n" +
            "        <label><b>Gender</b></label>\n" +
            "        <input type=\"radio\" name=\"gender\" id=\"gender1\" value=\"male\" required=\"required\" />\n" +
            "        <label for=\"gender1\">male</label>\n" +
            "        <input type=\"radio\" name=\"gender\" id=\"gender2\" value=\"female\" required=\"required\" />\n" +
            "        <label for=\"gender2\">female</label>\n<br></br>" +
            "\n" +
            "        <label for=\"location\"><b>Location</b></label>\n" +
            "        <input type=\"text\" placeholder=\"Enter City\" name=\"location\" id=\"location\" required=\"required\" />\n" +
            "        <hr/>\n" +
            "\n" +
            "        <button type=\"submit\" class=\"subbtn\">Submit</button>\n" +
            "    </div>\n" +
            "\n" +
            "</form>";


    public static final String PAGE_FOOTER = "\n" +
            "</body>\n" +
            "</html>";
}
