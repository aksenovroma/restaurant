package by.epam.javatraining.restaurant.model.exception.tecnical;

public class DishDAOException extends DAOException {
    public DishDAOException() {
    }

    public DishDAOException(String message) {
        super(message);
    }

    public DishDAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public DishDAOException(Throwable cause) {
        super(cause);
    }
}
