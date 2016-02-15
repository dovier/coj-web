<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h2 class="postheader">
	<spring:message code="pagehdr.24hproblems" />
</h2>
<div class="postcontent">
	<c:if test="${message != null}">
	<div class="alert alert-success alert-dismissable fade in">
		<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
		<i class="fa fa-check"></i><spring:message code="${message}" />
	</div>
	</c:if>
	<!-- content -->
	<div class="row">
		<div class="col-xs-12">
			<form id="filter-form" class="form-inline">
				<div class="form-group">
					<input
						placeholder="<spring:message
					code="fieldhdr.searchproblems" />"
						type="text" class="form-control" size="25" name="pattern"
						value="${pattern}">
				</div>
				<div class="form-group">
					<select name="classification" class="form-control" data-toggle="tooltip" title="<spring:message code="filter.by.clasification"/>">
						<option value="-1"><spring:message code="fieldhdr.all" /></option>
						<c:forEach items="${classifications}" var="classif">
							<option value="${classif.idClassification}"
								<c:if test="${classification eq classif.idClassification}">selected</c:if>>${classif.name}</option>
						</c:forEach>
					</select>
				</div>
				<div class="form-group">
					<select class="form-control" name="complexity" data-toggle="tooltip" title="<spring:message code="filter.by.complexity"/>">
						<option value="-1"><spring:message code="fieldhdr.all" /></option>
						<c:forEach begin="1" end="5" var="complex">
							<option value="${complex}"
								<c:if test="${ complex eq complexity}">selected</c:if>>${complex}</option>
						</c:forEach>
					</select>
				</div>
				<authz:authorize ifAllGranted="ROLE_USER">
					<div class="form-group">
						<select class="form-control" name="filterby">
							<option value="0"><spring:message code="link.all" /></option>
							<option value="1"><spring:message code="link.solved" /></option>
							<option value="2"><spring:message code="link.unsolved" /></option>
							<option value="3"><spring:message code="link.untried" /></option>
							<option value="4"><spring:message code="link.favorities" /></option>
							<option value="5"><spring:message
									code="link.recommended" /></option>
						</select>
					</div>
				</authz:authorize>
				<div class="form-group">
					<input id="filter-button" type="submit" class="btn btn-primary"
						value="<spring:message code="button.filter"/>">
				</div>
			</form>
		</div>
	</div>
	<div class="row">
		<div class="col-xs-12">
			<div id="display-table-container"
				data-reload-url="/tables/problems.xhtml"></div>
		</div>
	</div>
</div>
<script>
$(initStandardFilterForm);
</script>