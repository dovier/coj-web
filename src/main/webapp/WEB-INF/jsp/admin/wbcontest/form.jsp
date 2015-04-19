<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
			
			<link rel="stylesheet" href="<c:url value="/css/ui-1.11.2/jquery-ui-timepicker-addon.css"/>" type="text/css" media="screen" />
			<link rel="stylesheet" href="<c:url value="/css/ui-1.11.2/jquery-ui-1.11.2.min.css"/>" type="text/css" media="screen" />
		
			
			<form:hidden path="id"/>
			
			<div class="form-group">
				<label class="control-label col-xs-3"><spring:message
						code="fieldhdr.name" /></label>
				<div class="col-xs-8">
					<form:input cssClass="form-control" path="name" size="50"/>
				</div>
				<div class="error col-xs-8 col-xs-offset-3">
					<span class="label label-danger"><form:errors path="name" /></span>
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
						code="fieldhdr.startdate" /></label>
				<div class="col-xs-8">
					<form:input cssClass="form-control" path="startDate" id="startDate" size="50"/>
				</div>
				<div class="error col-xs-8 col-xs-offset-3">
					<span class="label label-danger"><form:errors path="startDate" /></span>
				</div>
				<a><i data-toggle="tooltip" class="fa fa-asterisk"
					title="<spring:message code="mandatory.field"/>"></i></a>
			</div>
			
			
			<div class="form-group">
				<label class="control-label col-xs-3"><spring:message
						code="fieldhdr.enddate" /></label>
				<div class="col-xs-8">
					<form:input cssClass="form-control" path="endDate" id="endDate" size="50"/>
				</div>
				<div class="error col-xs-8 col-xs-offset-3">
					<span class="label label-danger"><form:errors path="endDate" /></span>
				</div>
				<a><i data-toggle="tooltip" class="fa fa-asterisk"
					title="<spring:message code="mandatory.field"/>"></i></a>
			</div>
			
			<div class="form-group">
				<label class="control-label col-xs-3"><spring:message
						code="fieldhdr.site" /></label>
				<div class="col-xs-8">
					<form:select cssClass="form-control" path="sid" id="sites">
						<option value="0"><spring:message code="combobox.message.select.site" /></option>
						<form:options items="${sites}" itemLabel="site"
							itemValue="sid" />
					</form:select>
				</div>
				<div class="error col-xs-8 col-xs-offset-3">
					<span class="label label-danger"><form:errors path="sid" /></span>
				</div>
				<a><i data-toggle="tooltip" class="fa fa-asterisk"
					title="<spring:message code="mandatory.field"/>"></i></a>
			</div>
			
			
			<div class="form-group">
				<label class="control-label col-xs-3"><spring:message
						code="fieldhdr.notifcreated" /></label>
				<div class="col-xs-8">
					<form:checkbox cssClass="checkbox" path="notifCreated"/>
				</div>
			</div>
			
			
			<div class="form-group">
				<label class="control-label col-xs-3"><spring:message
						code="fieldhdr.notifchanged" /></label>
				<div class="col-xs-8">
					<form:checkbox cssClass="checkbox" path="notifChanged"/>
				</div>
			</div>
			
			
			<script type="text/javascript" src="<c:url value="/js/ui-1.11.2/jquery-ui-timepicker-addon.js"/>"></script>
			<script type="text/javascript" src="<c:url value="/js/ui-1.11.2/jquery-ui-rangetimepicker-addon.js"/>"></script>
			<script type="text/javascript" src="<c:url value="/js/ui-1.11.2/jquery-migrate-1.0.0.js"/>"></script>
		
		
