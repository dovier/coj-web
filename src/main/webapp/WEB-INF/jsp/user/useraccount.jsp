<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<link rel="stylesheet" href="<c:url value="/css/wboard.css"/>"
	type="text/css" media="screen" />

<h2 class="postheader">
	<spring:message code="pagehdr.profileof" />
	<c:out value="${user.nick}" />
</h2>
<div class="postcontent">
	<div class="row">
		<div class="col-xs-12">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<spring:message code="fieldhdr.generalinfo" />
					<div class="badge pull-right">
						<a data-toggle="collapse" href="#gInfo"><i
							class="fa fa-chevron-up"></i></a>
					</div>
				</div>
				<div id="gInfo" class="panel-body collapse in">

					<c:if test="${user.team == false}">
						<div class="col-xs-2">
							<div class="avatar_preview">
								<img class="avatar img-responsive"
									src="<c:url value="../images/avatars/${user.username}" />" />
							</div>
						</div>
						<div class="col-xs-2 col-xs-offset-1">
							<spring:message code="fieldhdr.fname" />
						</div>
						<div class="col-xs-6 col-xs-offset-1">
							<c:out value="${user.name}" />
						</div>
						<div class="col-xs-2 col-xs-offset-1">
							<spring:message code="fieldhdr.lname" />
						</div>
						<div class="col-xs-6 col-xs-offset-1">
							<c:out value="${user.lastname}" />
						</div>
					</c:if>

					<c:choose>
						<c:when test="${user.team == false}">
							<div class="col-xs-2 col-xs-offset-1">
								<spring:message code="fieldhdr.username" />
							</div>
							<div class="col-xs-6 col-xs-offset-1">
								<a
									href="<c:url value="/mail/composemail.xhtml?usernameto=${user.username}"/>"
									title="<spring:message code="titval.sendpm"/>"><c:out
										value="${user.username}" /> <i class="fa fa-envelope"></i></a>
							</div>
						</c:when>
						<c:otherwise>
							<div class="col-xs-5">
								<spring:message code="fieldhdr.username" />
							</div>
							<div class="col-xs-6 col-xs-offset-1">${user.username}</div>
						</c:otherwise>
					</c:choose>

					<c:if test="${user.team == false}">

						<div class="col-xs-2 col-xs-offset-1">
							<spring:message code="fieldhdr.gender" />
						</div>
						<div class="col-xs-6 col-xs-offset-1">
							<c:choose>
								<c:when test="${user.gender eq 1}">
									<spring:message code="fieldval.male" />
								</c:when>
								<c:otherwise>
									<spring:message code="fieldval.female" />
								</c:otherwise>
							</c:choose>
						</div>
					</c:if>
					<c:if test="${user.showdob}">
						<div class="col-xs-2 col-xs-offset-1">
							<spring:message code="fieldhdr.dob" />
						</div>
						<div class="col-xs-6 col-xs-offset-1">
							<fmt:formatDate pattern="yyyy-MM-dd" value="${user.dob1}" />
						</div>
					</c:if>

					<c:choose>
						<c:when test="${user.team == false}">
							<div class="col-xs-2 col-xs-offset-1">
								<spring:message code="fieldhdr.country" />
							</div>
							<div class="col-xs-6 col-xs-offset-1">${user.country_desc}</div>

							<div class="col-xs-2 col-xs-offset-1">
								<spring:message code="fieldhdr.institution" />
							</div>
							<div class="col-xs-6 col-xs-offset-1">${user.institution_desc}</div>
							<div class="col-xs-12"></div>
						</c:when>
						<c:otherwise>
							<div class="col-xs-5">
								<spring:message code="fieldhdr.country" />
							</div>
							<div class="col-xs-6 col-xs-offset-1">${user.country_desc}</div>

							<div class="col-xs-5">
								<spring:message code="fieldhdr.institution" />
							</div>
							<div class="col-xs-6 col-xs-offset-1">${user.institution_desc}</div>
							<div class="col-xs-12"></div>
						</c:otherwise>
					</c:choose>
					<c:if test="${user.team == false}">

						<div class="col-xs-5">
							<spring:message code="fieldhdr.notifcontest" />
						</div>
						<div class="col-xs-6 col-xs-offset-1">
							<c:if test="${user.contestNotifications}">
								<i class="fa fa-check-square-o"></i>
							</c:if>
							<c:if test="${!user.contestNotifications}">
								<i class="fa fa-square-o"></i>
							</c:if>
						</div>
						<div class="col-xs-5">
							<spring:message code="fieldhdr.notifsubmit" />
						</div>
						<div class="col-xs-6 col-xs-offset-1">
							<c:if test="${user.submissionNotifications}">
								<i class="fa fa-check-square-o"></i>
							</c:if>
							<c:if test="${!user.submissionNotifications}">
								<i class="fa fa-square-o"></i>
							</c:if>
						</div>
						<div class="col-xs-5">
							<spring:message code="fieldhdr.notifproblem" />
						</div>
						<div class="col-xs-6 col-xs-offset-1">
							<c:if test="${user.problemNotifications}">
								<i class="fa fa-check-square-o"></i>
							</c:if>
							<c:if test="${!user.problemNotifications}">
								<i class="fa fa-square-o"></i>
							</c:if>
						</div>
						<div class="col-xs-5">
							<spring:message code="fieldhdr.notifnewprivatemessage" />
						</div>
						<div class="col-xs-6 col-xs-offset-1">
							<c:if test="${user.newprivatemessageNotifications}">
								<i class="fa fa-check-square-o"></i>
							</c:if>
							<c:if test="${!user.newprivatemessageNotifications}">
								<i class="fa fa-square-o"></i>
							</c:if>
						</div>
						<div class="col-xs-5">
							<spring:message code="fieldhdr.notifwboard" />
						</div>
						<div class="col-xs-6 col-xs-offset-1">
							<c:if test="${user.wboardNotifications}">
								<i class="fa fa-check-square-o"></i>
							</c:if>
							<c:if test="${!user.wboardNotifications}">
								<i class="fa fa-square-o"></i>
							</c:if>
						</div>
					</c:if>

					<div class="col-xs-5">
						<spring:message code="fieldhdr.defaultguilang" />
					</div>
					<div class="col-xs-6 col-xs-offset-1">
						<font>${user.glanguage}</font>
					</div>

					<div class="col-xs-5">
						<spring:message code="fieldhdr.defaultproglang" />
					</div>
					<div class="col-xs-6 col-xs-offset-1">
						<font>${user.planguage} </font>
					</div>
					<div class="col-xs-5">
						<spring:message code="fieldhdr.status" />
					</div>
					<div class="col-xs-6 col-xs-offset-1">
						<label class="label bg${user.status}"><c:out
								value="${user.status}" /></label>
					</div>

					<div class="col-xs-5">
						<spring:message code="fieldhdr.regdate" />
					</div>
					<div class="col-xs-6 col-xs-offset-1">
						<c:out value="${user.rgdate}" />
					</div>


					<c:if test="${user.team == true}">
						<div class="col-xs-5">
							<spring:message code="fieldhdr.tcoach" />
						</div>
						<div class="col-xs-6 col-xs-offset-1">
							<c:out value="${user.coach}" />
							&nbsp;
						</div>
						<div class="col-xs-5">
							<spring:message code="fieldhdr.tmember" />
						</div>
						<div class="col-xs-6 col-xs-offset-1">
							<c:out value="${user.user_1}" />
							&nbsp;
						</div>
						<div class="col-xs-5">
							<spring:message code="fieldhdr.tmember" />
						</div>
						<div class="col-xs-6 col-xs-offset-1">
							<c:out value="${user.user_2}" />
							&nbsp;
						</div>
						<div class="col-xs-5">
							<spring:message code="fieldhdr.tmember" />
						</div>
						<div class="col-xs-6 col-xs-offset-1">
							<c:out value="${user.user_3}" />
							&nbsp;
						</div>
					</c:if>

					<c:if test="${user.team == false}">
						<div class="col-xs-5">
							<spring:message code="fieldhdr.lastsubdate" />
						</div>
						<div class="col-xs-6 col-xs-offset-1">
							<c:out value="${user.last_submission}" />
						</div>

						<div class="col-xs-5">
							<spring:message code="fieldhdr.lastacdate" />
						</div>
						<div class="col-xs-6 col-xs-offset-1">
							<c:out value="${user.last_accepted}" />
						</div>

						<div class="col-xs-5">
							<spring:message code="fieldhdr.score" />
						</div>
						<div class="col-xs-6 col-xs-offset-1">
							<fmt:formatNumber type="number" value="${user.score}"
								minFractionDigits="2" maxFractionDigits="2" />
						</div>

						<div class="col-xs-5">
							<spring:message code="fieldhdr.rankusers" />
						</div>
						<div class="col-xs-6 col-xs-offset-1">
							<c:out value="${user.ranking}" />
							<spring:message code="fieldval.of" />
							<a href="<c:url value="/24h/usersrank.xhtml"/>"><c:out
									value="${user.tot_ranking}" /></a>
						</div>

						<div class="col-xs-5">
							<spring:message code="fieldhdr.rankinstitution" />
						</div>
						<div class="col-xs-6 col-xs-offset-1">
							<c:out value="${user.rankingbyinstitution}" />
							<spring:message code="fieldval.of" />
							<a
								href="<c:url value="/24h/usersinstitutionrank.xhtml?inst_id=${user.institution_id}"/>"><c:out
									value="${user.tot_rankingbyinstitution}" /></a>
						</div>

						<div class="col-xs-5">
							<spring:message code="fieldhdr.rankcountry" />
						</div>
						<div class="col-xs-6 col-xs-offset-1">
							<c:out value="${user.rankingbycountry}" />
							<spring:message code="fieldval.of" />
							<a
								href="<c:url value="/24h/userscountryrank.xhtml?country_id=${user.country_id}"/>"><c:out
									value="${user.tot_rankingbycountry}" /></a>
						</div>
					</c:if>
				</div>
			</div>
		</div>
	</div>

	<c:if test="${user.team == false}">
		<div class="row">
			<div class="col-xs-12">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<spring:message code="fieldhdr.achievements" />
						<div class="badge pull-right">
							<a data-toggle="collapse" href="#achievements"><i
								class="fa fa-chevron-up"></i></a>
						</div>
					</div>
					<div id="achievements" class="panel-body collapse in">
						<div class="row">
							<div class="col-xs-2 pull-right">
								<a class="pull-right"
									href="<c:url value="/general/achievements.xhtml?username=${user.username}"/>"><spring:message
										code="link.achievements" />&nbsp;<i class="fa fa-list"></i></a>
							</div>
						</div>
						<c:forEach items="${achievements}" var="achievement">
							<c:if test="${achievement.level > 0}">
								<div class="col-xs-1">
									<div class="shadow white" data-toggle="tooltip"
										title="${achievement.condition}">
										<img class="achievement-image"
											src='<c:out value="/images/achievements/${achievement.id}.${achievement.level}.png" />' />
									</div>
								</div>
							</c:if>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-xs-12">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<spring:message code="fieldhdr.user.entries" />
						<div class="pull-right">
							<div class="badge">
								<a data-toggle="collapse" href="#entries"><i
									class="fa fa-chevron-up"></i></a>
							</div>
						</div>
					</div>
					<div id="entries" class="panel-body collapse in">
						<div class="row">
							<div class="col-xs-2">
								<a data-toggle="tooltip"
									title="<spring:message code='count.followers' />"
									href="<c:url value="/user/${user.username}/following.xhtml"/>">
									<i class="fa fa-users red"></i>&nbsp;${followers}
								</a>
							</div>
							<div class="col-xs-8">
								<spring:message code="last.entry" />
							</div>
							<div class="col-xs-2 pull-right">
								<a class="pull-right"
									href="<c:url value="/user/${user.username}/listentries.xhtml"/>"><spring:message
										code="link.list.entries" />&nbsp;<i class="fa fa-list"></i></a>
							</div>
						</div>
						<div class="row">
							<div class="col-xs-2">
								<a data-toggle="tooltip"
									title="<spring:message code="count.following" />"
									href="<c:url value="/user/${user.username}/following.xhtml"/>"><i
									class="fa fa-users green"></i>&nbsp;${following} </a>
							</div>
							<div class="col-xs-8">
								<div>
									<div>
										<a
											href="<c:url value="/user/useraccount.xhtml?username=${lastEntry.username}"/>">${lastEntry.username}</a>
									</div>
									<div class="text-justify">${lastEntry.text}</div>
									<div class="pull-left">
										<fmt:formatDate value="${lastEntry.date}"
											pattern="yyyy-MM-dd HH:mm:ss" />
									</div>
									<div class="pull-right">
										<c:if test="${lastEntry.rate gt 0}">
											<b class="text-success" id="rating${lastEntry.id}">${lastEntry.rate}</b>
										</c:if>
										<c:if test="${lastEntry.rate lt 0}">
											<b class="text-danger" id="rating${lastEntry.id}">${lastEntry.rate}</b>
										</c:if>
										<c:if test="${lastEntry.rate eq 0}">
											<b id="rating${lastEntry.id}">${lastEntry.rate}</b>
										</c:if>
									</div>
								</div>
							</div>
							<div class="col-xs-2">
								<c:if test="${isfollowing ne null}">
									<c:if test="${!isfollowing}">

										<a class="pull-right"
											href="<c:url value="/user/${user.username}/follow.xhtml"/>"><i
											class="fa fa-plus-square"></i>&nbsp;<spring:message
												code="link.follow" /></a>
									</c:if>
									<c:if test="${isfollowing}">
										<a class="pull-right"
											href="<c:url value="/user/${user.username}/unfollow.xhtml"/>"><i
											class="fa fa-minus-square"></i>&nbsp;<spring:message
												code="link.unfollow" /></a>
									</c:if>
								</c:if>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>


		<div class="row">
			<div class="col-xs-12">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<spring:message code="fieldhdr.statsjudgments" />
						<div class="pull-right">
							<div class="badge">
								<a data-toggle="collapse" href="#stats"><i
									class="fa fa-chevron-up"></i></a>
							</div>
						</div>
					</div>
					<div id="stats" class="panel-body collapse in">
					<div class="row row-centered"><div class="col-centered col-xs-4"><canvas id="chart"></canvas></div></div>
						<div class="col-xs-12">
							<div class="margin-top-05">
								<table id="wijbarcharttable"
									class="table table-bordered table-condensed">
									<thead>
										<tr>
											<th><spring:message code="tablehdr.ac" /></th>
											<th><spring:message code="tablehdr.ce" /></th>
											<th><spring:message code="tablehdr.mle" /></th>
											<th><spring:message code="tablehdr.ole" /></th>
											<th><spring:message code="tablehdr.pe" /></th>
											<th><spring:message code="tablehdr.rte" /></th>
											<th><spring:message code="tablehdr.tle" /></th>
											<th><spring:message code="tablehdr.wa" /></th>
											<th><spring:message code="tablehdr.total" /></th>
										</tr>
									</thead>
									<tr>
										<td><a
											href="/24h/status.xhtml?username=${user.username}&status=ac"><c:out
													value="${user.acc}" /></a> <authz:authorize
												ifNotGranted="ROLE_ANONYMOUS">
												<c:if test="${currentUsername}">
													<a href="/24h/downloadsourcezip.xhtml?status=1">&nbsp;<i
														class="fa fa-save"></i></a>
												</c:if>
											</authz:authorize>
										<td><a
											href="/24h/status.xhtml?username=${user.username}&status=ce"><c:out
													value="${user.ce}" /></a>
										<td><a
											href="/24h/status.xhtml?username=${user.username}&status=mle"><c:out
													value="${user.mle}" /></a>
										<td><a
											href="/24h/status.xhtml?username=${user.username}&status=ole"><c:out
													value="${user.ole}" /></a>
										<td><a
											href="/24h/status.xhtml?username=${user.username}&status=pe"><c:out
													value="${user.pe}" /></a>
										<td><a
											href="/24h/status.xhtml?username=${user.username}&status=rte"><c:out
													value="${user.rte}" /></a>
										<td><a
											href="/24h/status.xhtml?username=${user.username}&status=tle"><c:out
													value="${user.tle}" /></a>
										<td><a
											href="/24h/status.xhtml?username=${user.username}&status=wa"><c:out
													value="${user.wa}" /></a>
										<td><c:out value="${user.total}" /> <authz:authorize
												ifNotGranted="ROLE_ANONYMOUS">
												<c:if test="${currentUsername}">
													<a href="/24h/downloadsourcezip.xhtml">&nbsp;<i
														class="fa fa-save"></i></a>
												</c:if>
											</authz:authorize></td>
									</tr>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<c:if test="${solved ne null}">
			<div class="row">
				<div class="col-xs-12">
					<div class="panel panel-primary">

						<div class="panel-heading">
							<spring:message code="fieldhdr.solvedprob" />
							<span class="badge">${user.solved}</span>
							<div class="badge pull-right">
								<a data-toggle="collapse" href="#probsACC"><i
									class="fa fa-chevron-up"></i></a>
							</div>
						</div>
						<div id="probsACC" class="panel-body collapse in">
							<c:forEach items="${solved}" var="problem">
								<div class="col-xs-1 margin-top-05">
									<a
										href="/24h/status.xhtml?username=<c:out value="${user.username}"/>&pid=${problem.pid}"
										title="${problem.title}"><span class="badge alert-success">${problem.pid}</span></a>
								</div>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
		</c:if>
		<c:if test="${unsolved ne null}">
			<div class="row">
				<div class="col-xs-12">
					<div class="panel panel-primary">

						<div class="panel-heading">
							<spring:message code="fieldhdr.triedunsolvedprob" />
							<span class="badge">${user.unsolved}</span>
							<div class="badge pull-right">
								<a data-toggle="collapse" href="#probsWA"><i
									class="fa fa-chevron-up"></i></a>
							</div>
						</div>
						<div id="probsWA" class="panel-body collapse in">
							<c:forEach items="${unsolved}" var="problem">
								<div class="col-xs-1 margin-top-05">
									<a
										href="/24h/status.xhtml?username=${user.username}&pid=${problem.pid}"
										title="${problem.title}"><span class="badge alert-danger">${problem.pid}</span></a>
								</div>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
		</c:if>
	</c:if>
</div>

<script type="text/javascript" src="<c:url value="/js/Chart.min.js"/>"></script>
<script>
$(function() {
	$("[data-toggle='tooltip']").tooltip();
});

	$('img.avatar').error(function() {
		$(this).attr('src', '<c:url value="/images/default_avatar.png"/>');
	});

	$(function() {
		$('.fa-chevron-up').click(function() {
			$(this).toggleClass('fa-chevron-up');
			$(this).toggleClass('fa-chevron-down');
		});

		$("[data-toggle='tooltip']").tooltip();
	});
	
		var chart = null;
	$(document).ready(function() {
		callback();
	});

	function callback() {
		$("#loading").show();
		var nat = $('#username').val();
				var data = {
					    labels: ["AC",
						"CE",
						"MLE",
						"OLE",
						"PE",
						"RTE",
						"TLE",
						"WA"
						],
					    datasets: [
					        {
					            label: "Submits",
					            fillColor: "#4c83c3",
					            data: [${user.acc}, ${user.ce}, ${user.mle},
										${user.ole}, ${user.pe}, ${user.rte},
										${user.tle}, ${user.wa}]
					        }
					    ]
					};				
					chart = new Chart($("#chart").get(0).getContext("2d")).Bar(data);
	}
</script>