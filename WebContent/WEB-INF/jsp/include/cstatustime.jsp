<div class="row margin-bottom-05">
	<div class="col-xs-1 col-xs-offset-4">
		<b><spring:message code="fieldhdr.status" />: </b>
	</div>
	<div class="col-xs-1">
		<c:choose>
			<c:when test="${contest.running == true}">
				<div class="label label-success">
					<spring:message code="fieldhdr.running" />
				</div>
			</c:when>
			<c:when test="${contest.coming == true}">
				<div class="label label-danger">
					<spring:message code="fieldhdr.coming" />
				</div>
			</c:when>
			<c:when test="${contest.past == true}">
				<div class="label label-default">
					<spring:message code="fieldhdr.past" />
				</div>
			</c:when>
		</c:choose>
	</div>
	<c:if test="${contest.running == true}">

		<div class="col-xs-1">
			<b><spring:message code="fieldhdr.elapsed" />: </b>
		</div>
		<div class="col-xs-2">${contest.elapsed}</div>
		<div class="col-xs-1">
			<b><spring:message code="fieldhdr.remaining" />: </b>
		</div>
		<div class="col-xs-2">${contest.remaining}</div>

	</c:if>

	<c:if test="${contest.running != true}">

		<div class="col-xs-1">
			<b><spring:message code="fieldhdr.start" />:</b>
		</div>
		<div class="col-xs-2">
			<fmt:formatDate value="${contest.initdate}"
				pattern="yyyy-MM-dd HH:mm:ss" />
		</div>
		<div class="col-xs-1">
			<b><spring:message code="fieldhdr.end" />: </b>
		</div>
		<div class="col-xs-2">
			<fmt:formatDate value="${contest.enddate}"
				pattern="yyyy-MM-dd HH:mm:ss" />
	</c:if>
	<c:if
		test="${contest.running == true && (contest.full_frozen == true || contest.frozen == true)}">
		<c:choose>
			<c:when test="${contest.full_frozen == true}">
				<div class="label label-danger">
					<i class="fa fa-warning"></i>&nbsp;<spring:message code="text.deadtime" />
				</div>
			</c:when>
			<c:otherwise>
				<div class="label label-primary">
					<i class="fa fa-info-circle"></i>&nbsp;<spring:message code="text.frozentime" />
				</div>
			</c:otherwise>
		</c:choose>
	</c:if>
</div>