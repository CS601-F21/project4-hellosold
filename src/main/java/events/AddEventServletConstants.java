package events;

public class AddEventServletConstants {

    public static String ADD_BODY = "<form action=\"/events/add\" method=\"post\">\n" +
            "  <label for=\"title\">Title:</label>\n" +
            "  <input type=\"text\" id=\"title\" name=\"title\" required=\"required\"/><br/><br/>\n" +
            "  <label for=\"date\">Date:</label>\n" +
            "  <input type=\"date\" id=\"date\" name=\"date\" value=\"2021-12-01\" min=\"2021-01-01\" " +
            "max=\"2022-12-31\" required=\"required\"/><br/><br/>\n" +
            "  <label for=\"time\">Time:</label>\n" +
            "  <input type=\"time\" id=\"time\" name=\"time\" min=\"00:00:00\" max=\"24:00:00\" " +
            "required=\"required\"/><br/><br/>\n" +
            "  <label for=\"place\">Place:</label>\n" +
            "  <input type=\"text\" id=\"place\" name=\"place\" required=\"required\"/><br/><br/>\n" +
            "  <label for=\"ticket\">Total tickets:</label>\n" +
            "  <input type=\"text\" id=\"ticket\" name=\"ticket\" required=\"required\"/><br/><br/>\n" +
            "  <label for=\"description\">Details:</label>\n" +
            "<br/><br/>" +
            "  <input type=\"text\" id=\"description\" name=\"description\" style=\"height:200px; width:500px;\" " +
            "required=\"required\"/><br/><br/>\n" +
            "  <input type=\"submit\" value=\"Submit\"/>\n" +
            "</form>";
}
