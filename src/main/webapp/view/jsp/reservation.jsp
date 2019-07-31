<%--
  Created by IntelliJ IDEA.
  User: aksenov
  Date: 7/31/19
  Time: 7:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Reservation</title>
    <link rel="stylesheet" href="../../css/style.css" type="text/css">
    <style type="text/css">
        #res_btn_checkout{
            margin-top: 8%;
            color: #fff;
            text-decoration: none;
            user-select: none;
            background: rgb(133, 181, 165);
            padding: .7em 1.5em;
            font-size: 40px;
            height: 100px;
            width: 300px;
            position: relative;
            left: 50%;
            transform: translate(-50%, 0);
        }
        #res_input_address{
            position: absolute;
            margin-top: 15px;
            left: 50%;
            transform: translate(-50%, 0);
        }
    </style>
</head>
<body>
    <%@include file="header.jsp"%>


    <form id="form_id" method="post" action="${pageContext.request.contextPath}/restaurant">
            <div>
                <div>
                    <button id="res_btn_checkout" type="submit" name="checkout">Сheckout</button>
                </div>
                <div>
                    <label>
                        <input id="res_input_address" type="text" required placeholder=" address" name="address">
                    </label>
                </div>
                <div>
                    <div>
                        Ваш заказ:
                    </div>
                    <div>
                        <c:forEach items="${sessionScope.orderDish}" var="dish">
                            <c:out value="${dish.key}"/> х
                            <c:out value="${dish.value}"/><br>
                        </c:forEach>
                    </div>
                </div>
            </div>
    </form>


    <%@include file="footer.jsp"%>
</body>
</html>
