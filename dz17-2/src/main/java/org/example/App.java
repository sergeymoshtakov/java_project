package org.example;

import org.example.database.DatabaseConnection;
import org.example.database.DatabaseInitializer;
import org.example.menu.CoffeeMenu;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) {
        String configFilePath = "src/main/resources/config.properties";
        DatabaseConnection.initialize(configFilePath);

        DatabaseInitializer dbInitializer = new DatabaseInitializer();
        String sqlScriptPath = "src/main/resources/createTables.sql";
        dbInitializer.runSqlScript(sqlScriptPath);

        CoffeeMenu coffeeMenu = new CoffeeMenu();
        coffeeMenu.start();
    }
}
