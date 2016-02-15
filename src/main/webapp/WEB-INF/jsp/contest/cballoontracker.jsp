<%@include file="/WEB-INF/jsp/include/include.jsp" %>


<meta content="60" http-equiv="refresh">


<h2 class="postheader" style="clear: both">
    <a class="linkheader"
       href="<c:url value="contestview.xhtml?cid=${contest.cid}"/>">${contest.name}</a>
    <br>
    <spring:message code="pagetit.balloontracker"/>
</h2>

<div class="row postcontent">
    <div class="col-xs-12">
        <c:choose>
            <c:when test="${contest.repointing == true}">
                <div class="label label-info">
                    <i class="fa fa-info-circle"></i>The contest is being repointed,
                    please wait a minute and try again
                </div>
            </c:when>
            <c:otherwise>
                <div class="pull-right">
                    <form class="form-inline" id="filter-form">
                        <input type="hidden" name="cid" value="${contest.cid}"/> <select
                            class="form-control" id="group" name="group">
                        <option value=""><spring:message code="messages.general.all"/></option>
                        <c:forEach items="${groups}" var="igroup">
                            <c:if test="${igroup == selGroup}">
                                <option selected="selected" value="${igroup}">${igroup}</option>
                            </c:if>
                            <c:if test="${igroup != selGroup}">
                                <option value="${igroup}">${igroup}</option>
                            </c:if>
                        </c:forEach>
                    </select> <input type="hidden" value="${contest.cid}" name="cid"/> <input
                            class="btn btn-primary" type="submit" id="filter-button"
                            value="<spring:message code="button.filter"/>"/>
                    </form>
                </div>

                <div id="display-table-container"
                     data-reload-url="/tables/cballoontracker.xhtml"></div>

            </c:otherwise>
        </c:choose>
    </div>
</div>
<script>
    function mark(sid) {
        $.ajax({
            type: "GET",
            url: "/contest/markballoon.json",
            data: "sid=" + sid,
            dataType: 'text',
            success: function (data) {
                $("#" + sid).parents("tr").remove();
            }
        });
    }

    $(initStandardFilterForm);
</script>
