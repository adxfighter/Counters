<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Введите число месяцев для расчета средних показаний</title>
    <link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen"/>
    <link href="/bootstrap/js/bootstrap.min.js" rel="stylesheet" media="screen"/>
    <link href="/css/jquery.dataTables.min.css" rel="stylesheet" media="screen"/>
    <link href="/css/jquery.dataTables.min.js" rel="stylesheet" media="screen"/>
    <link href="/css/common-template.css" rel="stylesheet" media="screen"/>
    <link href="/jquery/jquery.min.js" rel="stylesheet" media="screen"/>
    <script src="/jquery/jquery.min.js" type="text/javascript"></script>
    <script src="/css/jquery.dataTables.min.js" type="text/javascript"></script>
    <script src="/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
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

<div class="container common-template">
<form:form class="form-horizontal pull-left"
           accept-charset="UTF-8" method="POST" action="insertAvgDataInfo">

        <div class="form-group">
            <label for="interval" class="col-sm-2 control-label pull-left">Срок</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="interval" name="interval" placeholder="Введите число показаний"/>
            </div>
        </div>
        <button type="submit" class="btn btn-default ">Ввести показания</button>
</form:form>
</div>
<br><br>
<a href="./">Главная страница</a>
</body>
</html>