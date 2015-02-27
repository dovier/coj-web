<%-- 
    Document   : subaftercontest
    Created on : mar 19, 2014, 2:31:09 p.m.
    Author     : Z
--%>


<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer = "32kb" autoFlush="true" %>


<h2 class="cojstats">COJ Statistics    
</h2>

<div class="body">
			
                        <p>
				<label id="output">
					Select.</label></p>
                                        <div class="selector">
				<select id="states" onchange="$('#output').html('Filter by ' + this.value + '!')">
					
					<option value="SubmissionJudge date">Date</option>
					<option value="Nationality">Nationality</option>
					<option value="Institution">Institution</option>
											
				</select>
			</div>
    <div class="date1">
       <input type="text" id="startDate" name="startDate" />
          </div>
                                        
    <div class="date2">           
        <input type="text" id="endDate" name="endDate" />
           </div>

                        					
                        </div>




<button id="wijmo-button">
                        Submit</button>
          


    <script  type="text/javascript" src="<c:url value="/js/jquery" />"></script>
    
    <link type="text/css" href="<c:url value="/css/statistics.css"/>" rel="stylesheet" />
    <link type="text/css" href="<c:url value="/js/themes/rocket/jquery-wijmo.css"/>" rel="stylesheet" />
       
    <link type="text/css" href="<c:url value="/js/themes/wijmo/jquery.wijmo.wijcombobox.css"/>" rel="stylesheet" />
    <link type="text/css" href="<c:url value="/js/themes/wijmo/jquery.wijmo.wijinput.css"/>" rel="stylesheet" />
    <link type="text/css" href="<c:url value="/js/themes/wijmo/jquery.wijmo-open.1.5.0.css"/>" rel="stylesheet" />
    <link type="text/css" href="<c:url value="/js/themes/wijmo/jquery.wijmo.wijpager.css"/>" rel="stylesheet" />
    <link type="text/css" href="<c:url value="/js/themes/wijmo/jquery.wijmo.wijgrid.css"/>" rel="stylesheet" />

    <script  type="text/javascript" src="<c:url value="/js/external/jquery-1.6.2.min.js"/>"></script>
    <script  type="text/javascript" src="<c:url value="/js/external/jquery-ui-1.8.16.custom.min.js"/>"></script>
    <script  type="text/javascript" src="<c:url value="/js/external/globalize.min.js"/>"></script>
    <script  type="text/javascript" src="<c:url value="/js/external/jquery.mousewheel.min.js"/>"></script>
    <script  type="text/javascript" src="<c:url value="/js/external/jquery.wijmo-open.1.5.0.min.js"/>"></script>
    <script  type="text/javascript" src="<c:url value="/js/external/raphael.js"/>"></script>
    
    <script  type="text/javascript" src="<c:url value="/js/wijmo/jquery.wijmo.wijinputcore.js"/>"></script>
    <script  type="text/javascript" src="<c:url value="/js/wijmo/jquery.wijmo.wijinputdate.js"/>"></script>
    <script  type="text/javascript" src="<c:url value="/js/wijmo/jquery.plugin.wijtextselection.js"/>"></script>
    <script  type="text/javascript" src="<c:url value="/js/wijmo/jquery.wijmo.wijdatasource.js"/>"></script>
    <script  type="text/javascript" src="<c:url value="/js/wijmo/jquery.wijmo.wijpager.js"/>"></script>
    <script  type="text/javascript" src="<c:url value="/js/wijmo/jquery.wijmo.wijdatasource.js"/>"></script>
    <script  type="text/javascript" src="<c:url value="/js/wijmo/jquery.wijmo.wijgrid.js"/>"></script>
    <script  type="text/javascript" src="<c:url value="/js/wijmo/jquery.wijmo.wijcombobox.js"/>"></script>
    <script  type="text/javascript" src="<c:url value="/js/wijmo/jquery.wijmo.wijchartcore.js"/>"></script>
    <script  type="text/javascript" src="<c:url value="/js/wijmo/jquery.wijmo.wijlinechart.js"/>"></script>
   

 <script id="scriptInit" type="text/javascript">
     
     
     $(document).ready(callback);
     //submit
      $(document).ready(function () {
            $("#wijmo-button").button();
            $("#wijmo-button").click(callback);
        });
     //combobox
     $(document).ready(function () {
			$("#tags").wijcombobox();
			$("#states").wijcombobox();
		});
                
                
        //input date
         $(function () {
            $("#startDate").wijinputdate(
        {
            showTrigger: true
        });
        });
        
        $(function () {
            $("#endDate").wijinputdate(
        {
            showTrigger: true
        });
        });
                
       //LineChart  
       function callback(){
           var init=$("#startDate").val();
           var end=$("#endDate").val();
           
    $.ajax({
            url:"/json/xtats/distsubmissions.json",
            type: 'GET',
            data:{"startDate":init,"endDate":end },
            dataType: 'json',
            success:
        });
        
        
            
        }
         
        </script>