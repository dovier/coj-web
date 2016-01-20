<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer="16kb" autoFlush="true"%>

<h2 class="postheader"></h2>
<div id = "followingcontainer" class="row row-centered postcontent">

</div>

<div id = "followerscontainer" class="row row-centered postcontent">
	
</div>
<script>

var username = '${username}';



	function followersPage(page) {
		$.ajax({
			type : "POST",
			url : "/user/" + username + "/pagefollowers.xhtml",
			data : "page=" + page,
			dataType : 'html',
			success : function(data) {
				$("#followerscontainer").html(data);
				$('img.avatar').error(function() {
					$(this).attr('src', '<c:url value="/images/default_avatar.png"/>');
				});
			}
		});
	}

	function followingPage(page) {
		$.ajax({
			type : "POST",
			url : "/user/" + username + "/pagefollowing.xhtml",
			data : "page=" + page,
			dataType : 'html',
			success : function(data) {
				$("#followingcontainer").html(data);
				$('img.avatar').error(function() {
					$(this).attr('src', '<c:url value="/images/default_avatar.png"/>');
				});
			}
		});
	}

	$(function() {
		
		followingPage(1);
		followersPage(1);
		
		$('.fa-chevron-up').click(function() {
			$(this).toggleClass('fa-chevron-up');
			$(this).toggleClass('fa-chevron-down');
		});
		$("[data-toggle='tooltip']").tooltip();
	});

	
</script>