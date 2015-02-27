<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
	String uid = request.getParameter("uid");
%>

<%@page buffer="16kb" autoFlush="true"%>

<h2 class="postheader">
	<spring:message code="pagehdr.24hcusers" />
</h2>
<div class="postcontent">
	<!-- article-content -->
	<div class="row">
		<form action="compareusers.xhtml" method="get" class="form-inline">
			<div class="form-group col-xs-5">
				<div class="pull-right">
					<input placeholder="<spring:message code="fieldhdr.user" />"
						class="form-control" name="uid1" value="${compare.user1}">
				</div>
			</div>
			<div class="form-group col-xs-1">
				<img class="versus" src="../images/versus.jpg" />
			</div>
			<div class="form-group col-xs-5">
				<div class="pull-left">
					<input placeholder="<spring:message	code="fieldhdr.user" />"
						class="form-control" name="uid2" value="${compare.user2}">
				</div>
				<div class="pull-right">
					<input class="btn btn-primary" type="submit" name="submit"
						id="submit" value="<spring:message code="button.compare"/>" />
				</div>
			</div>
		</form>
	</div>
	<div class="row margin-top-05">
		<c:if test="${error == false}">
			<div class="col-xs-12">
				<div class="panel panel-success">
					<div class="panel-heading">
						<spring:message code="fieldhdr.probsolvedonlyby" />
							<a
								href="<c:url value="useraccount.xhtml?username=${compare.user1}"/>">${compare.user1}</a>
						<div class="badge">
							${compare.ft}
						</div>
						<div class="badge pull-right">
							<a data-toggle="collapse" href="#pfirstAC"><i
								class="fa fa-chevron-up"></i></a>
						</div>
					</div>
					<div id="pfirstAC" class="panel-body collapse in">
						<c:forEach items="${compare.facc}" var="problem">
							<div class="col-xs-1 margin-top-05">
								<a href="/24h/problem.xhtml?pid=${problem.pid}"
									title="${problem.title}"> <span class="badge alert-success">
										${problem.pid} </span>
								</a>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
			<div class="col-xs-12">
				<div class="panel panel-info">
					<div class="panel-heading">
						<spring:message code="fieldhdr.probsolvedbyboth" />
						<div class="badge">${compare.bt}</div>
						<div class="badge pull-right">
							<a data-toggle="collapse" href="#pbothAC"><i
								class="fa fa-chevron-up"></i></a>
						</div>
					</div>
					<div id="pbothAC" class="panel-body collapse in">
						<c:forEach items="${compare.bacc}" var="problem">
							<div class="col-xs-1 margin-top-05">
								<a href="/24h/problem.xhtml?pid=${problem.pid}"
									title="${problem.title}"><span class="badge alert-info">${problem.pid}</span></a>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
			<div class="col-xs-12">
				<div class="panel panel-success">
					<div class="panel-heading">
						<spring:message code="fieldhdr.probsolvedonlyby" />
							<a
								href="<c:url value="useraccount.xhtml?username=${compare.user2}"/>">${compare.user2}</a>
						<div class="badge">
							${compare.st}
						</div>
						<div class="badge pull-right">
							<a class="text-success" data-toggle="collapse" href="#psecAC"><i
								class="fa fa-chevron-up"></i></a>
						</div>
					</div>
					<div id="psecAC" class="panel-body collapse in">
						<c:forEach items="${compare.sacc}" var="problem">
							<div class="col-xs-1 margin-top-05">
								<a href="/24h/problem.xhtml?pid=${problem.pid}"
									title="${problem.title}"><span class="badge alert-success">${problem.pid}</span></a>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
			<div class="col-xs-12">
				<div class="panel panel-warning">
					<div class="panel-heading">
						<spring:message code="fieldhdr.probtriedonlyby" />
							<a href="<c:url value="useraccount.xhtml?username=${compare.user1}"/>">${compare.user1}</a>
						<div class="badge">
							${compare.fft}
						</div>
						<div class="badge pull-right">
							<a data-toggle="collapse" href="#pfirstWA"><i
								class="fa fa-chevron-up"></i></a>
						</div>
					</div>
					<div id="pfirstWA" class="panel-body collapse in">
						<c:forEach items="${compare.ff}" var="problem">
							<div class="col-xs-1 margin-top-05">
								<a href="/24h/problem.xhtml?pid=${problem.pid}"
									title="${problem.title}"><span class="badge alert-warning">${problem.pid}</span></a>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
			<div class="col-xs-12">
				<div class="panel panel-danger">
					<div class="panel-heading">
						<spring:message code="fieldhdr.probtriedbyboth" />
						<div class="badge">
							${compare.bft}
							</legend>
						</div>
						<div class="badge pull-right">
							<a data-toggle="collapse" href="#pbothWA"><i
								class="fa fa-chevron-up"></i></a>
						</div>
					</div>
					<div id="pbothWA" class="panel-body collapse in">
						<c:forEach items="${compare.bf}" var="problem">
							<div class="col-xs-1 margin-top-05">
								<a href="/24h/problem.xhtml?pid=${problem.pid}"
									title="${problem.title}"><span class="badge alert-danger">${problem.pid}</span></a>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
			<div class="col-xs-12">
				<div class="panel panel-warning">
					<div class="panel-heading">
						<spring:message code="fieldhdr.probtriedonlyby" />
							<a href="<c:url value="useraccount.xhtml?username=${compare.user2}"/>">${compare.user2}</a>
						<div class="badge">
							${compare.sft}
						</div>
						<div class="badge alert-danger pull-right">
							<a data-toggle="collapse" href="#psecWA"><i
								class="fa fa-chevron-up"></i></a>
						</div>
					</div>
					<div id="psecWA" class="panel-body collapse in">
						<c:forEach items="${compare.sf}" var="problem">
							<div class="col-xs-1 margin-top-05">
								<a href="/24h/problem.xhtml?pid=${problem.pid}"
									title="${problem.title}"><span class="badge alert-warning">${problem.pid}</span></a>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</c:if>
	</div>
</div>

<script>
$(function() {
	$('.fa-chevron-up').click(function() {
		$(this).toggleClass('fa-chevron-up');
		$(this).toggleClass('fa-chevron-down');
	});
});

</script>