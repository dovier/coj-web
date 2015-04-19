<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<h2 class="postheader">
	<spring:message code="pagehdr.rcsstats" />
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
					<td><c:out value="${stats.acc}" /></td>
					<td><c:out value="${stats.ce}" /></td>
					<td><c:out value="${stats.ivf}" /></td>
					<td><c:out value="${stats.mle}" /></td>
					<td><c:out value="${stats.ole}" /></td>
					<td><c:out value="${stats.pe}" /></td>
					<td><c:out value="${stats.rte}" /></td>
					<td><c:out value="${stats.tle}" /></td>
					<td><c:out value="${stats.wa}" /></td>
					<td><c:out value="${stats.total}" /></td>
				</tr>
			</c:forEach>
			<tr>
				<td class="colheader"><spring:message code="tablehdr.total" /></td>
				<td><c:out value="${stat.acc}" /></td>
				<td><c:out value="${stat.ce}" /></td>
				<td><c:out value="${stat.ivf}" /></td>
				<td><c:out value="${stat.mle}" /></td>
				<td><c:out value="${stat.ole}" /></td>
				<td><c:out value="${stat.pe}" /></td>
				<td><c:out value="${stat.rte}" /></td>
				<td><c:out value="${stat.tle}" /></td>
				<td><c:out value="${stat.wa}" /></td>
				<td><c:out value="${stat.total}" /></td>
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
	fitToContainer(canvas);
	
	callback();
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
</script>