<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Список тарифов по счетчику</title>
    <link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen"/>
    <link href="/bootstrap/js/bootstrap.min.js" rel="stylesheet" media="screen"/>
    <link href="/css/jquery.dataTables.min.css" rel="stylesheet" media="screen"/>
    <link href="/css/jquery.dataTables.min.js" rel="stylesheet" media="screen"/>
    <link href="/css/common-template.css" rel="stylesheet" media="screen"/>
    <link href="/jquery/jquery.min.js" rel="stylesheet" media="screen"/>
    <script src="/jquery/jquery.min.js" type="text/javascript" ></script>
    <script src="/css/jquery.dataTables.min.js" type="text/javascript" ></script>
    <script src="/bootstrap/js/bootstrap.min.js" type="text/javascript" ></script>
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
<script>
    $(document).ready(function () {
        $('#myTable').dataTable();
    });
</script>
<h2>${counter_name}</h2>
<form:form method="POST" action="deleteDataInfo">
    <input type="submit" value="Удалить"/>
        <table id="myTable" class="display table table-striped" width="100%" border="1" align="center">
            <thead>
            <tr>
                <td><b>Выбрать</b></td>
                <td><b>Показание</b></td>
                <td><b>Дата</b></td>
                <td><b>Оплата</b></td>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="listVal" items="${pokazaniaList}">
                <tr>
                    <td><input type="checkbox" name="selectedItems" value="${listVal.id}"/></td>
                    <td>${listVal.data}</td>
                    <td>${listVal.date}</td>
                    <td>${listVal.isPaid}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <input type="submit" value="Удалить"/>
</form:form>
<br>
<a href="delete">Вернуться к выбору счетчика</a>
<br><br>
<a href="./">Главная страница</a>
</body>
</html>