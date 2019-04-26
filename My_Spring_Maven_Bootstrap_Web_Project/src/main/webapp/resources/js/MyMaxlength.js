;
const MyMaxlength = {
    init: function (targetObj) {
        targetObj = targetObj || $(document);
        $(targetObj).find('[maxlength]').each(function () {
            $(this).maxlength({
                threshold: $(this).attr('maxlength') - 1,
                validate: true
            });
        });
    }
};
$(document).ready(function () {
    MyMaxlength.init();
});