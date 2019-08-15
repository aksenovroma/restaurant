<%--
  Created by IntelliJ IDEA.
  User: aksenov
  Date: 5/7/19
  Time: 11:47 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<fmt:bundle basename="localization">
    <fmt:message key="locale.sign_in.login" var="login"/>
    <fmt:message key="locale.sign_in.password" var="password"/>
    <fmt:message key="locale.sign_in.title" var="title"/>
    <fmt:message key="locale.sign_in.sign_in_btn" var="sign_in_btn"/>
    <fmt:message key="locale.sign_in.not_reg" var="not_reg"/>
    <fmt:message key="locale.sign_in.create" var="create"/>
</fmt:bundle>
<html>
<head>
    <title>${title}</title>
    <link rel="stylesheet" href="../../css/style.css" type="text/css">
</head>
<body class="bg">

<%@include file="jspf/header.jsp" %>

<div class="sign_in">
    <form class="login-form" method="post" action="${pageContext.request.contextPath}/restaurant">
        <input type="hidden" name="command" value="sign_in"/>
        <div class="login-page">
            <div class="form">
                <input type="text" required placeholder="${login}" name="login"/>
                <input type="password" required placeholder="${password}" name="password"/>
                <input type="submit" value="${sign_in_btn}">
                <p class="message">${not_reg} <a href="sign_up.jsp">${create}</a></p>
            </div>
        </div>
    </form>
</div>

<%@include file="jspf/footer.jsp" %>

</body>
</html>
