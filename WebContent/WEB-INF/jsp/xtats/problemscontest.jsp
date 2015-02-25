<%-- 
    Document   : problemscontest
    Created on : mar 19, 2014, 2:49:39 p.m.
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

<div class="main demo">
    <input id="textbox" style="width: 300px"/>  
        </div> 

<div class="main demo">
    <input id="textbox1" style="width: 100px"/>  
        </div>
<div class="main demo">
    <input id="textbox2" style="width: 100px"/>
    <button id="wijmo-button1">
                        problems</button>
        </div>

                
<div>
                            <select id="veredict" onchange="$('#output').html('Contest time: ' + this.value + '!')">
                                        <option value="All Contest">All Contest</option>
                                        <option value="First Hour">First Hour</option>					
					<option value="Last Hour">Last Hour</option>
											
				</select>
    
    <select id="status" onchange="$('#output').html('Filter by ' + this.value + '!')">
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
            $("#wijmo-button1").button();
            $("#wijmo-button").bind('click', callback);
            $("#wijmo-button1").bind('click', generateProblems);
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
        
        
        function generateProblems(){
            var cid=$('#textbox1').val();
                  $.ajax({
            url:"/json/xtats/problemscontestfilt.json",
            type: 'GET',
            data:{"textbox1":cid},
            dataType: 'json',
            success:function(datos){
                var nor = new Array();
                for (var i = 0; i < datos.length; i++) {                    
                    nor.push({label: datos[i][0], value: datos[i][1]});
                
                }    
            
            $("#textbox2").wijcombobox({data:nor});            
            }
            });
        }
        //table
        function callback(){            
            var nat=$('#textbox').val();
           var st=$('#states').val();
           var cid=$('#textbox1').val();
           var pid = parseInt($('#textbox2').val());
           var ver=$('#veredict').val();
            $.ajax({
            url:"/json/xtats/problemscontest.json",
            type: 'GET',
            data:{"textbox1":cid, "textbox2":pid, "veredict":ver, "textbox":nat, "states":st},
            dataType: 'json',
            success:function(datos){return DrawGraphicVeredictsContesttl(datos);}
                  
        });
        }       
            
      function DrawGraphicVeredictsContesttl(x){
          alert(x);
                var count=new Array(); var date=new Array();
                alert($('#status').val());
                if($('#status').val()==="ac"){
                     for (var i = 0; i < x.length; i++) { 
                        if(x[i][0]==="Accepted"){
                        
                            count.push(parseInt(x[i][1]));
                            date.push(x[i][2]);
                            }
                        }   
                    }
                    else if($('#status').val()==="wa"){
                        
                        for (var i = 0; i < x.length; i++) { 
                        if(x[i][0]==="Wrong Answer"){
                        
                            count.push(parseInt(x[i][1]));
                            date.push(x[i][2]);
                            }
                        }
                    }
                    else if($('#status').val()==="ce"){
                        for (var i = 0; i < x.length; i++) { 
                        if(x[i][0]==="Compilation Error"){
                        
                            count.push(parseInt(x[i][1]));
                            date.push(x[i][2]);
                            }
                        }
                    }
                    else if($('#status').val()==="pe"){
                        for (var i = 0; i < x.length; i++) { 
                        if(x[i][0]==="Presentation Error"){
                        
                            count.push(parseInt(x[i][1]));
                            date.push(x[i][2]);
                            }
                        }
                    }
                    else if($('#status').val()==="tle"){
                        for (var i = 0; i < x.length; i++) { 
                        if(x[i][0]==="Time Limit Exceeded"){
                        
                            count.push(parseInt(x[i][1]));
                            date.push(x[i][2]);
                            }
                        }
                    }
                    else if($('#status').val()==="ole"){
                        for (var i = 0; i < x.length; i++) { 
                        if(x[i][0]==="Output Limit Exceeded"){
                        
                            count.push(parseInt(x[i][1]));
                            date.push(x[i][2]);
                            }
                        }
                    }
                    else if($('#status').val()==="rte"){
                        for (var i = 0; i < x.length; i++) { 
                        if(x[i][0]==="RunTime Limit Exceeded"){
                        
                            count.push(parseInt(x[i][1]));
                            date.push(x[i][2]);
                            }
                        }
                    }
                    else if($('#status').val()==="mle"){
                        for (var i = 0; i < x.length; i++) { 
                        if(x[i][0]==="Memory Limit Exceeded"){
                        
                            count.push(parseInt(x[i][1]));
                            date.push(x[i][2]);
                            }
                        }
                    }
                    else{alert("none");}
                           
                alert(count);
                
                        
                	$("#wijdatelinechart").wijlinechart({
				showChartLabels: false,
				hint: {
					content: function () {
						return this.y;
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
						legendEntry: false,
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
				seriesStyles: [{
					stroke: "#afe500", "stroke-width": 5, opacity: 0.8
				}],
				seriesHoverStyles: [{
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