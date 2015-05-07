<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer="16kb" autoFlush="true"%>

<h2 class="postheader">
	<spring:message code="pagetit.listpolls" />
</h2>
<div class="postcontent">
	<c:forEach var="poll" items="${polls}">
		<div class="row">
			<div class="col-xs-12">
				<div class="panel panel-primary">
					<div class="panel-heading">${poll.question}
						<div class=" pull-right">
							<span class="badge"> <i class="fa fa-user"></i>
								${poll.voteTotal}
							</span>
						</div>
					</div>
					<div id="g${poll.pid}" class="panel-body">
						<c:if test="${not poll.voted}">
							<form method="post">
								<div class="col-xs-12">
									<c:if test="${not empty poll.answer1}">
										<div data-pid="${poll.pid}" data-option="1"
											class="btn btn-default col-xs-12">
											<p>${poll.answer1}</p>
											<div class="col-xs-12">
												<div class="progress">
													<div class="progress-bar" role="progressbar"
														style="width:${poll.vote1PC}%">
														<span>${poll.vote1PC}% (${poll.votes1})</span>
													</div>
												</div>
											</div>
										</div>
									</c:if>
									<c:if test="${not empty poll.answer2}">
										<div data-pid="${poll.pid}" data-option="2"
											class="margin-top-05 btn btn-default col-xs-12">
											<p>${poll.answer2}</p>
											<div class="col-xs-12">
												<div class="progress">
													<div class="progress-bar" role="progressbar"
														style="width:${poll.vote2PC}%">
														<span>${poll.vote2PC}% (${poll.votes2})</span>
													</div>
												</div>
											</div>
										</div>
									</c:if>
									<c:if test="${not empty poll.answer3}">
										<div data-pid="${poll.pid}" data-option="3"
											class="margin-top-05 btn btn-default col-xs-12">
											<p>${poll.answer3}</p>
											<div class="col-xs-12">
												<div class="progress">
													<div class="progress-bar" role="progressbar"
														style="width:${poll.vote3PC}%">
														<span>${poll.vote3PC}% (${poll.votes3})</span>
													</div>
												</div>
											</div>
										</div>
									</c:if>
									<c:if test="${not empty poll.answer4}">
										<div data-pid="${poll.pid}" data-option="4"
											class="margin-top-05 btn btn-default col-xs-12">
											<p>${poll.answer4}</p>
											<div class="col-xs-12 ">
												<div class="progress">
													<div class="progress-bar" role="progressbar"
														style="width:${poll.vote4PC}%">
														<span>${poll.vote4PC}% (${poll.votes4})</span>
													</div>
												</div>
											</div>
										</div>
									</c:if>
									<c:if test="${not empty poll.answer5}">
										<div data-pid="${poll.pid}" data-option="5"
											class="margin-top-05 btn btn-default col-xs-12">
											<p>${poll.answer5}</p>
											<div class="col-xs-12 ">
												<div class="progress">
													<div class="progress-bar" role="progressbar"
														style="width:${poll.vote5PC}%">
														<span>${poll.vote5PC}% (${poll.votes5})</span>
													</div>
												</div>
											</div>
										</div>
									</c:if>
								</div>
							</form>
						</c:if>
						<c:if test="${poll.voted}">
							<div class="col-xs-12">
								<div class=" col-xs-12">
									<p>${poll.answer1}</p>
									<div class="col-xs-12">
										<div class="progress">
											<div class="progress-bar" role="progressbar"
												style="width:${poll.vote1PC}%">
												<span>${poll.vote1PC}% (${poll.votes1})</span>
											</div>
										</div>
									</div>
								</div>
								<div class="margin-top-05 col-xs-12">
									<p>${poll.answer2}</p>
									<div class="col-xs-12">
										<div class="progress">
											<div class="progress-bar" role="progressbar"
												style="width:${poll.vote2PC}%">
												<span>${poll.vote2PC}% (${poll.votes2})</span>
											</div>
										</div>
									</div>
								</div>
								<div class="margin-top-05 col-xs-12">
									<p>${poll.answer3}</p>
									<div class="col-xs-12">
										<div class="progress">
											<div class="progress-bar" role="progressbar"
												style="width:${poll.vote3PC}%">
												<span>${poll.vote3PC}% (${poll.votes3})</span>
											</div>
										</div>
									</div>
								</div>
								<div class="margin-top-05 col-xs-12">
									<p>${poll.answer4}</p>
									<div class="col-xs-12 ">
										<div class="progress">
											<div class="progress-bar" role="progressbar"
												style="width:${poll.vote4PC}%">
												<span>${poll.vote4PC}% (${poll.votes4})</span>
											</div>
										</div>
									</div>
								</div>
								<div class="margin-top-05 col-xs-12">
									<p>${poll.answer5}</p>
									<div class="col-xs-12 ">
										<div class="progress">
											<div class="progress-bar" role="progressbar"
												style="width:${poll.vote5PC}%">
												<span>${poll.vote5PC}% (${poll.votes5})</span>
											</div>
										</div>
									</div>
								</div>
							</div>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</c:forEach>
</div>
<script>
	function enableVote() {
		$.ajax({
			type : "POST",
			url : "/poll/vote.xhtml",
			data : {
				pid : $(this).attr('data-pid'),
				option : $(this).attr('data-option')
			},
			success : function(data) {
				$("html").html(data);
				$(".btn").on("click", enableVote);
			},
			dataType : "html"
		});
	};

	$(function() {
		$(".btn").on("click", enableVote);
	});
</script>