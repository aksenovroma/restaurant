package by.epam.javatraining.restaurant.model.entity;

public enum DishCategory {
    APPETIZER("appetizer"), DESSERT("dessert"), SOUP("soup"), DRINK("drink");

    private String name;

    DishCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
