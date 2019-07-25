package by.epam.javatraining.restaurant.model.entity;

import java.util.Objects;

public class Dish extends Entity {
    private String name;
    private double price;
    private double weight;
    private String photo;
    private String description;
    private DishCategory dishCategory;

    public Dish() {}

    public Dish(String name, double price, double weight, String photo, String description, DishCategory dishCategory) {
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.photo = photo;
        this.description = description;
        this.dishCategory = dishCategory;
    }

    public Dish(int id, String name, double price, double weight, String photo, String description, DishCategory dishCategory) {
        super(id);
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.photo = photo;
        this.description = description;
        this.dishCategory = dishCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DishCategory getDishCategory() {
        return dishCategory;
    }

    public void setDishCategory(DishCategory dishCategory) {
        this.dishCategory = dishCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Dish dish = (Dish) o;
        return Double.compare(dish.price, price) == 0 &&
                Double.compare(dish.weight, weight) == 0 &&
                Objects.equals(name, dish.name) &&
                Objects.equals(photo, dish.photo) &&
                Objects.equals(description, dish.description) &&
                dishCategory == dish.dishCategory;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, price, weight, photo, description, dishCategory);
    }

    @Override
    public String toString() {
        return "Dish{" +
                super.toString() +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", weight=" + weight +
                ", photo='" + photo + '\'' +
                ", description='" + description + '\'' +
                ", dishCategory=" + dishCategory +
                '}';
    }
}
