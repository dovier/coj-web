<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<h2 class="postheader">
	<fmt:message key="page.general.admin.header" />: <fmt:message key="page.header.admin.site.details" />
</h2>

<div class="postcontent">
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
						<a href="${site.url}"> ${site.url}</a>
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
							<span class="label label-success"><fmt:message
									key="page.general.yes" /></span>
						</c:if>
						<c:if test="${!site.enabled}">
							<span class="label label-danger"><fmt:message
									key="page.general.no" /></span>
						</c:if>
					</div>
			
					<div class="col-xs-5">
						<spring:message code="fieldhdr.completed" />
					</div>
					<div class="col-xs-6 col-xs-offset-1">
						<c:if test="${site.completed}">
							<span class="label label-success"><fmt:message
									key="page.general.yes" /></span>
						</c:if>
						<c:if test="${!site.completed}">
							<span class="label label-danger"><fmt:message
									key="page.general.no" /></span>
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

			<div class="coj_float_rigth">
				<a class="btn btn-primary" href="<c:url value="/admin/wboard/site/list.xhtml"/>">
					<spring:message code="button.close"/>
				</a>
			</div>
		</div>
	</div>
</div>