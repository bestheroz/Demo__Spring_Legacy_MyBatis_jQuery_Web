;
const MyModal = {
    open: (paramObj) => {
        return $.when($(paramObj).modal({
            backdrop: 'static',
            show: true
        }));
    },
    close: (paramObj) => {
        $(paramObj).modal('hide');
    }
};