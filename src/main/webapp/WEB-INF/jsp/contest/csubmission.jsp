<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script  type="text/javascript" src="/js/edit_area/edit_area_full.js"></script>
<script  type="text/javascript">
    editAreaLoader.init({
        id: "code"	// id of the textarea to transform
        ,start_highlight: true
        ,font_size: "10"
        ,font_family: "verdana, monospace"
        ,allow_toggle: false
        ,language: "en"
        ,syntax: "java"
        ,toolbar: "new_document, save, load, |, charmap, |, search, go_to_line, |, undo, redo, |, select_font, |, change_smooth_selection, highlight, reset_highlight, |, help"
        ,plugins: "charmap"
        ,charmap_default: "arrows"
        ,EA_load_callback: "toogle_editable"
        ,word_wrap : true
    });

    function toogle_editable(id)
    {
        editAreaLoader.execCommand(id, 'set_editable', !editAreaLoader.execCommand(id, 'is_editable'));
    }

</script>

<h2 class="postheader" style="clear: both">
    <a class="linkheader" href="<c:url value="contestview.xhtml?cid=${contest.cid}"/>" title="Go to Contest">${contest.name}</a>
    <br/>
    <spring:message code="pagehdr.submission"/>
</h2>
<div class="postcontent">
    <center>
        <table class="volume" border="1px">
            <thead>
            <th><spring:message code="tablehdr.id"/></th>
            <th><spring:message code="tablehdr.date"/></th>
            <th class="user"><spring:message code="tablehdr.user"/></th>
            <th><spring:message code="tablehdr.prob"/></th>
            <th class="result"><spring:message code="tablehdr.judgment"/></th>            
            <th><spring:message code="tablehdr.time"/></th>
            <th class="headmem"><spring:message code="tablehdr.mem"/></th>
            <th class="headsize"><spring:message code="tablehdr.size"/></th>
            <th class="headlang"><spring:message code="tablehdr.lang"/></th>
            </thead>
            <tr>
                <td><c:out value="${submission.sid}"/></td>
                <td class="date"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${submission.date}" /></td>
                <td><a href="cuseraccount.xhtml?cid=${contest.cid}&uid=${submission.username}">${submission.username}</a></td>
                <td><a href="<c:url value="cproblem.xhtml?pid=${submission.pid}&cid=${contest.cid}"/>"><c:out value="${submission.problemTitle}"/></a></td>
                <td width="15%" class="<c:out value="sub${submission.statusClass}"/>" id="<c:out value="${submission.statusId}"/>" name="<c:out value="${submission.statusName}"/>" ><b><c:out value="${submission.status}"/></b></td>                
                <td><c:out value="${submission.timeUsed}"/></td>
                <td><c:out value="${submission.memoryMB}"/></td>
                <td><c:out value="${submission.fontMB}"/></td>
                <td id="lang"><c:out value="${submission.lang}"/></td>
            </tr>
        </table>        
    </center>
    <br/>
    <div style="clear: both;float: right;"><a href="/contest/csubmit.xhtml?sid=${submission.sid}&cid=${contest.cid}" title="<fmt:message key="link.edit"/>"><i class="fa fa-edit"></i>&nbsp;<spring:message code="link.edit" /></a> <a href="downloadsource.xhtml?sid=${submission.sid}&cid=${contest.cid}"><i class="fa fa-save"></i>&nbsp;<spring:message code="link.download"/></a></div>
    <div>
        <textarea cols="" rows=""  id="code" style="height: 410px;width: 100%;text-align: justify;" readonly>${submission.code}</textarea>
    </div>
</div>








