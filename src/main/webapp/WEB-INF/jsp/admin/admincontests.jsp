<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<h2 class="postheader">
	<spring:message code="pagehdr.amcontests" />
</h2>
<div class="postcontent">
	<div class="row">
		<div class="col-xs-12">
			<form action="/admin/tables/admincontests.xhtml" method="get" id="filter-form" class="form-inline">
				<div class="form-group">
					<select class="form-control" name="access">
						<option value="all"><spring:message code="fieldhdr.all" /></option>
						<option value="2"><spring:message code="titval.public"/></option>
						<option value="0"><spring:message code="titval.private"/></option>
					</select>
				</div>
				
				<div class="form-group">
					<select class="form-control" name="enabled">
						<option value="all"><spring:message code="fieldhdr.all" /></option>
						<option value="true"><spring:message code="fieldhdr.enabled" /></option>
						<option value="false"><spring:message code="fieldhdr.disabled" /></option>
					</select>
				</div>
				
				<div class="form-group">
					<select class="form-control" name="status">
						<option value="all"><spring:message code="fieldhdr.all" /></option>
						<option value="coming"><spring:message code="fieldval.coming" /></option>
						<option value="running"><spring:message code="fieldval.running" /></option>
						<option value="past"><spring:message code="fieldval.past" /></option>
					</select>
				</div>
				
				<div class="form-group">
					<input class="btn btn-primary" type="submit" id ="filter-button"
					value="<spring:message code="button.filter"/>" />
				</div>
			</form>
		</div>
	</div>	
	
	<br/>
	
	<a href="<c:url value="/admin/createcontest.xhtml" />"><spring:message
		code="link.aaddcontest" /></a>	
	<div id="display-table-container" data-reload-url="/admin/tables/admincontests.xhtml"></div>
</div>
<script>
$(initStandardFilterForm);
</script>










