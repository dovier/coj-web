
<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer="16kb" autoFlush="true"%>

<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate var="year" value="${now}" pattern="yyyy" />

<h2 class="postheader">
	<spring:message code="pagehdr.euaccount" />
</h2>

<div class="row">
	<div class="col-xs-10">
		<form:form method="post" enctype="multipart/form-data"
			commandName="user" cssClass="form-horizontal">
			<authz:authorize ifAnyGranted="ROLE_USER">
				<div class="form-group">
					<label class="control-label col-xs-3" for="imagefile">Avatar
						(120x120, &lt;35KB)</label>
					<div class="col-xs-8">
						<input id="avatar" name="avatar" type="file" class="file"
							data-show-upload="false" accept="image/*"
							data-show-caption="true">
					</div>
				</div>
			</authz:authorize>
			<authz:authorize ifAnyGranted="ROLE_USER,ROLE_TEAM">
				<div class="form-group">
					<label class="control-label col-xs-3"><spring:message
							code="fieldhdr.username" /></label>
					<div class="col-xs-8">
						<form:input cssClass="form-control" path="username" size="30"
							maxlength="15" readonly="true" />
					</div>
					<div class="error col-xs-8 col-xs-offset-3">
						<span class="label label-danger"><form:errors path="username" /></span>
					</div>
					<a><i data-toggle="tooltip" class="fa fa-asterisk"
						title="<spring:message code="mandatory.field"/>"></i></a>
				</div>
			</authz:authorize>
			<div class="form-group">
				<label class="control-label col-xs-3"><spring:message
						code="fieldhdr.nname" />: </label>
				<div class="col-xs-8">
					<c:choose>
						<c:when test="${user.update_nick == false}">
							<form:input cssClass="form-control" path="nick" size="30"
								maxlength="30" readonly="true" tabindex="1" />
						</c:when>
						<c:otherwise>
							<form:input cssClass="form-control" path="nick" size="30"
								maxlength="30" tabindex="1" />
						</c:otherwise>
					</c:choose>
				</div>
				<div class="error col-xs-8 col-xs-offset-3">
					<span class="label label-danger"><form:errors path="nick" /></span>
				</div>
				<a><i data-toggle="tooltip" class="fa fa-asterisk"
					title="<spring:message code="mandatory.field"/>"></i></a> <a> <i
					data-toggle="tooltip" class="fa fa-info-circle"
					title="<spring:message code="infomsg.2"/>"></i></a>
			</div>



			<authz:authorize ifAllGranted="ROLE_USER">
				<div class="form-group">
					<label class="control-label col-xs-3"><spring:message
							code="fieldhdr.fname" />: </label>
					<div class="col-xs-8">
						<form:input cssClass="form-control" path="name" size="30"
							maxlength="30" />
					</div>
					<div class="error col-xs-8 col-xs-offset-3">
						<span class="label label-danger"><form:errors path="name" /></span>
					</div>
					<a><i data-toggle="tooltip" class="fa fa-asterisk"
						title="<spring:message code="mandatory.field"/>"></i></a> <a><i
						data-toggle="tooltip" class="fa fa-info-circle"
						title="<spring:message code="infomsg.3"/>"></i></a>
				</div>
				<div class="form-group">
					<label class="control-label col-xs-3"><spring:message
							code="fieldhdr.lname" />: </label>
					<div class="col-xs-8">
						<form:input cssClass="form-control" path="lastname" size="30"
							maxlength="50" />
					</div>
					<div class="error col-xs-8 col-xs-offset-3">
						<span class="label label-danger"><form:errors path="lastname" /></span>
					</div>
					<a><i data-toggle="tooltip" class="fa fa-asterisk"
						title="<spring:message code="mandatory.field"/>"></i></a> <a><i
						data-toggle="tooltip" class="fa fa-info-circle"
						title="<spring:message code="infomsg.4"/>"></i></a>
				</div>
				<div class="form-group">
					<label class="control-label col-xs-3"><spring:message
							code="fieldhdr.gender" />: </label>
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
							code="fieldhdr.dob" />: </label>
					<div class="col-xs-2 col-xs-offset-2">
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
				</div>
				<div class="form-group">
					<label class="control-label col-xs-3"><spring:message
							code="fieldhdr.showtags" />: </label>
					<div class="col-xs-8">
						<form:checkbox cssClass="checkbox" path="view_problem_info" />
					</div>
				</div>
			</authz:authorize>
			<div class="form-group">
				<label class="control-label col-xs-3"><spring:message
						code="fieldhdr.country" />: </label>
				<div class="col-xs-8">
					<form:select cssClass="form-control" path="country_id" id="country"
						onchange="getInstitution();">
						<form:option value="0">
							<spring:message code="fieldval.select" />
						</form:option>
						<form:options items="${countries}" itemValue="id" itemLabel="name" />
					</form:select>
				</div>
				<input type="hidden" name="noneInstitution" value='<spring:message code="user.none.institution" />'/>
				<div class="error col-xs-8 col-xs-offset-3">
					<span class="label label-danger"><form:errors path="country" /></span>
				</div>
				<a><i data-toggle="tooltip" class="fa fa-asterisk"
					title="<spring:message code="mandatory.field"/>"></i></a> <a><i
					data-toggle="tooltip" class="fa fa-info-circle"
					title="<spring:message code="infomsg.5"/>"></i></a>
			</div>
			<div id="inst" class="form-group">
				<label class="control-label col-xs-3"><spring:message
						code="fieldhdr.institution" />: </label>
				<div class="col-xs-8">
					<form:select cssClass="form-control" path="institution_id"
						id="institution">
						<form:options items="${institutions}" itemLabel="name"
							itemValue="id" />
					</form:select>
					<div class="error col-xs-8 col-xs-offset-3">
						<span class="label label-danger"><form:errors
								path="institution" /></span>
					</div>
				</div>
				<a><i class="fa fa-info-circle" data-toggle="tooltip"
					title="<spring:message code="infomsg.6"/>"></i></a>
			</div>
			
			<authz:authorize ifAllGranted="ROLE_USER">
				<div class="form-group">
					<label class="control-label col-xs-3"><spring:message
							code="fieldhdr.email" />: </label>
					<div class="col-xs-8">
						<input name="email" type="email" class="form-control" value="${user.email}" size="30" />
					</div>
					<a><i data-toggle="tooltip" class="fa fa-asterisk"
						title="<spring:message code="mandatory.field"/>"></i></a> <a><i
						data-toggle="tooltip" class="fa fa-info-circle"
						title="<spring:message code="infomsg.11"/>"></i></a>
					<div class="error col-xs-8 col-xs-offset-3">
						<span class="label label-danger"><form:errors path="email" /></span>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-xs-3"><spring:message
							code="fieldhdr.notifcontest" />: </label>
					<div class="col-xs-8">
						<form:checkbox cssClass="checkbox" path="contestNotifications" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-xs-3"><spring:message
							code="fieldhdr.notifproblem" />: </label>
					<div class="col-xs-8">
						<form:checkbox cssClass="checkbox" path="problemNotifications" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-xs-3"><spring:message
							code="fieldhdr.notifsubmit" />: </label>
					<div class="col-xs-8">
						<form:checkbox cssClass="checkbox" path="submissionNotifications" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-xs-3"><spring:message
							code="fieldhdr.notifnewprivatemessage" />: </label>
					<div class="col-xs-8">
						<form:checkbox cssClass="checkbox" path="newprivatemessageNotifications" />
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
							code="fieldhdr.profileproblemclass" />: </label>
					<div class="col-xs-8">
						<form:checkboxes cssClass="checkbox-inline"
							path="preferedClassificationsIds" items="${classifications}"
							itemValue="idClassification" itemLabel="name" delimiter="</br>" />
					</div>
				</div>
			</authz:authorize>
			
			<authz:authorize ifAllGranted="ROLE_USER">
				<div id="sitesFollowed" class="form-group">
					<label class="control-label col-xs-3"><spring:message
							code="fieldhdr.sites.followed" />: </label>
					<div class="col-xs-8">
						<form:select size="6" multi="true" cssClass="form-control" path="sitesFollowing"
							id="site">
							<form:options items="${sites}" itemLabel="site"
								itemValue="sid" />
						</form:select>
					</div>
				</div>
			</authz:authorize>
			
			<div class="form-group">
				<label class="control-label col-xs-3"><spring:message
						code="fieldhdr.defaultguilang" />: </label>
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
				<label class="control-label col-xs-3"> <spring:message
						code="fieldhdr.defaultproglang" />:
				</label>
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
				<label class="control-label col-xs-3"> <spring:message
						code="fieldhdr.enableadveditor" />:
				</label>
				<div class="col-xs-8">
					<form:checkbox cssClass="checkbox" path="enableadveditor" />
				</div>
			</div>
			<authz:authorize ifAllGranted="ROLE_TEAM">
				<c:choose>
					<c:when test="${user.update_nick == true}">
						<div class="form-group">
							<label class="control-label col-xs-3"><spring:message
									code="fieldhdr.tcoach" />: </label>
							<div class="col-xs-8">
								<form:input cssClass="form-control" path="coach" size="60"
									maxlength="60" />
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-xs-3"><spring:message
									code="fieldhdr.tmember" />: </label>
							<div class="col-xs-8">
								<form:input cssClass="form-control" path="user_1" size="60"
									maxlength="60" />
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-xs-3"><spring:message
									code="fieldhdr.tmember" />: </label>
							<div class="col-xs-8">
								<form:input cssClass="form-control" path="user_2" size="60"
									maxlength="60" />
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-xs-3"><spring:message
									code="fieldhdr.tmember" />: </label>
							<div class="col-xs-8">
								<form:input cssClass="form-control" path="user_3" size="60"
									maxlength="60" />
							</div>
						</div>
					</c:when>
					<c:otherwise>
					<div class="form-group">
							<label class="control-label col-xs-3"><spring:message
									code="fieldhdr.tcoach" />: </label>
							<div class="col-xs-8">
								<form:input cssClass="form-control" path="coach" size="60"
									maxlength="60" readonly="true" />
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-xs-3"><spring:message
									code="fieldhdr.tmember" />:</label>
							<div class="col-xs-8">
								<form:input cssClass="form-control" path="user_1" size="60"
									maxlength="60" readonly="true" />
							</div>
							<div class="error col-xs-8 col-xs-offset-3">
								<span class="label label-danger"><form:errors path="user_1" /></span>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-xs-3"><spring:message
									code="fieldhdr.tmember" />:</label>
							<div class="col-xs-8">
								<form:input cssClass="form-control" path="user_2" size="60"
									maxlength="60" readonly="true" />
							</div>
							<div class="error col-xs-8 col-xs-offset-3">
								<span class="label label-danger"><form:errors path="user_2" /></span>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-xs-3"><spring:message
									code="fieldhdr.tmember" />:</label>
							<div class="col-xs-8">
								<form:input cssClass="form-control" path="user_3" size="60"
									maxlength="60" readonly="true" />
							</div>
							<div class="error col-xs-8 col-xs-offset-3">
								<span class="label label-danger"><form:errors path="user_3" /></span>
							</div>
						</div>
					</c:otherwise>

				</c:choose>

			</authz:authorize>
			<div class="form-group">
				<label class="control-label col-xs-3"><spring:message
						code="fieldhdr.npassword" />:</label>
				<div class="col-xs-8">
					<form:password cssClass="form-control" path="password" size="30"
						minlength="8" maxlength="100"></form:password>
				</div>
				<div class="error col-xs-8 col-xs-offset-3">
					<span class="label label-danger"><form:errors path="password" /></span>
				</div>
				<a><i data-toggle="tooltip"
					title="<spring:message code="infomsg.8"/>"
					class="fa fa-info-circle"></i></a>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-3" for="confirmPassword"><spring:message
						code="fieldhdr.cpassword" />:</label>
				<div class="col-xs-8">
					<form:password cssClass="form-control" path="confirmPassword"
						size="30" minlength="8" maxlength="100" />
				</div>
				<div class="error col-xs-8 col-xs-offset-3">
					<span class="label label-danger"><form:errors path="confirmPassword" /></span>
				</div>
			</div>
			<form:hidden path="uid" readonly="true" />
			<div class="form-actions pull-right">
				<input class="btn btn-primary" type="submit" name="submit"
					id="submit" value="<spring:message code="button.edit"/>" />
				<input
					class="btn btn-primary" type="reset" name="reset" id="reset"
					value="<spring:message code="button.reset"/>" />
				<a href="/index.xhtml" class="btn btn-primary"><spring:message code="button.close" /></a>
			</div>
		</form:form>
	</div>
</div>
<script>
	var i18n = {};
	i18n.file = "<spring:message code="message.filename"/>";

	$("#avatar").fileinput({
		maxFileSize : 35,
		msgProgress : 'Loading {percent}%',
		previewClass : 'file_preview',
		previewFileType : "image",
		browseClass : "btn btn-primary",
		browseLabel : "<spring:message code="message.pickimage"/>",
		browseIcon : '<i class="fa fa-picture-o"></i>&nbsp;',
		removeClass : "btn btn-default",
		removeLabel : "<spring:message code="message.deleteimage"/>",
		removeIcon : '<i class="fa fa-trash"></i>'
	});
	$("[data-toggle='tooltip']").tooltip();
</script>

