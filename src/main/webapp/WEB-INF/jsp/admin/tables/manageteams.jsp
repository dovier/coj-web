<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer="16kb" autoFlush="true"%>
<c:choose>
	<c:when test="${search == true}">
		<label><fmt:message key="fieldhdr.totalfound" />: ${found}</label>
	</c:when>
</c:choose>
<br />
<display:table id="user" name="users" class="volume" requestURI=""
	decorator="cu.uci.coj.utils.tabledecorator.problemsTableDecorator">
	<display:column property="nick" titleKey="tablehdr.user"
		href="/user/useraccount.xhtml" paramId="username"
		paramProperty="username" />
	<display:column property="rgdate" titleKey="tablehdr.date"
		headerClass="headdate" />
	<display:column titleKey="tablehdr.connected">
		<c:choose>
			<c:when test="${user.online == true}">
				<span class="label label-success"><fmt:message
						key="page.general.yes" /></span>
			</c:when>
			<c:otherwise>
				<span class="label label-danger"><fmt:message
						key="page.general.no" /></span>
			</c:otherwise>
		</c:choose>
	</display:column>
	<display:column property="lastip" titleKey="tablehdr.ip" />
	<display:column titleKey="tablehdr.enabled">
		<c:choose>
			<c:when test="${user.enabled == true}">
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
			href="<c:url value="/admin/manageuser.xhtml?username=${user.username}" />"
			title="<fmt:message key="messages.general.go"/>"><i
			class="fa fa-edit"></i></a>
	</display:column>
</display:table>