package by.epam.javatraining.restaurant.command;

import by.epam.javatraining.restaurant.util.PagePath;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class SignOutCommand implements Command {
    private static final Logger LOGGER = Logger.getRootLogger();

    @Override
    public String execute(HttpServletRequest req) {
        LOGGER.info("SignOutCommand started");

        req.getSession().invalidate();

        LOGGER.info("SignOutCommand return " + PagePath.MAIN);

        return PagePath.MAIN;
    }
}
