package by.epam.javatraining.restaurant.command;

import by.epam.javatraining.restaurant.model.dao.OrderDAO;
import by.epam.javatraining.restaurant.model.dao.implementation.OrderDAOImpl;
import by.epam.javatraining.restaurant.model.entity.Dish;
import by.epam.javatraining.restaurant.model.entity.Order;
import by.epam.javatraining.restaurant.model.exception.DAOException;
import by.epam.javatraining.restaurant.util.PagePath;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class CheckOutCommand implements Command {
    private static final OrderDAO orderDAO = new OrderDAOImpl();
    private static final Logger LOGGER = Logger.getRootLogger();

    @Override
    public String execute(HttpServletRequest req) {
        Order order = (Order) req.getSession().getAttribute("order");
        String address = req.getParameter("address");
        order.setIdClient((Integer)req.getSession().getAttribute("iduser"));
        order.setTime((new Date().toString()));
        order.setAddress(address);

        try {
            orderDAO.insert(order);
            LOGGER.info("User " + "{id=" + order.getIdClient() + "} added order");
            req.getSession().setAttribute("orderDishes", null);
            List dishes = (List) req.getSession().getAttribute("dishes");
            HashMap<Integer, Integer> orderDishes = new HashMap<>();
            if (dishes != null) {
                for (Object dish : dishes) {
                    orderDishes.put(((Dish) dish).getId(), 0);
                }
                req.getSession().setAttribute("orderDishes", orderDishes);
            }
            LOGGER.trace("Attribute 'orderDishes' cleared");
        } catch (DAOException e) {
            LOGGER.error(e);
        }

        return PagePath.MAIN;
    }
}
