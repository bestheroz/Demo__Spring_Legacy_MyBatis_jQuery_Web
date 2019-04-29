<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/include/include.jsp" %>

<my:html fileDownloader="YES">
    <body>
    <my:menu/>
    <div class="page-header text-center">
        <h3>
            ajax
            <small>요청 하기</small>
        </h3>
    </div>
    <div class="page-header text-center">
        <h4>목차</h4>
        <p class="text-muted">
            <a href="#list1"> 1. ajax - json </a>
        </p>
        <p class="text-muted">
            <a href="#list2"> 2. ajax - html </a>
        </p>
        <p class="text-muted">
            <a href="#list3"> 3. ajax - file upload(form data) </a>
        </p>
        <p class="text-muted">
            <a href="#list4"> 4. ajax - file download </a>
        </p>
        <div id="list1"></div>
    </div>
    <div class="container">
        <div class="panel panel-default">
            <div class="panel-heading text-center">
                <h3 class="panel-title">1. ajax - json</h3>
            </div>
            <div class="panel-body">
                <h5>데이터 얻어오기</h5>
                <div class="input-group">
					<span class="input-group-btn">
						<button class="btn btn-default" type="button" onclick="try1_1()">
							<i class="fa fa-play-circle-o" aria-hidden="true"></i>
							Demo
						</button>
					</span>
                    <input type="text" class="form-control" id="resultTry1_1" placeholder="result..." readonly />
                </div>
                <pre class="prettyprint">
let params = {};
MyAjax.excute('&#36;{CONTEXT_PATH}/sample/admin/menu/getSampleMenuMstVOList.json', params).done(function(response) {
    console.info(response);
});
</pre>
                <h5>추가/수정/삭제 등의 처리 요청하기</h5>
                <div class="input-group">
					<span class="input-group-btn">
						<button class="btn btn-default" type="button" onclick="try1_2()">
							<i class="fa fa-play-circle-o" aria-hidden="true"></i>
							Demo
						</button>
					</span>
                    <input type="text" class="form-control" id="resultTry1_2" placeholder="result..." readonly />
                </div>
                <pre class="prettyprint">
let params = {
    memberId : 0
};
MyAjax.excute('&#36;{CONTEXT_PATH}/sample/admin/member/deleteSampleMemberMst.json', params, {
    autoResultFunctionTF : true // 자동 결과 메시지 출력
    //, successMessage : "정상적으로 삭제 처리되었습니다." // 성공 시 수동 메시지 설정
}).done(function(response) {
    console.info(response);
});
</pre>
                <div id="list2"></div>
            </div>
        </div>

        <div class="panel panel-default">
            <div class="panel-heading text-center">
                <h3 class="panel-title">2. ajax - html</h3>
            </div>
            <div class="panel-body">
                <h5>html 얻어오기</h5>
                <div class="input-group">
					<span class="input-group-btn">
						<button class="btn btn-default" type="button" onclick="try2_1()">
							<i class="fa fa-play-circle-o" aria-hidden="true"></i>
							Demo
						</button>
					</span>
                    <input type="text" class="form-control" id="resultTry2_1" placeholder="result..." readonly />
                </div>
                <pre class="prettyprint">
let params = {};
MyAjax.excute('&#36;{CONTEXT_PATH}/sample/guide/ajax/sampleHtml.view', params, {
    dataType : "html"
}).done(function(response) {
    console.info(response);
});
</pre>
                <div id="list3"></div>
            </div>
        </div>

        <div class="panel panel-default">
            <div class="panel-heading text-center">
                <h3 class="panel-title">3. ajax - file upload(form data)</h3>
            </div>
            <div class="panel-body">
                <h5>요청하기</h5>
                <input type="file" id="file"/>
                <div class="input-group">
					<span class="input-group-btn">
						<button class="btn btn-default" type="button" onclick="try3_1()">
							<i class="fa fa-play-circle-o" aria-hidden="true"></i>
							Demo
						</button>
					</span>
                    <input type="text" class="form-control" id="resultTry3_1" placeholder="result..." readonly />
                </div>
                <pre class="prettyprint">
