package by.epam.javatraining.restaurant.command;

import by.epam.javatraining.restaurant.model.dao.implementation.UserDAOImpl;
import by.epam.javatraining.restaurant.model.entity.User;
import by.epam.javatraining.restaurant.model.exception.UserDAOException;
import by.epam.javatraining.restaurant.util.Constant;
import by.epam.javatraining.restaurant.util.PagePath;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static by.epam.javatraining.restaurant.util.Constant.*;

public class SignUpCommand implements Command {
    private static final Logger LOGGER = Logger.getRootLogger();

    private static final UserDAOImpl userDAO = new UserDAOImpl();

    @Override
    public String execute(HttpServletRequest req) {
        LOGGER.info("SignUpCommand started");
        String name = req.getParameter(getConst(PAR_NAME));
        String login = req.getParameter(getConst(PAR_LOGIN));
        String password = req.getParameter(getConst(PAR_PASSWORD));
        LOGGER.trace("name : " + name);
        LOGGER.trace("login : " + login);
        LOGGER.trace("password : " + password);

        User user = new User(name, login, password);

        try {
            if (!userDAO.existLogin(user.getLogin())) {
                userDAO.insert(user);
                user = userDAO.getByLogin(login);
            } else {
                return PagePath.REGISTRATION;
            }
        } catch (UserDAOException e) {
            LOGGER.error(e);
        }

        req.getSession().setAttribute("iduser", user.getId());
        req.getSession().setAttribute("username", user.getName());
        req.getSession().setAttribute("login", user.getLogin());
        req.getSession().setAttribute("password", user.getPassword());
        req.getSession().setAttribute("role", user.getUserRole().getRole());
        req.getSession().setAttribute("userphoto", user.getPhoto());

        LOGGER.info("SignUpCommand return " + PagePath.MAIN);

        return PagePath.MAIN;
    }
}
