<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<h2 class="postheader">
    <a href="<c:url  value="course.xhtml?course_id=${course.course_id}"/>">${course.name}</a>
</h2>
<div class="postcontent">
    <!-- article-content -->
    <table class="navigating" width="100%">
        <tr>
            <authz:authorize ifNotGranted="ROLE_ANONYMOUS">
                <td width="10%"><a href="coursesubmit.xhtml?pid=${problem.pid}&course_id=${course.course_id}"><spring:message code="link.submit"/></a></td>
                <td width="10%"><a href="<c:url value="coursestatus.xhtml?pid=${problem.pid}&username="/><authz:authentication property="principal.username" />"><spring:message code="link.mysubmissions"/></a></td>                
            </authz:authorize>           
            <td width="10%"><a href="/24h/problempdf.xhtml?pid=<c:url value="${problem.pid}"/>">PDF</a></td>
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
    <br/>
    <table class="pinfo">
        <tbody>
            <tr><td><spring:message code="fieldhdr.createdby"/></td><td><c:out value="${problem.author}"/></td></tr>
            <tr><td width="25%"><spring:message code="fieldhdr.addedby"/></td><td><a href="<c:url value="/user/useraccount.xhtml?username=${problem.username}"/>"><c:out value="${problem.username}"/></a></td></tr>
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
            <tr>
                <td><spring:message code="fieldhdr.enabledlanguages"/></td>
                <td>
                    <c:forEach items="${problem.languages}" var="language" varStatus="status">
                        <c:if test="${status.count ne 1}" ><c:out value="|"/></c:if>
                        ${language.language}
                    </c:forEach>
                </td>
            </tr> 
            <c:if test="${view_pinfo}">
                <authz:authorize ifAllGranted="ROLE_USER">

                    <tr>
                        <td><spring:message code="fieldhdr.classif"/></td>
                        <td>                            
                            <c:forEach items="${classifications}" var="classification" varStatus="status">
                                <c:if test="${status.count ne 1}" ><c:out value="|"/></c:if>
                                <c:out value="${classification.name} ${classification.complexity}"/> 
                            </c:forEach>

                        </td>
                    </tr> 

                </authz:authorize>
            </c:if>
        </tbody>
    </table>
</div>

