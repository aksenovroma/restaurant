package by.epam.javatraining.restaurant.util;

import java.util.ResourceBundle;

public class Configuration {
    private static final ResourceBundle resourceBundle;

    public static final String DB_USER_NAME = "username";
    public static final String DB_PASSWORD = "password";
    public static final String DB_URL = "url";
    public static final String DB_DRIVER = "driver";
    public static final String DB_MAX_CONNECTIONS = "max-connections";

    static {
        resourceBundle = ResourceBundle.getBundle("database");
    }

    public static String getProp(String key) {
        if (key != null) {
            return resourceBundle.getString(key);
        }
        return null;
    }
}
