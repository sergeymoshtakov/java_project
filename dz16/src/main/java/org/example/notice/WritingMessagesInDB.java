package org.example.notice;

import org.example.notice.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Random;

/**
 * Hello world!
 *
 */
public class WritingMessagesInDB
{
    private static final String INSERT_SQL = "INSERT INTO notice (message, type, processed) VALUES (?, ?, ?)";

    public static void main( String[] args )
    {
        try (Connection connection = DatabaseConnection.getConnection()){
            Random random = new Random();
            while(true){
                try(PreparedStatement statement = connection.prepareStatement(INSERT_SQL)){
                    String message;
                    String type;

                    if (random.nextBoolean()) {
                        message = "New message from " + LocalDateTime.now();
                        type = "INFO";
                    } else {
                        message = "Error occurred at " + LocalDateTime.now();
                        type = "WARN";
                    }

                    statement.setString(1, message);
                    statement.setString(2, type);
                    statement.setBoolean(3, false);

                    statement.executeUpdate();
                    System.out.println("Message: " + message + " \nType: " + type);

                    Thread.sleep(1000);
                } catch (SQLException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
