package by.epam.javatraining.restaurant.model.dao.poolconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcConnectionPool {

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
        final int MAX_POOL_SIZE = Configuration.getInstance().DB_MAX_CONNECTIONS;

        return availableConnections.size() >= MAX_POOL_SIZE;
    }

    private Connection createNewConnectionForPool() {
        Configuration config = Configuration.getInstance();
        try {
            Class.forName(config.DB_DRIVER);
            return DriverManager.getConnection(config.DB_URL, config.DB_USER_NAME, config.DB_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
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
        availableConnections.add(connection);
    }
}

