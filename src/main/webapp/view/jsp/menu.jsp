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
    <style type="text/css">
        #menu {
            margin: 7% 20px;
            border-color: azure;
            position: center;
            text-align: -webkit-center;
            outline: 2px solid #000;
        }

        #menu_title {
            padding: 10px;
            background: #85b5a5;
            color: #fff;
        }

        #menu_image {
            position: center;
            width: 300px;
            height: 200px;
            text-align: center;
        }

        #menu_category {
            text-align: center;
            font-size: 25px;
        }

        #attention {
            color: firebrick;
            font-size: 20px;
            margin-bottom: 10px;
        }

        #btn_reservation{
            margin-bottom: 30px;
            color: #fff; /* цвет текста */
            text-decoration: none; /* убирать подчёркивание у ссылок */
            user-select: none; /* убирать выделение текста */
            background: rgb(35, 181, 90); /* фон кнопки */
            padding: .7em 1.5em;
            font-size: 30px;
        }
        #btn_plus{
            display: inline-block;
            width: 34px;
            height: 34px;
            background-image: url(https://img.icons8.com/dotty/30/000000/plus.png);
            background-size: 30px, 30px;
            cursor: pointer;
            border-radius: 50%;
        }
        #btn_minus{
            display: inline-block;
            width: 34px;
            height: 34px;
            background-image: url(https://img.icons8.com/dotty/30/000000/minus.png);
            background-size: 30px, 30px;
            cursor: pointer;
            border-radius: 50%;
        }
        #dish_count{
            display: inline-block;
            font-size: 30px;
            width: 34px;
            height: 34px;
        }

        #btn_clear{
            margin-bottom: 30px;
            color: #fff; /* цвет текста */
            text-decoration: none; /* убирать подчёркивание у ссылок */
            user-select: none; /* убирать выделение текста */
            background: rgb(143, 181, 148); /* фон кнопки */
            padding: .7em 1.5em;
            font-size: 30px;
        }

    </style>
</head>
<body>

<%@include file="header.jsp" %>

<form method="post" action="${pageContext.request.contextPath}/restaurant">
    <input type="hidden" name="command" value="dish_action"/>

    <div class="menu">
        <h1 class="menu_title">Menu</h1>

        <c:set var="dishes" value="${sessionScope.dishes}"/>

        <c:if test="${sessionScope.username != null}">
            <c:if test="${sessionScope.role == 'client'}">
                <div>
                    <input class="menu_btn_reservation" type="submit" name="res_action" value="Reservation">
                </div>
                <div>
                    <input class="menu_btn_clear" type="submit" name="clr_action" value="Clear">
                </div>
            </c:if>
        </c:if>

        <c:if test="${sessionScope.username == null}">
            <div class="menu_attention">Registration required for ordering</div>
        </c:if>

        <div>
            <c:forEach var="dish" items="${dishes}">
                <table>
                    <tr>
                        <td>
                            <h4 class="menu_dish_category"><c:out value="${dish.dishCategory}"/></h4>
                        </td>
                    </tr>
                    <tr align="center">
                        <td>
                            <img class="menu_dish_image" src="${dish.photo}" alt="dish image">

                            <h4><c:out value="${dish.name}"/></h4>

                            <c:out value="${dish.description}"/>
                        </td>
                    </tr>
                    <tr align="center">
                        <td colspan="2">
                            Weight : <c:out value="${dish.weight}"/>
                            Price : <c:out value="${dish.price}"/>
                        </td>
                    </tr>
                    <tr align="center">
                        <td>
                            <c:if test="${sessionScope.username != null}">
                                <c:if test="${sessionScope.role == 'client'}">
                                    <div>
                                        <button class="menu_btn_minus" type="submit" name="remove_action" value="${dish.id}"></button>
                                    </div>
                                        <c:forEach items="${sessionScope.orderDishes}" var="portion">
                                            <c:if test="${dish.id == portion.key}">
                                                <div id="dish_count"><c:out value="${portion.value}"/></div>
                                            </c:if>
                                        </c:forEach>
                                    <div>
                                        <button class="menu_btn_plus" type="submit" name="add_action" value="${dish.id}"></button>
                                    </div>
                                </c:if>
                            </c:if>
                        </td>
                    </tr>
                </table><br>
            </c:forEach>
        </div>
    </div>
</form>

<%@include file="footer.jsp" %>

</body>
</html>
