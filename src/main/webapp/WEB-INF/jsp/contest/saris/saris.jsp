<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer="16kb" autoFlush="true"%>
<link href="<c:url value="/css/saris/styles/style.css" />"
	type="text/css" rel="stylesheet" media="screen">

<script type="text/javascript"
	src="<c:url value="/js/saris/jquery.min.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/js/saris/jquery.localscroll-1.2.7-min.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/js/saris/jquery.scrollTo-1.4.2-min.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/js/saris/jquery.easing.1.3.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/js/saris/jquery.animate-colors.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/js/saris/jquery.xml2json.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/js/saris/ejudge.convertor.js" />"></script>
<script type="text/javascript" src="<c:url value="/js/saris/main.js" />"></script>

<h2 class="postheader">
	${contest.name}
</h2>
<div class="margin-top-05 postcontent">
	<div id="about">
		<a target="_blank" href="https://codeforces.com/blog/entry/7914">about
			Saris</a> <br /> <a href="#" class="instructions-link">Instructions</a>
	</div>
	<div style="clear: both"></div>
	<div id="standings-table" style="overflow: hidden;"></div>
	<div id="instructions">
		<ul>
			<li>Press 'N' to unfreeze last pending submission</li>
			<li>Press 'F' to unfreeze current contestant</li>
			<li>Press 'B' to go back in ranking</li>
		</ul>
	</div>
</div>
<div>
	<script type="text/javascript">
	 load(${contestLog});
	 
	 $(document).ready(function(){
		    $(".instructions-link").hover(
		        function(){
		            var id = $(this).data("id");
		            $("#instructions").show();
		            
		        },function(){
		            $("#instructions").hide();
		            
		        });
		});
	 </script>
</div>