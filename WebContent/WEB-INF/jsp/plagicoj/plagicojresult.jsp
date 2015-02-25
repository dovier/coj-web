<%@page import="org.springframework.web.util.HtmlUtils"%>
<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!-- Styles -->
<link type="text/css" href="<c:url value="/css/plagicoj.css"/>" rel="stylesheet" />

<link type="text/css" href="<c:url value="/css/ui/jquery.ui.all.css"/>" rel="stylesheet" />
<!-- Scripts -->
<script type="text/javascript" src="<c:url value="/js/jquery.js" />"> </script>
<script type="text/javascript" src="<c:url value="/js/plagicoj.js" />"></script>
<script type="text/javascript" src="<c:url value="/js/edit_area/edit_area_full.js" />"></script>

<script type="text/javascript" src="<c:url value="/js/ui/jquery.ui.core.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/js/ui/jquery.ui.widget.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/js/ui/jquery.ui.mouse.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/js/ui/jquery.ui.slider.min.js" />"></script>


<script type="text/javascript">
    $(function() {        
        dibRows("#tbjudgesrevisions");    
        dibRows("#tbPlagiCOJDetectorResults");
    });
    
    var evaluations = new Array("nonPlagiarism","noSurePlagiarism", "MaybePlagiarism","probablyPlagiarism","certainlyPlagiarism");          
    
    function addRevision(){        
        var row = $("#tbjudgesrevisions tr:eq(0)"); 
        var nameuser = "${SPRING_SECURITY_LAST_USERNAME}";
        var date = "<jsp:useBean id="now" class="java.util.Date" /><fmt:formatDate value="${now}" dateStyle="long" />.<fmt:formatDate value="${now}" pattern="HH:mm:ss" type="time" />";
        row.after("<tr><td>"+nameuser+"</td><td>"+date+"</td><td><input type=\"text\" id=\"amount\" style=\"border:0; color:#4C83C3; font-weight:bold;\" /><div id=\"slider\"></div></td><td><input type=\"text\"></td><td><img onclick=\"saveRevision()\" style=\"border: none\" src=\"/images/save.png\" /></td></tr>"); 
        $("#btnAddRevision").hide();
        $("#slider").slider({
            value:99,
            min: 0,
            max: 99,
            step: 1,
            slide: function(event, ui) {
                $("#amount").val(ui.value + "%-"+ evaluations[Math.floor(ui.value/20)]);
            }
        });
            
        $("#amount").val($("#slider").slider("value") + "%-"+ evaluations[Math.floor($("#slider").slider("value")/20)]);
        dibRows("#tbjudgesrevisions");
    }  
    function saveRevision(){
        $.ajax({
            url: "/plagicoj/saverevision.xhtml",
            type: "POST",
            data: {ssid: ${sourcesubmission.sid},
                   didd: ${destinationsubmission.sid},
                   eval: Math.floor($("#slider").slider("value")/20),
                   comment: $("#tbjudgesrevisions input:eq(1)").val()
            },
            dataType: "json"
            }).done(function(msg){          
                if(msg.state == 'success'){
                var idRevision = jQuery(msg).find("#tbjudgesrevisions tbody tr:first").attr("id");
                $("#tbjudgesrevisions tr:eq(1) td:eq(2)").html(evaluations[Math.floor($("#slider").slider("value")/20)]);
                $("#tbjudgesrevisions tr:eq(1) td:eq(3)").html($("#tbjudgesrevisions input:eq(0)").attr("value"));
                $("#tbjudgesrevisions tr:eq(1) td:eq(4)").html("<a onclick=\"deleteRevision("+idRevision+")\"><i class=\"fa fa-trash\"></i></a>");                
                $("#tbjudgesrevisions tr:eq(1)").attr("id",idRevision);
                }else{
                    alert("Error saving Revision");
                }
            }).fail(function(jqXHR, textStatus) {
                alert( "Error saving Revision: " + textStatus );
            });;
       
//        $.ajax({ 
//            type: "POST", 
//            url: "/plagicoj/saverevision.xhtml", 
//            data: "ssid=${sourcesubmission.sid}&didd=${destinationsubmission.sid}&uid=${SPRING_SECURITY_LAST_USERNAME}&eval="+Math.floor($("#slider").slider("value")/20)+"&comment="+$("#tbjudgesrevisions input:eq(1)").attr("value"), 
//            success: function(msg){          
//                if(msg.state == 'success'){
//                var idRevision = jQuery(msg).find("#tbjudgesrevisions tbody tr:first").attr("id");
//                $("#tbjudgesrevisions tr:eq(1) td:eq(2)").html(evaluations[Math.floor($("#slider").slider("value")/20)]);
//                $("#tbjudgesrevisions tr:eq(1) td:eq(3)").html($("#tbjudgesrevisions input:eq(0)").attr("value"));
//                $("#tbjudgesrevisions tr:eq(1) td:eq(4)").html("<a onclick=\"deleteRevision("+idRevision+")\"><i class=\"fa fa-trash\"></i></a>");                
//                $("#tbjudgesrevisions tr:eq(1)").attr("id",idRevision);
//                }else{
//                    alert("Error saving Revision");
//                }
//            }
//        });
        $("#btnAddRevision").show();        
    }  
    
    function deleteRevision(idRevision){     
        $.ajax({ 
            type: "POST", 
            url: "/plagicoj/plagicojresult.xhtml", 
            data: "revid="+idRevision, 
            success: function(msg){                 
                $("#tbjudgesrevisions tr#"+idRevision).remove();              
            }
        });
    }
    
