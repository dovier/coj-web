<%@page import="cu.uci.coj.utils.Utils"%>
<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="layout-cell sidebar1">
	<authz:authorize ifAnyGranted="ROLE_ANONYMOUS">
		<div class="block">
			<div class="panel panel-primary block-body">
				<div class="panel-heading blockheader">
					<i class="fa fa-lock fa-lg"></i>&nbsp;
					<spring:message code="block.login" />
				</div>
				<div class="panel-body blockcontent-body">
					<!-- block-content -->
					<div>
						<form action="<c:url value="/j_spring_security_check"/>"
							method="post">

							<table width=50% border=0 class="login">
								
								<tr>
									<td><input class="inputbox" type='text' name='j_username'
										size="17"
										<c:if test="${not empty param.login_error}">value='<c:out value="${SPRING_SECURITY_LAST_USERNAME}"/>'</c:if>></td>
								</tr>
								<tr>
									<td style="padding-top: 5px"><spring:message code="fieldhdr.password"/>:</td>
								</tr>
								<tr>
									<td style="padding-top: 5px"><input class="inputbox"
										type='password' name='j_password' size="17"></td>
								</tr>
								<c:if test="${not empty param.login_error}">
									<tr>
										<td colspan="2"><span class="label label-danger"><spring:message
													code="page.general.login.error" /></span> <br /> <!--Reason:
                                                <%=((AuthenticationException) session
							.getAttribute(AbstractAuthenticationProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY))
							.getMessage()%>
                                                --></td>
									</tr>
								</c:if>
								<tr align="center">
									<td colspan="2" style="padding-top: 8px;"><input
										name="submit" value="<spring:message code="button.login"/>"
										type="submit"></td>
								</tr>
								<tr>
									<td colspan="2" align="center" style="padding-top: 7px"></td>
								</tr>
							</table>
							<input name="rurl" value="<%=request.getAttribute("rurl")%>"
								type="hidden">
						</form>

					</div>
					<!-- /block-content -->

					<div class="cleared"></div>
				</div>
				<div class="cleared"></div>
			</div>
		</div>
	</authz:authorize>
	<authz:authorize ifAnyGranted="ROLE_ADMIN,ROLE_USER,ROLE_TEAM">
		<div class="block">
			<div class="panel panel-primary block-body">
				<div class="panel-heading blockheader">
					<i class="fa fa-user fa-lg"></i>&nbsp;
					<spring:message code="block.userlogged" />
					<authz:authentication property="principal.username" />
				</div>
				<div class="panel-body blockcontent-body">
					<!-- block-content -->
					<div>
						<ul class="list-unstyled">
							<authz:authorize ifAnyGranted="ROLE_ADMIN">
								<li><i class="fa fa-wrench"></i><a
										href="<c:url value="/admin/index.xhtml"/>"><spring:message
												code="link.admin" /></a></li>
							</authz:authorize>
							<li><i class="fa fa-sign-out"></i>&nbsp;<a
									href="<c:url value="/j_spring_logout"/>"><spring:message
											code="link.logout" /></a></li>
						</ul>
					</div>
					<!-- /block-content -->
					<div class="cleared"></div>
				</div>
				<div class="cleared"></div>
			</div>
		</div>
	</authz:authorize>
</div>
