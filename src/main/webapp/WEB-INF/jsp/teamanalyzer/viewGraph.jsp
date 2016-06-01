<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<link rel="stylesheet" href="<c:url value="/css/confirm-message.css"/>"
      type="text/css" media="screen"/>

<h2 class="postheader">
    <spring:message code="pagehdr.teamanalyzer"/>
    <c:out value="${user.nick}"/>
</h2>

<div class="postcontent">


    <div class="col-xs-12">
        <c:if test="${userclassif.count > 0}">
            <label> <spring:message code="user.percent.stats"/>
            </label>
            <canvas id="top-chart" width="500" height="500"></canvas>
        </c:if>
    </div>

    <c:if test="${userclassif.count > 0}">
        <div class="col-centered col-xs-11 no-gutters">
            <c:forEach var="loop" begin="0" end="${userclassif.count-1}">
                <div class="col-xs-4">
                    <div class="col-padded">
                        <center>
                            <small><label id="classif-label-${loop}"
                                          class="text-sm text-muted"></label></small>
                        </center>
                        <div>
                            <canvas id="classif-chart-${loop}"></canvas>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </c:if>

</div>

<script type="text/javascript" src="<c:url value="/js/Chart.min.js"/>"></script>
<script>
    $(function () {
        $("[data-toggle='tooltip']").tooltip();
    });

    $('img.avatar').error(function () {
        $(this).attr('src', '<c:url value="/images/default_avatar.png"/>');
    });

    $(function () {
        $('.fa-chevron-up').click(function () {
            $(this).toggleClass('fa-chevron-up');
            $(this).toggleClass('fa-chevron-down');
        });
        $('.fa-chevron-down').click(function () {
            $(this).toggleClass('fa-chevron-down');
            $(this).toggleClass('fa-chevron-up');
        });

        $("[data-toggle='tooltip']").tooltip();
    });

    $(document).ready(function () {
        callback();
        topCallback();
        classifCallback();
        timelineCallback();
    });

    function callback() {
        $("#loading").show();
        var nat = $('#username').val();
        var data = {
            labels: ["AC",
                "CE",
                "MLE",
                "OLE",
                "PE",
                "RTE",
                "TLE",
                "WA"
            ],
            datasets: [
                {
                    label: "Submits",
                    fillColor: "#4c83c3",
                    data: [${user.acc}, ${user.ce}, ${user.mle},
                        ${user.ole}, ${user.pe}, ${user.rte},
                        ${user.tle}, ${user.wa}]
                }
            ]
        };
        var chart = new Chart($("#chart").get(0).getContext("2d")).Bar(data, {scaleShowVerticalLines: false,});
    }
    ;

    function topCallback() {
        var data = {
            labels: ${userclassif.labels},
            datasets: [
                {
                    fillColor: "rgba(0,150,0,0.8)",
                    strokeColor: "rgba(0,150,0,0.8)",
                    pointColor: "rgba(0,150,0,0.8)",
                    pointStrokeColor: "#fff",
                    pointHighlightFill: "#fff",
                    pointHighlightStroke: "rgba(0,150,0,0.8)",
                    data: ${userclassif.percentTop}
                },
                {
                    fillColor: "rgba(151,187,205,0.4)",
                    strokeColor: "rgba(151,187,205,0.4)",
                    pointColor: "rgba(151,187,205,0.4)",
                    pointStrokeColor: "#fff",
                    data: ${userclassif.percentTotal}
                }
            ]
        };
        var topChart = new Chart($("#top-chart").get(0).getContext("2d")).Radar(data, {
            pointDot: false,
            scaleOverride: false,
            scaleIntegersOnly: true,
            scaleSteps: 1,
            scaleStepWidth: 1,
            scaleStartValue: 0
        });
    }
    ;


    function classifCallback() {

        var labels = ${userclassif.labels};
        for (var i = 0; i < ${userclassif.count}; i++) {
            var data = {
                labels: ["1", "2", "3", "4", "5"],
                datasets: [
                    {
                        label: labels[i],
                        fillColor: "rgba(0,150,0,0.6)",
                        data: ${userclassif.classifications}[i]
                    }
                ]
            };
            $("#classif-label-" + i).html(labels[i]);
            var chart = new Chart($("#classif-chart-" + i).get(0).getContext("2d")).Bar(data, {
                scaleShowVerticalLines: false,
                animation: false,
                responsive: true
            });
        }
    }
    ;

    function timelineCallback() {

        var data = {
            labels: ${userclassif.dates},
            datasets: [
                {
                    label: 'AC',
                    fillColor: "rgba(0,150,0,0.4)",
                    strokeColor: "rgba(0,150,0,1)",
                    pointColor: "rgba(0,150,0,1)",
                    pointStrokeColor: "#fff",
                    pointHighlightFill: "#fff",
                    pointHighlightStroke: "rgba(0,150,0,1)",
                    data: ${userclassif.acStatus}
                },
                {
                    label: 'Error',
                    fillColor: "rgba(150,0,0,0.4)",
                    strokeColor: "rgba(150,0,0,1)",
                    pointColor: "rgba(150,0,0,1)",
                    pointStrokeColor: "#fff",
                    pointHighlightFill: "#fff",
                    pointHighlightStroke: "rgba(200,0,0,1)",
                    data: ${userclassif.errorStatus}
                }
            ]
        };

        Chart.types.Line.extend({
            name: "AltLine",
            initialize: function (data) {
                Chart.types.Line.prototype.initialize.apply(this, arguments);
                this.scale.draw = function () {
                    if (this.display && true) {
                        this.endPoint = this.height - 5;
                    }
                    Chart.Scale.prototype.draw.apply(this, arguments);
                };
            }
        });

        var chart = new Chart($("#timeline-chart").get(0).getContext("2d")).AltLine(data, {
            scaleShowVerticalLines: true,
            pointDotRadius: 2,
            scaleShowLabels: true,
            pointHitDetectionRadius: 5,
            animation: false,
            responsive: true
        });
        chart.draw();
    }
    ;

</script>