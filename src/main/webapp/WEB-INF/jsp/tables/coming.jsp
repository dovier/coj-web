<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer="16kb" autoFlush="true"%>
<display:table id="contest" name="contests" class="volume" requestURI="" defaultsort="0" defaultorder="ascending" decorator="cu.uci.coj.utils.tabledecorator.problemsTableDecorator">
        <display:column property="cid" titleKey="tablehdr.id" headerClass="headid"/>
        <display:column titleKey="tablehdr.access">
            <c:choose>
                <c:when test="${contest.registration eq 2}">
                	<a title="<spring:message code="altval.private"/>"><i class="fa fa-lock fa-lg"></i></a>
                </c:when>
                <c:otherwise>
                    <a title="<spring:message code="altval.open"/>"><i class="fa fa-unlock fa-lg"></i></a>
                </c:otherwise>
            </c:choose>
        </display:column>
        <display:column property="name" titleKey="tablehdr.name"  href="contestview.xhtml" paramId="cid" paramProperty="cid" style="text-transform:none" />
        <display:column titleKey="tablehdr.start" class="date">
            <a href="http://timeanddate.com/worldclock/fixedtime.html?day=${contest.initdate.date}&month=${contest.initdate.month + 1}&year=${contest.initdate.year + 1900}&hour=${contest.initdate.hours}&min=${contest.initdate.minutes}&sec=${contest.initdate.seconds}&p1=99" target="new">
                <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${contest.initdate}" />                
            </a>
        </display:column>
        <display:column titleKey="tablehdr.end" class="date">
            <a href="http://timeanddate.com/worldclock/fixedtime.html?day=${contest.enddate.date}&month=${contest.enddate.month +1}&year=${contest.enddate.year + 1900}&hour=${contest.enddate.hours}&min=${contest.enddate.minutes}&sec=${contest.enddate.seconds}&p1=99" target="new">
                <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${contest.enddate}" />                
            </a>
        </display:column>
    </display:table>