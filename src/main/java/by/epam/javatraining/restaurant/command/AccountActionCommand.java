package by.epam.javatraining.restaurant.command;

import by.epam.javatraining.restaurant.model.dao.implementation.OrderDAOImpl;
import by.epam.javatraining.restaurant.model.entity.Order;
import by.epam.javatraining.restaurant.model.exception.DAOException;
import by.epam.javatraining.restaurant.util.PagePath;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AccountActionCommand implements Command {
    private static final Logger LOGGER = Logger.getRootLogger();

    @Override
    public String execute(HttpServletRequest req) {
        return PagePath.ACCOUNT;
    }
}
