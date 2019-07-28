<%--
  Created by IntelliJ IDEA.
  User: aksenov
  Date: 7/24/19
  Time: 2:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
  <head>
    <title>Restaurant</title>
    <style type="text/css">
      #btn_menu {
        margin-top: 170px;
        color: #fff; /* цвет текста */
        text-decoration: none; /* убирать подчёркивание у ссылок */
        user-select: none; /* убирать выделение текста */
        background: rgb(133, 181, 165); /* фон кнопки */
        padding: .7em 1.5em;
        font-size: 40px;
        height: 100px;
        width: 300px;
        position: relative;
        left: 50%;
        transform: translate(-50%, 0);
      }
    </style>
  </head>
  <body>
  <%@include file="view/header.jsp"%>

  <form method="post" action="${pageContext.request.contextPath}/restaurant">
    <input type="hidden" name="command" value="show_menu"/>
    <input id="btn_menu" type="submit" value="Menu"/>
  </form>

  <%@include file="view/footer.jsp"%>
  </body>
</html>