</script>
<h2 class="postheader">
	<fmt:message key="plagicoj.palgdetectorresult.title" />
</h2>
<div class="postcontent">
	<div class="pcojsubinform">
		<div class="pcojsubinformsource">
			<h4>Source Submission</h4>
			<b>ID:</b> <a href="/24h/submission.xhtml?id=${sourcesubmission.sid}"><c:out value="${sourcesubmission.sid}" /></a><br> <b>Date:</b>
			<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${sourceSubmission.date}" />
			<br> <b>Language:</b>
			<c:out value="${sourceSubmission.lang}" />
			<br> <b>Name:</b> <a href="/user/useraccount.xhtml?username=${sourceSubmission.username}"><c:out value="${sourceSubmission.username}" /></a><br> <b>Problem ID:</b>
			<c:out value="${sourceSubmission.pid}" />
			<br>
			<div id="sourceuseractions">				
				<c:if test="${sourceUser.enabled}">
					<a onclick="changeEnabledStatus('${sourceUser.username}',false)"><i class="fa fa-unlock fa-lg"></i></a>
				</c:if>
				<c:if test="${sourceUser.enabled == false}">
					<a id="btnenableuser" onclick="changeEnabledStatus('${sourceUser.username}',true)"><i class="fa fa-lock fa-lg"></i></a>
				</c:if>
			</div>
			<div style="clear: both; float: right;">                            
				<a href="/24h/downloadsource.xhtml?sid=${sourcesubmission.sid}"><img alt="" src="/images/save.png" /> <spring:message code="link.downloadscf" /></a>
			</div>
			<div>
				<textarea cols="" rows="" id="sourcecode" name="sourcecode" style="height: 410px; width: 100%; text-align: justify;" readonly><c:out value="${sourceSubmission.code}" /></textarea>
			</div>
		</div>

		<div class="pcojsubinformdestination">
			<h4>Destination Submission</h4>
			<b>ID:</b> <a href="/24h/submission.xhtml?id=${destinationsubmission.sid}"><c:out value="${destinationsubmission.sid}" /></a><br> <b>Date:</b>
			<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${destinationSubmission.date}" />
			<br> <b>Language:</b>
			<c:out value="${destinationSubmission.lang}" />
			<br> <b>Name:</b> <a href="/user/useraccount.xhtml?username=${destinationSubmission.username}"><c:out value="${destinationSubmission.username}" /></a><br> <b>Problem ID:</b>
			<c:out value="${destinationSubmission.pid}" />
			<br>
			<div id="destinationuseractions">				
				<c:if test="${destinationUser.enabled}">
					<a id="btndisabledestuser" onclick="changeEnabledDestStatus('${destinationUser.username}',false)"><i class="fa fa-unlock fa-lg"></i></a>
				</c:if>
				<c:if test="${destinationUser.enabled == false}">
					<a id="btnenabledestuser" onclick="changeEnabledDestStatus('${destinationUser.username}',true)" title="Enable user"><i class="fa fa-lock fa-lg"></i></a>
				</c:if>
			</div>
			<div style="clear: both; float: right;">
				<a href="/24h/downloadsource.xhtml?sid=${destinationsubmission.sid}"><img alt="" src="/images/save.png" /> <spring:message code="link.downloadscf" /></a>
			</div>
			<div>
				<textarea cols="" rows="" id="destinationcode" name="destinationcode" style="height: 410px; width: 100%; text-align: justify;" readonly><c:out value="${destinationSubmission.code}" /></textarea>
			</div>
		</div>
	</div>
	<hr>

	<table class="volume plagicojtable" id="tbPlagiCOJDetectorResults" border="1px">
		<caption class="plagicojtable">Independent Detector Results</caption>
		<thead class="orderby">
			<th>Detector Type</th>
			<th>Dictum</th>
			<th>Edit</th>
		</thead>
		<tbody>
			<c:forEach items="${plagiCOJDetectorResults}" var="result">
				<tr>
					<td><c:out value="${result.getOrigin()}" /></td>                                        
                                        <td><fmt:formatNumber type="percent" value="${result.getDictum()}" /> </td>
					<td><a href="/plagicoj/sourcecodedetectorresult.xhtml?ssid=${sourcesubmission.sid}&dsid=${destinationsubmission.sid}" title="<fmt:message key="messages.general.go"/>"><i class="fa fa-edit"></i></a></td>
				</tr>

			</c:forEach>
		</tbody>
	</table>
	<table id="tbjudgesrevisions" class="volume plagicojtable" border="1px">
		<caption>Judges Revisions</caption>
		<thead class="orderby">
			<th>Judge User Id</th>
			<th>Date</th>
			<th>Evaluation</th>
			<th>Comment</th>
			<th>Edit</th>
		</thead>
		<tbody>
			<c:forEach items="${judgesRevisions}" var="revision">
				<tr id="${revision.revisionId}">
					<td><c:out value="${revision.userId}" /></td>
					<td><fmt:formatDate value="${revision.date}" dateStyle="long" />.<fmt:formatDate value="${revision.date}" pattern="HH:mm:ss" type="time" /></td>
					<td><c:out value="${revision.evaluation}" /></td>
					<td><c:out value="${revision.comment}" /></td>
					<td><c:if test="${SPRING_SECURITY_LAST_USERNAME == revision.userId}">
							<a onclick="deleteRevision(${revision.revisionId})"><i class="fa fa-trash"></i></a>
						</c:if></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<input type="button" id="btnAddRevision" value="Add revision" onclick="addRevision()" />
