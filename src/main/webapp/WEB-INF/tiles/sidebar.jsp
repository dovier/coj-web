<%@page import="cu.uci.coj.utils.Utils"%>
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
				<i class="fa fa-clock-o fa-lg"></i>&nbsp;
				<spring:message code="block.24h" />
			</div>
			<div class="panel-body blockcontent-body">
				<!-- block-content -->
				<div>
					<ul class="list-unstyled">
						<li <c:if test="${idpage == 'problems'}"> class="item-sidebar-selected" </c:if>><a href="<c:url value="/24h/problems.xhtml" />"><i
								class="fa fa-list"></i> <spring:message code="link.problems" /></a></li>
						<authz:authorize ifAnyGranted="ROLE_USER">
							<li <c:if test="${idpage == 'submit'}"> class="item-sidebar-selected" </c:if>><a href="<c:url value="/24h/submit.xhtml" />"><i
									class="fa fa-file-code-o"></i>&nbsp;<spring:message
										code="link.submit" /></a></li>
						</authz:authorize>

						<li <c:if test="${idpage == 'status'}"> class="item-sidebar-selected" </c:if>><a href="<c:url value="/24h/status.xhtml" />"><i
								class="fa fa-legal"></i>&nbsp;<spring:message
									code="link.judgments" /></a></li>

						<li><a href="<c:url value="/24h/usersrank.xhtml" />"><i
								class="fa fa-sort-numeric-asc"></i>&nbsp;<spring:message
									code="link.standings" /></a>
						<ul>
								<li <c:if test="${idpage == 'usersrank'}"> class="item-sidebar-selected" </c:if>><a href="<c:url value="/24h/usersrank.xhtml" />"><i
										class="fa fa-users"></i>&nbsp;<spring:message
											code="link.users" /></a>
								</li>

								<li <c:if test="${idpage == 'institutionrank'}"> class="item-sidebar-selected" </c:if>><a href="<c:url value="/24h/institutionsrank.xhtml" />"><i
										class="fa fa-institution"></i>&nbsp;<spring:message
											code="link.institutions" /></a>
								</li>

								<li <c:if test="${idpage == 'countriesrank'}"> class="item-sidebar-selected" </c:if>><a href="<c:url value="/24h/countriesrank.xhtml" />"><i
										class="fa fa-globe"></i>&nbsp;<spring:message
											code="link.countries" /></a>
								</li>
							</ul>
						</li>
						<li <c:if test="${idpage == 'compareusers'}"> class="item-sidebar-selected" </c:if>><a href="<c:url value="/user/compareusers.xhtml" />"><i
								class="fa fa-refresh"></i>&nbsp;<spring:message
									code="link.cusers" /></a></li>
									
						<!-- frankr addition start -->
						<li <c:if test="${idpage == 'compareusersbytag'}"> class="item-sidebar-selected" </c:if>><a href="<c:url value="/user/compareusersbytag.xhtml" />"><i
								class="fa fa-refresh"></i>&nbsp;<spring:message
									code="link.cusersbytag" /></a></li>
						<!-- frankr addition end -->									
									
									
						<li <c:if test="${idpage == 'statistics'}"> class="item-sidebar-selected" </c:if>><a href="<c:url value="/24h/statistics.xhtml" />"><i
								class="fa fa-bar-chart"></i>&nbsp;<spring:message
									code="link.statistics" /></a></li>
					</ul>
				</div>
				<!-- /block-content -->

				<div class="cleared"></div>
			</div>
			<div class="cleared"></div>
		</div>
	</div>
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
						<li <c:if test="${idpage == 'coming_contest'}"> class="item-sidebar-selected" </c:if>><a href="<c:url value="/contest/coming.xhtml"/>"><i
								class="fa fa-arrow-circle-right"></i>&nbsp;<spring:message
									code="link.coming" /> <c:choose>
									<c:when test="${com > 0}">
										<span class="label label-danger">(${com})</span>
									</c:when>
								</c:choose> </a>
						</li>

						<li <c:if test="${idpage == 'running_contest'}"> class="item-sidebar-selected" </c:if>><a href="<c:url value="/contest/running.xhtml"/>"><i
								class="fa fa-play"></i>&nbsp;<spring:message code="link.running" />
								<c:choose>
									<c:when test="${run > 0}">
										<span class="label label-success">(${run})</span>
									</c:when>
								</c:choose> </a>
						</li>

						<li <c:if test="${idpage == 'past_contest'}"> class="item-sidebar-selected" </c:if>><a href="<c:url value="/contest/past.xhtml"/>"><i
								class="fa fa-arrow-circle-left"></i>&nbsp;<spring:message
									code="link.previous" /> <span class="label label-default">(${attpast})</span></a>
						</li>

						<li <c:if test="${idpage == 'rank_contest'}"> class="item-sidebar-selected" </c:if>><a href="<c:url value="/contest/contestsrank.xhtml" />"><i
								class="fa fa-sort-numeric-asc"></i>&nbsp;<spring:message
									code="link.standings" /></a></li>
						<li <c:if test="${idpage == 'globalstatistics_contest'}"> class="item-sidebar-selected" </c:if>><a
							href="<c:url value="/contest/globalstatistics.xhtml"/>"><i
								class="fa fa-bar-chart"></i>&nbsp;<spring:message
									code="link.statistics" /></a></li>
					</ul>

				</div>
				<!-- /block-content -->

				<div class="cleared"></div>
			</div>
			<div class="cleared"></div>
		</div>
	</div>
</div>
