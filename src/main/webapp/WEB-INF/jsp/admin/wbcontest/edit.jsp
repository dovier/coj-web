<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script  type="text/javascript" src="<c:url value="/js/ui/jquery-ui.min.js" />"></script>
<link rel="stylesheet" href="<c:url value="/css/ui/jquery-ui.min.css"/>" type="text/css" media="screen" />


<h2 class="postheader">
	<fmt:message key="page.header.admin.wbcontest.edit" />
</h2>

<div class="postcontent">
	<a class="btn btn-primary" href='<c:url value="/admin/wboard/contest/list.xhtml"/>'> <spring:message code="wboard.list.contest"/> </a>
	
	<br />
	<br />
	<div class="row">
		<div class="col-xs-12">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<spring:message code="fieldhdr.edit" />
				</div>
				<div id="edit" class="panel-body">
					<div class="col-xs-10">
						<form:form method="post"
							commandName="wbContest" cssClass="form-horizontal">	

							<legend>
								<spring:message code="pagehdr.edit.contest" />
							</legend>

							<tiles:insertAttribute name="form"/>
															
							<div class="form-actions pull-right">
								<input class="btn btn-primary" type="submit" name="submit"
									id="submit" value="<spring:message code="button.edit"/>" /> <input
									class="btn btn-primary" type="reset" name="reset" id="reset"
									value="<spring:message code="button.reset"/>" />
							</div>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>	
</div>