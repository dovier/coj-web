<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer="16kb" autoFlush="true"%>
<authz:authentication var="principal" property="principal" />
<div class="panel panel-primary">
	<div class="panel-heading">
		<b><spring:message code="count.followers" /></b>
		<div class="pull-right">
			<div class="badge">${followers.pageNumber}/
				${followers.totalPages}</div>
			<div class="badge">
				<a data-toggle="collapse" href="#followers"><i
					class="fa fa-chevron-up"></i></a>
			</div>
		</div>
	</div>
	<div id="followers" class="panel-body text-center collapse in">
		<ul class="pager">
			<c:if test="${followers.pageNumber != 1}">
				<li class="previous"><a
					href="javascript:followersPage(${followers.pageNumber - 1});">&larr;</a></li>
			</c:if>
			<c:if test="${followers.pageNumber < followers.totalPages}">
				<li class="next"><a
					href="javascript:followersPage(${followers.pageNumber + 1});">&rarr;</a></li>
			</c:if>
		</ul>
		<c:forEach items="${followers.list}" var="f">
			<div
				class="col-xs-2 col-centered margin-right-02 margin-left-02 panel panel-primary">
				<div class="entry_avatar">
					<div class="center-block">
						<a
							href="<c:url value='/user/useraccount.xhtml?username=${f.username}'/>">
							<h6>${f.username}</h6> <img class="avatar img-responsive"
							src="<c:url value="../../images/avatars/${f.username}" />" />
						</a>
					</div>
					<div id="actions${f.uid}" class="margin-top-05 pull-right">
						<authz:authorize access="isAuthenticated()">
							<c:if test="${user == principal.username}">
								<a data-toggle='tooltip' title="PM"
									href="<c:url value="/mail/composemail.xhtml?usernameto=${f.username}"/>"><i
									class="fa fa-envelope"></i></a>&nbsp; <a
									href="javascript:follow('${f.username}',${followers.pageNumber});"
									data-toggle="tooltip"
									title='<spring:message code="link.follow" />'> <i
									class="fa fa-plus-square"></i></a>
							</c:if>
						</authz:authorize>
					</div>
				</div>
			</div>
		</c:forEach>
		<ul class="pager">
			<c:if test="${followers.pageNumber != 1}">
				<li class="previous"><a
					href="javascript:followersPage(${followers.pageNumber - 1});">&larr;</a></li>
			</c:if>
			<c:if test="${followers.pageNumber < followers.totalPages}">
				<li class="next"><a
					href="javascript:followersPage(${followers.pageNumber + 1});">&rarr;</a></li>
			</c:if>
		</ul>
	</div>
</div>
<script>
	function follow(user, page) {
		$.ajax({
			type : "GET",
			url : "/user/" + user + "/follow.xhtml",
			dataType : 'html',
			success : function(data) {
				followingPage(page);
			}
		});
	}
</script>