package by.epam.javatraining.restaurant.command;

import by.epam.javatraining.restaurant.model.dao.UserDAO;
import by.epam.javatraining.restaurant.model.dao.implementation.UserDAOImpl;
import by.epam.javatraining.restaurant.model.entity.User;
import by.epam.javatraining.restaurant.model.exception.tecnical.UserDAOException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static by.epam.javatraining.restaurant.util.Constant.*;

public class SignInCommand implements Command {
    private static final Logger LOGGER = Logger.getRootLogger();

    private static final UserDAO userDAO = new UserDAOImpl();

    @Override
    public String execute(HttpServletRequest req) {
        String login = req.getParameter(getConst(PAR_LOGIN));
        String password = req.getParameter(getConst(PAR_PASSWORD));
        LOGGER.trace(getConst(LOG_SIGN_IN_LOGIN) + login);
        LOGGER.trace(getConst(LOG_SIGN_IN_PASSWORD) + password);

        if (login != null && password != null) {
            try {
                User user = userDAO.getByLogin(login);
                if (user != null && password.equals(user.getPassword())) {
                    req.getSession().setAttribute(getConst(ATR_ID_USER), user.getId());
                    req.getSession().setAttribute(getConst(ATR_USER_NAME), user.getName());
                    req.getSession().setAttribute(getConst(ATR_LOGIN), user.getLogin());
                    req.getSession().setAttribute(getConst(ATR_PASSWORD), user.getPassword());
                    req.getSession().setAttribute(getConst(ATR_ROLE), user.getUserRole().getRole());
                    req.getSession().setAttribute(getConst(ATR_USER_PHOTO), user.getPhoto());
                } else {
                    return getConst(PAGE_LOGIN);
                }
            } catch (UserDAOException e) {
                LOGGER.error(e);
            }
            return getConst(PAGE_MAIN);
        }
        return getConst(PAGE_LOGIN);
    }
}
