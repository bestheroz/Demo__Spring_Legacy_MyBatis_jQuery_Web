<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/include/include.jsp" %>

<my:html validator="YES">
    <body>
    <my:menu/>
    <div class="mx-auto text-center">
        <h3>
            값 검증하기
            <small>(숫자값, 이메일값, URL값, IP값 등)</small>
        </h3>
    </div>
    <div class="mx-auto text-center">
        <h4>required</h4>
        <p>&lt;my:html validator="YES"&gt;</p>
    </div>

    <div class="mx-auto text-center">
        <h4>목차</h4>
        <p class="text-muted">
            <a href="#list1"> 1. 숫자값 검증 </a>
        </p>
        <p class="text-muted">
            <a href="#list2"> 2. 이메일값 검증 </a>
        </p>
        <p class="text-muted">
            <a href="#list3"> 3. URL값 검증 </a>
        </p>
        <p class="text-muted">
            <a href="#list4"> 4. IP값 검증 </a>
        </p>
        <div id="list1"></div>
        <p class="text-muted">
            <a href="#list5"> 5. 기타값 검증 </a>
        </p>
    </div>
    <div class="container">
        <div class="card">
            <div class="panel-heading text-center">
                <h3 class="panel-title">1. 숫자값 검증</h3>
            </div>
            <div class="card-body">
                <input type="text" placeholder="숫자값 검증" id="inputTry1_1"/>

                <div class="input-group">
					<span class="input-group-btn">
						<button class="btn btn-default" type="button" onclick="try1_1()">
							<i class="far fa-play-circle"></i>
							Demo
						</button>
					</span>
                    <input type="text" class="form-control" id="resultTry1_1" placeholder="result..." readonly/>
                </div>
                <pre class="prettyprint">
jQuery.isNumeric(value);
</pre>
                <div id="list2"></div>
            </div>
        </div>

        <div class="card">
            <div class="panel-heading text-center">
                <h3 class="panel-title">2. 이메일값 검증</h3>
            </div>
            <div class="card-body">
                <input type="text" placeholder="이메일값 검증" id="inputTry2_1"/>

                <div class="input-group">
					<span class="input-group-btn">
						<button class="btn btn-default" type="button" onclick="try2_1()">
							<i class="far fa-play-circle"></i>
							Demo
						</button>
					</span>
                    <input type="text" class="form-control" id="resultTry2_1" placeholder="result..." readonly/>
                </div>
                <pre class="prettyprint">
validator.isEmail(value);
</pre>
                <div id="list3"></div>
            </div>
        </div>

        <div class="card">
            <div class="panel-heading text-center">
                <h3 class="panel-title">3. URL값 검증</h3>
            </div>
            <div class="card-body">
                <input type="text" placeholder="URL값 검증" id="inputTry3_1"/>

                <div class="input-group">
					<span class="input-group-btn">
						<button class="btn btn-default" type="button" onclick="try3_1()">
							<i class="far fa-play-circle"></i>
							Demo
						</button>
					</span>
                    <input type="text" class="form-control" id="resultTry3_1" placeholder="result..." readonly/>
                </div>
                <pre class="prettyprint">
validator.isURL(value);
</pre>
                <div id="list4"></div>
            </div>
        </div>

        <div class="card">
            <div class="panel-heading text-center">
                <h3 class="panel-title">4. IP값 검증</h3>
            </div>
            <div class="card-body">
                <input type="text" placeholder="IP값 검증" id="inputTry4_1"/>

                <div class="input-group">
					<span class="input-group-btn">
						<button class="btn btn-default" type="button" onclick="try4_1()">
							<i class="far fa-play-circle"></i>
							Demo
						</button>
					</span>
                    <input type="text" class="form-control" id="resultTry4_1" placeholder="result..." readonly/>
                </div>
                <pre class="prettyprint">
validator.isIP(value);
</pre>
                <div id="list5"></div>
            </div>
        </div>

        <div class="card">
            <div class="panel-heading text-center">
                <h3 class="panel-title">5. 기타값 검증</h3>
            </div>
            <div class="card-body">
                <div class="input-group">
					<span class="input-group-btn">
						<button class="btn btn-default" type="button" onclick="try5_1()">
							<i class="far fa-play-circle"></i>
							Demo
						</button>
					</span>
                    <input type="text" class="form-control" id="resultTry5_1" placeholder="Demo를 누르시면 검증 plugin URL로 링크됩니다." readonly/>
                </div>
                <div id="list6"></div>
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
            $('#resultTry1_1').val(jQuery.isNumeric($('#inputTry1_1').val()));
        }

        function try2_1() {
            $('#resultTry2_1').val(validator.isEmail($('#inputTry2_1').val()));
        }

        function try3_1() {
            $('#resultTry3_1').val(validator.isURL($('#inputTry3_1').val()));
        }

        function try4_1() {
            $('#resultTry4_1').val(validator.isIP($('#inputTry4_1').val()));
        }

        function try5_1() {
            MyCommon.goTab('https://lodash.com/docs/4.17.4#isNumber');
            MyCommon.goTab('https://github.com/chriso/validator.js#validators');
        }
    </script>
</my:html>
