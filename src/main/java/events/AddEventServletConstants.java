package events;

public class AddEventServletConstants {

    public static String ADD_BODY = "<form action=\"/events/add\" method=\"post\">\n" +
            "  <label for=\"title\">Title:</label>\n" +
            "  <input type=\"text\" id=\"title\" name=\"title\" required/><br><br>\n" +
            "  <label for=\"date\">Date:</label>\n" +
            "  <input type=\"date\" id=\"date\" name=\"date\" value=\"2021-12-01\" min=\"2021-01-01\" " +
            "max=\"2022-12-31\" required/><br><br>\n" +
            "  <label for=\"time\">Time:</label>\n" +
            "  <input type=\"time\" id=\"time\" name=\"time\" min=\"00:00:00\" max=\"24:00:00\" required/><br><br>\n" +
            "  <label for=\"place\">Place:</label>\n" +
            "  <input type=\"text\" id=\"place\" name=\"place\" required/><br><br>\n" +
            "  <label for=\"description\">Details:</label>\n" +
            "<br />" +
            "  <input type=\"text\" id=\"description\" name=\"description\" style=\"height:200px; width:500px;\" " +
            "required/><br><br>\n" +
            "  <input type=\"submit\" value=\"Submit\">\n" +
            "</form>";
}
