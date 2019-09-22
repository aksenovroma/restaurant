package by.epam.javatraining.restaurant.command;

import by.epam.javatraining.restaurant.model.dao.UserDAO;
import by.epam.javatraining.restaurant.model.dao.implementation.UserDAOImpl;
import by.epam.javatraining.restaurant.model.exception.tecnical.DAOException;
import by.epam.javatraining.restaurant.model.exception.tecnical.UserDAOException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static by.epam.javatraining.restaurant.util.Constant.*;

public class ShowUsersCommand implements Command {
    private static final UserDAO userDAO = new UserDAOImpl();
    private static final Logger LOGGER = Logger.getRootLogger();
    private static final int MAX_COUNT = Integer.parseInt(getConst(MAX_USERS_ON_PAGE));

    @Override
    public String execute(HttpServletRequest req) {
        String page = getConst(PAGE_USERS_LIST);
        List users = null;
        Object pageNumber = req.getSession().getAttribute(getConst(ATR_PAGE_NUMBER));

        try {
            int number = (int) pageNumber;
            users = userDAO.getLimit((number - 1) * MAX_COUNT, MAX_COUNT);
        } catch (UserDAOException e) {
            LOGGER.error(e);
        }

        req.getSession().setAttribute(getConst(ATR_USERS), users);
        LOGGER.trace(this.getClass().getName() + getConst(RETURN) + page);
        return page;
    }
}
