<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer = "16kb" autoFlush="true" %>
<script type='text/javascript' src="<c:url value="/js/jquery.js" />"></script>
<script type='text/javascript' src="<c:url value="/js/coj.js" />"></script>

<h2 class="postheader">
    <fmt:message key="judge.createteams.title"/>
</h2>
<div class="postcontent">    
    <form:form method="post" commandName="team">
        <table class="createnewuser">
            <tbody>

                <tr>
                    <td style="align:right"><fmt:message key="judge.register.username"/><i class="fa fa-asterisk"></i></td>
                    <td><form:input path="username" size="30" maxlength="30"/></td>
                    <td><span class="label label-danger"><form:errors path="username" /></span></td>
                </tr>
                <tr>
                    <td style="align:right"><fmt:message key="judge.register.nick"/><i class="fa fa-asterisk"></i></td>
                    <td><form:input path="nick" size="30" maxlength="30"/></td>
                    <td><span class="label label-danger"><form:errors path="nick" /></span></td>
                </tr>
                
                <tr>
                    <td style="align:right"><fmt:message key="fieldhdr.modnickname"/><i class="fa fa-asterisk"></i></td>
                    <td><form:checkbox path="update_nick"/></td>
                    <td><span class="label label-danger"><form:errors path="update_nick" /></span></td>
                </tr>
                
                <tr>
                    <td style="align:right"><fmt:message key="judge.register.password"/><i class="fa fa-asterisk"></i></td>
                    <td><form:password path="password" size="30" maxlength="30"/>
                    <a><i data-toggle="tooltip" class="fa fa-info-circle" title="<spring:message code="infomsg.8"/>"></i></a></td>
                    <td><span class="label label-danger"><form:errors path="password" /></span></td>
                </tr>
                <tr>
                    <td style="align:right"><fmt:message key="judge.register.confirmpassword"/><i class="fa fa-asterisk"></i></td>
                    <td><form:password path="confirmPassword" size="30" maxlength="30"/></td>
                    <td><span class="label label-danger"><form:errors path="confirmPassword" /></span></td>
                </tr>

                <tr>
                    <td style="align:right"><fmt:message key="judge.createteams.total"/><i class="fa fa-asterisk"></i></td>
                    <td><form:input path="total" size="30" maxlength="30"/></td>
                    <td><span class="label label-danger"><form:errors path="total" /></span></td>
                </tr>

                <tr>
                    <td style="align:right"><spring:message code="fieldhdr.country"/>:<i class="fa fa-asterisk"></i></td>
                    <td>
                        <form:select path="country" onchange="getInstitution();">
                            <form:options items="${countries}" itemLabel="name" itemValue="id"/>
                        </form:select>
                        <br/>
                        <span class="label label-danger"><spring:message code="text.ruaccount.2"/></span>
                    </td>
                    <td><span class="label label-danger"><form:errors path="country" /></span></td>
                </tr>

                <tr>
                    <td style="align:right"><spring:message code="fieldhdr.institution"/>:<i class="fa fa-asterisk"></i></td>
                    <td>
                        <form:select id="institution" path="institution">
                            <form:option value="0"><spring:message code="fieldval.none"/></form:option>
                            <form:options items="${institutions}" itemLabel="name" itemValue="id"/>
                        </form:select>
                        <br/>
                        <span class="label label-danger"><spring:message code="text.ruaccount.3"/></span>
                    </td>
                    <td><span class="label label-danger"><form:errors path="institution" /></span></td>
                </tr>
                <tr>
                    <td style="align:right"><fmt:message key="judge.register.locale"/><i class="fa fa-asterisk"></i></td>
                    <td>
                        <form:select path="locale">
                            <form:option value="0">
                                <fmt:message key="judge.register.select"/>
                            </form:option>
                            <form:options items="${locales}" itemValue="lid" itemLabel="description"/>
                        </form:select>
                    </td>
                    <td><span class="label label-danger"><form:errors path="locale" /></span></td>
                </tr>

                <tr>
                    <td style="align:right"><fmt:message key="judge.createteams.contest"/><i class="fa fa-asterisk"></i></td>
                    <td>
                        <form:select path="contest">
                            <form:option value="0">
                                <fmt:message key="judge.register.select"/>
                            </form:option>
                            <form:options items="${contests}" itemValue="cid" itemLabel="name"/>
                        </form:select>
                    </td>
                    <td><span class="label label-danger"><form:errors path="contest" /></span></td>
                </tr>

                <tr>
                    <td><span class="label label-danger"><i class="fa fa-asterisk"></i><fmt:message key="judge.register.requiredfield.value" /></span></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td><input type="submit" name="submit" id="submit" value="<fmt:message key="judge.register.submit.value"/>" /></td>
                    <td><input type="reset" name="submit" id="submit" value="<fmt:message key="judge.register.reset.value"/>" /></td>
                    <td></td>
                </tr>
            </tbody>
        </table>
    </form:form>   
</div>

