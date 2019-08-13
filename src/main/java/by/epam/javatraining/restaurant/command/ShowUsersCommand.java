package by.epam.javatraining.restaurant.command;

import by.epam.javatraining.restaurant.model.dao.UserDAO;
import by.epam.javatraining.restaurant.model.dao.implementation.UserDAOImpl;
import by.epam.javatraining.restaurant.model.exception.tecnical.DAOException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static by.epam.javatraining.restaurant.util.Constant.*;

public class ShowUsersCommand implements Command {
    private static final UserDAO userDAO = new UserDAOImpl();
    private static final Logger LOGGER = Logger.getRootLogger();

    @Override
    public String execute(HttpServletRequest req) {
        List users = null;
        try {
            users = userDAO.getAll();
        } catch (DAOException e) {
            LOGGER.error(e);
        }
        req.getSession().setAttribute(getConst(ATR_USERS), users);
        return getConst(PAGE_USERS_LIST);
    }
}
