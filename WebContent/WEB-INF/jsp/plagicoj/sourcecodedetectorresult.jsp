<%@page import="org.antlr.runtime.Token"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
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
            type: "POST", 
            url: "/plagicoj/plagicojdetectorresult.xhtml", 
            data:"ssid=${sourcesubmission.sid}&dsid=${destinationsubmission.sid}&uid=${SPRING_SECURITY_LAST_USERNAME}&eval="+Math.floor($("#slider").slider("value")/20)+"&comment="+$("#tbjudgesrevisions input:eq(1)").attr("value"),
            success: function(msg){                                 
                var idRevision = jQuery(msg).find("#tbjudgesrevisions tbody tr:first").attr("id");
                $("#tbjudgesrevisions tr:eq(1) td:eq(2)").html(evaluations[Math.floor($("#slider").slider("value")/20)]);
                $("#tbjudgesrevisions tr:eq(1) td:eq(3)").html($("#tbjudgesrevisions input:eq(0)").attr("value"));
                $("#tbjudgesrevisions tr:eq(1) td:eq(4)").html("<a onclick=\"deleteRevision("+idRevision+")\"><i class=\"fa fa-trash\"></i></a>");                
                $("#tbjudgesrevisions tr:eq(1)").attr("id",idRevision);
            }
        });
        $("#btnAddRevision").show();        
    }  
    
    function deleteRevision(idRevision){ 
        $.ajax({ 
            type: "POST", 
            url: "/plagicoj/plagicojdetectorresult.xhtml", 
            data: "revid="+idRevision, 
            success: function(msg){                 
                $("#tbjudgesrevisions tr#"+idRevision).remove();              
            }
        });
    }
    
</script>

<h2 class="postheader">
	<fmt:message key="plagicoj.title" />
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
			<div style="clear: both; float: right;">
				<a href="/src/submission.xhtml?sid=${sourcesubmission.sid}"><img alt="" src="/images/save.png" /> <spring:message code="link.downloadscf" /></a>
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
			<div style="clear: both; float: right;">
				<a href="/src/submission.xhtml?sid=${destinationsubmission.sid}"><img alt="" src="/images/save.png" /> <spring:message code="link.downloadscf" /></a>
			</div>
			<div>
				<textarea cols="" rows="" id="destinationcode" name="destinationcode" style="height: 410px; width: 100%; text-align: justify;" readonly><c:out value="${destinationSubmission.code}" /></textarea>
			</div>
		</div>
	</div>	
			<div style="clear: both" id="pcojdiffdiv">
				<table class="pcojdifftable">
					<caption>Plagicoj code Matches</caption>
					<thead>
						<tr>
							<th></th>
							<th class="texttitle">Base Text</th>
							<th></th>
							<th class="texttitle">New Text</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${matches}" var="match">

							<c:set var="b" value="${match.getSourceIndex()}" />
							<c:set var="be" value="${match.getSourceIndex() + match.getLength()}" />
							<c:set var="n" value="${match.getDestinationIndex()}" />
							<c:set var="nend" value="${match.getDestinationIndex() + match.getLength()}" />
							<c:forEach var="i" begin="0" end="${match.getLength() - 1}">
								<c:if test="${b < be || n < nend}">
									<tr>
										<c:if test="${b < be}">
											<th>${sourceTokens.get(b).line}</th>
											<td title="${sourceTokens.get(b).type}" class="${match.plagiCOJDiffTag}">${sourceTokens.get(b).text}
										</c:if>
										<c:if test="${b >= be}">
											<th></th>
											<td class="empty">
										</c:if>
										<c:set var="b" value="${b+1}" />
										<c:set var="sourceTokens" value="${sourceTokens}" />
										<c:set var="plagiCOJDiffTag" value="${match.plagiCOJDiffTag}" />
										<%
											int b = Integer.parseInt(pageContext.getAttribute("b").toString());
										                                                int be = Integer.parseInt(pageContext.getAttribute("be").toString());
										                                                String diff = pageContext.getAttribute("plagiCOJDiffTag").toString();
										                                                List<Token> sourceTokens = (List<Token>) pageContext.getAttribute("sourceTokens");
										                                                while (b < be && sourceTokens.get(b).getLine() == sourceTokens.get(b - 1).getLine()) {
										                                                    out.print("<span title=\"" + sourceTokens.get(b).getType() + "\" class=\"" + diff + "\">" + sourceTokens.get(b).getText() + " </span>");
										                                                    b++;
										                                                }
										                                                pageContext.setAttribute("b", b);
										%>
										</td>
										<c:if test="${n < nend}">
											<th>${destinationTokens.get(n).line}</th>
											<td title="${destinationTokens.get(n).type}" class="${match.plagiCOJDiffTag}">${destinationTokens.get(n).text}
										</c:if>
										<c:if test="${n >= nend}">
											<th></th>
											<td class="empty">
										</c:if>
										<c:set var="n" value="${n+1}" />
										<c:set var="destinationTokens" value="${destinationTokens}" />
										<%
											int n = Integer.parseInt(pageContext.getAttribute("n").toString());
										                                                int nend = Integer.parseInt(pageContext.getAttribute("nend").toString());
										                                                List<Token> destinationTokens = (List<Token>) pageContext.getAttribute("destinationTokens");
										                                                while (n < nend && destinationTokens.get(n).getLine() == destinationTokens.get(n - 1).getLine()) {
										                                                    out.print("<span title=\"" + destinationTokens.get(n).getType() + "\" class=\"" + diff + "\">" + destinationTokens.get(n).getText() + " </span>");
										                                                    n++;
										                                                }
										                                                pageContext.setAttribute("n", n);
										%>
										</td>
									</tr>
								</c:if>
							</c:forEach>
						</c:forEach>

					</tbody>
				</table>
			</div>
		
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
							<a onclick="deleteRevision(${revision.revisionId});" title="<fmt:message key="messages.general.delete"/>"><i class="fa fa-trash"></i></a>
						</c:if></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<input type="button" id="btnAddRevision" value="Add revision" onclick="addRevision()" />
</div>
<a href="/plagicoj/plagicojresult.xhtml?ssid=${sourcesubmission.sid}&didd=${destinationsubmission.sid}">Back</a>
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