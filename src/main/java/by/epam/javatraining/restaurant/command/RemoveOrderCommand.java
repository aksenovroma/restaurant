package by.epam.javatraining.restaurant.command;

import by.epam.javatraining.restaurant.model.dao.OrderDAO;
import by.epam.javatraining.restaurant.model.dao.implementation.OrderDAOImpl;
import by.epam.javatraining.restaurant.model.exception.tecnical.DAOException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static by.epam.javatraining.restaurant.util.Constant.*;

public class RemoveOrderCommand implements Command {
    private static final OrderDAO orderDAO = new OrderDAOImpl();
    private static final Logger LOGGER = Logger.getRootLogger();

    @Override
    public String execute(HttpServletRequest req) {
        String page = getConst(PAGE_CLIENT_ORDER);
        try {
            int idOrder = Integer.parseInt(req.getParameter(getConst(PAR_REMOVE_ORDER)));
            orderDAO.delete(idOrder);
            List orders = orderDAO.getAllById((Integer) req.getSession().getAttribute(getConst(ATR_ID_USER)));
            if (!orders.isEmpty()) {
                req.getSession().setAttribute(getConst(ATR_USER_ORDER), orders);
            } else {
                req.getSession().setAttribute(getConst(ATR_USER_ORDER), null);
            }
        } catch (DAOException e) {
            LOGGER.error(e);
        }
        LOGGER.trace(this.getClass().getName() + getConst(RETURN) + page);
        return page;
    }
}
