package by.epam.javatraining.restaurant.model.exception;

public class OrderDAOException extends DAOException {
    public OrderDAOException() {
    }

    public OrderDAOException(String message) {
        super(message);
    }

    public OrderDAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderDAOException(Throwable cause) {
        super(cause);
    }
}
