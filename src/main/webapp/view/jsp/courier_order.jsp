<%--
  Created by IntelliJ IDEA.
  User: aksenov
  Date: 8/3/19
  Time: 11:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<fmt:bundle basename="localization">
    <fmt:message key="locale.courier_order.title" var="title"/>
    <fmt:message key="locale.courier_order.no_orders" var="no_orders"/>
    <fmt:message key="locale.courier_order.orders" var="orders"/>
    <fmt:message key="locale.courier_order.weight" var="weight"/>
    <fmt:message key="locale.courier_order.price" var="price"/>
    <fmt:message key="locale.courier_order.time" var="time"/>
    <fmt:message key="locale.courier_order.address" var="address"/>
    <fmt:message key="locale.courier_order.accepted" var="accepted"/>
    <fmt:message key="locale.courier_order.not_accepted" var="not_accepted"/>
    <fmt:message key="locale.courier_order.courier_id" var="courier_id"/>
    <fmt:message key="locale.courier_order.accept_order" var="accept_order"/>
    <fmt:message key="locale.courier_order.your_order" var="your_order"/>
</fmt:bundle>

<html>
<head>
    <title>${title}</title>
    <link rel="stylesheet" href="../../css/style.css" type="text/css">
</head>
<body class="bg">
<div>
    <%@include file="jspf/header.jsp" %>
</div>
<div>
    <form method="post" action="${pageContext.request.contextPath}/restaurant">
        <input type="hidden" name="command" value="accept_order">
        <div class="client_orders">
            <c:if test="${sessionScope.allOrders == null}">
                <div class="client_orders_title">
                        ${no_orders}
                </div>
            </c:if>
            <c:if test="${sessionScope.allOrders != null}">
                <div class="client_orders_title">
                        ${orders}
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
                                        <c:if test="${sessionScope.iduser == userOrders.idCourier}">
                                            <div class="res_order_price">
                                                    ${your_order}
                                            </div>
                                        </c:if>
                                        <c:if test="${sessionScope.iduser != userOrders.idCourier}">
                                            <div class="res_order_price">
                                                    ${courier_id} : <c:out value="${userOrders.idCourier}"/>
                                            </div>
                                        </c:if>
                                    </c:if>
                                </div>
                                <div class="client_orders_no_accepted">
                                    <c:if test="${userOrders.orderState == 'NOT_ACCEPTED'}">
                                        ${not_accepted}
                                        <div>
                                            <button class="client_orders_btn_remove" type="submit" name="accept_order"
                                                    value="${userOrders.id}">${accept_order}</button>
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
    <%@include file="jspf/footer.jsp" %>
</div>
</body>
</html>