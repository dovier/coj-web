<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer="16kb" autoFlush="true"%>
<c:choose>
	<c:when test="${notid}">
		<div class="error col-xs-12 col-md-offset-6">
			<span class="label label-danger"><spring:message code="errormsg.25"/></span>
		</div>
	</c:when>
	<c:when test="${notint}">
		<div class="error col-xs-12 col-md-offset-6">
			<span class="label label-danger"><spring:message code="typeMismatch.submit.pid"/></span>
		</div>
	</c:when>
</c:choose>
<%--<c:if test="${notint}">
	<div class="alert alert-danger alert-dismissable fade in">
		<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
		<i class="fa fa-check"></i><spring:message code="errormsg.25"/>
	</div>
</c:if>--%>
<display:table id="translation" name="translations" class="volume">
	<display:column property="id" titleKey="tablehdr.id" headerClass="headid" />
	<display:column property="username" titleKey="tablehdr.user"
		headerClass="headuser" href="/user/useraccount.xhtml"
		paramId="username" paramProperty="username"
		style="text-transform:none" />
	<display:column property="pid" titleKey="tablehdr.prob"
		headerClass="headprob" href="/24h/problem.xhtml" paramId="pid"
		paramProperty="pid" />
	<display:column titleKey="tablehdr.lang" headerClass="headlang">
		<img data-toggle="tooltip" class="image" title="${translation.locale}" src="/images/i18n/${translation.locale}.png"/>
	</display:column>
	<display:column titleKey="tablehdr.date"
		headerClass="headdate" ><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${translation.date}" /></display:column>
	<display:column titleKey="tablehdr.actions" headerClass="headedit">
		<a class="details"
			data-toggle="tooltip"
			href="<c:url value="/admin/managetranslations/check.xhtml?id=${translation.id}"/>"
			title="<spring:message code="messages.general.check"/>"><i
			class="fa fa-eye"></i></a>
	</display:column>
</display:table>

<script>
	$("[data-toggle='tooltip']").tooltip();
</script>