package by.epam.javatraining.restaurant.command;

import by.epam.javatraining.restaurant.model.dao.DishDAO;
import by.epam.javatraining.restaurant.model.dao.implementation.DishDAOImpl;
import by.epam.javatraining.restaurant.model.entity.Dish;
import by.epam.javatraining.restaurant.model.exception.tecnical.DAOException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

import static by.epam.javatraining.restaurant.util.Constant.*;

public class ShowMenuCommand implements Command {
    private static DishDAO dishDAO = new DishDAOImpl();

    private static final Logger LOGGER = Logger.getRootLogger();

    @Override
    public String execute(HttpServletRequest req) {
        String page = getConst(PAGE_MENU);
        try {

            List dishes = dishDAO.getAll();
            req.getSession().setAttribute(getConst(ATR_DISHES), dishes);
            HashMap<Integer, Integer> orderDishes = new HashMap<>();
            for (Object dish : dishes) {
                orderDishes.put(((Dish) dish).getId(), 0);
            }
            req.getSession().setAttribute(getConst(ATR_ORDER_DISHES), orderDishes);
        } catch (DAOException e) {
            LOGGER.error(e);
        }
        LOGGER.trace(this.getClass().getName() + getConst(RETURN) + page);
        return page;
    }
}
