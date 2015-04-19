<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer="16kb" autoFlush="true"%>

<display:table id="contest" name="contests" class="volume" requestURI=""
	defaultsort="0" defaultorder="ascending">
	<display:column property="name" titleKey="tablehdr.name"
		sortable="true" sortProperty="name" sortName="name" autolink="true"
		headerClass="headfirst" href="details.xhtml" paramId="id"
		paramProperty="id" />

	<display:column titleKey="tablehdr.status" headerClass="headsub"
		sortable="true" sortProperty="startdate" sortName="startdate">
		<c:choose>
			<c:when test="${contest.startDate > now}">
				<span class="label label-danger"><spring:message
						code="contest.status.com" /></span>
			</c:when>
			<c:when test="${contest.endDate < now}">
				<span class="label label-default"><spring:message
						code="contest.status.end" /></span>
			</c:when>
			<c:otherwise>
				<span class="label label-success"> <spring:message
						code="contest.status.rn" /></span>
			</c:otherwise>
		</c:choose>
	</display:column>

	<display:column titleKey="tablehdr.site" style="text-transform:none"
		headerClass="headsub" sortable="true" sortProperty="sid"
		sortName="sid">
		<a href="${mapsites.get(contest.sid).url}">${mapsites.get(contest.sid).code}</a>
	</display:column>


	<display:column titleKey="tablehdr.start" sortable="true"
		sortProperty="startdate" sortName="startdate" class="date"
		headerClass="headacc">
		<a
			href="http://timeanddate.com/worldclock/fixedtime.html?day=${contest.startDate.date}&month=${contest.startDate.month + 1}&year=${contest.startDate.year + 1900}&hour=${contest.startDate.hours}&min=${contest.startDate.minutes}&sec=${contest.startDate.seconds}&p1=${mapsites.get(contest.sid).timeanddateid}">
			<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"
				value="${contest.startDate}" />
		</a>
	</display:column>

	<display:column titleKey="tablehdr.end" sortable="true"
		sortProperty="enddate" sortName="enddate" class="date"
		headerClass="headacc">
		<a
			href="http://timeanddate.com/worldclock/fixedtime.html?day=${contest.endDate.date}&month=${contest.endDate.month + 1}&year=${contest.endDate.year + 1900}&hour=${contest.endDate.hours}&min=${contest.endDate.minutes}&sec=${contest.endDate.seconds}&p1=${mapsites.get(contest.sid).timeanddateid}">
			<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"
				value="${contest.endDate}" />
		</a>
	</display:column>
	<display:column titleKey="tablehdr.actions" headerClass="headedit">
		<a class="details"
			data-toggle="tooltip"
			href="<c:url value="/admin/wboard/contest/details.xhtml?id=${contest.id}"/>"
			title="<spring:message code="messages.general.details"/>"><i
			class="fa fa-eye"></i></a>
		<a class="edit"
			data-toggle="tooltip"
			href="<c:url value="/admin/wboard/contest/edit.xhtml?id=${contest.id}"/>"
			title="<spring:message code="messages.general.edit"/>"><i
			class="fa fa-edit"></i></a>
		<a class="confirm-message"
			data-toggle="tooltip"
			data-confirm-title='<spring:message code="message.confirm.delete.hdr.wbcontest"/>'
			data-confirm-message='<spring:message code="message.confirm.delete.wbcontest"/>'
			data-confirm-type="delete"
			href="<c:url value="/admin/wboard/contest/delete.xhtml?id=${contest.id}"/>"
			title="<spring:message code="messages.general.delete"/>"><i
			class="fa fa-trash"></i> </a>
	</display:column>
</display:table>

<%@include file="/WEB-INF/jsp/general/confirmmessage.jsp"%>

<script>
	$("[data-toggle='tooltip']").tooltip();
</script>
<%@include file="/WEB-INF/jsp/general/confirmmessage.jsp"%>