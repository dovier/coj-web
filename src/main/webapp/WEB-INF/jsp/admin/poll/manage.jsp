<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer="16kb" autoFlush="true"%>

<h2 class="postheader">
	<spring:message code="pagetit.manage.poll" />
</h2>
<div class="postcontent">
	<form:form method="post" commandName="poll">
		<div class="form-group col-xs-12">
			<label class="control-label col-xs-3"><spring:message
					code="tablehdr.question" /></label>
			<div class="col-xs-9">
				<form:textarea cols="80" rows="5" id="question" path="question"
					cssClass="form-control" />
				<span class="label label-danger"><form:errors path="question" /></span>
			</div>
		</div>
		<div class="form-group col-xs-12">
			<label class="control-label col-xs-3"><spring:message
					code="addfaq.answer" /> 1</label>
			<div class="col-xs-9">
				<form:textarea cols="80" rows="5" id="answer1" path="answer1"
					cssClass="form-control" />
				<span class="label label-danger"><form:errors path="answer1" /></span>
			</div>
		</div>
		<div class="form-group col-xs-12">
			<label class="control-label col-xs-3"><spring:message
					code="addfaq.answer" /> 2</label>
			<div class="col-xs-9">
				<form:textarea cols="80" rows="5" id="answer2" path="answer2"
					cssClass="form-control" />
				<span class="label label-danger"><form:errors path="answer2" /></span>
			</div>
		</div>
		<div class="form-group col-xs-12">
			<label class="control-label col-xs-3"><spring:message
					code="addfaq.answer" /> 3</label>
			<div class="col-xs-9">
				<form:textarea cols="80" rows="5" id="answer3" path="answer3"
					cssClass="form-control" />
				<span class="label label-danger"><form:errors path="answer3" /></span>
			</div>
		</div>
		<div class="form-group col-xs-12">
			<label class="control-label col-xs-3"><spring:message
					code="addfaq.answer" /> 4</label>
			<div class="col-xs-9">
				<form:textarea cols="80" rows="5" id="answer4" path="answer4"
					cssClass="form-control" />
				<span class="label label-danger"><form:errors path="answer4" /></span>
			</div>
		</div>
		<div class="form-group col-xs-12">
			<label class="control-label col-xs-3"><spring:message
					code="addfaq.answer" /> 5</label>
			<div class="col-xs-9">
				<form:textarea cols="80" rows="5" id="answer5" path="answer5"
					cssClass="form-control" />
				<span class="label label-danger"><form:errors path="answer5" /></span>
			</div>
		</div>
		<div class="form-group col-xs-12">
			<label class="control-label col-xs-3">Enable</label>
			<div class="col-xs-8">
				<form:checkbox cssClass="checkbox" path="enabled" />
			</div>
		</div>
		<div class="col-xs-12">
			<div class="form-actions pull-right ">
				<input class="btn btn-primary" type="submit" name="submit"
					id="submit" value="<spring:message code="button.edit"/>" /> <input
					class="btn btn-primary" type="reset" name="reset" id="reset"
					value="<spring:message code="button.reset"/>" />
			</div>
		</div>
	</form:form>
</div>