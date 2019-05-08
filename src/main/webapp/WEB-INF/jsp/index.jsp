<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page buffer="16kb" autoFlush="true" %>

<h2 class="postheader">
    <spring:message code="pagehdr.welcomemsg"/>
</h2>
<!-- /article-content -->
<div class="row">
    <div class="col-xs-12">
        <c:if test="${message != null}">
            <div class="alert alert-success alert-dismissable fade in">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                <i class="fa fa-check"></i><spring:message code="${message}"/>
            </div>
        </c:if>

        <authz:authorize access="isAuthenticated()">
            <form:form method="post" commandName="entry">
                <div class="form-group">
                    <label class="control-label"><fmt:message key="entry.text"/></label>
                    <form:textarea id="text" maxlength="255" cssClass="form-control"
                                   path="text"/>
                    <div>
                        <span class="label label-danger"><form:errors path="text"/></span>
                    </div>
                </div>
                <div class="margin-top-05 form-actions pull-left">
                    <c:forEach items="${emoties}" var="emot">
                        <a href="javascript:insertText('${emot.key}');">${emot.value}</a>&nbsp;
                    </c:forEach>
                </div>
                <div class="margin-top-05 form-actions pull-right">
                    <input class="btn btn-primary" type="submit" name="submit"
                           id="submit"
                           value="<fmt:message key="judge.register.submit.value"/>"/>
                </div>
            </form:form>
        </authz:authorize>
    </div>
</div>
<div class="row">
    <div class="col-xs-5 col-xs-offset-7 filter-follow">
        <ul class="list-inline pull-right">
            <li><a href="javascript:displayTableReload('');"><spring:message
                    code="link.entry.user"/></a></li>
            <authz:authorize access="isAuthenticated()">
                <li><a href="javascript:displayTableReload('?entries=following');"><spring:message
                        code="link.entry.fallowing"/></a></li>
            </authz:authorize>
            <li><a href="javascript:displayTableReload('?entries=cojboard');"><spring:message
                    code="link.entry.cojboard"/></a></li>
        </ul>
    </div>

    <div id="display-table-container" data-reload-url="/tables/entries.xhtml" class="col-xs-12"></div>
</div>
<script>
    $(document).ready(function () {
        $('textarea#text').maxlength({
            alwaysShow: true,
            threshold: 10,
            warningClass: "label label-primary",
        });
    });


    function reply(text) {
        insertText(text);
        $('#reply').attr('checked', 'checked');
    }

    function insertText(text) {
        $('#text').val($('#text').val() + text);
    }

    $(document).ready(displayTableReload("?page=1"));
</script>
<!-- Matomo -->
<script type="text/javascript">
    var _paq = _paq || [];
    /* tracker methods like "setCustomDimension" should be called before "trackPageView" */
    _paq.push(['trackPageView']);
    _paq.push(['enableLinkTracking']);
    (function() {
        var u="//piwik.uci.cu/";
        _paq.push(['setTrackerUrl', u+'piwik.php']);
        _paq.push(['setSiteId', '68']);
        var d=document, g=d.createElement('script'), s=d.getElementsByTagName('script')[0];
        g.type='text/javascript'; g.async=true; g.defer=true; g.src=u+'piwik.js'; s.parentNode.insertBefore(g,s);
    })();
</script>
<!-- End Matomo Code -->

<script type="text/javascript" src="<c:url value="/js/admin/utility.js" />"></script>
<link href="/css/bootstrap-dialog.min.css" rel="stylesheet" type="text/css"/>
<script src="/js/bootstrap-dialog.min.js"></script>

<script>
    var i18n = {};
    i18n.title = "<spring:message code="message.confirm.hdr.aprobation.entry"/>";
    i18n.message = "<spring:message code="message.confirm.aprobation.entry"/>";
    i18n.btn_accept = "<spring:message code="btn.text.accept"/>";
</script>
