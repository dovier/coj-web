<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer="16kb" autoFlush="true"%>


<display:table id="site" name="sites" class="volume" requestURI=""
               defaultsort="0" defaultorder="ascending">
    <display:column property="site" titleKey="tablehdr.site"
                    sortable="true" sortProperty="site" sortName="site" autolink="true"
                    headerClass="headfirst" href="details.xhtml" paramId="sid"
                    paramProperty="sid" />
    <display:column titleKey="tablehdr.completed" headerClass="headedit">
        <c:choose>
            <c:when test="${site.completed}">
                <span class="label label-success"><spring:message
                        code="page.general.yes" /></span>
                </c:when>
                <c:otherwise>
                <span class="label label-danger"> <spring:message
                        code="page.general.no" /></span>
                </c:otherwise>
            </c:choose>
        </display:column>
        <display:column titleKey="tablehdr.enabled" headerClass="headedit">
            <c:choose>
                <c:when test="${site.enabled}">
                <span class="label label-success"><spring:message
                        code="page.general.yes" /></span>
                </c:when>
                <c:otherwise>
                <span class="label label-danger"> <spring:message
                        code="page.general.no" /></span>
                </c:otherwise>
            </c:choose>
        </display:column>
        <display:column titleKey="tablehdr.actions" headerClass="headedit">
        <a class="details"
           data-toggle="tooltip"
           href="<c:url value="/admin/wboard/site/details.xhtml?sid=${site.sid}"/>"
           title="<spring:message code="messages.general.details"/>"><i
                class="fa fa-eye"></i></a>
        <a class="edit"
           data-toggle="tooltip"
           href="<c:url value="/admin/wboard/site/edit.xhtml?sid=${site.sid}"/>"
           title="<spring:message code="messages.general.edit"/>"><i
                class="fa fa-edit"></i></a>
        <a href="#" onclick="confirm_delete('<c:url value="/admin/wboard/site/delete.xhtml?sid=${site.sid}"/>')">

            <i data-toggle="tooltip" class="fa fa-trash"
               title="<spring:message code="messages.general.delete"/>">
            </i>

        </a>
    </display:column>
</display:table>

<script>
    $("[data-toggle='tooltip']").tooltip();
</script>

<%@include file="/WEB-INF/jsp/general/confirmmessage.jsp"%>