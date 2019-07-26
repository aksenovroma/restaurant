package by.epam.javatraining.restaurant.model.dao.implementation;

import by.epam.javatraining.restaurant.model.dao.AbstractDAO;
import by.epam.javatraining.restaurant.model.dao.OrderDAO;
import by.epam.javatraining.restaurant.model.entity.*;
import by.epam.javatraining.restaurant.model.exception.DAOException;
import by.epam.javatraining.restaurant.model.exception.DishDAOException;
import by.epam.javatraining.restaurant.model.exception.OrderDAOException;
import by.epam.javatraining.restaurant.model.exception.UserDAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.epam.javatraining.restaurant.model.dao.DAOUtil.prepareStatement;

public class OrderDAOImpl extends AbstractDAO implements OrderDAO {
    private static final String SQL_INSERT_ORDER = "insert into restaurant.order " +
            "(idclient, idcourier, time, totalprice, totalweight, address) values (?, ?, ?, ?, ?, ?);";

    private static final String SQL_INSERT_ORDER_STATE = "insert into restaurant.order_state " +
            "(idorder, state) values (last_insert_id(), ?);";

    private static final String SQL_INSERT_ORDER_DETAIL = "insert into restaurant.order_detail " +
            "(idorder, iddish) values (last_insert_id(), ?);";

    private static final String SQL_DELETE_ORDER = "DELETE FROM restaurant.order WHERE idclient = ?;";

    private static final String SQL_GET_ORDER = "SELECT `order`.idorder, idclient, idcourier, time, totalprice,\n" +
            "       totalweight, address, order_state.state, order_detail.iddish\n" +
            "FROM `order`\n" +
            "    INNER JOIN order_state on `order`.idorder = order_state.idorder\n" +
            "    INNER JOIN order_detail on `order`.idorder = order_detail.idorder\n" +
            "WHERE idclient = ?;";

    private static final String SQL_GET_ALL_ORDERS = "SELECT `order`.idorder, idclient, idcourier, time, totalprice,\n" +
            "       totalweight, address, order_state.state, order_detail.iddish\n" +
            "FROM `order`\n" +
            "    INNER JOIN order_state on `order`.idorder = order_state.idorder\n" +
            "    INNER JOIN order_detail on `order`.idorder = order_detail.idorder;";

    @Override
    public void insert(Entity entity) throws OrderDAOException {
        if (entity instanceof Order) {
            Order order = (Order) entity;
            Object[] values = {
                    order.getIdClient(),
                    order.getIdCourier(),
                    order.getTime(),
                    order.getTotalPrice(),
                    order.getTotalWeight(),
                    order.getAddress()
            };
            Connection connection = getConnection();

            try {
                PreparedStatement preparedStatementOrder = prepareStatement(connection,
                        SQL_INSERT_ORDER, false, values);
                PreparedStatement preparedStatementOrderState = prepareStatement(connection,
                        SQL_INSERT_ORDER_STATE, false, order.getOrderState().getState());

                connection.setAutoCommit(false);

                int affectedRowsUser = preparedStatementOrder.executeUpdate();
                int affectedRowsUserRole = preparedStatementOrderState.executeUpdate();

                for (Map.Entry<Integer, Integer> entry : order.getDishes().entrySet()) {
                    for (int i = 0; i < entry.getValue(); i++) {
                        PreparedStatement preparedStatementDish = prepareStatement(connection,
                                SQL_INSERT_ORDER_DETAIL, false, entry.getKey());
                        preparedStatementDish.executeUpdate();
                    }
                }

                connection.commit();
                if (affectedRowsUser == 0 && affectedRowsUserRole == 0){
                    throw new OrderDAOException("Couldn't insert order");
                }
            } catch (SQLException e) {
                throw new OrderDAOException("Couldn't update order" + e.getMessage());
            } finally {
                returnConnection(connection);
            }
        }
    }

    @Override
    public void delete(int idClient) throws OrderDAOException {
        updateStatement(SQL_DELETE_ORDER, "Couldn't delete order", idClient);
    }

    @Override
    public void update(int idEntity, Entity entity) throws DAOException {

    }

    @Override
    public List getAll() throws OrderDAOException {
        List<Order> orders = new ArrayList<>();
        Connection connection = getConnection();

        try (PreparedStatement statement = connection.prepareStatement(SQL_GET_ALL_ORDERS)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    orders.add(map(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new OrderDAOException(e);
        } finally {
            returnConnection(connection);
        }

        return orders;
    }

    @Override
    public Entity getById(int idClient) throws OrderDAOException {
        return get(SQL_GET_ORDER, idClient);
    }

    private void updateStatement(String sql, String exceptionMessage, Object... values) throws OrderDAOException{
        Connection connection = getConnection();

        try (PreparedStatement preparedStatement = prepareStatement(connection,
                sql, false, values)) {
            connection.setAutoCommit(false);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new OrderDAOException(exceptionMessage);
            }
            connection.commit();

        } catch (SQLException e) {
            throw new OrderDAOException(exceptionMessage + e.getMessage());
        } finally {
            returnConnection(connection);
        }
    }

    private Order get(String sql, Object... values) throws OrderDAOException{
        Order order = null;
        Connection connection = getConnection();

        try (
                PreparedStatement statement = prepareStatement(connection, sql, false, values);
                ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                order = map(resultSet);
            }
        } catch (SQLException e) {
            throw new OrderDAOException("Couldn't get order " + e);
        } finally {
            returnConnection(connection);
        }
        return order;
    }

    private static Order map(ResultSet resultSet) throws SQLException {
        Order order = new Order();

        order.setId(resultSet.getInt("idorder"));
        order.setIdClient(resultSet.getInt("idclient"));
        order.setIdCourier(resultSet.getInt("idcourier"));
        order.setTime(resultSet.getString("time"));
        order.setOrderState(OrderState.valueOf(resultSet.getString("state").toUpperCase()));
        order.setTotalPrice(resultSet.getDouble("totalprice"));
        order.setTotalWeight(resultSet.getDouble("totalweight"));
        order.setAddress(resultSet.getString("address"));

        Map<Integer, Integer> dishes = new HashMap<>();
        int idDish = resultSet.getInt("iddish");
        int dishCount = 1;
        while ((resultSet.getInt("idorder") == order.getId()) && !(resultSet.isLast())) {
            resultSet.next();
            if (resultSet.getInt("iddish") == idDish) {
                dishCount++;
            } else {
                dishes.put(idDish, dishCount);
                idDish = resultSet.getInt("iddish");
                dishCount = 1;
            }
        }
        dishes.put(idDish, dishCount);
        order.setDishes(dishes);
        resultSet.previous();

        return order;
    }
}
