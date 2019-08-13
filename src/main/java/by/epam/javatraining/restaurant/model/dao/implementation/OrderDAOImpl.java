package by.epam.javatraining.restaurant.model.dao.implementation;

import by.epam.javatraining.restaurant.model.dao.AbstractDAO;
import by.epam.javatraining.restaurant.model.dao.OrderDAO;
import by.epam.javatraining.restaurant.model.entity.*;
import by.epam.javatraining.restaurant.model.exception.tecnical.OrderDAOException;

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

    private static final String SQL_DELETE_ORDER = "DELETE FROM restaurant.order WHERE idorder = ?;";

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

    private static final String SQL_DELETE_ORDER_DETAIL = "delete from order_detail where idorder = ?;";

    private static final String SQl_INSERT_ORDER_DETAILS = "insert into order_detail (idorder, iddish) VALUES (?, ?);";

    private static final String SQL_UPDATE_ORDER = "update `order` set totalprice = ?, totalweight = ? where idorder = ?;";

    private static final String SQL_UPDATE_STATE = "UPDATE order_state SET state = ? WHERE idorder = ?;";

    private static final String SQL_UPDATE_COURIER = "UPDATE `order` SET idcourier = ? WHERE idorder = ?;";

    private static final String ERR_UPDATE_ORDER = "Couldn't update order";
    private static final String ERR_INSERT_ORDER = "Couldn't insert order";
    private static final String ERR_GET_ORDER = "Couldn't get order";

    private static final String PAR_ID_ORDER = "idorder";
    private static final String PAR_ID_CLIENT = "idclient";
    private static final String PAR_ID_COURIER = "idcourier";
    private static final String PAR_TIME = "time";
    private static final String PAR_STATE = "state";
    private static final String PAR_TOTAL_PRICE = "totalprice";
    private static final String PAR_TOTAL_WEIGHT = "totalweight";
    private static final String PAR_ADDRESS = "address";
    private static final String PAR_ID_DISH = "iddish";

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
                    throw new OrderDAOException(ERR_INSERT_ORDER);
                }
            } catch (SQLException e) {
                throw new OrderDAOException(ERR_INSERT_ORDER + e.getMessage());
            } finally {
                returnConnection(connection);
            }
        }
    }

    @Override
    public void delete(int idClient) throws OrderDAOException {
        updateStatement(SQL_DELETE_ORDER, idClient);
    }

    @Override
    public void updateOrderState(int idOrder, String state) throws OrderDAOException {
        updateStatement(SQL_UPDATE_STATE, state, idOrder);
    }

    @Override
    public void updateIdCourier(int idOrder, int idCourier) throws OrderDAOException {
        updateStatement(SQL_UPDATE_COURIER, idCourier, idOrder);
    }

    @Override
    public void update(int idEntity, Entity entity) throws OrderDAOException {
        if (entity instanceof Order) {
            Order order = (Order) entity;
            Object[] values = {
                    order.getTotalPrice(),
                    order.getTotalWeight(),
                    idEntity
            };
            Connection connection = getConnection();

            try {
                PreparedStatement preparedStatementOrderDetail = prepareStatement(connection,
                        SQL_DELETE_ORDER_DETAIL, false, idEntity);

                connection.setAutoCommit(false);

                int affectedRowsOrderDetail = preparedStatementOrderDetail.executeUpdate();

                for (Map.Entry<Integer, Integer> entry : order.getDishes().entrySet()) {
                    for (int i = 0; i < entry.getValue(); i++) {
                        PreparedStatement preparedStatementDish = prepareStatement(connection,
                                SQl_INSERT_ORDER_DETAILS, false, idEntity, entry.getKey());
                        preparedStatementDish.executeUpdate();
                    }
                }

                PreparedStatement preparedStatementOrder = prepareStatement(connection,
                        SQL_UPDATE_ORDER, false, values);

                int affectedRowsOrder = preparedStatementOrder.executeUpdate();

                connection.commit();
                if (affectedRowsOrder == 0 || affectedRowsOrderDetail == 0){
                    throw new OrderDAOException(ERR_UPDATE_ORDER);
                }
            } catch (SQLException e) {
                throw new OrderDAOException(ERR_UPDATE_ORDER + e.getMessage());
            } finally {
                returnConnection(connection);
            }
        }
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
            throw new OrderDAOException(ERR_GET_ORDER + e);
        } finally {
            returnConnection(connection);
        }

        return orders;
    }

    @Override
    public Entity getById(int idClient) throws OrderDAOException {
        return get(SQL_GET_ORDER, idClient);
    }

    @Override
    public List<Order> getAllById(int idClient) throws OrderDAOException{
        List<Order> orders = new ArrayList<>();
        Connection connection = getConnection();

        try (PreparedStatement statement = prepareStatement(connection, SQL_GET_ORDER, false, idClient)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    orders.add(map(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new OrderDAOException(ERR_GET_ORDER + e);
        } finally {
            returnConnection(connection);
        }

        return orders;
    }

    private void updateStatement(String sql, Object... values) throws OrderDAOException{
        Connection connection = getConnection();

        try (PreparedStatement preparedStatement = prepareStatement(connection,
                sql, false, values)) {
            connection.setAutoCommit(false);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new OrderDAOException(ERR_UPDATE_ORDER);
            }
            connection.commit();

        } catch (SQLException e) {
            throw new OrderDAOException(e);
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
            throw new OrderDAOException(ERR_GET_ORDER + e);
        } finally {
            returnConnection(connection);
        }
        return order;
    }

    private static Order map(ResultSet resultSet) throws SQLException {
        Order order = new Order();

        order.setId(resultSet.getInt(PAR_ID_ORDER));
        order.setIdClient(resultSet.getInt(PAR_ID_CLIENT));
        order.setIdCourier(resultSet.getInt(PAR_ID_COURIER));
        order.setTime(resultSet.getString(PAR_TIME));
        order.setOrderState(OrderState.valueOf(resultSet.getString(PAR_STATE).toUpperCase()));
        order.setTotalPrice(resultSet.getDouble(PAR_TOTAL_PRICE));
        order.setTotalWeight(resultSet.getDouble(PAR_TOTAL_WEIGHT));
        order.setAddress(resultSet.getString(PAR_ADDRESS));

        Map<Integer, Integer> dishes = new HashMap<>();
        int idDish = resultSet.getInt(PAR_ID_DISH);
        int dishCount = 1;
        while (resultSet.next()) {
            if (resultSet.getInt(PAR_ID_ORDER) == order.getId()) {
                if (resultSet.getInt(PAR_ID_DISH) == idDish) {
                    dishCount++;
                } else {
                    dishes.put(idDish, dishCount);
                    idDish = resultSet.getInt(PAR_ID_DISH);
                    dishCount = 1;
                }
            } else {
                resultSet.previous();
                break;
            }
        }
        dishes.put(idDish, dishCount);
        order.setDishes(dishes);

        return order;
    }
}
