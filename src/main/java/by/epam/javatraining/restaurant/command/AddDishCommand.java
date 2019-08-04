package by.epam.javatraining.restaurant.command;

import by.epam.javatraining.restaurant.model.dao.DishDAO;
import by.epam.javatraining.restaurant.model.dao.implementation.DishDAOImpl;
import by.epam.javatraining.restaurant.model.entity.Dish;
import by.epam.javatraining.restaurant.model.entity.DishCategory;
import by.epam.javatraining.restaurant.model.exception.DAOException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static by.epam.javatraining.restaurant.util.Constant.*;

public class AddDishCommand implements Command {
    private static final DishDAO dishDAO = new DishDAOImpl();
    private static final Logger LOGGER = Logger.getRootLogger();

    @Override
    public String execute(HttpServletRequest req) {
        DishCategory dishCategory = DishCategory.valueOf(req.getParameter(getConst(PAR_CATEGORY)).toUpperCase());
        String name = req.getParameter(getConst(PAR_NAME));
        double price = Double.valueOf(req.getParameter(getConst(PAR_PRICE)));
        double weight = Double.valueOf(req.getParameter(getConst(PAR_WEIGHT)));
        String photo = req.getParameter(getConst(PAR_IMAGE_URL));
        String description = req.getParameter(getConst(PAR_DESCRIPTION));

        Dish dish = new Dish(name, price, weight, photo, description, dishCategory);

        try {
            dishDAO.insert(dish);
            req.getSession().setAttribute(getConst(ATR_DISHES), dishDAO.getAll());
        } catch (DAOException e) {
            LOGGER.error(e);
        }
        return getConst(PAGE_MENU);
    }
}
