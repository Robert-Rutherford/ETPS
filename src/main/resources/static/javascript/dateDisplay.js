"use strict";

// This is using Moment.js to format the way the date is displayed to users
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