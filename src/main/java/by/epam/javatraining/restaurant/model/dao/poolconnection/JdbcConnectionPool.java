package by.epam.javatraining.restaurant.model.dao.poolconnection;

import by.epam.javatraining.restaurant.util.ConfigurationDB;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.epam.javatraining.restaurant.util.ConfigurationDB.*;

public class JdbcConnectionPool {
    private static final Logger LOGGER = Logger.getRootLogger();

    private List<Connection> availableConnections = new ArrayList<>();

    public JdbcConnectionPool() {
        initializeConnectionPool();
    }

    private void initializeConnectionPool() {
        while (!checkIfConnectionPoolIsFull()) {
            availableConnections.add(createNewConnectionForPool());
        }
    }

    private synchronized boolean checkIfConnectionPoolIsFull() {
        final int MAX_POOL_SIZE = Integer.parseInt(ConfigurationDB.getProp(DB_MAX_CONNECTIONS));

        return availableConnections.size() >= MAX_POOL_SIZE;
    }

    private Connection createNewConnectionForPool() {
        try {
            Class.forName(ConfigurationDB.getProp(DB_DRIVER));
            return DriverManager.getConnection(ConfigurationDB.getProp(DB_URL),
                    ConfigurationDB.getProp(DB_USER_NAME),
                    ConfigurationDB.getProp(DB_PASSWORD));
        } catch (ClassNotFoundException | SQLException e) {
            LOGGER.error(e);
        }
        return null;

    }

    public synchronized Connection getConnectionFromPool() {
        Connection connection = null;
        if (availableConnections.size() > 0) {
            connection = availableConnections.get(0);
            availableConnections.remove(0);
        }
        return connection;
    }

    public synchronized void returnConnectionToPool(Connection connection) {
        if (connection != null) {
            availableConnections.add(connection);
        }
    }
}

