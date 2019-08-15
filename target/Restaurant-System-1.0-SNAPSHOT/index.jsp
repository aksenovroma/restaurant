<%--
  Created by IntelliJ IDEA.
  User: aksenov
  Date: 7/24/19
  Time: 2:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<fmt:bundle basename="localization">
  <fmt:message key="locale.index.title" var="title"/>
  <fmt:message key="locale.index.menu" var="menu"/>
</fmt:bundle>
<html>
  <head>
    <title>${title}</title>
    <link rel="stylesheet" href="css/style.css" type="text/css">
  </head>
  <body class="bg">
  <%@include file="view/jsp/jspf/header.jsp"%>

  <form method="post" action="${pageContext.request.contextPath}/restaurant">
    <input type="hidden" name="command" value="show_menu"/>
    <input class="btn_menu" type="submit" value="${menu}"/>
  </form>

  <%@include file="view/jsp/jspf/footer.jsp"%>
  </body>
</html>
