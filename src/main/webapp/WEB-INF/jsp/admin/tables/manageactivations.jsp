<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer="16kb" autoFlush="true"%>
<display:table id="activation" name="activations" class="volume"
	requestURI="" defaultsort="0" defaultorder="descending">
	<display:column property="id" titleKey="tablehdr.id"
		headerClass="headid" />
	<display:column property="username" titleKey="tablehdr.user" />
	<display:column property="token" titleKey="tablehdr.token" />
	<display:column property="date" titleKey="tablehdr.date" />
	<display:column titleKey="tablehdr.actions">
		<button data-activation="${activation.id}"
			class="resend mybutton btn btn-primary " type="button">
			<i class="fa fa-envelope-o"></i>
		</button>&nbsp;
		<a
			href="<c:url value="/admin/accountactivation.xhtml?key=${activation.token}"/>"><i
			class="text-success fa fa-check-circle"></i></a>&nbsp;
			<a
			href="<c:url value="/admin/deleteactivation.xhtml?key=${activation.token}"/>">
			<i class="fa fa-trash"></i>
		</a>
	</display:column>
</display:table>
<script>
	$(".resend").click(function(event) {
		var data = $(this).data("activation");
		var caller = $(this);
		$.post("/admin/resendactivations.json?act_id=" + data, function(event) {
			caller.hide();
		});
		event.preventDefault();
	});
</script>