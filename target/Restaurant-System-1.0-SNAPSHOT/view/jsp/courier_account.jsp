<%--
  Created by IntelliJ IDEA.
  User: aksenov
  Date: 8/1/19
  Time: 11:48 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<fmt:bundle basename="localization">
    <fmt:message key="locale.courier_account.title" var="title"/>
    <fmt:message key="locale.courier_account.orders" var="orders"/>
</fmt:bundle>
<html>
<head>
    <title>${title}</title>
</head>
<body class="bg">
<%@include file="jspf/header.jsp" %>
<form method="post" action="${pageContext.request.contextPath}/restaurant">
    <input type="hidden" name="command" value="show_order"/>
    <div>
        <%@include file="account_information.jsp" %>
    </div>
    <div>
        <button class="account_btn_show_order" type="submit" name="show_order">${orders}</button>
    </div>
</form>

<%@include file="jspf/footer.jsp" %>
</body>
</html>
