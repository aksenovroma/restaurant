package by.epam.javatraining.restaurant.command;

import by.epam.javatraining.restaurant.model.dao.OrderDAO;
import by.epam.javatraining.restaurant.model.dao.implementation.OrderDAOImpl;
import by.epam.javatraining.restaurant.model.exception.DAOException;
import by.epam.javatraining.restaurant.util.PagePath;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class RemoveOrderCommand implements Command {
    private static final OrderDAO orderDAO = new OrderDAOImpl();
    private static final Logger LOGGER = Logger.getRootLogger();

    @Override
    public String execute(HttpServletRequest req) {
        try {
            int idOrder = Integer.parseInt(req.getParameter("remove_order"));
            orderDAO.delete(idOrder);
            List orders = orderDAO.getAllById((Integer)req.getSession().getAttribute("iduser"));
            if (!orders.isEmpty()) {
                req.getSession().setAttribute("userOrder", orders);
            } else {
                req.getSession().setAttribute("userOrder", null);
            }
        } catch (DAOException e) {
            LOGGER.error(e);
        }
        return PagePath.CLIENT_ORDER;
    }
}
