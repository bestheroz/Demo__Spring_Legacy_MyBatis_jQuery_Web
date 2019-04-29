<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/include/include.jsp" %>

<my:html>
    <body>
    <my:menu/>
    <div class="page-header text-center">
        <h3>
            달력 (date time picker)
            <small>사용하기</small>
        </h3>
    </div>
    <div class="page-header text-center">
        <h4>목차</h4>
        <p class="text-muted">
            <a href="#list1"> 1. 날짜 선택(date picker) </a>
        </p>
        <p class="text-muted">
            <a href="#list2"> 2. 날짜 선택(from ~ to) </a>
        </p>
        <p class="text-muted">
            <a href="#list3"> 3. 시간 선택(time picker) </a>
        </p>
        <p class="text-muted">
            <a href="#list4"> 4. 시간 선택(from ~ to) </a>
        </p>
        <p class="text-muted">
            <a href="#list5"> 5. 선택 불가능 날짜 설정(disable date) </a>
        </p>
        <p class="text-muted">
            <a href="#list6"> 6. 날짜 선택 범위 설정(range date) </a>
        </p>
        <div id="list1"></div>
        <p class="text-muted">
            <a href="#list7"> 7. 날짜 + 시간 선택(date&amp;time picker) </a>
        </p>
    </div>
    <div class="container">
        <div class="panel panel-default">
            <div class="panel-heading text-center">
                <h3 class="panel-title">1. 날짜 선택(date picker)</h3>
            </div>
            <div class="panel-body">
                <h4>YYYY-MM-DD(기본형)</h4>
                <div class="row">
					<span class="col-sm-2">
						<input type="text" class="form-control datepicker" id="resultTry1_1" placeholder="result..." readonly/>
					</span>
                </div>
                <pre class="prettyprint">
&lt;input type="text" class="datepicker" placeholder="result..." readonly&gt;
&lt;input type="text" class="datepicker-yyyymmdd" placeholder="result..." readonly&gt;
</pre>
                <h4>YYYY-MM</h4>
                <div class="row">
					<span class="col-sm-2">
						<input type="text" class="form-control datepicker-yyyymm" id="resultTry1_2" placeholder="result..." readonly/>
					</span>
                </div>
                <pre class="prettyprint">
&lt;input type="text" class="datepicker-yyyymm" placeholder="result..." readonly&gt;
</pre>
                <h4>YYYY</h4>
                <div class="row">
					<span class="col-sm-2">
						<input type="text" class="form-control datepicker-yyyy" id="resultTry1_3" placeholder="result..." readonly/>
					</span>
                </div>
                <pre class="prettyprint">
&lt;input type="text" class="datepicker-yyyy" placeholder="result..." readonly&gt;
</pre>
                <div id="list2"></div>
            </div>
        </div>

        <div class="panel panel-default">
            <div class="panel-heading text-center">
                <h3 class="panel-title">2. 날짜 선택(from ~ to)</h3>
            </div>
            <div class="panel-body">
                <h4>YYYY-MM-DD(기본형)</h4>
                <div class="row">
					<span class="col-sm-2">
						<input type="text" class="form-control datepicker" id="resultTry2_1_1" placeholder="result..." readonly/>
					</span>
                    <span class="col-sm-2">
						<input type="text" class="form-control datepicker" id="resultTry2_1_2" placeholder="result..." readonly/>
					</span>
                </div>
                <pre class="prettyprint">
$(document).ready(function() {
    MyDatetimePicker.setFromTo($('#from'), $('#to'));
});
</pre>
                <h4>YYYY-MM</h4>
                <div class="row">
					<span class="col-sm-2">
						<input type="text" class="form-control datepicker-yyyymm" id="resultTry2_2_1" placeholder="result..." readonly/>
					</span>
                    <span class="col-sm-2">
						<input type="text" class="form-control datepicker-yyyymm" id="resultTry2_2_2" placeholder="result..." readonly/>
					</span>
                </div>
                <pre class="prettyprint">
