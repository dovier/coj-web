<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h2 class="postheader">
    <spring:message code="page.general.admin.header" />: <spring:message code="pagehdr.sessions" />
</h2>

<div class="postcontent">
	<c:if test="${message != null}">
		<div class="alert alert-success alert-dismissable fade in">
			<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
			<i class="fa fa-check"></i><spring:message code="${message}"/>
		</div>
	</c:if>

	<div id="display-table-container" data-reload-url="/admin/tables/sessions.xhtml"></div>

	<div class="coj_float_rigth">
		<a href="/admin/index.xhtml" class="btn btn-primary">
			<spring:message code="button.close" />
		</a>
	</div>
	<div class="clearfix"></div>
</div>

<link href="/css/bootstrap-dialog.min.css" rel="stylesheet" type="text/css" />
<script src="/js/bootstrap-dialog.min.js"></script>
<script src="/js/admin/utility.js"></script>

<script>
	$(document).ready(displayTableReload(" "));
</script>