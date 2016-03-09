<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page buffer="16kb" autoFlush="true" %>
<display:table id="entry" name="entries" class="volume" requestURI=""
               defaultsort="0" defaultorder="ascending">
    <display:column titleKey="tablehdr.entry" headerClass="headrk">
        <div>
            <div class="col-xs-3 pull-left">
                <div>
                    <a
                            href="<c:url value="/user/useraccount.xhtml?username=${entry.username}"/>">${entry.username}</a>
                </div>
                <div>
                    <fmt:formatDate value="${entry.date}" pattern="yyyy-MM-dd HH:mm:ss"/>
                </div>
            </div>
            <div class="col-xs-9 ">
                <authz:authorize access="${!entry.adminEnabled}">
                    <div class="text-left">
                        <i><fmt:message key="entry.pending.approval"/></i>
                    </div>
                </authz:authorize>
                <authz:authorize access="${entry.adminEnabled}">
                    <div class="text-left">${entry.text}</div>
                </authz:authorize>
                <div class="pull-right">
                 <%--   <authz:authorize access="isAuthenticated()">
                        <c:if test="${not entry.voted and entry.username != principal.username}">
                            <a id="thumbs-up${entry.id}" data-toggle="tooltip"
                               title="<spring:message code="messages.general.upvoteentry"/>"
                               href="javascript:like(${entry.id});"><i
                                    class="fa fa-thumbs-o-up"></i></a>
                        </c:if>
                    </authz:authorize>--%>
                    <c:if test="${entry.rate gt 0}">
                        <b class="text-success" id="rating${entry.id}">${entry.rate}</b>
                    </c:if>
                    <c:if test="${entry.rate lt 0}">
                        <b class="text-danger" id="rating${entry.id}">${entry.rate}</b>
                    </c:if>
                    <c:if test="${entry.rate eq 0}">
                        <b id="rating${entry.id}">${entry.rate}</b>
                    </c:if>
                   <%-- <authz:authorize access="isAuthenticated()">
                        <c:if test="${not entry.voted and entry.username != principal.username}">
                            <a id="thumbs-down${entry.id}" data-toggle="tooltip"
                               title="<spring:message code="messages.general.downvoteentry"/>"
                               href="javascript:dislike(${entry.id});"><i
                                    class="fa fa-thumbs-o-down"></i></a>
                        </c:if>
                    </authz:authorize>--%>
                </div>
            </div>
        </div>
    </display:column>
</display:table>