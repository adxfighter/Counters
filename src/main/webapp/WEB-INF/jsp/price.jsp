<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 03.08.14
  Time: 22:03
  To change this template use File | Settings | File Templates.
--%>
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
    <title>Новый тариф</title>
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
<h2>Новый тариф</h2>

<div class="container common-template">

    <form class="form-horizontal pull-left"
          accept-charset="UTF-8" method="POST" action="addPrice">

        <div class="form-group">
            <label for="selectCounter" class="col-sm-2 control-label">Cчетчик</label>
            <div class="col-sm-10">
                <select class="form-control" id="selectCounter" name="selectCounter">
                    <c:forEach var="listVal" items="${listCountersNames}">

                        <option>${listVal} </option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="form-group">
            <label for="price" class="col-sm-2 control-label">Новый тариф</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="price" name="price"
                       placeholder="Введите тариф"/>
            </div>
        </div>

        <script type='text/javascript' src='./images/jquery.js'></script>
        <link href='./images/calendar.css' rel='stylesheet' type='text/css'>
        <script type='text/javascript'>
            var getDatee = new Date();
            var monthe = getDatee.getMonth();
            var yeare = getDatee.getFullYear();
            var day = getDatee.getDate();

            function isEmpty(val) {
                return (val === undefined || val == null || val.length <= 0) ? true : false;
            }

            function prev() {
                monthe = monthe - 1;
                if (monthe < 0) {
                    yeare = yeare - 1;
                    monthe = 11;
                }
                dispCal(monthe, yeare);
                return false;
            }

            function next() {
                monthe = monthe + 1;
                if (monthe > 11) {
                    yeare = yeare + 1;
                    monthe = 0;
                }
                dispCal(monthe, yeare);
                return false;
            }

            function daysInMonth(monthe, yeare) {
                return 32 - new Date(yeare, monthe, 32).getDate();
            }

            function getElementPosition(arrName, arrItem) {
                for (var pos = 0; pos < arrName.length; pos++) {
                    if (arrName[pos] == arrItem) {
                        return pos;
                    }
                }
            }

            function setVal(getDat) {
                $('#sel').val(getDat);
                $('#calendar').hide();
            }

            function dispCal(mon, yea) {
                var ar = new Array('Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec');
                var chkEmpty = isEmpty(mon);
                var n, days, calendar, startDate, newYea, setvale, i;
                if (chkEmpty != true) {
                    mon = mon + 1;
                    n = ar[mon - 1];
                    n += " " + yea;
                    newYea = yea;
                    days = daysInMonth((mon - 1), yea);
                    startDate = new Date(ar[mon - 1] + " 1" + "," + parseInt(yea));
                } else {
                    mon = getElementPosition(ar, ar[getDatee.getMonth()]);
                    n = ar[getDatee.getMonth()];
                    n += " " + yeare;
                    newYea = yeare;
                    days = daysInMonth(mon, yeare);
                    startDate = new Date(ar[mon] + " 1" + "," + parseInt(yeare));
                }

                var startDay = startDate.getDay();
                var startDay1 = startDay;
                while (startDay > 0) {
                    calendar += "<td></td>";
                    startDay--;
                }
                i = 1;
                while (i <= days) {
                    if (startDay1 > 6) {
                        startDay1 = 0;
                        calendar += "</tr><tr>";
                    }
                    mon = monthe + 1;
                    setvale = i + "," + n;
                    if (i == day && newYea == yeare && mon == monthe) {
                        calendar += "<td class='thisday' onclick='setVal(\"" + i + "-" + mon + "-" + newYea + "\")'>" + i + "</td>";
                    } else {
                        calendar += "<td class='thismon' onclick='setVal(\"" + i + "-" + mon + "-" + newYea + "\")'>" + i + "</td>";
                    }
                    startDay1++;
                    i++;
                }
                calendar += "<td><a style='font-size: 9px; color: #efefef; font-family: arial; text-decoration: none;' href='http://www.hscripts.com'>&copy;h</a></td>";

                $('#calendar').css('display', 'block');
                $('#month').html(n);
                var test = "<tr class='weekdays'><td>Sun</td><td>Mon</td><td>Tue</td><td>Wed</td><td>Thu</td><td>Fri</td><td>Sat</td></tr>";
                test += calendar;
                $('#dispDays').html(test);
            }
        </script>
        <div class="form-group">
            <label for="sel" class="col-sm-2 control-label">Введите дату</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="date1" type='text' id='sel' onclick='dispCal()' size=10
                       readonly='readonly'/>
                <img src='./images/calendar.png' onclick='dispCal()' style='cursor: pointer; vertical-align: middle;'/>
            </div>
        </div>

        <div class="form-group">
            <table class='calendar' id='calendar' border=0 cellpadding=0 cellspacing=0>
                <tr class='monthdisp'>
                    <td class='navigate' align='left'><img src='./images/previous.png' onclick='return prev()'/>
                    </td>
                    <td id='month'></td>
                    <td class='navigate' align='right'><img src='./images/next.png' onclick='return next()'/></td>
                </tr>
                <tr>
                    <td colspan=3>
                        <table id='dispDays' border=0 cellpadding=4 cellspacing=4>
                        </table>
                    </td>
                </tr>
            </table>
        </div>

        <button type="submit" class="btn btn-default">Добавить тариф</button>
    </form>
</div>
</body>
</html>
