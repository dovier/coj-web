<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer="16kb" autoFlush="true"%>

<h2 class="postheader">Entries</h2>
<div class="row postcontent">
	<div class="col-xs-12">
		<div id="display-table-container"
			data-reload-url="/user/${username}/tables/listentries.xhtml"></div>
	</div>
</div>
<script>
	$(document).ready(displayTableReload(" "));
</script>