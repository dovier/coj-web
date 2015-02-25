<%-- 
    Document   : distsubmitions
    Created on : mar 6, 2014, 9:02:07 a.m.
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
                                        <option value="All">All</option>
                                        <option value="User">User</option>					
					<option value="Nationality">Nationality</option>
					<option value="Institution">Institution</option>
											
				</select>
			</div>
        

                        </div>

<div class="main demo">
    <input id="textbox" style="width: 300px"/>  
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
            $("#wijmo-button").bind('click', callback);  
            $("#states").wijcombobox();
            $("#states").change( evaluate);
        });
        
        
        
        function callback(){          
           var nat=$('#textbox').val();
           var st=$('#states').val();
                                
            $.ajax({
            url:"/json/xtats/distsubmissions.json",
            type: 'GET',
            data:{"textbox":nat, "states":st},
            dataType: 'json',
            success:function (datos){return drawBarChart(datos);}
                  
        });
        }
        
        function drawBarChart(x){
                var aux0 = parseInt(x[0][0]);
                var aux1 = parseInt(x[0][1]);
                var aux2 = parseInt(x[0][2]);
                var aux3 = parseInt(x[0][3]);
                var aux4 = parseInt(x[0][4]);
                var aux5 = parseInt(x[0][5]);
                var aux6 = parseInt(x[0][6]);
                var aux7 = parseInt(x[0][7]);
                var aux8 = parseInt(x[0][8]);
                var aux9 = parseInt(x[0][9]);
                	$("#wijbarchart").wijbarchart({
				axis: {
					y: {
						text: "Number of Submitions",
						autoMax: true,						
						autoMin: true
						

					},
					x: {
						text: "COJ Veredicts"
					}
				},
				hint: {
					content: function () {
						return this.data.label + '<br/> ' + this.y + '';
					}
				},
				stacked: true,
				clusterRadius: 5,
				seriesList: [{
                    label: "",
                    legendEntry: false,
                    data: { x: ['Ac', 'Ce', 'Fle', 'Ivf', 'Mle', 'Ole', 'Pe', 'Rte', 'Tle', 'Wa'], y: [aux0, aux1, aux2, aux3, aux4, aux5, aux6, aux7, aux8, aux9] }
                }]
		});	
			
            }
            
        </script>