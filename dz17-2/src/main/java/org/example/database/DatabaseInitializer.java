package org.example.database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {
    public void runSqlScript(String scriptPath) {
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             BufferedReader reader = new BufferedReader(new FileReader(scriptPath))) {

            StringBuilder sql = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sql.append(line).append("\n");
            }

            statement.execute(sql.toString());
            System.out.println("Tables created successfully.");

        } catch (SQLException | IOException e) {
            System.out.println("Exception while initializing database: " + e.getMessage());
        }
    }
}
