package by.epam.javatraining.restaurant.command;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static by.epam.javatraining.restaurant.util.Constant.*;

public class SignOutCommand implements Command {
    private static final Logger LOGGER = Logger.getRootLogger();

    @Override
    public String execute(HttpServletRequest req) {
        LOGGER.info(getConst(LOG_SIGN_OUT_START));

        req.getSession().invalidate();

        return getConst(PAGE_MAIN);
    }
}
