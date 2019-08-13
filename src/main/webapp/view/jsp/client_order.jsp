<%--
  Created by IntelliJ IDEA.
  User: aksenov
  Date: 8/1/19
  Time: 4:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<fmt:bundle basename="localization">
    <fmt:message key="locale.client_order.title" var="title"/>
    <fmt:message key="locale.client_order.no_orders" var="no_orders"/>
    <fmt:message key="locale.client_order.orders" var="orders"/>
    <fmt:message key="locale.client_order.weight" var="weight"/>
    <fmt:message key="locale.client_order.price" var="price"/>
    <fmt:message key="locale.client_order.time" var="time"/>
    <fmt:message key="locale.client_order.address" var="address"/>
    <fmt:message key="locale.client_order.accepted" var="accepted"/>
    <fmt:message key="locale.client_order.not_accepted" var="not_accepted"/>
    <fmt:message key="locale.client_order.courier_id" var="courier_id"/>
    <fmt:message key="locale.client_order.remove_order" var="remove_order"/>
</fmt:bundle>

<html>
<head>
    <title>${title}</title>
    <link rel="stylesheet" href="../../css/style.css" type="text/css">
</head>
<body class="bg">
<div>
    <%@include file="header.jsp" %>
</div>
<div>
    <form method="post" action="${pageContext.request.contextPath}/restaurant">
        <input type="hidden" name="command" value="remove_order">
        <div class="client_orders">
            <c:if test="${sessionScope.userOrder == null}">
                <div class="client_orders_title">
                        ${no_orders}
                </div>
            </c:if>
            <c:if test="${sessionScope.userOrder != null}">
                <div class="client_orders_title">
                        ${orders}
                </div>

                <div class="client_orders_div">
                    <c:forEach items="${sessionScope.userOrder}" var="userOrders">
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
                                        ${weight} : <c:out value="${userOrders.totalWeight}"/>
                                </div>
                                <div class="res_order_price">
                                        ${price} : <c:out value="${userOrders.totalPrice}"/>
                                </div>
                                <div class="res_order_price">
                                        ${time} : <c:out value="${userOrders.time}"/>
                                </div>
                                <div class="res_order_price">
                                        ${address} : <c:out value="${userOrders.address}"/>
                                </div>
                            </div>
                            <div>
                                <div class="client_orders_accepted">
                                    <c:if test="${userOrders.orderState == 'ACCEPTED'}">
                                        ${accepted}
                                        <div class="res_order_price">
                                                ${courier_id} : <c:out value="${userOrders.idCourier}"/>
                                        </div>
                                    </c:if>
                                </div>
                                <div class="client_orders_no_accepted">
                                    <c:if test="${userOrders.orderState == 'NOT_ACCEPTED'}">
                                        ${not_accepted}
                                        <div>
                                            <button class="client_orders_btn_remove" type="submit" name="remove_order"
                                                    value="${userOrders.id}">${remove_order}</button>
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