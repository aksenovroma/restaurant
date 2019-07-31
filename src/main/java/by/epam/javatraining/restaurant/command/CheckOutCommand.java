package by.epam.javatraining.restaurant.command;

import by.epam.javatraining.restaurant.util.PagePath;

import javax.servlet.http.HttpServletRequest;

public class CheckOutCommand implements Command {
    @Override
    public String execute(HttpServletRequest req) {
        return PagePath.MENU;
    }
}
