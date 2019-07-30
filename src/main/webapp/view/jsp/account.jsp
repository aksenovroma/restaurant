<%--
  Created by IntelliJ IDEA.
  User: aksenov
  Date: 5/21/19
  Time: 1:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <style type="text/css">
        #account {
            margin-top: 7%;
            margin-left: auto;
            margin-right: auto;
            text-align: center;
            outline: 2px solid #000;
        }
        #account_title {
            text-align: center;
            font-size: 30px;
        }
        #order {
            text-align: center;
            outline: 2px solid #000;
            margin: 3% auto 5%;
        }
        #order_title {
            text-align: center;
            font-size: 30px;
        }
        #order_attention {
            color: firebrick;
            font-size: 20px;
        }
    </style>
    <title>Account</title>
</head>
<body>

<%@include file="header.jsp" %>

<form method="post" action="${pageContext.request.contextPath}/restaurant">
    <input type="hidden" name="command" value="account_action"/>
    <table id="account">
        <tr id="account_title">
            <td>
                Account information
                <img id="menu_image" src="${sessionScope.userphoto}" alt="user photo">
            </td>
        </tr>
        <tr>
            <td>
                Role : <c:out value="${sessionScope.role}"/>
            </td>
        </tr>
        <tr>
            <td>
                Name : <c:out value="${sessionScope.username}"/>
            </td>
        </tr>
        <tr>
            <td>
                Login : <c:out value="${sessionScope.login}"/>
            </td>
        </tr>
        <tr>
            <td>
                Password : <c:out value="${sessionScope.password}"/>
            </td>
        </tr>
    </table>
    <c:if test="${sessionScope.role == 'CLIENT'}">
        <table id="order">
            <tr>
                <td id="order_title">
                    Order
                </td>
            </tr>
            <tr>
                <td id="order_attention">
                    <c:if test="${sessionScope.order == null}">
                        You do not have any orders
                    </c:if>
                </td>
            </tr>
            <tr>
                <c:if test="${sessionScope.order != null}">
                <td>
                    Dishes:<br>
                    <c:forEach items="${sessionScope.portionCount}" var="dish">
                        <c:out value="${dish.key}"/> х
                        <c:out value="${dish.value}"/><br>
                    </c:forEach>
                </td>
            </tr>
            <tr>
                <td>
                    Client's name : <c:out value="${sessionScope.order.client.name}" default="no name"/>
                </td>
            </tr>
            <tr>
                <td>
                    Waiter's name : <c:out value="${sessionScope.order.waiter.name}" default="no name"/>
                </td>
            </tr>
            <tr>
                <td>
                    Order time : <c:out value="${sessionScope.order.dataTime}"/>
                </td>
            </tr>
            <tr>
                <td>
                    Weight : <c:out value="${sessionScope.order.weight}"/>
                </td>
            </tr>
            <tr>
                <td>
                    Price : <c:out value="${sessionScope.order.price}"/>
                </td>
            </tr>
            <tr>
                <td>
                    Order state : <c:out value="${sessionScope.order.orderState.state}"/>
                </td>
            </tr>
            <tr>
                <td>
                    <button type="submit" name="action" value="remove">Remove</button>
                </td>
            </tr>
            </c:if>
        </table>
    </c:if>
    <c:if test="${sessionScope.role == 'WAITER'}">
        <table id="order">
            <tr>
                <td>
                    Orders
                </td>
            </tr>
            <tr>
                <td>
                    <c:forEach items="${sessionScope.allOrders}" var="order">
                        <tr>
                            <td>
                                <c:out value="${order.dish}"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Client's name : <c:out value="${order.client.name}" default="no name"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Waiter's name : <c:out value="${order.waiter.name}" default="no name"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Order time : <c:out value="${order.dataTime}"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Weight : <c:out value="${order.weight}"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Price : <c:out value="${order.price}"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Order state : <c:out value="${order.orderState.state}"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <button type="submit" name="submit_action" value="${order.client.name}">Submit</button>
                            </td>
                        </tr>
                </c:forEach>
        </table>
    </c:if>
</form>

<%@include file="footer.jsp" %>
</body>
</html>
