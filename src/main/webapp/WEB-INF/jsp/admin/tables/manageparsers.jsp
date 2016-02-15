<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer="16kb" autoFlush="true"%>

<%--property="site"--%>
<display:table id="site" name="sites" class="volume" requestURI=""
	defaultsort="0" defaultorder="ascending">
	<display:column   titleKey="tablehdr.site" >
		<a href="${site.url}">${site.site}</a>
	</display:column>
	<display:column titleKey="tablehdr.enabled">
		<c:choose>
			<c:when test="${site.enabled == true}">
				<button id="e${site.sid}" class="btn btn-success enable mybutton"
					type="button"><fmt:message key="page.general.yes" />

				</button>
			</c:when>
			<c:otherwise>
				<button id="e${site.sid}" class="btn btn-danger enable mybutton"
					type="button"><fmt:message key="page.general.no" /></button>
			</c:otherwise>
		</c:choose>
	</display:column>
	<display:column titleKey="tablehdr.actions">
		<c:choose>
			<c:when test="${site.enabled == true}">
				<%--<button id="p${site.sid}" class="btn btn-primary parse mybutton"
					type="button">
					<fmt:message key="tablehdr.parse.uppercase" />
				</button>--%>
				<%--<button id="e${site.sid}" class="btn btn-success enable mybutton"
						type="button"><fmt:message key="page.general.yes" /></button>--%>
				<button id="p${site.sid}" class="btn btn-link parse mybutton" title="<fmt:message key="tablehdr.parse.uppercase" />" data-toggle="tooltip">
					<i class="fa fa-search">

					</i>
				</button>
			</c:when>
			<c:otherwise>
				<%--<button id="p${site.sid}"
					class="btn btn-primary disabled parse mybutton" type="button">
					<fmt:message key="tablehdr.parse.uppercase" />
				</button>--%>
				<%--<button id="e${site.sid}" class="btn btn-danger enable mybutton"
						type="button"><fmt:message key="page.general.no" /></button>--%>
				<button id="p${site.sid}" class="btn btn-link disabled parse mybutton" title="<fmt:message key="tablehdr.parse.uppercase" />" data-toggle="tooltip">
					<i class="fa fa-search">

					</i>
				</button>
			</c:otherwise>
		</c:choose>
		<span id="g${site.sid}" class="label label-success hide notif"><spring:message
				code="text.good" /></span>
		<span id="b${site.sid}" class="label label-danger hide notif"><spring:message
				code="text.error" /></span>
		<span id="ajax-loader${site.sid}" class="hide label notif myloader"><img
			src="<c:url value="/images/ajax-loader-6.gif"/>" /></span>
	</display:column>
</display:table>

<script>
	$("[data-toggle='tooltip']").tooltip();
</script>

<script>
	$(document).ready(function() {
		var yes_text = "<fmt:message key="page.general.yes" />";
		var no_text = "<fmt:message key="page.general.no" />";

		var parseifactive = function() {
			if (!$(this).hasClass("disabled")) {
				$(this).click();
			}
		}

		$("#parseall").click(function() {
			$(".parse").each(parseifactive);
		});

		$(".enable").click(function() {
			var elem = $(this);
			var sid = elem.attr("id").substr(1);
			
			elem.toggleClass("btn-success");
			elem.toggleClass("btn-danger");
			elem.toggleClass("disabled");
			
			$("#g" + sid).addClass("hide");
			$("#b" + sid).addClass("hide");
			$("#ajax-loader" + sid).addClass("hide");

			$("#p" + sid).toggleClass("disabled");

			$.ajax({
				url : "/admin/manageparsers/enable.xhtml",
				type : 'POST',
				data : {
					"sid" : sid
				},
				success : function(data) {
					elem.toggleClass("disabled");
					
					if (elem.text() == yes_text) {
						elem.text(no_text);
					} else {
						elem.text(yes_text);
					}
				}
			});
		});

		$(".parse").click(function() {
			var elem = $(this);
			var sid = elem.attr("id").substr(1);
			
			elem.toggleClass("disabled");
			$("#g" + sid).addClass("hide");
			$("#b" + sid).addClass("hide");
			$("#ajax-loader" + sid).toggleClass("hide");
			
			$.ajax({
				url : "/admin/manageparsers/parse.json",
				type : 'POST',
				data : {
					"sid" : sid
				},
				success : function(data) {
					elem.toggleClass("disabled");
					$("#ajax-loader" + sid).toggleClass("hide");
					if (data == true) {
						$("#g" + sid).removeClass("hide");
						$("#b" + sid).addClass("hide");
					} else {
						$("#b" + sid).removeClass("hide");
						$("#g" + sid).addClass("hide");
					}
				}
			});
		});
	});
</script>