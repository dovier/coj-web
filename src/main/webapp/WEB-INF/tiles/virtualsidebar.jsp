<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="layout-cell sidebar1">
	<authz:authorize ifAnyGranted="ROLE_ANONYMOUS">
		<%@include file="/WEB-INF/tiles/sidebarpublic.jsp"%>
	</authz:authorize>
	<authz:authorize ifAnyGranted="ROLE_USER,ROLE_TEAM">
		<%@include file="/WEB-INF/tiles/sidebarlogged.jsp"%>
	</authz:authorize>
	<div class="block">
		<div class="panel panel-primary block-body">
			<div class="panel-heading">
				<i class="fa fa-cubes fa-lg"></i>&nbsp;
				<spring:message code="block.pcontests" />
			</div>
			<div class="panel-body blockcontent-body">
				<!-- block-content -->
				<div>
					<ul class="list-unstyled">
						<authz:authorize ifAnyGranted="ROLE_USER,ROLE_TEAM">
							<c:choose>
								<c:when
									test="${contest.running == true || contest.past == true}">
									<li><a
										href="/practice/vcontestview.xhtml"><i class="fa fa-eye"></i>&nbsp;<spring:message
												code="link.overview" /></a></li>
									<li><a
											href="<c:url value="/practice/vproblems.xhtml" />"><i class="fa fa-list"></i>&nbsp;<fmt:message
													key="link.problems" /></a></li>
								</c:when>
							</c:choose>
						</authz:authorize>
						<authz:authorize ifAnyGranted="ROLE_USER,ROLE_TEAM">
							<c:choose>
								<c:when test="${contest.running == true}">
									<li><a
											href="<c:url value="/practice/vsubmit.xhtml" />"><i class="fa fa-file-code-o"></i>&nbsp;<fmt:message
													key="link.submit" /></a></li>
								</c:when>
							</c:choose>
						</authz:authorize>
						<authz:authorize ifAnyGranted="ROLE_USER,ROLE_TEAM">
							<c:choose>
								<c:when
									test="${contest.running == true || contest.past == true}">
									<li><a
											href="<c:url value="/practice/vstatus.xhtml" />"><i class="fa fa-legal"></i>&nbsp;<fmt:message
													key="link.judgments" /></a></li>
									<li><a
											href="<c:url value="/practice/vscoreboard.xhtml" />"><i class="fa fa-sort-numeric-asc"></i>&nbsp;<fmt:message
													key="link.standings" /></a></li>
								</c:when>
							</c:choose>
						</authz:authorize>
					</ul>
				</div>
				<!-- /block-content -->

				<div class="cleared"></div>
			</div>
			<div class="cleared"></div>
		</div>
	</div>
</div>

