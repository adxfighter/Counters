<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*" %>
<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Counters</title>
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

<table width="100%">
    <tr>
        <td>
            <%
                Calendar calendar = new GregorianCalendar();
                String am_pm;
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH) + 1;
                int year = calendar.get(Calendar.YEAR);
                int hour = calendar.get(Calendar.HOUR);
                int minute = calendar.get(Calendar.MINUTE);
                int second = calendar.get(Calendar.SECOND);
                if(calendar.get(Calendar.AM_PM) == 0)
                    am_pm = "AM";
                else
                    am_pm = "PM";
                String date = day+"."+month+"."+year;
                String time = hour+":"+ minute +":"+ second +" "+ am_pm;
                out.println(date + " " + time + "\n");
            %>
        </td>
    </tr>

    <tr>
        <td height="100">
            <h3>${address}</h3>
        </td>
    </tr>
    <tr>
        <td height="50"><a href="data">Новые показания</a></td>
    </tr>
    <tr>
        <td height="50"><a href="print">Страница печати</a></td>
    </tr>
    <tr>
        <td height="50"><a href="printAndPrices">Страница печати с ценами</a></td>
    </tr>
    <tr>
        <td height="50"><a href="delete">Удаление показаний</a></td>
    </tr>
    <tr>
        <td height="50"><a href="update">Обновление информации об оплате</a></td>
    </tr>
    <tr>
        <td height="50"><a href="used">Список потребления по счетчику</a></td>
    </tr>
    <tr>
        <td height="50"><a href="sum">Сумма потребления по месяцам</a></td>
    </tr>
    <tr>
        <td height="50"><a href="counter">Добавить счетчик</a></td>
    </tr>
    <tr>
        <td height="50"><a href="priceIndex">Тарифы</a></td>
    </tr>
</table>

<script src="/jquery/jquery.min.js"></script>
<script src="/bootstrap/js/bootstrap.min.js"></script>


</body>
</html>