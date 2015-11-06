function confirm_delete(_route){   

        var dialog = new BootstrapDialog({
            message: '<div>'+i18n.message+'</div>',
            title: i18n.title,
            buttons: [{
                    label: i18n.btn_cancel,
                    action: function(dialogRef) {
                        dialogRef.close();
                    }
                },
                {
                    label: i18n.btn_accept,
                    cssClass: 'btn-primary',
                    action: function() {
                        window.location = _route;
                    }
                }]
        });
        dialog.realize();
        dialog.open();
        return false;
}