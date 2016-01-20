<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<link rel="stylesheet" href="<c:url value="/css/confirm-message.css"/>"
      type="text/css" media="screen" />

<h2 class="postheader">
    <fmt:message key="pagehdr.24translation" />
</h2>
<div class="row postcontent">
    <div class="col-xs-12">
        <div class="form-group col-xs-12">
            <label class="control-label col-xs-3">PID</label>
            <div class="col-xs-8">
                <form>
                    <input class="form-control" value="${problem.pid}" readonly>
                </form>
            </div>
            <a><i data-toggle="tooltip" class="fa fa-asterisk"
                           title="<spring:message code="mandatory.field"/>">
                        </i></a>
        </div>		

        <div class="form-group col-xs-12">
            <ul class="nav nav-tabs" role="tablist">
                <li class="active"><a role="tab"  data-toggle="tab" href="#en"><fmt:message
                            key="titval.en" /></a></li>
                <li><a role="tab"  data-toggle="tab" href="#es"><fmt:message
                            key="titval.es" /></a></li>
                <li><a role="tab"  data-toggle="tab" href="#pt"><fmt:message
                            key="titval.pt" /></a></li>
            </ul>
        </div>

        <div id="tabs" class="tab-content margin-top-05">
            <div role="tabpanel" class="tab-pane fade in active" id="en">
                <form:form method="post" commandName="translation" cssClass="translation">
                    <form:input path="pid" type="hidden"></form:input>
                    <form:input path="locale" type="hidden" value="en" />

                    <div class="form-group col-xs-12">	
                        <label class="control-label col-xs-3"><fmt:message
                                key="addproblem.name" /></label>
                        <div class="col-xs-8">
                            <form:input cssClass="form-control" path="title" value="${problem.title}" />
                        </div>
                    </div>

                    <div class="form-group col-xs-12">
                        <label class="control-label col-xs-3"><fmt:message
                                key="addproblem.description" /></label>
                        <textarea class="form-control" name="description"
                                  id="code" rows="15"> ${problem.description} </textarea>
                    </div>

                    <div class="form-group col-xs-12">
                        <label class="control-label col-xs-3"><fmt:message
                                key="addproblem.input" /></label>
                        <textarea class="form-control in" name="input" id="input"
                                  rows="15"> ${problem.input} </textarea>
                    </div>

                    <div class="form-group col-xs-12">
                        <label class="control-label col-xs-3"><fmt:message
                                key="addproblem.output" /></label>
                        <textarea class="form-control out" name="output" id="output"
                                  rows="15"> ${problem.output} </textarea>
                    </div>

                    <div class="form-group col-xs-12">
                        <label class="control-label col-xs-3"><fmt:message
                                key="addproblem.comm" /></label>
                        <textarea class="form-control in " name="comments"
                                  id="hint" rows="15"> ${problem.comments} </textarea>
                    </div>

                    <div class="col-xs-12">
                        <div class="form-actions pull-right ">
                            <input class="btn btn-primary confirm-message" type="submit" name="but"
                                   value='<spring:message code="button.send" />'
                                   data-confirm-title='<spring:message code="message.title"/>'
                                   data-confirm-message='<spring:message code="message.translation.pending"/>'
                                   data-confirm-type="message"
                                   data-redirect="/24h/problems.xhtml" />
                        </div>
                    </div>
                </form:form>
            </div>
            <div role="tabpanel" class="tab-pane fade in active" style="position:fixed;left:10000px;" id="es">
                <form:form method="post" commandName="translation" cssClass="translation">
                    <form:input path="pid" type="hidden"></form:input>
                    <form:input path="locale" type="hidden" value="es" />

                    <div class="form-group col-xs-12">	
                        <label class="control-label col-xs-3"><fmt:message
                                key="addproblem.name" /></label>
                        <div class="col-xs-8">
                            <form:input cssClass="form-control" path="title" value="${problem.titleEs}" />
                        </div>
                    </div>

                    <div class="form-group col-xs-12">
                        <label class="control-label col-xs-3"><fmt:message
                                key="addproblem.description" /></label>
                        <textarea class="form-control" name="description"
                                  id="codeEs" rows="15">${problem.descriptionEs}</textarea>
                    </div>

                    <div class="form-group col-xs-12">
                        <label class="control-label col-xs-3"><fmt:message
                                key="addproblem.input" /></label>
                        <textarea class="form-control in" name="input"
                                  id="inputEs" rows="15">${problem.inputEs}</textarea>
                    </div>

                    <div class="form-group col-xs-12">
                        <label class="control-label col-xs-3"><fmt:message
                                key="addproblem.output" /></label> 
                        <textarea class="form-control out" name="output"
                                  id="outputEs" rows="15">${problem.outputEs}</textarea>
                    </div>

                    <div class="form-group col-xs-12">
                        <label class="control-label col-xs-3"><fmt:message
                                key="addproblem.comm" /></label>
                        <textarea class="form-control in" name="comments"
                                  id="hintEs" rows="15">${problem.commentsEs} </textarea>
                    </div>

                    <div class="col-xs-12">
                        <div class="form-actions pull-right ">
                            <input class="btn btn-primary confirm-message" type="submit" name="but"
                                   value='<spring:message code="button.send" />'
                                   data-confirm-title='<spring:message code="message.title"/>'
                                   data-confirm-message='<spring:message code="message.translation.pending"/>'
                                   data-confirm-type="message"
                                   data-redirect="/24h/problems.xhtml" />
                        </div>
                    </div>
                </form:form>
            </div>
            <div role="tabpanel" class="tab-pane fade in active" style="position:fixed;left:10000px;" id="pt">
                <form:form method="post" commandName="translation" cssClass="translation">
                    <form:input path="pid" type="hidden"></form:input>
                    <form:input path="locale" type="hidden" value="pt" />

                    <div class="form-group col-xs-12">
                        <label class="control-label col-xs-3"><fmt:message
                                key="addproblem.name" /></label>
                        <div class="col-xs-8">
                            <form:input cssClass="form-control" path="title" value="${problem.titlePt}" />
                        </div>
                    </div>

                    <div class="form-group col-xs-12">
                        <label class="control-label col-xs-3"><fmt:message
                                key="addproblem.description" /></label>
                        <textarea class="form-control" name="description"
                                  id="codePt" rows="15"> ${problem.descriptionPt} </textarea>
                    </div>

                    <div class="form-group col-xs-12">
                        <label class="control-label col-xs-3"><fmt:message
                                key="addproblem.input" /></label>
                        <textarea class="form-control in" name="input"
                                  id="inputPt" rows="15"> ${problem.inputPt} </textarea>
                    </div>

                    <div class="form-group col-xs-12">
                        <label class="control-label col-xs-3"><fmt:message
                                key="addproblem.output" /></label>
                        <textarea class="form-control out" name="output"
                                  id="outputPt" rows="15"> ${problem.outputPt} </textarea>
                    </div>

                    <div class="form-group col-xs-12">
                        <label class="control-label col-xs-3"><fmt:message
                                key="addproblem.comm" /></label>
                        <textarea class="form-control in " name="comments"
                                  id="hintPt" rows="15"> ${problem.commentsPt} </textarea>
                    </div>

                    <div class="col-xs-12">
                        <div class="form-actions pull-right ">
                            <input class="btn btn-primary confirm-message" type="submit" name="but"
                                   value='<spring:message code="button.send" />'
                                   data-confirm-title='<spring:message code="message.title"/>'
                                   data-confirm-message='<spring:message code="message.translation.pending"/>'
                                   data-confirm-type="message"
                                   data-redirect="/24h/problems.xhtml" />
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/jsp/general/confirmmessage.jsp"%>

<script type="text/javascript"
src="<c:url value="/js/WYSIWYG/source.js" />"></script>

<script>
    $(function () {
        $("form.translation").each(function () {
            $(this).submit(function (e) {
                e.preventDefault();
                $(this).ajaxSubmit();
            });
        });

        $(".nav-tabs li").on("click", function () {
            $(".tab-pane").each(function () {
                $(this).css("position", "static");
            });
        });
    });

    $("[data-toggle='tooltip']").tooltip();
</script>