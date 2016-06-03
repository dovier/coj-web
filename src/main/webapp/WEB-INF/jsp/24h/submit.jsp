<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<jsp:useBean id="FileUtils" class="org.apache.commons.io.FileUtils" />
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
<%-- 						<th><spring:message code="tablehdr.time" /></th> --%>
						<th><spring:message code="tablehdr.mem" /></th>
<%-- 						<th><spring:message code="tablehdr.size" /></th> --%>
					</tr>
				</thead>
				<tr>
					<td align="center"><label class=<c:if test="${testSubmit.status eq 'Accepted'}">"subAC"</c:if> 
									 <c:if test="${!(testSubmit.status eq 'Accepted')}">"subWA"</c:if> >
							${testSubmit.status} </label></td>
<%-- 					<td><c:if test="${testSubmit.timeUsed == -1}"> --%>
<!-- 				... -->
<%-- 			</c:if> <c:if test="${testSubmit.timeUsed != -1}"> --%>
<%-- 				${testSubmit.timeUsed} --%>
<%-- 			</c:if></td> --%>
 					<td align="center">${testSubmit.memoryMB}</td> 
<%-- 					<td>${FileUtils.byteCountToDisplaySize(submit.code.length())}</td>  --%>
				</tr>
				<c:if test="${ not empty testSubmit.errMsg }">
				<tr>
					<th class="col-xs-2">Error</th>
					<td colspan="3">${testSubmit.errMsg}</td>
				</tr>
				</c:if>
			</table>

		<c:if
 			test="${!(testSubmit.status eq 'Compilation Error') and !(testSubmit.status eq 'Judging') and !(testSubmit.status eq 'Internal Error') and !(testSubmit.status eq 'Invalid Function')}"> 			 
			<table class="volume">
				<thead>
					<th><spring:message code="tablehdr.tests" /></th>
					<th><spring:message code="tablehdr.totaltime" /></th>
					<th><spring:message code="tablehdr.avetime" /></th>
					<th><spring:message code="tablehdr.mintesttime" /></th>
					<th><spring:message code="tablehdr.maxtesttime" /></th>
				</thead>
				<tr>
					<td width="16%">${testSubmit.acTestCases} 		<c:if test="${testSubmit.totalTestCases > 0}">
						 / ${testSubmit.totalTestCases} 
						 (<b><fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" 
						 value="${(testSubmit.acTestCases / testSubmit.totalTestCases * 100.0)}"/></b>)
					</c:if> </td>
					<td width="16%"><c:if test="${testSubmit.status eq 'Accepted'}">${testSubmit.timeUsed}</c:if>
					                <c:if test="${!(testSubmit.status eq 'Accepted')}">...</c:if></td>
					<td width="17%"><c:if test="${testSubmit.status eq 'Accepted'}">${testSubmit.avgTimeUsed}</c:if>
					                <c:if test="${!(testSubmit.status eq 'Accepted')}">...</c:if></td>
					<td width="17%">${testSubmit.minTimeUsed}</td>
					<td width="17%">${testSubmit.maxTimeUsed}</td>
				</tr>
			</table>
		</c:if>
		
		<c:if
 			test="${testSubmit.totalTestCases > 0}"> 
	
			<div class="">
				<div class="panel panel-primary">
					<div class="panel-heading">
					
						<div class="badge pull-left"><spring:message code="fieldhdr.verdictsdetails" /> </div>
						<div class="badge pull-right">
							<a data-toggle="collapse" href="#details"><i
								class="fa fa-chevron-down"></i></a>
						</div>
						<br />
					</div>
					<div id="details" class="panel-body collapse">
						<table class="volume" width='100%'>
							<thead>
								<th><spring:message code="tablehdr.testnum" /></th>
								<th class="result"><spring:message code="tablehdr.judgment" /></th>
								<th><spring:message code="tablehdr.time" /></th>
								<th><spring:message code="tablehdr.mem" /></th>
							</thead>
			
							<c:forEach items="${testSubmit.datasetVerdicts}" var="dv" varStatus="loop">
								<tr>
									<td><c:out value="${loop.index + 1}" /></td>
									<td><label class=<c:if test="${dv.verdict.associatedMessage() eq 'Accepted'}">"subAC"</c:if>
													 <c:if test="${!(dv.verdict.associatedMessage() eq 'Accepted')}">"subWA"</c:if>	> 
										<c:out value="${dv.verdict.associatedMessage()}" />
										</label></td>
									<td><c:out value="${dv.userTime}" /> <c:if
										test="${!empty dv.userTime}">ms</c:if><c:if
										test="${empty dv.userTime}">...</c:if></td>
									<td><c:if
										test="${!empty dv.memory}"> <c:out value="${FileUtils.byteCountToDisplaySize(dv.memory)}" /></c:if><c:if
										test="${empty dv.memory}">...</c:if></td>
								</tr>
							</c:forEach>
						</table>			
					</div>

				</div>
			</div>			
		</c:if>
			
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
				<a><i data-toggle="tooltip" class="fa fa-asterisk"
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
			<authz:authorize ifAnyGranted="ROLE_ADMIN,ROLE_SUPER_PSETTER,ROLE_PSETTER">
				<div class="form-group col-xs-12">
					<label class="control-label col-xs-3"><spring:message
							code="fieldhdr.test" /></label>
					<div class="col-xs-9	">
						<form:checkbox cssClass="checkbox" path="test" />
					</div>
				</div>
			</authz:authorize>
                                <div class="clearfix" > </div>
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
                                
				<a><i data-toggle="tooltip" class="fa fa-asterisk"
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
	$("#uploadfile").fileinput({
		maxFileSize : 100,
		allowedFileTypes : [ 'text', 'object' ],
		removeClass : "btn btn-default",
		removeLabel : "<spring:message code="tablehdr.delete"/>",
		previewFileType : 'text',
		msgProgress : 'Loading {percent}%',
		browseClass : "btn btn-primary",

		browseLabel : "<spring:message code="message.filename"/>",
		browseIcon : '<i class="fa fa-file"></i>&nbsp;',
		removeIcon : '<i class="fa fa-trash"></i>',
		msgValidationError: "<spring:message code="message.filename.uploaderror"/>",
		msgSizeTooLarge: '<spring:message code="message.filename.largeerror"/>'
	});
</script>

<script>
	var enable = $
	{
		enableadveditor
	};
	activate_editor(enable);

</script>
<script>    
$(function(){
		$("[data-toggle='tooltip']").tooltip();	
	});
</script> 