package by.epam.javatraining.restaurant.model.logic;

import by.epam.javatraining.restaurant.model.entity.Order;

import javax.servlet.http.HttpServletRequest;

public interface Manager {
    double totalPrice(Order order, HttpServletRequest req);
    double totalWeight(Order order, HttpServletRequest req);
}
