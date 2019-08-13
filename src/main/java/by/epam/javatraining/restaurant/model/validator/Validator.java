package by.epam.javatraining.restaurant.model.validator;

import javax.servlet.http.HttpServletRequest;

public interface Validator {
    boolean validate(HttpServletRequest req);
}
