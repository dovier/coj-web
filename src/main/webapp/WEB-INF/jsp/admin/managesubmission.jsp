<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer = "16kb" autoFlush="true" %>
<script  type="text/javascript" src="/js/edit_area/edit_area_full.js"></script>

<h2 class="postheader">
    <fmt:message key="page.general.admin.header"/>: Submission
</h2>
<div class="postcontent">    
    <form:form method="post" commandName="submission">
        <table class="createnewuser">
            <tbody>
                <tr>
                    <td style="align:right">ID<i class="fa fa-asterisk"></i></td>
                    <td><form:input path="sid" readonly="true" maxlength="30"/></td>
                    <td><span class="label label-danger"><form:errors path="sid" /></span></td>
                </tr>

                <tr>
                    <td style="align:right">Username<i class="fa fa-asterisk"></i></td>
                    <td><form:input path="username" size="30" maxlength="30" readonly="true"/></td>
                    <td><span class="label label-danger"><form:errors path="username" /></span></td>
                </tr>

                <tr>
                    <td style="align:right">Status<i class="fa fa-asterisk"></i></td>
                    <td>
                        <form:select path="status">
                            <form:options items="${results}" itemLabel="status" itemValue="status"/>
                        </form:select>
                    </td>
                    <td><span class="label label-danger"><form:errors path="status" /></span></td>
                </tr>

                <tr>
                    <td style="align:right">Time (MS)<i class="fa fa-asterisk"></i></td>
                    <td><form:input path="timeUsed" size="30" maxlength="30"/></td>
                    <td><span class="label label-danger"><form:errors path="timeUsed" /></span></td>
                </tr>

                <tr>
                    <td style="align:right">Memory (KB)<i class="fa fa-asterisk"></i></td>
                    <td><form:input path="memoryMB" size="30" maxlength="30"/></td>
                    <td><span class="label label-danger"><form:errors path="memoryUsed" /></span></td>
                </tr>
                <tr>
                    <td style="align:right"><fmt:message key="judge.register.language"/>:<i class="fa fa-asterisk"></i></td>
                    <td>
                        <form:select path="lang">
                            <form:options items="${planguages}" itemLabel="descripcion" itemValue="language"/>
                        </form:select>                        
                    </td>
                </tr>

                <tr>
                    <td style="align:right"><fmt:message key="judge.register.enabled"/><i class="fa fa-asterisk"></i></td>
                    <td>
                        <form:checkbox path="enabled"/>
                    </td>
                </tr>

                <tr>
                    <td style="align:right">Source Code</td>
                    <td>
                        <form:textarea path="code" id="code" cssStyle="height: 410px;width: 100%;text-align: justify;" cols="100"/>
                    </td>
                    <td><span class="label label-danger"><form:errors path="code" /></span></td>
                </tr>

                <tr>
                    <td><input type="submit" name="submit" id="submit" value="<fmt:message key="judge.updateaccount.submit"/>" /></td>
                    <td></td>
                </tr>
            </tbody>
        </table>
    </form:form>    
</div>

<script  type="text/javascript">

    var lang = document.getElementById("lang").innerHTML;
    if(lang == "Pascal"){
        lang = "pas";
    }
    if(lang == "C"){
        lang = "c";
    }
    if(lang == "Text"){
        lang = "robotstxt";
    }
    if(lang == "C++"){
        lang = "cpp";
    }
    editAreaLoader.init({
        id: "code"	// id of the textarea to transform
        ,start_highlight: true
        ,font_size: "10"
        ,font_family: "verdana, monospace"
        ,allow_toggle: false
        ,language: "en"
        ,syntax: lang
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