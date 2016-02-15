<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page buffer="16kb" autoFlush="true" %>
<display:table id="problem" name="problems" class="volume" requestURI="" defaultsort="0" defaultorder="ascending"
               decorator="cu.uci.coj.utils.tabledecorator.problemsTableDecorator">
    <display:column titleKey="tablehdr.id" class="headidp">
        <c:choose>
            <c:when test="${problem.favorite == true}">
                ${problem.pid} <a href="unmarkfavorite.xhtml?pid=${problem.pid}" data-toggle="tooltip"
                                  title="<spring:message code="titval.unmarkfavorite"/>"><i class="gold fa fa-star"></i></a>
            </c:when>
            <c:otherwise>
                ${problem.pid} <authz:authorize ifAllGranted="ROLE_USER">
                <a href="markasfavorite.xhtml?pid=${problem.pid}" data-toggle="tooltip"
                   title="<spring:message code="titval.markasfavorite"/>"><i class="gold fa fa-star-o"></i></a>
            </authz:authorize>
            </c:otherwise>
        </c:choose>
    </display:column>
    <authz:authorize ifNotGranted="ROLE_ANONYMOUS">
        <display:column titleKey="tablehdr.status">
            <c:choose>
                <c:when test="${problem.solved == true}">
                    <i class="green fa fa-check-circle" data-toggle="tooltip"
                       title="<spring:message code="problem.solved"/>"></i>
                </c:when>
                <c:when test="${problem.unsolved == true}">
                    <i class="red fa fa-minus-circle" data-toggle="tooltip"
                       title="<spring:message code="problem.attempted"/>"></i>
                </c:when>
            </c:choose>
        </display:column>
    </authz:authorize>
    <display:column titleKey="tablehdr.title" style="text-transform:none">
        <a href="problem.xhtml?pid=${problem.pid}">${problem.title}</a>
    </display:column>
    <display:column property="submitions" titleKey="tablehdr.sub" sortable="true" sortProperty="submitions"
                    sortName="total" autolink="true" headerClass="headsub" href="status.xhtml" paramId="pid"
                    paramProperty="pid"/>
    <display:column property="ac" titleKey="tablehdr.ac" sortable="true" sortProperty="ac" sortName="acc"
                    headerClass="headacc" href="status.xhtml?status=ac" paramId="pid" paramProperty="pid"/>
    <display:column property="accp" titleKey="tablehdr.accpercent" sortable="true" sortProperty="accp" sortName="accp"
                    headerClass="headpercent"/>
    <display:column property="points" titleKey="tablehdr.score" sortable="true" sortProperty="accu" sortName="accu"
                    headerClass="headpoint"/>
</display:table>
<script>
    $("[data-toggle='tooltip']").tooltip();
</script>