<%@ page session="true" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/include/include.jsp" %>

<my:html numberFormatter="YES">
    <body>
    <my:menu/>
    <div class="page-header text-center">
        <h3>
            데이터 포맷
            <small>처리하기</small>
        </h3>
    </div>
    <div class="page-header text-center">
        <h4>목차</h4>
        <div id="list1"></div>
        <p class="text-muted">
            <a href="#list1"> 1. 날짜 포맷 처리 </a>
        </p>
        <p class="text-muted">
            <a href="#list2"> 2. 숫자 포맷 처리 </a>
        </p>
    </div>
    <div class="container">
        <div class="panel panel-default">
            <div class="panel-heading text-center">
                <h3 class="panel-title">1. 날짜 포맷 처리</h3>
            </div>
            <div class="panel-body">
                <h5>현재 시간 출력(YYYY-MM-DD HH:mm)</h5>
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
moment().format('YYYY-MM-DD HH:mm');
</pre>
                <h5>현재 시간 출력(YYYY년MM월DD일 HH시mm분ss초)</h5>
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
moment().format('YYYY년MM월DD일 HH시mm분ss초');
</pre>
                <h5>데이터를 받아 포맷변경(YYYY-MM-DD -> YYYY년MM월DD일)</h5>
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
moment('YYYY-MM-DD').format('YYYY년MM월DD일');
</pre>
                <h5>데이터를 받아 포맷변경(YYYYMMDD HHmmss -> YYYY년MM월DD일 HH시mm분ss초)</h5>
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
moment('YYYYMMDD HHmmss').format('YYYY년MM월DD일 HH시mm분ss초');
// 아래는 안되니 주의!
moment('YYYYMMDDHHmmss').format('YYYY년MM월DD일 HH시mm분ss초');
</pre>
                <h5>Timestamp 데이터를 받아 포맷변경(longValue -> YYYY년MM월DD일 HH시mm분ss초)</h5>
                <div class="input-group">
					<span class="input-group-btn">
						<button class="btn btn-default" type="button" onclick="try1_5()">
							<i class="fa fa-play-circle-o" aria-hidden="true"></i>
							Demo
						</button>
					</span>
                    <input type="text" class="form-control" id="resultTry1_5" placeholder="result..." readonly>
                </div>
                <pre class="prettyprint">
let longValue = 1497513052362;
moment(longValue).format('YYYY년MM월DD일 HH시mm분ss초');
</pre>
                <h5>현재 시간에서 +10년, +3달, -2주, +1일, -5시간, -35분, +20초 연산한 후 출력(YYYY-MM-DD HH:mm:ss)</h5>
                <div class="input-group">
					<span class="input-group-btn">
						<button class="btn btn-default" type="button" onclick="try1_6()">
							<i class="fa fa-play-circle-o" aria-hidden="true"></i>
							Demo
						</button>
					</span>
                    <input type="text" class="form-control" id="resultTry1_6" placeholder="result..." readonly>
                </div>
                <pre class="prettyprint">
moment().add(10, 'years').add(3, 'months').subtract(2, 'weeks').add(1, 'days').subtract(5, 'hours').subtract(35, 'minutes').add(20, 'seconds').format('YYYY-MM-DD HH:mm:ss');
</pre>
                <div id="list2"></div>
            </div>
        </div>

        <div class="panel panel-default">
            <div class="panel-heading text-center">
                <h3 class="panel-title">2. 숫자 포맷 처리</h3>
            </div>
            <div class="panel-body">
                <h4>required</h4>
                <pre class="prettyprint">&lt;my:html numberFormatter="YES"&gt;</pre>
                <h5>숫자 천자리마다 콤마(,) 포맷 후 값 얻기</h5>
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
$.number(123456789000);
</pre>
                <h5>실수 소수점 자리수 포맷 후 값 얻기</h5>
                <div class="input-group">
					<span class="input-group-btn">
						<button class="btn btn-default" type="button" onclick="try2_2()">
							<i class="fa fa-play-circle-o" aria-hidden="true"></i>
							Demo
						</button>
					</span>
                    <input type="text" class="form-control" id="resultTry2_2" placeholder="result..." readonly>
                </div>
                <pre class="prettyprint">
$.number(5020.2364, 2);
</pre>
                <h5>숫자 천자리마다 콤마(,) 포맷 후 값 얻기</h5>
                <ul id="ul_try2_3">
                    <li>1025.8702</li>
                    <li>18023</li>
                    <li>982.3</li>
                    <li>.346323</li>
                </ul>
                <div class="input-group">
					<span class="input-group-btn">
						<button class="btn btn-default" type="button" onclick="try2_3()">
							<i class="fa fa-play-circle-o" aria-hidden="true"></i>
							Demo
						</button>
					</span>
                </div>
                <pre class="prettyprint">
$('ul#ul_try2_3 > li').number( true );
</pre>
                <h5>실수 소수점 자리수 포맷 후 값 얻기</h5>
                <ul id="ul_try2_4">
                    <li>1025.8702</li>
                    <li>18023</li>
                    <li>982.3</li>
                    <li>.346323</li>
                </ul>
                <div class="input-group">
					<span class="input-group-btn">
						<button class="btn btn-default" type="button" onclick="try2_4()">
							<i class="fa fa-play-circle-o" aria-hidden="true"></i>
							Demo
						</button>
					</span>
                </div>
                <pre class="prettyprint">
$('ul#ul_try2_4 > li').number( true, 2 );
</pre>
                <div id="list3"></div>
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
            $('#resultTry1_1').val(moment().format('YYYY-MM-DD HH:mm'));
        }

        function try1_2() {
            $('#resultTry1_2').val(moment().format('YYYY년MM월DD일 HH시mm분ss초'));
        }

        function try1_3() {
            $('#resultTry1_3').val(moment('2017-06-14').format('YYYY년MM월DD일'));
        }

        function try1_4() {
            $('#resultTry1_4').val(moment('20170614 123456').format('YYYY년MM월DD일 HH시mm분ss초'));
        }

        function try1_5() {
            $('#resultTry1_5').val(moment(1497513052362).format('YYYY년MM월DD일 HH시mm분ss초'));
        }

        function try1_6() {
            $('#resultTry1_6').val(
                moment().add(10, 'years').add(3, 'months').subtract(2, 'weeks').add(1, 'days').subtract(5, 'hours').subtract(35, 'minutes').add(20, 'seconds').format('YYYY-MM-DD HH:mm:ss'));
        }

        function try2_1() {
            $('#resultTry2_1').val($.number(123456789000));
        }

        function try2_2() {
            $('#resultTry2_2').val($.number(5020.2364, 2));
        }

        function try2_3() {
            $('ul#ul_try2_3 > li').number(true);
        }

        function try2_4() {
            $('ul#ul_try2_4 > li').number(true, 2);
        }
    </script>
</my:html>
