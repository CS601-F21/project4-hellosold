package jdbc;

import events.Event;
import events.EventServletConstants;
import login.LoginServerConstants;
import tickets.Ticket;
import tickets.TransferTicket;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class contains functions that will be used to insert, update and delete user, events, purchases and transfers
 * tables that maintains the information of users and events.
 *
 * @author Li Liu
 */
public class JDBCUtility {

    /**
     * Add a new user to users table.
     * @param con connection
     * @param name user name
     * @param email user email
     * @return userId
     * @throws SQLException SQLException
     */
    public static int executeAddNewUser(Connection con, String name, String email) throws SQLException {
        String insertUserSql = "INSERT INTO users (name, email) VALUES (?, ?);";
        // Statement, insertUserStmt can be repeated used
        PreparedStatement insertUserStmt = con.prepareStatement(insertUserSql);
        insertUserStmt.setString(1, name);
        insertUserStmt.setString(2, email);
        int affectRows = insertUserStmt.executeUpdate();
        if (affectRows == 0) {
            throw new SQLException("Creating user failed, no rows affected.");
        }

        // Get user id
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

    /**
     * Get a user information by providing the user name and email.
     * @param con con
     * @param name user name
     * @param email user email
     * @return user id
     * @throws SQLException SQLException
     */
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
     * Edit user info of a specific user by providing user email in the users table.
     * @param con con
     * @param name name
     * @param email email
     * @param gender gender
     * @param location location
     * @throws SQLException SQLException
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

    /**
     * Seclect all events from events table.
     * @param con con
     * @return list of events
     * @throws SQLException SQLException
     */
    public static ArrayList<Event> executeSelectEvents(Connection con) throws SQLException {
        ArrayList<Event> events = new ArrayList<>();
        String selectAllEvents = "SELECT users.name, events.id, events.user_id, events.title, events.event_date, " +
                "events.event_time, events.num_ticket FROM events JOIN users ON users.id=events.user_id";
        PreparedStatement selectAllEventsStmt = con.prepareStatement(selectAllEvents);
        ResultSet results = selectAllEventsStmt.executeQuery();
        int eventId;
        String sponsor;
        String title;
        Date date;
        Time time;
        int tickets;
        int userId;

        // Get information of a event, then create a event object
        // Add the event to event list
        while(results.next()) {
            userId = results.getInt(EventServletConstants.USER_ID);
            eventId = results.getInt("id");
            sponsor = results.getString(LoginServerConstants.NAME_KEY);
            title = results.getString(EventServletConstants.TITLE);
            date = results.getDate(EventServletConstants.DATE);
            time = results.getTime(EventServletConstants.TIME);
            tickets = results.getInt(EventServletConstants.NUM_TICKET);

            Event event = new Event();
            event.setUserId(userId);
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

    /**
     * Add a new event to events table.
     * @param con con
     * @param userId userId
     * @param description description
     * @param date date
     * @param time time
     * @param place place
     * @param title title
     * @param numTicket numTicket
     * @throws SQLException SQLException
     */
    public static void executeAddEvent(Connection con, int userId, String description, Date date, Time time,
                                       String place, String title, int numTicket, String imagePath) throws SQLException {
        String insertEventSql = "INSERT INTO events (user_id, description, event_date, event_time, event_place, " +
                "title, num_ticket, image) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement insertUserStmt = con.prepareStatement(insertEventSql);
        insertUserStmt.setInt(1, userId);
        insertUserStmt.setString(2, description);
        insertUserStmt.setDate(3, date);
        insertUserStmt.setTime(4, time);
        insertUserStmt.setString(5, place);
        insertUserStmt.setString(6, title);
        insertUserStmt.setInt(7, numTicket);
        insertUserStmt.setString(8, imagePath);
        insertUserStmt.executeUpdate();
    }

    /**
     * Get info of a event by eventId.
     * @param con con
     * @param eventId eventId
     * @return event
     * @throws SQLException SQLException
     */
    public static Event executeGetAEvent(Connection con, int eventId) throws SQLException {
        String getAEventSql = "SELECT user_id, description, event_date, event_time, event_place, title, num_ticket, " +
                "image, users.name FROM events" + " JOIN users ON users.id=events.user_id AND events.id=?";
        PreparedStatement getEventStmt = con.prepareStatement(getAEventSql);
        getEventStmt.setInt(1, eventId);
        ResultSet results = getEventStmt.executeQuery();

        int userId;
        String host;
        String title;
        String description;
        String place;
        Date date;
        Time time;
        int tickets;
        String imagePath;
        Event event = null;

        // Get all info of the event, then return it
        while(results.next()) {
            userId = results.getInt(EventServletConstants.USER_ID);
            description = results.getString(EventServletConstants.DESCRIPTION);
            date = results.getDate(EventServletConstants.DATE);
            time = results.getTime(EventServletConstants.TIME);
            place = results.getString(EventServletConstants.PLACE);
            title = results.getString(EventServletConstants.TITLE);
            tickets = results.getInt(EventServletConstants.NUM_TICKET);
            host = results.getString(LoginServerConstants.NAME_KEY);
            imagePath = results.getNString(EventServletConstants.IMAGE);

            event = new Event(eventId, userId, title, host, description, date, time, place, tickets);
            event.setImagePath(imagePath);
        }
        return event;
    }

    /**
     * When user buy some tickets, update the events table and purchase table.
     * @param con con
     * @param uerId userId
     * @param eventId eventId
     * @throws SQLException SQLException
     */
    public static int executeBuyTicket(Connection con, int uerId, int eventId, int num) throws SQLException {
        // First check how many tickets left fot this event
        String selectEventSql = "SELECT num_ticket FROM events WHERE id=?";
        PreparedStatement selectStmt = con.prepareStatement(selectEventSql);
        selectStmt.setInt(1, eventId);
        ResultSet resultSet = selectStmt.executeQuery();
        int numTickets = 0;
        while (resultSet.next()) {
            numTickets = resultSet.getInt(EventServletConstants.NUM_TICKET);
        }

        // if there are enough tickets left, check whether this user has purchased the event before,
        // if the user has purchased this event before, update the num column in the purchases table
        // else insert into purchases table
        if (numTickets >= num) {
            // check whether this user has purchased tickets for this event already
            String selectTicketSql = "SELECT id, num FROM purchases where user_id=? AND event_id=?";
            PreparedStatement selectTicketStmt = con.prepareStatement(selectTicketSql);
            selectTicketStmt.setInt(1, uerId);
            selectTicketStmt.setInt(2, eventId);
            ResultSet res = selectTicketStmt.executeQuery();
            int purchaseId = 0;
            int alreadyHave = 0;
            while (res.next()) {
                purchaseId = res.getInt("id");
                alreadyHave = res.getInt(EventServletConstants.NUM);
            }

            // this is the first time for this user to purchase tickets
            if (purchaseId == 0 && alreadyHave == 0) {
                String buyTicketSql = "INSERT INTO purchases (user_id, event_id, num) VALUES (?,?,?);";
                PreparedStatement buyTicketStmt = con.prepareStatement(buyTicketSql);
                buyTicketStmt.setInt(1, uerId);
                buyTicketStmt.setInt(2, eventId);
                buyTicketStmt.setInt(3, num);
                buyTicketStmt.executeUpdate();
            } else {
                // update the purchases table, update num column
                String buyTicketSql = "UPDATE purchases SET num=? WHERE id=?;";
                PreparedStatement buyTicketStmt = con.prepareStatement(buyTicketSql);
                buyTicketStmt.setInt(1, num + alreadyHave);
                buyTicketStmt.setInt(2, purchaseId);
                buyTicketStmt.executeUpdate();
            }

            // Update tickets for the event
            String updateEventSql = "UPDATE events SET num_ticket=? WHERE id=?;";
            PreparedStatement updateStmt = con.prepareStatement(updateEventSql);
            updateStmt.setInt(1, numTickets - num);
            updateStmt.setInt(2, eventId);
            return updateStmt.executeUpdate();
        } else {
            return 0;
        }

    }

    /**
     * Get the total number of tickets that a user has purchased for an event.
     * @param con con
     * @param userId userId
     * @param eventId eventId
     * @return number of tickets
     * @throws SQLException SQLException
     */
    public static int executedGetNumOfTickets(Connection con, int userId, int eventId) throws SQLException {
        String ticketSql = "SELECT num FROM purchases WHERE user_id=? AND event_id=?;";
        PreparedStatement ticketStmt = con.prepareStatement(ticketSql);
        ticketStmt.setInt(1, userId);
        ticketStmt.setInt(2, eventId);
        ResultSet resultSet = ticketStmt.executeQuery();

        int total = 0;
        while (resultSet.next()) {
            int number = resultSet.getInt(EventServletConstants.NUM);
            total += number;
        }
        System.out.println("total number of tickers that the user has purchased is: " + total);
        return total;
    }

    public static List<Ticket> executeGetAllPurchasedTickets(Connection con, int userId) throws SQLException {
        String getAllPurchasedTickets = "SELECT events.event_date, events.event_time, events.title, events.num_ticket,"
                + " events.event_place, purchases.num FROM events JOIN purchases ON events.id=purchases.event_id WHERE"
                + " purchases.user_id=?";
        PreparedStatement allPurchasedTickets = con.prepareStatement(getAllPurchasedTickets);
        allPurchasedTickets.setInt(1, userId);
        ResultSet resultSet = allPurchasedTickets.executeQuery();

        List<Ticket> tickets = new ArrayList<>();
        while (resultSet.next()) {
            String title = resultSet.getString(EventServletConstants.TITLE);
            Time time = resultSet.getTime(EventServletConstants.TIME);
            Date date = resultSet.getDate(EventServletConstants.DATE);
            String place = resultSet.getString(EventServletConstants.PLACE);
            int numPurchased = resultSet.getInt(EventServletConstants.NUM);
            int leftTickets = resultSet.getInt(EventServletConstants.NUM_TICKET);
            Event e = new Event();
            e.setTickets(leftTickets);
            e.setTime(time);
            e.setDate(date);
            e.setPlace(place);
            e.setTitle(title);

            Ticket ticket = new Ticket(e, numPurchased);
            tickets.add(ticket);
        }
        return tickets;
    }

    /**
     * Delete an existing event in events table.
     * @param con con
     * @param eventId eventID
     * @throws SQLException SQLException
     */
    public static void executeDeleteEvent(Connection con, int eventId) throws SQLException {
        String deleteEvent = "DELETE FROM events WHERE id=?";
        PreparedStatement deleteStmt = con.prepareStatement(deleteEvent);
        deleteStmt.setInt(1, eventId);
        deleteStmt.executeUpdate();
    }

    /**
     * Get all event titles.
     * @param con con
     * @return list of event titles
     */
    public static ArrayList<String> selectAllEventTitle(Connection con) {
        ArrayList<String> titles = new ArrayList<>();
        String selectEvents = "SELECT title FROM events;";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(selectEvents);
            while (rs.next()) {
                titles.add(rs.getString(EventServletConstants.TITLE));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return titles;


    }

    /**
     * Find uerId by giving user's email and user's name.
     * @param con con
     * @param name name
     * @param email email
     * @return user id
     * @throws SQLException SQLException
     */
    public static int executeFindUser(Connection con, String name, String email)
            throws SQLException{
        int userId = 0;
        String findUser = "SELECT id From users WHERE email=? AND name=?";
        PreparedStatement findStmt = con.prepareStatement(findUser);
        findStmt.setString(1, email);
        findStmt.setString(2, name);
        ResultSet rs = findStmt.executeQuery();
        while (rs.next()) {
            userId = rs.getInt("id");
        }
        return userId;
    }

    /**
     * Handle transfer tickets.
     * @param con con
     * @param toUser toUser
     * @param eventTitle eventTitle
     * @param num num
     * @param fromUser fromUser
     * @return true if transfer successfully, otherwise false
     * @throws SQLException SQLException
     */
    public static boolean executeTransfer(Connection con, int toUser, String eventTitle, int num, int fromUser) throws SQLException {
        // check num of tickets that the fromUser has
        String getNum = "SELECT * FROM purchases WHERE purchases.event_id=(SELECT " +
                "id FROM events WHERE events.title=?) AND purchases.user_id=?";
        PreparedStatement getNumStmt = con.prepareStatement(getNum);
        getNumStmt.setString(1, eventTitle);
        getNumStmt.setInt(2, fromUser);
        ResultSet rs = getNumStmt.executeQuery();
        int total = 0;
        int ticketId = 0;
        int eventId = 0;
        int numTicket;
        while (rs.next()) {
            ticketId = rs.getInt("id");
            eventId = rs.getInt("event_id");
            numTicket = rs.getInt(EventServletConstants.NUM);
            total += numTicket;
        }

        // if there are not enough tickets for the user to transfer, return false
        if (total < num) {
            return false;
        } else if (total == num){
            // the number of tickets that the user has is same as the number of tickets that user wants to transfer,
            // update the purchases table, set user_id to be the be given user
            String updateTicket = "UPDATE purchases SET user_id=? WHERE id=?";
            PreparedStatement updateStmt = con.prepareStatement(updateTicket);
            updateStmt.setInt(1, toUser);
            updateStmt.setInt(2, ticketId);
            updateStmt.executeUpdate();
        } else {
            // update the purchased table, update the number of tickets that the user has
            int newNum = total - num;
            String updateTicket = "UPDATE purchases SET num=? WHERE id=?";
            PreparedStatement updateStmt = con.prepareStatement(updateTicket);
            updateStmt.setInt(1, newNum);
            updateStmt.setInt(2, ticketId);
            updateStmt.executeUpdate();
        }

        // insert into transfers table
        String insertTransfer = "INSERT into transfers (from_user_id, to_user_id, event_id, num) VALUES (?,?,?,?)";
        PreparedStatement insertStmt = con.prepareStatement(insertTransfer);
        insertStmt.setInt(1, fromUser);
        insertStmt.setInt(2, toUser);
        insertStmt.setInt(3, eventId);
        insertStmt.setInt(4, num);
        insertStmt.executeUpdate();
        return true;
    }

    /**
     * Get all transfer tickets data.
     * @param con con
     * @param userId userId
     * @return list of transfer tickets
     * @throws SQLException SQLException
     */
    public static List<TransferTicket> executeGetAllTransfers(Connection con, int userId) throws SQLException {
        String selectTransSql = "SELECT users.name, transfers.num, events.title " +
                "FROM transfers " +
                "JOIN events " +
                "ON transfers.event_id=events.id " +
                "JOIN users " +
                "ON users.id=transfers.to_user_id " +
                "WHERE from_user_id=?";
        PreparedStatement selectStmt = con.prepareStatement(selectTransSql);
        selectStmt.setInt(1, userId);
        ResultSet rs = selectStmt.executeQuery();
        List<TransferTicket> tickets = new ArrayList<>();
        String toUser;
        int num;
        String eventTitle;
        while (rs.next()) {
            toUser= rs.getString(LoginServerConstants.NAME_KEY);
            num = rs.getInt(EventServletConstants.NUM);
            eventTitle = rs.getString(EventServletConstants.TITLE);
            TransferTicket ticket = new TransferTicket(toUser, eventTitle, num);
            tickets.add(ticket);
        }
        return tickets;
    }

    /**
     * Get details of an event.
     * @param connection connection
     * @param eventId eventId
     * @return Event object
     * @throws SQLException SQLException
     */
    public static Event executeGetEvent(Connection connection, int eventId) throws SQLException {
        String selectEvent = "SELECT events.title, events.event_date, events.event_time, events.event_place, " +
                "events.description, events.num_ticket, events.image FROM events " +
                "WHERE events.id=?";
        PreparedStatement eventStmt = connection.prepareStatement(selectEvent);
        eventStmt.setInt(1, eventId);
        ResultSet rs = eventStmt.executeQuery();

        while (rs.next()) {
            String title = rs.getString(EventServletConstants.TITLE);
            Date date = rs.getDate(EventServletConstants.DATE);
            Time time = rs.getTime(EventServletConstants.TIME);
            String place = rs.getString(EventServletConstants.PLACE);
            int numTickets = rs.getInt(EventServletConstants.NUM_TICKET);
            String imagePath = rs.getString(EventServletConstants.IMAGE);
            String description = rs.getString(EventServletConstants.DESCRIPTION);
            Event event = new Event();
            event.setImagePath(imagePath);
            event.setDescription(description);
            event.setTitle(title);
            event.setDate(date);
            event.setTime(time);
            event.setPlace(place);
            event.setTickets(numTickets);
            return event;
        }
        return null;
    }

    /**
     * Update an existing event in events table.
     * @param connection connection
     * @param eventId eventId
     * @param title title
     * @param description description
     * @param date date
     * @param time time
     * @param place place
     * @param tickets tickets
     * @throws SQLException SQLException
     */
    public static void executeUpdateEvent(Connection connection, int eventId, String title, String description,
                                          Date date, Time time, String place, int tickets) throws SQLException {
        String updateEventSql = "UPDATE events SET events.description=?, events.event_date=?, " +
                "events.event_time=?, events.event_place=?, events.title=?, events.num_ticket=? " +
                "WHERE events.id=?";
        PreparedStatement updateEventStmt = connection.prepareStatement(updateEventSql);
        updateEventStmt.setString(1, description);
        updateEventStmt.setDate(2, date);
        updateEventStmt.setTime(3, time);
        updateEventStmt.setString(4, place);
        updateEventStmt.setString(5, title);
        updateEventStmt.setInt(6, tickets);
        updateEventStmt.setInt(7, eventId);
        updateEventStmt.executeUpdate();

    }
}
