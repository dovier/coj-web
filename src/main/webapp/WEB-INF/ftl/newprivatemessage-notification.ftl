<!DOCTYPE html>
<html  lang="en"> 
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<head>
		<title>Caribbean Online Judge</title>
		<#include "/base/css.ftl">
	</head>

	<body>
		<#setting number_format="#########">
		<div class="content">
			<#include "/base/header.ftl">
						
			<div class="inner-container">		
				Hello ${username},<br/><br/>
				
				You have received a new private message from ${mail.id_from} to your account on the COJ system with the following subject:<br/>
				${mail.title}<br/><br/>
				
				You can view your new message by clicking on the following link:<br/>
				<a href="http://coj.uci.cu/mail/mailview.xhtml?idmail=${mail.idmail}">http://coj.uci.cu/mail/mailview.xhtml?idmail=${mail.idmail}</a><br/><br/>

				You have requested that you be notified on this event, remember that you can always choose not to be notified of new messages by changing the
				appropriate setting in your profile.<br/>														
			</div>
						
			<#include "/base/footer.ftl">
		</div>
	</body>
</html>