package by.epam.javatraining.restaurant.model.dao;

import by.epam.javatraining.restaurant.model.dao.poolconnection.JdbcConnectionPool;

import java.sql.Connection;

public class AbstractDAO {
    private static JdbcConnectionPool pool = new JdbcConnectionPool();

    public static Connection getConnection() {
        return pool.getConnectionFromPool();
    }

    public static void returnConnection(Connection connection) {
        pool.returnConnectionToPool(connection);
    }
}
