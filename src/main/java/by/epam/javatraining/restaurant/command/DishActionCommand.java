package by.epam.javatraining.restaurant.command;

import by.epam.javatraining.restaurant.model.dao.DishDAO;
import by.epam.javatraining.restaurant.model.dao.OrderDAO;
import by.epam.javatraining.restaurant.model.dao.UserDAO;
import by.epam.javatraining.restaurant.model.dao.implementation.DishDAOImpl;
import by.epam.javatraining.restaurant.model.dao.implementation.OrderDAOImpl;
import by.epam.javatraining.restaurant.model.dao.implementation.UserDAOImpl;
import by.epam.javatraining.restaurant.model.entity.Dish;
import by.epam.javatraining.restaurant.model.entity.Order;
import by.epam.javatraining.restaurant.model.entity.OrderState;
import by.epam.javatraining.restaurant.model.entity.User;
import by.epam.javatraining.restaurant.model.exception.DAOException;
import by.epam.javatraining.restaurant.util.PagePath;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DishActionCommand implements Command {


    @Override
    public String execute(HttpServletRequest req) {
        if (req.getSession().getAttribute("orderDishes") != null) {
            HashMap<Integer, Integer> orderDishes = (HashMap) req.getSession().getAttribute("orderDishes");

            String addDish = req.getParameter("add_action");
            String removeDish = req.getParameter("remove_action");
            String reservation = req.getParameter("res_action");

            if (addDish != null) {
                for (Map.Entry<Integer, Integer> entry : orderDishes.entrySet()) {
                    if (entry.getKey().equals(Integer.valueOf(addDish))) {
                        int count = entry.getValue();
                        count++;
                        entry.setValue(count);
                    }
                }
            } else if (removeDish != null) {
                for (Map.Entry<Integer, Integer> entry : orderDishes.entrySet()) {
                    if (entry.getKey().equals(Integer.valueOf(removeDish)) && entry.getValue() > 0) {
                        int count = entry.getValue();
                        count--;
                        entry.setValue(count);
                    }
                }
            } else if (reservation != null) {
                return PagePath.
            }

        } else {
            List dishes = (List) req.getSession().getAttribute("dishes");
            Map <Integer, Integer> orderDishes = new HashMap<>();
            for (Object dish : dishes) {
                orderDishes.put(((Dish) dish).getId(), 0);
            }
            req.getSession().setAttribute("orderDishes", orderDishes);
        }

        Map  = (HashMap) req.getSession().getAttribute("portionCount");

        if (portionCount == null) {
            portionCount = new HashMap<>();
            req.getSession().setAttribute("portionCount", portionCount);
        }


        if (addDish != null) {
            if (portionCount == null) {
                portionCount = new HashMap<>();
            }
            if (portionCount.containsKey(addDish)) {
                Integer count = (Integer) portionCount.get(addDish);
                count++;
                portionCount.put(addDish, count);
            } else {
                portionCount.put(addDish, 1);
            }
            req.setAttribute("portionCount", portionCount);

        }
        if (removeDish != null) {
            if (portionCount == null) {
                portionCount = new HashMap<>();
            }
            if (portionCount.containsKey(removeDish)) {
                Integer count = (Integer) portionCount.get(removeDish);
                if (count > 1) {
                    count--;
                    portionCount.put(removeDish, count);
                } else {
                    portionCount.remove(removeDish);
                }
            }
            req.setAttribute("portionCount", portionCount);
        }
        if (reservation != null) {
            Map<String, Integer> portions = (HashMap<String, Integer>) req.getSession().getAttribute("portionCount");
            if (portions == null) {
                portions = new HashMap<>();
            }

            if (!portions.isEmpty()) {
                DishDAO dishDAO = new DishDAOImpl();
                Map<Dish, Integer> dishes = new HashMap<>();
                for (Map.Entry<String, Integer> entry : portions.entrySet()) {
                    dishes.put(dishDAO.get(entry.getKey()), entry.getValue());
                }

                UserDAO userDAO = new UserDAOImpl();
                OrderDAO orderDAO = new OrderDAOImpl();
                Manager manager = new OrderManager();

                Integer iduser = (Integer)req.getSession().getAttribute("iduser");
                User client = userDAO.getById(iduser);
                double price = manager.totalPrice(dishes);
                double weight = manager.totalWeight(dishes);
                Date date = new Date();

                Order order = new Order(dishes, client, null, date.toString(), price, weight, OrderState.NOT_PAID);
                req.getSession().setAttribute("order", order);
                try {
                    orderDAO.add(order);
                } catch (DAOException e) {
                    e.printStackTrace();
                }

                return PagePath.ACCOUNT;
            }
        }
        return PagePath.MENU;
    }
}
