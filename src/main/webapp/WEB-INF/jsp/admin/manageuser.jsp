<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer="16kb" autoFlush="true"%>
<script type='text/javascript' src="<c:url value="/js/jquery.js" />"></script>
<script type='text/javascript' src="<c:url value="/js/coj.js" />"></script>


<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate var="year" value="${now}" pattern="yyyy" />

<h2 class="postheader">
	<spring:message code="pagehdr.euaccount" />
</h2>
<div class="row postcontent">

	<form:form method="post" commandName="user">
		<div class="form-group col-xs-12">
			<span class="label label-info"> <i class="fa fa-info-circle"></i>
				<spring:message code="text.euaccount.1" />
			</span>
		</div>
		<div class="form-group col-xs-12">
			<label class="control-label col-xs-3"> <spring:message
					code="fieldhdr.nname" />&nbsp;<i class="fa fa-asterisk"></i></label>
			<div class="col-xs-9">
				<form:input path="nick" size="30" maxlength="50" />
				<span class="label label-danger"><form:errors path="nick" /></span>
			</div>
		</div>
		<div class="form-group col-xs-12">
			<label class="control-label col-xs-3"><spring:message
					code="fieldhdr.modnickname" />&nbsp;<i class="fa fa-asterisk"></i></label>
			<div class="col-xs-9">
				<form:checkbox path="update_nick" />
				<span class="label label-danger"><form:errors
						path="update_nick" /></span>
			</div>
		</div>
		<div class="form-group col-xs-12">
			<label class="control-label col-xs-3"> </label>
			<div class="col-xs-9"></div>
		</div>

		<div class="form-group col-xs-12">
			<label class="control-label col-xs-3"><spring:message
					code="fieldhdr.country" />&nbsp;<i class="fa fa-asterisk"></i></label>
			<div class="col-xs-9">
				<form:select path="country_id" id="country"
					onchange="getInstitution();">
					<form:option value="0">none</form:option>
					<form:options items="${countries}" itemValue="id" itemLabel="name" />
				</form:select>
				<br /> <span class="label label-info"><spring:message
						code="text.euaccount.3" /></span>
			</div>
			<span class="label label-danger"><form:errors path="country" /></span>
		</div>

		<div class="form-group col-xs-12">
			<label class="control-label col-xs-3"><spring:message
					code="fieldhdr.institution" />&nbsp;<i class="fa fa-asterisk"></i></label>
			<div class="col-xs-9">
				<form:select path="institution_id" id="institution">
					<form:option value="0">
						<spring:message code="fieldval.none" />
					</form:option>
					<form:options items="${institutions}" itemLabel="name"
						itemValue="id" />
				</form:select>
				<br /> <span class="label label-info"><spring:message
						code="text.euaccount.4" /></span>
			</div>
			<span class="label label-danger"><form:errors
					path="institution" /></span>
		</div>

		<div class="form-group col-xs-12">
			<label class="control-label col-xs-3"><spring:message
					code="fieldhdr.defaultguilang" />&nbsp;<i class="fa fa-asterisk"></i></label>
			<div class="col-xs-9">
				<form:select path="locale">
					<form:options items="${locales}" itemLabel="description"
						itemValue="lid" />
				</form:select>
				<span class="label label-danger"><form:errors path="locale" /></span>
			</div>
		</div>

		<div class="form-group col-xs-12">
			<label class="control-label col-xs-3"><spring:message
					code="fieldhdr.defaultproglang" />&nbsp;<i class="fa fa-asterisk"></i></label>
			<div class="col-xs-9">
				<form:select path="lid">
					<form:options items="${planguages}" itemLabel="descripcion"
						itemValue="lid" />
				</form:select>
				<span class="label label-danger"><form:errors path="lid" /></span>
			</div>
		</div>

		<div class="form-group col-xs-12">
			<label class="control-label col-xs-3"><spring:message
					code="fieldhdr.npassword" />&nbsp;<i class="fa fa-asterisk"></i></label>
			<div class="col-xs-9">
				<form:password path="password" size="30" maxlength="30" />
				<a><i data-toggle="tooltip" class="fa fa-info-circle"
					title="<spring:message code="infomsg.8"/>"></i></a> <span
					class="label label-danger"><form:errors path="password" /></span>
			</div>
		</div>

		<div class="form-group col-xs-12">
			<label class="control-label col-xs-3"><spring:message
					code="fieldhdr.cpassword" />&nbsp;<i class="fa fa-asterisk"></i></label>
			<div class="col-xs-9">
				<form:password path="confirmPassword" size="30" maxlength="30" />
				<span class="label label-danger"><form:errors
						path="confirmPassword" /></span>
			</div>
		</div>

		<div class="form-group col-xs-12">
			<label class="control-label col-xs-3"><spring:message
					code="fieldhdr.accessrule" /></label>
			<div class="col-xs-9">
				<form:input path="access_rule" />
				<span class="label label-danger"><form:errors
						path="access_rule" /></span>
			</div>
		</div>

		<div class="form-group col-xs-12">
			<label class="control-label col-xs-3"><spring:message
					code="fieldhdr.enabled" /></label>
			<div class="col-xs-9">
				<form:checkbox path="enabled" />
			</div>
		</div>
		<div class="col-xs-12">
			<label class="control-label col-xs-3"><spring:message
					code="fieldhdr.banreason" /></label>
			<div class="col-xs-9">
				<form:textarea cols="80" rows="5" id="banReason" path="banReason"
					cssClass="form-control" />
			</div>
		</div>

		<c:if test="${user.team == true}">
			<div class="form-group col-xs-12">
				<label class="control-label col-xs-3"><spring:message
						code="fieldhdr.tcoach" /></label>
				<div class="col-xs-9">
					<form:input path="coach" size="60" maxlength="60" />
					<span class="label label-danger"><form:errors path="user_1" /></span>
				</div>
			</div>
			<div class="form-group col-xs-12">
				<label class="control-label col-xs-3"><spring:message
						code="fieldhdr.tmember" /></label>
				<div class="col-xs-9">
					<form:input path="user_1" size="60" maxlength="60" />
					<span class="label label-danger"><form:errors path="user_1" /></span>
				</div>
			</div>
			<div class="form-group col-xs-12">
				<label class="control-label col-xs-3"><spring:message
						code="fieldhdr.tmember" /></label>
				<div class="col-xs-9">
					<form:input path="user_2" size="60" maxlength="60" />
					<span class="label label-danger"><form:errors path="user_2" /></span>
				</div>
			</div>

			<div class="form-group col-xs-12">
				<label class="control-label col-xs-3"><spring:message
						code="fieldhdr.tmember" /></label>
				<div class="col-xs-9">
					<form:input path="user_3" size="60" maxlength="60" />
					<span class="label label-danger"><form:errors path="user_3" /></span>
				</div>
			</div>

		</c:if>

		<c:if test="${user.team == false}">
			<div class="form-group col-xs-12">
				<label class="control-label col-xs-3"><spring:message
						code="fieldhdr.fname" />&nbsp;<i class="fa fa-asterisk"></i></label>
				<div class="col-xs-9">
					<form:input path="name" size="30" maxlength="30" />
					<span class="label label-danger"><form:errors path="name" /></span>
				</div>
			</div>

			<div class="form-group col-xs-12">
				<label class="control-label col-xs-3"><spring:message
						code="fieldhdr.lname" />&nbsp;<i class="fa fa-asterisk"></i></label>
				<div class="col-xs-9">
					<form:input path="lastname" size="30" maxlength="50" />
					<span class="label label-danger"><form:errors
							path="lastname" /></span>
				</div>
			</div>

			<div class="form-group col-xs-12">
				<label class="control-label col-xs-3"><spring:message
						code="fieldhdr.gender" />&nbsp;<i class="fa fa-asterisk"></i></label>
				<div class="col-xs-9">
					<form:select path="gender">
						<form:option value="1">
							<spring:message code="fieldval.male" />
						</form:option>
						<form:option value="2">
							<spring:message code="fieldval.female" />
						</form:option>
					</form:select>
					<span class="label label-danger"><form:errors path="gender" /></span>
				</div>
			</div>

			<div class="form-group col-xs-12">
				<label class="control-label col-xs-3"><spring:message
						code="fieldhdr.dob" />&nbsp;<i class="fa fa-asterisk"></i></label>
				<div class="col-xs-9">
					<form:select path="year">
						<c:forEach begin="1930" step="1" end="${year}" var="value">
							<form:option value="${value}">${value}</form:option>
						</c:forEach>
					</form:select>
					<form:select path="month">
						<c:forEach begin="1" step="1" end="12" var="value">
							<form:option value="${value}">${value}</form:option>
						</c:forEach>
					</form:select>
					<form:select path="day">
						<c:forEach begin="1" step="1" end="31" var="value">
							<form:option value="${value}">${value}</form:option>
						</c:forEach>
					</form:select>
				</div>
			</div>

			<div class="form-group col-xs-12">
				<label class="control-label col-xs-3"><spring:message
						code="fieldhdr.sdob" /></label>
				<div class="col-xs-9">
					<form:checkbox path="showdob" />
				</div>
			</div>

			<div class="form-group col-xs-12">
				<label class="control-label col-xs-3"><spring:message
						code="fieldhdr.email" />&nbsp;<i class="fa fa-asterisk"></i></label>
				<div class="col-xs-9">
					<form:input path="email" />
					<span class="label label-danger"><form:errors path="email" /></span>
				</div>
			</div>

			<div class="form-group col-xs-12">
				<label class="control-label col-xs-3"><spring:message
						code="fieldhdr.notifcontest" /></label>
				<div class="col-xs-9">
					<form:checkbox path="contestNotifications" />
				</div>
			</div>
			<!-- 					
					<div class="form-group col-xs-12">
						<td style="align:right"><spring:message code="fieldhdr.notifentries" />&nbsp;
						<div class="col-xs-9"><form:checkbox path="entriesNotifications" />
					</div>
	 -->
			<div class="form-group col-xs-12">
				<label class="control-label col-xs-3"><spring:message
						code="fieldhdr.notifproblem" /></label>
				<div class="col-xs-9">
					<form:checkbox path="problemNotifications" />
				</div>
			</div>
			<div class="form-group col-xs-12">
				<label class="control-label col-xs-3"><spring:message
						code="fieldhdr.notifsubmit" /></label>
				<div class="col-xs-9">
					<form:checkbox path="submissionNotifications" />
				</div>
			</div>
			<div class="form-group col-xs-12">
				<label class="control-label col-xs-3"><spring:message
						code="fieldhdr.mailquota" /> (<spring:message
						code="fieldval.bytes" />):<i class="fa fa-asterisk"></i></label>
				<div class="col-xs-9">
					<form:input path="mail_quote" />
					<span class="label label-danger"><form:errors
							path="mail_quote" /></span>
				</div>
			</div>
			<div class="form-group col-xs-12">
				<label class="control-label col-xs-3"><spring:message
						code="fieldhdr.roles" /><i class="fa fa-asterisk"></i></label>
				<div class="form-group col-xs-9">
					<div class='col-xs-4'>
						<form:checkboxes cssClass="checkbox" path="authorities"
							items="${authorities}" itemValue="authority"
							itemLabel="authority" delimiter="</div><div class='col-xs-4'>" />
					</div>
				</div>
				<span class="label label-danger"><form:errors path="role" /></span>
			</div>
		</c:if>

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
