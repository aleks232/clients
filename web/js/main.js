function pageInit(){
    $("#dialog").hide();
    $("#dialogFind").hide();
    var radioMap = {
        UL: {id: '#trUL', hideId: '#trIP'},
        IP: {id: '#trIP', hideId: '#trUL'}
    };
    $('input, select').styler();
    $(".dateBlock").datepicker({dateFormat: 'dd.mm.yy'});

    $("#opener").click(function () {
        $("#dialog").show();
        dialogOpen();
        $("#dialog").dialog("open");
    });
    $("#finder").click(function () {
        $("#dialogFind").show();
        dialogFindOpen();
        $("#dialogFind").dialog("open");
    });
    $('input:radio').change(
        function () {
            var value = $(this).val();
            $(radioMap[value].id).show();
            $(radioMap[value].hideId).hide();
        }
    );
}

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