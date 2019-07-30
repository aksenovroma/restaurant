package by.epam.javatraining.restaurant.command;

import by.epam.javatraining.restaurant.model.dao.DishDAO;
import by.epam.javatraining.restaurant.model.dao.implementation.DishDAOImpl;
import by.epam.javatraining.restaurant.model.entity.Dish;
import by.epam.javatraining.restaurant.model.exception.DAOException;
import by.epam.javatraining.restaurant.util.PagePath;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowMenuCommand implements Command {
    private static DishDAO dishDAO = new DishDAOImpl();

    private static final Logger LOGGER = Logger.getRootLogger();

    @Override
    public String execute(HttpServletRequest req) {
        try {
            List dishes = dishDAO.getAll();
            req.getSession().setAttribute("dishes", dishes);
        } catch (DAOException e) {
            LOGGER.error(e);
        }
        return PagePath.MENU;
    }
}
