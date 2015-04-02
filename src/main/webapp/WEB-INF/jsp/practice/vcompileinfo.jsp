<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script  type="text/javascript" src="/js/edit_area/edit_area_full.js"></script>
<style type="text/css">
    table {

        font: .9em/1.2em Georgia, "Times New Roman", Times, serif;
        color: #000;
    }

    caption {
        font-size: 1.3em;
        font-weight: bold;
        text-align: left;
        padding: 1em 4px;
    }
    td, th {
        padding: 1px 1px .75em 1px;
        line-height: 1.3em;
    }
    th {
        background: #839E99;
        color: #fff; font-weight:
            bold; text-align: left;
        padding-right: .5em;
        vertical-align: top;
    }
    thead th {
        text-align: center;
    }
    .odd td {
        background: #DBE6DD;
    }
    .odd th {
        background: #6E8D88;
    }
    td a, td a:link {
        color: #325C91;
    }
    td a:visited {
        color: #466C8E;
    }
    td a:hover, td a:focus {
        color: #1E4C94;
    }
    th a, td a:active {
        color: #fff;
    }
    tfoot th, tfoot td {
        background: #2C5755;
        color: #fff;
    }
    th + td {
        padding-left: .5em;
    }
</style>
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
    <a class="linkheader" href="<c:url value="vcontestview.xhtml?cid=${contest.cid}"/>">${contest.name}</a>
    <br/>
    <spring:message code="pagehdr.compileinfo"/>
</h2>
<div class="postcontent">
    <div><textarea id="code" style="height: 410px;width: 100%;text-align: justify;"><c:out value="${info}"/></textarea></div>
</div>