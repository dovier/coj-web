<%-- 
    Document   : compareproblems
    Created on : mar 19, 2014, 2:06:52 p.m.
    Author     : Z
--%>

<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer = "32kb" autoFlush="true" %>


<h2 class="cojstats">COJ Statistics    
</h2>

<div class="main demo">
			<p>
				<label id="output">
					Please select.</label></p>
			<div>
                            <select id="states" onchange="$('#output').html('Filter by ' + this.value + '!'), $('#textbox').show()">
                                        <option value="non">Please select</option>
                                        <option value="User">User</option>					
					<option value="Nationality">Nationality</option>
					<option value="Institution">Institution</option>
											
				</select>
			</div>
                        </div>

<div class="main demo">
    <input id="textbox" style="width: 300px"/>  
        </div> 

<div class="main demo">
    <input id="textbox1" style="width: 100px"/>  
        </div>
<div class="main demo">
    <input id="textbox2" style="width: 100px"/>  
        </div>

<div class="main demo">
           <input type="text" id="startDate" />          
           
        </div>        
    


        <div class="main demo">
            <input type="text" id="endDate" />

        </div>     

</div>  

<button id="wijmo-button">
                        Submit</button>

<div id="wijbarchart" class="ui-widget ui-widget-content ui-corner-all" style="width: 756px; height: 475px">
			</div>

    <script  type="text/javascript" src="<c:url value="/js/jquery" />"></script>

    <link type="text/css" href="<c:url value="/js/themes/cobalt/jquery-wijmo.css"/>" rel="stylesheet" />
    
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
    <script  type="text/javascript" src="<c:url value="/js/Statistics.js"/>"></script>
    
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
    <script  type="text/javascript" src="<c:url value="/js/wijmo/jquery.wijmo.wijbarchart.js"/>"></script>
       
 <script id="scriptInit" type="text/javascript">
    //submit
      $(document).ready(function () {
            $('#textbox').hide();
            $("#wijmo-button").button();            
            $("#wijmo-button").bind('click', callback);  // .click(callback);
            $("#states").wijcombobox();
            $("#states").change( evaluate);
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
        
        function callback(){          
          var init=$("#startDate").val();
          var end=$("#endDate").val();
          var nat=$('#textbox').val();
          var st=$('#states').val();
          var pid1=$('#textbox1').val();
          var pid2=$('#textbox2').val();
            $.ajax({
            url:"/json/xtats/problemcompare.json",
            type: 'GET',
            data:{"textbox1":pid1, "textbox2":pid2, "startDate":init,"endDate":end, "textbox":nat, "states":st},
            dataType: 'json',
            success:function (datos){return drawBarChartCompareProblems(datos);}
                  
        });
        }
        
        function drawBarChartCompareProblems(x){
                alert(x);
                var axisy1 =new Array();
                var axisy2 =new Array();
                var condition = "_";
                var ar1 = new Array();
                var ar2 = new Array();
                
                for (var i = 0; i < x.length; i++) { 
                    if(x[i][0]=== condition){
                        ar1= x.slice(0, i);
                        ar2=x.slice(i+1, x.length);
                    }
                    
                }
                var aux =new Array();
                for(var j=0;j<ar1.length;j++){
                    aux.push(ar1[j][0]);
                }
                var aux0 =new Array();
                for(var y=0;y<ar2.length;y++){
                    aux0.push(ar2[y][0]);
                }
                
                axisy1[0]="Accepted";
                axisy1[1]="Wrong Answer";
                axisy1[2]="Compilation Error";
                axisy1[3]="Presentation Error";
                axisy1[4]="Time Limit Exceeded";
                axisy1[5]="Output Limit Exceeded";
                axisy1[6]="Runtime Error";
                axisy1[7]="Memory Limit Exceeded";
                var axis1=new Array();
                for(var i=0;i < axisy1.length;i++){
                    if(aux.indexOf(axisy1[i])=== -1){
                        axis1.push( 0);
                    }                        
                    else{
                        axis1.push(ar1[aux.indexOf(axisy1[i])][1]);
                    }
                    
                 }
                 
                 var ax2=new Array();
                 for(var z=0;z < axisy1.length;z++){
                    if(aux0.indexOf(axisy1[z])=== -1){
                        ax2.push( 0);
                    }                        
                    else{
                        ax2.push(ar2[aux0.indexOf(axisy1[z])][1]);
                    }
                    
                 }
                 
                $("#wijbarchart").wijbarchart({
			    horizontal: false,
				axis: {
					y: {
						text: "Total Submitions"
					},
					x: {
						text: ""
					}
				},
				hint: {
					content: function () {
						return this.data.label + '\n ' + this.y + '';
					}
				},
				header: {
					text: "Comparison Chart"
				},
				seriesList: [{
					label: "Problem #1",
					legendEntry: true,
					data: { x: ['Ac', 'WA', 'Ce', 'Pe', 'Tle', 'Ole', 'Rte', 'Mle'], y: [parseInt(axis1[0]), parseInt(axis1[1]), parseInt(axis1[2]), parseInt(axis1[3]), parseInt(axis1[4]), parseInt(axis1[5]), parseInt(axis1[6]), parseInt(axis1[7])] }
				}, {
					label: "Problem #2",
					legendEntry: true,
					data: { x: ['Ac', 'WA', 'Ce', 'Pe', 'Tle', 'Ole', 'Rte', 'Mle'], y: [parseInt(ax2[0]), parseInt(ax2[1]), parseInt(ax2[2]), parseInt(ax2[3]), parseInt(ax2[4]), parseInt(ax2[5]), parseInt(ax2[6]), parseInt(ax2[7])] }
				}]
			});
        	
			
            }
            
        </script>   