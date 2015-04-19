<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--link rel="stylesheet" href="../Style/style.css" type="text/css" media="screen" /-->
<script  type="text/javascript" src="<c:url value="/js/WYSIWYG/source.js" />"></script>
<script  type="text/javascript" src="<c:url value="/js/coj.js" />"></script>
<h2 class="postheader">
    <fmt:message key="page.general.admin.header"/>: <fmt:message key="page.menu.admin.upload" />
</h2>
<div class="postcontent">
    <form:form method="post" enctype="multipart/form-data" commandName="uploadFile">
        <div id="inputfiles">

            <table>
                <tr>
                    <td colspan="2" style="text-align: left">
                        ROOT${currentDir}
                    </td>
                    <td>
                        <a href="/admin/uploadfile.xhtml?action=refresh">
                            <img width="16" height="16" src="<c:url value="/images/refresh.ico"/>" />
                        </a>
                    </td>
                </tr>
                <c:if test="${backAvail}">
                    <tr>
                        <td style="text-align: left">
                            <a href="/admin/uploadfile.xhtml?action=back">
                                /..
                            </a>
                        </td>
                        <td colspan="2">
                        </td>
                    </tr>
                </c:if>
                <c:forEach items="${files}" var="file">
                    <tr>
                        <td style="text-align: left">
                            <c:if test="${file.directory}">
                                <a href="/admin/uploadfile.xhtml?dir=${file.name}">
                                </c:if>
                                ${file.name}
                                <c:if test="${file.directory}">
                                </a>
                            </c:if>
                        </td>
                        <td>
                            <a onclick="return validate()" href="/admin/uploadfile.xhtml?action=delete&dir=${file.name}" title="<spring:message code="titval.delete" />">
                                <i class="fa fa-trash"></i> 
                            </a>                                
                        </td>
                        <td>
                            <c:if test="${!file.directory}">
                                <a href="/admin/downloadfile.xhtml?dir=${file.name}">
                                    <a><i class="fa fa-save"></i></a>
                                </a>
                            </c:if>
                        </td>
                    </tr>

                </c:forEach>
            </table>
                        <fieldset>
            <table id="uploadinput">
                <tr>
                    <td style="align:right">
                        <spring:message code="fieldhdr.createfolder" />:
                    </td>
                    <td style="text-align: left;">
                        <input type="text" name="folder"/>
                    </td>
                </tr>
                <tr>
                    <td style="align:right">
                        <spring:message code="fieldhdr.uploadfile" />:
                    </td>
                    <td style="text-align: left;">
                        <input type="file" name="file1"/>
                    </td>
                </tr>
            </table>
                    </fieldset>
        </div>
        <input type="submit" name="but" value="Add"/>
    </form:form>
</div>

<script type="text/javascript">
    function validate(){
        var answer = confirm ("Are You sure you want to delete this file ....");
        if(!answer)
            return false;
        return true;
    }
</script>