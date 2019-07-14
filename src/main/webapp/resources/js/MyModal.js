;
const MyModal = {
    open: function (paramObj) {
        return $.when($(paramObj).modal({
            backdrop: 'static',
            show: true
        }));
    },
    close: function (paramObj) {
        $(paramObj).modal('hide');
    }
};