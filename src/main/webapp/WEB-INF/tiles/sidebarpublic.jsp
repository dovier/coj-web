		<div class="block">
			<div class="panel panel-primary block-body">
				<div class="panel-heading">
					<i class="fa fa-lock fa-lg"></i>&nbsp;
					<spring:message code="block.login" />
				</div>
				<div class="panel-body blockcontent-body">
					<!-- block-content -->
					<div>
						<c:if test="${not empty param.login_error}">
							<div class="label label-danger">
								<spring:message code="page.general.login.error" />
							</div>
							<!--Reason:

                                                -->
						</c:if>
						<form action="<c:url value="/j_spring_security_check"/>"
							method="post">
							<div class="margin-top-05 form-group">
								<div class="input-group">
									<span class="input-group-addon"> <i class="fa fa-user"></i></span>
									<c:if test="${not empty param.login_error}">
										<input class="form-control" placeholder="<spring:message code="fieldhdr.usermail"/>" type='text'
											name='j_username' size="17"
											value='<c:out value="${SPRING_SECURITY_LAST_USERNAME}"/>'>
									</c:if>
									<c:if test="${empty param.login_error}">
										<input class="form-control" placeholder="<spring:message code="fieldhdr.usermail"/>" type='text'
											name='j_username' size="17">
									</c:if>
								</div>
							</div>
							<div class="form-group">
								<div class="input-group">
									<span class="input-group-addon"><i
										class="fa fa-ellipsis-h"></i></span> <input class="form-control"
										placeholder="<spring:message code="fieldhdr.password"/>" name="j_password" size="17"
										type="password">
								</div>
							</div>
							<div class="form-group">
								<a href="<c:url value="/user/createnewaccount.xhtml"/>"><i
									class="fa fa-user"></i> <spring:message code="link.register" /></a>
								<span class="pull-right"> <input name="submit"
									id="submit" class="pull-right btn btn-primary"
									value="<spring:message code="button.login"/>" type="submit">
								</span>
							</div>
							<div class="clearfix"></div>
							<a href="<c:url value="/user/forgottenpassword.xhtml"/>"><i
								class="fa fa-key"></i> <spring:message code="link.fpassword" /></a>
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