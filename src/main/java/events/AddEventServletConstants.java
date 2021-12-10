package events;

public class AddEventServletConstants {
    public static String STYLE = "<style>\n" +
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
            "input[type=file] {\n" +
            "  width: 30%;\n" +
            "  padding: 12px 20px;\n" +
            "  margin: 8px 0;\n" +
            "  box-sizing: border-box;\n" +
            "  border: 2px solid #ccc;\n" +
            "  border-radius: 4px;\n" +
            "}\n" +
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

    public static String ADD_BODY = "<form action=\"/events/add\" method=\"post\" enctype=\"multipart/form-data\">\n" +
            "    <label for=\"title\">Title:</label><br/>\n" +
            "    <input type=\"text\" id=\"title\" name=\"title\" required=\"required\" /> <br/><br/>\n" +
            "\n" +
            "    <label for=\"date\">Date:</label><br/>\n" +
            "    <input type=\"date\" id=\"date\" name=\"date\" required=\"required\" /> <br/><br/>\n" +
            "\n" +
            "    <label for=\"time\">Time:</label><br/>\n" +
            "    <input type=\"time\" id=\"time\" name=\"time\" min=\"00:00:00\" max=\"24:00:00\" required=\"required\" /> <br/><br/>\n" +
            "\n" +
            "    <label for=\"place\">Place:</label><br/>\n" +
            "    <input type=\"text\" id=\"place\" name=\"place\" required=\"required\" /> <br/><br/>\n" +
            "\n" +
            "    <label for=\"ticket\">Total tickets:</label><br/>\n" +
            "    <input type=\"number\" id=\"ticket\" name=\"ticket\" required=\"required\" /> <br/><br/>\n" +
            "\n" +
            "    <label for=\"description\">Description:</label><br/>\n" +
            "    <textarea type=\"text\" id=\"description\" name=\"description\" rows=\"5\" cols=\"40\"" +
            " required=\"required\"></textarea>\n" +
            "    <br/><br/>\n" +
            "\n" +
            "    <label for=\"file\">Upload a image:</label><br/>\n" +
            "    <input type=\"file\" id=\"file\" name=\"file\" required=\"required\" /> <br/>\n" +
            "    \n" +
            "    <input type=\"submit\" value=\"Add\"/> <br/>\n" +
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
