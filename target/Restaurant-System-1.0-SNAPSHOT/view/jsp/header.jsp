<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<fmt:bundle basename="localization">
    <fmt:message key="locale.header.restaurant" var="restaurant"/>
    <fmt:message key="locale.header.hello" var="hello"/>
    <fmt:message key="locale.header.guest" var="guest"/>
    <fmt:message key="locale.header.signIn" var="signIn"/>
    <fmt:message key="locale.header.signUp" var="signUp"/>
    <fmt:message key="locale.header.signOut" var="signOut"/>
</fmt:bundle>

<html>
<head>
    <link rel="stylesheet" href="../../css/style.css" type="text/css">
</head>
<body>
<div class="header">
    <table>
        <tr>
            <td class="header_title">
                <h1>${restaurant}</h1>
            </td>
            <td class="header_user">
                <input type="hidden" name="command" value="sign_out"/>

                <div class="header_welcome_user">
                    <h2>${hello},
                        <c:if test="${sessionScope.username != null}">
                        <a href="${pageContext.request.contextPath}/view/jsp/account.jsp" class="header_name">
                            <c:out value="${sessionScope.username}"/>
                        </a>
                        </c:if>
                        <c:if test="${sessionScope.username == null}">
                            ${guest}
                        </c:if>
                    </h2>
                </div>

                <div class="header_command">
                    <c:if test="${sessionScope.username == null}">
                        <a href="${pageContext.request.contextPath}/view/jsp/sign_in.jsp" class="header_sign_in">${signIn}</a> |
                        <a href="${pageContext.request.contextPath}/view/jsp/sign_up.jsp" class="header_sign_up">${signUp}</a>
                    </c:if>
                    <c:if test="${sessionScope.username != null}">
                        <a href="${pageContext.request.contextPath}/restaurant?command=sign_out" class="header_sign_out">${signOut}</a>
                    </c:if>
                </div>
            </td>
        </tr>
    </table>
</div>
</body>
</html>


