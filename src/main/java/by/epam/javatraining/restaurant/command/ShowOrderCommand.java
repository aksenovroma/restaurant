package by.epam.javatraining.restaurant.command;

import by.epam.javatraining.restaurant.model.dao.DishDAO;
import by.epam.javatraining.restaurant.model.dao.OrderDAO;
import by.epam.javatraining.restaurant.model.dao.implementation.DishDAOImpl;
import by.epam.javatraining.restaurant.model.dao.implementation.OrderDAOImpl;
import by.epam.javatraining.restaurant.model.entity.Order;
import by.epam.javatraining.restaurant.model.exception.DAOException;
import by.epam.javatraining.restaurant.util.PagePath;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowOrderCommand implements Command {
    private static final OrderDAO orderDAO = new OrderDAOImpl();
    private static final DishDAO dishDAO = new DishDAOImpl();
    private static final Logger LOGGER = Logger.getRootLogger();

    @Override
    public String execute(HttpServletRequest req) {
        try {
            List dishes = dishDAO.getAll();
            req.getSession().setAttribute("dishes", dishes);
            List orders = orderDAO.getAllById((Integer)req.getSession().getAttribute("iduser"));
            if (!orders.isEmpty()) {
                req.getSession().setAttribute("userOrder", orders);
            } else {
                req.getSession().setAttribute("userOrder", null);
            }
        } catch (DAOException e) {
            LOGGER.error(e);
        }
        return PagePath.CLIENT_ORDER;
    }
}
