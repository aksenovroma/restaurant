<%--
  Created by IntelliJ IDEA.
  User: aksenov
  Date: 8/1/19
  Time: 12:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<fmt:bundle basename="localization">
    <fmt:message key="locale.account_information.title" var="title"/>
    <fmt:message key="locale.account_information.name" var="name"/>
    <fmt:message key="locale.account_information.email" var="email"/>
    <fmt:message key="locale.account_information.password" var="password"/>
    <fmt:message key="locale.account_information.role" var="role"/>
</fmt:bundle>
<html>
<head>
    <title>${title}</title>
    <link rel="stylesheet" href="../../css/style.css" type="text/css">
</head>
<body>
    <div class="account_info">
        <div class="account_info_title">
            ${title}
        </div>
        <div>
            <img class="account_user_image" src="${sessionScope.userphoto}" alt="user image">
        </div>
        <div class="account_info_log_pas">
            <div>
                ${name} : <c:out value="${sessionScope.username}"/>
            </div>
            <div>
                ${email} : <c:out value="${sessionScope.login}"/>
            </div>
            <div>
                ${password} : <c:out value="${sessionScope.password}"/>
            </div>
            <div>
                ${role} : <c:out value="${sessionScope.role}"/>
            </div>
        </div>
    </div>
</body>
</html>
