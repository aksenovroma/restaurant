package by.epam.javatraining.restaurant.model.dao;

import by.epam.javatraining.restaurant.model.entity.User;
import by.epam.javatraining.restaurant.model.entity.UserRole;
import by.epam.javatraining.restaurant.model.exception.tecnical.UserDAOException;

import java.util.List;

public interface UserDAO extends DAO{
    User getByLogin(String login) throws UserDAOException;

    String getRoleById(int idUser) throws UserDAOException;

    void updateUserRole(int idUser, UserRole userRole) throws UserDAOException;

    boolean existLogin(String login) throws UserDAOException;

    List<User> getLimit(int start, int count) throws UserDAOException;
}
