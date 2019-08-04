package by.epam.javatraining.restaurant.command;

import by.epam.javatraining.restaurant.util.PagePath;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.jstl.core.Config;

public class ChangeLocaleCommand implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        String locale = req.getParameter("localization");
        req.getSession().setAttribute("locale", locale);
        System.out.println(req.getSession().getAttribute("locale"));
        Config.set(req.getSession(), Config.FMT_LOCALE, locale);
        return PagePath.MAIN;
    }
}
