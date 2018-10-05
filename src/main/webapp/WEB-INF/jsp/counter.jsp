<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<HTML>
<BODY>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Добавление счетчика</title>
    <link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen"/>
    <link href="/css/common-template.css" rel="stylesheet" media="screen"/>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/">Counters</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="/">Главная</a></li>
                <li><a href="data">Новые показания</a></li>
            </ul>
        </div>
    </div>
</nav>
<h2>Добавление счетчика</h2>
<% request.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;charset=UTF-8");
    response.setCharacterEncoding("UTF-8");%>
<form:form method="POST" action="addCounter">

    <table width="100%" border="1" align="center">
        <tr><form:label path="counterName"><h3>Список счетчиков:</h3></form:label></tr>
        <c:forEach var="listVal" items="${listCountersNames}">
            <tr>
                <td>${listVal}</td>
            </tr>
        </c:forEach>
    </table>
    <table width="100%" align="center">
        <tr>
            <form:label path="counterName"><h3>Новый счетчик</h3></form:label>
        </tr>
        <tr>
            <form:input path="counterName"/>
        </tr>
        <br>
        <tr>
            <td colspan="2">
                <input type="submit" value="Добавить счетчик"/>
            </td>
        </tr>
    </table>
</form:form>
<br><br><br>
<a href="./">Главная страница</a>
</BODY>
</HTML>