<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>График потребления по месяцам</title>
    <link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
    <script src="/jquery/jquery.min.js"></script>
    <script src="/webjars/chart.js/2.9.4/dist/Chart.min.js"></script>
    <style>
        .chart-container {
            width: 90%;
            margin: 20px auto;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="/">Counters</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li><a href="/">Главная</a></li>
                <li><a href="data">Новые показания</a></li>
            </ul>
        </div>
    </div>
</nav>
<div class="chart-container">
    <h2>Сумма потребления по месяцам</h2>
    <canvas id="consumptionChart"></canvas>
    <div id="errorMsg" style="color: red; margin-top: 20px;"></div>
</div>
<script>
    var chartData = null;
    var colors = {};
    var counterNames = [];

    function getColor(index) {
        var presetColors = ['#FF6384', '#36A2EB', '#FFCE56', '#4BC0C0', '#9966FF', '#FF9F40'];
        return presetColors[index % presetColors.length];
    }

    $.ajax({
        url: '/chartData',
        dataType: 'json',
        success: function(data) {
            chartData = data;
            counterNames = data.counterNames || [];
            var ctx = document.getElementById('consumptionChart').getContext('2d');

            var labels = data.labels || [];
            var datasets = [];

            for (var i = 0; i < counterNames.length; i++) {
                var name = counterNames[i];
                var sums = data.datasets[name] || [];
                datasets.push({
                    label: name,
                    data: sums,
                    borderColor: getColor(i),
                    backgroundColor: 'transparent',
                    tension: 0.1
                });
            }

            var totalSums = data.totalSums || [];
            datasets.push({
                label: 'Общая сумма',
                data: totalSums,
                borderColor: '#4BC0C0',
                backgroundColor: 'rgba(75, 192, 192, 0.2)',
                tension: 0.1,
                borderWidth: 3
            });

            new Chart(ctx, {
                type: 'line',
                data: {
                    labels: labels,
                    datasets: datasets
                },
                options: {
                    responsive: true,
                    scales: {
                        x: {
                            title: {
                                display: true,
                                text: 'Месяц'
                            }
                        },
                        y: {
                            title: {
                                display: true,
                                text: 'Сумма (руб)'
                            },
                            beginAtZero: true
                        }
                    }
                }
            });
        },
        error: function(xhr, status, error) {
            $('#errorMsg').html('Ошибка загрузки данных: ' + error + '<br>Статус: ' + status);
        }
    });
</script>
<div class="chart-container">
    <br>
    <a href="./">Главная страница</a>
</div>
</body>
</html>