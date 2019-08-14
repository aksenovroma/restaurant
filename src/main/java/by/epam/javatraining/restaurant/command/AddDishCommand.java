package by.epam.javatraining.restaurant.command;

import by.epam.javatraining.restaurant.model.dao.DishDAO;
import by.epam.javatraining.restaurant.model.dao.implementation.DishDAOImpl;
import by.epam.javatraining.restaurant.model.entity.Dish;
import by.epam.javatraining.restaurant.model.entity.DishCategory;
import by.epam.javatraining.restaurant.model.exception.tecnical.DAOException;
import by.epam.javatraining.restaurant.model.validator.Validator;
import by.epam.javatraining.restaurant.model.validator.ValidatorFactory;
import by.epam.javatraining.restaurant.util.InputDefence;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static by.epam.javatraining.restaurant.util.Constant.*;

public class AddDishCommand implements Command {
    private static final DishDAO dishDAO = new DishDAOImpl();
    private static final Logger LOGGER = Logger.getRootLogger();

    @Override
    public String execute(HttpServletRequest req) {
        String page = getConst(PAGE_INVALID_ADD_DISH);
        Validator validator = ValidatorFactory.getValidator().getAddDishValidator();

        if (validator.validate(req)) {
            DishCategory dishCategory = DishCategory.valueOf(req.getParameter(getConst(PAR_CATEGORY)).toUpperCase());
            double price = Double.parseDouble(req.getParameter(getConst(PAR_PRICE)));
            double weight = Double.parseDouble(req.getParameter(getConst(PAR_WEIGHT)));
            String name = req.getParameter(getConst(PAR_NAME));
            name = InputDefence.scriptPrevention(name);
            String photo = req.getParameter(getConst(PAR_IMAGE_URL));
            photo = InputDefence.scriptPrevention(photo);
            String description = req.getParameter(getConst(PAR_DESCRIPTION));
            description = InputDefence.scriptPrevention(description);

            Dish dish = new Dish(name, price, weight, photo, description, dishCategory);

            try {
                dishDAO.insert(dish);
                req.getSession().setAttribute(getConst(ATR_DISHES), dishDAO.getAll());
                page = getConst(PAGE_MENU);
            } catch (DAOException e) {
                LOGGER.error(e);
            }
        }
        return page;
    }
}
