package by.epam.javatraining.restaurant.command;

import by.epam.javatraining.restaurant.model.dao.DishDAO;
import by.epam.javatraining.restaurant.model.dao.implementation.DishDAOImpl;
import by.epam.javatraining.restaurant.model.entity.Dish;
import by.epam.javatraining.restaurant.model.exception.DAOException;
import by.epam.javatraining.restaurant.util.PagePath;
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
        try {
            List dishes = dishDAO.getAll();
            req.getSession().setAttribute("dishes", dishes);
            HashMap<Integer, Integer> orderDishes = new HashMap<>();
            for (Object dish : dishes) {
                orderDishes.put(((Dish) dish).getId(), 0);
            }
            req.getSession().setAttribute("orderDishes", orderDishes);
        } catch (DAOException e) {
            LOGGER.error(e);
        }
        return PagePath.MENU;
    }
}
