$(function() {
    $('#messages').click(function() {
        $(this).fadeOut();
    });
    setTimeout(function() {
        $('#messages div.alert-info').fadeOut();
    }, 1000);
    setTimeout(function() {
        $('#messages div.alert-success').fadeOut();
    }, 1000);
});