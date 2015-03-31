<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<script type="text/javascript" src="<c:url value="/js/coj.js"/>"></script>

<c:if test="${refresh == true}">
	<c:choose>
		<c:when test="${contest.running}">
			<meta content="60" http-equiv="refresh">
		</c:when>
		<c:when test="${contest.frozen}">
			<meta content="60" http-equiv="refresh">
		</c:when>
	</c:choose>
</c:if>
<div class="row">
	<h2 class="postheader" style="clear: both">
		<a class="linkheader"
			href="<c:url value="contestview.xhtml?cid=${contest.cid}"/>">${contest.name}</a>
		<br />
		<spring:message code="pagehdr.standings" />
	</h2>
	<div style="text-align: center; margin-top: -20px;">
		<c:if test="${contest.running}">
			<c:choose>
				<c:when test="${refresh}">
						<div class="label label-info"><i class="fa fa-info-circle"></i>
							<spring:message code="text.vcstandings.3" />
						</div>
					<br />
					<c:if test="${group}">
						<a href="cscoreboard.xhtml?&cid=${contest.cid}">[Disable auto
							refresh]</a>
					</c:if>
					<c:if test="${not group}">
						<a href="cscoreboard.xhtml?&cid=${contest.cid}&ungroup">[Disable
							auto refresh]</a>
					</c:if>
				</c:when>
				<c:otherwise>
					<c:if test="${group}">
						<a href="cscoreboard.xhtml?&cid=${contest.cid}&refresh=true">[Auto
							refresh]</a>
					</c:if>
					<c:if test="${not group}">
						<a
							href="cscoreboard.xhtml?&cid=${contest.cid}&refresh=true&ungroup">[Auto
							refresh]</a>
					</c:if>
				</c:otherwise>
			</c:choose>
		</c:if>
	</div>
</div>
<div class="postcontent">
	<table class="navigating">
		<tbody>
			<tr>
				<td><a
					href="<c:url value="/contest/contestview.xhtml?cid=${contest.cid}" />"><i
										class="fa fa-eye"></i>&nbsp;<spring:message
							code="link.overview" /> </a></td>
				<td><a
					href="<c:url value="/contest/myclarifications.xhtml?cid=${contest.cid}"/>"><i
										class="fa fa-bullhorn"></i>&nbsp;<spring:message
							code="link.clarifications" /> <sup><c:if test="${totalmsg != 0}">
							<span class="label label-default">(${totalmsg})</span></c:if>
						<c:if test="${unread != 0}">
							<span class="label label-danger">(${unread})</span>
						</c:if></sup> </a></td>
				<td><a
					href="<c:url value="/contest/cproblems.xhtml?cid=${contest.cid}" />"><i
										class="fa fa-list"></i>&nbsp;<spring:message
							code="link.problems" /> </a></td>
				<authz:authorize ifAnyGranted="ROLE_USER,ROLE_TEAM">
					<c:choose>
						<c:when test="${contest.running == true}">
							<td><a
								href="<c:url value="/contest/csubmit.xhtml?cid=${contest.cid}" />"><i
										class="fa fa-file-code-o"></i>&nbsp;<spring:message
										code="link.submit" /></a></td>
						</c:when>
					</c:choose>
				</authz:authorize>
				<td><a
					href="<c:url value="/contest/cstatus.xhtml?cid=${contest.cid}" />"><i
										class="fa fa-legal"></i>&nbsp;<spring:message
							code="link.judgments" /></a></td>
				<c:if test="${contest.past}">
					<td><a
						href="<c:url value="/contest/cawards.xhtml?cid=${contest.cid}" />"><i
										class="fa fa-trophy"></i>&nbsp;<spring:message
									code="link.cawards" /></span></a></td>
				</c:if>
				<td><a
					href="<c:url value="/contest/cstatistics.xhtml?cid=${contest.cid}" />"><i
										class="fa fa-bar-chart"></i>&nbsp;<spring:message
							code="link.statistics" /></a></td>
			</tr>
		</tbody>
	</table>
	<c:choose>
		<c:when test="${contest.repointing == true}">
			<div class="label label-info">
				<i class="fa fa-info-circle"></i>The contest is being repointed,
				please wait a minute and try again
			</div>
		</c:when>
		<c:otherwise>
			<c:choose>
				<c:when test="${contest.show_status == true}">
					<c:choose>
						<c:when test="${contest.style eq 1}">
							<%@include file="/WEB-INF/jsp/contest/cscoreboardacm.jsp"%>
						</c:when>


						<c:when test="${contest.style eq 3}">
							<%@include file="/WEB-INF/jsp/contest/cscoreboard3.jsp"%>
						</c:when>

						<c:when test="${contest.style eq 4}">
							<%@include file="/WEB-INF/jsp/contest/cscoreboard4.jsp"%>
						</c:when>

						<c:otherwise>
                            STYLE SCOREBOARD NOT IMPLEMENTED YET ${contest.style}
                        </c:otherwise>
					</c:choose>
				</c:when>
				<c:otherwise>
					<div class="error">
						<spring:message code="contest.error.disable" />
					</div>
				</c:otherwise>
			</c:choose>
		</c:otherwise>
	</c:choose>
</div>
<script>
	$(function() {
		$("[data-toggle='tooltip']").tooltip();
		$('.fa-chevron-up').click(function() {
			$(this).toggleClass('fa-chevron-up');
			$(this).toggleClass('fa-chevron-down');
		});
		$('.fa-chevron-down').click(function() {
			$(this).toggleClass('fa-chevron-up');
			$(this).toggleClass('fa-chevron-down');
		});
	});
</script>