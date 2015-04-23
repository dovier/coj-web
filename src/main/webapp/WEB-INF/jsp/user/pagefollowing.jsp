<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer="16kb" autoFlush="true"%>
<div class="panel panel-primary">
	<div class="panel-heading">
		<b><spring:message code="count.following" /></b>
		<div class="pull-right">
			<div class="badge">${following.pageNumber}/
				${following.totalPages}</div>
			<div class="badge">
				<a data-toggle="collapse" href="#following"><i
					class="fa fa-chevron-up"></i></a>
			</div>
		</div>
	</div>
	<div id="following" class="panel-body collapse in">
		<ul class="pager">
			<c:if test="${following.pageNumber != 1}">
				<li class="previous"><a
					href="javascript:followingPage(${following.pageNumber - 1});">&larr;</a></li>
			</c:if>
			<c:if test="${following.pageNumber < following.totalPages}">
				<li class="next"><a
					href="javascript:followingPage(${following.pageNumber + 1});">&rarr;</a></li>
			</c:if>
		</ul>
		<c:forEach items="${following.list}" var="f">
			<div
				class="col-xs-2 col-centered margin-right-02 margin-left-02 panel panel-primary">
				<div class="entry_avatar">
					<div class="center-block">
						<a
							href="<c:url value='/user/useraccount.xhtml?username=${f.username}'/>" >
							<h6>${f.username}</h6> <img class="avatar img-responsive"
							src="<c:url value="../../images/avatars/${f.username}" />" />
						</a>
					</div>
					<div id="actions${f.uid}" class="margin-top-05 pull-right">
						<authz:authorize access="isAuthenticated()">
							<c:if test="${user == principal.username}">
								<a data-toggle='tooltip' title="PM"
									href="<c:url value="/mail/composemail.xhtml?usernameto=${f.username}"/>"><i
									class="fa fa-envelope"></i></a>&nbsp;
								<a
									href="javascript:unfollow('${f.username}',${following.pageNumber});"
									data-toggle="tooltip"
									title='<spring:message code="link.unfollow" />'> <i
									class="fa fa-minus-square"></i></a>
							</c:if>
						</authz:authorize>
					</div>
				</div>
			</div>
		</c:forEach>
		<ul class="pager">
			<c:if test="${following.pageNumber != 1}">
				<li class="previous"><a
					href="javascript:followingPage(${following.pageNumber - 1});">&larr;</a></li>
			</c:if>
			<c:if test="${following.pageNumber < following.totalPages}">
				<li class="next"><a
					href="javascript:followingPage(${following.pageNumber + 1});">&rarr;</a></li>
			</c:if>
		</ul>
	</div>
</div>
<script>
	function unfollow(user, page) {
		$.ajax({
			type : "GET",
			url : "/user/" + user + "/unfollow.xhtml",
			dataType : 'html',
			success : function(data) {
				followingPage(page);
			}
		});
	}
</script>