<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer="16kb" autoFlush="true"%>
<display:table id="country" name="countries" class="volume" requestURI="" defaultsort="0" defaultorder="ascending" decorator="cu.uci.coj.utils.tabledecorator.countriesrankTableDecorator">
        <display:column titleKey="tablehdr.rank" headerClass="headrk">
            <c:choose>
                <c:when test="${country.hasmedal == true}">
                    <img  src="/images/<c:url value="${country.medal}"/>" alt="${country.rank}" title="<spring:message code="titval.${country.medal}"/>"/>
                </c:when>
                <c:otherwise>
                    <c:url value="${country.rank}"/>
                </c:otherwise>
            </c:choose>
        </display:column>
        <display:column titleKey="tablehdr.country" headerClass="headcountry" >
            <img src="/images/countries/<c:url value="${country.zip}"/>.png" title="<c:url value="${country.zip}"/>" alt="<c:url value="${country.name}"/>"/>
        </display:column>
        <display:column property="name" titleKey="tablehdr.country" style="text-transform:none" href="/profile/country.xhtml" paramId="country_id" paramProperty="id"/>
        <display:column property="institutions" titleKey="tablehdr.institution" sortable="true" sortProperty="inst" sortName="inst" headerClass="headschools" href="institutionsrankbycountry.xhtml" paramId="country_id" paramProperty="id"/>
        <display:column property="users" titleKey="tablehdr.users" sortable="true" sortProperty="usr" sortName="usr" headerClass="headusers" href="userscountryrank.xhtml" paramId="country_id" paramProperty="id"/>
        <display:column property="acc" titleKey="tablehdr.ac" sortable="true" sortProperty="acc" sortName="acc" headerClass="headacc"/>
        <display:column property="points" titleKey="tablehdr.score" sortable="true" sortProperty="points" sortName="score" headerClass="headpoint"/>
    </display:table>