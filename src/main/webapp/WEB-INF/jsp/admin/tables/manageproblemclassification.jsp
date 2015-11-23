<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer="16kb" autoFlush="true" %>
<display:table id="problem" name="problems" class="volume" requestURI=""
	defaultsort="0" defaultorder="ascending"
	decorator="cu.uci.coj.utils.tabledecorator.problemsTableDecorator">
	<display:column titleKey="tablehdr.id" class="headidp">
		<c:out value="${problem.pid}" />
	</display:column>
	<display:column titleKey="tablehdr.title" style="text-transform:none">
		<a href="/24h/problem.xhtml?pid=${problem.pid}">${problem.title}</a>
	</display:column>
	<display:column titleKey="pagehdr.problemsetter">
		${problem.username}
	</display:column>
	<display:column property="submitions" titleKey="tablehdr.sub"
		sortable="true" sortProperty="submitions" sortName="total"
		autolink="true" headerClass="headsub" href="status.xhtml"
		paramId="pid" paramProperty="pid" />
	<display:column property="ac" titleKey="tablehdr.ac" sortable="true"
		sortProperty="ac" sortName="acc" headerClass="headacc"
		href="status.xhtml?status=ac" paramId="pid" paramProperty="pid" />
	<display:column property="accp" titleKey="tablehdr.accpercent"
		sortable="true" sortProperty="accp" sortName="accp"
		headerClass="headpercent" />
	<display:column property="points" titleKey="tablehdr.score"
		sortable="true" sortProperty="accu" sortName="accu"
		headerClass="headpoint" />
	<display:column titleKey="tablehdr.actions">
		<a href="/admin/manageproblemclassification.xhtml?pid=${problem.pid}"
			title="<fmt:message key="messages.general.go"/>"><i
			class="fa fa-edit"></i></a>
	</display:column>
</display:table>