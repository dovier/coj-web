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
<div class="postcontent">

	<form:form method="post" commandName="user">
		<table class="createnewuser">
			<td><span class="label label-danger"><form:errors path="uid" /></span></td>
			<tbody>
				<tr>
					<td colspan="3"><div class="label label-info"><i class="fa fa-info-circle"></i>
							<spring:message code="text.euaccount.1" />
						</div></td>
				</tr>

				<tr>
					<td style="align:right"><spring:message code="fieldhdr.nname" />:<i class="fa fa-asterisk"></i></td>
					<td><form:input path="nick" size="30" maxlength="50" /></td>
					<td><span class="label label-danger"><form:errors path="nick" /></span></td>
				</tr>

				<tr>
					<td style="align:right"><spring:message code="fieldhdr.modnickname" />:<i class="fa fa-asterisk"></i></td>
					<td><form:checkbox path="update_nick" /></td>
					<td><span class="label label-danger"><form:errors path="update_nick" /></span></td>
				</tr>

				<tr>
					<td style="align:right"><spring:message code="fieldhdr.country" />:<i class="fa fa-asterisk"></i></td>
					<td><form:select path="country_id" id="country" onchange="getInstitution();">
							<form:option value="0">none</form:option>
							<form:options items="${countries}" itemValue="id" itemLabel="name" />
						</form:select> <br /> <span class="label label-info"><spring:message code="text.euaccount.3" /></span></td>
					<td><span class="label label-danger"><form:errors path="country" /></span></td>
				</tr>

				<tr>
					<td style="align:right"><spring:message code="fieldhdr.institution" />:<i class="fa fa-asterisk"></i></td>
					<td><form:select path="institution_id" id="institution">
							<form:option value="0">
								<spring:message code="fieldval.none" />
							</form:option>
							<form:options items="${institutions}" itemLabel="name" itemValue="id" />
						</form:select> <br /> <span class="label label-info"><spring:message code="text.euaccount.4" /></span></td>
					<td><span class="label label-danger"><form:errors path="institution" /></span></td>
				</tr>

				<tr>
					<td style="align:right"><spring:message code="fieldhdr.defaultguilang" />:<i class="fa fa-asterisk"></i></td>
					<td><form:select path="locale">
							<form:options items="${locales}" itemLabel="description" itemValue="lid" />
						</form:select></td>
					<td><span class="label label-danger"><form:errors path="locale" /></span></td>
				</tr>

				<tr>
					<td style="align:right"><spring:message code="fieldhdr.defaultproglang" />:<i class="fa fa-asterisk"></i></td>
					<td><form:select path="lid">
							<form:options items="${planguages}" itemLabel="descripcion" itemValue="lid" />
						</form:select></td>
					<td><span class="label label-danger"><form:errors path="lid" /></span></td>
				</tr>

				<tr>
					<td style="align:right"><spring:message code="fieldhdr.npassword" />:<i class="fa fa-asterisk"></i></td>
					<td><form:password path="password" size="30" maxlength="30" /><a><i data-toggle="tooltip" class="fa fa-info-circle" title="<spring:message code="infomsg.8"/>"></i></a></td>
					<td><span class="label label-danger"><form:errors path="password" /></span></td>
				</tr>

				<tr>
					<td style="align:right"><spring:message code="fieldhdr.cpassword" />:<i class="fa fa-asterisk"></i></td>
					<td><form:password path="confirmPassword" size="30" maxlength="30" /></td>
					<td><span class="label label-danger"><form:errors path="confirmPassword" /></span></td>
				</tr>

				<tr>
					<td style="align:right"><spring:message code="fieldhdr.accessrule" />:</td>
					<td><form:input path="access_rule" /></td>
					<td><span class="label label-danger"><form:errors path="access_rule" /></span></td>
				</tr>

				<tr>
					<td style="align:right"><spring:message code="fieldhdr.enabled" />:</td>
					<td><form:checkbox path="enabled" /></td>
				</tr>

				<c:if test="${user.team == true}">
					<tr>
						<td style="align:right"><spring:message code="fieldhdr.tcoach" />:</td>
						<td><form:input path="coach" size="60" maxlength="60" /></td>
						<td><span class="label label-danger"><form:errors path="user_1" /></span></td>
					</tr>
					<tr>
						<td style="align:right"><spring:message code="fieldhdr.tmember" />:</td>
						<td><form:input path="user_1" size="60" maxlength="60" /></td>
						<td><span class="label label-danger"><form:errors path="user_1" /></span></td>
					</tr>
					<tr>
						<td style="align:right"><spring:message code="fieldhdr.tmember" />:</td>
						<td><form:input path="user_2" size="60" maxlength="60" /></td>
						<td><span class="label label-danger"><form:errors path="user_2" /></span></td>
					</tr>

					<tr>
						<td style="align:right"><spring:message code="fieldhdr.tmember" />:</td>
						<td><form:input path="user_3" size="60" maxlength="60" /></td>
						<td><span class="label label-danger"><form:errors path="user_3" /></span></td>
					</tr>

				</c:if>

				<c:if test="${user.team == false}">
					<tr>
						<td style="align:right"><spring:message code="fieldhdr.fname" />:<i class="fa fa-asterisk"></i></td>
						<td><form:input path="name" size="30" maxlength="30" /></td>
						<td><span class="label label-danger"><form:errors path="name" /></span></td>
					</tr>

					<tr>
						<td style="align:right"><spring:message code="fieldhdr.lname" />:<i class="fa fa-asterisk"></i></td>
						<td><form:input path="lastname" size="30" maxlength="50" /></td>
						<td><span class="label label-danger"><form:errors path="lastname" /></span></td>
					</tr>

					<tr>
						<td style="align:right"><spring:message code="fieldhdr.gender" />:<i class="fa fa-asterisk"></i></td>
						<td><form:select path="gender">
								<form:option value="1">
									<spring:message code="fieldval.male" />
								</form:option>
								<form:option value="2">
									<spring:message code="fieldval.female" />
								</form:option>
							</form:select></td>
						<td><span class="label label-danger"><form:errors path="gender" /></span></td>
					</tr>

					<tr>
						<td style="align:right"><spring:message code="fieldhdr.dob" />:<i class="fa fa-asterisk"></i></td>
						<td colspan="3"><form:select path="year">
								<c:forEach begin="1930" step="1" end="${year}" var="value">
									<form:option value="${value}">${value}</form:option>
								</c:forEach>
							</form:select> <form:select path="month">
								<c:forEach begin="1" step="1" end="12" var="value">
									<form:option value="${value}">${value}</form:option>
								</c:forEach>
							</form:select> <form:select path="day">
								<c:forEach begin="1" step="1" end="31" var="value">
									<form:option value="${value}">${value}</form:option>
								</c:forEach>
							</form:select></td>
					</tr>

					<tr>
						<td style="align:right"><spring:message code="fieldhdr.sdob" />:</td>
						<td><form:checkbox path="showdob" /></td>
						<td></td>
					</tr>

					<tr>
						<td style="align:right"><spring:message code="fieldhdr.email" />:<i class="fa fa-asterisk"></i></td>
						<td><form:input path="email" /></td>
						<td><span class="label label-danger"><form:errors path="email" /></span></td>
					</tr>

					<tr>
						<td style="align:right"><spring:message code="fieldhdr.notifcontest" />:</td>
						<td><form:checkbox path="contestNotifications" /></td>
					</tr>
					<!-- 					
					<tr>
						<td style="align:right"><spring:message code="fieldhdr.notifentries" />:</td>
						<td><form:checkbox path="entriesNotifications" /></td>
					</tr>
	 -->
					<tr>
						<td style="align:right"><spring:message code="fieldhdr.notifproblem" />:</td>
						<td><form:checkbox path="problemNotifications" /></td>
					</tr>

					<tr>
						<td style="align:right"><spring:message code="fieldhdr.notifsubmit" />:</td>
						<td><form:checkbox path="submissionNotifications" /></td>
					</tr>


					<tr>
						<td style="align:right"><spring:message code="fieldhdr.mailquota" /> (<spring:message code="fieldval.bytes" />):<i class="fa fa-asterisk"></i></td>
						<td><form:input path="mail_quote" /></td>
						<td><span class="label label-danger"><form:errors path="mail_quote" /></span></td>
					</tr>
					<tr>
						<td style="align:right"><spring:message code="fieldhdr.roles" /><i class="fa fa-asterisk"></i></td>
						<td>
						<form:select path="role" id="role" >
							<form:options items="${authorities}" itemValue="authority" itemLabel="authority" />
						</form:select>
						</td>
						<td><span class="label label-danger"><form:errors path="role" /></span></td>
					</tr>
				</c:if>

			</tbody>
		</table>
		<input type="submit" name="submit" id="submit" value="<spring:message code="button.edit"/>" />
		<input type="reset" name="reset" id="reset" value="<spring:message code="button.reset"/>" />
	</form:form>
</div>
