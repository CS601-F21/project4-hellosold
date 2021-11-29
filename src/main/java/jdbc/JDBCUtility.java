package jdbc;

import utilities.UserInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCUtility {

    public static void executeAddNewUser(Connection con, String name, String email) throws SQLException {
        String insertUserSql = "INSERT INTO users (name, email) VALUES (?, ?);";
        PreparedStatement insertUserStmt = con.prepareStatement(insertUserSql);
        insertUserStmt.setString(1, name);
        insertUserStmt.setString(2, email);
        insertUserStmt.executeUpdate();
    }

    public static boolean executeSelectUser(Connection con, String name, String email) throws SQLException {
        String selectUserSql = "SELECT * FROM users WHERE name = ? AND email = ?;";
        PreparedStatement selectUserStmt = con.prepareStatement(selectUserSql);
        selectUserStmt.setString(1, name);
        selectUserStmt.setString(2, email);
        ResultSet results = selectUserStmt.executeQuery();
        return results.next();
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

}
