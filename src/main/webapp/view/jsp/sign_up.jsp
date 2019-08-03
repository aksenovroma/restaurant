<%--
  Created by IntelliJ IDEA.
  User: aksenov
  Date: 5/7/19
  Time: 11:47 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<html>
<head>
    <title>Sign Up</title>
    <link rel="stylesheet" href="../../css/style.css" type="text/css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="../../js/emailValidation.js"></script>
</head>
<body class="bg">

<%@include file="header.jsp"%>

<div class="sign_up">
    <form id="form_id" method="post" action="${pageContext.request.contextPath}/restaurant" onsubmit="javascript:return validate('form_id','login');">
        <input type="hidden" name="command" value="sign_up"/>
        <div class="login-page">
            <div class="form">
                <form class="register-form">
                    <input type="text" required placeholder="name" name="name"/>
                    <input type="text" required placeholder="e-mail" name="login"/>
                    <input type="password" required placeholder="password" name="password"/>
                    <input type="submit" value="Sing Up">
                    <p class="message">Already registered? <a href="sign_in.jsp">Sign In</a></p>
                </form>
            </div>
        </div>
    </form>
</div>

<%@include file="footer.jsp"%>

</body>
</html>
