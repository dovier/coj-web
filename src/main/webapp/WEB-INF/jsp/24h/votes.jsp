<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<link rel="stylesheet" href="<c:url value="/js/recommenderVoting/jRating.jquery.css" />" type="text/css" />
<script type="text/javascript" src="<c:url value="/js/jquery.js" />"></script>
<script type="text/javascript" src="<c:url value="/js/recommenderVoting/jRating.jquery.js" />">
	
</script>

<script type="text/javascript">
	$(document).ready(function() {
		$('.votes').jRating({
			showRateInfo : false,
			step : false,
			length : 5,
			decimalLength : 1,
			type : 'big'
		});

		$('.votes2').jRating({
			showRateInfo : false,
			step : false,
			length : 5,
			decimalLength : 1,
			type : 'big',
			isDisabled : true
		});
	});
</script>

<h2 class="postheader">
	<spring:message code="pagehdr.problem" />
	<a href="<c:url value="problem.xhtml?pid=${problem.pid}"/>" style="color: #4c83c3;"> ${problem.title}</a> <br />
	<spring:message code="votes.header" />
</h2>


<div class="postcontent">

	<authz:authorize ifAllGranted="ROLE_USER">
		<c:choose>
			<c:when test="${voted == false}">
				<form method="post" action="voting.xhtml">
					<input name="pid" type="hidden" value="${problem.pid}" />
					<table id="tbl_votes">
						<tr>
							<td><p>
									<spring:message code="votes.message.1" />
								<p></td>
							<td><a><i data-toggle="tooltip" class="fa fa-info-circle" title="<spring:message code="altval.vote.1"/>"></i></a></td>
							<td><div class="votes" data-average="0" data-id="1"></div>
								<input name="vote1" id="vote1" type="hidden" value="0" /></td>
						</tr>
						<tr>
							<td><p>
									<spring:message code="votes.message.2" />
								<p></td>
							<td><a><i data-toggle="tooltip" class="fa fa-info-circle" title="<spring:message code="altval.vote.2"/>"></i></a></td>
							<td><div class="votes" data-average="0" data-id="2"></div>
								<input name="vote2" id="vote2" type="hidden" value="0" /></td>
						</tr>
						<tr>
							<td><p>
									<spring:message code="votes.message.3" />
								<p></td>
							<td><a><i data-toggle="tooltip" class="fa fa-info-circle" title="<spring:message code="altval.vote.3"/>"></i></a></td>
							<td><div class="votes" data-average="0" data-id="3"></div>
								<input name="vote3" id="vote3" type="hidden" value="0" /></td>
						</tr>
						<tr>
							<td><p>
									<spring:message code="votes.message.4" />
								<p></td>
							<td><a><i data-toggle="tooltip" class="fa fa-info-circle" title="<spring:message code="altval.vote.4"/>"></i></a></td>
							<td><div class="votes" data-average="0" data-id="4"></div>
								<input name="vote4" id="vote4" type="hidden" value="0" /></td>
						</tr>
						<tr>
							<td><p>
									<spring:message code="votes.message.5" />
								<p></td>
							<td><a><i data-toggle="tooltip" class="fa fa-info-circle" title="<spring:message code="altval.vote.5"/>"></i></a></td>
							<td><div class="votes" data-average="0" data-id="5"></div>
								<input name="vote5" id="vote5" type="hidden" value="0" /></td>
						</tr>
					</table>

					<div id="btn_votar">
						<input name="submit" type="submit" value="<spring:message code="votes.btn"/>" />
					</div>
				</form>
			</c:when>
			<c:otherwise>

				<table id="tbl_votes">
					<tr>
						<td><p>
								<spring:message code="votes.message.1" />
							<p></td>
						<td><a><i data-toggle="tooltip" class="fa fa-info-circle" title="<spring:message code="altval.vote.1"/>"></i></a></td>
						<td><div class="votes2" data-average="${vote1}" data-id="1"></div>
							<input name="vote1" id="vote1" type="hidden" value="0" /></td>
						<td><p>${str_vote1}</p></td>
					</tr>
					<tr>
						<td><p>	
								<spring:message code="votes.message.2" /></p></td>
						<td><a><i data-toggle="tooltip" class="fa fa-info-circle" title="<spring:message code="altval.vote.2"/>"></i></a>
						</td>
						<td><div class="votes2" data-average="${vote2}" data-id="2"></div>
							<input name="vote2" id="vote2" type="hidden" value="0" /></td>
						<td><p>${str_vote2}</p></td>
					</tr>
					<tr>
						<td><p>
								<spring:message code="votes.message.3" /></p></td>
						<td><a><i data-toggle="tooltip" class="fa fa-info-circle" title="<spring:message code="altval.vote.3"/>"></i></a>
						</td>
						<td><div class="votes2" data-average="${vote3}" data-id="3"></div>
							<input name="vote3" id="vote3" type="hidden" value="0" /></td>
						<td><p>${str_vote3}</p></td>
					</tr>
					<tr>
						<td><p>
								<spring:message code="votes.message.4" /></p></td>
						<td><a><i data-toggle="tooltip" class="fa fa-info-circle" title="<spring:message code="altval.vote.4"/>"></i></a>
						</td>
						<td><div class="votes2" data-average="${vote4}" data-id="4"></div>
							<input name="vote4" id="vote4" type="hidden" value="0" /></td>
						<td><p>${str_vote4}</p></td>
					</tr>
					<tr>
						<td><p>
								<spring:message code="votes.message.5" /></p></td>
						<td><a><i data-toggle="tooltip" class="fa fa-info-circle" title="<spring:message code="altval.vote.5"/>"></i></a>
						</td>
						<td><div class="votes2" data-average="${vote5}" data-id="5"></div>
							<input name="vote5" id="vote5" type="hidden" value="0" /></td>
						<td><p>${str_vote5}</p></td>
					</tr>
				</table>

</c:otherwise>
</c:choose>

</authz:authorize>

</div>
