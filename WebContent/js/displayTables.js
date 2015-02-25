//Funcion auxiliar para adicionar comportamiento ajax al displaytag de jsp. 
//Solo temporal hasta que se logre sustituir el displaytag por una solucion ajax genuina, que no sea un parche.
//ordenamiento basado en http://kevin.mudrick.com/2009/11/23/ajax-loading-sorting-and-paginating-of-displaytag-tables/
function displayTableReload(page) {
	var pag = page.charAt(0) == '?'?page.substring(1):page;
	
	var url = $("#display-table-container").data("reloadUrl");
	$.ajax({
		url : url,
		dataType : "html",
		data : pag,
		beforeSend : function() {
			// TODO: Poner algo aqui para que se muestre que se esta recargando
			// la pagina
		},
		success : function(data) {
			if ($("#display-table-container").length == 0)
				$("html").html(data);
			$("#display-table-container").html(data);
			 $("#display-table-container th.sortable").each(function() {
			        // Iterate over each column header containing the sortable class, so
			        // we can setup overriding click handlers to load via ajax, rather than
			        // allowing the browser to follow a normal link
			        $(this).click(function() {
			          // "this" is scoped as the sortable th element
			          var link = $(this).find("a").attr("href");
			          displayTableReload(link);
			          // Stop event propagation, i.e. tell browser not to follow the clicked link
			          return false;
			        });
			      });
		}
	});
};