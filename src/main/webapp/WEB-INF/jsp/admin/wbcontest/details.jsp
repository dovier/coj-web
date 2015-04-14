<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<h2 class="postheader">
	<fmt:message key="page.header.admin.wbcontest.details" />
</h2>

<div class="postcontent">
	<a class="btn btn-primary" href='<c:url value="/admin/wboard/contest/list.xhtml"/>'> <spring:message code="wboard.list.contest"/> </a>
	
	<br />
	<br />
	<div class="row">
		<div class="col-xs-12">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<spring:message code="fieldhdr.details" />
				</div>
				<div id="details" class="panel-body">				
					<div class="col-xs-5">
						<spring:message code="fieldhdr.name" />							
					</div>
					<div class="col-xs-6 col-xs-offset-1">
						${wbcontest.name}
					</div>
				
			
					<div class="col-xs-5">
						<spring:message code="fieldhdr.url" />							
					</div>
					<div class="col-xs-6 col-xs-offset-1">
						${wbcontest.url}
					</div>
				
					<div class="col-xs-5">
						<spring:message code="fieldhdr.startdate" />							
					</div>
					<div class="col-xs-6 col-xs-offset-1">
						${wbcontest.startDate}
					</div>					
					
					<div class="col-xs-5">
						<spring:message code="fieldhdr.enddate" />							
					</div>
					<div class="col-xs-6 col-xs-offset-1">
						${wbcontest.endDate}
					</div>	
					
					<div class="col-xs-5">
						<spring:message code="fieldhdr.site" />							
					</div>
					<div class="col-xs-6 col-xs-offset-1">
						${mapsites.get(wbcontest.sid).site}
					</div>							
				
					<div class="col-xs-5">
						<spring:message code="fieldhdr.notifcreated" />
					</div>
					<div class="col-xs-6 col-xs-offset-1">
						<c:if test="${wbcontest.notifCreated}">
							<i class="fa fa-check-square-o"></i>
						</c:if>
						<c:if test="${!wbcontest.notifCreated}">
							<i class="fa fa-square-o"></i>
						</c:if>
					</div>
			
					<div class="col-xs-5">
						<spring:message code="fieldhdr.notifchanged" />
					</div>
					<div class="col-xs-6 col-xs-offset-1">
						<c:if test="${wbcontest.notifChanged}">
							<i class="fa fa-check-square-o"></i>
						</c:if>
						<c:if test="${!wbcontest.notifChanged}">
							<i class="fa fa-square-o"></i>
						</c:if>
					</div>
						
				</div>
			</div>
		</div>
	</div>	
</div>