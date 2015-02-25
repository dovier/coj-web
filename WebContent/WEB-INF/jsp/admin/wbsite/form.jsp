<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

			
			<form:hidden path="sid"/>
			
			<div class="form-group">
				<label class="control-label col-xs-3"><spring:message
						code="fieldhdr.site" /></label>
				<div class="col-xs-8">
					<form:input cssClass="form-control" path="site" size="50"/>
				</div>
				<div class="error col-xs-8 col-xs-offset-3">
					<span class="label label-danger"><form:errors path="site" /></span>
				</div>
				<a><i data-toggle="tooltip" class="fa fa-asterisk"
					title="<spring:message code="mandatory.field"/>"></i></a>
			</div>		
		
			<div class="form-group">
				<label class="control-label col-xs-3"><spring:message
						code="fieldhdr.url" /></label>
				<div class="col-xs-8">
					<form:input cssClass="form-control" path="url" size="50"/>
				</div>
				<div class="error col-xs-8 col-xs-offset-3">
					<span class="label label-danger"><form:errors path="url" /></span>
				</div>
				<a><i data-toggle="tooltip" class="fa fa-asterisk"
					title="<spring:message code="mandatory.field"/>"></i></a>
			</div>
			
			<div class="form-group">
				<label class="control-label col-xs-3"><spring:message
						code="fieldhdr.code" /></label>
				<div class="col-xs-8">
					<form:input cssClass="form-control" path="code" size="50"/>
				</div>
				<div class="error col-xs-8 col-xs-offset-3">
					<span class="label label-danger"><form:errors path="code" /></span>
				</div>
				<a><i data-toggle="tooltip" class="fa fa-asterisk"
					title="<spring:message code="mandatory.field"/>"></i></a>
			</div>
			
			
			<div class="form-group">
				<label class="control-label col-xs-3"><spring:message
						code="fieldhdr.enabled" /></label>
				<div class="col-xs-8">
					<form:checkbox cssClass="checkbox" path="enabled"/>
				</div>
			</div>
			
			
			<div class="form-group">
				<label class="control-label col-xs-3"><spring:message
						code="fieldhdr.completed" /></label>
				<div class="col-xs-8">
					<form:checkbox cssClass="checkbox" path="completed"/>
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-xs-3"><spring:message
						code="fieldhdr.timeanddateid" /></label>
				<div class="col-xs-8">
					<form:input cssClass="form-control" path="timeanddateid" size="5"/>
				</div>
				<div class="error col-xs-8 col-xs-offset-3">
					<span class="label label-danger"><form:errors path="timeanddateid" /></span>
				</div>
				<a><i data-toggle="tooltip" class="fa fa-asterisk"
					title="<spring:message code="mandatory.field"/>"></i></a>
			</div>
			

			<div class="form-group">
				<label class="control-label col-xs-3"><spring:message
						code="fieldhdr.timezone" /></label>
				<div class="col-xs-8">
					<form:select cssClass="form-control" path="timezone" id="timezones">
						<option value="0"><spring:message code="combobox.message.select.timezone" /></option>
						<form:options items="${timezones}" />
					</form:select>
				</div>
				<div class="error col-xs-8 col-xs-offset-3">
					<span class="label label-danger"><form:errors path="timezone" /></span>
				</div>
				<a><i data-toggle="tooltip" class="fa fa-asterisk"
					title="<spring:message code="mandatory.field"/>"></i></a>
			</div>