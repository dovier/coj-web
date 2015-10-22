<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h2 class="postheader">
	<spring:message code="pagetit.listpolls" />
</h2>
<div class="postcontent">
	<br /> <a href="<c:url value="/admin/poll/manage.xhtml" />"><spring:message
			code="add.poll" /></a> <br />

	<display:table id="poll" name="polls" class="volume">
		<display:column property="pid" titleKey="tablehdr.id"
			headerClass="headid" />
		<display:column property="question" titleKey="tablehdr.question"
			 />
		<display:column titleKey="tablehdr.enabled" headerClass="headid">
			<c:choose>
				<c:when test="${poll.enabled}">
					<button data-id="${poll.pid}"
						class="btn btn-success enable mybutton" type="button">
						<fmt:message key="page.general.yes" />
					</button>
				</c:when>
				<c:otherwise>
					<button data-id="${poll.pid}"
						class="btn btn-danger enable mybutton" type="button">
						<fmt:message key="page.general.no" />
					</button>
				</c:otherwise>
			</c:choose>
		</display:column>
		<display:column titleKey="tablehdr.actions" headerClass="headid">
			<a href="<c:url value="/admin/poll/manage.xhtml?pid=${poll.pid}"/>"
				title="<spring:message code="messages.general.edit"/>"><i
				class="fa fa-edit"></i></a>
			<a href="<c:url value="/admin/poll/delete.xhtml?pid=${poll.pid}"/>"
				title="<spring:message code="messages.general.delete"/>"><i
				class="fa fa-trash"></i> </a>
		</display:column>
	</display:table>
</div>
<script>
	$.ready(function() {
		$(".enable").on("click", function(event) {
			var elem = $(this);
			var pid = elem.data("id");

			elem.toggleClass("btn-success");
			elem.toggleClass("btn-danger");
			elem.toggleClass("disabled");

			$.ajax({
				url : "/admin/poll/enable.xhtml",
				type : 'POST',
				data : {
					"pid" : pid
				},
				success : function(data) {
					elem.toggleClass("disabled");

					if (elem.text() == yes_text) {
						elem.text(no_text);
					} else {
						elem.text(yes_text);
					}
				}
			});
		});
	});
</script>