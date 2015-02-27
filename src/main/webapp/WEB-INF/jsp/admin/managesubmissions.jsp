<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--link rel="stylesheet" href="../Style/style.css" type="text/css" media="screen" /-->
<link rel="stylesheet"
	href="<c:url value="/css/ui-1.11.2/jquery-ui-timepicker-addon.css"/>"
	type="text/css" media="screen" />
<link rel="stylesheet"
	href="<c:url value="/css/ui-1.11.2/jquery-ui-1.11.2.min.css"/>"
	type="text/css" media="screen" />
<h2 class="postheader">
	<fmt:message key="page.general.admin.header" />
	: Submissions
</h2>
<div class=" row postcontent">
	<div class="col-xs-12">
		<form:form id="filter-form" commandName="filter" action="/admin/rejudgesubmissions.xhtml" method="post">
			<div class="row form-group">
				<div class="col-xs-4">
					<spring:message code="fieldhdr.user" />
					<form:input cssClass="form-control" path="username"
						value="${filter.username}" size="10" />
				</div>
				<div class="col-xs-4">
					<spring:message code="fieldhdr.startdate" />
					<form:input cssClass="form-control" path="startDate" id="startDate"
						value="${filter.startDate}" size="10" />
				</div>
				<div class="col-xs-4">
					<spring:message code="fieldhdr.enddate" />
					<form:input cssClass="form-control" path="endDate" id="endDate"
						value="${filter.endDate}" size="10" />
				</div>
			</div>
			<div class="row form-group">
				<div class="col-xs-4">
					<spring:message code="fieldhdr.cid" />
					<form:input cssClass="form-control" path="cid"
						value="${filter.cid}" size="8" />
				</div>
				<div class="col-xs-4">
					<spring:message code="fieldhdr.startSid" />
					<form:input cssClass="form-control" path="startSid"
						value="${filter.startSid}" size="8" />
				</div>
				<div class="col-xs-4">
					<spring:message code="fieldhdr.endSid" />
					<form:input cssClass="form-control" path="endSid"
						value="${filter.endSid}" size="8" />
				</div>
			</div>
			<div class="row form-group">
				<div class="col-xs-4">
					<spring:message code="fieldhdr.prob" />
					<form:input cssClass="form-control" path="pid"
						value="${filter.pid}" size="8" />
				</div>
				<div class="col-xs-4">
					<spring:message code="fieldhdr.judgment" />
					<form:select cssClass="form-control" path="status">
						<form:option value="">
							<spring:message code="fieldhdr.all" />
						</form:option>
						<form:options items="${statuslist}" itemValue="key"
							itemLabel="key"></form:options>
					</form:select>
				</div>
				<div class="col-xs-4">
					<spring:message code="fieldhdr.lang" />
					<form:select cssClass="form-control" path="language" id="language">
						<form:option value="">
							<spring:message code="fieldhdr.all" />
						</form:option>
						<form:options items="${languagelist}" itemValue="language"
							itemLabel="language"></form:options>
					</form:select>
				</div>
			</div>
			<div class="row form-group">
				<div class="col-xs-12">
					<div class="form-actions pull-right">
						<span id="count"></span> <input id="filter-button"
							class="btn btn-primary" type="submit"
							value="<spring:message code="button.filter"/>"
							name="filter-button" /> <input id="rejudge"
							class="btn btn-primary" name="rejudge" type="submit"
							value="<spring:message code="button.rejudge"/>" />
					</div>
				</div>
			</div>
		</form:form>
	</div>
	<div class="col-xs-12">
		<div id="display-table-container"
			data-reload-url="/admin/tables/managesubmissions.xhtml"></div>
	</div>
</div>


<script type="text/javascript"
	src="<c:url value="/js/ui/jquery-ui.min.js" />"></script>

<script type="text/javascript"
	src="<c:url value="/js/ui-1.11.2/jquery-ui-timepicker-addon.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/js/ui-1.11.2/jquery-ui-rangetimepicker-addon.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/js/ui-1.11.2/jquery-migrate-1.0.0.js"/>"></script>

<script>
	function rejudge24h(sid) {
		$.ajax({
			type : "GET",
			url : "/admin/24h/rejudge.json",
			data : "sid=" + sid,
			dataType : 'text',
			success : function(data) {
			}
		});
	}
	function rejudgeContest(sid) {
		$.ajax({
			type : "GET",
			url : "/admin/contest/rejudge.json",
			data : "sid=" + sid,
			dataType : 'text',
			success : function(data) {
			}
		});
	}

	$('#rejudge').click(function() {
		$('#form').attr('action', '/admin/rejudgesubmissions.xhtml');
		$('#form').attr('method', 'post');
	});

	function count() {
		$.ajax({
			type : "GET",
			url : "/admin/queuecount.json",
			dataType : 'text',
			success : function(data) {
				$('#count').html(data);
			},
			complete : function(data) {
				setTimeout(count, 2000);
			}
		});

	};

	$(function() {
		count();
	});

	$(function() {
		$('#filter-button').click(function(event) {
			displayTableReload($('#filter-form').formSerialize());
			event.preventDefault();
		});
	});

	$(document).ready(displayTableReload($('#filter-form').formSerialize()));
</script>