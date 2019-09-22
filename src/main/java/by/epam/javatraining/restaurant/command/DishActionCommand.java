package by.epam.javatraining.restaurant.command;

import by.epam.javatraining.restaurant.model.dao.DishDAO;
import by.epam.javatraining.restaurant.model.dao.implementation.DishDAOImpl;
import by.epam.javatraining.restaurant.model.entity.Dish;
import by.epam.javatraining.restaurant.model.entity.Order;
import by.epam.javatraining.restaurant.model.exception.tecnical.DAOException;
import by.epam.javatraining.restaurant.model.logic.OrderManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.epam.javatraining.restaurant.util.Constant.*;

public class DishActionCommand implements Command {
    private HashMap<Integer, Integer> orderDishes;
    private static final DishDAO dishDAO = new DishDAOImpl();
    private static final Logger LOGGER = Logger.getRootLogger();

    @Override
    public String execute(HttpServletRequest req) {
        String pagePath = getConst(PAGE_MENU);
        if (req.getSession().getAttribute(getConst(ATR_ORDER_DISHES)) != null) {
            orderDishes = (HashMap) req.getSession().getAttribute(getConst(ATR_ORDER_DISHES));

            String addDish = req.getParameter(getConst(PAR_ADD_ACTION));
            String removeDish = req.getParameter(getConst(PAR_REMOVE_ACTION));
            String reservation = req.getParameter(getConst(PAR_RES_ACTION));
            String removeDishFromMenu = req.getParameter(getConst(PAR_REMOVE_DISH_ACTION));
            String clear = req.getParameter(getConst(PAR_CLR_ACTION));

            if (addDish != null) {
                for (Map.Entry<Integer, Integer> entry : orderDishes.entrySet()) {
                    if (entry.getKey().equals(Integer.valueOf(addDish))) {
                        int count = entry.getValue();
                        count++;
                        entry.setValue(count);
                    }
                }
                req.getSession().setAttribute(getConst(ATR_ORDER_DISHES), orderDishes);
            } else if (removeDish != null) {
                for (Map.Entry<Integer, Integer> entry : orderDishes.entrySet()) {
                    if (entry.getKey().equals(Integer.valueOf(removeDish)) && entry.getValue() > 0) {
                        int count = entry.getValue();
                        count--;
                        entry.setValue(count);
                    }
                }
                req.getSession().setAttribute(getConst(ATR_ORDER_DISHES), orderDishes);
            } else if (reservation != null) {
                if (!isEmptyOrder()) {
                    Order order = new Order();
                    OrderManager orderManager = new OrderManager();

                    Map<Integer, Integer> dishes = new HashMap<>();
                    for (Map.Entry<Integer, Integer> entry : orderDishes.entrySet()) {
                        if (entry.getValue() > 0) {
                            dishes.put(entry.getKey(), entry.getValue());
                        }
                    }

                    order.setDishes(dishes);
                    order.setTotalPrice(orderManager.calcTotalPrice(order, req));
                    order.setTotalWeight(orderManager.calcTotalWeight(order, req));
                    req.getSession().setAttribute(getConst(ATR_ORDER), order);
                    pagePath = getConst(PAGE_RESERVATION);
                    LOGGER.trace(this.getClass().getName() + getConst(RETURN) + pagePath);
                    return pagePath;
                }
            } else if (clear != null) {
                List dishes = (List) req.getSession().getAttribute(getConst(ATR_DISHES));
                HashMap<Integer, Integer> orderDishes = new HashMap<>();
                if (dishes != null) {
                    for (Object dish : dishes) {
                        orderDishes.put(((Dish) dish).getId(), 0);
                    }
                    req.getSession().setAttribute(getConst(ATR_ORDER_DISHES), orderDishes);
                }
            } else if (removeDishFromMenu != null) {
                try {
                    dishDAO.delete(Integer.parseInt(removeDishFromMenu));
                    req.getSession().setAttribute(getConst(ATR_DISHES), dishDAO.getAll());
                } catch (DAOException e) {
                    LOGGER.error(e);
                }
            }
        }
        LOGGER.trace(this.getClass().getName() + getConst(RETURN) + pagePath);
        return pagePath;
    }

    private boolean isEmptyOrder() {
        boolean orderEmpty = true;
        for (Map.Entry<Integer, Integer> entry : orderDishes.entrySet()) {
            if (entry.getValue() > 0) {
                orderEmpty = false;
                break;
            }
        }
        return orderEmpty;
    }
}
