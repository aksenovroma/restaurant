package by.epam.javatraining.restaurant.model.exception.tecnical;

import by.epam.javatraining.restaurant.model.exception.RestaurantException;

public class DAOException extends RestaurantException {
    public DAOException() {
    }

    public DAOException(String message) {
        super(message);
    }

    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public DAOException(Throwable cause) {
        super(cause);
    }
}
