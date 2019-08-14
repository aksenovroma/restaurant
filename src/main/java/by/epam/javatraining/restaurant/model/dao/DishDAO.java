package by.epam.javatraining.restaurant.model.dao;

import by.epam.javatraining.restaurant.model.entity.Dish;
import by.epam.javatraining.restaurant.model.entity.DishCategory;
import by.epam.javatraining.restaurant.model.exception.tecnical.DishDAOException;

public interface DishDAO extends DAO{
    Dish getByName(String name) throws DishDAOException;

    void updateDishCategory(int idDish, DishCategory dishCategory) throws DishDAOException;

    boolean existName(String name) throws DishDAOException;
}
