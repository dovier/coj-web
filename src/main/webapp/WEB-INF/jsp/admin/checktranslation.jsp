<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<link rel="stylesheet" href="<c:url value="/css/confirm-message.css"/>"
	type="text/css" media="screen" />

<h2 class="postheader">
	<spring:message code="page.general.admin.header" />: <fmt:message key="pagetit.checktranslation" />
</h2>
<div class="row postcontent">
	<div class="col-xs-12">
		<form:form method="post" commandName="translation" cssClass="translation">
			<div class="form-group col-xs-12">
				<label class="control-label col-xs-3">PID</label>
				<div class="col-xs-8">
					<a class="form-control" href="/24h/problem.xhtml?pid=${translation.pid}">${translation.pid}</a>
					<form:hidden cssClass="form-control" path="pid" />
				</div>
			</div>	
			
			<div class="form-group col-xs-12">
				<label class="control-label col-xs-3"><fmt:message
						key="fieldhdr.idioma" /></label>
				<div class="col-xs-8">
					<form:select path="locale" cssClass="form-control">
						<form:options items="${locales}" /> 
					</form:select>
				</div>
			</div>	
		
			<div class="form-group col-xs-12">	
				<label class="control-label col-xs-3"><fmt:message
						key="addproblem.name" /></label>
				<div class="col-xs-8">
					<form:input cssClass="form-control" path="title" />
				</div>
			</div>

			<div class="form-group col-xs-12">
				<label class="control-label col-xs-3"><fmt:message
						key="addproblem.description" /></label>
				<form:textarea cssClass="form-control" path="description"
					id="code" rows="15" />
			</div>

			<div class="form-group col-xs-12">
				<label class="control-label col-xs-3"><fmt:message
						key="addproblem.input" /></label>
				<form:textarea cssClass="form-control in" path="input" id="input"
					rows="15" />
			</div>

			<div class="form-group col-xs-12">
				<label class="control-label col-xs-3"><fmt:message
						key="addproblem.output" /></label>
				<form:textarea cssClass="form-control out" path="output" id="output"
					 rows="15" />
			</div>

			<div class="form-group col-xs-12">
				<label class="control-label col-xs-3"><fmt:message
						key="addproblem.comm" /></label>
				<form:textarea cssClass="form-control in " path="comments"
					id="hint" rows="15" />
			</div>
			
			<div class="col-xs-12">
				<div class="form-actions pull-right ">
					<input class="btn btn-primary confirm-message" type="submit"
						value='<spring:message code="button.approve"/>' />
						
					<a class="btn btn-primary confirm-message"
						href="/admin/managetranslations/delete.xhtml?id=${translation.id}"><spring:message code="button.reject"/></a>
						
					<input class="btn btn-primary" type="reset"
						value='<spring:message code="button.reset"/>' />
						
					<a class="btn btn-primary"
						href="/admin/managetranslations.xhtml"><spring:message code="button.close"/></a>
				</div>
			</div>
		</form:form>
	</div>
</div>


<script type="text/javascript"
	src="<c:url value="/js/WYSIWYG/source.js" />"></script>
	
<script>
	$(function() {
	
	});
</script>