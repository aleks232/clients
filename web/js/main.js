function dialogOpen() {
    $("#dialog").dialog({
        autoOpen: false,
        show: {
            effect: "blind",
            duration: 1000
        },
        hide: {
            effect: "explode",
            duration: 1000
        },
        modal: true,
        height: 250,
        width: 770,
        close: function () {
            $('#createClient').each(function () {
                this.reset();
            });
        },
        buttons: {
            "Добавить": function () {
                $('#createClient').submit();
                $(this).dialog("close");
            },
            'Отмена': function () {
                $(this).dialog("close");
            }
        }
    });
}
function dialogFindOpen() {
    $("#dialogFind").dialog({
        autoOpen: false,
        show: {
            effect: "blind",
            duration: 1000
        },
        hide: {
            effect: "explode",
            duration: 1000
        },
        modal: true,
        height: 250,
        width: 770,
        close: function () {
            $('#findClient').each(function () {
                this.reset();
            });
        },
        buttons: {
            "Искать": function () {
                $('#findClient').submit();
                $(this).dialog("close");
            },
            'Отмена': function () {
                $(this).dialog("close");
            }
        }
    });
}