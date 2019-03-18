<%@ page session="true" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/include/include.jsp"%>

<my:html paging="YES">
<body>
	<my:menu />
	<div class="page-header text-center">
		<h3>
			페이징
			<small>처리</small>
		</h3>
	</div>
	<div class="page-header text-center">
		<h4>required</h4>
		<p>&lt;my:html paging="YES"&gt;</p>
	</div>

	<div class="page-header text-center">
		<h4>목차</h4>
		<p class="text-muted">
			<a href="#list1"> 1. &lt;button&gt; 페이징 처리 </a>
		</p>
		<div id="list1"></div>
		<p class="text-muted">
			<a href="#list2"> 1. &lt;a&gt; 페이징 처리 </a>
		</p>
	</div>
	<div class="container">
		<div class="panel panel-default">
			<div class="panel-heading text-center">
				<h3 class="panel-title">1. &lt;button&gt; 페이징 처리</h3>
			</div>
			<div class="panel-body">
				<nav>
					<ul class="pagination">
						<li>
							<a href="javascript:void(0);" aria-label="First">
								<span aria-hidden="true">&laquo;</span>
							</a>
						</li>
						<li>
							<a href="javascript:void(0);" aria-label="Previous">
								<span aria-hidden="true">&lt;</span>
							</a>
						</li>
						<li class="active">
							<a href="javascript:void(0);" data-page-number="true">1</a>
						</li>
						<li>
							<a href="javascript:void(0);" data-page-number="true" style="display: none;">2</a>
						</li>
						<li>
							<a href="javascript:void(0);" data-page-number="true" style="display: none;">3</a>
						</li>
						<li>
							<a href="javascript:void(0);" data-page-number="true" style="display: none;">4</a>
						</li>
						<li>
							<a href="javascript:void(0);" data-page-number="true" style="display: none;">5</a>
						</li>
						<li>
							<a href="javascript:void(0);" aria-label="Next">
								<span aria-hidden="true">&gt;</span>
							</a>
						</li>
						<li>
							<a href="javascript:void(0);" aria-label="Last">
								<span aria-hidden="true">&raquo;</span>
							</a>
						</li>
					</ul>
				</nav>
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
&lt;nav&gt;
    &lt;ul class="pagination"&gt;
        &lt;li&gt;
            &lt;a href="javascript:void(0);" aria-label="Previous"&gt;
                &lt;span aria-hidden="true"&gt;&laquo;&lt;/span&gt;
            &lt;/a&gt;
        &lt;/li&gt;
        &lt;li class="active"&gt;
            &lt;a href="javascript:void(0);"&gt;1&lt;/a&gt;
        &lt;/li&gt;
        &lt;li&gt;
            &lt;a href="javascript:void(0);" style="display: none;"&gt;2&lt;/a&gt;
        &lt;/li&gt;
        &lt;li&gt;
            &lt;a href="javascript:void(0);" style="display: none;"&gt;3&lt;/a&gt;
        &lt;/li&gt;
        &lt;li&gt;
            &lt;a href="javascript:void(0);" style="display: none;"&gt;4&lt;/a&gt;
        &lt;/li&gt;
        &lt;li&gt;
            &lt;a href="javascript:void(0);" style="display: none;"&gt;5&lt;/a&gt;
        &lt;/li&gt;
        &lt;li&gt;
            &lt;a href="javascript:void(0);" aria-label="Next"&gt;
                &lt;span aria-hidden="true"&gt;&raquo;&lt;/span&gt;
            &lt;/a&gt;
        &lt;/li&gt;
    &lt;/ul&gt;
&lt;/nav&gt;</pre>
				<div id="list2"></div>
			</div>
		</div>
	</div><my:footer />
</body>
<script src="https://cdn.rawgit.com/google/code-prettify/master/loader/run_prettify.js"></script>
<script data-for="ready">
	$(document).ready(function() {
	});
</script>
<script>
	function try1_1() {
		MyPaging.firstBtnObj = $('ul.pagination > li > a').first();
		MyPaging.prevBtnObj = $('ul.pagination > li > a').eq(1);
		MyPaging.nextBtnObj = $('ul.pagination > li > a').eq(7);
		MyPaging.lastBtnObj = $('ul.pagination > li > a').last();
		MyPaging.numberOfDisplayPages = 5;
		MyPaging.numberGroupObj = $('ul.pagination > li > a[data-page-number="true"]');
		MyPaging.numberSelectedClassName = 'active';
		MyPaging.positionSelectedClassName = 'parent';
		MyPaging.selectListFunctionName = selectList;
		MyPaging.init();
		selectList();
	}
	let selectList = function() {
		MyAjax.excute('${CONTEXT_PATH }/sample/guide/paging/sampleGuidePaging.json', {}).done(function(response) {
			MyPaging.changeValue(MyPaging.getSelectedPageIndex(), response.totalItemCount, response.itemPerPage, response.nextPage);
		});
	}
</script>
</my:html>
