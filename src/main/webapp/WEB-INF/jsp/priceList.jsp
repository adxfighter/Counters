
<%@ page import="java.util.List" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Выбор счетчика для вывода списка тарифов</title>
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
<h2>Выбор счетчиика для вывода списка тарифов</h2>
<form:form method="POST" action="deletePrice">
    <table>
        <tr>
            <td><form:label path="counterName">Выбирете счетчик</form:label></td>
            <td><select name="selectCounter" size="1">
                <c:forEach var="listVal" items="${listCountersNames}">

                    <option>${listVal} </option>
                </c:forEach>
            </select></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Выбрать"/>
            </td>
            <td><a href="./">Главная страница</a></td>
        </tr>
    </table>
</form:form>
</body>
</html>
