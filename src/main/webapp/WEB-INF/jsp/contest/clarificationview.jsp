<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--link rel="stylesheet" href="../Style/style.css" type="text/css" media="screen" /-->

<h2 class="postheader" style="clear: both">
    <a class="linkheader" href="<c:url value="contestview.xhtml?cid=${contest.cid}"/>" />${contest.name}</a>
<br/>
<spring:message code="pagehdr.clarification"/>
</h2>
<div class="postcontent">
    <c:choose>
        <c:when test="${available == true}">
            <c:if test="${contest.running == true}">
                <authz:authorize ifAnyGranted="ROLE_ADMIN,ROLE_SUPER_PSETTER,ROLE_PSETTER">
                    <a href="<c:url value="sendclarification.xhtml?cid=${contest.cid}&ccid=${clarification.id}&uid=${clarification.username}&pid=${clarification.pid}"/>"><i
										class="fa fa-mail-reply"></i>&nbsp;<spring:message code="link.reply" /></a>
                    </authz:authorize>
                </c:if>
            <a href="<c:url value="myclarifications.xhtml?cid=${contest.cid}"/>"><i
										class="fa fa-close"></i>&nbsp;<spring:message code="link.close"/></a>
            <c:if test="${clarification.isread == false}">
                <a href="<c:url value="markanswered.xhtml?cid=${contest.cid}&ccid=${clarification.id}"/>"><i class="fa fa-check-circle"></i>&nbsp;<spring:message code="link.markanswered"/></a>
                </c:if>
            <fieldset>
                <table class="msgheader">
                    <tr>
                        <td style="align:right" width="10%"><b><spring:message code="fieldhdr.from"/>:</b></td>
                        <td style="padding-left: 20px;" style="align:left">${clarification.username}</td>
                    </tr>
                    <tr>
                        <td style="align:right" width="10%"><b><spring:message code="fieldhdr.received"/>:</b></td>
                        <td style="padding-left: 20px;" style="align:left">${clarification.date}</td>
                    </tr>
                    <tr>
                        <td style="align:right" width="10%">
                            <b><spring:message code="fieldhdr.subject"/>:</b>
                        </td>
                        <td style="padding-left: 20px;" style="align:left">
                            <c:choose>
                                <c:when test="${clarification.pid eq 0}">
                                    General
                                </c:when>
                                <c:otherwise>
                                    <a href="<c:url value="cproblem.xhtml?cid=${contest.cid}&pid=${clarification.pid}" />">${clarification.ptitle}</a>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                    <!--tr>
                        <td style="align:right" width="10%"><b><spring:message code="fieldhdr.subject"/>:</b></td>
                        <td style="padding-left: 20px;" style="align:left">${clarification.title}</td>
                    </tr-->
                </table>
                <hr>
                ${clarification.description}
                <hr>
            </fieldset>            
        </c:when>
        <c:otherwise>
            <center><span class="label label-danger">The resource is not available or you are not allow to see it</span></center>
            </c:otherwise>
        </c:choose>
</div>
