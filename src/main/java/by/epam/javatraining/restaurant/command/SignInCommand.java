package by.epam.javatraining.restaurant.command;

import by.epam.javatraining.restaurant.model.dao.UserDAO;
import by.epam.javatraining.restaurant.model.dao.implementation.UserDAOImpl;
import by.epam.javatraining.restaurant.model.entity.User;
import by.epam.javatraining.restaurant.model.entity.UserRole;
import by.epam.javatraining.restaurant.model.exception.tecnical.UserDAOException;
import by.epam.javatraining.restaurant.model.validator.Validator;
import by.epam.javatraining.restaurant.model.validator.ValidatorFactory;
import by.epam.javatraining.restaurant.util.InputDefence;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static by.epam.javatraining.restaurant.util.Constant.*;

public class SignInCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(SignInCommand.class);

    private static final UserDAO userDAO = new UserDAOImpl();

    @Override
    public String execute(HttpServletRequest req) {
        String page = getConst(PAGE_INVALID_LOGIN);
        Validator validator = ValidatorFactory.getValidator().getSignInValidator();

        if (validator.validate(req)) {
            try {
                String login = req.getParameter(getConst(PAR_LOGIN));
                login = InputDefence.scriptPrevention(login);
                User user = userDAO.getByLogin(login);

                if (user != null) {
                    req.getSession().setAttribute(getConst(ATR_ID_USER), user.getId());
                    req.getSession().setAttribute(getConst(ATR_USER_NAME), user.getName());
                    req.getSession().setAttribute(getConst(ATR_LOGIN), user.getLogin());
                    req.getSession().setAttribute(getConst(ATR_PASSWORD), user.getPassword());
                    req.getSession().setAttribute(getConst(ATR_ROLE), user.getUserRole().getRole());
                    req.getSession().setAttribute(getConst(ATR_USER_PHOTO), user.getPhoto());
                    if (user.getUserRole().equals(UserRole.ADMIN)) {
                        req.getSession().setAttribute("pageNumber", 1);
                    }
                }

                page = getConst(PAGE_MAIN);
            } catch (UserDAOException e) {
                LOGGER.error(e);
            }
        }
        return page;
    }
}
