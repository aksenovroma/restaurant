package by.epam.javatraining.restaurant.command;

import by.epam.javatraining.restaurant.model.dao.UserDAO;
import by.epam.javatraining.restaurant.model.dao.implementation.UserDAOImpl;
import by.epam.javatraining.restaurant.model.entity.UserRole;
import by.epam.javatraining.restaurant.model.exception.tecnical.DAOException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static by.epam.javatraining.restaurant.util.Constant.*;

public class ChangeRoleCommand implements Command {
    private static final UserDAO userDAO = new UserDAOImpl();
    private static final Logger LOGGER = Logger.getRootLogger();

    @Override
    public String execute(HttpServletRequest req) {
        String client = req.getParameter(getConst(PAR_USER_ROLE_CLIENT));
        String courier = req.getParameter(getConst(PAR_USER_ROLE_COURIER));
        System.out.println(client);
        System.out.println(courier);

        try {
            if (client != null) {
                int idUser = Integer.valueOf(client);
                userDAO.updateUserRole(idUser, UserRole.COURIER);
                req.getSession().setAttribute(getConst(ATR_ROLE), UserRole.COURIER.getRole());

            } else if (courier != null){
                int idUser = Integer.valueOf(courier);
                userDAO.updateUserRole(idUser, UserRole.CLIENT);
                req.getSession().setAttribute(getConst(ATR_ROLE), UserRole.CLIENT.getRole());
            }
            req.getSession().setAttribute(getConst(ATR_USERS), userDAO.getAll());
        } catch (DAOException e) {
            LOGGER.error(e);
        }

        return getConst(PAGE_USERS_LIST);
    }
}
