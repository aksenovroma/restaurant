<%--
  Created by IntelliJ IDEA.
  User: aksenov
  Date: 8/3/19
  Time: 12:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>New Dish</title>
    <link rel="stylesheet" href="../../css/style.css" type="text/css">
    <script src="../../js/numberValidation.js"></script>
</head>
<body class="bg">
    <%@include file="header.jsp"%>
    <div>
        <form id="form_add_dish" method="post" action="${pageContext.request.contextPath}/restaurant"
              onsubmit="javascript:return validateNumber('form_add_dish','price','weight')">
            <input type="hidden" name="command" value="add_dish"/>
            <div class="login-page">
                <div class="add_dish_form">
                    <form class="register-form">
                        <select name="category">
                            <option disabled>Choose dish category</option>
                            <option value="appetizer">Appetizer</option>
                            <option value="soup">Soup</option>
                            <option value="dessert">Dessert</option>
                            <option value="drink">Drink</option>
                        </select>
                        <input type="text" required placeholder="name" name="name"/>
                        <input type="text" required placeholder="price" name="price"/>
                        <input type="text" required placeholder="weight" name="weight"/>
                        <input type="text" required placeholder="image-url" name="image-url"/>
                        <input type="text" required placeholder="description" name="description"/>
                        <input type="submit" value="Add">
                    </form>
                </div>
            </div>
        </form>
    </div>
    <%@include file="footer.jsp"%>
</body>
</html>
