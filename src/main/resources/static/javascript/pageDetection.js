let resizeTimer;
let alertMessage = `Device detected: ${getMobileOperatingSystem()}\n
    ETPS is not designed for mobile usage\n
    * resize screen to a larger view *`;

let width = function getWindowWidth() { // Dynamically get window width
    return $(window).width();
};

if (width() < 1008) {
    alert(alertMessage);
}

/**
 * Determine the mobile operating system.
 * This function returns one of 'iOS', 'Android', 'Windows Phone', or 'unknown'.
 *
 * @returns {String}
 */
// var deviceAgent = navigator.userAgent.toLowerCase();
function getMobileOperatingSystem() {
    var userAgent = navigator.userAgent || navigator.vendor || window.opera;

    // Windows Phone must come first because its UA also contains "Android"
    if (/windows phone/i.test(userAgent)) {
        return "Windows Phone";
    }

    if (/android/i.test(userAgent)) {
        return "Android";
    }

    if (/iphone/i.test(userAgent)) {
        return 'iPhone';
    }

    if (/macintosh/i.test(userAgent)) {
        return 'Macintosh'
    }

    if (/CrOS/i.test(userAgent)) {
        return 'Chromebook';
    }

    return "unknown";
}

// function to perform on resize
function resizePop() {

    if (width() < 1008) {
        alert(alertMessage)
    }
}

window.onresize = function(){
    if (resizeTimer){
        clearTimeout(resizeTimer);
    }
    resizeTimer = setTimeout(function(){
        resizePop()
    }, 500);
};