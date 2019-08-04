package by.epam.javatraining.restaurant.command;

import by.epam.javatraining.restaurant.model.dao.DishDAO;
import by.epam.javatraining.restaurant.model.dao.OrderDAO;
import by.epam.javatraining.restaurant.model.dao.implementation.DishDAOImpl;
import by.epam.javatraining.restaurant.model.dao.implementation.OrderDAOImpl;
import by.epam.javatraining.restaurant.model.entity.UserRole;
import by.epam.javatraining.restaurant.model.exception.DAOException;
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
        String userRole = (String) req.getSession().getAttribute(getConst(ATR_ROLE));
        String page = getConst(PAGE_ACCOUNT);

        try {
            List dishes = dishDAO.getAll();
            req.getSession().setAttribute(getConst(ATR_DISHES), dishes);
            if (userRole != null) {
                if (userRole.equals(UserRole.COURIER.getRole())) {
                    List orders = orderDAO.getAll();
                    req.getSession().setAttribute(getConst(ATR_ALL_ORDERS), orders);
                    page = getConst(PAGE_COURIER_ORDER);
                } else {
                    List orders = orderDAO.getAllById((Integer)req.getSession().getAttribute(getConst(ATR_ID_USER)));
                    if (!orders.isEmpty()) {
                        req.getSession().setAttribute(getConst(ATR_USER_ORDER), orders);
                    } else {
                        req.getSession().setAttribute(getConst(ATR_USER_ORDER), null);
                    }
                    page = getConst(PAGE_CLIENT_ORDER);
                }
            }
        } catch (DAOException e) {
            LOGGER.error(e);
        }
        return page;
    }
}
