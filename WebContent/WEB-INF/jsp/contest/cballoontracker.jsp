<%@include file="/WEB-INF/jsp/include/include.jsp"%>


<meta content="60" http-equiv="refresh">


<h2 class="postheader" style="clear: both">
	<a class="linkheader"
		href="<c:url value="contestview.xhtml?cid=${contest.cid}"/>">${contest.name}</a>
	<br>
	<spring:message code="pagetit.balloontracker" />
</h2>
<div class="row postcontent">
	<div class="col-xs-12">
		<c:choose>
			<c:when test="${contest.repointing == true}">
				<div class="label label-info">
					<i class="fa fa-info-circle"></i>The contest is being repointed,
					please wait a minute and try again
				</div>
			</c:when>
			<c:when
				test="${contest.running == true && (contest.full_frozen == true || contest.frozen == true)}">
				<center>
						<c:choose>
							<c:when test="${contest.full_frozen == true}">
							<div class="label label-danger">
								<i class="fa fa-warning"></i>&nbsp;
								<spring:message code="text.deadtime" />
							</div>
						</c:when>
							<c:otherwise>
								<div class="label label-primary">
											<i class="fa fa-info-circle"></i>&nbsp;<spring:message code="text.frozentime" />
								</div>
							</c:otherwise>
						</c:choose>
				</center>
			</c:when>
			<c:otherwise>
				<form method="get" class="form-inline">
					<select class="form-control" id="group" name="group">
						<option value="">All</option>
						<c:forEach items="${groups}" var="igroup">
							<c:if test="${igroup == selGroup}">
								<option selected="selected" value="${igroup}">${igroup}</option>
							</c:if>
							<c:if test="${igroup != selGroup}">
								<option value="${igroup}">${igroup}</option>
							</c:if>
						</c:forEach>
					</select> <input type="hidden" value="${contest.cid}" name="cid" /> <input
						class="btn btn-primary" type="submit"
						value="<spring:message code="button.filter"/>" />
				</form>
				<div class="col-xs-12">
					<div id="display-table-container"
						data-reload-url="/tables/cballoontracker.xhtml"></div>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</div>
<script>
	$(document).ready(displayTableReload("cid=${contest.cid}"));
</script>