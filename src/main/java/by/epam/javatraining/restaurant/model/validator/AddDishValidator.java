package by.epam.javatraining.restaurant.model.validator;

import by.epam.javatraining.restaurant.model.dao.DishDAO;
import by.epam.javatraining.restaurant.model.dao.implementation.DishDAOImpl;
import by.epam.javatraining.restaurant.model.exception.tecnical.DishDAOException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static by.epam.javatraining.restaurant.util.Constant.*;

public class AddDishValidator implements Validator {
    private static final DishDAO dishDAO = new DishDAOImpl();

    private static final Logger LOGGER = Logger.getRootLogger();
    @Override
    public boolean validate(HttpServletRequest req) {
        boolean result = false;

        String name = req.getParameter(getConst(PAR_NAME));
        String photo = req.getParameter(getConst(PAR_IMAGE_URL));
        String description = req.getParameter(getConst(PAR_DESCRIPTION));

        if (name != null && photo != null && description != null) {
            try {
                if (!dishDAO.existName(name)) {
                    result = true;
                }
            } catch (DishDAOException e) {
                LOGGER.error(e);
            }
        }
        LOGGER.info(this.getClass().getName() + getConst(RETURN) + result);
        return result;
    }
}
