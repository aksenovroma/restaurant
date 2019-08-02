package by.epam.javatraining.restaurant.model.dao;

import by.epam.javatraining.restaurant.model.entity.Order;
import by.epam.javatraining.restaurant.model.exception.DAOException;
import by.epam.javatraining.restaurant.model.exception.OrderDAOException;

import java.util.List;

public interface OrderDAO extends DAO{
    List<Order> getAllById(int idClient) throws OrderDAOException;
}
