<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer="16kb" autoFlush="true"%>

<script type="text/javascript"
	src="<c:url value="/js/WYSIWYG/source.js" />"></script>


<h2 class="postheader">
	<fmt:message key="page.general.admin.header" />
	: Manage Account Activations
</h2>
<div class="postcontent">
	<div>
		<form id="filter-form" class="form-inline">
			<div class="form-group">
				<label><spring:message code="fieldhdr.searchactivations" />:</label>
				<input type="text" class="form-control" name="pattern"
					value="${pattern}">
			</div>
			<div class="form-group">
				<input id="filter-button" type="submit" class="btn btn-primary"
					value="<spring:message code="button.filter"/>">
			</div>
		</form>
	</div>
	<label><spring:message code="fieldhdr.totalfound" />: ${found}</label>
	<div>
		<button id="resend" class="mybutton btn btn-primary " type="button">
			<fmt:message key="fieldhdr.resendactivations" />
		</button>
		<span id="resendgood" class="hide label label-success notif"><spring:message
				code="text.good" /></span>
	</div>
	<div id="display-table-container"
		data-reload-url="/admin/tables/manageactivations.xhtml"></div>
</div>
<script>
	$(function() {
		$("#resend").click(function(event) {
			$.post("/admin/resendactivations.json", function(event) {
				$("#resendgood").toggleClass("hide");
			});
			event.preventDefault();
		});
	});
	
	$(initStandardFilterForm);
</script>