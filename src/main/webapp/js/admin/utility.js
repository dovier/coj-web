function confirm_delete(_route) {

    var dialog = new BootstrapDialog({
        message: '<div>' + i18n.message + '</div>',
        title: i18n.title,
        buttons: [{
            label: i18n.btn_cancel,
            action: function (dialogRef) {
                dialogRef.close();
            }
        },
            {
                label: i18n.btn_accept,
                cssClass: 'btn-primary',
                action: function () {
                    window.location = _route;
                }
            }]
    });
    dialog.realize();
    dialog.open();
    return false;
}

function confirm_update() {

    var dialog = new BootstrapDialog({
        message: '<div>' + i18n.message + '</div>',
        title: i18n.title,
        buttons: [{
            label: i18n.btn_accept,
            cssClass: 'btn-primary',
            action: function (dialogItself) {
                dialogItself.close();
            }
        }]
    });
    dialog.realize();
    dialog.open();
    return false;
}

var currentForm;
$("#rejudge").click(function () {
    currentForm = $(this).closest('form');
    var tr = $("#submission tr[class!='empty']").length - 1;

    var dialog = new BootstrapDialog({
        message: '<div>' + i18n.message + '</div>',
        title: i18n.title,
        buttons: [{
            label: i18n.btn_cancel,
            action: function (dialogRef) {
                dialogRef.close();
            }
        },
            {
                label: i18n.btn_accept,
                cssClass: 'btn-primary',
                action: function () {
                    /*//validar formulario
                    var ok = true;
                    var frm = document.filter-form;
                    if (isNaN(formulario.edad.value) == true || /^[1-9]\d$/.test(formulario.edad.value) == false) {
                        alert('Edad no valida');
                        todoCorrecto = false;
                    }
                    if (isNaN(formulario.altura.value) == true || formulario.altura.value <= 0 || formulario.altura.value >= 2.50) {
                        alert('Altura no valida');
                        todoCorrecto = false;
                    }
                    if (todoCorrecto == true) {
                        formulario.submit();
                    }*/
                    currentForm.submit();
                }
            }]
    });
    dialog.realize();
    dialog.open();
    return false;
});

$(".alert-dismissable").fadeTo(3000, 800).slideUp(800, function () {
    $(".alert-dismissable").alert('close');
});
