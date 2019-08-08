package by.epam.javatraining.restaurant.model.dao.implementation;

import by.epam.javatraining.restaurant.model.dao.AbstractDAO;
import by.epam.javatraining.restaurant.model.dao.UserDAO;
import by.epam.javatraining.restaurant.model.entity.Entity;
import by.epam.javatraining.restaurant.model.entity.User;
import by.epam.javatraining.restaurant.model.entity.UserRole;
import by.epam.javatraining.restaurant.model.exception.UserDAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.epam.javatraining.restaurant.model.dao.DAOUtil.prepareStatement;

public class UserDAOImpl extends AbstractDAO implements UserDAO {
    private static final String SQL_INSERT_USER = "insert into user (name, login, password, photo) values (?, ?, ?, ?);";

    private static final String SQL_INSERT_USER_ROLE = "insert into user_role (iduser, role) values (last_insert_id(), ?);";

    private static final String SQL_DELETE_USER = "DELETE FROM user WHERE iduser = ?;";

    private static final String SQL_UPDATE_USER = "UPDATE user SET name=?, login=?, password=?, photo=? WHERE iduser= ?;";

    private static final String SQL_UPDATE_USER_ROLE = "UPDATE user_role SET role=? WHERE iduser = ?;";

    private static final String SQL_GET_USER_BY_ID = "SELECT user.iduser, name, login, password, photo, user_role.role " +
            "FROM user INNER JOIN user_role ON user.iduser = user_role.iduser WHERE user.iduser = ?";

    private static final String SQL_GET_USER_BY_LOGIN = "SELECT user.iduser, name, login, password, photo, user_role.role " +
            "FROM user INNER JOIN user_role ON user.iduser = user_role.iduser WHERE user.login = ?;";

    private static final String SQL_GET_ALL_USERS = "SELECT user.iduser, name, login, password, photo, user_role.role " +
            "FROM user INNER JOIN user_role ON user.iduser = user_role.iduser;";

    private static final String SQL_EXIST_LOGIN =
            "SELECT iduser FROM user WHERE login = ?";

    private static final String SQL_GET_ROLE_BY_ID = "SELECT user_role.role " +
            "FROM user INNER JOIN user_role ON user.iduser = user_role.iduser WHERE user.iduser = ?";

    private static final String PAR_ID_USER = "iduser";
    private static final String PAR_NAME = "name";
    private static final String PAR_LOGIN = "login";
    private static final String PAR_PASSWORD = "password";
    private static final String PAR_PHOTO = "photo";
    private static final String PAR_ROLE = "role";

    private static final String ERR_UPDATE_USER = "Couldn't update user";
    private static final String ERR_INSERT_USER = "Couldn't insert user";
    private static final String ERR_GET_USER = "Couldn't get user";

    @Override
    public void insert(Entity entity) throws UserDAOException {
        if (entity instanceof User) {
            User user = (User) entity;
            Object[] values = {
                    user.getName(),
                    user.getLogin(),
                    user.getPassword(),
                    user.getPhoto(),
            };
            Connection connection = getConnection();

            try (PreparedStatement preparedStatementUser = prepareStatement(connection,
                    SQL_INSERT_USER, false, values);
            PreparedStatement preparedStatementUserRole = prepareStatement(connection,
                    SQL_INSERT_USER_ROLE, false, user.getUserRole().getRole())) {
                connection.setAutoCommit(false);
                int affectedRowsUser = preparedStatementUser.executeUpdate();
                int affectedRowsUserRole = preparedStatementUserRole.executeUpdate();
                connection.commit();
                if (affectedRowsUser == 0 && affectedRowsUserRole == 0){
                    throw new UserDAOException(ERR_INSERT_USER);
                }
            } catch (SQLException e) {
                throw new UserDAOException(ERR_INSERT_USER + e.getMessage());
            } finally {
                returnConnection(connection);
            }
        }
    }

    @Override
    public void delete(int idUser) throws UserDAOException {
            updateStatement(SQL_DELETE_USER, idUser);
    }

