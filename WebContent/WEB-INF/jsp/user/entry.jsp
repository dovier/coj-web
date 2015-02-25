<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer = "16kb" autoFlush="true" %>

<h2 class="postheader">
    ${entry.title}
</h2>
<div class="postcontent">

    <div id ="noticias">
        <authz:authorize ifAnyGranted="ROLE_ADMIN">
            <div style="align:right">
                <c:if test="${entry.adminEnabled ne true}" >
                    <a href="<c:url value="/admin/enableentry.xhtml?id=${entry.id}" />" /><spring:message code="link.adminenabled"/></a>
                </c:if>
                <c:if test="${entry.adminEnabled eq true}">
                    <a href="<c:url value="/admin/disableentry.xhtml?id=${entry.id}" />" /><spring:message code="link.admindisabled"/></a>
                </c:if>
            </authz:authorize>

            <table border="none" class="volume">
                <tr>
                    <td>
                        <a href="<c:url value="/user/useraccount.xhtml?username=${entry.username}"/>">${entry.username}</a>
                    </td>
                    <td>${entry.date}</td>
                </tr>
                <tr>
                    <td colspan="2">
                        ${entry.content}
                    </td>
                </tr>
                <tr>
                    <td colspan="2" >
                        <authz:authorize ifAnyGranted="ROLE_USER">
                            <c:if test="${!rated}">
                                <a href="<c:url value="/user/like.xhtml?id=${entry.id}"/>"><img class="rate" alt="like" src="/images/thumbs-up.jpg" /></a>&nbsp;&nbsp;&nbsp;&nbsp;
                                <a href="<c:url value="/user/dislike.xhtml?id=${entry.id}"/>"><img class="rate" alt="dislike" src="/images/thumbs-down.jpg" /></a>
                                </c:if>
                            </authz:authorize>
                            ${entry.rate}</td>
                </tr>
            </table>


        </div>
        <!-- /article-content -->
    </div>
