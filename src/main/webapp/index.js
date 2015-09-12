$(document).ready(function () {
    $('#tabela').hide();
    $('#erro').hide();
});

$('#btn-buscar-id').click(function () {
    var userId = $('#id').val();
    if (!userId) {
        return;
    }
    $.getJSON("user/"+userId, function (data) {
        var items = [];
        items.push("<tr> <th>" + data.id + "</th><td>" + data.name + "</td> </tr>");
        $('#tabela').show();
        $('#erro').hide();
        $('#tabela-resultado').html(items);
    }).fail(function() {
        $('#tabela').hide();
        $('#erro').show();
    });
});

$('#btn-buscar-todos').click(function () {
    $.getJSON("user", function (data) {
        var items = [];
        $.each(data, function (key, val) {
            items.push("<tr> <th>" + val.id + "</th><td>" + val.name + "</td> </tr>");
        });
        $('#tabela').show();
        $('#erro').hide();
        $('#tabela-resultado').html(items);
    }).fail(function() {
        $('#tabela').hide();
        $('#erro').show();
    });
});

$('#btn-limpar').click(function () {
    $('#tabela').hide();
    $('#erro').hide();
});