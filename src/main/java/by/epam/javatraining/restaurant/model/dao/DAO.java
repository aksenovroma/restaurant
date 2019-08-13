package by.epam.javatraining.restaurant.model.dao;

import by.epam.javatraining.restaurant.model.entity.Entity;
import by.epam.javatraining.restaurant.model.exception.tecnical.DAOException;

import java.util.List;

public interface DAO <T extends Entity> {
    void insert(T entity) throws DAOException;

    void delete(int idEntity) throws DAOException;

    void update(int idEntity, T entity) throws DAOException;

    List<T> getAll() throws DAOException;

    T getById(int id) throws DAOException;
}
