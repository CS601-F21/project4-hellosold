package events;

public class AddEventServletConstants {

    public static String INPUT_STYLE = "<style>\ninput {\n" +
            "  padding: 6px;\n" +
            "  text-align: left;\n" +
            "}\n</style>";

    public static String ADD_BODY = "<form action=\"/events/add\" method=\"post\" enctype=\"multipart/form-data\">\n" +
            "    <label for=\"title\">Title:</label>\n" +
            "    <input type=\"text\" id=\"title\" name=\"title\" required=\"required\" /> <br/><br/>\n" +
            "\n" +
            "    <label for=\"date\">Date:</label>\n" +
            "    <input type=\"date\" id=\"date\" name=\"date\" required=\"required\" /> <br/><br/>\n" +
            "\n" +
            "    <label for=\"time\">Time:</label>\n" +
            "    <input type=\"time\" id=\"time\" name=\"time\" min=\"00:00:00\" max=\"24:00:00\" required=\"required\" /> <br/><br/>\n" +
            "\n" +
            "    <label for=\"place\">Place:</label>\n" +
            "    <input type=\"text\" id=\"place\" name=\"place\" required=\"required\" /> <br/><br/>\n" +
            "\n" +
            "    <label for=\"ticket\">Total tickets:</label>\n" +
            "    <input type=\"number\" id=\"ticket\" name=\"ticket\" required=\"required\" /> <br/><br/>\n" +
            "\n" +
            "    <label for=\"description\">Description:</label>\n" +
            "    <input type=\"text\" id=\"description\" name=\"description\" style=\"height:200px; width:500px;\" required=\"required\" />\n" +
            "    <br/><br/>\n" +
            "\n" +
            "    <label for=\"file\">Total tickets:</label>\n" +
            "    <input type=\"file\" id=\"file\" name=\"file\" required=\"required\" /> <br/>\n" +
            "    \n" +
            "    <input type=\"submit\" value=\"submit\"/> <br/>\n" +
            "</form>";

    public static String TITLE = "title";
    public static String DATE = "date";
    public static String TIME = "time";
    public static String PLACE = "place";
    public static String TICKET = "ticket";
    public static String DESCRIPTION = "description";
    public static String FILE = "file";
    public static String PATH = "/Users/liuli/IdeaProjects/project4-hellosold/images/";

    public static String ALERT = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
            "<style>\n" +
            ".alert {\n" +
            "  padding: 20px;\n" +
            "  background-color: #f44336;\n" +
            "  color: white;\n" +
            "}\n" +
            "\n" +
            ".closebtn {\n" +
            "  margin-left: 15px;\n" +
            "  color: white;\n" +
            "  font-weight: bold;\n" +
            "  float: right;\n" +
            "  font-size: 22px;\n" +
            "  line-height: 20px;\n" +
            "  cursor: pointer;\n" +
            "  transition: 0.3s;\n" +
            "}\n" +
            "\n" +
            ".closebtn:hover {\n" +
            "  color: black;\n" +
            "}\n" +
            "</style>\n" +
            "</head>\n" +
            "<body>\n" +
            "\n" +
            "<h2>Error Messages</h2>\n" +
            "\n" +
            "<p>Click on the \"x\" symbol to close the alert message.</p>\n" +
            "<div class=\"alert\">\n" +
            "  <span class=\"closebtn\" onclick=\"this.parentElement.style.display='none';\">&times;</span> \n" +
            "  <strong>Ooops!</strong> File not upload successfully.\n" +
            "</div>\n" +
            "\n" +
            "</body>\n" +
            "</html>";
}
