<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/include/include.jsp" %>

<my:html fileDownloader="YES">
    <my:menu/>
    <main role="main" class="flex-shrink-0">
        <div class="container">
            <div class="jumbotron text-center">
                <h1 class="display-4">ajax
                    <small>요청 하기</small>
                </h1>
                <hr class="my-4">
                <h4>목차</h4>
                <div class="list-group list-group-flush">
                    <a href="#list1" class="list-group-item list-group-item-action list-group-item-light"> 1. ajax - json </a>
                    <a href="#list2" class="list-group-item list-group-item-action list-group-item-light"> 2. ajax - html </a>
                    <a href="#list3" class="list-group-item list-group-item-action list-group-item-light"> 3. ajax - file upload(form data) </a>
                    <a href="#list4" class="list-group-item list-group-item-action list-group-item-light"> 4. ajax - file download </a>
                    <div id="list1"></div>
                </div>
            </div>
            <div class="card">
                <div class="panel-heading text-center">
                    <h3 class="panel-title">1. ajax - json</h3>
                </div>
                <div class="card-body">
                    <h5>데이터 얻어오기</h5>
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <button class="btn btn-outline-secondary" type="button" onclick="try1_1()">
                                <i class="far fa-play-circle"></i>
                                Demo
                            </button>
                        </div>
                        <input type="text" class="form-control" id="resultTry1_1" placeholder="result..." readonly/>
                    </div>
                    <pre class="prettyprint lang-javascript">
const params = {};
const response = await MyAjax.excute('&#36;{CONTEXT_PATH}/sample/admin/menu/getSampleMenuMstVOList.json', params)
console.info(response);
</pre>
                    <h5>추가/수정/삭제 등의 처리 요청하기</h5>
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <button class="btn btn-outline-secondary" type="button" onclick="try1_2()">
                                <i class="far fa-play-circle"></i>
                                Demo
                            </button>
                        </div>
                        <input type="text" class="form-control" id="resultTry1_2" placeholder="result..." readonly/>
                    </div>
                    <pre class="prettyprint lang-javascript">
const params = {
    memberId : 0
};
const response = await MyAjax.excute('&#36;{CONTEXT_PATH}/sample/admin/member/deleteSampleMemberMst.json', params, {
    autoResultFunctionTF : true // 자동 결과 메시지 출력
    //, successMessage : "정상적으로 삭제 처리되었습니다." // 성공 시 수동 메시지 설정
});
console.info(response);
</pre>
                    <div id="list2"></div>
                </div>
            </div>

            <div class="card">
                <div class="panel-heading text-center">
                    <h3 class="panel-title">2. ajax - html</h3>
                </div>
                <div class="card-body">
                    <h5>html 얻어오기</h5>
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <button class="btn btn-outline-secondary" type="button" onclick="try2_1()">
                                <i class="far fa-play-circle"></i>
                                Demo
                            </button>
                        </div>
                        <input type="text" class="form-control" id="resultTry2_1" placeholder="result..." readonly/>
                    </div>
                    <pre class="prettyprint lang-javascript">
const params = {};
const response = await MyAjax.excute('&#36;{CONTEXT_PATH}/sample/guide/ajax/sampleHtml.view', params, {
    dataType : "html"
});
console.info(response);
</pre>
                    <div id="list3"></div>
                </div>
            </div>

            <div class="card">
                <div class="panel-heading text-center">
                    <h3 class="panel-title">3. ajax - file upload(form data)</h3>
                </div>
                <div class="card-body">
                    <h5>요청하기</h5>
                    <input type="file" id="file" class="form-control-file"/>
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <button class="btn btn-outline-secondary" type="button" onclick="try3_1()">
                                <i class="far fa-play-circle"></i>
                                Demo
                            </button>
                        </div>
                        <input type="text" class="form-control" id="resultTry3_1" placeholder="result..." readonly/>
                    </div>
                    <pre class="prettyprint lang-javascript">
