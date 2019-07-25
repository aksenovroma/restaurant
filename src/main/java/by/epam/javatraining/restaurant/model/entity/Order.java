package by.epam.javatraining.restaurant.model.entity;

import java.util.Map;

public class Order extends Entity{
    private int idClient;
    private int idWaiter;
    private String time;
    private OrderState orderState;
    private double totalPrice;
    private Map<Dish, Integer> dishes;

    public Order() {}

    public Order(int idClient, int idWaiter, String time,
                 OrderState orderState, double totalPrice, Map<Dish, Integer> dishes) {
        this.idClient = idClient;
        this.idWaiter = idWaiter;
        this.time = time;
        this.orderState = orderState;
        this.totalPrice = totalPrice;
        this.dishes = dishes;
    }

    public Order(int id, int idClient, int idWaiter, String time,
                 OrderState orderState, double totalPrice, Map<Dish, Integer> dishes) {
        super(id);
        this.idClient = idClient;
        this.idWaiter = idWaiter;
        this.time = time;
        this.orderState = orderState;
        this.totalPrice = totalPrice;
        this.dishes = dishes;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdWaiter() {
        return idWaiter;
    }

    public void setIdWaiter(int idWaiter) {
        this.idWaiter = idWaiter;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public OrderState getOrderState() {
        return orderState;
    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Map<Dish, Integer> getDishes() {
        return dishes;
    }

    public void setDishes(Map<Dish, Integer> dishes) {
        this.dishes = dishes;
    }


}
