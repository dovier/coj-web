<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h2 class="postheader">
    <spring:message code="pagehdr.profileof"/>
    <br/>
    <c:out value="${country.name}"/>
</h2>
<div class="postcontent">
    <fieldset>
        <legend><spring:message code="fieldhdr.generalinfo"/></legend>
        <table class="userinfo">
            <tr>
                <td width="170px"><spring:message code="fieldhdr.twolettercode"/>:</td> <td><c:out value="${country.zip_two}"/> </td>
            </tr>
            <tr>
                <td><spring:message code="fieldhdr.threelettercode"/>:</td> <td><c:out value="${country.zip}"/></td>
            </tr>
            <tr>
                <td><spring:message code="fieldhdr.flag"/>:</td><td> <img src="/images/countries/<c:url value="${country.zip}"/>.png" title="<c:url value="${country.name}"/>" alt="<c:url value="${country.zip}"/>"/> </td>
            </tr>
            <tr>
                <td><spring:message code="fieldhdr.website"/>:</td><td><a href="${country.website}">${country.website}</a></td>
            </tr>
        </table>
    </fieldset>
    <br/>
    <fieldset>
        <legend><spring:message code="fieldhdr.part24h"/></legend>
        <table class="userinfo">
            <tr>
                <td width="170px"><spring:message code="fieldhdr.institutions"/>:</td><td><a href="/24h/institutionsrankbycountry.xhtml?country_id=${country.id}"><c:out value="${country.institutions}"/> </a></td>
            </tr>
            <tr>
                <td><spring:message code="fieldhdr.users"/>:</td> <td><a href="/24h/userscountryrank.xhtml?country_id=${country.id}"><c:out value="${country.users}"/></a></td>
            </tr>
            <tr>
                <td><spring:message code="fieldhdr.score"/>:</td> <td> <fmt:formatNumber pattern="#.##" value="${country.points}"/> </td>
            </tr>
            <tr>
                <td><spring:message code="fieldhdr.rank"/>:</td><td><a href="/24h/countriesrank.xhtml">${country.rank}</a></td>
            </tr>            
        </table>
    </fieldset>
</div>
