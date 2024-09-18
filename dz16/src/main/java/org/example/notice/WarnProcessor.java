package org.example.notice;

import org.example.notice.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WarnProcessor {

    private static final String SELECT_SQL = "SELECT id, message, type, processed FROM notice WHERE type = 'WARN' AND processed = false";
    private static final String UPDATE_SQL = "UPDATE notice SET processed = true WHERE id = ?";

    public static void main(String[] args) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            while (true) {
                boolean hasMessages = false;

                try (PreparedStatement selectStmt = conn.prepareStatement(SELECT_SQL);
                     ResultSet rs = selectStmt.executeQuery()) {

                    while (rs.next()) {
                        hasMessages = true;

                        int id = rs.getInt("id");
                        String message = rs.getString("message");
                        String type = rs.getString("type");
                        boolean processed = rs.getBoolean("processed");

                        System.out.println("Message: " + message + "type: " + type + ", processed: " + processed);

                        try (PreparedStatement updateStmt = conn.prepareStatement(UPDATE_SQL)) {
                            updateStmt.setInt(1, id);
                            updateStmt.executeUpdate();
                        }
                    }

                    if (!hasMessages) {
                        System.out.println("No more WARN messages to process. Exiting...");
                        break;
                    }

                    Thread.sleep(1000);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.err.println("Прерывание потока");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
