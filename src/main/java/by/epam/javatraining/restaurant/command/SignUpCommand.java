package by.epam.javatraining.restaurant.command;

import by.epam.javatraining.restaurant.model.dao.implementation.UserDAOImpl;
import by.epam.javatraining.restaurant.model.entity.User;
import by.epam.javatraining.restaurant.model.exception.UserDAOException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static by.epam.javatraining.restaurant.util.Constant.*;

public class SignUpCommand implements Command {
    private static final Logger LOGGER = Logger.getRootLogger();

    private static final UserDAOImpl userDAO = new UserDAOImpl();

    @Override
    public String execute(HttpServletRequest req) {
        LOGGER.info(getConst(LOG_SIGN_UP_START));
        String name = req.getParameter(getConst(PAR_NAME));
        String login = req.getParameter(getConst(PAR_LOGIN));
        String password = req.getParameter(getConst(PAR_PASSWORD));
        LOGGER.trace(getConst(LOG_SIGN_UP_LOGIN) + login);

        User user = new User(name, login, password);

        try {
            if (!userDAO.existLogin(user.getLogin())) {
                userDAO.insert(user);
                user = userDAO.getByLogin(login);
            } else {
                return getConst(PAGE_REGISTRATION);
            }
        } catch (UserDAOException e) {
            LOGGER.error(e);
        }

        req.getSession().setAttribute(getConst(ATR_ID_USER), user.getId());
        req.getSession().setAttribute(getConst(ATR_USER_NAME), user.getName());
        req.getSession().setAttribute(getConst(ATR_LOGIN), user.getLogin());
        req.getSession().setAttribute(getConst(ATR_PASSWORD), user.getPassword());
        req.getSession().setAttribute(getConst(ATR_ROLE), user.getUserRole().getRole());
        req.getSession().setAttribute(getConst(ATR_USER_PHOTO), user.getPhoto());

        return getConst(PAGE_MAIN);
    }
}
