<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer="16kb" autoFlush="true"%>
<!--<script type='text/javascript'
	src="<c:url value="/js/jquery.js" />"></script>-->
<script type='text/javascript' src="<c:url value="/js/coj.js" />"></script>

<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate var="year" value="${now}" pattern="yyyy" />
<h2 class="postheader"></h2>
<div class="row postcontent">
	<div class="col-xs-10">
		<form:form method="post" commandName="user" cssClass="form-horizontal">
			<legend>
				<spring:message code="pagehdr.ruaccount" />
			</legend>
			<div class="form-group">
				<label class="control-label col-xs-3"><spring:message
						code="fieldhdr.username" /></label>
				<div class="col-xs-8">
					<form:input cssClass="form-control" path="username" size="30"
						maxlength="15" />
				</div>
				<div class="error col-xs-8 col-xs-offset-3">
					<span class="label label-danger"><form:errors
							path="username" /></span>
				</div>
				<a><i class="fa fa-asterisk" data-toggle="tooltip"
					title="<spring:message code="mandatory.field"/>"></i></a>&nbsp;<a><i
					class="fa fa-info-circle" data-toggle="tooltip"
					title="<spring:message code="infomsg.1"/>"></i></a>
			</div>

			<div class="form-group">
				<label class="control-label col-xs-3"><spring:message
						code="fieldhdr.nname" /></label>
				<div class="col-xs-8">
					<form:input cssClass="form-control" path="nick" size="30"
						maxlength="15" />
				</div>
				<div class="error col-xs-8 col-xs-offset-3">
					<span class="label label-danger"><form:errors path="nick" /></span>
				</div>
				<a><i class="fa fa-asterisk" data-toggle="tooltip"
					title="<spring:message code="mandatory.field"/>"></i></a>&nbsp;<a><i
					class="fa fa-info-circle" data-toggle="tooltip"
					title="<spring:message code="infomsg.2"/>"></i></a>
			</div>

			<div class="form-group">
				<label class="control-label col-xs-3"><spring:message
						code="fieldhdr.fname" /></label>
				<div class="col-xs-8">
					<form:input cssClass="form-control" path="name" size="30"
						maxlength="30" />
				</div>
				<div class="error col-xs-8 col-xs-offset-3">
					<span class="label label-danger"><form:errors path="name" /></span>
				</div>
				<a><i class="fa fa-asterisk" data-toggle="tooltip"
					title="<spring:message code="mandatory.field"/>"></i></a>&nbsp;<a><i
					class="fa fa-info-circle" data-toggle="tooltip"
					title="<spring:message code="infomsg.3"/>"></i></a>
			</div>

			<div class="form-group">
				<label class="control-label col-xs-3"><spring:message
						code="fieldhdr.lname" /></label>
				<div class="col-xs-8">
					<form:input cssClass="form-control" path="lastname" size="30"
						maxlength="50" />
				</div>
				<div class="error col-xs-8 col-xs-offset-3">
					<span class="label label-danger"><form:errors
							path="lastname" /></span>
				</div>
				<a><i class="fa fa-asterisk" data-toggle="tooltip"
					title="<spring:message code="mandatory.field"/>"></i></a>&nbsp;<a><i
					class="fa fa-info-circle" data-toggle="tooltip"
					title="<spring:message code="infomsg.4"/>"></i></a>
			</div>

			<div class="form-group">
				<label class="control-label col-xs-3"><spring:message
						code="fieldhdr.gender" /></label>
				<div class="col-xs-8">
					<form:select cssClass="form-control" path="gender">
						<form:option value="1">
							<spring:message code="fieldval.male" />
						</form:option>
						<form:option value="2">
							<spring:message code="fieldval.female" />
						</form:option>
					</form:select>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-3"><spring:message
						code="fieldhdr.dob" /></label>
				<div class="col-xs-2">
					<form:select cssClass="form-control" path="year">
						<c:forEach begin="1930" step="1" end="${year}" var="value">
							<form:option value="${value}">${value}</form:option>
						</c:forEach>
					</form:select>

				</div>
				<div class="col-xs-2">
					<form:select cssClass="form-control" path="month">
						<c:forEach begin="1" step="1" end="12" var="value">
							<form:option value="${value}">${value}</form:option>
						</c:forEach>
					</form:select>
				</div>
				<div class="col-xs-2">
					<form:select cssClass="form-control" path="day">
						<c:forEach begin="1" step="1" end="31" var="value">
							<form:option value="${value}">${value}</form:option>
						</c:forEach>
					</form:select>
				</div>
				<div class="error col-xs-8">
					<span class="label label-danger"><form:errors
							path="year" /></span>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-3"><spring:message
						code="fieldhdr.sdob" /></label>
				<div class="col-xs-8">
					<form:checkbox cssClass="checkbox" path="showdob" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-3"><spring:message
						code="fieldhdr.country" /></label>
				<div class="col-xs-8">
					<form:select cssClass="form-control" path="country_id" id="country"
						onchange="getInstitution();">
						<form:option value="0">
							<spring:message code="fieldval.select" />
						</form:option>
						<form:options items="${countries}" itemValue="id" itemLabel="name" />
					</form:select>
				</div>

				<div class="error col-xs-8 col-xs-offset-3">
					<span class="label label-danger"><form:errors path="country" /></span>
				</div>
				<a><i class="fa fa-asterisk" data-toggle="tooltip"
					title="<spring:message code="mandatory.field"/>"></i></a>&nbsp;<a><i
					class="fa fa-info-circle" data-toggle="tooltip"
					title="<spring:message code="infomsg.5"/>"></i></a>
			</div>

			<div class="form-group">
				<label class="control-label col-xs-3"><spring:message
						code="fieldhdr.institution" /></label>
				<div class="col-xs-8">
					<form:select cssClass="form-control" path="institution_id"
						id="institution">
						<form:options items="${institutions}" itemLabel="name"
							itemValue="id" />
					</form:select>
				</div>
				<div class="error col-xs-8 col-xs-offset-3">
					<span class="label label-danger"><form:errors
							path="institution" /></span>
				</div>
				&nbsp;<a><i class="fa fa-info-circle" data-toggle="tooltip"
					title="<spring:message code="infomsg.5"/>"></i></a>
			</div>

			<div class="form-group">
				<label class="control-label col-xs-3"><spring:message
						code="fieldhdr.email" /></label>
				<div class="col-xs-8">
					<form:input cssClass="form-control" path="email" size="30" />
				</div>
				<div class="error col-xs-8 col-xs-offset-3">
					<span class="label label-danger"><form:errors
							path="email" /></span>
				</div>
				<a><i class="fa fa-asterisk" data-toggle="tooltip"
					title="<spring:message code="mandatory.field"/>"></i></a>&nbsp;<a><i
					class="fa fa-info-circle" data-toggle="tooltip"
					title="<spring:message code="infomsg.7"/>"></i></a>
			</div>

			<div class="form-group">
				<label class="control-label col-xs-3"><spring:message
						code="fieldhdr.cemail" /></label>
				<div class="col-xs-8">
					<form:input cssClass="form-control" path="cemail" size="30" />
				</div>
				<div class="error col-xs-8 col-xs-offset-3">
					<span class="label label-danger"><form:errors path="cemail" /></span>
				</div>
				
			</div>

			<div class="form-group">
				<label class="control-label col-xs-3"><spring:message
						code="fieldhdr.semail" /></label>
				<div class="col-xs-8">
					<form:checkbox cssClass="checkbox" path="showemail" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-3"><spring:message
						code="fieldhdr.notifcontest" /></label>
				<div class="col-xs-8">
					<form:checkbox cssClass="checkbox" path="contestNotifications" />
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-xs-3"><spring:message
						code="fieldhdr.notifproblem" /></label>
				<div class="col-xs-8">
					<form:checkbox cssClass="checkbox" path="problemNotifications" />
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-xs-3"><spring:message
						code="fieldhdr.notifsubmit" /></label>
				<div class="col-xs-8">
					<form:checkbox cssClass="checkbox" path="submissionNotifications" />
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-xs-3"><spring:message
						code="fieldhdr.notifwboard" />: </label>
				<div class="col-xs-8">
					<form:checkbox cssClass="checkbox" path="wboardNotifications" />
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-xs-3"><spring:message
						code="fieldhdr.defaultguilang" /></label>
				<div class="col-xs-8">
					<form:select cssClass="form-control" path="locale">
						<form:options items="${locales}" itemLabel="description"
							itemValue="lid" />
					</form:select>
				</div>

				<div class="error col-xs-8 col-xs-offset-3">
					<span class="label label-danger"><form:errors path="locale" /></span>
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-xs-3"><spring:message
						code="fieldhdr.defaultproglang" /></label>
				<div class="col-xs-8">
					<form:select cssClass="form-control" path="lid">
						<form:options items="${planguages}" itemLabel="descripcion"
							itemValue="lid" />
					</form:select>
				</div>
				<div class="error col-xs-8 col-xs-offset-3">
					<span class="label label-danger"><form:errors path="lid" /></span>
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-xs-3"><spring:message
						code="fieldhdr.enableadveditor" /></label>
				<div class="col-xs-8">
					<form:checkbox cssClass="checkbox" path="enableadveditor" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-3"><spring:message
						code="fieldhdr.password" /></label>
				<div class="col-xs-8">
					<form:password cssClass="form-control" path="password" size="30"
						maxlength="100" />
				</div>

				<div class="error col-xs-8 col-xs-offset-3">
					<span class="label label-danger"><form:errors
							path="password" /></span>
				</div>
				<a><i class="fa fa-asterisk" data-toggle="tooltip"
					title="<spring:message code="mandatory.field"/>"></i></a>&nbsp;<a><i
					class="fa fa-info-circle" data-toggle="tooltip"
					title="<spring:message code="infomsg.8"/>"></i></a>
			</div>

			<div class="form-group">
				<label class="control-label col-xs-3"><spring:message
						code="fieldhdr.cpassword" /> </label>
				<div class="col-xs-8">
					<form:password cssClass="form-control" path="confirmPassword"
						size="30" maxlength="100" />
				</div>

				<div class="error col-xs-8 col-xs-offset-3">
					<span class="label label-danger"><form:errors
							path="confirmPassword" /></span>
				</div>
				<a><i class="fa fa-asterisk" data-toggle="tooltip"
					title="<spring:message code="mandatory.field"/>"></i></a>
			</div>

			<div class="form-group">
				<label class="control-label col-xs-3"><form:checkbox
						path="agreementcojtos" /> </label>
				<div class="col-xs-8">
					<spring:message code="fieldhdr.agreementcojtos" />
				</div>
				<div class="error col-xs-8 col-xs-offset-3">
					<span class="label label-danger"><form:errors
							path="agreementcojtos" /></span>
				</div>
				<a><i class="fa fa-asterisk" data-toggle="tooltip"
					title="<spring:message code="mandatory.field"/>"></i></a>
			</div>
			<div class="form-actions pull-right">
				<input class="btn btn-primary" type="submit" name="submit"
					id="submit" value="<spring:message code="button.create"/>" /> <input
					class="btn btn-primary" type="reset" name="reset" id="reset"
					value="<spring:message code="button.reset"/>" />
			</div>
		</form:form>
	</div>
</div>

<script type='text/javascript'>
	if ($("#country option:selected").val() == "0") {
		$('#inst').hide();
	}
</script>
<script>
    $("[data-toggle='tooltip']").tooltip();
</script>