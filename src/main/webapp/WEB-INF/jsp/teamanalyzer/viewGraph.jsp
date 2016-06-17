<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<link rel="stylesheet" href="<c:url value="/css/confirm-message.css"/>"
      type="text/css" media="screen"/>

<h2 class="postheader">
    <spring:message code="pagehdr.teamanalyzer"/>:
    <spring:message code="pagehdr.teamanalyzer.graph"/>
</h2>

<div class="postcontent">

    <div class="row row-centered no-gutters">
        <div class="col-xs-12">
            <label> <spring:message code="pagehdr.teamanalyzer.graph.team"/> ${tid}
            </label>
            <canvas id="top-chart" width="500" height="500"></canvas>
        </div>
        <div class="form-group col-xs-3 coj_float_rigth">
            <a class="btn btn-primary"
               href="<c:url value="/teamanalyzer/viewAnalysis.xhtml?taid=${taid}"/>"><spring:message
                    code="button.close"/></a>
        </div>
    </div>

</div>

<script type="text/javascript" src="<c:url value="/js/Chart.min.js"/>"></script>
<script>
    $(document).ready(function () {
        topCallback();
    });

    function topCallback() {
        var data = {
            labels: ${teamStats.labels},
            datasets: [
                {
                    fillColor: "rgba(230,72,151,0.8)",
                    strokeColor: "rgba(230,72,151,1)",
                    pointColor: "rgba(230,72,151,0.8)",
                    pointStrokeColor: "#fff",
                    pointHighlightFill: "#fff",
                    pointHighlightStroke: "rgba(230,72,151,0.8)",
                    data: ${teamStats.percentTopMax}
                },
                {
                    fillColor: "rgba(0,150,0,0.8)",
                    strokeColor: "rgba(0,150,0,0.8)",
                    pointColor: "rgba(0,150,0,0.8)",
                    pointStrokeColor: "#fff",
                    pointHighlightFill: "#fff",
                    pointHighlightStroke: "rgba(0,150,0,0.8)",
                    data: ${teamStats.percentTop1}
                },
                {
                    fillColor: "rgba(150,0,0,0.8)",
                    strokeColor: "rgba(150,0,0,0.8)",
                    pointColor: "rgba(150,0,0,0.8)",
                    pointStrokeColor: "#fff",
                    pointHighlightFill: "#fff",
                    pointHighlightStroke: "rgba(150,0,0,0.8)",
                    data: ${teamStats.percentTop2}
                },
                {
                    fillColor: "rgba(0,0,150,0.8)",
                    strokeColor: "rgba(0,0,150,0.8)",
                    pointColor: "rgba(0,0,150,0.8)",
                    pointStrokeColor: "#fff",
                    pointHighlightFill: "#fff",
                    pointHighlightStroke: "rgba(0,0,150,0.8)",
                    data: ${teamStats.percentTop3}
                },
                {
                    fillColor: "rgba(230,72,151,0)",
                    strokeColor: "rgba(230,72,151,1)",
                    pointColor: "rgba(230,72,151,0)",
                    pointStrokeColor: "#fff",
                    pointHighlightFill: "#fff",
                    pointHighlightStroke: "rgba(230,72,151,0)",
                    data: ${teamStats.percentTopMax}
                },
                {
                    fillColor: "rgba(151,187,205,0.4)",
                    strokeColor: "rgba(151,187,205,0.4)",
                    pointColor: "rgba(151,187,205,0.4)",
                    pointStrokeColor: "#fff",
                    data: ${teamStats.percentTotal}
                }
            ]
        };
        var topChart = new Chart($("#top-chart").get(0).getContext("2d")).Radar(data, {
            pointDot: false,
            scaleOverride: false,
            scaleIntegersOnly: true,
            scaleSteps: 1,
            scaleStepWidth: 1,
            scaleStartValue: 0,
        });
    }
    ;
</script>