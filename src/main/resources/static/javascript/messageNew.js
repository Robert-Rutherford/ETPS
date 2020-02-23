
$(document).ready(function() {

    var inbox = $('#inbox');
    var outbox = $('#outbox');

    $('.onoffswitch').click(function () {
        inbox.toggle();
        outbox.toggle();
    });
})
