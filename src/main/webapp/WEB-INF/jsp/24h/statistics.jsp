<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer="32kb" autoFlush="true"%>

<h2 class="postheader">
	<spring:message code="pagehdr.24hstats" />
</h2>
<div class="row">
	<div class="col-xs-12">
		<table class="table table-bordered table-condensed">
			<thead>
				<th class="col-xs-2"><spring:message code="tablehdr.lang" /></th>
				<th><spring:message code="tablehdr.ac" /></th>
				<th><spring:message code="tablehdr.ce" /></th>
				<th><spring:message code="tablehdr.ivf" /></th>
				<th><spring:message code="tablehdr.mle" /></th>
				<th><spring:message code="tablehdr.ole" /></th>
				<th><spring:message code="tablehdr.pe" /></th>
				<th><spring:message code="tablehdr.rte" /></th>
				<th><spring:message code="tablehdr.tle" /></th>
				<th><spring:message code="tablehdr.wa" /></th>
				<th><spring:message code="tablehdr.total" /></th>
			</thead>
			<c:forEach items="${statistics}" var="stats">
				<tr>
					<td><c:out value="${stats.language}" /></td>
					<td><a
						href="<c:url value="status.xhtml?status=ac&planguage=${stats.key}"/>"><c:out
								value="${stats.acc}" /></a></td>
					<td><a
						href="<c:url value="status.xhtml?status=ce&planguage=${stats.key}"/>"><c:out
								value="${stats.ce}" /></a></td>
					<td><a
						href="<c:url value="status.xhtml?status=ivf&planguage=${stats.key}"/>"><c:out
								value="${stats.ivf}" /></a></td>
					<td><a
						href="<c:url value="status.xhtml?status=mle&planguage=${stats.key}"/>"><c:out
								value="${stats.mle}" /></a></td>
					<td><a
						href="<c:url value="status.xhtml?status=ole&planguage=${stats.key}"/>"><c:out
								value="${stats.ole}" /></a></td>
					<td><a
						href="<c:url value="status.xhtml?status=pe&planguage=${stats.key}"/>"><c:out
								value="${stats.pe}" /></a></td>
					<td><a
						href="<c:url value="status.xhtml?status=rte&planguage=${stats.key}"/>"><c:out
								value="${stats.rte}" /></a></td>
					<td><a
						href="<c:url value="status.xhtml?status=tle&planguage=${stats.key}"/>"><c:out
								value="${stats.tle}" /></a></td>
					<td><a
						href="<c:url value="status.xhtml?status=wa&planguage=${stats.key}"/>"><c:out
								value="${stats.wa}" /></a></td>
					<td><a
						href="<c:url value="status.xhtml?planguage=${stats.key}"/>"><c:out
								value="${stats.total}" /></a></td>
				</tr>
			</c:forEach>
			<tr>
				<td class="colheader"><spring:message code="tablehdr.total" /></td>
				<td><a href="<c:url value="status.xhtml?status=ac"/>"><c:out
							value="${stat.acc}" /></a></td>
				<td><a href="<c:url value="status.xhtml?status=ce"/>"><c:out
							value="${stat.ce}" /></a></td>
				<td><a href="<c:url value="status.xhtml?status=ivf"/>"><c:out
							value="${stat.ivf}" /></a></td>
				<td><a href="<c:url value="status.xhtml?status=mle"/>"><c:out
							value="${stat.mle}" /></a></td>
				<td><a href="<c:url value="status.xhtml?status=ole"/>"><c:out
							value="${stat.ole}" /></a></td>
				<td><a href="<c:url value="status.xhtml?status=pe"/>"><c:out
							value="${stat.pe}" /></a></td>
				<td><a href="<c:url value="status.xhtml?status=rte"/>"><c:out
							value="${stat.rte}" /></a></td>
				<td><a href="<c:url value="status.xhtml?status=tle"/>"><c:out
							value="${stat.tle}" /></a></td>
				<td><a href="<c:url value="status.xhtml?status=wa"/>"><c:out
							value="${stat.wa}" /></a></td>
				<td><a href="<c:url value="status.xhtml"/>"><c:out
							value="${stat.total}" /></a></td>
			</tr>
		</table>
	</div>
</div>
<div class="row row-center">
	<div class="col-xs-12 col-centered">
		<canvas id="chart"></canvas>
	</div>
</div>
<div class="row row-center">
	<div class="col-xs-12 col-centered">
		<canvas id="top-chart"></canvas>
	</div>
</div>
<script type="text/javascript" src="<c:url value="/js/Chart.min.js"/>"></script>
<script>

function fitToContainer(canvas){
	  canvas.css("width",'100%');
	  canvas.css('height',canvas.outerWidth()/2);
	  canvas.attr('width',canvas.outerWidth());
	  canvas.attr('height',canvas.outerHeight());
	}
	
$(document).ready(function() {
	var canvas = $('#chart');
	var topCanvas = $('#top-chart');
	fitToContainer(canvas);
	fitToContainer(topCanvas);
	
	callback();
	topCallback();
});

function callback() {
			var data = {
				    labels: ["AC",
					"CE",
					"IVF",
					"MLE",
					"OLE",
					"PE",
					"RTE",
					"TLE",
					"WA",
					"Total"
					],
				    datasets: [
				        {
				            label: "General",
				            fillColor: "#4c83c3",
				            data: [${graph.acc}, ${graph.ce}, ${graph.ivf}, ${graph.mle},
									${graph.ole}, ${graph.pe}, ${graph.rte},
									${graph.tle}, ${graph.wa},${graph.total}]
				        }
				    ]
				};				
				var chart = new Chart($("#chart").get(0).getContext("2d")).Bar(data,{scaleShowVerticalLines: false,});
};

function topCallback() {
	//i18n datasets
	var i = 0;

	/*for(value in ${classif}){
     alert(value);
	}*/


	var data = {
		    labels: ${classif.labels},
		    datasets: [
		        {
		            fillColor: "rgba(151,187,205,0.4)",
		            strokeColor: "rgba(151,187,205,0.4)",
		            pointColor: "rgba(151,187,205,0.4)",
		            pointStrokeColor: "#fff",
		            pointHighlightFill: "#fff",
		            pointHighlightStroke: "rgba(220,220,220,1)",
		            data: ${classif.probTop}
		        }
		    ]
		};
	var topChart = new Chart($("#top-chart").get(0).getContext("2d")).Radar(data,{pointDotRadius : 4,scaleOverride: false,scaleIntegersOnly: true,scaleSteps: 1,scaleStepWidth: 1,scaleStartValue: 0});
};
</script>
