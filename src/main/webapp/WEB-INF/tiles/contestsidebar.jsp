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
				<i class="fa fa-trophy fa-lg"></i>&nbsp;
				<spring:message code="block.rcontests" />
			</div>
			<div class="panel-body blockcontent-body">
				<!-- block-content -->
				<div>
					<ul class="list-unstyled">
						<li><a href="/contest/contestview.xhtml?cid=${contest.cid}">
								<i class="fa fa-eye"></i>&nbsp;<spring:message
									code="link.overview" />
						</a></li>
						<c:if test="${contest.gallery}">
							<li><a target="_blank"
								href="<c:url value="/contest/gallery.xhtml?cid=${contest.cid}" />">
									<span style="position: relative; top: 2px"><i
										class="fa fa-photo"></i>&nbsp;<spring:message
											code="link.gallery" /></span>
							</a></li>
						</c:if>
						<c:choose>
							<c:when test="${contest.running == true || contest.past == true}">
								<li><a
									href="<c:url value="/contest/myclarifications.xhtml?cid=${contest.cid}"/>"><i
										class="fa fa-bullhorn"></i>&nbsp;<spring:message
											code="link.clarifications" /> <c:if test="${totalmsg != 0}">(${totalmsg})</c:if>
										<c:if test="${unread != 0}">
											<span class="label label-danger">(${unread})</span>
										</c:if></a></li>
								<li><a
									href="<c:url value="/contest/cproblems.xhtml?cid=${contest.cid}" />"><i
										class="fa fa-list"></i>&nbsp; <spring:message
											code="link.problems" /></a></li>
							</c:when>
						</c:choose>
						<authz:authorize ifAnyGranted="ROLE_USER,ROLE_TEAM">
							<c:choose>
								<c:when test="${contest.running == true}">
									<li><a
										href="<c:url value="/contest/csubmit.xhtml?cid=${contest.cid}" />"><i
											class="fa fa-file-code-o"></i>&nbsp;<spring:message
												code="link.submit" /></a></li>
								</c:when>
							</c:choose>
						</authz:authorize>
						<c:choose>
							<c:when test="${contest.running == true || contest.past == true}">
								<li><a
									href="<c:url value="/contest/cstatus.xhtml?cid=${contest.cid}" />"><i
										class="fa fa-legal"></i>&nbsp;<spring:message
											code="link.judgments" /></a></li>
								<li><a
									href="<c:url value="/contest/cscoreboard.xhtml?cid=${contest.cid}" />"><i
										class="fa fa-sort-numeric-asc"></i>&nbsp;<spring:message
											code="link.standings" /></a></li>
								<c:if test="${contest.balloon and showBalloons}">
									<li><a target="_blank"
										href="<c:url value="/contest/cballoontracker.xhtml?cid=${contest.cid}" />">
											<span style="position: relative; top: 2px"><i
												class="fa fa-circle-o"></i>&nbsp;<spring:message
													code="link.balloontracker" /></span>
									</a></li>
								</c:if>
								<c:if test="${contest.saris and showSaris}">
									<li><a target="_blank"
										href="<c:url value="/contest/saris.xhtml?cid=${contest.cid}" />">
											<span style="position: relative; top: 2px"><i
												class="fa fa-flag-checkered"></i>&nbsp;<spring:message
													code="link.saris" /></span>
									</a></li>
								</c:if>
								<c:if test="${contest.past}">
									<li><a
										href="<c:url value="/contest/cawards.xhtml?cid=${contest.cid}" />">
											<span style="position: relative; top: 2px"><i
												class="fa fa-trophy"></i>&nbsp;<spring:message
													code="link.cawards" /></span>
									</a></li>
								</c:if>
								<li><a
									href="<c:url value="/contest/cstatistics.xhtml?cid=${contest.cid}" />"><i
										class="fa fa-bar-chart"></i>&nbsp;<spring:message
											code="link.statistics" /></a></li>
							</c:when>
						</c:choose>
					</ul>
				</div>
				<!-- /block-content -->

				<div class="cleared"></div>
			</div>
			<div class="cleared"></div>
		</div>
	</div>
</div>
