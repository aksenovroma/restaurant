package by.epam.javatraining.restaurant.command;

import by.epam.javatraining.restaurant.model.dao.UserDAO;
import by.epam.javatraining.restaurant.model.dao.implementation.UserDAOImpl;
import by.epam.javatraining.restaurant.model.entity.UserRole;
import by.epam.javatraining.restaurant.model.exception.DAOException;
import by.epam.javatraining.restaurant.model.exception.UserDAOException;
import by.epam.javatraining.restaurant.util.PagePath;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class ChangeRoleCommand implements Command {
    private static final UserDAO userDAO = new UserDAOImpl();
    private static final Logger LOGGER = Logger.getRootLogger();

    @Override
    public String execute(HttpServletRequest req) {
        String client = req.getParameter("userRoleClient");
        String courier = req.getParameter("userRoleCourier");
        System.out.println(client);
        System.out.println(courier);

        try {
            if (client != null) {
                int idUser = Integer.valueOf(client);
                userDAO.updateUserRole(idUser, UserRole.COURIER);
                req.getSession().setAttribute("role", UserRole.COURIER.getRole());

            } else if (courier != null){
                int idUser = Integer.valueOf(courier);
                userDAO.updateUserRole(idUser, UserRole.CLIENT);
                req.getSession().setAttribute("role", UserRole.CLIENT.getRole());
            }
            req.getSession().setAttribute("users", userDAO.getAll());
        } catch (DAOException e) {
            LOGGER.error(e);
        }

        return PagePath.USERS_LIST;
    }
}