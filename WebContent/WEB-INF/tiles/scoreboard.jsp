<%@page import="cu.uci.coj.utils.Utils"%>
<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr" lang="en-US"
	xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />

<title>
	<%
		try {
	%> <c:set var="title">
		<tiles:getAsString name="title" />
	</c:set> <spring:message code="${title}" argumentSeparator=","
		arguments="${user.username},${problem.title},${institution.name},${country.name},${contest.name}" />
	<%
		} catch (Exception e) {
	%> <c:out value="COJ" /> <%
 	}
 %>
</title>

<link rel="shortcut icon"
	href="<c:url value="/images/coj_favicon.png"/>" />
<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>"
	type="text/css" media="screen" />
<link rel="stylesheet" href="<c:url value="/css/font-awesome.min.css"/>"
	type="text/css" media="screen" />
<link rel="stylesheet"
	href="<c:url value="/css/custom.bootstrap.tooltip.css"/>"
	type="text/css" media="screen" />

<link rel="stylesheet" href="<c:url value="/css/datatable.css"/>"
	type="text/css" media="screen" />
<link rel="stylesheet" href="<c:url value="/css/style.css"/>"
	type="text/css" media="screen" />
<link rel="stylesheet" href="<c:url value="/css/main.css"/>"
	type="text/css" media="screen" />
<!--[if IE 6]>
        <link rel="stylesheet" href="<c:url value="/css/style.ie6.css"/>" type="text/css" media="screen" />
        <![endif]-->
<!--[if IE 7]>
            <link rel="stylesheet" href="<c:url value="/css/style.ie7.css"/>" type="text/css" media="screen" />
        <![endif]-->

<script type="text/javascript"
	src="<c:url value="/js/jquery.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/js/bootstrap.min.js"/>"></script>

<script type="text/javascript" src="<c:url value="/js/script.js"/>"></script>
</head>
<body>
	<div id="main">
		<div class="sheet">
			<div class="sheet-body">
				<tiles:insertAttribute name='header' />
				<div class="content-layout">
					<div class="content-layout-row">
						<div class="post">
							<div class="post-body">
							<div class="post-inner article">
										<c:if test="${contest != null and contest.initdate != null and (contest.past or contest.running or contest.coming)}">
											<div class="pull-right">
												<table class="contestlanguages" style="align:right">
													<tbody>
														<tr>
															<td><b><spring:message code="fieldhdr.status" />: </b></td>
															<td><c:choose>
																	<c:when test="${contest.running == true}">
																		<span class="label label-success"><spring:message code="fieldhdr.running" /></span>
																	</c:when>
																	<c:when test="${contest.coming == true}">
																		<span class="label label-danger"><spring:message code="fieldhdr.coming" /></span>
																	</c:when>
																	<c:when test="${contest.past == true}">
																		<spring:message code="fieldhdr.past" />
																	</c:when>
																</c:choose></td>
															<c:if test="${contest.running}">

																<td><b><spring:message code="fieldhdr.elapsed" />: </b></td>
																<td>${contest.elapsed}</td>
																<td><b><spring:message code="fieldhdr.remaining" />: </b></td>
																<td>${contest.remaining}</td>

															</c:if>
															<c:if test="${contest.past}">

																<td><b><spring:message code="fieldhdr.start" />:</b></td>
																<td><fmt:formatDate value="${contest.initdate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
																<td><b><spring:message code="fieldhdr.end" />: </b></td>
																<td><fmt:formatDate value="${contest.enddate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
															</c:if>
														</tr>
														<c:if test="${contest.running == true && (contest.full_frozen == true || contest.frozen == true)}">
															<tr>
																<td colspan="6" style="text-align: right"><c:choose>
																		<c:when test="${contest.full_frozen == true}">
																			<span class="label label-danger"><i class="fa fa-warning"></i>&nbsp;<spring:message code="text.deadtime" /></span>
																		</c:when>
																		<c:otherwise>
																			<span class="label label-primary"><i class="fa fa-info-circle"></i>&nbsp;<spring:message code="text.frozentime" /></span>
																		</c:otherwise>
																	</c:choose></td>
															</tr>
														</c:if>

													</tbody>
												</table>
											</div>
											<br />
										</c:if>
										<div class="clearfix"></div>
								<div class="post-inner article">
									<tiles:insertAttribute name='content' />
									<div class="cleared"></div>
								</div>

								<div class="cleared"></div>
							</div>
						</div>
					</div>
				</div>
				<div class="cleared"></div>
				<div class="footer">
					<div class="footer-inner">
						<div class="footer-text">
							<tiles:insertAttribute name='footer' />
						</div>
					</div>
				</div>
				<div class="cleared"></div>
			</div>
		</div>
		<div class="cleared"></div>
	</div>

</body>
</html>