//Funcion auxiliar para adicionar comportamiento ajax al displaytag de jsp. 
//Solo temporal hasta que se logre sustituir el displaytag por una solucion ajax genuina, que no sea un parche.
//ordenamiento basado en http://kevin.mudrick.com/2009/11/23/ajax-loading-sorting-and-paginating-of-displaytag-tables/
function displayTableReload(page) {
	var pag = null;
	if (page === undefined) {
		pag = $("#filter-form").find(":input").filter(function() {
			return this.value;
		}).serialize();
	} else
		pag = page.charAt(0) == '?' ? page.substring(1) : page;
	var displayUrl = $("#display-table-container").attr("data-reload-url");
	$
			.ajax({
				url : displayUrl,
				dataType : "html",
				data : pag,
				beforeSend : function() {
					$("#display-table-container")
							.html(
									"<br><center class='text-muted' ><i class='fa fa-circle-o-notch fa-3x fa-spin'></i></center>");
				},
				success : function(data) {
					if ($("#display-table-container").length == 0)
						$("html").html(data);
					$("#display-table-container").html(data);
					$("#display-table-container th.sortable").each(function() {
						// Iterate over each column header containing the
						// sortable class, so
						// we can setup overriding click handlers to load via
						// ajax, rather than
						// allowing the browser to follow a normal link
						$(this).click(function() {
							// "this" is scoped as the sortable th element
							var link = $(this).find("a").attr("href");
							displayTableReload(link);
							// Stop event propagation, i.e. tell browser not to
							// follow the clicked link
							return false;
						});
					});
				},
				error: function(data){
					$("#display-table-container").html("<br><center><i class='fa red fa-3x fa-warning'></i>&nbsp;Search error</center>");
				}
			});
};

function initStandardFilterForm() {

	$('#filter-button').click(function(event) {
		displayTableReload();
		event.preventDefault();
	});
	displayTableReload();
};