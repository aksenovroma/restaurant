package by.epam.javatraining.restaurant.model.logic;

import by.epam.javatraining.restaurant.model.entity.Dish;
import by.epam.javatraining.restaurant.model.entity.DishCategory;
import by.epam.javatraining.restaurant.model.entity.Order;
import org.apache.struts.mock.MockHttpServletRequest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class OrderManagerTest {
    private MockHttpServletRequest mockHttpServletRequest;
    private OrderManager orderManager;
    private Order order;

    @BeforeMethod
    public void setValue() {
        mockHttpServletRequest = new MockHttpServletRequest();
        final Dish dish1 = new Dish(1,"Окрошка", 2.3, 200,
                "http:/...1.png", "description1", DishCategory.SOUP);
        final Dish dish2 = new Dish(2,"Картошка", 3, 320,
                "http:/...2.png", "description2", DishCategory.APPETIZER);
        List dishes = new ArrayList() {
            {
                add(dish1);
                add(dish2);
            }
        };
        mockHttpServletRequest.getSession().setAttribute("dishes", dishes);
        order = new Order();
        orderManager = new OrderManager();
    }

    @Test
    public void testCalcTotalPrice() {
        HashMap<Integer, Integer> orderDetail = new HashMap<>();
        orderDetail.put(1, 2);
        orderDetail.put(2, 3);

        order.setDishes(orderDetail);

        double expected = 13.6;
        double actual = orderManager.calcTotalPrice(order, mockHttpServletRequest);

        assertEquals(actual, expected);
    }

    @Test
    public void testCalcTotalPriceNullOrder() {
        double expected = -1;
        double actual = orderManager.calcTotalPrice(null, mockHttpServletRequest);

        assertEquals(actual, expected);
    }

    @Test
    public void testCalcTotalPriceNullDishes() {
        order.setDishes(null);

        double expected = -1;
        double actual = orderManager.calcTotalPrice(order, mockHttpServletRequest);

        assertEquals(actual, expected);
    }

    @Test
    public void testCalcTotalPriceEmptyDishes() {
        HashMap<Integer, Integer> orderDetail = new HashMap<>();

        order.setDishes(orderDetail);

        double expected = 0;
        double actual = orderManager.calcTotalPrice(order, mockHttpServletRequest);

        assertEquals(actual, expected);
    }

    @Test
    public void testCalcTotalWeight() {
        HashMap<Integer, Integer> orderDetail = new HashMap<>();
        orderDetail.put(1, 2);
        orderDetail.put(2, 3);

        order.setDishes(orderDetail);

        double expected = 1360;
        double actual = orderManager.calcTotalWeight(order, mockHttpServletRequest);

        assertEquals(actual, expected);
    }

    @Test
    public void testCalcTotalWeightNullOrder() {
        double expected = -1;
        double actual = orderManager.calcTotalWeight(null, mockHttpServletRequest);

        assertEquals(actual, expected);
    }

    @Test
    public void testCalcTotalWeightNullDishes() {
        order.setDishes(null);

        double expected = -1;
        double actual = orderManager.calcTotalWeight(order, mockHttpServletRequest);

        assertEquals(actual, expected);
    }

    @Test
    public void testCalcTotalWeightEmptyDishes() {
        HashMap<Integer, Integer> orderDetail = new HashMap<>();

        order.setDishes(orderDetail);

        double expected = 0;
        double actual = orderManager.calcTotalWeight(order, mockHttpServletRequest);

        assertEquals(actual, expected);
    }
}
