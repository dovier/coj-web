	<div class="block">
		<div class="panel panel-primary block-body">
			<div class="panel-heading">
				<i class="fa fa-cubes fa-lg"></i>&nbsp;
				<spring:message code="block.pcontests" />
			</div>
			<div class="panel-body blockcontent-body">
				<!-- block-content -->
				<div>
					<ul class="list-unstyled">
						<authz:authorize ifAnyGranted="ROLE_USER,ROLE_TEAM">
							<li><a href="<c:out value="/practice/createvc.xhtml"/>"><i
									class="fa fa-plus-circle"></i>&nbsp;<spring:message
										code="link.create" /></a></li>
							<li><a href="<c:out value="/practice/mylist.xhtml"/>"><i
									class="fa fa-list"></i>&nbsp;<spring:message code="link.mylist" /></a></li>
						</authz:authorize>
						<li><a href="<c:out value="/practice/globallist.xhtml"/>"><i
								class="fa fa-list-alt"></i>&nbsp;<spring:message
									code="link.globallist" /></a></li>
						<!-- 	<li class="iconstats"><a href="<c:url value="/practice/virtualstatistics.xhtml"/>"><spring:message code="link.statistics" /></a></li>
						 -->
					</ul>
				</div>
				<!-- /block-content -->

				<div class="cleared"></div>
			</div>
			<div class="cleared"></div>
		</div>
		
	</div>