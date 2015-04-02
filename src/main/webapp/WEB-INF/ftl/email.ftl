<!DOCTYPE html>
<html  lang="en"> 
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<head>
		<title>Caribbean Online Judge</title>
		<#include "/base/css.ftl">
	</head>

	<body>
		<div class="content">
			<#include "/base/header.ftl">
						
			<div class="inner-container">		
				<p>Dear user.</p>	
				
				<#if nearContests?exists>
					<div class="container">
						<p>We are glad to invite you to these upcoming contest(s):</p>
						<table border="0">
						    <thead>
						    	<th width="500px">name</th>
							    <th width="250px">startdate</th>
							    <th width="250px">enddate</th>
						    </thead>		    
						    <tbody>
						    	<#list nearContests as contest>
						    		<tr>						    		
						    			<td> <a href="${contest.url}"> ${contest.name} </a> </td>
						    			<td> <a href="${contest.startTimeAndDateUrl}${mapsites[contest.sid?string].timeanddateid}">${contest.startDate?string["yyyy-MM-dd hh:mm:ss"]}</a></td>
						    			<td> <a href="${contest.endTimeAndDateUrl}${mapsites[contest.sid?string].timeanddateid}">${contest.endDate?string["yyyy-MM-dd hh:mm:ss"]}</a></td>
						    		</tr>
						    	</#list>		    
						    </tbody>		    
						</table>
					</div>
				</#if> 
				
				
				<#if newContestsSites?exists>
					<div class="container">           
					 	<p> The next contest(s) have been created:</p>		
					 	<#list newContestsSites as site>	 	
					   		<a href="${site.url}" class="sitename">
					   			<h4>${site.site}</h4>
					   		</a>		
						  	<table border="0">
							    <thead>
							       <th width="500px">name</th>
							       <th width="250px">startdate</th>
							       <th width="250px">enddate</th>
							    </thead>		    
							    <tbody>
							    	<#list site.contests as contest>
							    		<tr>
							    			<td> <a href="${contest.url}"> ${contest.name} </a> </td>
							    			<td> <a href="${contest.startTimeAndDateUrl}${site.timeanddateid}">${contest.startDate?string["yyyy-MM-dd hh:mm:ss"]}</a></td>
							    			<td> <a href="${contest.endTimeAndDateUrl}${site.timeanddateid}">${contest.endDate?string["yyyy-MM-dd hh:mm:ss"]}</a></td>
							    		</tr>
							    	</#list>		    
							    </tbody>		    
						  </table>
					  	</#list>	  
				  	</div>
				  </#if>
				  
				  
				  <#if changedSites?exists>
				  	<div class="container">
					  <p> The next contest(s) have suffered schedule changes:</p>
					  <#list changedSites as site>
						  <a href="${site.url}">
						  	<h4>${site.site}</h4>
						  </a>		
						  <table border="0">
						    <thead>
							       <th width="500px">name</th>
							       <th width="250px">startdate</th>
							       <th width="250px">enddate</th>
						    </thead>		    
						    <tbody>
						    	<#list site.contests as contest>
						    		<tr>
						    			<td> <a href="${contest.url}"> ${contest.name} </a> </td>
						    			<td> <a href="${contest.startTimeAndDateUrl}${site.timeanddateid}">${contest.startDate?string["yyyy-MM-dd hh:mm:ss"]}</a></td>
							    		<td> <a href="${contest.endTimeAndDateUrl}${site.timeanddateid}">${contest.endDate?string["yyyy-MM-dd hh:mm:ss"]}</a></td>
						    		</tr>
						    	</#list>		    
						    </tbody>		    
						  </table>
					  </#list>
					</div>
				  </#if>
				  
				  <br/><br/><br/>Best regards.
			</div> 
			
			<#include "/base/footer.ftl">
		</div>
	</body>
</html>