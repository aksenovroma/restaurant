<%--
  Created by IntelliJ IDEA.
  User: aksenov
  Date: 8/3/19
  Time: 3:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<fmt:bundle basename="localization">
    <fmt:message key="locale.user_list.title" var="title"/>
    <fmt:message key="locale.user_list.users" var="users"/>
    <fmt:message key="locale.user_list.id" var="id"/>
    <fmt:message key="locale.user_list.name" var="name"/>
    <fmt:message key="locale.user_list.login" var="login"/>
    <fmt:message key="locale.user_list.password" var="password"/>
    <fmt:message key="locale.user_list.photo" var="photo"/>
    <fmt:message key="locale.user_list.role" var="role"/>
    <fmt:message key="locale.user_list.admin" var="admin"/>
    <fmt:message key="locale.user_list.client" var="client"/>
    <fmt:message key="locale.user_list.courier" var="courier"/>
    <fmt:message key="locale.user_list.click_to_change" var="click_to_change"/>
</fmt:bundle>
<html>
<head>
    <title>${title}</title>
    <link rel="stylesheet" href="../../css/style.css" type="text/css">
</head>
<body class="bg">
<%@include file="jspf/header.jsp" %>
<form id="user_form" method="post" action="${pageContext.request.contextPath}/restaurant">
    <input type="hidden" name="command" value="change_role"/>
    <div>
        <div class="show_users_title">
            ${users}
        </div>
        <div class="show_users_info_div">
            <table>
                <tr>
                    <th>${id}</th>
                    <th>${name}</th>
                    <th>${login}</th>
                    <th>${password}</th>
                    <th>${photo}</th>
                    <th class="show_users_info_role">${role}</th>
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
                                        ${admin}
                                </div>
                            </c:if>
                            <c:if test="${user.userRole == 'CLIENT'}">
                                <button class="show_users_info_btn" type="submit"
                                        name="userRoleClient" value="${user.id}" title="${click_to_change}">
                                        ${client}
                                </button>
                            </c:if>
                            <c:if test="${user.userRole == 'COURIER'}">
                                <button class="show_users_info_btn" type="submit"
                                        name="userRoleCourier" value="${user.id}" title="${click_to_change}">
                                        ${courier}
                                </button>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div class="show_users_btn_div">
            <div class="menu_count">
                <button class="show_users_btn_left" type="submit" name="left_page" value="${sessionScope.pageNumber}"></button>
            </div>
            <div class="number_page">
                ${sessionScope.pageNumber}
            </div>
            <div class="menu_count">
                <button class="show_users_btn_right" type="submit" name="right_page" value="${sessionScope.pageNumber}"></button>
            </div>
        </div>
    </div>
</form>
<%@include file="jspf/footer.jsp" %>
</body>
</html>
