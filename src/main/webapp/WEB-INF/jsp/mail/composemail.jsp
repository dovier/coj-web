<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer="16kb" autoFlush="true"%>
<script type="text/javascript"
	src="<c:url value="/js/WYSIWYG/source.js" />"></script>
<script type='text/javascript'
	src="<c:url value="/js/jquery.js" />"></script>
<script type='text/javascript' src="<c:url value="/js/coj.js" />"></script>

<div class="pull-right"><a href="<c:url value="inbox.xhtml"/>" class="mailheader"><i
		class="fa fa-close"></i></a></div>
<h2 class="postheader">
	<spring:message code="pagehdr.mailcomposemsg" />
</h2>
<div class="postcontent">
	<c:if test="${inboxOverflow}">
		<center>
			<div class="label label-info">
				<i class="fa fa-info-circle"></i>
				<spring:message code="inbox.overflow" />
			</div>
		</center>
	</c:if>
	<c:if test="${not empty receiverInboxOverflow}">
		<center>
			<spring:message code="receiver.inbox.overflow" />${receiverInboxOverflow}</center>
	</c:if>
</div>
<form:form method="POST" class="form-horizontal" commandName="mail" id="composeform">
	
		 <div class="form-group"> 
			   <label class="col-md-1 control-label" ><spring:message code="fieldhdr.to" />:</label>
			<div class="col-md-4"><form:input path="usernameTo" class="form-control" /> </div>
		</div><div class="col-md-offset-1"><span
				class="label label-danger"><form:errors path="usernameTo" /></span></div>
		

	<div class="form-group"> 
			  <label class="col-md-1 control-label"><spring:message code="fieldhdr.subject" />:</label>
			<div class="col-md-4"><form:input path="title"  class="form-control"/></div></div><div class="col-md-offset-1"> <span
				class="label label-danger"><form:errors path="title" /></span></div>

		<div class="form-group"> 
			
			<div class="col-md-10  col-md-offset-1"><form:textarea path="content" rows="15" id="code"
					cssClass="des" /></div>
		</div>
		
			<div class="col-md-offset-1"><span class="label label-danger"><form:errors
						path="general" /></span></div>
	<div class="col-md-offset-5">	
	<input type="submit"  class="btn btn-success" value="<spring:message code="button.send" />" />
	<input type="reset" class="btn btn-warning" value="<spring:message code="button.reset" />" />
	<input type="button" class="btn btn-info" value="<spring:message code="button.savedraft" />"
		onclick="saveDraft()" />
	<a href="<c:url value="inbox.xhtml"/>" class="mailheader btn btn-danger"><i
		class="fa fa-close"></i>&nbsp;<spring:message code="link.close" /></a></div>
</form:form>
</div>
