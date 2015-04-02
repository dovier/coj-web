<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer="16kb" autoFlush="true"%>

<display:table id="user" name="users" class="volume" requestURI=""
	defaultsort="0" defaultorder="ascending"
	decorator="cu.uci.coj.utils.tabledecorator.usersrankTableDecorator">
	<display:column titleKey="tablehdr.rank" headerClass="headrk">
		<c:url value="${user.rank}" />
	</display:column>
	<display:column titleKey="tablehdr.country" headerClass="headcountry">
		<img src="/images/countries/<c:url value="${user.country}"/>.png"
			title="<c:url value="${user.country_desc}"/>"
			alt="<c:url value="${user.country}"/>" />
	</display:column>
	<display:column titleKey="tablehdr.contestant"
		style="text-transform:none">
		<a title="<c:out value="${user.nick}"/>"
			href="<c:url value="/user/useraccount.xhtml?username=${user.username}"/>">
			<c:out value="${user.username}" />
		</a>
		<c:choose>
			<c:when test="${user.online == true and online == false}">
				<sup><a alt="logged"
					title="<spring:message code="altval.logged"/>"><i
						class="fa fa-plug"></i></a></sup>
			</c:when>
		</c:choose>
	</display:column>
	<display:column property="contests" titleKey="tablehdr.contests"
		sortable="true" sortProperty="contests" headerClass="headcontests"
		paramProperty="contests" />
	<display:column property="total" titleKey="tablehdr.sub"
		sortable="true" sortProperty="sub" sortName="sub"
		headerClass="headsub" />
	<display:column property="accu" titleKey="tablehdr.ac" sortable="true"
		sortProperty="acc" sortName="accu" headerClass="headacc" />
	<display:column property="percent" titleKey="tablehdr.accpercent"
		headerClass="headpercent" />
</display:table>