<%--
  Created by IntelliJ IDEA.
  User: aksenov
  Date: 8/1/19
  Time: 12:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Account Information</title>
    <link rel="stylesheet" href="../../css/style.css" type="text/css">
</head>
<body>
    <div class="account_info">
        <div class="account_info_title">
            Account Information
        </div>
        <div>
            <img class="account_user_image" src="${sessionScope.userphoto}" alt="user image">
        </div>
        <div class="account_info_log_pas">
            <div>
                Name : <c:out value="${sessionScope.username}"/>
            </div>
            <div>
                E-mail : <c:out value="${sessionScope.login}"/>
            </div>
            <div>
                Password : <c:out value="${sessionScope.password}"/>
            </div>
            <div>
                Role : <c:out value="${sessionScope.role}"/>
            </div>
        </div>
    </div>
</body>
</html>
