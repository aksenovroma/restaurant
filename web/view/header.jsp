<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <style type="text/css">
        #header {
            position: fixed; /* Фиксированное положение */
            left: 0; top: 0; /* Левый нижний угол */
            background: #73b592; /* Цвет фона */
            color: #fff; /* Цвет текста */
            width: 100%; /* Ширина слоя */
            height: 80px;
        }
        #header_title {
            position: fixed;
            margin-left: 30px;
            margin-bottom: 10px;
            color: #fff;
            left: 0;
        }

        #welcome_user {
            position: fixed;
            margin-bottom: 30px;
            margin-right: 15px;
            color: #fff;
            right: 0;
        }

        #command {
            position: fixed;
            margin-top: 50px;
            margin-right: 15px;
            color: #fff;
            right: 0;
        }
    </style>
</head>
<body>
<div id="header">
    <table>
        <tr>
            <td id="header_title">
                <h1>Restaurant</h1>
            </td>
            <td id="header_user">
                <input type="hidden" name="command" value="sign_out"/>

                <div id="welcome_user">
                    <h2>Hello,
                        <c:if test="${sessionScope.username != null}">
                        <a href="${pageContext.request.contextPath}/view/account.jsp" style="color: white">
                            <c:out value="${sessionScope.username}"/>
                        </a>
                        </c:if>
                        <c:if test="${sessionScope.username == null}">
                            Guest
                        </c:if>
                    </h2>
                </div>

                <div id="command">
                    <c:if test="${sessionScope.username == null}">
                        <a href="${pageContext.request.contextPath}/view/sign_in.jsp" style="color: beige">Sign In</a> |
                        <a href="${pageContext.request.contextPath}/view/sign_up.jsp" style="color: beige">Sign Up</a>
                    </c:if>
                    <c:if test="${sessionScope.username != null}">
                        <a href="${pageContext.request.contextPath}/restaurant?command=sign_out" style="color: white">Sign Out</a>
                    </c:if>
                </div>
            </td>
        </tr>
    </table>
</div>
</body>
</html>


