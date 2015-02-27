<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer="16kb" autoFlush="true"%>

<link rel="stylesheet" href="<c:url value="/css/wboard.css"/>" type="text/css" media="screen" />

<h2 class="postheader">
	<fmt:message key="page.general.admin.header" />
	: Manage Entries
</h2>
<div class="postcontent">
	<div id="hidden-form">
		<form:form method="post" id="entryForm" commandName="entry">
			<form:hidden id="id" path="id" />
			<div class="form-group">
				<label class="control-label"><fmt:message key="entry.text" /></label>
				<form:textarea rows="5"  id="text" cssClass="form-control" path="text" />
			</div>
			<div class="margin-top-05 form-actions pull-right">
				<input class="btn btn-success same-width" type="button" name="submit"
					id="submit" value="<fmt:message key="button.edit"/>" />
				<input class="btn btn-primary same-width" id="action-cancel" type="button"
					value="<fmt:message key="btn.text.cancel"/>" />
			</div>
		</form:form>
	</div>
	<br />
	 <div id="display-table-container" data-reload-url="/admin/tables/manageentries.xhtml"></div>
	 
	<br />
</div>
<%@include file="/WEB-INF/jsp/general/confirmmessage.jsp"%>
<script>
	$(function() {
		$('#submit').click(function() {
			$('#entryForm').ajaxSubmit();
			$('p#'+$('#id').val()).html($('#text').val());
			show();
		});
	});
	$(document).keypress(function(e) { 
	    if (e.keyCode == 27) { 
	    	show();
	    }
	});
	function edit(id) {
		$('#id').val(id);
		$('#text').val($('p#' + id).html());
		hide();
	}
	$("#action-cancel").click(function() {
		show();
	});
	$("#background").click(function() {
		show();
	});
	function hide() {
		$("#hidden-form").fadeIn("fast");
		$("#background").fadeIn("fast");
		$('html').css({
			'overflow' : 'hidden'
		});
	}
	function show() {
		$("#background").fadeOut("fast");
		$("#hidden-form").fadeOut("fast");
		$('html, body').css({
			'overflow' : 'auto'
		});
	}	
	$("#hidden-form").hide();
	
	$(document).ready(displayTableReload(" "));
</script>