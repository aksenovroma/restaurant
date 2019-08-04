<%--
  Created by IntelliJ IDEA.
  User: aksenov
  Date: 5/7/19
  Time: 11:47 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<fmt:bundle basename="localization">
    <fmt:message key="locale.sign_up.title" var="title"/>
    <fmt:message key="locale.sign_up.name" var="name"/>
    <fmt:message key="locale.sign_up.already_reg" var="already_reg"/>
    <fmt:message key="locale.sign_up.sign_up_btn" var="sign_up_btn"/>
    <fmt:message key="locale.sign_in.login" var="login"/>
    <fmt:message key="locale.sign_in.password" var="password"/>
    <fmt:message key="locale.sign_in.sign_in_btn" var="sign_in_btn"/>
</fmt:bundle>
<html>
<head>
    <title>${title}</title>
    <link rel="stylesheet" href="../../css/style.css" type="text/css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="../../js/emailValidation.js"></script>
</head>
<body class="bg">

<%@include file="header.jsp"%>

<div class="sign_up">
    <form id="form_id" method="post" action="${pageContext.request.contextPath}/restaurant" onsubmit="javascript:return validate('form_id','login');">
        <input type="hidden" name="command" value="sign_up"/>
        <div class="login-page">
            <div class="form">
                <form class="register-form">
                    <input type="text" required placeholder="${name}" name="name"/>
                    <input type="text" required placeholder="${login}" name="login"/>
                    <input type="password" required placeholder="${password}" name="password"/>
                    <input type="submit" value="${sign_up_btn}">
                    <p class="message">${already_reg} <a href="sign_in.jsp">${sign_in_btn}</a></p>
                </form>
            </div>
        </div>
    </form>
</div>

<%@include file="footer.jsp"%>

</body>
</html>
