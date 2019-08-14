package by.epam.javatraining.restaurant.controller;

import by.epam.javatraining.restaurant.model.dao.DishDAO;
import by.epam.javatraining.restaurant.model.dao.OrderDAO;
import by.epam.javatraining.restaurant.model.dao.UserDAO;
import by.epam.javatraining.restaurant.model.dao.implementation.DishDAOImpl;
import by.epam.javatraining.restaurant.model.dao.implementation.OrderDAOImpl;
import by.epam.javatraining.restaurant.model.dao.implementation.UserDAOImpl;
import by.epam.javatraining.restaurant.model.entity.*;
import by.epam.javatraining.restaurant.model.exception.tecnical.DAOException;
import by.epam.javatraining.restaurant.util.InputDefence;

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

            String str = "Окрошка";
            System.out.println(dishDAO.existName(str));

        } catch (DAOException e) {
            e.printStackTrace();
        }
    }
}
