;
const MyMaxlength = {
    init: (targetObj) => {
        targetObj = targetObj || $(document);
        $(targetObj).find('[maxlength]').each(() => {
            $(this).maxlength({
                threshold: $(this).attr('maxlength') - 1,
                validate: true
            });
        });
    }
};
jQuery(($) => {
    MyMaxlength.init();
});