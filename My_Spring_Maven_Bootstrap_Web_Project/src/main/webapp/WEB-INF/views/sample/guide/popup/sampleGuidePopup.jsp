<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/include/include.jsp" %>

<my:html>
    <body>
    <my:menu/>
    <div class="page-header text-center">
        <h3>
            팝업(popup)창
            <small>띄우기</small>
        </h3>
    </div>

    <div class="page-header text-center">
        <h4>목차</h4>
        <div id="list1"></div>
        <p class="text-muted">
            <a href="#list1"> 1. 팝업 띄우기 </a>
        </p>
        <p class="text-muted">
            <a href="#list2"> 2. 탭 띄우기 </a>
        </p>
    </div>
    <div class="container">
        <div class="panel panel-default">
            <div class="panel-heading text-center">
                <h3 class="panel-title">1. 팝업 띄우기</h3>
            </div>
            <div class="panel-body">
                <h5>새창으로 띄우기</h5>
                <div class="input-group">
					<span class="input-group-btn">
						<button class="btn btn-default" type="button" onclick="try1_1()">
							<i class="fa fa-play-circle-o" aria-hidden="true"></i>
							Demo
						</button>
					</span>
                    <input type="text" class="form-control" id="resultTry1_1" placeholder="result..." readonly/>
                </div>
                <pre class="prettyprint">
MyCommon.goWindow('http://google.com');
</pre>
                <h5>새창으로 띄우기(옵션추가)</h5>
                <div class="input-group">
					<span class="input-group-btn">
						<button class="btn btn-default" type="button" onclick="try1_2()">
							<i class="fa fa-play-circle-o" aria-hidden="true"></i>
							Demo
						</button>
					</span>
                    <input type="text" class="form-control" id="resultTry1_2" placeholder="result..." readonly/>
                </div>
                <pre class="prettyprint">
MyCommon.goWindow('http://google.com', {
	height : 800,
	width : 1200
});
</pre>
                <div id="list2"></div>
            </div>
        </div>

        <div class="panel panel-default">
            <div class="panel-heading text-center">
                <h3 class="panel-title">2. 탭 띄우기</h3>
            </div>
            <div class="panel-body">
                <h5>탭으로 띄우기</h5>
                <div class="input-group">
					<span class="input-group-btn">
						<button class="btn btn-default" type="button" onclick="try2_1()">
							<i class="fa fa-play-circle-o" aria-hidden="true"></i>
							Demo
						</button>
					</span>
                    <input type="text" class="form-control" id="resultTry2_1" placeholder="result..." readonly/>
                </div>
                <pre class="prettyprint">
MyCommon.goTab('http://google.com');
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
            MyCommon.goWindow('http://google.com');
        }

        function try1_2() {
            MyCommon.goWindow('http://google.com', {
                height: 800,
                width: 1200
            });
        }

        function try2_1() {
            MyCommon.goTab('http://google.com');
        }
    </script>
</my:html>
