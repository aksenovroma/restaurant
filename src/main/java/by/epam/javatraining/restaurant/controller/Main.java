package by.epam.javatraining.restaurant.controller;

import by.epam.javatraining.restaurant.model.dao.DishDAO;
import by.epam.javatraining.restaurant.model.dao.OrderDAO;
import by.epam.javatraining.restaurant.model.dao.UserDAO;
import by.epam.javatraining.restaurant.model.dao.implementation.DishDAOImpl;
import by.epam.javatraining.restaurant.model.dao.implementation.OrderDAOImpl;
import by.epam.javatraining.restaurant.model.dao.implementation.UserDAOImpl;
import by.epam.javatraining.restaurant.model.entity.*;
import by.epam.javatraining.restaurant.model.exception.DAOException;

import javax.jws.soap.SOAPBinding;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        DishDAO dishDAO = new DishDAOImpl();
        OrderDAO orderDAO = new OrderDAOImpl();
        UserDAO userDAO = new UserDAOImpl();

        try {
            Dish dish1 = (Dish) dishDAO.getById(2);
            Dish dish2 = (Dish) dishDAO.getById(4);
            HashMap<Integer, Integer> map = new HashMap<>();
            map.put(2, 2);
            map.put(4, 2);

            User client = userDAO.getByLogin("romashka@mail.ru");
            User waiter = (User) userDAO.getById(10);

            Date date = new Date();

            Order order = new Order(880, 230, map);

            orderDAO.update(28, order);

        } catch (DAOException e) {
            e.printStackTrace();
        }
    }
}
