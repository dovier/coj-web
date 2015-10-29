<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<legend>
	<spring:message code="page.general.admin.header" />: <spring:message code="pagetit.listpolls" />
</legend>
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
                                    <span class="label label-success"><fmt:message
						key="page.general.yes" /></span>
				</c:when>
				<c:otherwise>
					<span class="label label-danger"><fmt:message
						key="page.general.no" /></span>
				</c:otherwise>
			</c:choose>
		</display:column>
		<display:column titleKey="tablehdr.actions" headerClass="headid">
			<a href="<c:url value="/admin/poll/manage.xhtml?pid=${poll.pid}"/>"
				><i title="<spring:message code="messages.general.edit"/>"
				data-toggle="tooltip" class="fa fa-edit"></i></a>
			<a href="<c:url value="/admin/poll/delete.xhtml?pid=${poll.pid}"/>"
				><i title="<spring:message code="messages.general.delete"/>"
				data-toggle="tooltip" class="fa fa-trash"></i> </a>
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

<script>    
    $("[data-toggle='tooltip']").tooltip();
</script>