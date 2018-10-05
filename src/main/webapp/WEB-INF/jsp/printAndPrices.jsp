<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.io.*,java.util.*" %>
<html>
<head>
    <title>Показания и цены</title></head>
<body>
<h2>${address}</h2>
<c:if test="${!isCorrect}">
    <h2>Даты не совпадают!</h2>
</c:if>
<table width="100%" border="1" align="center">
    <tr>
        <td><b>Счетчик</b></td>
        <td><b>Показание</b></td>
        <td><b>Тариф</b></td>
    </tr>
    <c:forEach var="listVal" items="${pokazaniaAndPrices}">
        <tr>
            <td>${listVal.period}</td>
            <td>${listVal.data}</td>
            <td>${listVal.price}</td>
        </tr>
    </c:forEach>
</table>
<br><br><br>
<a href="./">Главная страница</a>
</body>
</html>