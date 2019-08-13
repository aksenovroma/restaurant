package by.epam.javatraining.restaurant.command;

import by.epam.javatraining.restaurant.model.dao.OrderDAO;
import by.epam.javatraining.restaurant.model.dao.implementation.OrderDAOImpl;
import by.epam.javatraining.restaurant.model.entity.OrderState;
import by.epam.javatraining.restaurant.model.exception.tecnical.DAOException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static by.epam.javatraining.restaurant.util.Constant.*;

public class AcceptOrderCommand implements Command {
    private static final OrderDAO orderDAO = new OrderDAOImpl();
    private static final Logger LOGGER = Logger.getRootLogger();

    @Override
    public String execute(HttpServletRequest req) {
        try {
            int idOrder = Integer.parseInt(req.getParameter(getConst(PAR_ACCEPT_ORDER)));
            int idCourier = (int) req.getSession().getAttribute(getConst(ATR_ID_USER));
            orderDAO.updateOrderState(idOrder, OrderState.ACCEPTED.getState());
            orderDAO.updateIdCourier(idOrder, idCourier);
            List orders = orderDAO.getAll();
            req.getSession().setAttribute(getConst(ATR_ALL_ORDERS), orders);
        } catch (DAOException e) {
            LOGGER.error(e);
        }
        return getConst(PAGE_COURIER_ORDER);
    }
}
