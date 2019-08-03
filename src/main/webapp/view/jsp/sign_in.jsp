<%--
  Created by IntelliJ IDEA.
  User: aksenov
  Date: 5/7/19
  Time: 11:47 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign In</title>
    <link rel="stylesheet" href="../../css/style.css" type="text/css">
</head>
<body class="bg">

<%@include file="header.jsp"%>

<div class="sign_in">
    <form class="login-form" method="post" action="${pageContext.request.contextPath}/restaurant">
        <input type="hidden" name="command" value="sign_in"/>
        <div class="login-page">
            <div class="form">
                    <input type="text" required placeholder="login" value="login"/>
                    <input type="password" required placeholder="password" value="password"/>
                    <input type="submit" value="Sing In">
                    <p class="message">Not registered? <a href="sign_up.jsp">Create an account</a></p>
            </div>
        </div>
    </form>
</div>

<%@include file="footer.jsp"%>

</body>
</html>
