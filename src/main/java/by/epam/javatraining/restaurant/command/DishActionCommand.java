package by.epam.javatraining.restaurant.command;

import by.epam.javatraining.restaurant.model.entity.Dish;
import by.epam.javatraining.restaurant.model.entity.Order;
import by.epam.javatraining.restaurant.model.logic.OrderManager;
import by.epam.javatraining.restaurant.util.PagePath;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DishActionCommand implements Command {
    private HashMap<Integer, Integer> orderDishes;

    @Override
    public String execute(HttpServletRequest req) {
        String pagePath = PagePath.MENU;
        if (req.getSession().getAttribute("orderDishes") != null) {
            orderDishes = (HashMap) req.getSession().getAttribute("orderDishes");

            String addDish = req.getParameter("add_action");
            String removeDish = req.getParameter("remove_action");
            String reservation = req.getParameter("res_action");
            String clear = req.getParameter("clr_action");

            if (addDish != null) {
                for (Map.Entry<Integer, Integer> entry : orderDishes.entrySet()) {
                    if (entry.getKey().equals(Integer.valueOf(addDish))) {
                        int count = entry.getValue();
                        count++;
                        entry.setValue(count);
                    }
                }
                req.getSession().setAttribute("orderDishes", orderDishes);
                req.getSession().setAttribute("add_action", null);
            } else if (removeDish != null) {
                for (Map.Entry<Integer, Integer> entry : orderDishes.entrySet()) {
                    if (entry.getKey().equals(Integer.valueOf(removeDish)) && entry.getValue() > 0) {
                        int count = entry.getValue();
                        count--;
                        entry.setValue(count);
                    }
                }
                req.getSession().setAttribute("orderDishes", orderDishes);
                req.getSession().setAttribute("remove_action", null);
            } else if (reservation != null) {
                req.getSession().setAttribute("res_action", null);
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
                    order.setTotalPrice(orderManager.totalPrice(order, req));
                    order.setTotalWeight(orderManager.totalWeight(order, req));
                    req.getSession().setAttribute("order", order);
                    pagePath = PagePath.RESERVATION;
                    return pagePath;
                } else {
                    return pagePath;
                }
            } else if (clear != null) {
                List dishes = (List) req.getSession().getAttribute("dishes");
                HashMap<Integer, Integer> orderDishes = new HashMap<>();
                if (dishes != null) {
                    for (Object dish : dishes) {
                        orderDishes.put(((Dish) dish).getId(), 0);
                    }
                    req.getSession().setAttribute("orderDishes", orderDishes);
                }
                req.getSession().setAttribute("clr_action", null);
                return pagePath;
            }
        }
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
