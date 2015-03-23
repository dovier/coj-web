<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<link rel="stylesheet" href="<c:url value="/css/confirm-message.css"/>"
	type="text/css" media="screen" />
	
<h2 class="postheader">
	<fmt:message key="addproblem.title" />
</h2>
<div class="row">
	<form:form method="post" enctype="multipart/form-data"
		commandName="problem">
		<div class="form-group col-xs-12">
			<label class="control-label col-xs-3">PID</label>
			<div class="col-xs-8">
				<form:input id="pid" cssClass="form-control" path="pid"
					readonly="true"></form:input>
				<span class="label label-danger"><form:errors path="pid" /></span>
			</div>
			<a><i class="fa fa-asterisk"></i></a>
		</div>

		<div class="form-group col-xs-12">

			<label class="control-label col-xs-3"><fmt:message
					key="addproblem.name" /></label>
			<div class="col-xs-8">
				<form:input cssClass="form-control" path="title" />
				<span class="label label-danger"><form:errors path="title" /></span>
			</div>
			<a><i class="fa fa-asterisk"></i></a>
		</div>

		<div class="form-group col-xs-12">
			<label class="control-label col-xs-3"><fmt:message
					key="addproblem.description" /></label>
			<form:textarea cssClass="form-control" path="description" id="code"
				rows="15" />
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
			<form:textarea cssClass="form-control out" path="output" id="output"
				rows="15" />
		</div>

		<div class="form-group col-xs-12">
			<label class="control-label col-xs-3"><fmt:message
					key="addproblem.comm" /></label>
			<form:textarea cssClass="form-control in " path="comments" id="hint"
				rows="15" />
		</div>

		<div class="form-group col-xs-12">
			<label class="control-label col-xs-3"><fmt:message
					key="addproblem.inputex" /></label>
			<form:textarea cssClass="form-control in_ex" path="inputex"
				id="code3" rows="15" />
		</div>

		<div class="form-group col-xs-12">
			<label class="control-label col-xs-3"><fmt:message
					key="addproblem.outputex" /></label>
			<form:textarea cssClass="form-control out_ex" path="outputex"
				id="code4" rows="15" />
		</div>

		<div class="form-group col-xs-12">
			<label class="control-label col-xs-3"><fmt:message
					key="addproblem.forum" /></label>
			<div class="col-xs-8">
				<form:input cssClass="form-control in " path="forumLink"
					id="forumLink" />
			</div>
		</div>

		<div class="form-group col-xs-12">
			<label class="control-label col-xs-3"><fmt:message
					key="addproblem.author" /></label>
			<div class="col-xs-8">
				<form:select cssClass="form-control" path="author">
					<form:options items="${sources}" itemLabel="fullName"
						itemValue="fullName" />
				</form:select>
			</div>
		</div>

		<div class="form-group col-xs-12">
			<label class="control-label col-xs-3">Special Judge </label>
			<div class="col-xs-8">
				<form:checkbox cssClass="checkbox" path="special_judge" />
			</div>
		</div>

		<div class="form-group col-xs-12">
			<label class="control-label col-xs-3"> <fmt:message
					key="addproblem.casechk" />
			</label>
			<div class="col-xs-8">
				<form:checkbox cssClass="checkbox" path="multidata"
					onchange="onCase();" id="casecheck" />
			</div>
		</div>

		<div class="form-group col-xs-12">
			<label class="control-label col-xs-3"><fmt:message
					key="addproblem.casetime" /> <b>(MS)</b></label>
			<div class="col-xs-8">
				<c:choose>
					<c:when test="${problem.multidata == true}">
						<form:input cssClass="form-control" path="casetimelimit"
							id="casetime" onclick="" />
					</c:when>
					<c:otherwise>
						<form:input cssClass="form-control" path="casetimelimit"
							id="casetime" disabled="true" />
					</c:otherwise>
				</c:choose>
				<span class="label label-danger"><form:errors
						path="casetimelimit" /></span>
			</div>
		</div>
		<div class="form-group col-xs-12">
			<label class="control-label col-xs-3"><fmt:message
					key="addproblem.time" /> <b>(MS)</b></label>
			<div class="col-xs-8">
				<form:input cssClass="form-control" path="time" />
				<span class="label label-danger"><form:errors path="time" /></span>

			</div>
		</div>
		<div class="form-group col-xs-12">
			<label class="control-label col-xs-3"><fmt:message
					key="addproblem.memory" /> (B)</label>
			<div class="col-xs-8">
				<form:input cssClass="form-control" path="memory" />
				<span class="label label-danger"><form:errors path="memory" /></span>
			</div>
		</div>
		<div class="form-group col-xs-12">
			<label class="control-label col-xs-3"><fmt:message
					key="addproblem.source" /> (B)</label>
			<div class="col-xs-8">
				<form:input cssClass="form-control" path="fontsize" />
				<span class="label label-danger"><form:errors path="fontsize" /></span>
			</div>
		</div>
		<div class="form-group col-xs-12">
			<label class="control-label col-xs-3">Disable 24h</label>
			<div class="col-xs-8">
				<form:checkbox cssClass="checkbox" path="disable_24h" />
			</div>
		</div>
		<div class="form-group col-xs-12">
			<label class="control-label col-xs-3">Enable</label>
			<div class="col-xs-8">
				<form:checkbox cssClass="checkbox" path="enabled" />
			</div>
		</div>
		<div class="form-group col-xs-12" id="inputfiles">
			<label class="control-label col-xs-3"><fmt:message
					key="addproblem.inputgen" /></label>
			<div class="col-xs-8">
				<input type="file" name="inputgen" class="file" />
			</div>
		</div>
		<div class="form-group col-xs-12">
			<label class="control-label col-xs-3"><fmt:message
					key="addproblem.modelsol" /></label>
			<div class="col-xs-8">
				<input type="file" name="modelsol" class="file" />
			</div>
		</div>

		<div class="form-group col-xs-12">
			<div class="panel panel-primary">
				<div class="panel-heading">
					Datasets
					<div class="badge pull-right">
						<a data-toggle="collapse" href="#gDatasets"><i
							class="fa fa-chevron-up"></i></a>
					</div>
				</div>
				<div id="gDatasets" class="panel-body collapse in">
					<div class="form-group">
						<div class="margin-top-05 col-xs-12">
							<label class="control-label col-xs-3"><fmt:message
									key="addproblem.existing.datasets" /></label>
							<div id="datasets-container" class="col-xs-8">
								<c:forEach items="${datasets}" varStatus="loop">
									<div id="${datasets[loop.index]}-container"
										class="margin-top-05 col-xs-4">
										<div class="input-group">
											<input readonly="readonly" name="${datasets[loop.index]}"
												class="form-control" value="${datasets[loop.index]}" /><span
												class="input-group-btn">
												<button type="button" class="btn btn-primary"
													onclick="javascript:removedataset(${pid},'${datasets[loop.index]}');">
													<i class="fa fa-trash"></i>
												</button>
											</span>
										</div>
									</div>
								</c:forEach>
							</div>
							<div class="form-group col-xs-12">
								<div class="margin-top-05 pull-right form-actions">
									<input class="btn btn-primary confirm-message" type="button"
										id="deleteall"
										value='<fmt:message key="addproblem.deleteall" />'
										data-confirm-title='<spring:message code="message.title"/>'
										data-confirm-message='Delete all datasets'
										data-confirm-type="delete"
										data-redirect="#" />
								</div>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="model-dataset margin-top-05 col-xs-12">
							<label class="control-label col-xs-3">ZIP file (must NOT
								contain folders)</label>
							<div class="col-xs-4">
								<input id="zipfile" name="zipfile" type="file" class="file"
									data-show-upload="false" />
							</div>
						</div>
					</div>
					<div id="datasets" class="form-group margin-top-05"></div>
					<div class="form-group col-xs-12">
						<div class="margin-top-05 pull-right form-actions">
							<input class="btn btn-primary" type="button"
								style="margin: right" id="add" onclick="javascript:addInput();"
								value='<fmt:message key="addproblem.addinput" />' />
						</div>
					</div>
				</div>
			</div>
		</div>


		<div class="form-group col-xs-12">
			<label class="control-label col-xs-3"> <fmt:message
					key="page.advancedcfg.languages" />
			</label>
			<div class="col-xs-9 contestlanguages">
				<div class='col-xs-4'>
					<form:checkbox cssClass="checkbox" id="all"
						onchange="selection('all','languageids');" label="All" path=""
						value="" />
				</div>
				<div class='col-xs-4'>
					<form:checkboxes cssClass="checkbox" path="languageids"
						items="${languages}" itemValue="lid" itemLabel="descripcion"
						delimiter="</div><div class='col-xs-4'>" />
				</div>
				<span class="label label-danger"><form:errors
						path="languages" /></span>
			</div>
		</div>
		<c:if test="${showpsetters == true}">
			<div class="form-group col-xs-12">
				<label class="control-label col-xs-3"> <fmt:message
						key="page.advancedcfg.psetters" /> <a><i
						data-toggle="tooltip" class="fa fa-info-circle"
						title="<spring:message code="text.psetters"/>"></i></a>
				</label>
				<div class="col-xs-9 contestlanguages">
					<div class="col-xs-3">
						<form:checkbox cssClass="checkbox" id="all_psetters"
							onchange="selection('all_psetters','psettersids');" label="All"
							path="" value="" />
					</div>
					<div class="col-xs-3">
						<form:checkboxes cssClass="checkbox" path="psettersids"
							items="${psetters}" itemValue="uid" itemLabel="username"
							delimiter="</div><div class='col-xs-3'>" />
					</div>
					<span class="label label-danger"><form:errors
							path="psetters" /></span>
				</div>
			</div>
		</c:if>
		<div class="col-xs-12">
			<div class="form-actions pull-right ">
				<input class="btn btn-primary" type="submit" name="but"
					value="Update" />
			</div>
		</div>
	</form:form>
