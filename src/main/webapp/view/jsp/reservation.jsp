<%--
  Created by IntelliJ IDEA.
  User: aksenov
  Date: 7/31/19
  Time: 7:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Reservation</title>
    <link rel="stylesheet" href="../../css/style.css" type="text/css">
</head>
<body class="bg">
    <%@include file="header.jsp"%>

    <form method="post" action="${pageContext.request.contextPath}/restaurant">
        <input type="hidden" name="command" value="check_out"/>
            <div>
                <div>
                    <button class="res_btn_checkout" type="submit" name="check_out">Сheckout</button>
                </div>
                <div>
                    <label>
                        <input class="res_input_address" type="text" required placeholder="address" name="address">
                    </label>
                </div>
                <div class="res_order">
                    <div class="res_order_title">
                        Ваш заказ
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
                            Weight : <c:out value="${sessionScope.order.totalWeight}"/>
                        </div>
                        <div class="res_order_price">
                            Price : <c:out value="${sessionScope.order.totalPrice}"/>
                        </div>
                    </div>
                </div>
                <div>
                    <div>
                        <button class="res_btn_edit" type="button"
                                onclick="location.href='${pageContext.request.contextPath}/view/jsp/menu.jsp'">Edit
                        </button>
                    </div>
                </div>
            </div>
    </form>


    <%@include file="footer.jsp"%>
</body>
</html>
