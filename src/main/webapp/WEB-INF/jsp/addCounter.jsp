<%@ page import="org.springframework.beans.factory.annotation.Autowired" %>
<%@ page import="com.counters.model.BO.*" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<html>
<head>
    <title>Счетчик добавлен</title>
</head>
<body>
<% request.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;charset=UTF-8");
    response.setCharacterEncoding("UTF-8");%>
<h2>Счетчик добавлен</h2>
<table>
    <tr>
        <td>Название нового счетчика:</td>
        <td>${counter_name}</td>
    </tr>

</table>
<br>
<a href="./">Главная страница</a>
</body>
</html>