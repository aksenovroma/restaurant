<%--
  Created by IntelliJ IDEA.
  User: aksenov
  Date: 8/1/19
  Time: 4:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Client Order</title>
    <link rel="stylesheet" href="../../css/style.css" type="text/css">
</head>
<body>
<div>
    <form method="post" action="${pageContext.request.contextPath}/restaurant">
        <input type="hidden" name="command" value="remove_order">
        <div>
            <c:if test="${sessionScope.userOrder == null}">
                У вас нет заказов
            </c:if>
            <c:if test="${sessionScope.userOrder != null}">
                <div class="res_order_title">
                    Ваши заказы
                </div>

                <c:forEach items="${sessionScope.userOrder}" var="userOrders">
                    <div>
                        <div>
                            <c:forEach items="${userOrders.dishes}" var="orderDish">
                                <c:forEach items="${sessionScope.dishes}" var="dish">
                                    <c:if test="${orderDish.key == dish.id}">
                                        <c:out value="${dish.name}"/>
                                    </c:if>
                                </c:forEach>х
                                <c:out value="${orderDish.value}"/><br>
                            </c:forEach>
                        </div>
                        <div class="res_order_weight">
                            Weight : <c:out value="${userOrders.totalWeight}"/>
                        </div>
                        <div class="res_order_price">
                            Price : <c:out value="${userOrders.totalPrice}"/>
                        </div>
                        <div class="res_order_price">
                            Time : <c:out value="${userOrders.time}"/>
                        </div>
                        <div class="res_order_price">
                            Address : <c:out value="${userOrders.address}"/>
                        </div>
                        <div>
                            <div>
                                <c:if test="${userOrders.orderState == 'ACCEPTED'}">
                                    State :
                                    <div>
                                        ACCEPTED
                                    </div>
                                </c:if>
                            </div>
                            <div>
                                <c:if test="${userOrders.orderState == 'NOT_ACCEPTED'}">
                                    State :
                                    <div>
                                        NOT ACCEPTED
                                    </div>
                                    <div>
                                        <button type="submit" name="remove_order">Remove order</button>
                                    </div>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:if>
        </div>
    </form>
</div>
</body>
</html>