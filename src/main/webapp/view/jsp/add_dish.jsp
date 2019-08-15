<%--
  Created by IntelliJ IDEA.
  User: aksenov
  Date: 8/3/19
  Time: 12:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<fmt:bundle basename="localization">
    <fmt:message key="locale.add_dish.title" var="title"/>
    <fmt:message key="locale.add_dish.choose" var="choose"/>
    <fmt:message key="locale.add_dish.appetizer" var="appetizer"/>
    <fmt:message key="locale.add_dish.soup" var="soup"/>
    <fmt:message key="locale.add_dish.dessert" var="dessert"/>
    <fmt:message key="locale.add_dish.drink" var="drink"/>
    <fmt:message key="locale.add_dish.name" var="name"/>
    <fmt:message key="locale.add_dish.price" var="price"/>
    <fmt:message key="locale.add_dish.weight" var="weight"/>
    <fmt:message key="locale.add_dish.image_url" var="image_url"/>
    <fmt:message key="locale.add_dish.description" var="description"/>
    <fmt:message key="locale.add_dish.add" var="add"/>
</fmt:bundle>
<html>
<head>
    <title>${title}</title>
    <link rel="stylesheet" href="../../css/style.css" type="text/css">
    <script src="../../js/numberValidation.js"></script>
</head>
<body class="bg">
    <%@include file="jspf/header.jsp"%>
    <div>
        <form id="form_add_dish" method="post" action="${pageContext.request.contextPath}/restaurant"
              onsubmit="return validateNumber('form_add_dish','price','weight')">
            <input type="hidden" name="command" value="add_dish"/>
            <div class="login-page">
                <div class="add_dish_form">
                    <form class="register-form">
                        <select name="category">
                            <option disabled>${choose}</option>
                            <option value="appetizer">${appetizer}</option>
                            <option value="soup">${soup}</option>
                            <option value="dessert">${dessert}</option>
                            <option value="drink">${drink}</option>
                        </select>
                        <input type="text" required placeholder="${name}" name="name"/>
                        <input type="text" required placeholder="${price}" name="price"/>
                        <input type="text" required placeholder="${weight}" name="weight"/>
                        <input type="text" required placeholder="${image_url}" name="image-url"/>
                        <input type="text" required placeholder="${description}" name="description"/>
                        <input type="submit" value="${add}">
                    </form>
                </div>
            </div>
        </form>
    </div>
    <%@include file="jspf/footer.jsp"%>
</body>
</html>
