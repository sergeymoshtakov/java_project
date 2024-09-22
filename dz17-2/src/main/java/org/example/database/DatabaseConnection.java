package org.example.database;

import org.example.util.ConfigLoader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private static ConfigLoader configLoader;

    public static void initialize(String configFilePath) {
        configLoader = new ConfigLoader(configFilePath);
        try {
            Class.forName(configLoader.getDbDriver());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        createDatabaseIfNotExists();
    }

    private static void createDatabaseIfNotExists() {
        String dbName = "coffee_shop";
        String dbUrlWithoutDB = configLoader.getDbUrl().replace("/" + dbName, "/postgres"); // Подключаемся к системной базе данных postgres

        try (Connection conn = DriverManager.getConnection(
                dbUrlWithoutDB,
                configLoader.getDbUser(),
                configLoader.getDbPassword());
             Statement stmt = conn.createStatement()) {

            String createDbQuery = "CREATE DATABASE " + dbName;
            stmt.executeUpdate(createDbQuery);
            System.out.println("Database created successfully!");

        } catch (SQLException e) {
            if (e.getSQLState().equals("42P04")) {
                System.out.println("Database already exists.");
            } else {
                System.out.println("Exception while creating database: " + e.getMessage());
            }
        }
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                    configLoader.getDbUrl(),
                    configLoader.getDbUser(),
                    configLoader.getDbPassword()
            );
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }
}
