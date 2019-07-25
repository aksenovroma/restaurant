package by.epam.javatraining.restaurant.model.dao.poolconnection;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class Configuration {
    private static final String PROPERTIES_FILE = "src/main/resources/database.properties";

    public String DB_USER_NAME ;
    public String DB_PASSWORD ;
    public String DB_URL;
    public String DB_DRIVER;
    public Integer DB_MAX_CONNECTIONS;

    public Configuration(){
        init();
    }

    private static Configuration configuration = new Configuration();

    public static Configuration getInstance(){
        return configuration;
    }

    private void init(){
        Properties props = new Properties();
        try(InputStream in = Files.newInputStream(Paths.get(PROPERTIES_FILE))){
            props.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        DB_URL = props.getProperty("url");
        DB_USER_NAME = props.getProperty("username");
        DB_PASSWORD = props.getProperty("password");
        DB_DRIVER = props.getProperty("driver");
        DB_MAX_CONNECTIONS = Integer.parseInt(props.getProperty("max-connections"));
    }
}
