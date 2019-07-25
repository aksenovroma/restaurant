package by.epam.javatraining.restaurant.controller;

import by.epam.javatraining.restaurant.model.dao.DishDAO;
import by.epam.javatraining.restaurant.model.dao.implementation.DishDAOImpl;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        Entity dish = new Dish("Борщ", 123, 130, "link7", "Хлебный", DishCategory.SOUP);
        DishDAO dishDAO = new DishDAOImpl();
        try {
            dishDAO.delete("Борщ");
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }
}
