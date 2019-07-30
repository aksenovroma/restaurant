package by.epam.javatraining.restaurant.command;

import by.epam.javatraining.restaurant.model.dao.OrderDAO;
import by.epam.javatraining.restaurant.model.dao.UserDAO;
import by.epam.javatraining.restaurant.model.dao.implementation.OrderDAOImpl;
import by.epam.javatraining.restaurant.model.dao.implementation.UserDAOImpl;
import by.epam.javatraining.restaurant.model.entity.Order;
import by.epam.javatraining.restaurant.model.entity.User;
import by.epam.javatraining.restaurant.model.exception.DAOException;
import by.epam.javatraining.restaurant.util.PagePath;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SignInCommand implements Command {
    private static final Logger LOGGER = Logger.getRootLogger();


    private static final UserDAO userDAO = new UserDAOImpl();
    private static final OrderDAO orderDAO = new OrderDAOImpl();

    @Override
    public String execute(HttpServletRequest req) {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        LOGGER.trace("login : " + login);
        LOGGER.trace("password : " + password);

        if (login != null && password != null) {
            Map<String, Integer> portionCount = new HashMap<>();
            User user = userDAO.getByLoginAndPas(login, password);

            if (user != null) {
                req.getSession().setAttribute("iduser", user.getId());
                req.getSession().setAttribute("username", user.getName());
                req.getSession().setAttribute("login", user.getLogin());
                req.getSession().setAttribute("password", user.getPassword());
                req.getSession().setAttribute("role", user.getRole());
                req.getSession().setAttribute("portionCount", portionCount);

                List<Order> orders = null;
                if (user.getRole().getUserRole().equals("waiter")) {

                    try {
                        orders = orderDAO.getAll();
                        System.out.println(orders);
                    } catch (DAOException e) {
                        LOGGER.error(e);
                    }
                    req.getSession().setAttribute("allOrders", orders);
                }

                LOGGER.info("SignInCommand return " + PagePath.MAIN);

                return PagePath.MAIN;
            }
            else {
                return PagePath.LOGIN;
            }
        }
        LOGGER.info("SignInCommand return " + PagePath.LOGIN);

        return PagePath.LOGIN;
    }
}