$(document).ready(function() {
    MyDatetimePicker.setFromTo($('#from'), $('#to'));
});
</pre>
                <h4>YYYY</h4>
                <div class="row">
					<span class="col-sm-2">
						<input type="text" class="form-control datepicker-yyyy" id="resultTry2_3_1" placeholder="result..." readonly/>
					</span>
                    <span class="col-sm-2">
						<input type="text" class="form-control datepicker-yyyy" id="resultTry2_3_2" placeholder="result..." readonly/>
					</span>
                </div>
                <pre class="prettyprint">
$(document).ready(function() {
    MyDatetimePicker.setFromTo($('#from'), $('#to'));
});
</pre>
                <div id="list3"></div>
            </div>
        </div>

        <div class="panel panel-default">
            <div class="panel-heading text-center">
                <h3 class="panel-title">3. 시간 선택(time picker)</h3>
            </div>
            <div class="panel-body">
                <h4>HH:mm(기본형)</h4>
                <div class="row">
					<span class="col-sm-2">
						<input type="text" class="form-control timepicker" id="resultTry3_2" placeholder="result..." readonly/>
					</span>
                </div>
                <pre class="prettyprint">
&lt;input type="text" class="timepicker" placeholder="result..." readonly&gt;
&lt;input type="text" class="timepicker-hhmm" placeholder="result..." readonly&gt;
</pre>
                <h4>HH</h4>
                <div class="row">
					<span class="col-sm-2">
						<input type="text" class="form-control timepicker-hh" id="resultTry3_3" placeholder="result..." readonly/>
					</span>
                </div>
                <pre class="prettyprint">
&lt;input type="text" class="timepicker-hh" placeholder="result..." readonly&gt;
</pre>
                <h4>HH:mm:ss</h4>
                <div class="row">
					<span class="col-sm-2">
						<input type="text" class="form-control timepicker-hhmmss" id="resultTry3_1" placeholder="result..." readonly/>
					</span>
                </div>
                <pre class="prettyprint">
&lt;input type="text" class="timepicker-hhmmss" placeholder="result..." readonly&gt;
</pre>
                <div id="list4"></div>
            </div>
        </div>

        <div class="panel panel-default">
            <div class="panel-heading text-center">
                <h3 class="panel-title">4. 시간 선택(from ~ to)</h3>
            </div>
            <div class="panel-body">
                <h4>hh:mm(기본형)</h4>
                <div class="row">
					<span class="col-sm-2">
						<input type="text" class="form-control timepicker" id="resultTry4_1_1" placeholder="result..." readonly/>
					</span>
                    <span class="col-sm-2">
						<input type="text" class="form-control timepicker" id="resultTry4_1_2" placeholder="result..." readonly/>
					</span>
                </div>
                <pre class="prettyprint">
$(document).ready(function() {
    MyDatetimePicker.setFromTo($('#from'), $('#to'));
});
</pre>
                <div id="list5"></div>
            </div>
        </div>

        <div class="panel panel-default">
            <div class="panel-heading text-center">
                <h3 class="panel-title">5. 선택 불가능 날짜 설정(disable date)</h3>
            </div>
            <div class="panel-body">
                <h4>특정일 제외</h4>
                <div class="row">
					<span class="col-sm-2">
						<input type="text" class="form-control datepicker" id="resultTry5_1" placeholder="result..." readonly/>
					</span>
                </div>
                <pre class="prettyprint">
$(document).ready(function() {
    $(targetObj).data("DateTimePicker").disabledDates([ moment().subtract(1, 'days'), moment().add(1, 'days') ]);
});
</pre>
                <h4>공휴일 제외</h4>
                <div class="row">
					<span class="col-sm-2">
						<input type="text" class="form-control datepicker" id="resultTry5_2" placeholder="result..." readonly/>
					</span>
                </div>
                <pre class="prettyprint">
$(document).ready(function() {
    let holidays = [ "0101", "0301", "0505", "0606", "0815", "1003", "1009", "1225", "1004", "1005", "1006" ];
    $(targetObj).data("DateTimePicker").disabledDates(holidays);
});
</pre>
                <h4>토, 일요일 제외</h4>
                <div class="row">
					<span class="col-sm-2">
						<input type="text" class="form-control datepicker" id="resultTry5_3" placeholder="result..." readonly/>
					</span>
                </div>
                <pre class="prettyprint">
