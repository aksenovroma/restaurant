<%--
  Created by IntelliJ IDEA.
  User: aksenov
  Date: 8/3/19
  Time: 3:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Users</title>
    <link rel="stylesheet" href="../../css/style.css" type="text/css">
</head>
<body class="bg">
<%@include file="header.jsp"%>
<form id="user_form" method="post" action="${pageContext.request.contextPath}/restaurant">
    <input type="hidden" name="command" value="change_role"/>
    <div>
        <div class="show_users_title">
            Users
        </div>
        <div class="show_users_info_div">
            <table>
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Login</th>
                    <th>Password</th>
                    <th>Photo</th>
                    <th class="show_users_info_role">Role</th>
                </tr>
                <c:forEach items="${sessionScope.users}" var="user">
                    <tr>
                        <td><c:out value="${user.id}"/></td>
                        <td><c:out value="${user.name}"/></td>
                        <td><c:out value="${user.login}"/></td>
                        <td><c:out value="${user.password}"/></td>
                        <td><c:out value="${user.photo}"/></td>
                        <td>
                            <c:if test="${user.userRole == 'ADMIN'}">
                                <div class="show_users_info_admin">
                                    <c:out value="${user.userRole}"/>
                                </div>
                            </c:if>
                            <c:if test="${user.userRole == 'CLIENT'}">
                                <button class="show_users_info_btn" type="submit"
                                        name="userRoleClient" value="${user.id}" title="click to change">
                                    CLIENT
                                </button>
                            </c:if>
                            <c:if test="${user.userRole == 'COURIER'}">
                                <button class="show_users_info_btn" type="submit"
                                        name="userRoleCourier" value="${user.id}" title="click to change">
                                    COURIER
                                </button>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</form>
<%@include file="footer.jsp"%>
</body>
</html>
