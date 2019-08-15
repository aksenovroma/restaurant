<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<fmt:bundle basename="localization">
    <fmt:message key="locale.error.title" var="title"/>
    <fmt:message key="locale.error.type" var="type"/>
    <fmt:message key="locale.error.message" var="message"/>
    <fmt:message key="locale.error.head" var="head"/>
</fmt:bundle>

<html>
<head>
    <title>${title}</title>
    <link rel="stylesheet" href="../../css/style.css" type="text/css">
</head>
<body class="bg">

<%@include file="jspf/header.jsp" %>

<div class="sign_in">
    <div class="login-page">
        <div class="form">
            <h2>${head}</h2>
            <c:if test="${pageContext.getException().getClass().toString() != null}">
                <p>${type}: ${pageContext.getException().getClass().toString()}</p>
            </c:if>
            <c:if test="${pageContext.getException().getMessage() != null}">
                <p>${message}: ${pageContext.getException().getMessage()}</p>
            </c:if>
        </div>
    </div>
</div>

<%@include file="jspf/footer.jsp" %>

</body>
</html>
