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
    <style type="text/css">
        #register {
            margin-top: 100px;
            position: center;
        }
    </style>
</head>
<body>

<%@include file="header.jsp"%>

<div id="register">
    <form method="post" action="${pageContext.request.contextPath}/restaurant">
        <input type="hidden" name="command" value="sign_up"/>
        <h1 align="center">Registration</h1><br>
        <table cellspacing="5" cellpadding="5" align="center">
            <tr>
                <td>
                    <input type="text" required placeholder="name" name="name">
                </td>
            </tr>
            <tr>
                <td>
                    <input type="text" required placeholder="login" name="login">
                </td>
            </tr>
            <tr>
                <td>
                    <input type="password" required placeholder="password" name="password">
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="reset" value="Clear"/>
                    <input type="submit" value="Sign Up"/>
                </td>
            </tr>

        </table>
    </form>
</div>

<%@include file="footer.jsp"%>

</body>
</html>
