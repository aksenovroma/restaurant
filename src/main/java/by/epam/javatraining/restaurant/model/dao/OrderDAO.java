package by.epam.javatraining.restaurant.model.dao;

import by.epam.javatraining.restaurant.model.entity.Order;
import by.epam.javatraining.restaurant.model.exception.tecnical.OrderDAOException;

import java.util.List;

public interface OrderDAO extends DAO{
    List<Order> getAllById(int idClient) throws OrderDAOException;

    void updateOrderState(int idOrder, String state) throws OrderDAOException;

    void updateIdCourier(int idOrder, int idCourier) throws OrderDAOException;
}
