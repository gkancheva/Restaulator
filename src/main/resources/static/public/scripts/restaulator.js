$(function() {
    $('#messages').click(function() {
        $(this).fadeOut();
    });
    setTimeout(function() {
        $('#messages div.alert-info').fadeOut();
    }, 2000);
    setTimeout(function() {
        $('#messages div.alert-success').fadeOut();
    }, 2000);
});