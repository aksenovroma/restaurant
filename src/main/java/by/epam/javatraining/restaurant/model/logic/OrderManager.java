package by.epam.javatraining.restaurant.model.logic;

import by.epam.javatraining.restaurant.model.entity.Dish;
import by.epam.javatraining.restaurant.model.entity.Order;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public class OrderManager implements Manager {
    @Override
    public double totalPrice(Order order, HttpServletRequest req) {
        double price = -1;

        if (order != null && order.getDishes() != null) {
            Map<Integer, Integer> orderDishes = order.getDishes();
            List dishes = (List) req.getSession().getAttribute("dishes");
            price = 0;

            for (Map.Entry<Integer, Integer> entry : orderDishes.entrySet()) {
                for (Object o : dishes) {
                    Dish dish = (Dish) o;
                    if (entry.getKey() == dish.getId()) {
                        price += dish.getPrice() * entry.getValue();
                    }
                }
            }
        }

        return price;
    }

    @Override
    public double totalWeight(Order order, HttpServletRequest req) {
        double weight = -1;

        if (order != null && order.getDishes() != null) {
            Map<Integer, Integer> orderDishes = order.getDishes();
            List dishes = (List) req.getSession().getAttribute("dishes");
            weight = 0;

            for (Map.Entry<Integer, Integer> entry : orderDishes.entrySet()) {
                for (Object o : dishes) {
                    Dish dish = (Dish) o;
                    if (entry.getKey() == dish.getId()) {
                        weight += dish.getWeight() * entry.getValue();
                    }
                }
            }
        }

        return weight;
    }
}
