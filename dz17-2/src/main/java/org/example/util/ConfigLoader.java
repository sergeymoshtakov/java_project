package org.example.util;

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
            e.printStackTrace();
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public String getDbUrl() {
        return getProperty("db.dburl");
    }

    public String getDbUser() {
        return getProperty("db.user");
    }

    public String getDbPassword() {
        return getProperty("db.password");
    }

    public String getDbDriver() {
        return getProperty("db.driver");
    }

    public String getSqlScriptPath() {
        return getProperty("db.sqlScriptCreateTables");
    }
}
