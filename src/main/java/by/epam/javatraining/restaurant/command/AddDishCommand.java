package by.epam.javatraining.restaurant.command;

import by.epam.javatraining.restaurant.model.dao.DishDAO;
import by.epam.javatraining.restaurant.model.dao.implementation.DishDAOImpl;
import by.epam.javatraining.restaurant.model.entity.Dish;
import by.epam.javatraining.restaurant.model.entity.DishCategory;
import by.epam.javatraining.restaurant.model.exception.DAOException;
import by.epam.javatraining.restaurant.util.PagePath;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class AddDishCommand implements Command {
    private static final DishDAO dishDAO = new DishDAOImpl();
    private static final Logger LOGGER = Logger.getRootLogger();

    @Override
    public String execute(HttpServletRequest req) {
        DishCategory dishCategory = DishCategory.valueOf(req.getParameter("category").toUpperCase());
        String name = req.getParameter("name");
        double price = Double.valueOf(req.getParameter("price"));
        double weight = Double.valueOf(req.getParameter("weight"));
        String photo = req.getParameter("image-url");
        String description = req.getParameter("description");

        Dish dish = new Dish(name, price, weight, photo, description, dishCategory);

        try {
            dishDAO.insert(dish);
            req.getSession().setAttribute("dishes", dishDAO.getAll());
        } catch (DAOException e) {
            LOGGER.error(e);
        }
        return PagePath.MENU;
    }
}
