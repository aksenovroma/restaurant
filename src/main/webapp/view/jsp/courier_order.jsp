<%--
  Created by IntelliJ IDEA.
  User: aksenov
  Date: 8/3/19
  Time: 11:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Client Order</title>
    <link rel="stylesheet" href="../../css/style.css" type="text/css">
</head>
<body class="bg">
<div>
    <%@include file="header.jsp" %>
</div>
<div>
    <form method="post" action="${pageContext.request.contextPath}/restaurant">
        <input type="hidden" name="command" value="accept_order">
        <div class="client_orders">
            <c:if test="${sessionScope.allOrders == null}">
                <div class="client_orders_title">
                    У вас нет заказов
                </div>
            </c:if>
            <c:if test="${sessionScope.allOrders != null}">
                <div class="client_orders_title">
                    Заказы
                </div>

                <div class="client_orders_div">
                    <c:forEach items="${sessionScope.allOrders}" var="userOrders">
                        <div class="client_orders_order">
                            <div class="client_orders_dish">
                                <c:forEach items="${userOrders.dishes}" var="orderDish">
                                    <c:forEach items="${sessionScope.dishes}" var="dish">
                                        <c:if test="${orderDish.key == dish.id}">
                                            <c:out value="${dish.name}"/>
                                        </c:if>
                                    </c:forEach>х
                                    <c:out value="${orderDish.value}"/><br>
                                </c:forEach>
                            </div>
                            <div class="client_orders_detail">
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
                            </div>
                            <div>
                                <div class="client_orders_accepted">
                                    <c:if test="${userOrders.orderState == 'ACCEPTED'}">
                                        ACCEPTED
                                        <c:if test="${sessionScope.iduser == userOrders.idCourier}">
                                            <div class="res_order_price">
                                                It's your order
                                            </div>
                                        </c:if>
                                        <c:if test="${sessionScope.iduser != userOrders.idCourier}">
                                            <div class="res_order_price">
                                                Courier ID : <c:out value="${userOrders.idCourier}"/>
                                            </div>
                                        </c:if>
                                    </c:if>
                                </div>
                                <div class="client_orders_no_accepted">
                                    <c:if test="${userOrders.orderState == 'NOT_ACCEPTED'}">
                                        NOT ACCEPTED
                                        <div>
                                            <button class="client_orders_btn_remove" type="submit" name="accept_order" value="${userOrders.id}">Accept order</button>
                                        </div>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:if>
        </div>
    </form>
</div>
<div>
    <%@include file="footer.jsp" %>
</div>
</body>
</html>