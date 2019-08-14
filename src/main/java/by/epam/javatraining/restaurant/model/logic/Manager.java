package by.epam.javatraining.restaurant.model.logic;

import by.epam.javatraining.restaurant.model.entity.Order;

import javax.servlet.http.HttpServletRequest;

public interface Manager {
    double calcTotalPrice(Order order, HttpServletRequest req);
    double calcTotalWeight(Order order, HttpServletRequest req);
}
