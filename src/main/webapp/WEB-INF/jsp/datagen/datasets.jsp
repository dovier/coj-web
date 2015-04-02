<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<script  type="text/javascript" src="<c:url value="/js/jquery.js" />"></script>
<script  type="text/javascript" src="<c:url value="/js/edit_area/edit_area_full.js"/>"></script>
<script  type="text/javascript" src="<c:url value="/js/coj.js" />"></script>

<h2 class="postheader">
    <spring:message code="pagehdr.datagen"/>
</h2>
<div class="postcontent">
    <form:form method="post" enctype="multipart/form-data" commandName="dataset">
        <table class="createnewuser">
            <tr>
                <td style="align:right">
                    <label id="modelabel">
                        <spring:message code="fieldhdr.mode"/>:
                    </label>
                </td>
                <td>
                    <form:select  id="mode" path="mode" onchange="updMode()">
                        <form:option id="customsol" value="customsol" ><spring:message code="fieldval.customsol"/></form:option>
                        <form:option id="inputgen" value="inputgen" ><spring:message code="fieldval.inputgen"/></form:option>
                        <form:option id="modelsol" value="modelsol" ><spring:message code="fieldval.modelsol"/></form:option>                        
                    </form:select>
                </td>
            </tr>
            <tr>
                <td style="align:right">
                    <label id ="problemidlabel" ><spring:message code="fieldhdr.problemid"/>:</label>
                </td>
                <td>
                    <form:input id="problemid" path="problemId"/>
                </td>
            </tr>
            <tr>
                <td></td>
                <td><div class="error"><form:errors path="problemId" /></div></td>
            </tr>
            <tr>
                <td style="align:right">
                    <label style="display: none;" id="langlabel"><spring:message code="fieldhdr.proglanguage"/>:</label>
                </td>
                <td>
                    <form:select path="key" id="lang" onchange="updSelect()">
                        <form:options items="${languages}" itemLabel="descripcion" itemValue="key" />
                    </form:select>                    
                </td>
            </tr>
            <tr>
                <td></td>
                <td><div class="error"><form:errors path="key" /></div></td>
            </tr>
            <tr>
                <td style="align:right">
                    <label id="codelabel" style="display: none;">
                        <spring:message code="fieldhdr.sourcecode"/>:
                    </label>
                </td>
                <td>
                    <input id="codefile" type="file" name="uploadcodefile" size="35px" style="display: none;"/>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <div id="code_container">

                        <form:textarea cols="100" rows="25" id="code" path="code" cssClass="submit_form" tabindex="2"/><br/>

                    </div>
                </td>
            </tr>

            <tr>
                <td><div class="error"><form:errors path="code" /></div></td>
                <td></td>
            </tr>
            <tr>
                <td style="align:right">
                    <label id="inputlabel">
                        <spring:message code="fieldhdr.input"/>:
                    </label>
                </td>
                <td>
                    <input id="inputfile" type="file" name="uploadinputfile" size="30px"/>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <div>
                        <form:textarea cols="100" rows="15" id="input" path="input" cssClass="submit_form" tabindex="2"/><br/>                        
                    </div> 
                </td>
            </tr>
            <tr>
                <td></td>
                <td><div class="error"><form:errors path="input" /></div></td>
            </tr>
            <tr>
                <td>
                    <label id="outputlabel">
                        <spring:message code="fieldhdr.output"/>:
                    </label>
                </td>
                <td>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <div>
                        <form:textarea cols="100" rows="15" id="output" path="output" readonly="true" cssClass="submit_form" tabindex="2"/><br/>                        
                    </div> 
                </td>
            </tr>
            <tr>
                <td>
                    <label id="comparisonlabel">
                        <spring:message code="fieldhdr.comparison"/>:
                    </label>
                </td>
                <td>
                    <label id="matchlabel">
                        <c:if test="${not empty cmp_lines && !matchStatus}">
                            <span class="match"><spring:message code="datagen.match"/></span>
                        </c:if>
                        <c:if test="${not empty cmp_lines && matchStatus}">
                            <span class="no_match"><spring:message code="datagen.no.match"/></span>
                        </c:if>
                    </label>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <div id="comparison_user" class="half_submit_form">
                        <label>
                            <spring:message code="fieldhdr.comparison.user"/>:
                        </label>
                        <select id="user_output" class="compare_select" multiple="true">
                            <c:forEach items="${cmp_lines}" var="line" >
                                <c:if test="${line.result eq 'Equal'}">
                                    <option class="line_equal" value="${line.source.info.source}" >${line.source.info.source}</option>
                                </c:if>
                                <c:if test="${line.result eq 'Diferent'}">
                                    <option class="line_different" value="${line.source.info.source}" >${line.source.info.source}</option>
                                </c:if>
                                <c:if test="${line.result ne 'Equal' && line.result ne 'Diferent'}">
                                    <option class="line_nil" value=" ${line.source.info.source}" >${line.source.info.source}</option>
                                </c:if>
                            </c:forEach>
                        </select> 
                    </div>
                    <div id="comparison_splitter" class="half_submit_form_splitter">

                    </div>
                    <div id="comparison_model" class="half_submit_form">
                        <label>
                            <spring:message code="fieldhdr.comparison.model"/>:
                        </label>
                        <select id="model_output"  class="compare_select" multiple="true">
                            <c:forEach items="${cmp_lines}" var="line" >
                                <c:if test="${line.result eq 'Equal'}">
                                    <option class="line_equal" value="${line.destination.info.source}" >${line.destination.info.source}</option>
                                </c:if>
                                <c:if test="${line.result eq 'Diferent'}">
                                    <option class="line_different" value="${line.destination.info.source}" >${line.destination.info.source}</option>
                                </c:if>
                                <c:if test="${line.result ne 'Equal' && line.result ne 'Diferent'}">
                                    <option class="line_nil" value=" ${line.destination.info.source}" >${line.destination.info.source}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </div>
                </td>
            </tr> 
            <tr>
                <td>
                    <div id="lastdl">
                        <c:if test="${output ne null}">
                            <a href="download.xhtml"><spring:message code="link.lastdataset"/></a>
                        </c:if>
                    </div>
                </td>
                <td>
                    <div id="fulldl">
                        <c:if test="${dlAvail}">
                            <a href="download.xhtml?pid=${dataset.problemId}"><spring:message code="link.dataset"/> ${dataset.problemId}</a>
                        </c:if>
                    </div>                     
                </td>
            </tr>
        </table>          

        <input type="submit" value="<spring:message code="button.submit"/>" name="sub" />
        <input type="button" value="<spring:message code="button.reset"/>" id="reset"/>
    </form:form>
</div>


<script  type="text/javascript">
    $("#reset").bind("click", function() {
        $('#code').val('');
        $('#input').val('');
        $('#output').val('');
        $('#matchlabel').empty();
        $(".line_equal").remove();
        $(".line_different").remove();
        $(".line_nil").remove();
    }
    );
        
    $("#submit").bind("click", function() {
        $('#matchlabel').empty();
        $(".line_equal").remove();
        $(".line_different").remove();
        $(".line_nil").remove();
    }
    );    
    updMode();

</script>
