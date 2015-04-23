<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer = "16kb" autoFlush="true" %>
<script  type="text/javascript" src="<c:url value="/js/coj.js" />"></script>
<script type='text/javascript' src="<c:url value="/js/jquery.js" />"></script>


<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate  var="year" value="${now}" pattern="yyyy" />
<h2 class="postheader">
    <spring:message code="pagehdr.pcscreate"/>
</h2>
<div class="postcontent">    
    <form:form method="post"  onsubmit="return SeleccionarRangosVirtualContest();" commandName="contest">

        <table class="createnewuser"> 

            <tr>                
                <td>
                    <spring:message code="fieldhdr.virtual"/><form:radiobutton path="style" value="1" onchange="changeProblemsState()" id="virtual_style"/> 
                </td> 

                <td> 
                    <spring:message code="fieldhdr.freeprob"/> <form:radiobutton path="style" value="2" onchange="changeProblemsState()" id="free_problems_style"/>
                </td>
            </tr>

            <tr>
                <td style="align:right">
                    <div id="virtual_contest_1"><spring:message code="fieldhdr.template"/>:</div>
                </td>
                <td colspan="6">
                    <div id="virtual_contest_2">
                        <form:select path="template" id="all_contests">
                            <form:option value="0">
                                <spring:message code="fieldval.select"/>
                            </form:option>
                            <form:options items="${contests}" itemLabel="name" itemValue="cid"/>
                        </form:select>
                        <a><i class="fa fa-asterisk" title="<spring:message code="mandatory.field"/>"></i></a>
                    </div>
                </td>                               
            </tr>

            <tr>
                <td></td>                     
                <td colspan="3"><div id="virtual_contest_3"><span class="label label-danger"><form:errors path="template" /></span></div></td>
            </tr>

            <tr>
                <td style="align:right">
                    <div><spring:message code="fieldhdr.start"/>:</div>
                </td>
                <td colspan="2">
                    <div>
                        <form:select path="iyear">
                            <c:forEach begin="${year - 1}" step="1" end="${year + 1}" var="value">
                                <form:option value="${value}">${value}</form:option>
                            </c:forEach>
                        </form:select>

                        <form:select path="imonth">
                            <c:forEach begin="1" step="1" end="12" var="value">
                                <form:option value="${value}">${value}</form:option>
                            </c:forEach>
                        </form:select>

                        <form:select path="iday">
                            <c:forEach begin="1" step="1" end="31" var="value">
                                <form:option value="${value}">${value}</form:option>
                            </c:forEach>
                        </form:select>
                    </div>
                </td>

                
                <td colspan="4">
                    <div>
                        <form:select path="ihour">
                            <c:forEach begin="0" step="1" end="23" var="value">
                                <form:option value="${value}">${value}</form:option>
                            </c:forEach>
                        </form:select>

                        <form:select path="iminutes">
                            <c:forEach begin="0" step="1" end="59" var="value">
                                <form:option value="${value}">${value}</form:option>
                            </c:forEach>
                        </form:select>

                        <form:select path="iseconds">
                            <c:forEach begin="0" step="1" end="59" var="value">
                                <form:option value="${value}">${value}</form:option>
                            </c:forEach>
                        </form:select>
                    </div>
                </td>                
                <td>
                    <div id="virtual_contest_9"><span class="label label-danger"><form:errors path="initdate" /></span></div>
                </td>
            </tr>
            <tr>
                <td rowspan="2" colspan="3">
                    <spring:message code="fieldhdr.participants"/><a><i class="fa fa-info-circle" title="<spring:message code="infomsg.13"/>"></i></a>
                    <form:select path="usersids" id="contest_users" size="14" cssClass="login" cssStyle="width: 310px; height: 230px; border: 1px solid #577A5A;" multiple="true">                        
                        <c:forEach items="${contest.practice_locked}" var="user">
                            ${user.username}
                            <form:option value="${user.username}" cssClass="errorFalse"/>
                        </c:forEach>
                    </form:select>
                </td>

                <td>
                    <button name="boton" type="button" onclick="addremove('contest_users','allusers');">
                        <i class="fa fa-arrow-right"></i>
                    </button>
                </td>

                <td rowspan="2" colspan="3">
                    <spring:message code="fieldhdr.allusers"/>:
                    <form:select path="" id="allusers" size="14" cssClass="login" cssStyle="width: 310px; height: 230px; border: 1px solid #577A5A;" multiple="true">
                        <form:options items="${allusers}" itemValue="username" itemLabel="username"/>
                    </form:select>
                </td>                
            </tr>
            <tr>
                <td>
                    <button name="boton" type="button" onclick="addremove('allusers','contest_users');">
                        <i class="fa fa-arrow-left"></i>
                    </button>
                </td>
            </tr>
            <tr>                
                <td colspan="4"><span class="label label-danger"><form:errors path="usersids" /></span></td>
            </tr>
            
            <tr>
                <td colspan="3">
                    <div id="hideproblemlabel1" style="display: none"><fmt:message key="fieldhdr.contestprob" />:</div> 
                </td>
                <td>

                </td>
                <td colspan="3">
                    <div id="hideproblemlabel2" style="display: none"><fmt:message key="fieldhdr.allprob" />:</div>
                </td>
            </tr>
            <tr>
                <td rowspan="2" colspan="3">
                    <div id="hideallproblems" style="display: none">
                        <form:select path="problemids" id="contests_problem" size="14" cssClass="login" cssStyle="width: 310px; height: 230px; border: 1px solid #577A5A;" multiple="true">
                            <form:options items="${contest.problems}" itemLabel="title" itemValue="pid"/>
                        </form:select>    
                    </div>
                </td>
                <td>
                    <div id="hidebutton1" style="display: none"> <button name="boton" type="button" onclick="addremove('contests_problem','all_problems');">
                            <i class="fa fa-arrow-right"></i>
                        </button>
                    </div>
                </td>
                <td rowspan="2" colspan="3">
                    <div id="hidecontestproblems" style="display: none">
                        <form:select path="" id="all_problems" size="14" cssStyle="width: 330px; height: 230px; border: 1px solid #577A5A;" multiple="true" cssClass="login">
                            <form:options items="${problems}" itemLabel="title" itemValue="pid"/>
                        </form:select>     
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <div id="hidebutton2" style="display: none"><button name="boton" type="button" onclick="addremove('all_problems','contests_problem');">
                            <i class="fa fa-arrow-left"></i>
                        </button>
                    </div>
                </td>
            </tr>
            <tr>
                <td colspan="7">
                    <div id="hideerror" style="display: none">   <span class="label label-danger"><form:errors path="problemids" /></span></div>
                </td>
            </tr>

            <tr>
                <td>
                    <spring:message code="altval.public"/>:<form:checkbox path="is_public"/> <a><i class="fa fa-info-circle" title="<spring:message code="infomsg.14"/>"></i></a>
                </td>
                <td><span class="label label-danger"><form:errors path="is_public" /></span></td>
            </tr>

            <tr>
                <td colspan="6"><input type="submit" name="submit" id="submit" value="<spring:message code="button.create"/>" />&nbsp;&nbsp;
                <input type="reset" name="submit" id="submit" onclick="javascript:clearSelect();" value="<spring:message code="button.reset"/>" /></td>
            </tr>

        </table>
    </form:form>    
</div>


<script>
    changeProblemsState();
    function clearSelect(){
    	$("#contest_users").text("");
    };
</script>



