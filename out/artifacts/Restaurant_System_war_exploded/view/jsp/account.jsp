<%--
  Created by IntelliJ IDEA.
  User: aksenov
  Date: 5/21/19
  Time: 1:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<fmt:bundle basename="localization">
    <fmt:message key="locale.account.title" var="title"/>
</fmt:bundle>

<html>
<head>
    <title>${title}</title>
    <script src="../../js/redirectPage.js"></script>
</head>
<body>
    <c:if test="${sessionScope.role == 'client'}">
        <script>
            redirect("${pageContext.request.contextPath}/view/jsp/client_account.jsp")
        </script>
    </c:if>
    <c:if test="${sessionScope.role == 'admin'}">
        <script>
            redirect("${pageContext.request.contextPath}/view/jsp/admin_account.jsp")
        </script>
    </c:if>
    <c:if test="${sessionScope.role == 'courier'}">
        <script>
            redirect("${pageContext.request.contextPath}/view/jsp/courier_account.jsp")
        </script>
    </c:if>
</body>
</html>
