<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<link rel="stylesheet" href="<c:url value="/css/confirm-message.css"/>" type="text/css" media="screen" />

<h2 class="postheader">
	<fmt:message key="page.general.admin.header" />
	: <fmt:message key="tablehdr.translations" />
</h2>
<div class="postcontent">
	<!-- article-content -->
	
	<div class="row">
		<div class="col-xs-12">
			<form action="/admin/tables/managetranslations.xhtml" method="get" id="filter-form" class="form-inline">
				<div class="form-group">						
					<input class="form-control" placeholder="<spring:message code="fieldhdr.user" />"
					 	type="text" name="username" value="${username}" size="12" />
				</div>				
				
				<div class="form-group">
					<input class="form-control" placeholder="<spring:message code="fieldhdr.prob" />"
						 type="text" name="pid"	size="10" />
				</div>
				
				<div class="form-group">
					<select class="form-control" name="locale">
						<option value="all"><spring:message code="fieldhdr.all" /></option>
						<option value="en">en</option>
						<option value="es">es</option>
						<option value="pt">pt</option>
					</select>
				</div>
				
				<div class="form-group">
					<input class="btn btn-primary" type="submit" id ="filter-button"
					value="<spring:message code="button.filter"/>" />
				</div>
			</form>
		</div>
	</div>	

	<div id="display-table-container" data-reload-url="/admin/tables/managetranslations.xhtml"></div>
	<!-- /article-content -->
</div>

<script>
$(initStandardFilterForm);
</script>