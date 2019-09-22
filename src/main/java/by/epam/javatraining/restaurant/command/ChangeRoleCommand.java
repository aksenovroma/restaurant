package by.epam.javatraining.restaurant.command;

import by.epam.javatraining.restaurant.model.dao.UserDAO;
import by.epam.javatraining.restaurant.model.dao.implementation.UserDAOImpl;
import by.epam.javatraining.restaurant.model.entity.UserRole;
import by.epam.javatraining.restaurant.model.exception.tecnical.DAOException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static by.epam.javatraining.restaurant.util.Constant.*;

public class ChangeRoleCommand implements Command {
    private static final UserDAO userDAO = new UserDAOImpl();
    private static final Logger LOGGER = Logger.getRootLogger();
    private static final int MAX_COUNT = Integer.parseInt(getConst(MAX_USERS_ON_PAGE));

    @Override
    public String execute(HttpServletRequest req) {
        String page = getConst(PAGE_USERS_LIST);
        String client = req.getParameter(getConst(PAR_USER_ROLE_CLIENT));
        String courier = req.getParameter(getConst(PAR_USER_ROLE_COURIER));

        List users;
        Object pageNumber = req.getSession().getAttribute(getConst(ATR_PAGE_NUMBER));
        int number = (int) pageNumber;
        String left = req.getParameter(getConst(PAR_LEFT_PAGE));
        String right = req.getParameter(getConst(PAR_RIGHT_PAGE));

        try {
            if (client != null) {
                int idUser = Integer.parseInt(client);
                userDAO.updateUserRole(idUser, UserRole.COURIER);
            }
            if (courier != null) {
                int idUser = Integer.parseInt(courier);
                userDAO.updateUserRole(idUser, UserRole.CLIENT);
            }
            if (left != null) {
                if (number > 1) {
                    number -= 1;
                    req.getSession().setAttribute(getConst(ATR_PAGE_NUMBER), number);
                }
            }
            if (right != null) {
                number += 1;
                if (!userDAO.getLimit((number - 1) * MAX_COUNT, MAX_COUNT).isEmpty()) {
                    req.getSession().setAttribute(getConst(ATR_PAGE_NUMBER), number);
                }
            }
            users = userDAO.getLimit((number - 1) * MAX_COUNT, MAX_COUNT);
            req.getSession().setAttribute(getConst(ATR_USERS), users);
        } catch (DAOException e) {
            LOGGER.error(e);
        }

        LOGGER.trace(this.getClass().getName() + getConst(RETURN) + page);
        return page;
    }
}
