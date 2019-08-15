<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<html>
<head>
    <title>Error</title>
    <link rel="stylesheet" href="../../css/style.css" type="text/css">
</head>
<body class="bg">

<%@include file="jspf/header.jsp" %>

<div class="sign_in">
    <div class="login-page">
        <div class="form">
            <h2>Exception occurred while processing the request</h2>
            <p>Type: ${pageContext.getException().getClass().toString()}</p>
            <p>Message: ${pageContext.getException().getMessage()}</p>
        </div>
    </div>
</div>

<%@include file="jspf/footer.jsp" %>

</body>
</html>
