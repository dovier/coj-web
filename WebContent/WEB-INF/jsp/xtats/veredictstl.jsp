<%-- 
    Document   : veredictstl
    Created on : mar 15, 2014, 10:46:19 a.m.
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
<!--<div id="main demo">        
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




</div>  

<button id="wijmo-button">
                        Submit</button>

<div id="wijdatelinechart" class="ui-widget ui-widget-content ui-corner-all" style="width: 756px; height: 475px">
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
    	
	
 <script id="scriptInit" type="text/javascript">
    
        //submit
      $(document).ready(function () {
            $('#textbox').hide();
            $("#wijmo-button").button();            
            $("#wijmo-button").bind('click', callback);  // .click(callback);
            $("#states").wijcombobox();
            $("#states").change(evaluate);
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
           var nat=$('#textbox').val();
           var st=$('#states').val();
           
           
                      
            $.ajax({
            url:"/json/xtats/veredictstl.json",
            type: 'GET',
            data:{"startDate":init,"endDate":end, "textbox":nat, "states":st},
            dataType: 'json',
            success:function(datos){return DrawGraphicVeredictstl(datos);}
                  
        });
        }       
            
      function DrawGraphicVeredictstl(x){
                var accCount=new Array(); var accDate=new Array();
                var waCount=new Array(); var waDate=new Array();
                var ceCount=new Array(); var ceDate=new Array();
                var peCount=new Array(); var peDate=new Array();
                var tleCount=new Array(); var tleDate=new Array();
                var oleCount=new Array(); var oleDate=new Array();
                var reCount=new Array(); var reDate=new Array();
                var mleCount=new Array(); var mleDate=new Array();
                alert(x);
                for (var i = 0; i < x.length; i++) { 
                    if(x[i][0]==="Accepted"){
                        
                        accCount.push(parseInt(x[i][1]));
                        accDate.push(x[i][2]);
                    }
                    else if(x[i][0]==="Wrong Answer"){
                        
                        waCount.push(parseInt(x[i][1]));
                        waDate.push(x[i][2]);
                    }
                    else if(x[i][0]==="Compilation Error"){
                        ceCount.push(parseInt(x[i][1]));
                        ceDate.push(x[i][2]);
                    }
                    else if(x[i][0]==="Presentation Error"){
                        peCount.push(parseInt(x[i][1]));
                        peDate.push(x[i][2]);
                    }
                    else if(x[i][0]==="Time Limit Exceeded"){
                        tleCount.push(parseInt(x[i][1]));
                        tleDate.push(x[i][2]);
                    }
                    else if(x[i][0]==="Output Limit Exceeded"){
                        oleCount.push(parseInt(x[i][1]));
                        oleDate.push(x[i][2]);
                    }
                    else if(x[i][0]==="Runtime Error"){
                        reCount.push(parseInt(x[i][1]));
                        reDate.push(x[i][2]);
                    }
                    else if(x[i][0]==="Memory Limit Exceeded"){
                        mleCount.push(parseInt(x[i][1]));
                        mleDate.push(x[i][2]);
                    }
                    else{alert("none");}
                }
                
                	$("#wijdatelinechart").wijlinechart({
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
				header: {
					text: "Time Line by COJ Veredicts"
				},
				seriesList: [
					{
						label: "Accepted",
						legendEntry: true,
						data: {
							x: accDate,
							y: accCount
						},
						markers: {
							visible: true,
							type: "circle"
						}
					},
					/*{
						label: "Wrong Answer",
						legendEntry: true,
						data: {
							x: waDate,
							y: waCount
						},
						markers: {
							visible: true,
							type: "circle"
						}
					},
					{
						label: "Compilation Error",
						legendEntry: true,
						data: {
							x: ceDate,
							y: ceCount
						},
						markers: {
							visible: true,
							type: "circle"
						}
					},
                                        {
						label: "Presentation Error",
						legendEntry: true,
						data: {
							x: peDate,
							y: peCount
						},
						markers: {
							visible: true,
							type: "circle"
						}
					},
                                        {
						label: "Time Limit Exceeded",
						legendEntry: true,
						data: {
							x: tleDate,
							y: tleCount
						},
						markers: {
							visible: true,
							type: "circle"
						}
					},
                                        {
						label: "Output Limit Exceeded",
						legendEntry: true,
						data: {
							x: oleDate,
							y: oleCount
						},
						markers: {
							visible: true,
							type: "circle"
						}
					},{
						label: "Runtime Error",
						legendEntry: true,
						data: {
							x: reDate,
							y: reCount
						},
						markers: {
							visible: true,
							type: "circle"
						}
					},
                                        {
						label: "Memory Limit Exceeded",
						legendEntry: true,
						data: {
							x: mleDate,
							y: mleCount
						},
						markers: {
							visible: true,
							type: "circle"
						}
					}*/
				],
				seriesStyles: [{
					stroke: "#afe500", "stroke-width": 5, opacity: 0.8
				}, {
					stroke: "#959595", "stroke-width": 5, opacity: 0.8
				}, {
					stroke: "#2d2d2d", "stroke-width": 5, opacity: 0.8
				},{
					stroke: "#ff2d95", "stroke-width": 5, opacity: 0.8
				},{
					stroke: "#a16d85", "stroke-width": 5, opacity: 0.8
				},{
					stroke: "#af9d15", "stroke-width": 5, opacity: 0.8
				},{
					stroke: "#562f85", "stroke-width": 5, opacity: 0.8
				},{
					stroke: "#112a91", "stroke-width": 5, opacity: 0.8
				}],
				seriesHoverStyles: [{
					"stroke-width": 8, opacity: 1
				}, {
					"stroke-width": 8, opacity: 1
				}, {
					"stroke-width": 8, opacity: 1
				},{
					"stroke-width": 8, opacity: 1
				},{
					"stroke-width": 8, opacity: 1
				},{
					"stroke-width": 8, opacity: 1
				},{
					"stroke-width": 8, opacity: 1
				},{
					"stroke-width": 8, opacity: 1
				}]
			});

			var resizeTimer = null;

			$(window).resize(function () { 
				window.clearTimeout(resizeTimer);
				resizeTimer = window.setTimeout(function () {
					var jqLine = $("#wijdatelinechart"),
						width = jqLine.width(),
						height = jqLine.height();

					if (!width || !height) {
						window.clearTimeout(resizeTimer);
						return;
					}

					jqLine.wijlinechart("redraw", width, height);
				}, 250);
			});
		
            
          
      }      
        </script>