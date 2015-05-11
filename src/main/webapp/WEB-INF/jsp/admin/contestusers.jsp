<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<c:url value="/js/coj.js" />"></script>
<script type="text/javascript" src="/js/jquery.typewatch.js"></script>
<h2 class="postheader">
	<fmt:message key="page.header.admin.managecontest" />
</h2>
<div class="postcontent">
	<ul class="nav nav-pills">
		<li><a
			href="<c:url value="managecontest.xhtml?cid=${contest.cid}"/>"><fmt:message
					key="page.managecontest.link.mc" /></a></li>
		<li><a
			href="<c:url value="contestglobalflags.xhtml?cid=${contest.cid}"/>"><fmt:message
					key="page.managecontest.link.gf" /></a></li>
		<li><a
			href="<c:url value="globalsettings.xhtml?cid=${contest.cid}"/>"><fmt:message
					key="page.managecontest.link.gs" /></a></li>
		<li><a
			href="<c:url value="contestproblems.xhtml?cid=${contest.cid}"/>"><fmt:message
					key="page.managecontest.link.mp" /></a></li>
		<li><a
			href="<c:url value="contestproblemcolors.xhtml?cid=${contest.cid}"/>"><fmt:message
					key="page.managecontest.link.mpc" /></a></li>
		<li><a
			href="<c:url value="importicpcusers.xhtml?cid=${contest.cid}"/>"><fmt:message
					key="page.managecontest.link.iiu" /></a></li>
		<li><a href="<c:url value="baylorxml.xhtml?cid=${contest.cid}"/>"><fmt:message
					key="page.managecontest.link.bx" /></a></li>
		<li><a
			href="<c:url value="contestlanguages.xhtml?cid=${contest.cid}"/>"><fmt:message
					key="page.managecontest.link.ml" /></a></li>
		<li class="active"><a
			href="<c:url value="contestusers.xhtml?cid=${contest.cid}"/>"><fmt:message
					key="page.managecontest.link.mu" /></a></li>
		<li><a
			href="<c:url value="contestawards.xhtml?cid=${contest.cid}"/>"><fmt:message
					key="page.managecontest.link.aw" /></a></li>
		<li><a
			href="<c:url value="contestoverview.xhtml?cid=${contest.cid}"/>"><fmt:message
					key="page.managecontest.link.ov" /></a></li>
		<li><a
			href="<c:url value="contestimg.xhtml?cid=${contest.cid}"/>"><fmt:message
					key="page.managecontest.link.img" /></a></li>
	</ul>
	<form:form method="post" onsubmit="return SeleccionarRangosContest();"
		commandName="contest">
		<c:if test="${contest.grouped eq true}">
			<fieldset style="width: 400px;">
				<table class="contestlanguages">
					<tr>
						<td colspan="2"><fmt:message
								key="page.managecontest.guestgroup" /></td>
						<td><form:input path="guestGroup" /></td>
					</tr>
					<tr>
						<td colspan="2"><fmt:message key="page.managecontest.group" />:</td>
						<td><form:input path="groupd" /></td>
					</tr>
				</table>
			</fieldset>
		</c:if>
		<table class="contestlanguages">
			<tr>
				<td><fmt:message key="contestusers.cu" /></td>
				<td></td>
				<td><fmt:message key="contestusers.all" /></td>
			</tr>
			<c:choose>
				<c:when test="${contest.registration eq 0}">
					<tr>

						<td><span class="label label-info"><i
								class="fa fa-info-circle"></i> <fmt:message
									key="page.managecontest.register.free" /> </span></td>

					</tr>
				</c:when>

				<c:when test="${contest.registration eq 1}">
					<tr>
						<td><fmt:message key="page.globalsettings.rg.limit" /></td>
					</tr>
				</c:when>

				<c:when test="${contest.registration eq 2}">
					<tr>

						<td rowspan="2"><form:select path="usersids"
								id="contest_users" size="14" cssClass="login"
								cssStyle="width: 310px; border: 1px solid #577A5A;"
								multiple="true">
							</form:select></td>
						<td>
							<button name="boton" type="button"
								onclick="addremove('contest_users','allusers');">
								<i class="fa fa-arrow-right"></i>
							</button>
						</td>
						<td rowspan="2"><input id="search-allusers" />
							<form:select path="" id="allusers" size="14" cssClass="login"
								cssStyle="width: 310px; border: 1px solid #577A5A;"
								multiple="true">
								<form:options items="${allusers}" itemValue="uid"
									itemLabel="username" />
							</form:select></td>

					</tr>
					<tr>
						<td>
							<button name="boton" type="button"
								onclick="addremove('allusers','contest_users');">
								<i class="fa fa-arrow-left"></i>
							</button>
						</td>
					</tr>
				</c:when>
			</c:choose>
		</table>

		<table class="contestlanguages">
			<tr>
				<td><fmt:message key="contestjudges.cu" /></td>
				<td></td>
				<td><fmt:message key="contestjudges.all" /></td>
			</tr>


			<tr>

				<td rowspan="2"><form:select path="judgesids"
						id="contest_users_clarification" size="14" cssClass="login"
						cssStyle="width: 310px; border: 1px solid #577A5A;"
						multiple="true">
						<form:options items="${contest.judges}" itemValue="uid"
							itemLabel="username" />
					</form:select></td>

				<td>
					<button name="boton" type="button"
						onclick="addremove('contest_users_clarification','all_judge');">
						<i class="fa fa-arrow-right"></i>
					</button>
				</td>

				<td rowspan="2"><form:select path="" id="all_judge" size="14"
						cssClass="login"
						cssStyle="width: 310px; border: 1px solid #577A5A;"
						multiple="true">
						<form:options items="${alljudges}" itemLabel="username"
							itemValue="uid" />
					</form:select></td>
			</tr>
			<tr>
				<td>
					<button name="boton" type="button"
						onclick="addremove('all_judge','contest_users_clarification');">
						<i class="fa fa-arrow-left"></i>
					</button>
				</td>
			</tr>
		</table>
		<table class="contestlanguages">
			<tr>
				<td><fmt:message key="contestusers.bt" /></td>
				<td></td>
				<td><fmt:message key="contestusers.all" /></td>
			</tr>
			<c:choose>
				<c:when test="${contest.registration eq 0}">
					<tr>

						<td><span class="label label-info"><i
								class="fa fa-info-circle"></i> <fmt:message
									key="page.managecontest.register.free" /> </span></td>

					</tr>
				</c:when>

				<c:when test="${contest.registration eq 1}">
					<tr>
						<td><fmt:message key="page.globalsettings.rg.limit" /></td>
					</tr>
				</c:when>

				<c:when test="${contest.registration eq 2}">
					<tr>

						<td rowspan="2"><form:select path="balloontrackerids"
								id="balloontrackers" size="14" cssClass="login"
								cssStyle="width: 310px; border: 1px solid #577A5A;"
								multiple="true">
								<form:options items="${contest.balloontrackers}" itemValue="uid"
									itemLabel="username" />
							</form:select></td>

						<td>
							<button name="boton" type="button"
								onclick="addremove('balloontrackers','bt_allusers');">
								<i class="fa fa-arrow-right"></i>
							</button>
						</td>

						<td rowspan="2"><input id="search-btusers" /><form:select path="" id="bt_allusers"
								size="14" cssClass="login"
								cssStyle="width: 310px; border: 1px solid #577A5A;"
								multiple="true">
								<form:options items="${btusers}" itemValue="uid"
									itemLabel="username" />
							</form:select></td>

					</tr>
					<tr>
						<td>
							<button name="boton" type="button"
								onclick="addremove('bt_allusers','balloontrackers');">
								<i class="fa fa-arrow-left"></i>
							</button>
						</td>
					</tr>
				</c:when>
			</c:choose>
		</table>
		<input type="submit" name="but"
			value="<spring:message code="button.update"/>" />
	</form:form>
	<a href="deleteusercontest.xhtml?all=true&cid=${contest.cid}"><i
		class="fa fa-trash"></i>&nbsp;Delete</a>
	<table class="table table-condensed table-striped">
		<thead>
			<tr>
				<th>User</th>
				<th>Nick</th>
				<th>Group</th>
				<th>Actions</th>
			</tr>
		</thead>
		<c:forEach items="${contest.users}" var="user">

			<tr>
				<td><a href="manageuser.xhtml?username=${user.username}">${user.username}</a></td>
				<td>${user.nick}</td>

				<td>${user.group}</td>
				<td>&nbsp; <a
					href="deleteusercontest.xhtml?uid=${user.uid}&cid=${contest.cid}"><i
						class="fa fa-trash"></i></a>
				</td>

			</tr>
		</c:forEach>
	</table>

</div>
<ul id="user-template" class="hide">
	<c:forEach items="${allusers}" var="user">
		<li data-uid="${user.uid}">${user.username}</li>
	</c:forEach>
</ul>
<ul id="bt-template" class="hide">
	<c:forEach items="${btusers}" var="user">
		<li data-uid="${user.uid}">${user.username}</li>
	</c:forEach>
</ul>
<script>
	$("#search-allusers").typeWatch(
			{
				callback : function(value) {
					$("#allusers").empty();
					$("#user-template li").filter(":contains(" + value + ")")
							.each(
									function() {
										$("#allusers").append(
												"<option value="
														+ $(this).data("uid")
														+ ">" + $(this).html()
														+ "</option>");
									});
				},
				wait : 250,
				highlight : true,
				captureLength : 0
			});
	$("#search-btusers").typeWatch(
			{
				callback : function(value) {
					$("#bt_allusers").empty();
					$("#bt-template li").filter(":contains(" + value + ")")
							.each(
									function() {
										$("#bt_allusers").append(
												"<option value="
														+ $(this).data("uid")
														+ ">" + $(this).html()
														+ "</option>");
									});
				},
				wait : 250,
				highlight : true,
				captureLength : 0
			});
</script>
