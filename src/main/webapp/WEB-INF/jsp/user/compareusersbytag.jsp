<!-- frankr addition start 
FIXME: DRY principle
-->

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
	<%-- 	<authz:authentication property=""/> --%>
	<form action="compareusersbytag.xhtml" method="get" class="form-inline">
		<div class="row">
			<div class="col-xs-3">
				<div class="pull-right">
					<input size="16"
						placeholder="<spring:message code="fieldhdr.user" />"
						class="form-control" name="user1" value="${user1}">
				</div>
			</div>

			<div class="col-xs-1">
				<img class="versus" src="../images/versus.jpg" />
			</div>

			<div class="col-xs-3">
				<div class="pull-left">
					<input size="16"
						placeholder="<spring:message	code="fieldhdr.user" />"
						class="form-control" name="user2" value="${user2}">
				</div>
			</div>

			<div class="col-xs-5">
				<div class="pull-left">
					<select name="classification" class="form-control">
						<option value="-1"><spring:message code="fieldhdr.all" /></option>
						<c:forEach items="${classifications}" var="classif">
							<option value="${classif.idClassification}"
								<c:if test="${classification eq classif.idClassification}">selected</c:if>>${classif.name}</option>
						</c:forEach>
					</select>
				</div>

				<div class="pull-right">
					<input class="btn btn-primary" type="submit" name="submit"
						id="submit" value="<spring:message code="button.compare"/>" />
				</div>
			</div>
		</div>
		<c:if test="${usernameError == true}">
			<span class="label label-danger"> <spring:message
					code="general.error.users.notexist" />
			</span>
		</c:if>
	</form>

	<c:if test="${(error == false) and (usernameError == false)}">
		<c:if test="${!(classification == -1)}">
			<!-- firstAC -->
			<div class="row margin-top-05">
				<div class="col-xs-12">
					<div class="panel panel-success">
						<div class="panel-heading">
							<spring:message code="fieldhdr.probsolvedonlyby" />
							<a href="<c:url value="useraccount.xhtml?username=${user1}"/>">${user1}</a>
							in <a href="/24h/problems.xhtml?classification=${classification}">${classifName}</a>
							<div class="badge">${onlybyuser1}</div>
							<div class="badge pull-right">
								<a data-toggle="collapse" href="#pFirstAC"><i
									class="fa fa-chevron-down"></i></a>
							</div>
						</div>

						<div id="pFirstAC" class="panel-body collapse">

							<c:forEach items="${onlyUser1Complexity}" var="entry">
								<c:if test="${entry.value.size() > 0}">
									<div class="panel panel-success">
										<div class="panel-heading">
											<spring:message code="fieldhdr.probsolvedonlyby" />
											<a
												href="<c:url value="useraccount.xhtml?username=${user1}"/>">${user1}</a>
											in <a
												href="/24h/problems.xhtml?classification=${classification}&complexity=${entry.key}">${classifName}
												${entry.key}</a>
											<div class="badge">${entry.value.size()}</div>
											<div class="badge pull-right">
												<a data-toggle="collapse"
													href="#complexity${user1}${entry.key}"><i
													class="fa fa-chevron-up"></i></a>
											</div>
										</div>

										<div id="complexity${user1}${entry.key}"
											class="panel-body collapse in">
											<c:forEach items='${entry.value}' var="pc">
												<div class="col-xs-1 margin-top-05">
													<a href="/24h/problem.xhtml?pid=${pc.pid}"
														title="${pc.problemTitle}"> <span
														class="badge alert-success"> ${pc.pid} </span>
													</a>
												</div>
											</c:forEach>
										</div>
									</div>
								</c:if>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>


			<!-- Both -->
			<div class="row margin-top-05">
				<div class="col-xs-12">
					<div class="panel panel-info">
						<div class="panel-heading">
							<spring:message code="fieldhdr.probsolvedbyboth" />
							in <a href="/24h/problems.xhtml?classification=${classification}">${classifName}</a>
							<div class="badge">${byboth}</div>
							<div class="badge pull-right">
								<a data-toggle="collapse" href="#bothAC"><i
									class="fa fa-chevron-down"></i></a>
							</div>
						</div>

						<div id="bothAC" class="panel-body collapse">

							<c:forEach items="${bothComplexity}" var="entry">
								<c:if test="${entry.value.size() > 0}">
									<div class="panel panel-info">
										<div class="panel-heading">
											<spring:message code="fieldhdr.probsolvedbyboth" />
											in <a
												href="/24h/problems.xhtml?classification=${classification}&complexity=${entry.key}">${classifName}
												${entry.key}</a>
											<div class="badge">${entry.value.size()}</div>
											<div class="badge pull-right">
												<a data-toggle="collapse" href="#complexityboth${entry.key}"><i
													class="fa fa-chevron-up"></i></a>
											</div>
										</div>

										<div id="complexityboth${entry.key}"
											class="panel-body collapse in">
											<c:forEach items='${entry.value}' var="pc">
												<div class="col-xs-1 margin-top-05">
													<a href="/24h/problem.xhtml?pid=${pc.pid}"
														title="${pc.problemTitle}"> <span
														class="badge alert-info"> ${pc.pid} </span>
													</a>
												</div>
											</c:forEach>
										</div>
									</div>
								</c:if>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>

			<!-- secondAC -->
			<div class="row margin-top-05">
				<div class="col-xs-12">
					<div class="panel panel-success">
						<div class="panel-heading">
							<spring:message code="fieldhdr.probsolvedonlyby" />
							<a href="<c:url value="useraccount.xhtml?username=${user2}"/>">${user2}</a>
							in <a href="/24h/problems.xhtml?classification=${classification}">${classifName}</a>
							<div class="badge">${onlybyuser2}</div>
							<div class="badge pull-right">
								<a data-toggle="collapse" href="#pSecondAC"><i
									class="fa fa-chevron-down"></i></a>
							</div>
						</div>

						<div id="pSecondAC" class="panel-body collapse">

							<c:forEach items="${onlyUser2Complexity}" var="entry">
								<c:if test="${entry.value.size() > 0}">
									<div class="panel panel-success">
										<div class="panel-heading">
											<spring:message code="fieldhdr.probsolvedonlyby" />
											<a
												href="<c:url value="useraccount.xhtml?username=${user2}"/>">${user2}</a>
											in <a
												href="/24h/problems.xhtml?classification=${classification}&complexity=${entry.key}">${classifName}
												${entry.key}</a>
											<div class="badge">${entry.value.size()}</div>
											<div class="badge pull-right">
												<a data-toggle="collapse"
													href="#complexity${user2}${entry.key}"><i
													class="fa fa-chevron-up"></i></a>
											</div>
										</div>

										<div id="complexity${user2}${entry.key}"
											class="panel-body collapse in">
											<c:forEach items='${entry.value}' var="pc">
												<div class="col-xs-1 margin-top-05">
													<a href="/24h/problem.xhtml?pid=${pc.pid}"
														title="${pc.problemTitle}"> <span
														class="badge alert-success"> ${pc.pid} </span>
													</a>
												</div>
											</c:forEach>
										</div>
									</div>
								</c:if>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>

			<!-- onlyfirstTried -->
			<div class="row margin-top-05">
				<div class="col-xs-12">
					<div class="panel panel-warning">
						<div class="panel-heading">
							<spring:message code="fieldhdr.probtriedonlyby" />
							<a href="<c:url value="useraccount.xhtml?username=${user1}"/>">${user1}</a>
							in <a href="/24h/problems.xhtml?classification=${classification}">${classifName}</a>
							<div class="badge">${triedonlybyuser1}</div>
							<div class="badge pull-right">
								<a data-toggle="collapse" href="#pFirstTried"><i
									class="fa fa-chevron-down"></i></a>
							</div>
						</div>

						<div id="pFirstTried" class="panel-body collapse">

							<c:forEach items="${triedUser1Complexity}" var="entry">
								<c:if test="${entry.value.size() > 0}">
									<div class="panel panel-warning">
										<div class="panel-heading">
											<spring:message code="fieldhdr.probtriedonlyby" />
											<a
												href="<c:url value="useraccount.xhtml?username=${user1}"/>">${user1}</a>
											in <a
												href="/24h/problems.xhtml?classification=${classification}&complexity=${entry.key}">${classifName}
												${entry.key}</a>
											<div class="badge">${entry.value.size()}</div>
											<div class="badge pull-right">
												<a data-toggle="collapse" href="#tried${user1}${entry.key}"><i
													class="fa fa-chevron-up"></i></a>
											</div>
										</div>

										<div id="tried${user1}${entry.key}"
											class="panel-body collapse in">
											<c:forEach items='${entry.value}' var="pc">
												<div class="col-xs-1 margin-top-05">
													<a href="/24h/problem.xhtml?pid=${pc.pid}"
														title="${pc.problemTitle}"> <span
														class="badge alert-warning"> ${pc.pid} </span>
													</a>
												</div>
											</c:forEach>
										</div>
									</div>
								</c:if>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>

			<!-- triedByBoth -->
			<div class="row margin-top-05">
				<div class="col-xs-12">
					<div class="panel panel-danger">
						<div class="panel-heading">
							<spring:message code="fieldhdr.probtriedbyboth" />
							in <a href="/24h/problems.xhtml?classification=${classification}">${classifName}</a>
							<div class="badge">${triedbyboth}</div>
							<div class="badge pull-right">
								<a data-toggle="collapse" href="#bothTried"><i
									class="fa fa-chevron-down"></i></a>
							</div>
						</div>

						<div id="bothTried" class="panel-body collapse">

							<c:forEach items="${triedBothComplexity}" var="entry">
								<c:if test="${entry.value.size() > 0}">
									<div class="panel panel-danger">
										<div class="panel-heading">
											<spring:message code="fieldhdr.probtriedbyboth" />
											in <a
												href="/24h/problems.xhtml?classification=${classification}&complexity=${entry.key}">${classifName}
												${entry.key}</a>
											<div class="badge">${entry.value.size()}</div>
											<div class="badge pull-right">
												<a data-toggle="collapse" href="#triedboth${entry.key}"><i
													class="fa fa-chevron-up"></i></a>
											</div>
										</div>

										<div id="triedboth${entry.key}" class="panel-body collapse in">
											<c:forEach items='${entry.value}' var="pc">
												<div class="col-xs-1 margin-top-05">
													<a href="/24h/problem.xhtml?pid=${pc.pid}"
														title="${pc.problemTitle}"> <span
														class="badge alert-danger"> ${pc.pid} </span>
													</a>
												</div>
											</c:forEach>
										</div>
									</div>
								</c:if>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>

			<!-- onlySecondTried -->
			<div class="row margin-top-05">
				<div class="col-xs-12">
					<div class="panel panel-warning">
						<div class="panel-heading">
							<spring:message code="fieldhdr.probtriedonlyby" />
							<a href="<c:url value="useraccount.xhtml?username=${user2}"/>">${user2}</a>
							in <a href="/24h/problems.xhtml?classification=${classification}">${classifName}</a>
							<div class="badge">${triedonlybyuser2}</div>
							<div class="badge pull-right">
								<a data-toggle="collapse" href="#pSecondTried"><i
									class="fa fa-chevron-down"></i></a>
							</div>
						</div>

						<div id="pSecondTried" class="panel-body collapse">

							<c:forEach items="${triedUser2Complexity}" var="entry">
								<c:if test="${entry.value.size() > 0}">
									<div class="panel panel-warning">
										<div class="panel-heading">
											<spring:message code="fieldhdr.probtriedonlyby" />
											<a
												href="<c:url value="useraccount.xhtml?username=${user2}"/>">${user2}</a>
											in <a
												href="/24h/problems.xhtml?classification=${classification}&complexity=${entry.key}">${classifName}
												${entry.key}</a>
											<div class="badge">${entry.value.size()}</div>
											<div class="badge pull-right">
												<a data-toggle="collapse" href="#tried${user2}${entry.key}"><i
													class="fa fa-chevron-up"></i></a>
											</div>
										</div>

										<div id="tried${user2}${entry.key}"
											class="panel-body collapse in">
											<c:forEach items='${entry.value}' var="pc">
												<div class="col-xs-1 margin-top-05">
													<a href="/24h/problem.xhtml?pid=${pc.pid}"
														title="${pc.problemTitle}"> <span
														class="badge alert-warning"> ${pc.pid} </span>
													</a>
												</div>
											</c:forEach>
										</div>
									</div>
								</c:if>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
		</c:if>

		<c:if test="${classification == -1}">
			<!-- firstAC -->
			<div class="row margin-top-05">
				<div class="col-xs-12">
					<div class="panel panel-success">
						<div class="panel-heading">
							<spring:message code="fieldhdr.probsolvedonlyby" />
							<a href="<c:url value="useraccount.xhtml?username=${user1}"/>">${user1}</a>
							<div class="badge">${allonlybyuser1.size()}</div>
							<div class="badge pull-right">
								<a data-toggle="collapse" href="#pAllFirstAC"><i
									class="fa fa-chevron-down"></i></a>
							</div>
						</div>

						<div id="pAllFirstAC" class="panel-body collapse">

							<c:forEach items="${allonlybyuser1}" var="pc">
								<div class="col-xs-1 margin-top-05">
									<a href="/24h/problem.xhtml?pid=${pc.pid}"
										title="${pc.richTitle}"> <span class="badge alert-success">
											${pc.pid} </span>
									</a>
								</div>
							</c:forEach>

						</div>
					</div>
				</div>
			</div>

			<!-- Both -->
			<div class="row margin-top-05">
				<div class="col-xs-12">
					<div class="panel panel-info">
						<div class="panel-heading">
							<spring:message code="fieldhdr.probsolvedbyboth" />
							<div class="badge">${allbyboth.size()}</div>
							<div class="badge pull-right">
								<a data-toggle="collapse" href="#AllbothAC"><i
									class="fa fa-chevron-down"></i></a>
							</div>
						</div>

						<div id="AllbothAC" class="panel-body collapse">

							<c:forEach items="${allbyboth}" var="pc">

								<div class="col-xs-1 margin-top-05">
									<a href="/24h/problem.xhtml?pid=${pc.pid}"
										title="${pc.richTitle}"> <span class="badge alert-info">
											${pc.pid} </span>
									</a>
								</div>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>

			<!-- secondAC -->
			<div class="row margin-top-05">
				<div class="col-xs-12">
					<div class="panel panel-success">
						<div class="panel-heading">
							<spring:message code="fieldhdr.probsolvedonlyby" />
							<a href="<c:url value="useraccount.xhtml?username=${user2}"/>">${user2}</a>
							<div class="badge">${allonlybyuser2.size()}</div>
							<div class="badge pull-right">
								<a data-toggle="collapse" href="#pAllSecondAC"><i
									class="fa fa-chevron-down"></i></a>
							</div>
						</div>

						<div id="pAllSecondAC" class="panel-body collapse">

							<c:forEach items="${allonlybyuser2}" var="pc">
								<div class="col-xs-1 margin-top-05">
									<a href="/24h/problem.xhtml?pid=${pc.pid}"
										title="${pc.richTitle}"> <span class="badge alert-success">
											${pc.pid} </span>
									</a>
								</div>
							</c:forEach>

						</div>
					</div>
				</div>
			</div>

			<!-- All First tried -->
			<div class="row margin-top-05">
				<div class="col-xs-12">
					<div class="panel panel-warning">
						<div class="panel-heading">
							<spring:message code="fieldhdr.probtriedonlyby" />
							<a href="<c:url value="useraccount.xhtml?username=${user1}"/>">${user1}</a>
							<div class="badge">${allTriedUser1.size()}</div>
							<div class="badge pull-right">
								<a data-toggle="collapse" href="#pAllFirstTried"><i
									class="fa fa-chevron-down"></i></a>
							</div>
						</div>

						<div id="pAllFirstTried" class="panel-body collapse">

							<c:forEach items="${allTriedUser1}" var="pc">
								<div class="col-xs-1 margin-top-05">
									<a href="/24h/problem.xhtml?pid=${pc.pid}"
										title="${pc.richTitle}"> <span class="badge alert-warning">
											${pc.pid} </span>
									</a>
								</div>
							</c:forEach>

						</div>
					</div>
				</div>
			</div>

			<!-- All Both tried -->
			<div class="row margin-top-05">
				<div class="col-xs-12">
					<div class="panel panel-danger">
						<div class="panel-heading">
							<spring:message code="fieldhdr.probtriedbyboth" />
							<div class="badge">${allTriedBoth.size()}</div>
							<div class="badge pull-right">
								<a data-toggle="collapse" href="#pAllBothTried"><i
									class="fa fa-chevron-down"></i></a>
							</div>
						</div>

						<div id="pAllBothTried" class="panel-body collapse">

							<c:forEach items="${allTriedBoth}" var="pc">
								<div class="col-xs-1 margin-top-05">
									<a href="/24h/problem.xhtml?pid=${pc.pid}"
										title="${pc.richTitle}"> <span class="badge alert-danger">
											${pc.pid} </span>
									</a>
								</div>
							</c:forEach>

						</div>
					</div>
				</div>
			</div>


			<!-- All Second tried -->
			<div class="row margin-top-05">
				<div class="col-xs-12">
					<div class="panel panel-warning">
						<div class="panel-heading">
							<spring:message code="fieldhdr.probtriedonlyby" />
							<a href="<c:url value="useraccount.xhtml?username=${user2}"/>">${user2}</a>
							<div class="badge">${allTriedUser2.size()}</div>
							<div class="badge pull-right">
								<a data-toggle="collapse" href="#pAllSecondTried"><i
									class="fa fa-chevron-down"></i></a>
							</div>
						</div>

						<div id="pAllSecondTried" class="panel-body collapse">

							<c:forEach items="${allTriedUser2}" var="pc">
								<div class="col-xs-1 margin-top-05">
									<a href="/24h/problem.xhtml?pid=${pc.pid}"
										title="${pc.richTitle}"> <span class="badge alert-warning">
											${pc.pid} </span>
									</a>
								</div>
							</c:forEach>

						</div>
					</div>
				</div>
			</div>


		</c:if>


	</c:if>

</div>

<script>
	$(function() {
		$('.fa-chevron-up').click(function() {
			$(this).toggleClass('fa-chevron-up');
			$(this).toggleClass('fa-chevron-down');
		});
	});

	//frankr addtion start
	$(function() {
		$('.fa-chevron-down').click(function() {
			$(this).toggleClass('fa-chevron-up');
			$(this).toggleClass('fa-chevron-down');
		});
	});
	//frankr addition end
</script>

<!-- frankr addition end -->