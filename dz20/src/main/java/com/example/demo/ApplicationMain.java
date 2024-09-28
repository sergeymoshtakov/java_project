package com.example.demo;

import com.example.demo.database.DatabaseConnection;
import com.example.demo.database.DatabaseInitializer;
import com.example.demo.menu.CoffeeMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApplicationMain implements CommandLineRunner {
    @Autowired
    private CoffeeMenuService coffeeMenuService;

    public static void main(String[] args) {
        String configFilePath = "src/main/resources/application.properties";
        DatabaseConnection.initialize(configFilePath);

        DatabaseInitializer dbInitializer = new DatabaseInitializer();
        String sqlScriptPath = "src/main/resources/createTables.sql";
        dbInitializer.runSqlScript(sqlScriptPath);

        SpringApplication.run(ApplicationMain.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        coffeeMenuService.start();
    }
}
