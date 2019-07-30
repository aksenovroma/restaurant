package by.epam.javatraining.restaurant.model.dao;

import by.epam.javatraining.restaurant.model.entity.User;
import by.epam.javatraining.restaurant.model.entity.UserRole;
import by.epam.javatraining.restaurant.model.exception.DAOException;
import by.epam.javatraining.restaurant.model.exception.UserDAOException;

public interface UserDAO extends DAO{
    User getByLogin(String login) throws UserDAOException;

    void updateUserRole(int idUser, UserRole userRole) throws UserDAOException;

    boolean existLogin(String login) throws UserDAOException;
}
