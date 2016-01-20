<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer="16kb" autoFlush="true"%>

<h2 class="postheader"><spring:message code="link.downloads"/></h2>
<div id="downloadscontainer" class="postcontent row">
	
</div>
<script>


function downloadsPage(page) {
	$.ajax({
		type : "POST",
		url : "/general/pagedownloads.xhtml",
		data : "page=" + page,
		dataType : 'html',
		success : function(data) {
			$("#downloadscontainer").html(data);
		}
	});
}

$(function() {
	downloadsPage(1);
});
</script>