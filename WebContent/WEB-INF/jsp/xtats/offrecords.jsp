<%-- 
    Document   : offrecords
    Created on : mar 6, 2014, 8:57:16 a.m.
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




<div class="postcontent">
   <table id="demo">
            </table>
</div>  

<button id="wijmo-button">
                        Submit</button>

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
    <script  type="text/javascript" src="<c:url value="/js/Statistics.js"/>"></script>
    
    <script  type="text/javascript" src="<c:url value="/js/wijmo/jquery.wijmo.wijinputcore.js"/>"></script>
    <script  type="text/javascript" src="<c:url value="/js/wijmo/jquery.wijmo.wijinputdate.js"/>"></script>
    <script  type="text/javascript" src="<c:url value="/js/wijmo/jquery.plugin.wijtextselection.js"/>"></script>
    <script  type="text/javascript" src="<c:url value="/js/wijmo/jquery.wijmo.wijdatasource.js"/>"></script>
    <script  type="text/javascript" src="<c:url value="/js/wijmo/jquery.wijmo.wijpager.js"/>"></script>
    <script  type="text/javascript" src="<c:url value="/js/wijmo/jquery.wijmo.wijdatasource.js"/>"></script>
    <script  type="text/javascript" src="<c:url value="/js/wijmo/jquery.wijmo.wijgrid.js"/>"></script>
    <script  type="text/javascript" src="<c:url value="/js/wijmo/jquery.wijmo.wijcombobox.js"/>"></script>

    
    <link type="text/css" href="<c:url value="/js/themes/wijmo/jquery.wijmo.wijtextbox.css"/>" rel="stylesheet" />
    <script  type="text/javascript" src="<c:url value="/js/external/jquery.bgiframe-2.1.3-pre.js"/>"></script>
    <script  type="text/javascript" src="<c:url value="/js/wijmo/jquery.wijmo.wijutil.js"/>"></script>
    <script  type="text/javascript" src="<c:url value="/js/wijmo/jquery.wijmo.wijtextbox.js"/>"></script>
    
    
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
        
        //table
        function callback(){            
           var init=$("#startDate").val();
           var end=$("#endDate").val();
           var nat=$('#textbox').val();
           var st=$('#states').val();
           
           
                      
            $.ajax({
            url:"/json/xtats/offrecords.json",
            type: 'GET',
            data:{"startDate":init,"endDate":end, "textbox":nat, "states":st},
            dataType: 'json',
            success:function(datos){return DrawTableOffRecords(datos);}         
      
        });
        }       
       
       
       function DrawTableOffRecords(x){
                 $("#demo").wijgrid({
                allowSorting: true,
                allowPaging: true,
                pageSize: 10,
                data: x,
                columns: [
                    { headerText: "Name" }, { headerText: "RgDate" }, { headerText: "Nationality" },{headerText: "Institution"}]
            });
            
           
       }
        </script>