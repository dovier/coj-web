<%--
  Created by IntelliJ IDEA.
  User: yunier
  Date: 4/03/16
  Time: 16:39
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page buffer="16kb" autoFlush="true" %>

<h2 class="postheader"><spring:message code="text.tools.6"/></h2>

<div class="postcontent">
    <div class="col-xs-12">
        <div class="col-xs-4">
            <h3>General</h3>
            <ul class="list-unstyled">
                <li><a href="<c:url value="/index.xhtml"/>"><span class="t"><i
                        class="fa fa-home"></i>&nbsp;<fmt:message key="link.home"/></span></a></li>
                <li><a href="<c:url value="/general/downloads.xhtml"/>"><span
                        class="t"><i class="fa fa-download"></i>&nbsp;<fmt:message
                        key="link.downloads"/></span></a></li>
                <li><a href="<c:url value="/general/tools.xhtml"/>"><span
                        class="t"><i class="fa fa-wrench"></i>&nbsp;<fmt:message
                        key="link.tools"/></span></a></li>
                <li><a href="http://coj-forum.uci.cu" target="new"><span
                        class="t"><i class="fa fa-comments"></i>&nbsp;<fmt:message
                        key="link.forum"/></span></a></li>
                <li><a href="<c:url value="/general/faqs.xhtml"/>"><span
                        class="t"><i class="fa fa-question-circle"></i>&nbsp;<fmt:message
                        key="link.faq"/></span></a></li>
                <li><a href="<c:url value="/general/links.xhtml"/>"><span
                        class="t"><i class="fa fa-link"></i>&nbsp;<fmt:message
                        key="link.links"/></span></a></li>
                <li><a href="<c:url value="/general/about.xhtml"/>"><span
                        class="t"><i class="fa fa-info-circle"></i>&nbsp;<fmt:message
                        key="link.about"/></span></a></li>
                <li><a href="<c:url value="/general/contact.xhtml"/>"><span
                        class="t"><i class="fa fa-envelope"></i>&nbsp;<fmt:message
                        key="link.contactus"/></span></a></li>
            </ul>

        </div>
        <div class="col-xs-4">
            <h3>
                <spring:message code="block.24h"/>
            </h3>
            <ul class="list-unstyled">
                <li><a href="<c:url value="/24h/problems.xhtml" />"><i
                        class="fa fa-list"></i> <spring:message code="link.problems"/></a></li>
                <authz:authorize ifAnyGranted="ROLE_USER">
                    <li><a href="<c:url value="/24h/submit.xhtml" />"><i
                            class="fa fa-file-code-o"></i>&nbsp;<spring:message
                            code="link.submit"/></a></li>
                </authz:authorize>
                <li><a href="<c:url value="/24h/status.xhtml" />"><i
                        class="fa fa-legal"></i>&nbsp;<spring:message
                        code="link.judgments"/></a></li>
                <li><a href="<c:url value="/24h/usersrank.xhtml" />"><i
                        class="fa fa-sort-numeric-asc"></i>&nbsp;<spring:message
                        code="link.standings"/></a>
                    <ul class="list-unstyled">
                        <li><a href="<c:url value="/24h/usersrank.xhtml" />"><i
                                class="fa fa-users"></i>&nbsp;<spring:message
                                code="link.users"/></a></li>
                        <li><a href="<c:url value="/24h/institutionsrank.xhtml" />"><i
                                class="fa fa-institution"></i>&nbsp;<spring:message
                                code="link.institutions"/></a></li>
                        <li><a href="<c:url value="/24h/countriesrank.xhtml" />"><i
                                class="fa fa-globe"></i>&nbsp;<spring:message
                                code="link.countries"/></a></li>
                    </ul>
                </li>
                <li><a href="<c:url value="/user/compareusers.xhtml" />"><i
                        class="fa fa-refresh"></i>&nbsp;<spring:message
                        code="link.cusers"/></a></li>
                <li><a href="<c:url value="/24h/statistics.xhtml" />"><i
                        class="fa fa-bar-chart"></i>&nbsp;<spring:message
                        code="link.statistics"/></a></li>
            </ul>
        </div>
        <div class="col-xs-4">
            <h3>
                <spring:message code="block.rcontests"/>
            </h3>
            <ul class="list-unstyled">
                <li><a href="<c:url value="/contest/coming.xhtml"/>"><i
                        class="fa fa-arrow-circle-right"></i>&nbsp;<spring:message
                        code="link.coming" /> <c:choose>
                    <c:when test="${com > 0}">
                        <span class="label label-danger">(${com})</span>
                    </c:when>
                </c:choose> </a></li>
                <li><a href="<c:url value="/contest/running.xhtml"/>"><i
                        class="fa fa-play"></i>&nbsp;<spring:message code="link.running" />
                    <c:choose>
                        <c:when test="${run > 0}">
                            <span class="label label-success">(${run})</span>
                        </c:when>
                    </c:choose> </a></li>
                <li><a href="<c:url value="/contest/past.xhtml"/>"><i
                        class="fa fa-arrow-circle-left"></i>&nbsp;<spring:message
                        code="link.previous" /> <span class="label label-default">(${attpast})</span></a></li>
                <li><a href="<c:url value="/contest/contestsrank.xhtml" />"><i
                        class="fa fa-sort-numeric-asc"></i>&nbsp;<spring:message
                        code="link.standings" /></a></li>
                <li><a
                        href="<c:url value="/contest/globalstatistics.xhtml"/>"><i
                        class="fa fa-bar-chart"></i>&nbsp;<spring:message
                        code="link.statistics" /></a></li>
            </ul>
        </div>
    </div>
</div>

