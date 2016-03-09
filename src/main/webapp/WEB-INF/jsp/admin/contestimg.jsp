<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!--link rel="stylesheet" href="../Style/style.css" type="text/css" media="screen" /-->
<script type="text/javascript"
        src="<c:url value="/js/WYSIWYG/source.js" />"></script>

<h2 class="postheader">
    <fmt:message key="page.general.admin.header"/>: <fmt:message key="page.managecontest.link.img"/>
</h2>

<div class="postcontent">
    <ul class="nav nav-pills">
        <li><a
                href="<c:url value="managecontest.xhtml?cid=${contest.cid}"/>"><fmt:message
                key="page.managecontest.link.mc"/></a></li>
        <li><a
                href="<c:url value="contestglobalflags.xhtml?cid=${contest.cid}"/>"><fmt:message
                key="page.managecontest.link.gf"/></a></li>
        <li><a
                href="<c:url value="globalsettings.xhtml?cid=${contest.cid}"/>"><fmt:message
                key="page.managecontest.link.gs"/></a></li>
        <li><a
                href="<c:url value="contestproblems.xhtml?cid=${contest.cid}"/>"><fmt:message
                key="page.managecontest.link.mp"/></a></li>
        <c:if test="${eproblem}">
            <li><a
                    href="<c:url value="contestproblemcolors.xhtml?cid=${contest.cid}"/>"><fmt:message
                    key="page.managecontest.link.mpc"/></a></li>
        </c:if>
        <li><a
                href="<c:url value="importicpcusers.xhtml?cid=${contest.cid}"/>"><fmt:message
                key="page.managecontest.link.iiu"/></a></li>
        <li><a href="<c:url value="baylorxml.xhtml?cid=${contest.cid}"/>"><fmt:message
                key="page.managecontest.link.bx"/></a></li>
        <li><a
                href="<c:url value="contestlanguages.xhtml?cid=${contest.cid}"/>"><fmt:message
                key="page.managecontest.link.ml"/></a></li>
        <li><a
                href="<c:url value="contestusers.xhtml?cid=${contest.cid}"/>"><fmt:message
                key="page.managecontest.link.mu"/></a></li>
        <li><a
                href="<c:url value="contestawards.xhtml?cid=${contest.cid}"/>"><fmt:message
                key="page.managecontest.link.aw"/></a></li>
        <li><a
                href="<c:url value="contestoverview.xhtml?cid=${contest.cid}"/>"><fmt:message
                key="page.managecontest.link.ov"/></a></li>
        <li class="active"><a
                href="<c:url value="contestimg.xhtml?cid=${contest.cid}"/>"><fmt:message
                key="page.managecontest.link.img"/></a></li>
    </ul>
    <br/>
    <c:if test="${message != null}">
        <div class="alert alert-success alert-dismissable fade in">
            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
            <i class="fa fa-check"></i><spring:message code="${message}"/>
        </div>
    </c:if>

    <form:form method="post" command="contest"
               enctype="multipart/form-data">
        <input name="cid" class="hidden" value="${contest.cid}">

        <div class="form-group col-xs-12">
            <div class="form-group">
                <c:forEach items="${images}" varStatus="loop">
                    <div id="${loop.index}-container" class="margin-top-05 col-xs-3">
                        <div class="img-thumbnail">
                            <span class="pull-right"> <a
                                    href="javascript:removeimage(${contest.cid},${loop.index},'${images[loop.index]}');">
                                <i class="fa fa-close"></i>
                            </a>
                            </span> <img class="img-responsive"
                                         src="/contests/images/${contest.cid}/${images[loop.index]}"/>
                        </div>
                    </div>
                </c:forEach>
                <div id="images" class="form-group margin-top-05"></div>
                <div class="form-group col-xs-12">
                    <div class="margin-top-05 col-xs-offset-10 form-actions">
                        <input class="btn btn-primary" type="button"
                               style="margin: right" id="add" onclick="javascript:addInput();"
                               value='<spring:message code="button.add"/>'/>
                    </div>
                </div>
            </div>
        </div>

        <br/>

        <div class="pull-right">
            <input type="submit" name="but" class="btn btn-primary"
                   value="<spring:message code="button.edit"/>"/>
            <a class="btn btn-primary" href="<c:url value="/admin/admincontests.xhtml"/>"><spring:message
                    code="button.close"/></a>
        </div>
        <br/>
    </form:form>
    <div class="clearfix"></div>
</div>

<script type="text/javascript">
    var i18n = {};
    i18n.newimages = "<spring:message code="page.contestimg.newimages"/>";
    function addInput() {
        var idx = $(".model-image").length;
        var string = (idx >= 1) ? "" : i18n.newimages;
        var html = "<div class=\"model-image margin-top-05 col-xs-12\">"
                + "<label class=\"control-label col-xs-3\">"
                + string
                + "</label>"
                + "<div class=\"col-xs-8\"><input id = \"img" + idx + "\" name= \"img" + idx + "\" type=\"file\" class=\"image file\" data-show-upload=\"false\" /></div>"
                + "</div>";
        $("div#images").append(html);
        $(".model-image:last .file").fileinput({
            maxFileSize: 5000000,
            allowedFileTypes: ['image'],
            removeClass: "btn btn-default",
            previewClass: 'file_preview',
            removeLabel: "<spring:message code="message.deleteimage"/>",
            previewFileType: 'image',
            msgProgress: 'Loading {percent}%',
            browseClass: "btn btn-primary",
            browseLabel: "<spring:message code="message.pickimage"/>",
            browseIcon: '<i class="fa fa-picture-o"></i>&nbsp;',
            removeIcon: '<i class="fa fa-trash"></i>'
        });
    }
    ;

    $(function () {
        $(".dataset,.file").fileinput({
            maxFileSize: 500,
            allowedFileExtensions: ['png', 'jpg'],
            removeClass: "btn btn-default",
            removeLabel: "<spring:message code="message.deleteimage"/>",
            previewFileType: 'image',
            msgProgress: 'Loading {percent}%',
            browseClass: "btn btn-primary",
            browseLabel: "<spring:message code="message.pickimage"/>",
            browseIcon: '<i class="fa fa-picture-o"></i>&nbsp;',
            removeIcon: '<i class="fa fa-trash"></i>'
        });

        addInput();
    });

    $("[data-toggle='tooltip']").tooltip();

    $(function () {
        $("[data-toggle='tooltip']").tooltip();
    });

    function removeimage(cid, containerId, image) {
        $.ajax({
            type: "GET",
            url: "/admin/removeimage.json",
            data: {
                "cid": cid,
                "image": image
            },
            dataType: 'text',
            success: function (data) {

                $('#' + containerId + '-container').remove();
            }
        });

    }
    ;
</script>

<script src="/js/admin/utility.js"></script>