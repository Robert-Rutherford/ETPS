"use strict";


$(document).ready(function() {
let momentDate ;

    function momentA() {

        $('.timeStamp').each(function() {
            momentDate = moment($(this).html()).format('MMMM Do YYYY, h:mm:ss a');
            $(this).html(momentDate)
            }
        )
    }

momentA();
});