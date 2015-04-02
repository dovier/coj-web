<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<link rel="stylesheet" href="<c:url value="/css/wboard.css"/>" type="text/css" media="screen" />

<h2 class="postheader">
	<spring:message code="pagehdr.wboard" />
</h2>

<div class="postcontent">
	<!-- content -->
	<div style="clear: both;"></div>
	<div class="row">
		<div class="col-xs-12">
			<form id="filter-form" class="form-inline">
				<div class="form-group" id="combo-select">

				</div>		
				
				<authz:authorize ifAllGranted="ROLE_USER">
					<div class="form-group pull-right">
						<select id="follow-select" class="form-control" name="followed">
							<option value="0"><spring:message code="link.all"/></option>
							<option value="1"><spring:message code="link.followed"/></option>
						</select>
					</div>
				</authz:authorize>
			</form>
		</div>
	</div>
	
	<br>
			
	 <div id="display-table-container" data-reload-url="/tables/wbcontests.xhtml"></div>
	
</div>


<script>

$(document).ready(function(){
	var follow_text = "<spring:message code="button.follow"/>";
	var unfollow_text = "<spring:message code="button.unfollow"/>";

	function loadCombobox(followed) {
		$.ajax({ 
            url: "/wboard/combobox.xhtml",
            type: "GET",
            data:{"followed":followed},
            success:function (datas){
            	$("#combo-select").html(datas);     
            	displayTableReload($("#filter-form").serialize());
            }              
        });
		
	}	

	loadCombobox(0);
	
	$('#combo-select').on("click", ".btn-follow", function(e){
		var elem = $(this);
		e.preventDefault();
		if($(this).text() == follow_text) {
			$.ajax({
	            url: "/wboard/follow.json",
	            type: 'POST',
	            data:{"sid": elem.data("site-id")},
	            success:function (datos){
	            	elem.toggleClass("btn-primary");
	            	elem.toggleClass("btn-danger");
	            	elem.text(unfollow_text);	        	
	            }                  
	        });
		} else {
			$.ajax({
	            url: "/wboard/unfollow.json",
	            type: 'POST',
	            data:{"sid": elem.data("site-id")},
	            success:function (datos){
	            	elem.toggleClass("btn-primary");
	            	elem.toggleClass("btn-danger");
	            	elem.text(follow_text);
	            }                  
	        });
		}
	});		
	
	$("#follow-select").change(function(){
		loadCombobox($(this).val());
	});
	
	$('#combo-select').on("change", "#site-select", function(){
		var option = $(this);
		displayTableReload($("#filter-form").serialize());
		$("button").each(function(){
			var elem = $(this);
			if(elem.data("site-id") == option.val()) {
				elem.removeClass("hide");
			} else {
				elem.addClass("hide");
			}
		});
	});	

});
	
</script>