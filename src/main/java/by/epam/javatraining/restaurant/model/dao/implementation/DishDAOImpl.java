package by.epam.javatraining.restaurant.model.dao.implementation;


import by.epam.javatraining.restaurant.model.dao.AbstractDAO;
import by.epam.javatraining.restaurant.model.dao.DishDAO;
import by.epam.javatraining.restaurant.model.entity.*;
import by.epam.javatraining.restaurant.model.exception.DAOException;
import by.epam.javatraining.restaurant.model.exception.DishDAOException;
import by.epam.javatraining.restaurant.model.exception.UserDAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.epam.javatraining.restaurant.model.dao.DAOUtil.prepareStatement;

public class DishDAOImpl extends AbstractDAO implements DishDAO {
    public static final String SQL_INSERT_DISH = "insert into dish (name, price, weight, photo, description) values (?, ?, ?, ?, ?);";

    public static final String SQL_INSERT_DISH_CATEGORY = "insert into dish_category (iddish, category) values (last_insert_id(), ?);";

    public static final String SQL_DELETE_DISH = "DELETE FROM dish WHERE iddish = ?;";

    public static final String SQL_UPDATE_DISH = "UPDATE dish SET name=?, price=?, weight=?, photo=?, description=? WHERE iddish= ?;";

    public static final String SQL_UPDATE_DISH_CATEGORY = "UPDATE dish_category SET category=? WHERE iddish = ?;";

    public static final String SQL_GET_DISH_BY_ID = "SELECT dish.iddish, name, price, weight, photo," +
            "description, dish_category.category FROM dish INNER JOIN dish_category " +
            "ON dish.iddish = dish_category.iddish WHERE dish.iddish = ?";

    public static final String SQL_GET_DISH_BY_NAME = "SELECT dish.iddish, name, price, weight, photo," +
            "description, dish_category.category FROM dish INNER JOIN dish_category " +
            "ON dish.iddish = dish_category.iddish WHERE dish.name = ?";

    public static final String SQL_GET_ALL_DISHES = "SELECT dish.iddish, name, price, weight, photo," +
            "description, dish_category.category FROM dish INNER JOIN dish_category " +
            "ON dish.iddish = dish_category.iddish";

    @Override
    public void insert(Entity entity) throws DishDAOException {
        if (entity instanceof Dish) {
            Dish dish = (Dish) entity;
            Object[] values = {
                    dish.getName(),
                    dish.getPrice(),
                    dish.getWeight(),
                    dish.getPhoto(),
                    dish.getDescription(),
            };
            Connection connection = getConnection();

            try (PreparedStatement preparedStatementDish = prepareStatement(connection,
                    SQL_INSERT_DISH, false, values);
                 PreparedStatement preparedStatementDishCategory = prepareStatement(connection,
                         SQL_INSERT_DISH_CATEGORY, false, dish.getDishCategory().getName())) {
                connection.setAutoCommit(false);
                int affectedRowsDish = preparedStatementDish.executeUpdate();
                int affectedRowsDishCategory = preparedStatementDishCategory.executeUpdate();
                connection.commit();
                if (affectedRowsDish == 0 && affectedRowsDishCategory == 0){
                    throw new DishDAOException("Couldn't insert dish");
                }
            } catch (SQLException e) {
                throw new DishDAOException("Couldn't insert dish" + e.getMessage());
            } finally {
                returnConnection(connection);
            }
        }
    }

    @Override
    public void delete(int idDish) throws DishDAOException {
        updateStatement(SQL_DELETE_DISH, "Couldn't delete dish", idDish);
    }

    @Override
    public void update(int idEntity, Entity entity) throws DishDAOException {
        if (entity instanceof Dish) {
            Dish dish = (Dish) entity;
            Object[] values = {
                    dish.getName(),
                    dish.getPrice(),
                    dish.getWeight(),
                    dish.getPhoto(),
                    dish.getDescription(),
                    idEntity
            };
            Connection connection = getConnection();

            try (PreparedStatement preparedStatementDish = prepareStatement(connection,
                    SQL_UPDATE_DISH, false, values);
                 PreparedStatement preparedStatementDishCategory = prepareStatement(connection,
                         SQL_UPDATE_DISH_CATEGORY, false, dish.getDishCategory().getName(), idEntity)) {
                connection.setAutoCommit(false);
                int affectedRowsDish = preparedStatementDish.executeUpdate();
                int affectedRowsDishCategory = preparedStatementDishCategory.executeUpdate();
                connection.commit();
                if (affectedRowsDish == 0 && affectedRowsDishCategory == 0){
                    throw new DishDAOException("Couldn't update dish");
                }
            } catch (SQLException e) {
                throw new DishDAOException("Couldn't update dish" + e.getMessage());
            } finally {
                returnConnection(connection);
            }
        }
    }

    @Override
    public void updateDishCategory(int idDish, DishCategory dishCategory) throws DishDAOException {
        updateStatement(SQL_UPDATE_DISH_CATEGORY, "Couldn't update dish category",
                dishCategory.getName(),
                idDish
        );
    }

    @Override
    public List getAll() throws DishDAOException {
        List<Dish> dishes = new ArrayList<>();
        Connection connection = getConnection();

        try (PreparedStatement statement = connection.prepareStatement(SQL_GET_ALL_DISHES)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    dishes.add(map(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new DishDAOException(e);
        } finally {
            returnConnection(connection);
        }

        return dishes;
    }

    @Override
    public Entity getById(int id) throws DishDAOException {
        return get(SQL_GET_DISH_BY_ID, id);
    }

    @Override
    public Dish getByName(String name) throws DishDAOException {
        return get(SQL_GET_DISH_BY_NAME, name);
    }

    private void updateStatement(String sql, String exceptionMessage, Object... values) throws DishDAOException{
        Connection connection = getConnection();

        try (PreparedStatement preparedStatement = prepareStatement(connection,
                sql, false, values)) {
            connection.setAutoCommit(false);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new DishDAOException(exceptionMessage);
            }
            connection.commit();

        } catch (SQLException e) {
            throw new DishDAOException(exceptionMessage + e.getMessage());
        } finally {
            returnConnection(connection);
        }
    }

    private Dish get(String sql, Object... values) throws DishDAOException{
        Dish dish = null;
        Connection connection = getConnection();

        try (
                PreparedStatement statement = prepareStatement(connection, sql, false, values);
                ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                dish = map(resultSet);
            }
        } catch (SQLException e) {
            throw new DishDAOException(e);
        } finally {
            returnConnection(connection);
        }
        return dish;
    }

    private static Dish map(ResultSet resultSet) throws SQLException {
        Dish dish = new Dish();

        dish.setId(resultSet.getInt("iddish"));
        dish.setName(resultSet.getString("name"));
        dish.setPrice(resultSet.getDouble("price"));
        dish.setWeight(resultSet.getDouble("weight"));
        dish.setPhoto(resultSet.getString("photo"));
        dish.setDescription(resultSet.getString("description"));
        dish.setDishCategory(DishCategory.valueOf(resultSet.getString("category").toUpperCase()));

        return dish;
    }
}
