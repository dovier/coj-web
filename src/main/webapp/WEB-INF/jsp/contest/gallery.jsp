<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<link rel="stylesheet"
	href="<c:url value="/css/blueimp-gallery.min.css"/>">
<script type='text/javascript' src="<c:url value="/js/jquery.js" />"></script>

<h2 class="postheader">
	<a class="linkheader"
		href="<c:url value="contestview.xhtml?cid=${contest.cid}"/>" />${contest.name}</a>
</h2>


<div class="postcontent">
	<div id="links" class="hidden">
		<c:forEach items="${images}" var="image">
			<a href="/images/${contest.cid}/${image}" title="${image}"> <img
				src="/images/${contest.cid}/${image}">
			</a>
		</c:forEach>
	</div>
	<div id="blueimp-gallery"
		class="blueimp-gallery blueimp-gallery-controls">
		<div class="slides"></div>
		<h3 class="title"></h3>
		<a class="prev">‹</a> <a class="next">›</a> <a class="close">×</a> <a
			class="play-pause"></a>
		<ol class="indicator"></ol>
	</div>
</div>
<div id="blueimp-gallery-carousel"
	class="blueimp-gallery blueimp-gallery-carousel">
	<div class="slides"></div>
	<h3 class="title"></h3>
	<a class="prev">‹</a> <a class="next">›</a> <a class="play-pause"></a>
	<ol class="indicator"></ol>
</div>
<script src="<c:url value="/js/blueimp-gallery.min.js"/>"></script>
<script>
	blueimp.Gallery(document.getElementById('links').getElementsByTagName('a'),
			{
				container : '#blueimp-gallery-carousel',
				carousel : true
			});
</script>