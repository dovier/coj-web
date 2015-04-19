<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h2 class="postheader">
	<fmt:message key="addproblem.title" />
</h2>
<div class="postcontent">
	<form:form method="post" enctype="multipart/form-data" commandName="problem">

		<div id="myTabs">
			<div id="english">
				<table class="login">
					<tr>
						<td><label><fmt:message key="addproblem.name" /></label><i class="fa fa-asterisk"></i></td>
						<td><form:input path="title" /></td>
						<td><span class="label label-danger"><form:errors path="title" /></span></td>
					</tr>
				</table>

				<label><fmt:message key="addproblem.description" /></label>

				<div class="tabContent">
					<form:textarea path="description" id="code" rows="15" cssStyle="width 99%" />
				</div>
				<label><fmt:message key="addproblem.input" /></label><br />
				<div class="tabContent">
					<form:textarea path="input" id="input" cssClass="in" rows="15" cssStyle="width 99%" />
					<br />
				</div>
				<label><fmt:message key="addproblem.output" /></label><br /> <span class="label label-danger"><form:errors path="output" /></span>
				<div class="tabContent">
					<form:textarea path="output" id="output" cssClass="out" rows="15" cssStyle="width 99%" />
					<br />
				</div>
				<label><fmt:message key="addproblem.comm" /></label><br />
				<div class="tabContent">
					<form:textarea path="comments" id="hint" cssClass="in" rows="15" cssStyle="width 99%" />
				</div>

			</div>
		</div>
		<label><fmt:message key="addproblem.inputex" /></label>
		<br />
		<div>
			<form:textarea path="inputex" id="code3" cssClass="in_ex" rows="15" cssStyle="width 99%" />

			<br />
		</div>
		<label><fmt:message key="addproblem.outputex" /></label>
		<br />
		<div>
			<form:textarea path="outputex" id="code4" cssClass="out_ex" rows="15" cssStyle="width 99%" />
			<br />
		</div>
		<label><fmt:message key="addproblem.author" /></label>
		<div>
			<form:select path="author">
				<form:options items="${sources}" itemLabel="fullName" itemValue="fullName" />
			</form:select>
		</div>
		<br />
		<table class="login">

			<tr>

				<td><label> Special Judge </label></td>
				<td><form:checkbox path="special_judge" /></td>

			</tr>

			<tr>

				<td><label> <fmt:message key="addproblem.casechk" />
				</label></td>
				<td><form:checkbox path="multidata" onchange="onCase();" id="casecheck" /></td>

			</tr>

			<tr>
				<td><label><fmt:message key="addproblem.casetime" /> <b>(MS)</b></label></td>
				<td><c:choose>
						<c:when test="${problem.multidata == true}">
							<form:input path="casetimelimit" id="casetime" onclick="" />
						</c:when>
						<c:otherwise>
							<form:input path="casetimelimit" id="casetime" disabled="true" />
						</c:otherwise>
					</c:choose></td>
				<td><span class="label label-danger"><form:errors path="casetimelimit" /></span></td>
			</tr>



			<tr>
				<td><label><fmt:message key="addproblem.time" /> <b>(MS)</b></label></td>
				<td><form:input path="time" /></td>
				<td><span class="label label-danger"><form:errors path="time" /></span></td>
			</tr>
			<tr>
				<td><label><fmt:message key="addproblem.memory" /> <b>(KB)</b></label></td>
				<td><form:input path="memory" /></td>
				<td><span class="label label-danger"><form:errors path="memory" /></span></td>
			</tr>
			<tr>
				<td><label><fmt:message key="addproblem.source" /> <b>(KB)</b></label></td>
				<td><form:input path="fontsize" /></td>
				<td><span class="label label-danger"><form:errors path="fontsize" /></span></td>
			</tr>
			<tr>
				<td><fmt:message key="addproblem.contest" /></td>
				<td><form:select path="contest">
						<form:option label="none" value="0" />
						<form:options items="${contests}" itemLabel="name" itemValue="cid" />
					</form:select></td>
			</tr>
		</table>
		<label><fmt:message key="addproblem.enable" /></label>
		<form:checkbox path="enabled" />


		<div id="inputfiles">
			<fieldset style="width: 480px;">
				<div>
					<label id="addinputgen" onclick="addInput('1');"><fmt:message key="addproblem.inputgen" /></label> <input type="file" name="inputgen" />
				</div>
				<div>
					<label id="addmodelsol" onclick="addInput('1');"><fmt:message key="addproblem.modelsol" /></label> &nbsp;<input type="file" name="modelsol" />
				</div>
			</fieldset>
			<fieldset style="width: 480px;">
				<table id="uploadinput" class="login">
					<tbody id = "uploadinputbody">
					<tr id ="file_1">
						<td><input type="file" name="file1" /></td>
						<td><input type="file" name="out_1" /></td>
					</tr>
					</tbody>
				</table>
			<input type="button" style="margin:right" id="add" onclick="addInput('1');" value='<fmt:message key="addproblem.addinput" />' />
			</fieldset>
			<fieldset style="width: 480px;">
				<legend>
					<fmt:message key="page.advancedcfg.languages" />
				</legend>
				<div class="contestlanguages">
					<form:checkbox id="all" onchange="selection('all','languageids');" label="All" path="" value="" />
					<br />
					<form:checkboxes path="languageids" items="${languages}" itemValue="lid" itemLabel="descripcion" delimiter="</br>" />
					<span class="label label-danger"><form:errors path="languages" /></span>
				</div>
			</fieldset>
		</div>
		<input type="submit" name="but" value="Add" />
	</form:form>
</div>

<script type="text/javascript" src="<c:url value="/js/coj.js" />"></script>


<style>
<!--
--> /* 
    Document   : sigpt
    Created on : 07-may-2014, 18:03:48
    Author     : nenes
    Description:
        Purpose of the stylesheet follows.
*/
root {
	display: block;
}
</style>
<script type="text/javascript" src="<c:url value="/js/WYSIWYG/source.js" />"></script>