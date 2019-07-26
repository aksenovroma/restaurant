package by.epam.javatraining.restaurant.model.entity;

import java.util.Map;
import java.util.Objects;

public class Order extends Entity{
    private int idClient;
    private int idCourier;
    private String time;
    private OrderState orderState;
    private double totalPrice;
    private double totalWeight;
    private String address;
    private Map<Integer, Integer> dishes;

    public Order() {}

    public Order(double totalPrice, double totalWeight, Map<Integer, Integer> dishes) {
        this.totalPrice = totalPrice;
        this.totalWeight = totalWeight;
        this.dishes = dishes;
    }

    public Order(int idClient, int idCourier, String time,
                 OrderState orderState, double totalPrice, double totalWeight, String address, Map<Integer, Integer> dishes) {
        this.idClient = idClient;
        this.idCourier = idCourier;
        this.time = time;
        this.orderState = orderState;
        this.totalPrice = totalPrice;
        this.totalWeight = totalWeight;
        this.address = address;
        this.dishes = dishes;
    }

    public Order(int idClient, String time, double totalPrice, double totalWeight, String address, Map<Integer, Integer> dishes) {
        this.idClient = idClient;
        idCourier = 1;
        this.time = time;
        this.orderState = OrderState.NOT_PAID;
        this.totalPrice = totalPrice;
        this.totalWeight = totalWeight;
        this.address = address;
        this.dishes = dishes;
    }

    public Order(int idClient, int idCourier, String time, double totalPrice, double totalWeight, String address, Map<Integer, Integer> dishes) {
        this.idClient = idClient;
        this.idCourier = idCourier;
        this.time = time;
        this.orderState = OrderState.NOT_PAID;
        this.totalPrice = totalPrice;
        this.totalWeight = totalWeight;
        this.address = address;
        this.dishes = dishes;
    }

    public Order(int id, int idClient, int idCourier, String time,
                 OrderState orderState, double totalPrice, double totalWeight, String address, Map<Integer, Integer> dishes) {
        super(id);
        this.idClient = idClient;
        this.idCourier = idCourier;
        this.time = time;
        this.orderState = orderState;
        this.totalPrice = totalPrice;
        this.totalWeight = totalWeight;
        this.address = address;
        this.dishes = dishes;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdCourier() {
        return idCourier;
    }

    public void setIdCourier(int idCourier) {
        this.idCourier = idCourier;
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

    public double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(double totalWeight) {
        this.totalWeight = totalWeight;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Map<Integer, Integer> getDishes() {
        return dishes;
    }

    public void setDishes(Map<Integer, Integer> dishes) {
        this.dishes = dishes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Order order = (Order) o;
        return idClient == order.idClient &&
                idCourier == order.idCourier &&
                Double.compare(order.totalPrice, totalPrice) == 0 &&
                Double.compare(order.totalWeight, totalWeight) == 0 &&
                Objects.equals(time, order.time) &&
                orderState == order.orderState &&
                Objects.equals(address, order.address) &&
                Objects.equals(dishes, order.dishes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), idClient, idCourier, time, orderState, totalPrice, totalWeight, address, dishes);
    }

    @Override
    public String toString() {
        return "Order{" +
                super.toString() +
                ", idClient=" + idClient +
                ", idCourier=" + idCourier +
                ", time='" + time + '\'' +
                ", orderState=" + orderState +
                ", totalPrice=" + totalPrice +
                ", totalWeight=" + totalWeight +
                ", address='" + address + '\'' +
                ", dishes=" + dishes +
                '}';
    }
}
