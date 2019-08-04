package by.epam.javatraining.restaurant.command;

import by.epam.javatraining.restaurant.model.dao.DishDAO;
import by.epam.javatraining.restaurant.model.dao.OrderDAO;
import by.epam.javatraining.restaurant.model.dao.implementation.DishDAOImpl;
import by.epam.javatraining.restaurant.model.dao.implementation.OrderDAOImpl;
import by.epam.javatraining.restaurant.model.entity.Order;
import by.epam.javatraining.restaurant.model.entity.UserRole;
import by.epam.javatraining.restaurant.model.exception.DAOException;
import by.epam.javatraining.restaurant.util.PagePath;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static by.epam.javatraining.restaurant.util.Constant.*;

public class ShowOrderCommand implements Command {
    private static final OrderDAO orderDAO = new OrderDAOImpl();
    private static final DishDAO dishDAO = new DishDAOImpl();
    private static final Logger LOGGER = Logger.getRootLogger();

    @Override
    public String execute(HttpServletRequest req) {
        String userRole = (String) req.getSession().getAttribute("role");

        try {
            List dishes = dishDAO.getAll();
            req.getSession().setAttribute("dishes", dishes);
            if (userRole != null) {
                if (userRole.equals("courier")) {
                    List orders = orderDAO.getAll();
                    req.getSession().setAttribute("allOrders", orders);
                    return PagePath.COURIER_ORDER;
                } else {
                    List orders = orderDAO.getAllById((Integer)req.getSession().getAttribute("iduser"));
                    if (!orders.isEmpty()) {
                        req.getSession().setAttribute("userOrder", orders);
                    } else {
                        req.getSession().setAttribute("userOrder", null);
                    }
                    return PagePath.CLIENT_ORDER;
                }
            }
        } catch (DAOException e) {
            LOGGER.error(e);
        }
        return PagePath.ACCOUNT;
    }
}
