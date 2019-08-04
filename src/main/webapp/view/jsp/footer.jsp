<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<fmt:bundle basename="localization">
    <fmt:message key="locale.footer.home" var="home"/>
</fmt:bundle>

<html>
    <head>
        <link rel="stylesheet" href="../../css/style.css" type="text/css">
    </head>
    <body>
        <div class="footer">
            <a href="${pageContext.request.contextPath}/index.jsp" class="footer_link">${home}</a> |
            <a href="${pageContext.request.contextPath}/restaurant?command=change_locale&localization=en_US"
               class="footer_link">EN</a> |
            <a href="${pageContext.request.contextPath}/restaurant?command=change_locale&localization=ru_RU"
               class="footer_link">RU</a>
        </div>
    </body>
</html>

