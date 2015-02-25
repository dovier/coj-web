<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<script  type="text/javascript" src="/js/edit_area/edit_area_full.js"></script>
<%@taglib prefix="authz" uri="http://www.springframework.org/security/tags" %>


<h2 class="postheader" style="clear: both">
    <a class="linkheader" href="<c:url value="contestview.xhtml?cid=${contest.cid}"/>">${contest.name}</a>
    <br>
    <spring:message code="pagehdr.submit"/>
</h2>
<div class="row postcontent">
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
					<form:errors path="pid" />
				</div>
				<a><i class="fa fa-asterisk"
					title="<spring:message code="mandatory.field"/>"></i></a>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-3"><spring:message
						code="fieldhdr.proglanguage" /></label>
				<div class="col-xs-8">
					<form:select cssClass="form-control" path="key" id="lang" onchange="updSelect()">
						<form:options items="${languages}" itemLabel="descripcion"
							itemValue="key" />
					</form:select>
				</div>
				<div class="error col-xs-8 col-xs-offset-3">
					<form:errors path="lid" />
				</div>
			</div>
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
				<div class="form-actions pull-right">
					<input class="btn btn-primary" type="submit"
						value="<spring:message code="button.submit"/>" name="sub" /> <input
						class="btn btn-primary" type="reset"
						value="<spring:message code="button.reset"/>" name="reset" />
				</div>
			</div>
		</form:form>
		<!-- /article-content -->
	</div>
</div>

<script type="text/javascript">
	var enable = ${enableadveditor};
	activate_editor(enable);
	
	$("#uploadfile").fileinput({
		maxFileSize : 100,
		allowedFileTypes: ['text','object'],
		removeClass : "btn btn-default",
		removeLabel : "Delete",
		previewFileType: 'text',
		msgProgress : 'Loading {percent}%',
		browseClass : "btn btn-primary",
		
		browseLabel : "Pick File",
		browseIcon : '<i class="fa fa-file"></i>&nbsp;',
		removeIcon : '<i class="fa fa-trash"></i>'
	});
</script>