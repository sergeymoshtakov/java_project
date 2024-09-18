package org.example.notice;

import org.example.notice.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InfoReader {

    private static final String SELECT_SQL = "SELECT id, message, type, processed FROM notice WHERE type = 'INFO' AND processed = false";
    private static final String DELETE_SQL = "DELETE FROM notice WHERE id = ?";

    public static void main(String[] args) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            while (true) {
                boolean hasMessages = false;

                try (PreparedStatement statement = connection.prepareStatement(SELECT_SQL);
                     ResultSet rs = statement.executeQuery()) {

                    while (rs.next()) {
                        hasMessages = true;

                        int id = rs.getInt("id");
                        String message = rs.getString("message");
                        String type = rs.getString("type");
                        boolean processed = rs.getBoolean("processed");

                        System.out.println("Message: " + message + "type: " + type + ", processed: " + processed);

                        try (PreparedStatement deleteStmt = connection.prepareStatement(DELETE_SQL)) {
                            deleteStmt.setInt(1, id);
                            deleteStmt.executeUpdate();
                        }
                    }

                    if (!hasMessages) {
                        System.out.println("No more messages to process. Exiting...");
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