</div>
<a href="/admin/manageplagicoj.xhtml">Back</a>
<script type="text/javascript">
    
    var langsource = '${sourceSubmission.lang}';
    if(langsource == "Pascal"){
        langsource = "pas";
    }
    if(langsource == "C"){
        langsource = "c";
    }
    if(langsource == "Text"){
        langsource = "robotstxt";
    }
    if(langsource == "C++"){
        langsource = "cpp";
    }
    var langdestinatino = '${destinationSubmission.lang}';
    if(langdestinatino == "Pascal"){
        langdestinatino = "pas";
    }
    if(langdestinatino == "C"){
        langdestinatino = "c";
    }
    if(langdestinatino == "Text"){
        langdestinatino = "robotstxt";
    }
    if(langdestinatino == "C++"){
        langdestinatino = "cpp";
    }
    
    editAreaLoader.init({
        id: "sourcecode"	// id of the textarea to transform
        ,start_highlight: true
        ,font_size: "10"
        ,font_family: "verdana, monospace"
        ,allow_toggle: false
        ,language: "en"
        ,syntax: langsource
        ,toolbar: "search, go_to_line,|, select_font, |, highlight, reset_highlight, |, help"
        ,plugins: "charmap"
        ,charmap_default: "arrows"
        ,word_wrap : true
    });
    editAreaLoader.init({
        id: "destinationcode"	// id of the textarea to transform
        ,start_highlight: true
        ,font_size: "10"
        ,font_family: "verdana, monospace"
        ,allow_toggle: false
        ,language: "en"
        ,syntax: langdestinatino
        ,toolbar: "search, go_to_line,|, select_font, |, highlight, reset_highlight, |, help"
        ,plugins: "charmap"
        ,charmap_default: "arrows"
        ,word_wrap : true
    });
    

    function toogle_editable(id)
    {
        editAreaLoader.execCommand(id, 'set_editable', !editAreaLoader.execCommand(id, 'is_editable'));
    }

</script>