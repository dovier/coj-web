<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h2 class="postheader">
	<fmt:message key="pagehdr.faq" />
</h2>
<div class="postcontent">

	<c:forEach var="faq" items="${faqs}">
		<ul>
			<li><a href="#${faq.id}">${faq.question}</a></li>
		</ul>
	</c:forEach>
	<dl class="dl-vertical">
		<c:forEach var="faq" items="${faqs}">
			<dt>
				<a id="${faq.id}"></a>
				<h3>${faq.question}&nbsp;<a href="#header"
						title="<spring:message code="titval.top"/>"><i
						class="fa fa-toggle-up fa-lg"></i></a>
				</h3>
			</dt>
			<dd>
				<div>${faq.answer}</div>
			</dd>
		</c:forEach>
		<div class="row row-centered">
			<div class="col-xs-6 col-centered">
				<table class="table table-condensed table-striped">
					<thead>
						<tr>
							<th><fmt:message key="fieldhdr.proglanguages" /></th>
							<th><fmt:message key="fieldhdr.version" /></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="lang" items="${languages}">
							<tr>
								<td>${lang.language}</td>
								<td>${lang.descripcion}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
</div>
