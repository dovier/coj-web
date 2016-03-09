<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h2 class="postheader">
	<spring:message code="pagehdr.problem" />
	<a href="<c:url value="problem.xhtml?pid=${problem.pid}"/>"
		style="color: #4c83c3;"> ${problem.title}</a> <br />
	<spring:message code="pagehdr.bestsolutions" />
</h2>
<div class="postcontent">
	<h3>
		<spring:message code="fieldhdr.statsjudgments" />
	</h3>
		<authz:authorize ifAllGranted="ROLE_USER">
			<c:if test="${problem.solved == true}">
				<div style="clear: both; float: right">
					<c:choose>
						<c:when test="${problem.locked == true}">
							<spring:message code="text.bestsolutions.1" />
						</c:when>
						<c:otherwise>
							<form action="lockproblem.xhtml" onsubmit="return checkLock()"
								method="post">
								<a><i data-toggle="tooltip" class="fa fa-info-circle"
								   title="<spring:message code="text.bestsolutions.2"/>"></i></a>
								<input type="hidden" value="${problem.pid}" name="pid" />
								<input type="submit" class="btn btn-primary" value="<spring:message code="button.lockmysols"/>">
							</form>
						</c:otherwise>
					</c:choose>
				</div>
			</c:if>
		</authz:authorize>

	<div class="row">
		<div class="col-xs-12">
			<table class="volume" border="1px">
				<thead>
					<th><spring:message code="tablehdr.ac" /></th>
					<th><spring:message code="tablehdr.ce" /></th>
					<th><spring:message code="tablehdr.sle" /></th>
					<th><spring:message code="tablehdr.ivf" /></th>
					<th><spring:message code="tablehdr.mle" /></th>
					<th><spring:message code="tablehdr.ole" /></th>
					<th><spring:message code="tablehdr.pe" /></th>
					<th><spring:message code="tablehdr.rte" /></th>
					<th><spring:message code="tablehdr.tle" /></th>
					<th><spring:message code="tablehdr.wa" /></th>
					<th><spring:message code="tablehdr.total" /></th>
				</thead>
				<tr>
					<td><a href="/24h/status.xhtml?pid=${problem.pid}&status=ac"><c:out
								value="${problem.ac}" /></a></td>
					<td><a href="/24h/status.xhtml?pid=${problem.pid}&status=ce"><c:out
								value="${problem.ce}" /></a></td>
					<td><a href="/24h/status.xhtml?pid=${problem.pid}&status=sle"><c:out
								value="${problem.fle}" /></a></td>
					<td><a href="/24h/status.xhtml?pid=${problem.pid}&status=ivf"><c:out
								value="${problem.ivf}" /></a></td>
					<td><a href="/24h/status.xhtml?pid=${problem.pid}&status=mle"><c:out
								value="${problem.mle}" /></a></td>
					<td><a href="/24h/status.xhtml?pid=${problem.pid}&status=ole"><c:out
								value="${problem.fle}" /></a></td>
					<td><a href="/24h/status.xhtml?pid=${problem.pid}&status=pe"><c:out
								value="${problem.pe}" /></a></td>
					<td><a href="/24h/status.xhtml?pid=${problem.pid}&status=rte"><c:out
								value="${problem.rte}" /></a></td>
					<td><a href="/24h/status.xhtml?pid=${problem.pid}&status=tle"><c:out
								value="${problem.tle}" /></a></td>
					<td><a href="/24h/status.xhtml?pid=${problem.pid}&status=wa"><c:out
								value="${problem.wa}" /></a></td>
					<td><a href="/24h/status.xhtml?pid=${problem.pid}"><c:out
								value="${problem.submitions}" /></a></td>
				</tr>
			</table>
		</div>
	</div>
	<div class="row">
		<div class="col-xs-12">
			<div class="text-center">
				<canvas id="chart" style="width: 756px; height: 475px">
				</canvas>
			</div>
		</div>
	</div>

	<div id="display-table-container" data-reload-url="/tables/bestsolutions.xhtml"></div> 
</div>

<script type="text/javascript" src="<c:url value="/js/Chart.min.js"/>"></script>
<script type="text/javascript">
	$("[data-toggle='tooltip']").tooltip();
    function checkLock(){
        var answer = confirm ("<spring:message code="imfomsg.bestsolutions.3"/>");
        if(!answer)
            return false;
        return true;
    }

    	$(document).ready(function() {
    		var pieData = [
    						{
    							value: ${problem.ac},
    							color:"green",
    							label: "AC"
    						},
    						{
    							value: ${problem.ce},color: "grey",label: "CE"
    						},
    						{
    							value: ${problem.ivf},color: "grey",label: "IVF"
    						},{
    							value: ${problem.mle},color: "yellow",label: "MLE"
    						},{
    							value: ${problem.fle},color: "blue",label: "OLE"
    						},{
    							value: ${problem.pe},color: "grey",label: "PE"
    						},{
    							value: ${problem.rte},color: "grey",label: "RTE"
    						},{
    							value: ${problem.tle},color: "grey",label: "TLE"
    						},{
    							value: ${problem.wa},color: "red",label: "WA"
    						}

    					];
    				
    				var chart = new Chart($("#chart").get(0).getContext("2d")).Pie(pieData);
    		});
    	$(document).ready(displayTableReload("?pid=${problem.pid}"));
    </script>