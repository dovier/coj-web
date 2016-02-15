<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript"
	src="<c:url value="/js/jquery.js" />">
	
</script>

<link rel="stylesheet" href="<c:url value="/css/confirm-message.css"/>"
	type="text/css" media="screen" />

<h2 class="postheader"><fmt:message key="page.general.admin.header" /><spring:message code="text.managecorrections" /></h2>

<button id="parseall" class="btn btn-primary " type="button">
	<fmt:message key="text.correctall" />
</button>
<div class="postcontent">
	<table id="site" class="volume">
		<thead>
			<tr>
				<th><spring:message code="tablehdr.correction"/></th>
				<th class="tablehdr.actions"><spring:message code="tablehdr.actions"/></th>
			</tr>
		</thead>
		<tbody>
			<tr class="odd">
				<td><spring:message code="tableval.correctstats24h"/></td>
				<td>
					<span id="tstats24h" class="time"></span>
					<button id="pstats24h" class="btn btn-primary parse mybutton"
						type="button"><spring:message code="btn.correct"/></button>
					 <span id="gstats24h" class="label label-success hide notif"><spring:message code="text.good"/></span>
					 <span id="ajax-loaderstats24h" class="hide label notif myloader">
					 	<img src="/images/ajax-loader-6.gif"/>
					 </span>
				</td>
			</tr>
			<tr class="even">
				<td><spring:message code="tableval.correctstatscontest"/></td>
				<td>
					<span id="tstatscontest" class="time"></span>
					<button id="pstatscontest" class="btn btn-primary parse mybutton"
						type="button"><spring:message code="btn.correct"/></button>
					 <span id="gstatscontest" class="label label-success hide notif"><spring:message code="text.good"/></span>
					 <span id="ajax-loaderstatscontest" class="hide label notif myloader">
					 	<img src="/images/ajax-loader-6.gif"/>
					 </span>
				</td>
			</tr>
			<tr class="odd">
				<td><spring:message code="tableval.correctuserstats24h"/></td>
				<td>
					<span id="tuserstats24h" class="time"></span>
					<button id="puserstats24h" class="btn btn-primary parse mybutton"
						type="button"><spring:message code="btn.correct"/></button>
					 <span id="guserstats24h" class="label label-success hide notif"><spring:message code="text.good"/></span>
					 <span id="ajax-loaderuserstats24h" class="hide label notif myloader">
					 	<img src="/images/ajax-loader-6.gif"/>
					 </span>
				</td>
			</tr>
			<tr class="even">
				<td><spring:message code="tableval.correctuserconteststats"/></td>
				<td>
					<span id="tstatsusercontest" class="time"></span>
					<button id="pstatsusercontest" class="btn btn-primary parse mybutton"
						type="button"><spring:message code="btn.correct"/></button>
					 <span id="gstatsusercontest" class="label label-success hide notif"><spring:message code="text.good"/></span>
					 <span id="ajax-loaderstatsusercontest" class="hide label notif myloader">
					 	<img src="/images/ajax-loader-6.gif"/>
					 </span>
				</td>
			</tr>
			<tr class="odd">
				<td><spring:message code="tableval.correctawards"/></td>
				<td>
					<span id="tachievements" class="time"></span>
					<button id="pachievements" class="btn btn-primary parse mybutton"
						type="button"><spring:message code="btn.correct"/></button>
					 <span id="gachievements" class="label label-success hide notif"><spring:message code="text.good"/></span>
					 <span id="ajax-loaderachievements" class="hide label notif myloader">
					 	<img src="/images/ajax-loader-6.gif"/>
					 </span>
				</td>
			</tr>
			<tr class="even">
				<td><spring:message code="tableval.correctuserpoints"/></td>
				<td>
					<span id="tpoints" class="time"></span>
					<button id="ppoints" class="btn btn-primary parse mybutton"
						type="button"><spring:message code="btn.correct"/></button>
					 <span id="gpoints" class="label label-success hide notif"><spring:message code="text.good"/></span>
					 <span id="ajax-loaderpoints" class="hide label notif myloader">
					 	<img src="/images/ajax-loader-6.gif"/>
					 </span>
				</td>
			</tr>
			<tr class="even">
				<td><spring:message code="tableval.correctsharedfiles"/></td>
				<td>
					<span id="tshared" class="time"></span>
					<button id="pshared" class="btn btn-primary parse mybutton"
						type="button"><spring:message code="btn.correct"/></button>
					 <span id="gshared" class="label label-success hide notif"><spring:message code="text.good"/></span>
					 <span id="ajax-loadershared" class="hide label notif myloader">
					 	<img src="/images/ajax-loader-6.gif"/>
					 </span>
				</td>
			</tr>
			<tr class="odd">
				<td><spring:message code="tableval.correctproblemstats24h"/></td>
				<td>
					<span id="tproblemstats24h" class="time"></span>
					<button id="pproblemstats24h" class="btn btn-primary parse mybutton"
						type="button"><spring:message code="btn.correct"/></button>
					 <span id="gproblemstats24h" class="label label-success hide notif"><spring:message code="text.good"/></span>
					 <span id="ajax-loaderproblemstats24h" class="hide label notif myloader">
					 	<img src="/images/ajax-loader-6.gif"/>
					 </span>
				</td>
			</tr>
		</tbody>
	</table>
	<br />
</div>

<script>
	$(document).ready(function() {
		var parseifactive = function() {
			if (!$(this).hasClass("disabled")) {
				$(this).click();
			}
		}

		$("#parseall").click(function() {
			$(".parse").each(parseifactive);
		});

		$(".parse").click(function() {
			var elem = $(this);
			var value = elem.attr("id").substr(1);
			var init_time = new Date();
			elem.toggleClass("disabled");
			$("#g" + value).addClass("hide");
			$("#t" + value).addClass("hide");
			$("#ajax-loader" + value).toggleClass("hide");
			$.ajax({
				url : "<c:url value="/admin/correct/"/>" +  value + ".xhtml",
				type : 'POST',
				success : function(data) {
					elem.toggleClass("disabled");
					$("#ajax-loader" + value).toggleClass("hide");
					$("#g" + value).removeClass("hide");
					$("#t" + value).removeClass("hide");
					var end_time = new Date();
					$("#t" + value).html(((end_time - init_time)/1000).toFixed(1) + "s");
				}
			});
		});
	});
</script>
