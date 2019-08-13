package by.epam.javatraining.restaurant.model.exception.tecnical;

public class UserDAOException extends DAOException {
    public UserDAOException() {
    }

    public UserDAOException(String message) {
        super(message);
    }

    public UserDAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserDAOException(Throwable cause) {
        super(cause);
    }
}