$(document).ready(function() {
    let holidays = [ "0101", "0301", "0505", "0606", "0815", "1003", "1009", "1225", "1004", "1005", "1006" ];
    $(targetObj).data("DateTimePicker").disabledDates(holidays);
});
</pre>
                <div id="list6"></div>
            </div>
        </div>

        <div class="panel panel-default">
            <div class="panel-heading text-center">
                <h3 class="panel-title">6. 날짜 선택 범위 설정(range date)</h3>
            </div>
            <div class="panel-body">
                <h4>범위 지정</h4>
                <div class="row">
					<span class="col-sm-2">
						<input type="text" class="form-control datepicker" id="resultTry6_1" placeholder="result..." readonly/>
					</span>
                </div>
                <pre class="prettyprint">
$(document).ready(function() {
    $(targetObj).data("DateTimePicker").minDate(moment().add(3, 'days')).maxDate(moment().add(7, 'days'));
});
</pre>
                <div id="list7"></div>
            </div>
        </div>

        <div class="panel panel-default">
            <div class="panel-heading text-center">
                <h3 class="panel-title">7. 날짜 + 시간 선택(date&amp;time picker)</h3>
            </div>
            <div class="panel-body">
                <h4>YYYY-MM-DD HH:mm(기본형)</h4>
                <div class="row">
					<span class="col-sm-3">
						<input type="text" class="form-control datetimepicker" id="resultTry7_1" placeholder="result..." readonly/>
					</span>
                </div>
                <pre class="prettyprint">
&lt;input type="text" class="datetimepicker" placeholder="result..." readonly&gt;
&lt;input type="text" class="datetimepicker-yyyymmddhhmm" placeholder="result..." readonly&gt;
</pre>
                <h4>YYYY-MM-DD HH</h4>
                <div class="row">
					<span class="col-sm-3">
						<input type="text" class="form-control datetimepicker-yyyymmddhh" id="resultTry7_2" placeholder="result..." readonly/>
					</span>
                </div>
                <pre class="prettyprint">
&lt;input type="text" class="datetimepicker-yyyymmddhh" placeholder="result..." readonly&gt;
</pre>
                <h4>YYYY-MM-DD HH:mm:ss</h4>
                <div class="row">
					<span class="col-sm-3">
						<input type="text" class="form-control datetimepicker-yyyymmddhhmmss" id="resultTry7_3" placeholder="result..." readonly/>
					</span>
                </div>
                <pre class="prettyprint">
&lt;input type="text" class="datetimepicker-yyyymmddhhmmss" placeholder="result..." readonly&gt;
</pre>

                <div id="list8"></div>
            </div>
        </div>
    </div>
    <my:footer/>
    </body>
    <script src="https://cdn.rawgit.com/google/code-prettify/master/loader/run_prettify.js"></script>
    <script data-for="ready">
        $(document).ready(function () {
            MyDatetimePicker.setFromTo($('#resultTry2_1_1'), $('#resultTry2_1_2'));
            MyDatetimePicker.setFromTo($('#resultTry2_2_1'), $('#resultTry2_2_2'));
            MyDatetimePicker.setFromTo($('#resultTry2_3_1'), $('#resultTry2_3_2'));
            MyDatetimePicker.setFromTo($('#resultTry4_1_1'), $('#resultTry4_1_2'));
            $('#resultTry5_1').data("DateTimePicker").disabledDates([moment().subtract(1, 'days'), moment().add(1, 'days')]);
            let holidays = ["20170101", "20170301", "20170505", "20170606", "20170815", "20171003", "20171009", "20171225", "20171004", "20171005", "20171006"];
            $('#resultTry5_2').data("DateTimePicker").disabledDates(holidays);
            $('#resultTry5_3').data("DateTimePicker").daysOfWeekDisabled([0, 6]);
            $('#resultTry6_1').data("DateTimePicker").minDate(moment().add(3, 'days')).maxDate(moment().add(7, 'days'));
        });
    </script>
    <script>

    </script>
</my:html>
