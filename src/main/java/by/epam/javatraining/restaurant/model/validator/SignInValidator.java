package by.epam.javatraining.restaurant.model.validator;

import by.epam.javatraining.restaurant.model.dao.UserDAO;
import by.epam.javatraining.restaurant.model.dao.implementation.UserDAOImpl;
import by.epam.javatraining.restaurant.model.entity.User;
import by.epam.javatraining.restaurant.model.exception.tecnical.UserDAOException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static by.epam.javatraining.restaurant.util.Constant.*;

public class SignInValidator implements Validator {
    private static final Logger LOGGER = Logger.getRootLogger();
    private static final UserDAO userDAO = new UserDAOImpl();

    @Override
    public boolean validate(HttpServletRequest req) {
        boolean result = false;
        String login = req.getParameter(getConst(PAR_LOGIN));
        String password = req.getParameter(getConst(PAR_PASSWORD));

        LOGGER.trace(getConst(LOG_SIGN_IN_LOGIN) + login);
        LOGGER.trace(getConst(LOG_SIGN_IN_PASSWORD) + password);

        if (login != null && password != null) {
            try {
                User user = userDAO.getByLogin(login);
                if (user != null && password.equals(user.getPassword())) {
                    result = true;
                }
            } catch (UserDAOException e) {
                LOGGER.error(e);
            }
        }
        return result;
    }
}
