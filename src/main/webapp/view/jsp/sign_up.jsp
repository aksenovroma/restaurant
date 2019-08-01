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
<body>

<%@include file="header.jsp"%>

<div class="sign_up">
    <form id="form_id" method="post" action="${pageContext.request.contextPath}/restaurant" onsubmit="javascript:return validate('form_id','login');">
        <input type="hidden" name="command" value="sign_up"/>
        <h1 class="sign_up_title">Registration</h1><br>
        <table cellspacing="6" cellpadding="6" align="center">
            <tr>
                <td>
                    <label>
                        <input type="text" required placeholder="name" name="name">
                    </label>
                </td>
            </tr>
            <tr>
                <td>
                    <label>
                        <input type="text" required placeholder="e-main" name="login">
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
                    <input type="submit" value="Sign Up"/>
                </td>
            </tr>

        </table>
    </form>
</div>

<%@include file="footer.jsp"%>

</body>
</html>
