<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h2 class="postheader" style="clear: both">
    <a class="linkheader" href="<c:url value="vcontestview.xhtml?cid=${contest.cid}"/>">${contest.name}</a>
    <br/>
    <spring:message code="pagehdr.problem"/>
</h2>
<div class="postcontent">
    <table class="navigating" width="100%">
        <tr>
            <authz:authorize ifNotGranted="ROLE_ANONYMOUS">
                <c:if test="${contest.running == true}">
                    <td width="10%"><a href="<c:url value="vsubmit.xhtml?pid=${problem.pid}"/>"><spring:message code="link.submit"/></a></td>
                </c:if>
                <td width="10%"><a href="<c:url value="vstatus.xhtml?pid=${problem.pid}&username="/><authz:authentication property="principal.username" />"><spring:message code="link.mysubmissions"/></a></td>
            </authz:authorize>
            <td width="10%"><a href="<c:url value="vstatus.xhtml?pid=${problem.pid}"/>"><spring:message code="link.submissions"/></a></td>                        
        </tr>
    </table>    
    <h3><center><label class="ex"><b><c:out value=" ${problem.pid}"/> - <c:out value="${problem.title}"/></b></label></center></h3>
    <h4><spring:message code="fieldhdr.description"/></h4>
    <p class="ex"><c:url value="${problem.description}"/></p>
    <h4><spring:message code="fieldhdr.inputspec"/></h4>
    <p class="ex"><c:url value="${problem.input}"/></p>
    <h4><spring:message code="fieldhdr.outputspec"/></h4>
    <p class="ex"><c:url value="${problem.output}"/></p>
    <h4><spring:message code="fieldhdr.sampleinput"/></h4>
    <pre class="ex dataset"><c:url value="${problem.inputex}"/></pre>
    <h4><spring:message code="fieldhdr.sampleoutput"/></h4>
    <pre class="ex dataset"><c:url value="${problem.outputex}"/></pre>
    <h4><spring:message code="fieldhdr.hint"/></h4>
    <p><c:url value="${problem.comments}"/></p>

    <table class="pinfo">
        <tbody>
            <tr><td><spring:message code="fieldhdr.createdby"/></td><td><c:out value="${problem.author}"/></td></tr>
            <tr><td width="25%"><spring:message code="fieldhdr.addedby"/></td><td><a href="<c:url value="useraccount.xhtml?uid=${problem.username}"/>"><c:out value="${problem.username}"/></a></td></tr>
            <tr><td><spring:message code="fieldhdr.additiondate"/></td><td><c:out value="${problem.date}"/></td></tr>
            <tr><td><spring:message code="fieldhdr.timelimit"/> </td><td><c:out value="${problem.time}"/></td></tr>
            <c:choose>
                <c:when test="${problem.multidata}">
                    <tr><td><span class="label label-danger"><spring:message code="fieldhdr.testlimit"/> </span></td><td><c:out value="${problem.casetimelimit}"/></td></tr>
                </c:when>
            </c:choose>
            <tr><td><spring:message code="fieldhdr.memorylimit"/> </td><td><c:out value="${problem.memory}"/></td></tr>
            <tr><td><spring:message code="fieldhdr.outputlimit"/> </td><td>64</td></tr>
            <tr><td><spring:message code="fieldhdr.sizelimit"/> </td><td><c:out value="${problem.fontsize}"/></td></tr>            
        </tbody>
    </table>    
</div>