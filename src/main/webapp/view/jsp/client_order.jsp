<%--
  Created by IntelliJ IDEA.
  User: aksenov
  Date: 8/1/19
  Time: 4:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Client Order</title>
    <link rel="stylesheet" href="../../css/style.css" type="text/css">
</head>
<body>
    <%@include file="header.jsp"%>
    <form method="post" action="${pageContext.request.contextPath}/restaurant">
        <input type="hidden" name="command" value="remove_order">
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
                    Weight : <c:out value="${sessionScope.userOrder.totalWeight}"/>
                </div>
                <div class="res_order_price">
                    Price : <c:out value="${sessionScope.userOrder.totalPrice}"/>
                </div>
                <div class="res_order_price">
                    Time : <c:out value="${sessionScope.userOrder.time}"/>
                </div>
                <div class="res_order_price">
                    Address : <c:out value="${sessionScope.userOrder.address}"/>
                </div>
                <div>
                    <div>
                        <c:if test="${sessionScope.userOrder.orderState == 'accepted'}">
                            State :
                            <div>
                                State : <c:out value="${sessionScope.userOrder.orderState}"/>
                            </div>
                        </c:if>
                    </div>
                    <div>
                        <c:if test="${sessionScope.userOrder.orderState == 'not_accepted'}">
                            State :
                            <div>
                                State : <c:out value="${sessionScope.userOrder.orderState}"/>
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
        <div>
            <c:if test="${sessionScope.userOrder.orderState == 'not_accepted'}">
                State :
                <div>
                    <button class="res_btn_edit" type="submit" name="remove_order">Remove order</button>
                </div>
            </c:if>
        </div>
    </form>
    <%@include file="footer.jsp"%>
</body>
</html>
