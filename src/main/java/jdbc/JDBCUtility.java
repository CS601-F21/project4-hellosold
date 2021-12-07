package jdbc;

import events.Event;
import events.EventServletConstants;
import login.LoginServerConstants;

import java.sql.*;
import java.util.ArrayList;

public class JDBCUtility {

    public static int executeAddNewUser(Connection con, String name, String email) throws SQLException {
        String insertUserSql = "INSERT INTO users (name, email) VALUES (?, ?);";
        PreparedStatement insertUserStmt = con.prepareStatement(insertUserSql);
        insertUserStmt.setString(1, name);
        insertUserStmt.setString(2, email);
        int affectRows = insertUserStmt.executeUpdate();
        if (affectRows == 0) {
            throw new SQLException("Creating user failed, no rows affected.");
        }
        int userId;
        try (ResultSet generatedKeys = insertUserStmt.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                userId = generatedKeys.getInt(1);
                System.out.println("userId is: " + userId);
                return userId;
            } else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
        }
    }

    public static int executeSelectUser(Connection con, String name, String email) throws SQLException {
        String selectUserSql = "SELECT * FROM users WHERE name = ? AND email = ?;";
        PreparedStatement selectUserStmt = con.prepareStatement(selectUserSql);
        selectUserStmt.setString(1, name);
        selectUserStmt.setString(2, email);
        ResultSet results = selectUserStmt.executeQuery();
        int userId = 0;
        if (results.next()) {
            userId = results.getInt("id");
        }
        return userId;
    }

    /**
     * Add a new user to the users table.
     * @param con con
     * @param name name
     * @param email email
     * @param gender gender
     * @param location location
     * @throws SQLException
     */
    public static void editUserProfile(Connection con, String name, String email,
                                         String gender, String location) throws SQLException {
        String updateUserSql = "UPDATE users SET name=?, gender=?, location=? " +
                "WHERE email=?;";
        PreparedStatement updateUserStmt = con.prepareStatement(updateUserSql);
        updateUserStmt.setString(1, name);
        updateUserStmt.setString(2, gender);
        updateUserStmt.setString(3, location);
        updateUserStmt.setString(4, email);
        updateUserStmt.executeUpdate();
    }

    public static ArrayList<Event> executeSelectEvents(Connection con) throws SQLException {
        ArrayList<Event> events = new ArrayList<>();
        String selectAllEvents = "SELECT users.name, events.id, events.title, events.event_date, events.event_time, " +
                "events.num_ticket FROM events JOIN users ON users.id=events.user_id";
        PreparedStatement selectAllEventsStmt = con.prepareStatement(selectAllEvents);
        ResultSet results = selectAllEventsStmt.executeQuery();
        int eventId;
        String sponsor;
        String title;
        Date date;
        Time time;
        int tickets;
        while(results.next()) {
            eventId = results.getInt("id");
            sponsor = results.getString(LoginServerConstants.NAME_KEY);
            title = results.getString(EventServletConstants.TITLE);
            date = results.getDate(EventServletConstants.DATE);
            time = results.getTime(EventServletConstants.TIME);
            tickets = results.getInt(EventServletConstants.NUM_TICKET);

            Event event = new Event();
            event.setId(eventId);
            event.setSponsor(sponsor);
            event.setTitle(title);
            event.setDate(date);
            event.setTime(time);
            event.setTickets(tickets);
            events.add(event);
        }
        return events;
    }

    public static void executeAddEvent(Connection con, int userId, String description, Date date, Time time,
                                       String place, String title) throws SQLException {
        String insertEventSql = "INSERT INTO events (user_id, description, event_date, event_time, event_place, title)" +
                 "VALUES (?, ?, ?, ?, ?, ?);";
        PreparedStatement insertUserStmt = con.prepareStatement(insertEventSql);
        insertUserStmt.setInt(1, userId);
        insertUserStmt.setString(2, description);
        insertUserStmt.setDate(3, date);
        insertUserStmt.setTime(4, time);
        insertUserStmt.setString(5, place);
        insertUserStmt.setString(6, title);
        insertUserStmt.executeUpdate();
    }

    public static Event executeGetAEvent(Connection con, int eventId) throws SQLException {
        String getAEventSql = "SELECT description, event_date, event_time, event_place, title, num_ticket, users.name" +
                " FROM events" + " JOIN users ON users.id=events.user_id AND events.id=?";
        PreparedStatement getEventStmt = con.prepareStatement(getAEventSql);
        getEventStmt.setInt(1, eventId);
        ResultSet results = getEventStmt.executeQuery();

        String host;
        String title;
        String description;
        String place;
        Date date;
        Time time;
        int tickets;
        Event event = null;

        while(results.next()) {
            description = results.getString(EventServletConstants.DESCRIPTION);
            date = results.getDate(EventServletConstants.DATE);
            time = results.getTime(EventServletConstants.TIME);
            place = results.getString(EventServletConstants.PLACE);
            title = results.getString(EventServletConstants.TITLE);
            tickets = results.getInt(EventServletConstants.NUM_TICKET);
            host = results.getString(LoginServerConstants.NAME_KEY);

            event = new Event(eventId, title, host, description, date, time, place, tickets);
        }
        return event;
    }

    public static void executeBuyTicket(Connection con, int uerId, int eventId) throws SQLException {
        String buyTicketSql = "INSERT INTO purchases (user_id, event_id) VALUES (?,?);";
        PreparedStatement buyTicketStmt = con.prepareStatement(buyTicketSql);
        buyTicketStmt.setInt(1, uerId);
        buyTicketStmt.setInt(2, eventId);
        int result = buyTicketStmt.executeUpdate();
        System.out.println(result);
    }
}
