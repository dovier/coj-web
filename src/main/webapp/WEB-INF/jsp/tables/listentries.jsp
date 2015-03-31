<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer="16kb" autoFlush="true"%>
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
					<fmt:formatDate value="${entry.date}" pattern="yyyy-MM-dd HH:mm:ss" />
				</div>
			</div>
			<div class="col-xs-9 ">
				<authz:authorize access="${!entry.adminEnabled}">
					<div class="text-justify">
						<i><fmt:message key="entry.pending.approval" /></i>
					</div>
				</authz:authorize>
				<authz:authorize access="${entry.adminEnabled}">
					<div class="text-justify">${entry.text}</div>
				</authz:authorize>
				<div class="pull-right">
					<c:if test="${not entry.voted}">
						<authz:authorize access="isAuthenticated()">
							<a id="thumbs-up${entry.id}" href="javascript:like(${entry.id});"><i
								class="fa fa-thumbs-o-up"></i></a>
						</authz:authorize>
					</c:if>
					<c:if test="${entry.rate gt 0}">
						<b class="text-success" id="rating${entry.id}">${entry.rate}</b>
					</c:if>
					<c:if test="${entry.rate lt 0}">
						<b class="text-danger" id="rating${entry.id}">${entry.rate}</b>
					</c:if>
					<c:if test="${entry.rate eq 0}">
						<b id="rating${entry.id}">${entry.rate}</b>
					</c:if>
					<c:if test="${not entry.voted}">
						<authz:authorize access="isAuthenticated()">
							<a id="thumbs-down${entry.id}"
								href="javascript:dislike(${entry.id});"><i
								class="fa fa-thumbs-o-down"></i></a>
						</authz:authorize>
					</c:if>
				</div>
			</div>
		</div>
	</display:column>
</display:table>