    @Override
    public void update(int idEntity, Entity entity) throws UserDAOException {
        if (entity instanceof User) {
            User user = (User) entity;
            Object[] values = {
                    user.getName(),
                    user.getLogin(),
                    user.getPassword(),
                    user.getPhoto(),
                    idEntity
            };

            Connection connection = getConnection();

            try (PreparedStatement preparedStatementUser = prepareStatement(connection,
                    SQL_UPDATE_USER, false, values);
                 PreparedStatement preparedStatementUserRole = prepareStatement(connection,
                         SQL_UPDATE_USER_ROLE, false, user.getUserRole().getRole(), idEntity)) {
                connection.setAutoCommit(false);
                int affectedRowsUser = preparedStatementUser.executeUpdate();
                int affectedRowsUserRole = preparedStatementUserRole.executeUpdate();
                connection.commit();
                if (affectedRowsUser == 0 && affectedRowsUserRole == 0){
                    throw new UserDAOException(ERR_UPDATE_USER);
                }
            } catch (SQLException e) {
                throw new UserDAOException(ERR_UPDATE_USER + e.getMessage());
            } finally {
                returnConnection(connection);
            }
        }
    }

    @Override
    public void updateUserRole(int idUser, UserRole userRole) throws UserDAOException {
        updateStatement(SQL_UPDATE_USER_ROLE,
                userRole.getRole(),
                idUser);
    }

    @Override
    public List getAll() throws UserDAOException {
        List<User> users = new ArrayList<>();
        Connection connection = getConnection();

        try (PreparedStatement statement = connection.prepareStatement(SQL_GET_ALL_USERS)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    users.add(map(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new UserDAOException(e);
        } finally {
            returnConnection(connection);
        }

        return users;
    }
    @Override
    public String getRoleById(int idUser) throws UserDAOException {
        String role = null;

        Connection connection = getConnection();

        try (
                PreparedStatement statement = prepareStatement(connection, SQL_GET_ROLE_BY_ID, false, idUser);
                ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                role = resultSet.getString(PAR_ROLE);
            }
        } catch (SQLException e) {
            throw new UserDAOException(e);
        } finally {
            returnConnection(connection);
        }

        return role;
    }

    @Override
    public Entity getById(int id) throws UserDAOException {
        return get(SQL_GET_USER_BY_ID, id);
    }

    @Override
    public User getByLogin(String login) throws UserDAOException{
        return get(SQL_GET_USER_BY_LOGIN, login);
    }

    @Override
    public boolean existLogin(String login) throws UserDAOException{
        boolean exist;

        Connection connection = getConnection();

        try (
                PreparedStatement statement = prepareStatement(connection, SQL_EXIST_LOGIN, false, login);
                ResultSet resultSet = statement.executeQuery()) {
            exist = resultSet.next();
        } catch (SQLException e) {
            throw new UserDAOException(e);
        } finally {
            returnConnection(connection);
        }

        return exist;
    }

    private void updateStatement(String sql, Object... values) throws UserDAOException{
        Connection connection = getConnection();

        try (PreparedStatement preparedStatement = prepareStatement(connection,
                sql, false, values)) {
            connection.setAutoCommit(false);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new UserDAOException(ERR_UPDATE_USER);
            }
            connection.commit();

        } catch (SQLException e) {
            throw new UserDAOException(ERR_UPDATE_USER + e.getMessage());
        } finally {
            returnConnection(connection);
        }
    }

    private User get(String sql, Object... values) throws UserDAOException{
        User user = null;
        Connection connection = getConnection();

        try (
                PreparedStatement statement = prepareStatement(connection, sql, false, values);
                ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                user = map(resultSet);
            }
        } catch (SQLException e) {
            throw new UserDAOException(e);
        } finally {
            returnConnection(connection);
        }
        return user;
    }

    private static User map(ResultSet resultSet) throws SQLException {
        User user = new User();

        user.setId(resultSet.getInt(PAR_ID_USER));
        user.setName(resultSet.getString(PAR_NAME));
        user.setLogin(resultSet.getString(PAR_LOGIN));
        user.setPassword(resultSet.getString(PAR_PASSWORD));
        user.setPhoto(resultSet.getString(PAR_PHOTO));
        user.setUserRole(UserRole.valueOf(resultSet.getString(PAR_ROLE).toUpperCase()));

        return user;
    }
}
