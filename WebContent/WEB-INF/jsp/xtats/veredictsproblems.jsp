<%-- 
    Document   : veredictsproblems
    Created on : mar 19, 2014, 2:16:34 p.m.
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
                                        <option value="ac">ac</option>					
					<option value="wa">wa</option>
					<option value="ce">ce</option>
                                        <option value="pe">pe</option>
                                        <option value="tle">tle</option>					
					<option value="ole">ole</option>
					<option value="rte">rte</option>
					<option value="mle">mle</option>						
				</select>
			</div>
                        </div>



<div class="main demo">
    <input id="textbox1" style="width: 100px"/>  
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
<div id="wijlinechartDefault" style="width: 756px; height: 475px" class="ui-widget ui-widget-content ui-corner-all">
            </div>

    <script  type="text/javascript" src="<c:url value="/js/jquery" />"></script>

    <link type="text/css" href="<c:url value="/js/themes/cobalt/jquery-wijmo.css"/>" rel="stylesheet" />
    
    <link type="text/css" href="<c:url value="/js/themes/wijmo/jquery.wijmo.wijcombobox.css"/>" rel="stylesheet" />
    <link type="text/css" href="<c:url value="/js/themes/wijmo/jquery.wijmo.wijinput.css"/>" rel="stylesheet" />
    <link type="text/css" href="<c:url value="/js/themes/wijmo/jquery.wijmo-open.1.5.0.css"/>" rel="stylesheet" />
    <link type="text/css" href="<c:url value="/js/themes/wijmo/jquery.wijmo.wijpager.css"/>" rel="stylesheet" />
    <link type="text/css" href="<c:url value="/js/themes/wijmo/jquery.wijmo.wijgrid.css"/>" rel="stylesheet" />

    <script  type="text/javascript" src="<c:url value="/js/Statistics.js"/>"></script>
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
    
        //submit
      $(document).ready(function () {            
            $("#wijmo-button").button();            
            $("#wijmo-button").bind('click', callback);
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
        
        //table
        function callback(){            
          var init=$("#startDate").val();
          var end=$("#endDate").val();          
          var pid1=$('#textbox1').val();
          var pref = ValidateDate(init,end);
          alert(pref);
            $.ajax({
            url:"/json/xtats/veredictsproblemstl.json",
            type: 'GET',
            data:{"textbox1":pid1, "startDate":init,"endDate":end, "prefix":pref},
            dataType: 'json',
            success:function(datos){return DrawGraphicVeredictsProblemstl(datos);}
                  
        });
        }       
            
      function DrawGraphicVeredictsProblemstl(x){
                alert(x);
                var count=new Array(); var date=new Array();
                alert($('#states').val());
                if($('#states').val()==="ac"){
                     for (var i = 0; i < x.length; i++) { 
                        if(x[i][0]==="Accepted"){
                            alert(x[i]);
                            count.push(parseInt(x[i][1]));
                            date.push(x[i][2]);
                            }
                        }   
                    }
                    else if($('#states').val()==="wa"){
                        
                        for (var i = 0; i < x.length; i++) { 
                        if(x[i][0]==="Wrong Answer"){
                        
                            count.push(parseInt(x[i][1]));
                            date.push(x[i][2]);
                            }
                        }
                    }
                    else if($('#states').val()==="ce"){
                        for (var i = 0; i < x.length; i++) { 
                        if(x[i][0]==="Compilation Error"){
                        
                            count.push(parseInt(x[i][1]));
                            date.push(x[i][2]);
                            }
                        }
                    }
                    else if($('#states').val()==="pe"){
                        for (var i = 0; i < x.length; i++) { 
                        if(x[i][0]==="Presentation Error"){
                        
                            count.push(parseInt(x[i][1]));
                            date.push(x[i][2]);
                            }
                        }
                    }
                    else if($('#states').val()==="tle"){
                        for (var i = 0; i < x.length; i++) { 
                        if(x[i][0]==="Time Limit Exceeded"){
                        
                            count.push(parseInt(x[i][1]));
                            date.push(x[i][2]);
                            }
                        }
                    }
                    else if($('#states').val()==="ole"){
                        for (var i = 0; i < x.length; i++) { 
                        if(x[i][0]==="Output Limit Exceeded"){
                        
                            count.push(parseInt(x[i][1]));
                            date.push(x[i][2]);
                            }
                        }
                    }
                    else if($('#states').val()==="rte"){
                        for (var i = 0; i < x.length; i++) { 
                        if(x[i][0]==="RunTime Limit Exceeded"){
                        
                            count.push(parseInt(x[i][1]));
                            date.push(x[i][2]);
                            }
                        }
                    }
                    else if($('#states').val()==="mle"){
                        for (var i = 0; i < x.length; i++) { 
                        if(x[i][0]==="Memory Limit Exceeded"){
                        
                            count.push(parseInt(x[i][1]));
                            date.push(x[i][2]);
                            }
                        }
                    }
                    else{alert("none");}
                           
                alert(count);
                alert(date);
                	$("#wijlinechartDefault").wijlinechart({
    			header: {
    				visible: true,
    				text: "Problem Submitions LineChart",
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
                    	label: "Submitions",
                    	legendEntry: true,
                    	fitType: "spline",
                    	data: {
                    		x: date, 
                    		y: count
                    	},
                    	markers: {
                    		visible: true,
                    		type: "circle"
                    	}
                    }
                ],
    			seriesStyles: [
                    { stroke: "#1E395B", "stroke-width": "3" }
                ],
    			seriesHoverStyles: [
                    { "stroke-width": "4" }
                ]
    		});

      }      
        </script>