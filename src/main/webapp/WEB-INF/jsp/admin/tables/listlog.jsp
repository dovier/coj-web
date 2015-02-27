<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer="16kb" autoFlush="true"%>
<c:choose>
        <c:when test="${search == true}">
            <label><spring:message code="fieldhdr.totalfound"/>: ${found}</label>
        </c:when>
    </c:choose>
<display:table id="log" name="logs" class="volume" requestURI="" decorator="cu.uci.coj.utils.tabledecorator.problemsTableDecorator">
        <display:column property="id" headerClass="headid" titleKey="tablehdr.id"/>
        <display:column property="username" titleKey="tablehdr.user"/>
        <display:column property="log" titleKey="tablehdr.content"/>
        <display:column property="date" headerClass="headdate" titleKey="tablehdr.date"/>
    </display:table>