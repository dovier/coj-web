<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h2 class="postheader">
    <spring:message code="pagehdr.profileof"/> 
    <br/>
    <c:out value="${institution.name}"/>
</h2>
<div class="postcontent">
    <fieldset>
        <legend><spring:message code="fieldhdr.generalinfo"/></legend>
        <table class="userinfo">
            <tr>
                <td width="170px"><spring:message code="fieldhdr.code"/>:</td> <td><c:out value="${institution.zip}"/> </td>
            </tr>
            <tr>
                <td><spring:message code="fieldhdr.logo"/>:</td> <td><img src="/images/school/<c:url value="${institution.zip}"/>.png" title="<c:url value="${institution.name}"/>" alt="<c:url value="${institution.zip}"/>"/></td>
            </tr>
            <tr>
                <td><spring:message code="fieldhdr.website"/>:</td><td><a href="${institution.website}">${institution.website}</a></td>
            </tr>
            <tr>
                <td><spring:message code="fieldhdr.country"/>:</td><td><a href="country.xhtml?country_id=${country.id}">${country.name}</a></td>
            </tr>
        </table>
    </fieldset>

    <br/>

    <fieldset>
        <legend><spring:message code="fieldhdr.part24h"/></legend>
        <table class="userinfo">
            <tr>
                <td width="170px"><spring:message code="fieldhdr.users"/>:</td> <td><a href="/24h/usersinstitutionrank.xhtml?inst_id=${institution.id}"><c:out value="${institution.count}"/></a></td>
            </tr>
            <tr>
                <td><spring:message code="fieldhdr.score"/>:</td> <td> <fmt:formatNumber pattern="#.##" value="${institution.points}"/> </td>
            </tr>
            <tr>
                <td><spring:message code="fieldhdr.rank"/>:</td><td><a href="/24h/institutionsrank.xhtml"><c:out value="${institution.rank}"/></a></td>
            </tr>
            <tr>
                <td><spring:message code="fieldhdr.rankcountry"/>:</td><td><a href="/24h/institutionsrankbycountry.xhtml?country_id=${country.id}">${institution.rankincountry}</a></td>
            </tr>
        </table>
    </fieldset>
</div>
