package by.epam.javatraining.restaurant.command;

import by.epam.javatraining.restaurant.model.dao.OrderDAO;
import by.epam.javatraining.restaurant.model.dao.implementation.OrderDAOImpl;
import by.epam.javatraining.restaurant.model.entity.OrderState;
import by.epam.javatraining.restaurant.model.exception.DAOException;
import by.epam.javatraining.restaurant.util.PagePath;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AcceptOrderCommand implements Command {
    private static final OrderDAO orderDAO = new OrderDAOImpl();
    private static final Logger LOGGER = Logger.getRootLogger();

    @Override
    public String execute(HttpServletRequest req) {
        try {
            int idOrder = Integer.parseInt(req.getParameter("accept_order"));
            int idCourier = (int) req.getSession().getAttribute("iduser");
            orderDAO.updateOrderState(idOrder, OrderState.ACCEPTED.getState());
            orderDAO.updateIdCourier(idOrder, idCourier);
            List orders = orderDAO.getAll();
            req.getSession().setAttribute("allOrders", orders);
        } catch (DAOException e) {
            LOGGER.error(e);
        }
        return PagePath.COURIER_ORDER;
    }
}
