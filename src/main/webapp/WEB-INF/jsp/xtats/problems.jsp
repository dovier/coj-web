<%-- 
    Document   : distproblems
    Created on : mar 6, 2014, 9:00:05 a.m.
    Author     : Z
--%>

<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer = "32kb" autoFlush="true" %>


<h2 class="cojstats">COJ Statistics    
</h2>

<div class="main demo">
			<!-- Begin demo markup -->
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
<!--<div id="totext">           <h3>
                        Input
                    </h3>
                    <input id="textbox" type="text" />
</div>-->
<div class="main demo">
    <input id="textbox" style="width: 300px"/>  
        </div> 


        <div class="main demo">
           <input type="text" id="startDate" />          
           
        </div>        
    


        <div class="main demo">
            <input type="text" id="endDate" />

        </div>        




<button id="wijmo-button">Submit</button>
          



 

          

<div id="wijlinechartDefault" style="width: 756px; height: 475px" class="ui-widget ui-widget-content ui-corner-all">
            </div>

    <script  type="text/javascript" src="<c:url value="/js/jquery" />"></script>
    
    <link type="text/css" href="<c:url value="/css/statistics.css"/>" rel="stylesheet" />
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
   

 <script id="scriptInit" type="text/javascript">
     
       
     //submit
      $(document).ready(function () {
            $('#textbox').hide();
            $("#wijmo-button").button();            
            $("#wijmo-button").bind('click', callback);
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
    
                
       //LineChart    
       function callback(){
           var init=$("#startDate").val();
           var end=$("#endDate").val();
           var nat=$('#textbox').val();
           var st=$('#states').val();
    $.ajax({
            url:"/json/xtats/newproblems.json",
            type: 'GET',
            data:{"startDate":init,"endDate":end, "textbox":nat, "states":st},
            dataType: 'json',
            success:function(datos){return DrawGraphicNewProblems(datos);}
            
      
        });
    }
        
        function DrawGraphicNewProblems(x){
                
                var nor=[];
                var date=[];
                for (var i = 0; i < x.length; i++) {
                    
                nor.push(parseInt(x[i][0]));
                date.push(x[i][1]);
            }
            
            $("#wijlinechartDefault").wijlinechart({
    			header: {
    				visible: true,
    				text: "Concurrency of New Users",
    				textStyle: {
    					fill: "#fafafa"
    				}
    			},
    			axis: {
    				y: {
    					labels: {
    						style: {
    							fill: "#7f7f7f",
    							"font-size": "11pt"
    						}
    					},
    					gridMajor: {
    						style: { stroke: "#353539", "stroke-dasharray": "- " }
    					},
    					tickMajor: { position: "outside", style: { stroke: "#7f7f7f"} },
    					tickMinor: { position: "outside", style: { stroke: "#7f7f7f"} }
    				},
    				x: {
    					labels: {
    						style: {
    							fill: "#7f7f7f",
    							"font-size": "11pt",
    							rotation: -45
    						}
    					},
    					tickMajor: { position: "outside", style: { stroke: "#7f7f7f"} }
    				}
    			},
    			showChartLabels: false,
    			hint: {
    				content: function () {
    					return this.data.lineSeries.label + '\n' +
                        this.x + '\n' + this.y + '';
    				},
    				contentStyle: {
    					"font-size": "10pt"
    				},
    				offsetY: -10
    			},
    			legend: {
    				visible: false
    			},
    			seriesList: [
                    {
                    	label: "Users",
                    	legendEntry: true,
                    	fitType: "spline",
                    	data: {
                    		x: date, 
                    		y: nor
                    	},
                    	markers: {
                    		visible: true,
                    		type: "circle"
                    	}
                    }
                ],
    			seriesStyles: [
                    { stroke: "#ff9900", "stroke-width": "3" }
                ],
    			seriesHoverStyles: [
                    { "stroke-width": "4" }
                ]
    		});
            
            
        }
         
        </script>
                