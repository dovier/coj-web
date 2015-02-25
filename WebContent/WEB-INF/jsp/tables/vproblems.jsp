<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer="16kb" autoFlush="true"%>
<display:table id="problem" name="problems" class="volume" requestURI=""
	defaultsort="0" defaultorder="ascending"
	decorator="cu.uci.coj.utils.tabledecorator.problemsTableDecorator">
	<display:column property="letter" titleKey="tablehdr.id"
		headerClass="headid" />
	<authz:authorize ifNotGranted="ROLE_ANONYMOUS">
		<display:column titleKey="tablehdr.solved">
			<c:choose>
				<c:when test="${problem.solved == true}">
					<i class="green fa fa-check-circle"></i>
				</c:when>
				<c:when test="${problem.unsolved == true}">
					<i class="red fa fa-minus-circle"></i>
				</c:when>
			</c:choose>
		</display:column>
	</authz:authorize>
	<display:column property="title" titleKey="tablehdr.title"
		href="vproblem.xhtml" paramId="pid" paramProperty="pid" />
	<display:column property="accu" titleKey="tablehdr.ac"
		headerClass="headac" />
</display:table>