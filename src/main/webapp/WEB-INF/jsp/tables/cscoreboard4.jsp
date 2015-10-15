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
		<img src="<c:url value="/images/countries/${user.country}"/>.png"
			title="<c:url value="${user.country_desc}"/>"
			alt="<c:url value="${user.country}"/>" />
	</display:column>
	<display:column titleKey="tablehdr.institution"
		headerClass="headcountry">
		<img class="school" src="<c:url value="/images/school/${user.institution}"/>.png"
			title="<c:out value="${user.institution_desc}"/>"
			alt="<c:url value="${user.institution}"/>" />
	</display:column>
	<display:column titleKey="tablehdr.contestant">
		<a title="<c:out value="${user.username}"/>"
			href="<c:url value="cuseraccount.xhtml?uid=${user.username}&cid=${contest.cid}"/>">
			<c:out value="${user.nick}" />
		</a>
		<i><sup><spring:message code="tableval.level" />
				${user.level}</sup></i>

		<c:choose>
			<c:when test="${user.online == true}">
				<sup><a alt="logged"
					title="<spring:message code="altval.logged"/>"><i
						class="fa fa-plug"></i></a></sup>
			</c:when>
		</c:choose>
	</display:column>
	<display:column property="total" titleKey="tablehdr.sub"
		sortable="true" sortProperty="sub" sortName="sub"
		headerClass="headsub" href="cstatus.xhtml?cid=${contest.cid}"
		paramId="username" paramProperty="username" />
	<display:column property="accu" titleKey="tablehdr.ac" sortable="true"
		sortProperty="acc" sortName="accu" headerClass="headacc"
		href="cstatus.xhtml?status=ac&cid=${contest.cid}" paramId="username"
		paramProperty="username" />
	<display:column property="percent" titleKey="tablehdr.accpercent"
		sortable="true" sortProperty="accp" sortName="percent"
		headerClass="headpercent" />
	<display:column property="points" titleKey="tablehdr.score"
		sortable="true" sortProperty="points" sortName="score"
		headerClass="headpoint" />
</display:table>