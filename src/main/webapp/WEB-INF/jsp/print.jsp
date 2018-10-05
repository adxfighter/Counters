<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.io.*,java.util.*" %>
<html>
<head>
    <title>Показания</title></head>
<body>
<h2>${address}</h2>
<c:if test="${!isCorrect}">
    <h2>Даты не совпадают!</h2>
</c:if>
<table width="100%" border="1" align="center">
    <tr>
        <td><b>Счетчик</b></td>
        <td><b>Объем</b></td>
        <c:if test="${!isCorrect}">
            <td><b>Дата</b></td>

        </c:if>

    </tr>
    <c:forEach var="listVal" items="${pokazania}">
        <tr>
            <td>${listVal.counter.counterName}</td>
            <td>${listVal.data}</td>
            <c:if test="${!isCorrect}">
                <td>${listVal.date}</td>

            </c:if>
        </tr>
    </c:forEach>
</table>
<br><br><br>
<a href="./">Главная страница</a>
</body>
</html>