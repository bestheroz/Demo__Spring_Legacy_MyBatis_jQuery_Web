<%@ page session="true" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/include.jsp"%>

<my:html modal="YES" textEditor="YES">
<body>
	<my:menu />
	<div class="page-header text-center">
		<h3>
			모달(modal)
			<small>띄우기</small>
		</h3>
	</div>
	<div class="page-header text-center">
		<h4>required</h4>
		<p>&lt;my:html modal="YES"&gt;</p>
	</div>
	<div class="page-header text-center">
		<h4>목차</h4>
		<div id="list1"></div>
		<p class="text-muted">
			<a href="#list1"> 1. 모달 띄우기 </a>
		</p>
		<p class="text-muted">
			<a href="#list2"> 2. 다중 모달 띄우기 </a>
		</p>
	</div>
	<div class="container">
		<div class="panel panel-default">
			<div class="panel-heading text-center">
				<h3 class="panel-title">1. 모달 띄우기</h3>
			</div>
			<div class="panel-body">
				<h5>띄우기 / 닫기</h5>
				<div class="input-group">
					<span class="input-group-btn">
						<button class="btn btn-default" type="button" onclick="try1_1()">
							<i class="fa fa-play-circle-o" aria-hidden="true"></i>
							Demo
						</button>
					</span>
				</div>
				<pre class="prettyprint">
&lt;div id="try1_modalContent" class="jmodal" style="display: none;"&gt;
    //modal contents
&lt;/div&gt;
MyModal.open($('#try1_modalContent')).done(function(){
    //callback
});
MyModal.close($('#try1_modalContent')).done(function(){
    //callback
});
</pre>
				<div id="list2"></div>
			</div>
		</div>

		<div class="panel panel-default">
			<div class="panel-heading text-center">
				<h3 class="panel-title">2. 다중 모달 띄우기</h3>
			</div>
			<div class="panel-body">
				<h5>띄우기</h5>
				<div class="input-group">
					<span class="input-group-btn">
						<button class="btn btn-default" type="button" onclick="try2_1()">
							<i class="fa fa-play-circle-o" aria-hidden="true"></i>
							Demo
						</button>
					</span>
				</div>
				<pre class="prettyprint">
&lt;div id="try2_1-a" class="jmodal" style="display: none;"&gt;
    //modal contents
&lt;/div&gt;
MyModal.openMultiple($('#try2_1-a')).done(function(){
    //callback
});
MyModal.close($('#try2_1-a')).done(function(){
    //callback
});
</pre>
			</div>
		</div>

	</div>

	<!-- Modal -->
	<div id="try1_modalContent" class="jmodal" style="display: none;">
		<div class="modal-header">
			<h3>텍스트 에디터 Modal</h3>
		</div>
		<div class="modal-body">
			<form class="form-horizontal">
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label">Email</label>
					<div class="col-sm-10">
						<input type="email" class="form-control" id="inputEmail3" placeholder="Email">
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-sm-2 control-label">Password</label>
					<div class="col-sm-10">
						<input type="password" class="form-control" id="inputPassword3" placeholder="Password">
					</div>
				</div>
				<div class="form-group">
					<label for="resultTry2_2" class="col-sm-2 control-label">비고</label>
					<div class="col-sm-10">
						<div id="textarea2" data-texteditor="on" data-texteditor-height="400"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<div class="checkbox">
							<label>
								<input type="checkbox">
								Remember me
							</label>
						</div>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="button" class="btn btn-success">Sign in</button>
						<button type="button" class="btn btn-deafult" onclick="MyModal.close($('#try1_modalContent'));">Cancel(Close)</button>
					</div>
				</div>
			</form>
		</div>
	</div>

	<div class="modal" id="try2_1-a" style="display: none;">
		<p>
			I'm the first modal.
			<a href="javascript:void(0);" onclick="MyModal.openMultiple($('#try2_1-b'));">Open second modal...</a>
		</p>
		<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris
			nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt
			in culpa qui officia deserunt mollit anim id est laborum.</p>
		<button type="button" class="btn btn-success" onclick="MyModal.close($('#try2_1-a'));">Cancel(Close)</button>
	</div>

	<div class="modal" id="try2_1-b" style="display: none;">
		<p>
			I'm the second modal.
			<a href="javascript:void(0);" onclick="MyModal.openMultiple($('#try2_1-c'));">Open third modal...</a>
		</p>
		<p>Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt
			mollit anim id est laborum.</p>

		<button type="button" class="btn btn-success" onclick="MyModal.close($('#try2_1-b'));">Cancel(Close)</button>
	</div>

	<div class="modal" id="try2_1-c" style="display: none;">
		<p>I'm the third modal. You get the idea.</p>
		<button type="button" class="btn btn-success" onclick="MyModal.close($('#try2_1-c'));">Cancel(Close)</button>
	</div><my:footer />
</body>
<script src="https://cdn.rawgit.com/google/code-prettify/master/loader/run_prettify.js"></script>
<script data-for="ready">
	$(document).ready(function() {
	});
</script>
<script>
	function try1_1() {
		MyModal.open($('#try1_modalContent')).done(function() {
			//
		});
	}
	function try2_1() {
		MyModal.openMultiple($('#try2_1-a')).done(function() {
			//
		});
	}
</script>
</my:html>