let formData = new FormData();
if ($('#file')[0].files[0] === undefined || $('#file')[0].files[0] === null) { // 기본 undefined, ie에서는 null
    alert("파일을 선택해주세요.");
    return;
}
formData.append('targetDirPath', '/upload_test/');
formData.append("file", $('#file')[0].files[0]);
MyAjax.excuteWithFile('&#36;{CONTEXT_PATH}/common/file/upload/fileUpload', formData, {
    autoResultFunctionTF : true,
    successMessage : "업로드 성공"
}).done(function(response) {
    // callback
});
</pre>
                <div id="list4"></div>
            </div>
        </div>

        <div class="panel panel-default">
            <div class="panel-heading text-center">
                <h3 class="panel-title">4. ajax - file download</h3>
            </div>
            <div class="panel-body">
                <h5>required</h5>
                <pre class="prettyprint">&lt;my:html fileDownloader="YES"&gt;</pre>
                <h5>파일 경로로 요청하기</h5>
                <div class="input-group">
					<span class="input-group-btn">
						<button class="btn btn-default" type="button" onclick="try4_1()">
							<i class="fa fa-play-circle-o" aria-hidden="true"></i>
							Demo
						</button>
					</span>
                    <input type="text" class="form-control" id="resultTry4_1" placeholder="result..." readonly />
                    <input type="hidden" id="filePath" class="form-control" value="./MyFileDownloadController.java"/>
                </div>
                <pre class="prettyprint">
let dataParam = {
	filePath : $('#filePath').val()
};
MyAjax.downloadFile('&#36;{CONTEXT_PATH}/common/file/download/fileDownload.proc', dataParam);
</pre>
                <h5>(파일데이터가 테이블:샘플SAMPLE_FILE_MST참조 에 존재할때)요청하기</h5>
                <div class="input-group">
					<span class="input-group-btn">
						<button class="btn btn-default" type="button" onclick="try4_2()">
							<i class="fa fa-play-circle-o" aria-hidden="true"></i>
							Demo
						</button>
					</span>
                    <input type="text" class="form-control" id="resultTry4_2" placeholder="result..." readonly />
                    <input type="hidden" id="filePath2" class="form-control" value="./MyFileDownloadController.java"/>
                </div>
                <pre class="prettyprint">
let dataParam = {
	fileSeq : $('#fileSeq').val(),
	fileName : $('#fileName').val() // 보안이슈로 추가
};
MyAjax.downloadFile('&#36;{CONTEXT_PATH}/common/file/download/fileDownload.proc', dataParam);
</pre>
            </div>
        </div>
    </div>
    <my:footer/>
    </body>
    <script src="https://cdn.rawgit.com/google/code-prettify/master/loader/run_prettify.js"></script>
    <script data-for="ready">
        $(document).ready(function () {
        });
    </script>
    <script>
        function try1_1() {
            let params = {};
            MyAjax.excute('${CONTEXT_PATH}/sample/admin/menu/getSampleMenuMstVOList.json', params).done(function (response) {
                $('#resultTry1_1').val(JSON.stringify(response));
            });
        }

        function try1_2() {
            let params = {
                memberId: 0
            };
            MyAjax.excute('${CONTEXT_PATH}/sample/admin/member/deleteSampleMemberMst.json', params, {
                autoResultFunctionTF: true
            }).done(function (response) {
                $('#resultTry1_2').val(JSON.stringify(response));
            });
        }

        function try2_1() {
            let params = {};
            MyAjax.excute('${CONTEXT_PATH}/sample/guide/ajax/sampleHtml.view', params, {
                dataType: "html"
            }).done(function (response) {
                $('#resultTry2_1').val(response);
            });
        }

        function try3_1() {
            let formData = new FormData();
            if ($('#file')[0].files[0] === undefined || $('#file')[0].files[0] === null) { // 기본 undefined, ie에서는 null
                alert("파일을 선택해주세요.");
                return;
            }
            formData.append('targetDirPath', '/upload_test/');
            formData.append("file", $('#file')[0].files[0]);
            MyAjax.excuteWithFile('${CONTEXT_PATH}/common/file/upload/fileUpload', formData, {
                autoResultFunctionTF: true,
                successMessage: "업로드 성공"
            }).done(function (response) {
                $('#resultTry3_1').val(JSON.stringify(response));
            });
        }

        function try4_1() {
            let dataParam = {
                filePath: $('#filePath').val()
            };
            MyAjax.downloadFile('${CONTEXT_PATH}/common/file/download/fileDownload.proc', dataParam);
        }

        function try4_2() {
            let dataParam = {
                fileSeq: '1000000003',
                fileName: 'smile_icon.jpg'
            };
            MyAjax.downloadFile('${CONTEXT_PATH}/common/file/download/fileDownload.proc', dataParam);
        }
    </script>
</my:html>
