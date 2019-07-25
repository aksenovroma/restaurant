package by.epam.javatraining.restaurant.model.dao;

import by.epam.javatraining.restaurant.model.entity.Order;
import by.epam.javatraining.restaurant.model.exception.DAOException;

import java.util.List;

public interface OrderDAO {
    Order getById(int id) throws DAOException;
    List<Order> getAll() throws DAOException;
    void delete(int id) throws DAOException;
    void add(Order order) throws DAOException;
}
