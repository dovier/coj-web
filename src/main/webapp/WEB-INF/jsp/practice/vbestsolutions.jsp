<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h2 class="postheader">
    <fmt:message key="page.general.header.vcontest"/>
    <br/>
    <center> <fmt:message key="page.24h.bsolutions.header"/></center>
    <a href="<c:url value="vproblem.xhtml?pid=${problem.pid}"/>"> ${problem.title}</a>
</h2>
<div class="postcontent">
    <c:choose>
        <c:when test="${exist == true}">
            <table class="navigating" align:center;width:100%">
                <tr>
                    <c:if test="${not empty nfirst.active}">
                        <td style="align:right" width = "15%">
                            <c:choose>
                                <c:when test="${nfirst.active==true}">
                                    <a href="vbestsolutions.xhtml?start=1&pid=${problem.pid}" ><i>&lt;&lt;</i></a>
                                    <a href="<c:url value="vbestsolutions.xhtml?start=${nfirst.value}&pid=${problem.pid}"/>"><i><fmt:message key="page.navigating.previous"/></i></a>
                                </c:when>
                                <c:when test="${nfirst.active==false}">
                                    <fmt:message key="page.navigating.previous"/>
                                </c:when>
                            </c:choose>
                        </td>
                    </c:if>

                    <c:forEach items="${links}" var="navigating">
                        <td width = "4%">
                            <c:if test="${not empty navigating.active}">
                                <c:choose>
                                    <c:when test="${navigating.active==true}">
                                        <a href="<c:url value="vbestsolutions.xhtml?start=${navigating.value}&pid=${problem.pid}"/>" ><c:out value="${navigating.value}"/></a>
                                    </c:when>
                                    <c:when test="${navigating.active==false}">
                                        <c:out value="${navigating.value}"/>
                                    </c:when>
                                </c:choose>
                            </c:if>
                        </td>
                    </c:forEach>
                    <c:if test="${not empty nend.active}">
                        <td style="align:left" width = "15%">
                            <c:choose>
                                <c:when test="${nend.active==true}">
                                    <a href="<c:url value="vbestsolutions.xhtml?start=${nend.value}&pid=${problem.pid}"/>" ><i><fmt:message key="page.navigating.next"/></i></a>
                                    <a href="<c:url value="vbestsolutions.xhtml?start=${end.value}&pid=${problem.pid}"/>" ><i>&gt;&gt;</i></a>
                                </c:when>
                                <c:when test="${nend.active==false}">
                                    <fmt:message key="page.navigating.next"/>
                                </c:when>
                            </c:choose>
                        </td>
                    </c:if>
                </tr>
            </table>
            <table class="volume" border="1px">

                <thead>
                <th>ID</th>
                <th><fmt:message key="status.table.date"/></th>
                <th class="user"><fmt:message key="status.table.user"/></th>
                <th class="user">ACC</th>
                <th class="result"><fmt:message key="status.table.result"/></th>
                <th><fmt:message key="status.table.time"/></th>
                <th><fmt:message key="status.table.memory"/></th>
                <th><fmt:message key="status.table.source"/></th>
                <th><fmt:message key="status.table.lang"/></th>
                </thead>

                <c:forEach items="${submissions}" var="submission">
                    <tr>
                        <c:if test="${not empty submission.authorize}">
                            <td>
                                <c:choose>
                                    <c:when test="${submission.authorize==true}">
                                        <a href="<c:url value="vsubmission.xhtml?id=${submission.sid}"/>" >${submission.sid}</a>
                                    </c:when>
                                    <c:when test="${submission.authorize==false}">
                                        <c:out value="${submission.sid}"/>
                                    </c:when>
                                </c:choose>
                            </td>
                        </c:if>
                        <td class="date"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${submission.date}" /></td>
                        <td><a title="${submission.username}" href="<c:url value="cuseraccount.xhtml?uid=${submission.username}"/>"><c:out value="${submission.userNick}"/></a></td>
                        <td><a href="vstatus.xhtml?username=${submission.username}&pid=${problem.pid}&status=ac" title="<fmt:message key="page.24h.bsolutions.totalacc"/>"><c:out value="${submission.acTestCases}"/></a></td>
                        <td width="15%" class="<c:out value="sub${submission.statusClass}"/>" id="<c:out value="${submission.statusId}"/>" name="<c:out value="${submission.statusName}"/>"><c:out value="${submission.status}"/></td>
                        <td><c:out value="${submission.timeUsed}"/></td>
                        <td><c:out value="${submission.memoryMB}"/></td>
                        <td><c:out value="${submission.fontMB}"/></td>
                        <td><c:out value="${submission.lang}"/></td>
                    </tr>
                </c:forEach>
            </table>
            <table class="navigating" align:center;width:100%">
                <tr>
                    <c:if test="${not empty nfirst.active}">
                        <td style="align:right" width = "15%">
                            <c:choose>
                                <c:when test="${nfirst.active==true}">
                                    <a href="vbestsolutions.xhtml?start=1&pid=${problem.pid}" ><i>&lt;&lt;</i></a>
                                    <a href="<c:url value="vbestsolutions.xhtml?start=${nfirst.value}&pid=${problem.pid}"/>"><i><fmt:message key="page.navigating.previous"/></i></a>
                                </c:when>
                                <c:when test="${nfirst.active==false}">
                                    <fmt:message key="page.navigating.previous"/>
                                </c:when>
                            </c:choose>
                        </td>
                    </c:if>


                    <c:forEach items="${links}" var="navigating">
                        <td width = "4%">
                            <c:if test="${not empty navigating.active}">
                                <c:choose>
                                    <c:when test="${navigating.active==true}">
                                        <a href="<c:url value="vbestsolutions.xhtml?start=${navigating.value}&pid=${problem.pid}"/>" ><c:out value="${navigating.value}"/></a>
                                    </c:when>
                                    <c:when test="${navigating.active==false}">
                                        <c:out value="${navigating.value}"/>
                                    </c:when>
                                </c:choose>
                            </c:if>
                        </td>
                    </c:forEach>
                    <c:if test="${not empty nend.active}">
                        <td style="align:left" width = "15%">
                            <c:choose>
                                <c:when test="${nend.active==true}">
                                    <a href="<c:url value="vbestsolutions.xhtml?start=${nend.value}&pid=${problem.pid}"/>" ><i><fmt:message key="page.navigating.next"/></i></a>
                                    <a href="<c:url value="vbestsolutions.xhtml?start=${end.value}&pid=${problem.pid}"/>" ><i>&gt;&gt;</i></a>
                                </c:when>
                                <c:when test="${nend.active==false}">
                                    <fmt:message key="page.navigating.next"/>
                                </c:when>
                            </c:choose>
                        </td>
                    </c:if>
                </tr>
            </table>
        </c:when>
        <c:otherwise>
            <br/>
            <span class="label label-danger"><center><fmt:message key="page.row.error"/></center></span>
        </c:otherwise>
    </c:choose>

</div>