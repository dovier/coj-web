<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h2 class="postheader">
	<spring:message code="pagehdr.24hjudgments" />
</h2>
<div class="postcontent">
	<!-- article-content -->

	<div class="row">
		<div class="col-xs-12">
			<form action="/tables/status.xhtml" method="get" id="filter-form"
				class="form-inline">
				<div class="form-group">
					<c:choose>
						<c:when test="${not empty filter.username}">
							<input class="form-control"
								placeholder="<spring:message code="fieldhdr.user" />"
								type="text" name="username" value="${filter.username}" size="10" />
						</c:when>
						<c:otherwise>
							<input class="form-control"
								placeholder="<spring:message code="fieldhdr.user" />"
								type="text" name="username" value="${username}" size="10" />
						</c:otherwise>
					</c:choose>
				</div>


				<div class="form-group">
					<input class="form-control"
						placeholder="<spring:message code="fieldhdr.prob" />" type="text" pattern="/\d*/"
						name="pid" value="${filter.pid}" size="8" />
				</div>

				<div class="form-group">
					<select class="form-control" name="status">
						<option value="All"><spring:message code="fieldhdr.all" /></option>
						<c:forEach items="${statuslist}" var="status">
							<c:choose>
								<c:when test="${filter.current_status eq status.key}">
									<option value="${status.key}" selected>${status.key}</option>
								</c:when>
								<c:otherwise>
									<option value="${status.key}">${status.key}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</div>

				<div class="form-group">
					<select class="form-control" name="planguage">
						<option value="All"><spring:message code="fieldhdr.all" /></option>
						<c:forEach items="${filter.languages}" var="language">
							<c:choose>
								<c:when test="${filter.language eq language.key}">
									<option value="${language.key}" selected>${language.language}
										(${language.descripcion})</option>
								</c:when>
								<c:otherwise>
									<option value="${language.key}">${language.language}
										(${language.descripcion})</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</div>

				<div class="form-group">
					<input class="btn btn-primary" type="submit" id="filter-button"
						value="<spring:message code="button.filter"/>" />
				</div>
			</form>
		</div>
	</div>

	<div id="display-table-container"
		data-reload-url="/tables/status.xhtml"></div>
	<!-- /article-content -->
</div>

<script>
	$(initStandardFilterForm);
</script>