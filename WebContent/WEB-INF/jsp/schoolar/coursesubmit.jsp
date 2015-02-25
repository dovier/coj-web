<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<script  type="text/javascript" src="<c:url value="/js/edit_area/edit_area_full.js"/>"></script>
<script  type="text/javascript" src="<c:url value="/js/coj.js" />"></script>
<script type='text/javascript' src="<c:url value="/js/jquery.js" />"></script>

<h2 class="postheader">
    <a href="<c:url  value="course.xhtml?course_id=${course.course_id}"/>">${course.name}</a>
</h2>
<div class="postcontent">
    <!-- article-content -->
    <table>
        <tr>
            <td class="schoolarleft">
                <form:form method="post" enctype="multipart/form-data" commandName="submit">
                    <table class="createnewuser">
                        <tr>
                            <td style="align:right"><label><spring:message code="fieldhdr.problemid"/>:</label>
                            </td>
                            <td><form:input path="pid"/> <a><i class="fa fa-asterisk" title="<spring:message code="mandatory.field"/>"></i></a></td>

                        </tr>
                        <tr>
                            <td></td>
                            <td><div class="error"><form:errors path="pid" /></div></td>
                        </tr>
                        <tr>
                            <td style="align:right">
                                <label><spring:message code="fieldhdr.proglanguage"/>:</label>
                            </td>

                            <td>
                                <form:select path="key" id="lang" onchange="updSelect()">
                                    <form:options items="${languages}" itemLabel="descripcion" itemValue="key" />
                                </form:select>
                            </td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><div class="error"><form:errors path="lid" /></div></td>
                        </tr>
                        <tr>
                            <td style="align:right">
                                <label>
                                    <spring:message code="fieldhdr.sourcecode"/>:
                                </label>
                            </td>
                            <td>
                                <input type="file" name="uploadfile" size="40px"/> <a><i class="fa fa-asterisk" title="<spring:message code="mandatory.field"/>"></i></a>
                            </td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><div class="error"><form:errors path="code" /></div></td>
                        </tr>

                    </table>          

                    <div>
                        <form:textarea cols="100" rows="25" id="code" path="code" cssClass="submit" tabindex="2"/><br/>                        
                    </div>        
                    <input type="submit" value="<spring:message code="button.submit"/>" name="sub" />
                    <input type="reset" value="<spring:message code="button.reset"/>" name="reset" />
                </form:form>
            </td>
            <td class="schoolarright">
                <fieldset>
                    <legend>Dash Board</legend>
                    <div class="designDiv">
                        <table class="volume" id="schoolarright">
                        </table>
                    </div>
                </fieldset>
            </td>
        </tr>
    </table>
    <!-- /article-content -->
</div>

<script  type="text/javascript">
    var enable = ${enableadveditor};
    activate_editor(enable);
    setTimeout("updateBoard(0,${course.course_id})", 1000);
</script>