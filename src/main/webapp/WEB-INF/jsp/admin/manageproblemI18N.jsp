<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h2 class="postheader">
	<fmt:message key="page.general.admin.header" />: <fmt:message key="addproblem.i18n" />
</h2>
<div class="row postcontent">
	<div class="col-xs-12">
		<form:form method="post" commandName="problem">
			<div class="form-group col-xs-12">
				<label class="control-label col-xs-3">PID</label>
				<div class="col-xs-8">
					<form:input cssClass="form-control" path="pid" readonly="true"></form:input>
					<span class="label label-danger"><form:errors path="pid" /></span>
				</div>
                                <a><i class="fa fa-asterisk" data-toggle="tooltip"
                                      title="<spring:message code="mandatory.field"/>">
                                    
                                    </i></a>
			</div>
			<div class="form-group col-xs-12">
				<ul class="nav nav-tabs">
					<li class="active"><a data-toggle="tab" href="#en"><fmt:message
								key="titval.en" /></a></li>
					<li><a data-toggle="tab" href="#es"><fmt:message
								key="titval.es" /></a></li>
					<li><a data-toggle="tab" href="#pt"><fmt:message
								key="titval.pt" /></a></li>
				</ul>
			</div>

			<div id="tabs" class="tab-content margin-top-05">
				<div class="tab-pane fade in active" id="en">
					<div class="form-group col-xs-12">

						<label class="control-label col-xs-3"><fmt:message
								key="addproblem.name" /></label>
                                                                                                
                                                                                <div class="col-xs-8">
							<form:input cssClass="form-control" path="title" />
							<span class="label label-danger"><form:errors path="title" /></span>
						</div>
						<a><i class="fa fa-asterisk" data-toggle="tooltip"
					title="<spring:message code="mandatory.field"/>"></i></a>
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
								key="addproblem.output" /></label> <span class="label label-danger"><form:errors
								path="output" /></span>
						<form:textarea cssClass="form-control out" path="output"
							id="output" rows="15" />
					</div>

					<div class="form-group col-xs-12">
						<label class="control-label col-xs-3"><fmt:message
								key="addproblem.comm" /></label>
						<form:textarea cssClass="form-control in " path="comments"
							id="hint" rows="15" />
					</div>
				</div>
				<div class="tab-pane fade in active" style="position:fixed;left:10000px;"  id="es">
					<div class="form-group col-xs-12">

						<label class="control-label col-xs-3"><fmt:message
								key="addproblem.name" /></label>
                                               
                                                
                                        
						<div class="col-xs-8">
							<form:input cssClass="form-control" path="titleEs" />
							<span class="label label-danger"><form:errors
									path="titleEs" /></span>
						</div>
						<a><i class="fa fa-asterisk" data-toggle="tooltip"
					title="<spring:message code="mandatory.field"/>"></i></a>
					</div>

					<div class="form-group col-xs-12">
						<label class="control-label col-xs-3"><fmt:message
								key="addproblem.description" /></label>
						<form:textarea cssClass="form-control" path="descriptionEs"
							id="codeEs" rows="15" />
					</div>

					<div class="form-group col-xs-12">
						<label class="control-label col-xs-3"><fmt:message
								key="addproblem.input" /></label>
						<form:textarea cssClass="form-control in" path="inputEs"
							id="inputEs" rows="15" />
					</div>

					<div class="form-group col-xs-12">
						<label class="control-label col-xs-3"><fmt:message
								key="addproblem.output" /></label> <span class="label label-danger"><form:errors
								path="outputEs" /></span>
						<form:textarea cssClass="form-control out" path="outputEs"
							id="outputEs" rows="15" />
					</div>

					<div class="form-group col-xs-12">
						<label class="control-label col-xs-3"><fmt:message
								key="addproblem.comm" /></label>
						<form:textarea cssClass="form-control in " path="commentsEs"
							id="hintEs" rows="15" />
					</div>
				</div>
				<div class="tab-pane fade in active" style="position:fixed;left:10000px;"  id="pt">
					<div class="form-group col-xs-12">
						<label class="control-label col-xs-3"><fmt:message
								key="addproblem.name" /></label>
						<div class="col-xs-8">
							<form:input cssClass="form-control" path="titlePt" />
							<span class="label label-danger"><form:errors
									path="titlePt" /></span>
						</div>
						<a><i class="fa fa-asterisk" data-toggle="tooltip"
					title="<spring:message code="mandatory.field"/>"></i></a>
					</div>
					<div class="form-group col-xs-12">
						<label class="control-label col-xs-3"><fmt:message
								key="addproblem.description" /></label>
						<form:textarea cssClass="form-control" path="descriptionPt"
							id="codePt" rows="15" />
					</div>
					<div class="form-group col-xs-12">
						<label class="control-label col-xs-3"><fmt:message
								key="addproblem.input" /></label>
						<form:textarea cssClass="form-control in" path="inputPt"
							id="inputPt" rows="15" />
					</div>
					<div class="form-group col-xs-12">
						<label class="control-label col-xs-3"><fmt:message
								key="addproblem.output" /></label> <span class="label label-danger"><form:errors
								path="outputPt" /></span>
						<form:textarea cssClass="form-control out" path="outputPt"
							id="outputPt" rows="15" />
					</div>
					<div class="form-group col-xs-12">
						<label class="control-label col-xs-3"><fmt:message
								key="addproblem.comm" /></label>
						<form:textarea cssClass="form-control in " path="commentsPt"
							id="hintPt" rows="15" />
					</div>
				</div>
			</div>
			<div class="col-xs-12">
				<div class="form-actions pull-right ">
					<input class="btn btn-primary" type="submit" name="but"
						value="<spring:message code="button.update1"/>" />
				</div>
			</div>
		</form:form>
	</div>
</div>
<script type="text/javascript"
	src="<c:url value="/js/WYSIWYG/source.js" />"></script>

<script>

	$(".nav-tabs li").on("click", function(){
		$(".tab-pane").each(function(){
			$(this).css("position", "static");	
		});
	});	
	
</script>
<script>
    $("[data-toggle='tooltip']").tooltip();
</script>