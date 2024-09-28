package com.example.demo.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {
    private Properties properties;

    public ConfigLoader(String configFilePath) {
        properties = new Properties();
        try (FileInputStream fis = new FileInputStream(configFilePath)) {
            properties.load(fis);
        } catch (IOException e) {
            System.out.println("Problem loading config file: " + configFilePath);
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public String getDbUrl() {
        return getProperty("spring.datasource.url");
    }

    public String getDbUser() {
        return getProperty("spring.datasource.username");
    }

    public String getDbPassword() {
        return getProperty("spring.datasource.password");
    }

    public String getDbDriver() {
        return getProperty("spring.datasource.driver-class-name");
    }

    public String getSqlScriptPath() {
        return getProperty("spring.datasource.sqlScriptCreateTables");
    }
}