const formData = new FormData();
if ($('#file')[0].files[0] === undefined || $('#file')[0].files[0] === null) { // 기본 undefined, ie에서는 null
    alert("파일을 선택해주세요.");
    return;
}
formData.append('targetDirPath', '/upload_test/');
formData.append("file", $('#file')[0].files[0]);
const response = await MyAjax.excuteWithFile('&#36;{CONTEXT_PATH}/common/file/upload/fileUpload', formData, {
    autoResultFunctionTF : true,
    successMessage : "업로드 성공"
});
// callback
</pre>
                    <div id="list4"></div>
                </div>
            </div>

            <div class="card">
                <div class="panel-heading text-center">
                    <h3 class="panel-title">4. ajax - file download</h3>
                </div>
                <div class="card-body">
                    <h5>required</h5>
                    <pre class="prettyprint lang-javascript">&lt;my:html fileDownloader="YES"&gt;</pre>
                    <h5>파일 경로로 요청하기</h5>
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <button class="btn btn-outline-secondary" type="button" onclick="try4_1()">
                                <i class="far fa-play-circle"></i>
                                Demo
                            </button>
                        </div>
                        <input type="text" class="form-control" id="resultTry4_1" placeholder="result..." readonly/>
                        <input type="hidden" id="filePath" class="form-control" value=".no-image-available.png"/>
                    </div>
                    <pre class="prettyprint lang-javascript">
let dataParam = {
	filePath : $('#filePath').val()
};
MyAjax.downloadFile('&#36;{CONTEXT_PATH}/common/file/download/fileDownload.proc', dataParam);
</pre>
                    <h5>(파일데이터가 테이블:샘플SAMPLE_FILE_MST참조 에 존재할때)요청하기</h5>
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <button class="btn btn-outline-secondary" type="button" onclick="try4_2()">
                                <i class="far fa-play-circle"></i>
                                Demo
                            </button>
                        </div>
                        <input type="text" class="form-control" id="resultTry4_2" placeholder="result..." readonly/>
                        <input type="hidden" id="filePath2" class="form-control" value="./MyFileDownloadController.java"/>
                    </div>
                    <pre class="prettyprint lang-javascript">
let dataParam = {
	fileSeq : $('#fileSeq').val(),
	fileName : $('#fileName').val() // 보안이슈로 추가
};
MyAjax.downloadFile('&#36;{CONTEXT_PATH}/common/file/download/fileDownload.proc', dataParam);
</pre>
                </div>
            </div>
        </div>
    </main>
    <my:footer/>
    <script src="https://cdn.rawgit.com/google/code-prettify/master/loader/run_prettify.js"></script>
    <script data-for="ready">
        $(document).ready(function () {
        });
    </script>
    <script>
        async function try1_1() {
            const response = await MyAjax.excute('${CONTEXT_PATH}/sample/admin/menu/getSampleMenuMstVOList.json', {});
            $('#resultTry1_1').val(JSON.stringify(response));
        }

        async function try1_2() {
            const response = await MyAjax.excute('${CONTEXT_PATH}/sample/admin/member/deleteSampleMemberMst.json', {
                memberId: 0
            }, {
                autoResultFunctionTF: true
            })
            $('#resultTry1_2').val(JSON.stringify(response));
        }

        async function try2_1() {
            const response = await MyAjax.excute('${CONTEXT_PATH}/sample/guide/ajax/sampleHtml.view', {}, {
                dataType: "html"
            })
            $('#resultTry2_1').val(response);
        }

        async function try3_1() {
            const formData = new FormData();
            if ($('#file')[0].files[0] === undefined || $('#file')[0].files[0] === null) { // 기본 undefined, ie에서는 null
                alert("파일을 선택해주세요.");
                return;
            }
            formData.append('targetDirPath', '/upload_test/');
            formData.append("file", $('#file')[0].files[0]);
            const response = await MyAjax.excuteWithFile('${CONTEXT_PATH}/common/file/upload/fileUpload', formData, {
                autoResultFunctionTF: true,
                successMessage: "업로드 성공"
            })
            $('#resultTry3_1').val(JSON.stringify(response));
        }

        function try4_1() {
            MyAjax.downloadFile('${CONTEXT_PATH}/common/file/download/fileDownload.proc', {
                filePath: $('#filePath').val()
            });
        }

        function try4_2() {
            MyAjax.downloadFile('${CONTEXT_PATH}/common/file/download/fileDownload.proc', {
                fileSeq: '1000000003',
                fileName: 'smile_icon.jpg'
            });
        }
    </script>
</my:html>
