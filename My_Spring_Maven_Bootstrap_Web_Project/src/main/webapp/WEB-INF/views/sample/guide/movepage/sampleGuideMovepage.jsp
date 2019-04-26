<%@ page session="true" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/include/include.jsp" %>

<my:html>
    <body>
    <my:menu/>
    <div class="page-header text-center">
        <h3>
            페이지 이동
            <small>(a href)</small>
        </h3>
    </div>

    <div class="page-header text-center">
        <h4>목차</h4>
        <p class="text-muted">
            <a href="#list1"> 1. 페이지 이동 </a>
        </p>
        <div id="list1"></div>
        <p class="text-muted">
            <a href="#list2"> 2. &lt;a href="url"&gt; </a>
        </p>
    </div>
    <div class="container">
        <div class="panel panel-default">
            <div class="panel-heading text-center">
                <h3 class="panel-title">1. 페이지 이동</h3>
            </div>
            <div class="panel-body">
                <h5>내부 링크</h5>
                <div class="input-group">
					<span class="input-group-btn">
						<button class="btn btn-default" type="button" onclick="try1_1()">
							<i class="fa fa-play-circle-o" aria-hidden="true"></i>
							Demo
						</button>
					</span>
                    <input type="text" class="form-control" id="resultTry1_1" placeholder="result..." readonly>
                </div>
                <pre class="prettyprint">
MyCommon.goLink('/sample/guide/movepage/sampleGuideMovepage.view');
</pre>
                <h5>외부 링크(외부 링크는 기본적으로 탭으로 뜹니다.)</h5>
                <div class="input-group">
					<span class="input-group-btn">
						<button class="btn btn-default" type="button" onclick="try1_2()">
							<i class="fa fa-play-circle-o" aria-hidden="true"></i>
							Demo
						</button>
					</span>
                    <input type="text" class="form-control" id="resultTry1_2" placeholder="result..." readonly>
                </div>
                <pre class="prettyprint">
MyCommon.goLink('http://google.com');
</pre>
                <h5>팝업으로 띄우기</h5>
                <div class="input-group">
					<span class="input-group-btn">
						<button class="btn btn-default" type="button" onclick="try1_3()">
							<i class="fa fa-play-circle-o" aria-hidden="true"></i>
							Demo
						</button>
					</span>
                    <input type="text" class="form-control" id="resultTry1_3" placeholder="result..." readonly>
                </div>
                <pre class="prettyprint">
MyCommon.goWindow('/sample/guide/movepage/sampleGuideMovepage.view');
MyCommon.goLink('/sample/guide/movepage/sampleGuideMovepage.view', 'W');
MyCommon.goLink('http://google.com', 'W');
</pre>
                <h5>외부 링크(페이지 이동)</h5>
                <div class="input-group">
					<span class="input-group-btn">
						<button class="btn btn-default" type="button" onclick="try1_4()">
							<i class="fa fa-play-circle-o" aria-hidden="true"></i>
							Demo
						</button>
					</span>
                    <input type="text" class="form-control" id="resultTry1_4" placeholder="result..." readonly>
                </div>
                <pre class="prettyprint">
MyCommon.goLink('http://google.com', 'L');
</pre>
                <div id="list2"></div>
            </div>
        </div>

        <div class="panel panel-default">
            <div class="panel-heading text-center">
                <h3 class="panel-title">2. &lt;a href="url"&gt;</h3>
            </div>
            <div class="panel-body">
                <h5>&lt;a href&gt; 사용을 지양합니다.</h5>
                <div class="input-group">
					<span class="input-group-btn">
						<button class="btn btn-default" type="button" onclick="try2_1()">
							<i class="fa fa-play-circle-o" aria-hidden="true"></i>
							Demo
						</button>
					</span>
                    <input type="text" class="form-control" id="resultTry2_1" placeholder="result..." readonly>
                </div>
                <pre class="prettyprint">
&lt;a href="javascript:void(0);" onclick="MyCommon.goLink('http://google.com');" /&gt;
</pre>
                <div id="list2"></div>
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
            MyCommon.goLink('${CONTEXT_PATH}/sample/guide/movepage/sampleGuideMovepage.view');
        }

        function try1_2() {
            MyCommon.goLink('http://google.com');
        }

        function try1_3() {
            MyCommon.goLink('http://google.com', 'W');
        }

        function try1_4() {
            MyCommon.goLink('http://google.com', 'L');
        }

        function try2_1() {
            MyCommon.goLink('http://google.com');
        }
    </script>
</my:html>
