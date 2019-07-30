package by.epam.javatraining.restaurant.command;

import by.epam.javatraining.restaurant.model.dao.UserDAO;
import by.epam.javatraining.restaurant.model.dao.implementation.UserDAOImpl;
import by.epam.javatraining.restaurant.model.entity.User;
import by.epam.javatraining.restaurant.model.exception.UserDAOException;
import by.epam.javatraining.restaurant.util.PagePath;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class SignInCommand implements Command {
    private static final Logger LOGGER = Logger.getRootLogger();

    private static final UserDAO userDAO = new UserDAOImpl();

    @Override
    public String execute(HttpServletRequest req) {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        LOGGER.trace("login : " + login);
        LOGGER.trace("password : " + password);

        if (login != null && password != null) {
            try {
                User user = userDAO.getByLogin(login);
                if (user != null && password.equals(user.getPassword())) {
                    req.getSession().setAttribute("iduser", user.getId());
                    req.getSession().setAttribute("username", user.getName());
                    req.getSession().setAttribute("login", user.getLogin());
                    req.getSession().setAttribute("password", user.getPassword());
                    req.getSession().setAttribute("role", user.getUserRole().getRole());
                    req.getSession().setAttribute("userphoto", user.getPhoto());
                } else {
                    LOGGER.info("SignInCommand return " + PagePath.LOGIN);

                    return PagePath.LOGIN;
                }
            } catch (UserDAOException e) {
                LOGGER.error(e);
            }
            LOGGER.info("SignInCommand return " + PagePath.MAIN);

            return PagePath.MAIN;
        }
        LOGGER.info("SignInCommand return " + PagePath.LOGIN);

        return PagePath.LOGIN;
    }
}
