package by.epam.javatraining.restaurant.model.validator;

import by.epam.javatraining.restaurant.model.dao.implementation.UserDAOImpl;
import by.epam.javatraining.restaurant.model.exception.tecnical.UserDAOException;
import by.epam.javatraining.restaurant.util.InputDefence;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static by.epam.javatraining.restaurant.util.Constant.*;

public class SignUpValidator implements Validator {
    private static final Logger LOGGER = Logger.getRootLogger();

    private static final UserDAOImpl userDAO = new UserDAOImpl();

    @Override
    public boolean validate(HttpServletRequest req) {
        boolean result = false;

        String name = req.getParameter(getConst(PAR_NAME));
        name = InputDefence.scriptPrevention(name);
        String login = req.getParameter(getConst(PAR_LOGIN));
        login = InputDefence.scriptPrevention(login);
        String password = req.getParameter(getConst(PAR_PASSWORD));
        password = InputDefence.scriptPrevention(password);
        LOGGER.trace(getConst(LOG_SIGN_UP_LOGIN) + login);

        if (name != null && login != null && password != null) {
            try {
                if (!userDAO.existLogin(login)) {
                    result = true;
                }
            } catch (UserDAOException e) {
                LOGGER.error(e);
            }
        }
        return result;
    }
}
