<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<link rel="stylesheet" href="<c:url value="/css/confirm-message.css"/>"
      type="text/css" media="screen"/>

<h2 class="postheader">
    <spring:message code="pagehdr.teamanalyzer"/>
    <%--<c:out value="${user.nick}"/>--%>
</h2>

<div class="postcontent">


    <div class="row row-centered no-gutters">
        <div class="col-xs-12">
            <%--<c:if test="${userclassif.count > 0}">--%>
            <label> <spring:message code="user.percent.stats"/>
            </label>
            <canvas id="top-chart" width="500" height="500"></canvas>
            <%--</c:if>--%>
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
            <%--labels: ${userclassif.labels},--%>
            labels: ["comer", "coser", "cantar"],
            datasets: [
                {
                    fillColor: "rgba(0,150,0,0.8)",
                    strokeColor: "rgba(0,150,0,0.8)",
                    pointColor: "rgba(0,150,0,0.8)",
                    pointStrokeColor: "#fff",
                    pointHighlightFill: "#fff",
                    pointHighlightStroke: "rgba(0,150,0,0.8)",
                    <%--data: ${userclassif.percentTop}--%>
                    data: [14, 15, 24]
                },
                {
                    fillColor: "rgba(151,187,205,0.4)",
                    strokeColor: "rgba(151,187,205,0.4)",
                    pointColor: "rgba(151,187,205,0.4)",
                    pointStrokeColor: "#fff",
                    <%--data: ${userclassif.percentTotal}--%>
                    data: [50, 50, 50]
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
</script>