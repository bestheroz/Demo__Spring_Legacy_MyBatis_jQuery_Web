;
const MyTextEditor = {
    defaultOptions: {
        lang: 'ko-KR',
        height: '250px',
        disableDragAndDrop: true,
        dialogsInBody: true,
        toolbar: [['style', ['style']], ['font', ['bold', 'strikethrough', 'underline', 'clear']], // [ 'fontname', [ 'fontname' ] ],
            ['fontsize', ['fontsize']], ['color', ['color']], ['para', ['ul', 'ol', 'paragraph']], ['table', ['table']], ['insert', ['link', 'picture', 'video']],
            ['view', ['undo', 'redo', 'codeview', 'fullscreen', 'help']]],
        useBase64DataUrlImage: true
    },
    init: function (targetObj) {
        targetObj = targetObj || $(document);
        $(targetObj).find('[data-texteditor="on"]').each(function () {
            let options = {};
            if (MyCommon.isNotEmpty($(this).attr('data-texteditor-height'))) {
                options['height'] = $(this).attr('data-texteditor-height') + 'px';
            }
            if ($(this).attr('data-texteditor-airmode') === 'true') {
                options['airMode'] = true;
            }
            MyTextEditor.instance(this, options);
        });
    },
    instance: function (targetObj, paramOptions) {
        // 서버 이미지 업로드 지원
        // if (!paramOptions.useBase64DataUrlImage && !MyCommon.isLocalhost()) {
        // paramOptions['callbacks'] = {
        // onImageUpload : function(files) {
        // let $summernote = $(this);
        // let formData = new FormData();
        // formData.append("file", files[0]);
        // MyAjax.excuteWithFile(CONTEXT_PATH + '/common/textEditor/imageUpload.proc', formData).done(function(response) {
        // // upload image to server and create imgNode...
        // $summernote.summernote('insertImage', url);
        // });
        // }
        // }
        // }
        $(targetObj).summernote(MyCommon.getOptions(MyTextEditor.defaultOptions, paramOptions));
    },
    getCode: function (targetObj) {
        return $(targetObj).summernote('code');
    },
    setCode: function (targetObj, code) {
        $(targetObj).summernote('code', code);
    },
    destroy: function (targetObj) {
        $(targetObj).summernote('destroy');
    },
    isEmpty: function (targetObj) {
        return $(targetObj).summernote('isEmpty');
    },
    getLength: function (targetObj) {
        return MyTextEditor.getCode(targetObj).length;
    },
    focus: function (targetObj) {
        $(targetObj).summernote('focus');
    },
    reset: function (targetObj) {
        $(targetObj).summernote('reset');
    },
    disable: function (targetObj, disableTF) {
        if (disableTF === undefined) {
            disableTF = true;
        }
        if (disableTF) {
            $(targetObj).summernote('disable');
        } else {
            $(targetObj).summernote('enable');
        }
    }
};
$(document).ready(function () {
    MyTextEditor.init();
});
// 드래그 방지에서 textarea와 충돌하기떄문에 드래그 방지소스는 아래와 같이 처리해줘야한다! 17-12-13 by bestheroz
// $(document).on('selectstart', ':not(.note-editor *)', function(e) {
// if ($(this).hasClass('note-editor')) {
// e.stopPropagation();
// } else {
// return false;
// }
// });
// $(document).on('dragstart', ':not(.note-editor *)', function(e) {
// if ($(this).hasClass('note-editor')) {
// e.stopPropagation();
// } else {
// return false;
// }
// });
