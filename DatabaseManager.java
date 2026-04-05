

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



/**
 * Handles all database interactions and logic.
 * Ensures transactions are safely opened and closed.
 */
public class DatabaseManager {
    
    // Adjusted path because the database is moved to the /db/ directory
    private static final String DB_URL = "jdbc:sqlite:db/bank.db";

    /**
     * Initializes the SQLite Database and creates the Users table if missing.
     */
    public static void initDB() {
        try {
            Class.forName("org.sqlite.JDBC");
            // Using Try-With-Resources to automatically close the connection and statement securely
            try (Connection connection = DriverManager.getConnection(DB_URL);
                 Statement stmt = connection.createStatement()) {
                
                String sql = "CREATE TABLE IF NOT EXISTS Users (" +
                             "username TEXT PRIMARY KEY, " +
                             "password TEXT NOT NULL, " +
                             "balance REAL DEFAULT 0.0" +
                             ")";
                stmt.execute(sql);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Database Connection Failed!");
        }
    }

    /**
     * Authenticates a user or registers automatically if the username is new.
     */
    public static boolean authenticateUser(BankingApp app, String username, String password) {
        String checkSql = "SELECT * FROM Users WHERE username = ?";
        
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement checkStmt = connection.prepareStatement(checkSql)) {
             
            checkStmt.setString(1, username);
            
            try (ResultSet rs = checkStmt.executeQuery()) {
                if (rs.next()) {
                    // User found, verify password (Simple plain-text for easy teacher testing)
                    if (rs.getString("password").equals(password)) {
                        app.setCurrentUser(username);
                        app.setBalance(rs.getDouble("balance"));
                        return true;
                    }
                    return false; // Wrong password
                } else {
                    // User not found, automatically register them
                    String insertSql = "INSERT INTO Users (username, password, balance) VALUES (?, ?, 0.0)";
                    try (PreparedStatement insertStmt = connection.prepareStatement(insertSql)) {
                        insertStmt.setString(1, username);
                        insertStmt.setString(2, password);
                        insertStmt.executeUpdate();
                        
                        app.setCurrentUser(username);
                        app.setBalance(0.0);
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Updates the user's balance in the persistent database.
     */
    public static void updateDatabaseBalance(String username, double balance) {
        if (username == null || username.isEmpty()) return;
        
        String sql = "UPDATE Users SET balance = ? WHERE username = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
             
            pstmt.setDouble(1, balance);
            pstmt.setString(2, username);
            pstmt.executeUpdate();
            
        } catch (SQLException e) { 
            e.printStackTrace(); 
        }
    }
}
