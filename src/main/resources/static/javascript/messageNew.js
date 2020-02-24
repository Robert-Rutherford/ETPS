"use strict";

$(document).ready(function () {

    // Re-usable variables
    let inbox = $('#inbox');
    let outbox = $('#outbox');
    let title = $('#reel-title');
    let deleteMsg = $("#msgDelete");
    let checkboxes = $(".msg-checkbox");

    // Set display to none on load
    outbox.hide();
    deleteMsg.hide();



    $("#myonoffswitch").change(function () {
        // Change reel title
        if (title.text().toLowerCase() === "outgoing messages") {
            title.html("Received Messages");
        } else {
            title.html("Outgoing Messages");
        }

        // Two methods to toggle both elements together
        // $("#inbox,#outbox").toggle(); // method 1
        inbox.add(outbox).toggle('fast'); // method 2

    });

    function checkedMessages() {
        let boxesChecked = false;
        checkboxes.each(function() {
            if ($(this).is(":checked")) {
                boxesChecked = true;
            }
        });

        if (boxesChecked === true) {
           deleteMsg.show();
        } else {
            deleteMsg.hide();
        }
    }

    checkboxes.change(function () {
        checkedMessages();
    })
});
