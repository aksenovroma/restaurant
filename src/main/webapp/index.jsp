<%--
  Created by IntelliJ IDEA.
  User: aksenov
  Date: 7/24/19
  Time: 2:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
  <head>
    <title>Restaurant</title>
    <link rel="stylesheet" href="css/style.css" type="text/css">
  </head>
  <body class="bg">
  <%@include file="view/jsp/header.jsp"%>

  <form method="post" action="${pageContext.request.contextPath}/restaurant">
    <input type="hidden" name="command" value="show_menu"/>
    <input class="btn_menu" type="submit" value="Menu"/>
  </form>

  <%@include file="view/jsp/footer.jsp"%>
  </body>
</html>
