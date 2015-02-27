<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<c:url value="/js/coj.js"/>"></script>

<h2 class="postheader" style="clear: both">
	<a class="linkheader"
		href="<c:url value="contestview.xhtml?cid=${contest.cid}"/>"
		title="Go to Contest">${contest.name}</a> <br />
	<spring:message code="pagehdr.contest.awards" />
</h2>

<c:if test="${not empty contestAwards}">
	<div class="row">
		<div class="col-xs-6 col-xs-offset-3">
			<div class="panel panel-primary">
				<div class="panel-body">
					<c:forEach items="${contestAwards}" var="award">
						<div class="row">
							<div class="col-xs-12">
								<a><i class="fa fa-trophy"></i></a> ${award.name} <a
									href="<c:url value="/contest/cuseraccount.xhtml?uid=${award.username}&cid=${award.cid}" />">${award.nick}</a>
								<a data-toggle="tooltip" data-placement="top"
									title="<c:out value="${award.description}" />"><i
									class="fa fa-info-circle"></i></a>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
</c:if>
<c:if test="${not empty users}">
	<div class="row">
		<c:forEach items="${users}" var="user">
			<div class="col-xs-offset-3 col-xs-6">
				<div class="panel panel-primary">
					<div class="panel-body">
						<img src="/images/<c:url value="${user.medal}"/>"
							alt="${user.rank}"
							title="<spring:message code="titval.${user.medal}"/>" /> <a
							href="<c:url value="/contest/cuseraccount.xhtml?uid=${user.username}&cid=${cid}" />">${user.nick}</a>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
</c:if>
</div>
</div>
<script>
	$(function() {
		$("[data-toggle='tooltip']").tooltip();
	});
</script>
