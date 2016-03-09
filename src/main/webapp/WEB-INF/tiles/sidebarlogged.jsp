<div class="block">
	<div class="panel panel-primary block-body">
		<div class="panel-heading">
			<i class="fa fa-user fa-lg"></i>&nbsp;
			<spring:message code="block.userlogged" />
			<authz:authentication property="principal.username" />
		</div>
		<div class="panel-body blockcontent-body">
			<!-- block-content -->
			<div>
				<ul class="list-unstyled">
					<authz:authorize
						ifAnyGranted="ROLE_ADMIN,ROLE_SUPER_PSETTER,ROLE_FILE_MANAGER,ROLE_PSETTER">
						<li <c:if test="${idpage == 'linkadmin'}"> class="item-sidebar-selected" </c:if>><a href="<c:url value="/admin/index.xhtml"/>"><i
								class="fa fa-wrench"></i>&nbsp;<spring:message code="link.admin" /></a></li>
					</authz:authorize>
					<authz:authorize ifAllGranted="ROLE_USER">
						<li <c:if test="${idpage == 'useraccount'}"> class="item-sidebar-selected" </c:if>><a
							href="<c:url value="/user/useraccount.xhtml?username=" /><authz:authentication property="principal.username" />"><i
								class="fa fa-user"></i>&nbsp;<spring:message
									code="link.vprofile" /></a></li>
					</authz:authorize>
					<li <c:if test="${idpage == 'updateaccount'}"> class="item-sidebar-selected" </c:if>><a href="<c:url value="/user/updateaccount.xhtml" />"><i
							class="fa fa-edit"></i>&nbsp;<spring:message code="link.eaccount" /></a></li>
					<authz:authorize ifAnyGranted="ROLE_USER">
						<li><a href="<c:url value="/mail/inbox.xhtml?start=1"/>"><i
								class="fa fa-envelope"></i>&nbsp;<spring:message
									code="link.mail" /></a>
							<ul>
								<li <c:if test="${idpage == 'inbox'}"> class="item-sidebar-selected" </c:if>><a href="<c:url value="/mail/inbox.xhtml?start=1"/>">
										<i class="fa fa-inbox"></i>&nbsp;<spring:message
											code="link.inbox" /> <c:if test="${mailunread ne 0}">
											<span class="label label-danger">(${mailunread})</span>
										</c:if>

								</a>
								</li>

								<li <c:if test="${idpage == 'outbox'}"> class="item-sidebar-selected" </c:if>><a href="<c:url value="/mail/outbox.xhtml?start=1"/>"><i
										class="fa fa-send"></i>&nbsp;<spring:message code="link.sent" />
								</a></li>

								<li <c:if test="${idpage == 'drafts'}"> class="item-sidebar-selected" </c:if>><a href="<c:url value="/mail/draft.xhtml"/>"><i
										class="fa fa-envelope-o"></i>&nbsp;<spring:message
											code="link.drafts" /> <c:if test="${drafts ne 0}">
											<span class="label label-default label-drafts">(${drafts})</span>
										</c:if> </a></li>
							</ul></li>
					</authz:authorize>

					<li <c:if test="${idpage == 'tos'}"> class="item-sidebar-selected" </c:if>><a href="<c:url value="/general/tos.xhtml"/>"><i
							class="fa fa-check"></i> <spring:message code="fieldhdr.agreementcojtos.menu"/></a></li>

					<li><a href="<c:url value="/j_spring_logout"/>"><i
							class="fa fa-sign-out"></i>&nbsp;<spring:message
								code="link.logout" /></a></li>
				</ul>

			</div>
			<!-- /block-content -->

			<div class="cleared"></div>
		</div>
		<div class="cleared"></div>
	</div>
</div>