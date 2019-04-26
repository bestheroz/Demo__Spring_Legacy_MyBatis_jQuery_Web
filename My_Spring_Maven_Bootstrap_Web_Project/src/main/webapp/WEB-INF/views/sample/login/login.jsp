<%@ page session="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/include/include.jsp" %>

<my:html cookie="YES" validator="YES">
    <body>
    <div class="page-header text-center">
        <h2>
            Bestheroz's Spring-Maven-Bootstrap Web
            <small>ver.190319</small>
        </h2>
    </div>
    <div class="jumbotron">
        <div class="row">
            <div class="col-sm-5 col-sm-offset-3">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label for="memberId" class="col-sm-5 control-label">아이디</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control input-sm" id="memberId" title="아이디" placeholder="아이디를 입력하세요." maxlength="9" required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="memberPw" class="col-sm-5 control-label">비밀번호</label>
                        <div class="col-sm-7">
                            <input type="password" class="form-control input-sm" id="memberPw" title="비밀번호" placeholder="비밀번호를 입력하세요." maxlength="20" required/>
                        </div>
                    </div>
                    <div class="col-sm-offset-3">
                        <div class="btn-group btn-group-justified" role="group" aria-label="...">
                            <div class="btn-group" role="group" style="width: 25%;">
                                <button type="button" class="btn btn-default" onclick="clickSaveId();">
                                    <i class="fa fa-check-square-o" aria-hidden="true" id="save_id" title="ID 저장"></i>
                                    ID 저장
                                </button>
                            </div>
                            <div class="btn-group" role="group" style="width: 75%;">
                                <button type="button" id="btnSave" class="btn btn-default" onclick="proc();">
                                    <i class="fa fa-sign-in" aria-hidden="true"></i>
                                    로그인
                                </button>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-offset-2" style="margin-top: 10px;">
                        <p class="text-muted text-center" style="font-size: 16px;">
                            테스트 [ ID / Password ] => [
                            <strong>1 / 1</strong>
                            ] 입니다.
                        </p>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <my:footer/>
    </body>
    <script data-for="ready">
        $(document).ready(function () {
            $('#memberPw, #memberId').on('keydown', function (event) {
                if (event.keyCode === 13) {
                    proc();
                }
            });
            let savedLoginId = MyCookie.getCookie("savedLoginId");
            if (savedLoginId !== null) {
                $('#memberId').val(savedLoginId);
                $('#save_id').removeClass('fa-square-o').addClass('fa-check-square-o');
            }
        });
    </script>
    <script>
        function proc() {
            if (MyValidator.validate($('form.form-horizontal'), true) !== null) {
                return;
            }
            let param = {
                memberId: $('#memberId').val(),
                memberPw: CryptoJS.SHA512($('#memberPw').val()).toString()
            };
            MyAjax.excute('${CONTEXT_PATH}/sample/login/loginProcess.json', param, {
                autoResultFunctionTF: false
            }).done(function (response) {
                if (!_.startsWith(response.responseCode, 'S')) {
                    alert(response.responseMessage);
                    return;
                }
                if ($('#save_id').hasClass('fa-check-square-o')) {
                    MyCookie.setCookie("savedLoginId", $('#memberId').val(), 30);
                } else {
                    MyCookie.removeCookie("savedLoginId");
                }
                window.location.reload(true);
            });
        }

        function clickSaveId() {
            if ($('#save_id').hasClass('fa-check-square-o')) {
                $('#save_id').addClass('fa-square-o').removeClass('fa-check-square-o');
            } else {
                $('#save_id').removeClass('fa-square-o').addClass('fa-check-square-o');
            }
        }
    </script>
    <script defer="defer" src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/3.1.9-1/crypto-js.min.js"></script>

</my:html>