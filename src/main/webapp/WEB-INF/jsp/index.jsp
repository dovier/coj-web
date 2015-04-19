<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer="16kb" autoFlush="true"%>

<h2 class="postheader">
	<spring:message code="pagehdr.welcomemsg" />
</h2>
<!-- /article-content -->
<div class="row postcontent">
	<div class="col-xs-12">
		<authz:authorize access="isAuthenticated()">
			<form:form method="post" commandName="entry">
				<div class="form-group">
					<label class="control-label"><fmt:message key="entry.text" /></label>
					<form:textarea id="text" maxlength="255" cssClass="form-control"
						path="text" />
				</div>
				<div>
					<span class="label label-danger"><form:errors path="text" /></span>
				</div>
				<div class="margin-top-05 form-actions pull-left">
					<c:forEach items="${emoties}" var="emot">
						<a href="javascript:insertText('${emot.key}');">${emot.value}</a>&nbsp;
					</c:forEach>
				</div>
				<div class="margin-top-05 form-actions pull-right">
					<input class="btn btn-primary" type="submit" name="submit"
						id="submit"
						value="<fmt:message key="judge.register.submit.value"/>" />
				</div>
			</form:form>
		</authz:authorize>
	</div>
</div>
<div class="row">
	<authz:authorize access="isAuthenticated()">
		<div class="col-xs-3 col-xs-offset-9 filter-follow">
			<ul class="list-inline pull-right">
				<li><a href="javascript:displayTableReload('');"><spring:message
							code="link.all" /></a></li>
				<li><a href="javascript:displayTableReload('?entries=following');"><spring:message
							code="link.following" /></a></li>
			</ul>
		</div>
	</authz:authorize>

	<div id="display-table-container" data-reload-url="/tables/entries.xhtml" class="col-xs-12"></div>
</div>
<script>
	$(document).ready(function() {
		$('textarea#text').maxlength({
			alwaysShow : true,
			threshold : 10,
			warningClass : "label label-primary",
		});
	});

	

	function reply(text) {
		insertText(text);
		$('#reply').attr('checked', 'checked');
	}

	function insertText(text) {
		$('#text').val($('#text').val() + text);
	}

	$(document).ready(displayTableReload("?page=1"));
</script>