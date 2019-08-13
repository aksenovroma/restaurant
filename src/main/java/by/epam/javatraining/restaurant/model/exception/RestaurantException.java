package by.epam.javatraining.restaurant.model.exception;

public class RestaurantException extends Exception {
    public RestaurantException() {
    }

    public RestaurantException(String s) {
        super(s);
    }

    public RestaurantException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public RestaurantException(Throwable throwable) {
        super(throwable);
    }
}
