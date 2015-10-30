<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
  <!--link rel="stylesheet" href="../Style/style.css" type="text/css" media="screen" /-->
<link rel="stylesheet"
	href="<c:url value="/css/ui-1.11.2/jquery-ui-timepicker-addon.css"/>"
	type="text/css" media="screen" />
<link rel="stylesheet"
	href="<c:url value="/css/ui-1.11.2/jquery-ui-1.11.2.min.css"/>"
	type="text/css" media="screen" />
<h2 class="postheader">
    <spring:message code="page.general.admin.header" />
	: <spring:message code="pagehdr.submissions" /> 	
</h2>
<div class=" row postcontent">
	<div class="col-xs-12">
		<form:form id="filter-form" commandName="filter" action="/admin/rejudgesubmissions.xhtml" method="post">
			<div class="row form-group">
				<div class="col-xs-4">
					<spring:message code="fieldhdr.user" />
					<form:input cssClass="form-control" path="username"
						value="${filter.username}" size="10" />
				</div>
				<div class="col-xs-4">
					<spring:message code="fieldhdr.startdate" />
					<form:input cssClass="form-control" path="startDate" id="startDate"
						value="${filter.startDate}" size="10" />
				</div>
				<div class="col-xs-4">
					<spring:message code="fieldhdr.enddate" />
					<form:input cssClass="form-control" path="endDate" id="endDate"
						value="${filter.endDate}" size="10" />
				</div>
			</div>
			<div class="row form-group">
				<div class="col-xs-4">
					<spring:message code="fieldhdr.cid" />
					<form:input cssClass="form-control" path="cid"
						value="${filter.cid}" size="8" />
				</div>
				<div class="col-xs-4">
					<spring:message code="fieldhdr.startSid" />
					<form:input cssClass="form-control" path="startSid"
						value="${filter.startSid}" size="8" />
				</div>
				<div class="col-xs-4">
					<spring:message code="fieldhdr.endSid" />
					<form:input cssClass="form-control" path="endSid"
						value="${filter.endSid}" size="8" />
				</div>
			</div>
			<div class="row form-group">
				<div class="col-xs-4">
					<spring:message code="fieldhdr.prob" />
					<form:input cssClass="form-control" path="pid"
						value="${filter.pid}" size="8" />
				</div>
				<div class="col-xs-4">
					<spring:message code="fieldhdr.judgment" />
					<form:select cssClass="form-control" path="status">
						<form:option value="">
							<spring:message code="fieldhdr.all" />
						</form:option>
						<form:options items="${statuslist}" itemValue="key"
							itemLabel="key"></form:options>
					</form:select>
				</div>
				<div class="col-xs-4">
					<spring:message code="fieldhdr.lang" />
					<form:select cssClass="form-control" path="language" id="language">
						<form:option value="">
							<spring:message code="fieldhdr.all" />
						</form:option>
						<form:options items="${languagelist}" itemValue="language"
							itemLabel="language"></form:options>
					</form:select>
				</div>
			</div>
			<div class="row form-group">
				<div class="col-xs-12">
					<div class="form-actions pull-right">
						<span id="count"></span> <input id="filter-button"
							class="btn btn-primary" type="submit"
							value="<spring:message code="button.filter"/>"
							name="filter-button" /> <input id="rejudge"
                                                        class="btn btn-primary" name="rejudge" type="submit" 
							value="<spring:message code="button.rejudge"/>" />
					</div>
				</div>
			</div>
		</form:form>
	</div>
	<div class="col-xs-12">
		<div id="display-table-container"
			data-reload-url="/admin/tables/managesubmissions.xhtml"></div>
	</div>
</div>
        
       
<script type="text/javascript"
	src="<c:url value="/js/ui/jquery-ui.min.js" />"></script>

<script type="text/javascript"
	src="<c:url value="/js/ui-1.11.2/jquery-ui-timepicker-addon.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/js/ui-1.11.2/jquery-ui-rangetimepicker-addon.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/js/ui-1.11.2/jquery-migrate-1.0.0.js"/>"></script> 
<script src="https://code.jquery.com/jquery-2.1.4.min.js"></script>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/prettify/r298/run_prettify.min.js"></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap3-dialog/1.34.5/css/bootstrap-dialog.min.css" rel="stylesheet" type="text/css" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap3-dialog/1.34.5/js/bootstrap-dialog.min.js"></script>
        
<script>
      var currentForm;
    $("#rejudge").click(function() {
        currentForm = $(this).closest('form');
        var tr = $("#submission tr[class!='empty']").length - 1;

        var dialog = new BootstrapDialog({
            message: '<div><p><spring:message code="fieldhdr.dialogconfirmsend"/> '+tr+' <spring:message code="fieldhdr.dialogconfirmsendtoo"/></p></div>',
            title:'<spring:message code="fieldhdr.confirmsend"/>',
            buttons: [{
                    label: '<spring:message code="btn.text.cancel"/>',
                    action: function(dialogRef) {
                        dialogRef.close();
                    }
                },
                {
                    label: 'OK',
                    cssClass: 'btn-primary',
                    action: function() {
                        currentForm.submit();
                    }
                }]
        });
        dialog.realize();
        dialog.open();
        return false;
    });
</script>
<script>
	function rejudge24h(sid) {
		$.ajax({
			type : "GET",
			url : "/admin/24h/rejudge.json",
			data : "sid=" + sid,
			dataType : 'text',
			success : function(data) {
			}
		});
	}
      	function rejudgeContest(sid) {
		$.ajax({
			type : "GET",
			url : "/admin/contest/rejudge.json",
			data : "sid=" + sid,
			dataType : 'text',
			success : function(data) {
			}
		});
	}
	
	function toggle24h(sid) {
		$.ajax({
			type : "GET",
			url : "/admin/24h/togglesub.json",
			data : "sid=" + sid,
			dataType : 'text',
			success : function(data) {
			}
		});
	}
	function toggleContest(sid) {
		$.ajax({
			type : "GET",
			url : "/admin/contest/togglesub.json",
			data : "sid=" + sid,
			dataType : 'text',
			success : function(data) {
			}
		});
	}

	$('#rejudge').click(function() {
		$('#form').attr('action', '/admin/rejudgesubmissions.xhtml');
		$('#form').attr('method', 'post');
	});

	function count() {
		$.ajax({
			type : "GET",
			url : "/admin/queuecount.json",
			dataType : 'text',
			success : function(data) {
				$('#count').html(data);
			},
			complete : function(data) {
				
			}
		});

	};
     
	$(function() {
		setInterval(count, 2000);
	});
        
	$(initStandardFilterForm);
</script>
