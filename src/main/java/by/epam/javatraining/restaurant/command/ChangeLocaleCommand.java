package by.epam.javatraining.restaurant.command;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.jstl.core.Config;

import static by.epam.javatraining.restaurant.util.Constant.*;

public class ChangeLocaleCommand implements Command {
    private static final Logger LOGGER = Logger.getRootLogger();

    @Override
    public String execute(HttpServletRequest req) {
        String page = getConst(PAGE_MAIN);
        String locale = req.getParameter(getConst(PAR_LOCALIZATION));

        req.getSession().setAttribute(getConst(ATR_LOCALE), locale);
        System.out.println(req.getSession().getAttribute(getConst(ATR_LOCALE)));
        Config.set(req.getSession(), Config.FMT_LOCALE, locale);

        LOGGER.trace(this.getClass().getName() + getConst(RETURN) + page);
        return page;
    }
}
