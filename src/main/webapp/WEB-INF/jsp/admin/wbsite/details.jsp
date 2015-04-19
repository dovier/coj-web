<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<h2 class="postheader">
	<fmt:message key="page.header.admin.site.details" />
</h2>

<div class="postcontent">
	<a class="btn btn-primary mybutton" href='<c:url value="/admin/wboard/site/list.xhtml"/>'> <spring:message code="wboard.list.site"/> </a>
	
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
						<spring:message code="fieldhdr.site" />							
					</div>
					<div class="col-xs-6 col-xs-offset-1">
						${site.site}
					</div>
				
			
					<div class="col-xs-5">
						<spring:message code="fieldhdr.url" />							
					</div>
					<div class="col-xs-6 col-xs-offset-1">
						${site.url}
					</div>
					
					<div class="col-xs-5">
						<spring:message code="fieldhdr.code" />							
					</div>
					<div class="col-xs-6 col-xs-offset-1">
						${site.code}
					</div>
				
					<div class="col-xs-5">
						<spring:message code="fieldhdr.enabled" />
					</div>
					<div class="col-xs-6 col-xs-offset-1">
						<c:if test="${site.enabled}">
							<i class="fa fa-check-square-o"></i>
						</c:if>
						<c:if test="${!site.enabled}">
							<i class="fa fa-square-o"></i>
						</c:if>
					</div>
			
					<div class="col-xs-5">
						<spring:message code="fieldhdr.completed" />
					</div>
					<div class="col-xs-6 col-xs-offset-1">
						<c:if test="${site.completed}">
							<i class="fa fa-check-square-o"></i>
						</c:if>
						<c:if test="${!site.completed}">
							<i class="fa fa-square-o"></i>
						</c:if>
					</div>
			
					<div class="col-xs-5">
						<spring:message code="fieldhdr.timeanddateid" />							
					</div>
					<div class="col-xs-6 col-xs-offset-1">
						${site.timeanddateid}
					</div>		
					
					<div class="col-xs-5">
						<spring:message code="fieldhdr.timezone" />							
					</div>
					<div class="col-xs-6 col-xs-offset-1">
						${site.timezone}
					</div>						
				</div>
			</div>
		</div>
	</div>	
</div>