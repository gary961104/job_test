<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>首页</title>
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/chartjs-adapter-date-fns/dist/chartjs-adapter-date-fns.bundle.min.js"></script>
    </head>
    <body>

        <c:forEach var="forex" items="${responseVO.rtnData}">
            <canvas id="line-chart-${forex.currency}"></canvas>
        </c:forEach>

        <script>
            // 迴圈調用 generateChart 函數
            <c:forEach var="forex" items="${responseVO.rtnData}">
                generateChart('${forex.currency}', '${forex.forexDetailListJson}');
            </c:forEach>
            // 定義 generateChart 函數
            function generateChart(currency, forexDetailListJson) {
                const forexDetailList = JSON.parse(forexDetailListJson);
                const buyData = [];
                const sellData = [];
                const label = [];

                forexDetailList.forEach(function(detail) {
                    buyData.push(parseFloat(detail.buy));
                    sellData.push(parseFloat(detail.sell));
                    label.push(detail.date);
                });

                const dataLine = {
                    labels: label,
                    datasets: [{
                        label: "買入",
                        data: buyData,
                        fill: false,
                        borderColor: 'rgb(75, 192, 192)',
                    },
                        {
                            label: "賣出",
                            data: sellData,
                            fill: false,
                            borderColor: 'rgb(192, 75, 192)',
                        }],
                    options: {
                        plugins: {
                            title: {
                                display: true,
                                text: currency
                            }
                        },
                        scales: {
                            x: {
                                type: 'time',
                                time: {
                                    unit: 'month',
                                    displayFormats: {
                                        month: 'yyyy-MM'
                                    }
                                }
                            }
                        }
                    }
                };


                new Chart(document.getElementById("line-chart-" + currency), {
                    type: "line",
                    data: dataLine,
                    options: dataLine.options
                });
            }

        </script>
    </body>
</html>
