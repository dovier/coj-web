<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer = "16kb" autoFlush="true" %>
 <label><spring:message code="fieldhdr.totalfound" />:
                ${found}</label>
<display:table id="institution" name="institutions" class="volume" requestURI="" defaultsort="0" defaultorder="ascending" decorator="cu.uci.coj.utils.tabledecorator.institutionsrankTableDecorator">
        <display:column titleKey="tablehdr.rank" headerClass="headrk">
            <c:choose>
                <c:when test="${institution.hasmedal == true}">
                    <img src="/images/<c:url value="${institution.medal}"/>" alt="${institution.rank}" title="<spring:message code="titval.${institution.medal}"/>"/>
                </c:when>
                <c:otherwise>
                    <c:url value="${institution.rank}"/>
                </c:otherwise>
            </c:choose>
        </display:column>
        <display:column titleKey="tablehdr.country" headerClass="headcountry" >
            <img src="/images/countries/<c:url value="${institution.czip}"/>.png" title="<c:url value="${institution.cname}"/>" alt="<c:url value="${institution.czip}"/>"/>
        </display:column>
        <display:column property="name" titleKey="tablehdr.institution" style="text-transform:none" href="/profile/institution.xhtml" paramId="inst_id" paramProperty="id"/>
        <display:column property="users" titleKey="tablehdr.users" sortable="true" sortProperty="usr" sortName="usr" headerClass="headusers" href="usersinstitutionrank.xhtml" paramId="inst_id" paramProperty="id"/>
        <display:column property="acc" titleKey="tablehdr.ac" sortable="true" sortProperty="acc" sortName="acc" headerClass="headacc"/>
        <display:column property="points" titleKey="tablehdr.score" sortable="true" sortProperty="points" sortName="score" headerClass="headpoint"/>
    </display:table>