<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer="16kb" autoFlush="true"%>
<display:table id="submit" name="submits" class="volume" requestURI=""
	defaultsort="0" defaultorder="ascending">
	<display:column titleKey="tablehdr.prob" headerClass="headid">
		<span id="${submit.sid}" class="fa-stack fa-2x"> <span
			style="color:${submit.color}"> <i
				class="fa fa-circle fa-stack-2x"></i>
		</span> <small class="fa fa-stack-1x white shadow">${submit.letter} </small>
		</span>
	</display:column>
	<display:column titleKey="tablehdr.user" headerClass="headdate">
		${submit.userNick}
	</display:column>
	<display:column titleKey="tablehdr.date" headerClass="headdate">
		<c:set var="newdate" value="${fn:substring(submit.ddate, 0, 19)}" />
		${newdate}
	</display:column>
	<c:if test="${showBalloonMark}">
		<display:column titleKey="tablehdr.actions" headerClass="headdate">
			<a href="javascript:mark(${submit.sid});" data-toggle="tooltip" title="<spring:message code="page.cballoontracker.markballoon" /> "><i class="fa fa-check-circle"></i></a>
		</display:column>
	</c:if>
</display:table>
<script>
	$("[data-toggle='tooltip']").tooltip();
</script>