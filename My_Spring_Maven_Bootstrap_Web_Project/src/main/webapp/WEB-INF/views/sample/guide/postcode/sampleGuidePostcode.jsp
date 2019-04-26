<%@ page session="true" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/include/include.jsp" %>

<my:html>
    <body>
    <my:menu/>
    <div class="page-header text-center">
        <h3>
            우편번호 검색창
            <small>띄우기</small>
        </h3>
    </div>

    <div class="page-header text-center">
        <h4>목차</h4>
        <div id="list1"></div>
        <p class="text-muted">
            <a href="#list1"> 1. 우편번호 검색창 띄우기 </a>
        </p>
    </div>
    <div class="container">
        <div class="panel panel-default">
            <div class="panel-heading text-center">
                <h3 class="panel-title">1. 우편번호 검색창 띄우기</h3>
            </div>
            <div class="panel-body">
                <div class="input-group">
					<span class="input-group-btn">
						<button class="btn btn-default" type="button" onclick="try1_1()">
							<i class="fa fa-play-circle-o" aria-hidden="true"></i>
							Demo
						</button>
					</span>
                    <input type="text" class="form-control" id="resultTry1_1" placeholder="result..." readonly>
                </div>
                <h4>프로토콜별 js 선언</h4>
                <pre class="prettyprint">
&lt;%
	if (request.isSecure()) {
%&gt;
&lt;script src="https://spi.maps.daum.net/imap/map_js_init/postcode.v2.js"&gt;&lt;/script&gt;
&lt;%
	} else {
%&gt;
&lt;script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"&gt;&lt;/script&gt;
&lt;%
	}
%&gt;
</pre>
                <pre class="prettyprint">
new daum.Postcode({
	oncomplete : function(data) {
		console.info(data);
	}
}).open();</pre>
                <div id="list2"></div>
            </div>
        </div>
    </div>
    <my:footer/>
    </body>
    <%
        if (request.isSecure()) {
    %>
    <script src="https://spi.maps.daum.net/imap/map_js_init/postcode.v2.js"></script>
    <%
    } else {
    %>
    <script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
    <%
        }
    %>
    <script src="https://cdn.rawgit.com/google/code-prettify/master/loader/run_prettify.js"></script>
    <script data-for="ready">
        $(document).ready(function () {
        });
    </script>
    <script>
        function try1_1() {
            new daum.Postcode({
                oncomplete: function (data) {
                    $('#resultTry1_1').val(JSON.stringify(data));
                }
            }).open();
        }
    </script>
</my:html>
