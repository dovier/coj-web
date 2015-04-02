<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<script type="text/javascript"
	src="<c:url value="/js/edit_area/edit_area_full.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/coj.js" />"></script>

<h2 class="postheader">
	<spring:message code="pagehdr.24hsubmit" />
</h2>
<c:if test="${not empty testSubmit}">
	<div class="row">
		<div class="col-xs-12">
			<table class="table table-condensed">
				<thead>
					<tr>
						<th><spring:message code="tablehdr.judgment" /></th>
						<th><spring:message code="tablehdr.time" /></th>
						<th><spring:message code="tablehdr.mem" /></th>
						<th><spring:message code="tablehdr.size" /></th>
					</tr>
				</thead>
				<tr>
					<td><label class="sub${testSubmit.statusClass}">
							${testSubmit.status} </label></td>
					<td><c:if test="${testSubmit.timeUsed == -1}">
				...
			</c:if> <c:if test="${testSubmit.timeUsed != -1}">
				${testSubmit.timeUsed}
			</c:if></td>
					<td>${testSubmit.memoryMB}</td>
					<td>${testSubmit.fontMB}</td>
				</tr>
				<tr>
					<th class="col-xs-2">Error</th>
					<td colspan="3">${testSubmit.errMsg}</td>
			</table>
		</div>
	</div>

</c:if>
<div class="row">
	<div class="col-xs-10">
		<!-- article-content -->
		<form:form method="post" enctype="multipart/form-data"
			cssClass="form-horizontal" commandName="submit">
			<div class="form-group">
				<label class="control-label col-xs-3"><spring:message
						code="fieldhdr.problemid" /></label>
				<div class="col-xs-8">
					<form:input class="form-control" path="pid" />
				</div>
				<div class="error col-xs-8 col-xs-offset-3">
					<span class="label label-danger"><form:errors path="pid" /></span>
				</div>
				<a><i class="fa fa-asterisk"
					title="<spring:message code="mandatory.field"/>"></i></a>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-3"><spring:message
						code="fieldhdr.proglanguage" /></label>
				<div class="col-xs-8">
					<form:select cssClass="form-control" path="key" id="lang"
						onchange="updSelect()">
						<form:options items="${languages}" itemLabel="descripcion"
							itemValue="key" />
					</form:select>
				</div>
				<div class="error col-xs-8 col-xs-offset-3">
					<span class="label label-danger"><form:errors path="key" /></span>
				</div>
			</div>
			<authz:authorize ifAnyGranted="ROLE_ADMIN,ROLE_SUPER_PSETTER">
				<div class="form-group col-xs-12">
					<label class="control-label col-xs-3"><spring:message
							code="fieldhdr.test" /></label>
					<div class="col-xs-9	">
						<form:checkbox cssClass="checkbox" path="test" />
					</div>
				</div>
			</authz:authorize>
			<div class="form-group">
				<label class="control-label col-xs-3"> <spring:message
						code="fieldhdr.sourcecode" />
				</label>
				<div class="col-xs-8">
					<input id="uploadfile" name="uploadfile" type="file" class="file"
						data-show-upload="false">
				</div>
				<div class="error col-xs-8 col-xs-offset-3">
					<span class="label label-danger"><form:errors path="code" /></span>
				</div>
				<a><i class="fa fa-asterisk"
					title="<spring:message code="mandatory.field"/>"></i></a>
			</div>
			<div class="form-group">
				<div class="col-xs-12">
					<form:textarea cols="100" rows="25" id="code" path="code"
						cssClass="form-control submit" tabindex="2" />
				</div>
			</div>
			<div class="form-actions pull-right">
				<input class="btn btn-primary" type="submit"
					value="<spring:message code="button.submit"/>" name="sub" /> <input
					class="btn btn-primary" type="reset"
					value="<spring:message code="button.reset"/>" name="reset" />
			</div>
		</form:form>
		<!-- /article-content -->
	</div>
</div>

<script>
	var enable = $
	{
		enableadveditor
	};
	activate_editor(enable);

	$("#uploadfile").fileinput({
		maxFileSize : 100,
		allowedFileTypes : [ 'text', 'object' ],
		removeClass : "btn btn-default",
		removeLabel : "Delete",
		previewFileType : 'text',
		msgProgress : 'Loading {percent}%',
		browseClass : "btn btn-primary",

		browseLabel : "Pick File",
		browseIcon : '<i class="fa fa-file"></i>&nbsp;',
		removeIcon : '<i class="fa fa-trash"></i>'
	});
</script>