<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <link rel="stylesheet" href="../../css/style.css" type="text/css">
</head>
<body>
<div class="header">
    <table>
        <tr>
            <td class="header_title">
                <h1>Restaurant</h1>
            </td>
            <td class="header_user">
                <input type="hidden" name="command" value="sign_out"/>

                <div class="header_welcome_user">
                    <h2>Hello,
                        <c:if test="${sessionScope.username != null}">
                        <a href="${pageContext.request.contextPath}/view/jsp/account.jsp" class="header_name">
                            <c:out value="${sessionScope.username}"/>
                        </a>
                        </c:if>
                        <c:if test="${sessionScope.username == null}">
                            Guest
                        </c:if>
                    </h2>
                </div>

                <div class="header_command">
                    <c:if test="${sessionScope.username == null}">
                        <a href="${pageContext.request.contextPath}/view/jsp/sign_in.jsp" class="header_sign_in">Sign In</a> |
                        <a href="${pageContext.request.contextPath}/view/jsp/sign_up.jsp" class="header_sign_up">Sign Up</a>
                    </c:if>
                    <c:if test="${sessionScope.username != null}">
                        <a href="${pageContext.request.contextPath}/restaurant?command=sign_out" class="header_sign_out">Sign Out</a>
                    </c:if>
                </div>
            </td>
        </tr>
    </table>
</div>
</body>
</html>


