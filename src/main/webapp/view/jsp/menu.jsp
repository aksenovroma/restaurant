<%--
  Created by IntelliJ IDEA.
  User: aksenov
  Date: 5/17/19
  Time: 2:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Menu</title>
    <link rel="stylesheet" href="../../css/style.css" type="text/css">
    <script src="../../js/redirectPage.js"></script>
</head>
<body class="bg">
<div>
    <%@include file="header.jsp" %>
</div>


<form method="post" action="${pageContext.request.contextPath}/restaurant">
    <input type="hidden" name="command" value="dish_action"/>
    <div>
        <div class="menu_btn_div">
            <c:if test="${sessionScope.username != null}">
                <c:if test="${sessionScope.role == 'client'}">
                    <div>
                        <button class="menu_btn_reservation" type="submit" name="res_action">Reservation</button>
                    </div>
                    <div>
                        <button class="menu_btn_clear" type="submit" name="clr_action">Clear</button>
                    </div>
                </c:if>
                <c:if test="${sessionScope.role == 'admin'}">
                    <div>
                        <a href="${pageContext.request.contextPath}/view/jsp/add_dish.jsp">
                            <input class="menu_btn_add" type="button" value="Add new Dish"/>
                        </a>
                    </div>
                </c:if>
            </c:if>
        </div>
        <div class="menu_dish_div">
            <c:forEach items="${sessionScope.dishes}" var="dish">
                <div class="menu_dish">
                    <div class="menu_dish_category">
                        <c:out value="${dish.dishCategory}"/>
                    </div>
                    <div>
                        <div>
                            <img class="menu_dish_image" src="${dish.photo}" alt="dish image">
                        </div>
                        <div class="menu_dish_name">
                            <c:out value="${dish.name}"/>
                        </div>
                        <div class="menu_dish_description" title="${dish.description}">
                            <c:out value="${dish.description}"/>
                        </div>
                    </div>
                    <div class="menu_dish_detail">
                        <div>
                            Weight : <c:out value="${dish.weight}"/>
                        </div>
                        <div>
                            Price : <c:out value="${dish.price}"/>
                        </div>
                    </div>
                    <div>
                        <c:if test="${sessionScope.username != null}">
                            <c:if test="${sessionScope.role == 'client'}">
                                <div class="menu_count">
                                    <button class="menu_btn_minus" type="submit" name="remove_action" value="${dish.id}"></button>
                                </div>
                                <c:forEach items="${sessionScope.orderDishes}" var="portion">
                                    <c:if test="${dish.id == portion.key}">
                                        <div class="menu_count">
                                            <div class="menu_dish_count"><c:out value="${portion.value}"/></div>
                                        </div>
                                    </c:if>
                                </c:forEach>
                                <div class="menu_count">
                                    <button class="menu_btn_plus" type="submit" name="add_action" value="${dish.id}"></button>
                                </div>
                            </c:if>
                            <c:if test="${sessionScope.role == 'admin'}">
                                <div>
                                    <button class="client_orders_btn_remove" type="submit" name="remove_dish_action" value="${dish.id}">Remove</button>
                                </div>
                            </c:if>
                        </c:if>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</form>

<%@include file="footer.jsp" %>

</body>
</html>
