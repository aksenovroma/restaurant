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
<body>

<%@include file="header.jsp"%>

<div class="sign_in">
    <h1 align="center">Login</h1><br>
    <form method="post" action="${pageContext.request.contextPath}/restaurant">
        <input type="hidden" name="command" value="sign_in"/>
        <table cellpadding="5" cellspacing="5" align="center">
            <tr>
                <td>
                    <label>
                        <input type="text" required placeholder="login" name="login">
                    </label>
                </td>
            </tr>
            <tr>
                <td>
                    <label>
                        <input type="password" required placeholder="password" name="password">
                    </label>
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
