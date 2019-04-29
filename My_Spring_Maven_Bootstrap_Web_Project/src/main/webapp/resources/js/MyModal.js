;
const MyModal = {
    open: function (paramObj) {
        return $.when($(paramObj).jmodal());
    },
    openMultiple: function (paramObj) {
        return $.when($(paramObj).jmodal({
            closeExisting: false
        }));
    },
    close: function (paramObj) {
        paramObj = paramObj || $('a[rel="jmodal:close"]:visible');
        return $(paramObj).trigger('click');
    }
};

$.jmodal.defaults = {
    closeExisting: true, // Close existing modals. Set this to false if you need to stack multiple modal instances.
    escapeClose: false, // Allows the user to close the modal by pressing `ESC`
    clickClose: false, // Allows the user to close the modal by clicking the overlay
    closeText: 'Close', // Text content for the close <a> tag.
    closeClass: '', // Add additional class(es) to the close <a> tag.
    showClose: true, // Shows a (X) icon/link in the top-right corner
    modalClass: "jmodal overflow-visible", // CSS class added to the element being displayed in the modal.
    blockerClass: 'jquery-modal', // CSS class added to the overlay (blocker).
    spinnerHtml: null, // HTML appended to the default spinner during AJAX requests.
    showSpinner: false, // Enable/disable the default spinner during AJAX requests.
    fadeDuration: 300, // Number of milliseconds the fade transition takes (null means no transition)
    fadeDelay: 0
// Point during the overlay's fade-in that the modal begins to fade in (.5 = 50%, 1.5 = 150%, etc.)
};
$(document).ready(function () {
    $(document).on('keydown', function (event) {
        if (event.key === 'Escape' && $('.jquery-modal:visible').length > 0) {
            event.stopPropagation();
            MyModal.close();
        }
    });
});
/**
 * <pre>
 *  Naming conflict with Bootstrap: Bootstrap's modal uses the same $.modal namespace.
 *  If you want to use jquery-modal with Bootstrap, the simplest solution is to manually modify the name of this plugin.
 *
 *  namespace 변경때문에 jquery.modal.js, jquery.modal.css가 변경되었다.
 *  modal to jmodal (Case sensitive, Whole word)
 *
 * </pre>
 */
