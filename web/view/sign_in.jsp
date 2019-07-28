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
    <style type="text/css">
        #login {
            margin-top: 100px;
            position: center;
        }
    </style>
</head>
<body>

<%@include file="header.jsp"%>

<div id="login">
    <h1 align="center">Login</h1><br>
    <form method="post" action="${pageContext.request.contextPath}/restaurant">
        <input type="hidden" name="command" value="sign_in"/>
        <table cellpadding="5" cellspacing="5" align="center">
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
                    <input type="submit" value="Sign In"/>
                </td>
            </tr>
        </table>
    </form>
</div>

<%@include file="footer.jsp"%>

</body>
</html>
