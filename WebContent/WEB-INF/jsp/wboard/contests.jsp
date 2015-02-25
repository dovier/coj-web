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
				<div class="form-group">
					<select id="site-select" class="form-control" name="site">
						<option value="0"><spring:message code="fieldhdr.all"/></option>
						<c:forEach items="${sites}" var="current_site">
							<option value="${current_site.sid}" <c:if test="${current_site.sid eq site}">selected</c:if>>${current_site.site}</option>
						</c:forEach>
					</select>
				</div>	
				
				<authz:authorize ifAllGranted="ROLE_USER">
					<c:if test="${site != 0 && followed == 0}">
						<div class="form-group">
							<c:choose>
								<c:when test="${follow}">
									<button id="follow" class="btn btn-danger"><spring:message code="button.unfollow"/></button>
								</c:when>
								<c:otherwise>
									<button id="follow" class="btn btn-primary"><spring:message code="button.follow"/></button>	
								</c:otherwise>
							</c:choose>
						</div>	
					</c:if>		
				</authz:authorize>		
				
				<authz:authorize ifAllGranted="ROLE_USER">
					<div class="form-group pull-right">
						<select id="follow-select" class="form-control" name="followed">
							<option value="0" <c:if test="${followed == 0}">selected</c:if>><spring:message code="link.all"/></option>
							<option value="1" <c:if test="${followed == 1}">selected</c:if>><spring:message code="link.followed"/></option>
						</select>
					</div>
				</authz:authorize>
			</form>
		</div>
	</div>
	
	<br>
			
	 <div id="display-table-container" data-reload-url="/tables/wbcontests.xhtml"></div>
	
</div>


<script src="<c:url value="/js/countdown.js"/>"></script>
<script>
$(document).ready(function(){
	var sid = ${site};
	var follow_text = "<spring:message code="button.follow"/>";
	var unfollow_text = "<spring:message code="button.unfollow"/>";
	var server_time = ${now};
	var time_access = (new Date().getTime());
	
	$("#site-select, #follow-select").change(function(){
		$("#filter-form").submit();
	});
	$("#follow").click(function(e){
		e.preventDefault();
		if($(this).text() == follow_text) {
			$.ajax({
	            url: "/wboard/follow.xhtml",
	            type: 'POST',
	            data:{"sid":sid},
	            success:function (datos){
	            	$("#follow").toggleClass("btn-primary");
	            	$("#follow").toggleClass("btn-danger");
	        		$("#follow").text(unfollow_text);	        	
	            }                  
	        });
		} else {
			$.ajax({
	            url: "/wboard/unfollow.xhtml",
	            type: 'POST',
	            data:{"sid":sid},
	            success:function (datos){
	            	$("#follow").toggleClass("btn-primary");
	            	$("#follow").toggleClass("btn-danger");
	        		$("#follow").text(follow_text);
	            }                  
	        });
		}
	});
	
	function updater() {
		countDown(server_time, time_access);
	}

	countDown(server_time, time_access);	
	setInterval(updater, 1000);
});

$(document).ready(displayTableReload(' '));
</script>