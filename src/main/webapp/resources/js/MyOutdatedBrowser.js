;
const MyOutdatedBrowser = {
    addLoadEvent: function (func) {
        let oldonload = window.onload;
        if (typeof window.onload != 'function') {
            window.onload = func;
        } else {
            window.onload = function () {
                if (oldonload) {
                    oldonload();
                }
                func();
            }
        }
    }
};
/**
 * <pre>
 * Lower Than (&lt;):
 *
 * IE11, borderImage
 * IE10, transform (Default property)
 * IE9, boxShadow
 * IE8, borderSpacing
 * </pre>
 */
$(document).ready(function () {
    $('body').append('<div id=\"outdated\"></div>');
    MyOutdatedBrowser.addLoadEvent(function () {
        outdatedBrowser({
            bgColor: '#f25648',
            color: '#ffffff',
            lowerThan: 'transform',
            languagePath: '/resources/plugin/outdatebrowser/lang/ko.html'
        });
    });
});