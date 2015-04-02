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
				<p>Dear user, here you can see the details of your SubmissionJudge to the problem ${submission.title}:<p>
				
				<div class="container">
					<table border="0">
					    <thead>
					    	<th width="100px">id</th>
						    <th width="100px">date</th>
						    <th width="150px">user</th>
						    <th width="100px">prob</th>
						    <th width="150px">judgement</th>
						    <th width="100px">time</th>
						    <th width="100px">mem</th>
						    <th width="100px">size</th>
						    <th width="100px">lang</th>
					    </thead>		    
					    <tbody>
				    		<tr>						    		
				    			<td> ${submission.sid}</td>
				    			<td> ${submission.date?string["yyyy-MM-dd hh:mm:ss"]}</td>
				    			<td> ${submission.username}</td>
				    			<td> ${submission.pid}</td>
				    			<td class="sub${submission.statusClass}"> ${submission.status}</td>
				    			<td> ${submission.timeUsed}</td>
				    			<td> ${submission.memoryMB}</td>
				    			<td> ${submission.fontMB}</td>
				    			<td> ${submission.lang}</td>
				    		</tr>	    
					    </tbody>		    
					</table>
				</div>
				
				<#if submission.status = 'Accepted' || submission.status = 'Presentation Error'>
				
					<div class="container">
						<table border="0">
						    <thead>
						    	<th width="200px">tests</th>
							    <th width="200px">total time (ms)</th>
							    <th width="200px">ave time (ms)</th>
							    <th width="200px">min test time (ms)</th>
							    <th width="200px">max test time (ms)</th>
						    </thead>		    
						    <tbody>
					    		<tr>						    		
					    			<td> ${submission.firstWaCase}</td>
					    			<td> ${submission.timeUsed}</td>
					    			<td> ${submission.avgTimeUsed}</td>
					    			<td> ${submission.minTimeUsed}</td>
					    			<td> ${submission.maxTimeUsed}</td>		
					    		</tr>	    
						    </tbody>		    
						</table>
					</div>
				</#if>								
			<div>
			
			<div class="footer">
			    COJ Development Team<br/>
			    If you no longer wish to receive update emails from the Caribbean Online Judge (COJ), please change your account settings in the Edit account page.
			</div>
		</div>
	</body>
</html>