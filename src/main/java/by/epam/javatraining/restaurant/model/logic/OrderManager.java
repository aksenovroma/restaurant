package by.epam.javatraining.restaurant.model.logic;

import by.epam.javatraining.restaurant.model.entity.Dish;
import by.epam.javatraining.restaurant.model.entity.Order;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

import static by.epam.javatraining.restaurant.util.Constant.*;

public class OrderManager implements Manager {
    @Override
    public double calcTotalPrice(Order order, HttpServletRequest req) {
        double price = -1;

        if (order != null && order.getDishes() != null) {
            Map<Integer, Integer> orderDishes = order.getDishes();
            List dishes = (List) req.getSession().getAttribute(getConst(ATR_DISHES));
            price = 0;

            if (dishes != null) {
                for (Map.Entry<Integer, Integer> entry : orderDishes.entrySet()) {
                    for (Object o : dishes) {
                        Dish dish = (Dish) o;
                        if (entry.getKey() == dish.getId()) {
                            price += dish.getPrice() * entry.getValue();
                        }
                    }
                }
            }
        }
        return price;
    }

    @Override
    public double calcTotalWeight(Order order, HttpServletRequest req) {
        double weight = -1;

        if (order != null && order.getDishes() != null) {
            Map<Integer, Integer> orderDishes = order.getDishes();
            List dishes = (List) req.getSession().getAttribute(getConst(ATR_DISHES));
            weight = 0;

            if (dishes != null) {
                for (Map.Entry<Integer, Integer> entry : orderDishes.entrySet()) {
                    for (Object o : dishes) {
                        Dish dish = (Dish) o;
                        if (entry.getKey() == dish.getId()) {
                            weight += dish.getWeight() * entry.getValue();
                        }
                    }
                }
            }
        }
        return weight;
    }
}
