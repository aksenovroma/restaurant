package by.epam.javatraining.restaurant.model.dao.implementation;


import by.epam.javatraining.restaurant.model.dao.AbstractDAO;
import by.epam.javatraining.restaurant.model.dao.DishDAO;
import by.epam.javatraining.restaurant.model.entity.*;
import by.epam.javatraining.restaurant.model.exception.tecnical.DishDAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.epam.javatraining.restaurant.model.dao.DAOUtil.prepareStatement;

public class DishDAOImpl extends AbstractDAO implements DishDAO {
    private static final String SQL_INSERT_DISH = "insert into dish (name, price, weight, photo, description) values (?, ?, ?, ?, ?);";

    private static final String SQL_INSERT_DISH_CATEGORY = "insert into dish_category (iddish, category) values (last_insert_id(), ?);";

    private static final String SQL_DELETE_DISH = "DELETE FROM dish WHERE iddish = ?;";

    private static final String SQL_UPDATE_DISH = "UPDATE dish SET name=?, price=?, weight=?, photo=?, description=? WHERE iddish= ?;";

    private static final String SQL_UPDATE_DISH_CATEGORY = "UPDATE dish_category SET category=? WHERE iddish = ?;";

    private static final String SQL_GET_DISH_BY_ID = "SELECT dish.iddish, name, price, weight, photo," +
            "description, dish_category.category FROM dish INNER JOIN dish_category " +
            "ON dish.iddish = dish_category.iddish WHERE dish.iddish = ?";

    private static final String SQL_GET_DISH_BY_NAME = "SELECT dish.iddish, name, price, weight, photo," +
            "description, dish_category.category FROM dish INNER JOIN dish_category " +
            "ON dish.iddish = dish_category.iddish WHERE dish.name = ?";

    private static final String SQL_GET_ALL_DISHES = "SELECT dish.iddish, name, price, weight, photo," +
            "description, dish_category.category FROM dish INNER JOIN dish_category " +
            "ON dish.iddish = dish_category.iddish " +
            "ORDER BY dish_category.category";

    private static final String ERR_UPDATE_DISH = "Couldn't update dish";
    private static final String ERR_INSERT_DISH = "Couldn't insert dish";
    private static final String ERR_GET_DISH = "Couldn't get dish";

    private static final String PAR_ID_DISH = "iddish";
    private static final String PAR_NAME = "name";
    private static final String PAR_PRICE = "price";
    private static final String PAR_WEIGHT = "weight";
    private static final String PAR_PHOTO = "photo";
    private static final String PAR_DESCRIPTION = "description";
    private static final String PAR_CATEGORY = "category";

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
                    throw new DishDAOException(ERR_INSERT_DISH);
                }
            } catch (SQLException e) {
                throw new DishDAOException(ERR_INSERT_DISH + e.getMessage());
            } finally {
                returnConnection(connection);
            }
        }
    }

    @Override
    public void delete(int idDish) throws DishDAOException {
        updateStatement(SQL_DELETE_DISH, idDish);
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
                    throw new DishDAOException(ERR_UPDATE_DISH);
                }
            } catch (SQLException e) {
                throw new DishDAOException(ERR_UPDATE_DISH + e.getMessage());
            } finally {
                returnConnection(connection);
            }
        }
    }

    @Override
    public void updateDishCategory(int idDish, DishCategory dishCategory) throws DishDAOException {
        updateStatement(SQL_UPDATE_DISH_CATEGORY,
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
            throw new DishDAOException(ERR_GET_DISH + e);
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

    private void updateStatement(String sql, Object... values) throws DishDAOException{
        Connection connection = getConnection();

        try (PreparedStatement preparedStatement = prepareStatement(connection,
                sql, false, values)) {
            connection.setAutoCommit(false);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new DishDAOException(ERR_UPDATE_DISH);
            }
            connection.commit();

        } catch (SQLException e) {
            throw new DishDAOException(e);
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
            throw new DishDAOException(ERR_GET_DISH + e);
        } finally {
            returnConnection(connection);
        }
        return dish;
    }

    private static Dish map(ResultSet resultSet) throws SQLException {
        Dish dish = new Dish();

        dish.setId(resultSet.getInt(PAR_ID_DISH));
        dish.setName(resultSet.getString(PAR_NAME));
        dish.setPrice(resultSet.getDouble(PAR_PRICE));
        dish.setWeight(resultSet.getDouble(PAR_WEIGHT));
        dish.setPhoto(resultSet.getString(PAR_PHOTO));
        dish.setDescription(resultSet.getString(PAR_DESCRIPTION));
        dish.setDishCategory(DishCategory.valueOf(resultSet.getString(PAR_CATEGORY).toUpperCase()));

        return dish;
    }
}
