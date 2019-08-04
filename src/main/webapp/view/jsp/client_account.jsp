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
    <fmt:message key="locale.client_account.title" var="title"/>
    <fmt:message key="locale.client_account.my_orders" var="my_orders"/>
</fmt:bundle>
<html>
<head>
    <title>${title}</title>
    <link rel="stylesheet" href="../../css/style.css" type="text/css">
</head>
<body class="bg">
    <%@include file="header.jsp"%>
    <form method="post" action="${pageContext.request.contextPath}/restaurant">
        <input type="hidden" name="command" value="show_order"/>
        <div>
            <%@include file="account_information.jsp"%>
        </div>
        <div>
            <button class="account_btn_show_order" type="submit" name="show_order">${my_orders}</button>
        </div>
    </form>

    <%@include file="footer.jsp"%>
</body>
</html>
