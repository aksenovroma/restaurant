package by.epam.javatraining.restaurant.command;

import by.epam.javatraining.restaurant.model.dao.OrderDAO;
import by.epam.javatraining.restaurant.model.dao.implementation.OrderDAOImpl;
import by.epam.javatraining.restaurant.model.entity.Dish;
import by.epam.javatraining.restaurant.model.entity.Order;
import by.epam.javatraining.restaurant.model.exception.tecnical.DAOException;
import by.epam.javatraining.restaurant.util.InputDefence;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static by.epam.javatraining.restaurant.util.Constant.*;

public class CheckOutCommand implements Command {
    private static final OrderDAO orderDAO = new OrderDAOImpl();
    private static final Logger LOGGER = Logger.getRootLogger();

    @Override
    public String execute(HttpServletRequest req) {
        Order order = (Order) req.getSession().getAttribute(getConst(ATR_ORDER));

        String address = req.getParameter(getConst(PAR_ADDRESS));
        address = InputDefence.scriptPrevention(address);

        order.setIdClient((Integer)req.getSession().getAttribute(getConst(ATR_ID_USER)));
        order.setTime((new Date().toString()));
        order.setAddress(address);

        try {
            orderDAO.insert(order);
            LOGGER.info(getConst(LOG_CHECK_OUT_ADD) + order.getIdClient());
            req.getSession().setAttribute(getConst(ATR_ORDER_DISHES), null);
            List dishes = (List) req.getSession().getAttribute(getConst(ATR_DISHES));
            HashMap<Integer, Integer> orderDishes = new HashMap<>();
            if (dishes != null) {
                for (Object dish : dishes) {
                    orderDishes.put(((Dish) dish).getId(), 0);
                }
                req.getSession().setAttribute(getConst(ATR_ORDER_DISHES), orderDishes);
            }
            LOGGER.trace(getConst(LOG_CHECK_OUT_CLR));
        } catch (DAOException e) {
            LOGGER.error(e);
        }

        return getConst(PAGE_MAIN);
    }
}
