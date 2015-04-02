<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h2 class="postheader">
	<fmt:message key="page.general.admin.header" />
	: Global Access Rules
</h2>
<div class="postcontent">
	<form:form method="post" action="globalaccessrules.xhtml"
		commandName="rule">
		<label>Rule</label>
		<form:input path="rule" />
		<input type="submit" value="Add">
		<br />
		<form:errors path="rule" cssClass="error" />
	</form:form>
	<br />

	<div id="display-table-container"
		data-reload-url="/admin/tables/globalaccessrules.xhtml"></div>
</div>
<script>
	$(document).ready(displayTableReload("?page=1"));
</script>