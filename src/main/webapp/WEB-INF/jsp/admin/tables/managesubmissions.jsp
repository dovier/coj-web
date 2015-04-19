<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer="16kb" autoFlush="true"%>
<display:table id="submission" name="submissions" class="volume"
	requestURI="" defaultsort="0" defaultorder="ascending">
	<display:column property="sid" titleKey="tablehdr.id"
		headerClass="headid" href="/24h/submission.xhtml" paramId="id"
		paramProperty="sid" />
	<display:column property="date" titleKey="tablehdr.date"
		headerClass="headdate" />
	<display:column property="username" titleKey="tablehdr.user"
		headerClass="headuser" href="/user/useraccount.xhtml" paramId="uid"
		paramProperty="username" style="text-transform:none" />
	<display:column property="pid" titleKey="tablehdr.prob"
		headerClass="headprob" href="/24h/problem.xhtml" paramId="pid"
		paramProperty="pid" />
	<display:column titleKey="tablehdr.judgment" headerClass="headjudgment">
		<c:if test="${!empty submission.statusName}">
			<c:choose>
				<c:when test="${submission.authorize==false}">
					<label class="sub${submission.statusClass}">${submission.status}</label>
				</c:when>
				<c:when test="${submission.authorize==true}">
					<a href="/24h/compileinfo.xhtml?sid=${submission.sid}">${submission.status}</a>
				</c:when>
			</c:choose>
		</c:if>
		<c:if test="${empty submission.statusName}">
			<label class="sub${submission.statusClass}">${submission.status}</label>
		</c:if>
	</display:column>
	<display:column titleKey="tablehdr.time" headerClass="headtime">
		<c:if test="${submission.timeUsed == -1}">
				...
			</c:if>
			<c:if test="${submission.timeUsed != -1}">
				${submission.timeUsed}
			</c:if>
	</display:column>
	<display:column property="memoryMB" titleKey="tablehdr.mem"
		headerClass="headmem" />
	<display:column property="fontMB" titleKey="tablehdr.size"
		headerClass="headsize" />
	<display:column property="lang" titleKey="tablehdr.lang"
		headerClass="headlang" />
	<display:column titleKey="tablehdr.enabled">
		<c:choose>
			<c:when test="${submission.enabled == true}">
				<span class="label label-success"><fmt:message
						key="page.general.yes" /></span>
			</c:when>
			<c:otherwise>
				<span class="label label-danger"><fmt:message
						key="page.general.no" /></span>
			</c:otherwise>
		</c:choose>
	</display:column>
	<display:column titleKey="tablehdr.edit">
		<a
			href="<c:url value="managesubmission.xhtml?sid=${submission.sid}"/>"
			title="<fmt:message key="messages.general.go"/>"><i
			class="fa fa-edit"></i></a>
	</display:column>
	<display:column titleKey="tablehdr.rejudge" headerClass="headrejudge">
		<c:if test="${submission.cid == 0}">
			<a title="<spring:message code="tablehdr.rejudge"/>"
				href="<c:url value="javascript:rejudge24h(${submission.sid})"/>"><i
				class="fa fa-repeat"></i></a>&nbsp;
			<a title="<spring:message code="tablehdr.enable"/>"
				href="<c:url value="javascript:toggle24h(${submission.sid})"/>">
				<c:if test="${submission.enabled}">
					<i class="fa fa-eye-slash"></i>
				</c:if> <c:if test="${not submission.enabled}">
					<i class="fa fa-eye"></i>
				</c:if>
			</a>
		</c:if>
		<c:if test="${submission.cid!=0 }">
			<a title="<spring:message code="tablehdr.rejudge"/>"
				href="<c:url value="javascript:rejudgeContest(${submission.sid})"/>"><i
				class="fa fa-repeat"></i></a>&nbsp;
			<a title="<spring:message code="tablehdr.enable"/>"
				href="<c:url value="javascript:toggleContest(${submission.sid})"/>">
				<c:if test="${submission.enabled}">
					<i class="fa fa-eye-slash"></i>
				</c:if> <c:if test="${not submission.enabled}">
					<i class="fa fa-eye"></i>
				</c:if>
			</a>
		</c:if>
	</display:column>
</display:table>