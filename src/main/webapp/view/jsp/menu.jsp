<%--
  Created by IntelliJ IDEA.
  User: aksenov
  Date: 5/17/19
  Time: 2:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<fmt:bundle basename="localization">
    <fmt:message key="locale.menu.title" var="title"/>
    <fmt:message key="locale.menu.reservation" var="reservation"/>
    <fmt:message key="locale.menu.add_new_dish" var="add_new_dish"/>
    <fmt:message key="locale.menu.clear" var="clear"/>
    <fmt:message key="locale.menu.weight" var="weihgt"/>
    <fmt:message key="locale.menu.price" var="price"/>
    <fmt:message key="locale.menu.remove" var="remove"/>
    <fmt:message key="locale.menu.category.appetizer" var="appetizer"/>
    <fmt:message key="locale.menu.category.soup" var="soup"/>
    <fmt:message key="locale.menu.category.dessert" var="dessert"/>
    <fmt:message key="locale.menu.category.drink" var="drink"/>
</fmt:bundle>

<html>
<head>
    <title>${title}</title>
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
                        <button class="menu_btn_reservation" type="submit" name="res_action">${reservation}</button>
                    </div>
                    <div>
                        <button class="menu_btn_clear" type="submit" name="clr_action">${clear}</button>
                    </div>
                </c:if>
                <c:if test="${sessionScope.role == 'admin'}">
                    <div>
                        <a href="${pageContext.request.contextPath}/view/jsp/add_dish.jsp">
                            <input class="menu_btn_add" type="button" value="${add_new_dish}"/>
                        </a>
                    </div>
                </c:if>
            </c:if>
        </div>
        <div class="menu_dish_div">
            <c:forEach items="${sessionScope.dishes}" var="dish">
                <div class="menu_dish">
                    <div class="menu_dish_category">
                        <c:choose>
                            <c:when test="${dish.dishCategory == 'APPETIZER'}">
                                <c:out value="${appetizer}"/>
                            </c:when>
                            <c:when test="${dish.dishCategory == 'DESSERT'}">
                                <c:out value="${dessert}"/>
                            </c:when>
                            <c:when test="${dish.dishCategory == 'SOUP'}">
                                <c:out value="${soup}"/>
                            </c:when>
                            <c:when test="${dish.dishCategory == 'DRINK'}">
                                <c:out value="${drink}"/>
                            </c:when>
                        </c:choose>
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
                                ${weihgt} : <c:out value="${dish.weight}"/>
                        </div>
                        <div>
                                ${price} : <c:out value="${dish.price}"/>
                        </div>
                    </div>
                    <div>
                        <c:if test="${sessionScope.username != null}">
                            <c:if test="${sessionScope.role == 'client'}">
                                <div class="menu_count">
                                    <button class="menu_btn_minus" type="submit" name="remove_action"
                                            value="${dish.id}"></button>
                                </div>
                                <c:forEach items="${sessionScope.orderDishes}" var="portion">
                                    <c:if test="${dish.id == portion.key}">
                                        <div class="menu_count">
                                            <div class="menu_dish_count"><c:out value="${portion.value}"/></div>
                                        </div>
                                    </c:if>
                                </c:forEach>
                                <div class="menu_count">
                                    <button class="menu_btn_plus" type="submit" name="add_action"
                                            value="${dish.id}"></button>
                                </div>
                            </c:if>
                            <c:if test="${sessionScope.role == 'admin'}">
                                <div>
                                    <button class="client_orders_btn_remove" type="submit" name="remove_dish_action"
                                            value="${dish.id}">
                                            ${remove}
                                    </button>
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
