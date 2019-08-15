package by.epam.javatraining.restaurant.command;

import by.epam.javatraining.restaurant.model.dao.implementation.UserDAOImpl;
import by.epam.javatraining.restaurant.model.entity.User;
import by.epam.javatraining.restaurant.model.exception.tecnical.UserDAOException;
import by.epam.javatraining.restaurant.model.validator.Validator;
import by.epam.javatraining.restaurant.model.validator.ValidatorFactory;
import by.epam.javatraining.restaurant.util.InputDefence;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static by.epam.javatraining.restaurant.util.Constant.*;

public class SignUpCommand implements Command {
    private static final Logger LOGGER = Logger.getRootLogger();

    private static final UserDAOImpl userDAO = new UserDAOImpl();

    @Override
    public String execute(HttpServletRequest req) {
        String page = getConst(PAGE_INVALID_REGISTRATION);
        LOGGER.info(getConst(LOG_SIGN_UP_START));
        Validator validator = ValidatorFactory.getValidator().getSignUpValidator();

        try {
            if (validator.validate(req)) {
                String name = req.getParameter(getConst(PAR_NAME));
                name = InputDefence.scriptPrevention(name);
                String login = req.getParameter(getConst(PAR_LOGIN));
                login = InputDefence.scriptPrevention(login);
                String password = req.getParameter(getConst(PAR_PASSWORD));
                password = InputDefence.scriptPrevention(password);

                User user = new User(name, login, password);
                userDAO.insert(user);
                System.out.println("up " + login);
                user = userDAO.getByLogin(login);
                System.out.println("up " + user);

                if (user != null) {
                    req.getSession().setAttribute(getConst(ATR_ID_USER), user.getId());
                    req.getSession().setAttribute(getConst(ATR_USER_NAME), user.getName());
                    req.getSession().setAttribute(getConst(ATR_LOGIN), user.getLogin());
                    req.getSession().setAttribute(getConst(ATR_PASSWORD), user.getPassword());
                    req.getSession().setAttribute(getConst(ATR_ROLE), user.getUserRole().getRole());
                    req.getSession().setAttribute(getConst(ATR_USER_PHOTO), user.getPhoto());
                }
                page = getConst(PAGE_MAIN);
            }
        } catch (UserDAOException e) {
            LOGGER.error(e);
        }

        return page;
    }
}
