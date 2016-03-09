<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page buffer="16kb" autoFlush="true" %>
<!--<label><spring:message code="fieldhdr.totalfound"/>: ${found}</label>-->
<display:table id="log" name="logs" class="volume" requestURI=""
               decorator="cu.uci.coj.utils.tabledecorator.problemsTableDecorator">
    <display:column property="id" headerClass="headdate"
                    titleKey="tablehdr.id"/>
    <display:column property="username" titleKey="tablehdr.user"/>
    <display:column property="log" titleKey="tablehdr.content"/>
    <display:column headerClass="headdate"
                    titleKey="tablehdr.date">
        <c:set var="newdate" value="${fn:substring(log.date, 0, 19)}"/>
        ${newdate}
    </display:column>
</display:table>