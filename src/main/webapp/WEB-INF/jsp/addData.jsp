<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 03.08.14
  Time: 22:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.springframework.beans.factory.annotation.Autowired" %>
<%@ page import="com.counters.model.BO.*" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page pageEncoding="CP1251" %>
<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Новые данные введены<</title>
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
            <a class="navbar-brand" th:href="/">Counters</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li><a href="/">Главная</a></li>
                <li class="active"><a href="data">Новые показания</a></li>
            </ul>
        </div>
    </div>
</nav>
<h2>Новые данные введены</h2>
<table>
    <tr>
        <td>счетчик:</td>
        <td>${counter_name}</td>
    </tr>
    <tr>
        <td>данные:</td>
        <td>${data}</td>
    </tr>
    <tr>
        <td>дата:</td>
        <td>${date}</td>
    </tr>

</table>

<br>
<a href="data">Новые показания</a>
<br><br><br>
<a href="./">Главная страница</a>

<script src="/jquery/jquery.min.js"></script>
<script src="/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
