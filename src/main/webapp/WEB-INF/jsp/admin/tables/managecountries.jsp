<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer="16kb" autoFlush="true"%>
<label><fmt:message key="fieldhdr.totalfound" />: ${found}</label>
<display:table id="country" name="countries" class="volume"
	decorator="cu.uci.coj.utils.tabledecorator.problemsTableDecorator">
	<display:column property="id" titleKey="tablehdr.id"
		headerClass="headid" />
	<display:column property="zip" titleKey="tablehdr.zip" />
	<display:column property="name" titleKey="tablehdr.name"
		href="/24h/userscountryrank.xhtml" paramId="country_id"
		paramProperty="id" />
	<display:column titleKey="tablehdr.enabled">
		<c:choose>
			<c:when test="${country.enabled == true}">
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
			href="<c:url value="/admin/managecountry.xhtml?country_id=${country.id}"/>"
			title="<fmt:message key="messages.general.go"/>"><i
			class="fa fa-edit"></i></a>
	</display:column>
</display:table>
