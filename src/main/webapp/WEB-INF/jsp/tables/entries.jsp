<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer="16kb" autoFlush="true"%>
<authz:authentication var="principal" property="principal" />
<display:table id="entry" name="entries" class="volume" requestURI=""
               defaultsort="0" defaultorder="ascending">
    <display:column titleKey="tablehdr.entry" headerClass="headrk">
        <div>
            <div class="col-xs-2 pull-left">
                <div class="entry_avatar">
                    <c:choose>
                        <c:when test="${entry.username=='COJboard'}"> 
                            <a href="<c:url value="/wboard/contests.xhtml"/>">
                                <img class="avatar img-responsive" src="<c:url value="/images/avatars/${entry.username}"/>" />
                            </a>
                        </c:when>
                        <c:otherwise>
                            <a href="<c:url value="/user/useraccount.xhtml?username=${entry.username}"/>">
                                <img class="avatar img-responsive" src="<c:url value="/images/avatars/${entry.username}"/>" />
                            </a>
                        </c:otherwise>
                    </c:choose>    

                </div>
            </div>
            <div class="col-xs-10">
                <div>

                    <span class="pull-left">
                        <c:choose>
                            <c:when test="${entry.username=='COJboard'}"> 
                                <a href="<c:url value="/wboard/contests.xhtml"/>">COJboard</a>
                            </c:when>
                            <c:otherwise>
                                <a href="<c:url value="/user/useraccount.xhtml?username=${entry.username}"/>">${entry.username}</a>
                            </c:otherwise>
                        </c:choose>                        
                    </span> 
                    <span>&nbsp;</span> 
                    <small class="pull-right"><fmt:formatDate
                            value="${entry.date}" pattern="yyyy-MM-dd HH:mm:ss" />
                    </small>
                </div>
                <authz:authorize access="${!entry.adminEnabled}">
                    <div id="entry${entry.id}" class="text-justify">
                        <i><fmt:message key="entry.pending.approval" /></i>
                    </div>
                </authz:authorize>
                <authz:authorize access="${entry.adminEnabled}">
                    <div id="entry${entry.id}" class="text-justify">${entry.text}</div>
                    <div id="actions${entry.id}" class="pull-right">
                        <authz:authorize access="hasRole('ROLE_ADMIN')">
                            <a href="javascript:disableEntry(${entry.id});"
                               data-toggle="tooltip" title="<spring:message code="messages.general.hideentry"/>">
                                <i
                                    class="fa fa-eye-slash"></i>                                
                            </a>
                        </authz:authorize>
                        <authz:authorize access="isAuthenticated()">
                            <a data-toggle="tooltip" title="<spring:message code="messages.general.replytouser"/>"
                               href="javascript:reply('@${entry.username} ');"><i
                                    class="fa fa-reply"></i></a>
                            <a data-toggle="tooltip" title="<spring:message code="messages.general.mailuser"/>"
                               href="<c:url value="/mail/composemail.xhtml?usernameto=${entry.username}"/>"><i
                                    class="fa fa-envelope"></i></a>
                            <a data-toggle="tooltip" title="<spring:message code="messages.general.forwardentry"/>"
                               href="<c:url value="/forwardentry.xhtml?id=${entry.id}"/>"><i
                                    class="fa fa-mail-forward"></i></a>
                            </authz:authorize>
                            <authz:authorize access="isAuthenticated()">
                                <c:if test="${not entry.voted and entry.username != principal.username}">
                                <a id="thumbs-up${entry.id}" data-toggle="tooltip"
                                   title="<spring:message code="messages.general.upvoteentry"/>" href="javascript:like(${entry.id});"><i
                                        class="fa fa-thumbs-o-up"></i></a>
                                </c:if>
                            </authz:authorize>
                            <c:if test="${entry.rate gt 0}">
                            <b class="text-success" id="rating${entry.id}">${entry.rate}</b>
                        </c:if>
                        <c:if test="${entry.rate lt 0}">
                            <b class="text-danger" id="rating${entry.id}">${entry.rate}</b>
                        </c:if>
                        <c:if test="${entry.rate eq 0}">
                            <b id="rating${entry.id}">${entry.rate}</b>
                        </c:if>
                        <authz:authorize access="isAuthenticated()">
                            <c:if
                                test="${not entry.voted and entry.username != principal.username}">
                                <a id="thumbs-down${entry.id}" data-toggle="tooltip"
                                   title="<spring:message code="messages.general.downvoteentry"/>" href="javascript:dislike(${entry.id});"><i
                                        class="fa fa-thumbs-o-down"></i></a>
                                </c:if>
                            </authz:authorize>
                    </div>
                </authz:authorize>
            </div>
        </div>
    </display:column>
</display:table>
<script>
    $('img.avatar').error(function () {
        $(this).attr('src', '<c:url value="/images/default_avatar.png"/>');
    });

    $(function () {
        $("[data-toggle='tooltip']").tooltip();
    });
</script>
