<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer="16kb" autoFlush="true"%>
<display:table id="contest" name="contests" class="volume" requestURI=""
               decorator="cu.uci.coj.utils.tabledecorator.problemsTableDecorator">
    <display:column property="name" titleKey="tablehdr.title" />
    <display:column titleKey="tablehdr.start" headerClass="headdate">
        <fmt:formatDate value="${contest.initdate}"
                        pattern="yyyy-MM-dd HH:mm:ss" />
    </display:column>
    <display:column titleKey="tablehdr.end" headerClass="headdate">
        <fmt:formatDate value="${contest.enddate}"
                        pattern="yyyy-MM-dd HH:mm:ss" />
    </display:column>
    <display:column titleKey="tablehdr.access">
        <c:choose>
            <c:when test="${contest.registration eq 2}">
                <a>                   
                    <i data-toggle="tooltip" class="fa fa-lock fa-lg"
                       title="<spring:message code="titval.private"/>">
                    </i>
                </a>
            </c:when>
            <c:otherwise>
                <a >
                   
                    <i data-toggle="tooltip" class="fa fa-unlock"
                       title="<spring:message code="titval.public"/>">
                    </i>
                </a>
                </c:otherwise>
            </c:choose>
        </display:column>
        <display:column titleKey="tablehdr.enabled">
            <c:choose>
                <c:when test="${contest.enabled == true}">
                <span class="label label-success"><spring:message code="fieldval.yes" /></span>
            </c:when>
            <c:otherwise>
                <span class="label label-danger"><spring:message
                        code="fieldval.no" /></span>
                </c:otherwise>
            </c:choose>
        </display:column>
        <display:column titleKey="tablehdr.status">
            <c:choose>
                <c:when test="${contest.running == true}">
                <span class="label label-success"><spring:message
                        code="fieldval.running" /></span>
                </c:when>
                <c:when test="${contest.coming == true}">
                <span class="label label-danger"><spring:message
                        code="fieldval.coming" /></span>
                </c:when>
                <c:when test="${contest.past == true}">
                <span class="label label-default"><spring:message code="fieldval.past" /></span>
            </c:when>
        </c:choose>
    </display:column>
    <display:column titleKey="tablehdr.actions">
        <a
            href="<c:url value="/admin/managesubmissions.xhtml?cid=${contest.cid}"/>">
            <img
                src="<c:url value="/images/rejudge.png"/>"
                alt="<spring:message code="titval.rejudge"/>"
                data-toggle="tooltip"
                title="<spring:message code="titval.rejudge"/>"
                />            
        </a>
        <a href="<c:url value="/admin/repoint.xhtml?cid=${contest.cid}"/>"><img
                src="<c:url value="/images/repoint.png"/>"
                alt="<spring:message code="altval.repoint"/>"
                data-toggle="tooltip"
                title="<spring:message code="titval.repoint"/>" /></a>
            <c:if test="${contest.past == true && contest.blocked == false}">
            <a href="<c:url value="/admin/lock.xhtml?cid=${contest.cid}"/>"
               ><i data-toggle="tooltip" class="fa fa-eye-slash"
                title="<spring:message code="titval.freeze"/>">
                </i></a>
            </c:if>
            <c:if test="${contest.past == true && contest.blocked == true}">
            <a href="<c:url value="/admin/unlock.xhtml?cid=${contest.cid}" />"
               ><i data-toggle="tooltip" class="fa fa-eye"
                title="<spring:message code="titval.unfreeze"/>">
                </i></a>
            </c:if>

        <a
            href="<c:url value="/admin/managecontest.xhtml?cid=${contest.cid}"/>"
            ><i data-toggle="tooltip" class="fa fa-edit"
            title="<spring:message code="titval.edit"/>">
            </i></a>
        </display:column>
    </display:table>

<script>
    $("[data-toggle='tooltip']").tooltip();
</script>