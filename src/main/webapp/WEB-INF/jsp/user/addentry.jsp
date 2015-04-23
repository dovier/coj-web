<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer="16kb" autoFlush="true"%>

<h2 class="postheader">
	<fmt:message key="page.general.admin.header" />
	: Add Entry
</h2>
<div class="postcontent">
	<form:form method="post" commandName="entry">
		<table class="createnewentry">
			<tbody>
				<tr>
					<td style="align:right">Text<i class="fa fa-asterisk"></i></td>
					<td><form:textarea path="text" /></td>
					<td><span class="label label-danger"><form:errors path="text" /></span></td>
				</tr>
				<tr>
					<td style="align:right">Enabled<i class="fa fa-asterisk"></i></td>
					<td><form:checkbox path="enabled" /></td>
					<td><span class="label label-danger"><form:errors path="enabled" /></span></td>
				</tr>

				<tr>
					<td></td>
					<td colspan="2">
						<div style="text-align:right">
							<input type="submit" name="submit" id="submit" value="<fmt:message key="judge.register.submit.value"/>" /> <input type="reset" name="submit" id="submit" value="<fmt:message key="judge.register.reset.value"/>" />
						</div>
					</td>
				</tr>
			</tbody>
		</table>
	</form:form>
</div>
