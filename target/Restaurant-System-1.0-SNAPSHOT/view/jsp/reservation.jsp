<%--
  Created by IntelliJ IDEA.
  User: aksenov
  Date: 7/31/19
  Time: 7:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<fmt:bundle basename="localization">
    <fmt:message key="locale.reservation.title" var="title"/>
    <fmt:message key="locale.reservation.checkout" var="checkout"/>
    <fmt:message key="locale.reservation.address" var="address"/>
    <fmt:message key="locale.reservation.your_order" var="your_order"/>
    <fmt:message key="locale.reservation.weight" var="weight"/>
    <fmt:message key="locale.reservation.price" var="price"/>
    <fmt:message key="locale.reservation.edit" var="edit"/>
</fmt:bundle>
<html>
<head>
    <title>${title}</title>
    <link rel="stylesheet" href="../../css/style.css" type="text/css">
</head>
<body class="bg">
<%@include file="jspf/header.jsp" %>

<form method="post" action="${pageContext.request.contextPath}/restaurant">
    <input type="hidden" name="command" value="check_out"/>
    <div>
        <div>
            <button class="res_btn_checkout" type="submit" name="check_out">${checkout}</button>
        </div>
        <div>
            <label>
                <input class="res_input_address" type="text" required placeholder="${address}" name="address">
            </label>
        </div>
        <div class="res_order">
            <div class="res_order_title">
                ${your_order}
            </div>
            <div class="res_order_dish">
                <c:forEach items="${sessionScope.order.dishes}" var="orderDish">
                    <c:forEach items="${sessionScope.dishes}" var="dish">
                        <c:if test="${orderDish.key == dish.id}">
                            <c:out value="${dish.name}"/>
                        </c:if>
                    </c:forEach>х
                    <c:out value="${orderDish.value}"/><br>
                </c:forEach>
            </div>
            <div class="res_order_detail">
                <div class="res_order_weight">
                    ${weight} : <c:out value="${sessionScope.order.totalWeight}"/>
                </div>
                <div class="res_order_price">
                    ${price} : <c:out value="${sessionScope.order.totalPrice}"/>
                </div>
            </div>
        </div>
        <div>
            <div>
                <button class="res_btn_edit" type="button"
                        onclick="location.href='${pageContext.request.contextPath}/view/jsp/menu.jsp'">${edit}
                </button>
            </div>
        </div>
    </div>
</form>
<%@include file="jspf/footer.jsp" %>
</body>
</html>