</div>
<%@include file="/WEB-INF/jsp/general/confirmmessage.jsp"%>
<script type="text/javascript"
	src="<c:url value="/js/WYSIWYG/source.js" />"></script>

<script type="text/javascript">
	function addInput() {
		var idx = $(".model-dataset").length;
		var string = (idx >1)?"":"New Datasets";
		var html = "<div class=\"model-dataset margin-top-05 col-xs-12\">"
				+ "<label class=\"control-label col-xs-3\">"
				+ string
				+ "</label>"
				+ "<div class=\"col-xs-4\"><input id = \"in"+idx + "\" name= \"in"+idx + "\" type=\"file\" class=\"dataset file\" data-show-upload=\"false\" /></div>"
				+ "<div class=\"col-xs-4\"><input id = \"out"+idx + "\" name= \"out"+idx + "\" type=\"file\" class=\"dataset file\" data-show-upload=\"false\" /></div>"
				+ "</div>";
		$("div#datasets").append(html);
		$(".model-dataset:last .file").fileinput({
			maxFileSize : 5000000,
			allowedFileTypes : [ 'text' ],
			removeClass : "btn btn-default",
			removeLabel : "Delete",
			previewFileType : 'text',
			msgProgress : 'Loading {percent}%',
			browseClass : "btn btn-primary",

			browseLabel : "Pick",
			browseIcon : '<i class="fa fa-file"></i>&nbsp;',
			removeIcon : '<i class="fa fa-trash"></i>'
		}).on('fileloaded', function(event, file, previewId) {
		   if (file.name.search(/\d{3}\.(in|out)/) == -1){
			   alert("Error in file name: " + file.name + "; must be in the form 001.in, 002.out, etc.");
		   	   $(this).fileinput('reset');
		   }
	    });
	};

	$(function() {
		$(".dataset,.file").fileinput({
			maxFileSize : 5000000,
			allowedFileTypes : [ 'text' ],
			removeClass : "btn btn-default",
			removeLabel : "Delete",
			previewFileType : 'text',
			showPreview : 0,
			msgProgress : 'Loading {percent}%',
			browseClass : "btn btn-primary",
			browseLabel : "Pick",
			browseIcon : '<i class="fa fa-file-o"></i>&nbsp;',
			removeIcon : '<i class="fa fa-trash"></i>'
		});
		
		$("#zipfile.file").fileinput({
			maxFileSize : 50000000,
			allowedFileTypes : [ 'object' ],
			removeClass : "btn btn-default",
			msgProgress : 'Loading {percent}%',
			browseClass : "btn btn-danger",
			browseLabel : "Pick",
			browseIcon : '<i class="fa fa-file-o"></i>&nbsp;',
			removeIcon : '<i class="fa fa-trash"></i>'
		});
	

		$('.fa-chevron-up').click(function() {
			$(this).toggleClass('fa-chevron-up');
			$(this).toggleClass('fa-chevron-down');
		});

		addInput();
		$("[data-toggle='tooltip']").tooltip();
		
		$("#deleteall").click(deleteall);
	});

	function deleteall() {
		var pid = $("#pid").val();  
		$.ajax({
			type : "GET",
			url : "/admin/removealldatasets.json",
			data: {
				"pid":pid
			},
			dataType : 'text',
			success : function(data) {
				$('#datasets-container').empty();
			}
		});

	};
	
	function removedataset(pid,dataset) {
		$.ajax({
			type : "GET",
			url : "/admin/removedataset.json",
			data: {
				"pid":pid,
				"dataset":dataset
			},
			dataType : 'text',
			success : function(data) {
				$('#' + dataset + '-container').remove();
			}
		});

	};
</script>