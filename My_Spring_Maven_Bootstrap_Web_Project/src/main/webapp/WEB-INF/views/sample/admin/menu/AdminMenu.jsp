<%@ page session="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/include.jsp"%>
<my:html table="YES" modal="YES" validator="YES" fileDownloader="YES">
<body>
	<my:menu />
	<div class="page-header">
		<h3 class="text-center">
			메뉴관리
			<small>
				For 관리자
				<i class="fa fa-wrench" aria-hidden="true"></i>
			</small>
		</h3>
	</div>
	<div class="container">
		<div class="panel panel-default">
			<div class="panel-body">
				<table id="table1" class="table table-bordered table-hover table-condensed" data-order='[[ 9, "desc" ]]' data-page-length="10">
					<thead>
						<tr>
							<th>
								<i class="fa fa-key" aria-hidden="true"></i>
								&nbsp;메뉴 ID
							</th>
							<th>메뉴명</th>
							<th>메뉴종류</th>
							<th>URL</th>
							<th>출력순서</th>
							<th>상위메뉴</th>
							<th>사용여부</th>
							<th>권한</th>
							<th>수정자</th>
							<th>수정일</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
	<!-- Modal -->
	<div id="modalMenu" class="jmodal" style="display: none;">
		<div class="modal-header">
			<h3>메뉴 입력</h3>
		</div>
		<div class="modal-body">
			<form class="form-horizontal">
				<input type="hidden" id="menuId" />
				<div class="form-group">
					<label for="menuNm" class="col-sm-3 control-label">메뉴명</label>
					<div class="col-sm-9">
						<input type="text" id="menuNm" class="form-control" title="메뉴명" maxlength="30" />
					</div>
				</div>
				<div class="form-group">
					<label for="menuTyp" class="col-sm-3 control-label">메뉴종류</label>
					<div class="col-sm-9">
						<select id="menuTyp" class="form-control" title="메뉴종류" style="width: 70%" required></select>
					</div>
				</div>
				<div class="form-group">
					<label for="dispSeq" class="col-sm-3 control-label">출력순서</label>
					<div class="col-sm-9">
						<input type="text" id="dispSeq" class="form-control" title="출력순서" maxlength="10" data-parsley-type="digits" />
					</div>
				</div>
				<div class="form-group">
					<label for="parMenuId" class="col-sm-3 control-label">상위메뉴</label>
					<div class="col-sm-9">
						<select id="parMenuId" class="form-control" title="상위메뉴" style="width: 70%" required></select>
					</div>
				</div>
				<div class="form-group">
					<label for="url" class="col-sm-3 control-label">URL</label>
					<div class="col-sm-9">
						<input type="text" id="url" class="form-control" title="URL" maxlength="1000" />
					</div>
				</div>
				<div class="form-group">
					<label for="useYn" class="col-sm-3 control-label">사용여부</label>
					<div class="col-sm-9">
						<select id="useYn" class="form-control" title="사용여부" style="width: 30%" required></select>
					</div>
				</div>
				<div class="form-group">
					<label for="power" class="col-sm-3 control-label">권한</label>
					<div class="col-sm-9">
						<select id="power" class="form-control" title="권한" style="width: 30%" required></select>
					</div>
				</div>
			</form>
		</div>
		<div class="modal-footer">
			<div class="text-center">
				<div class="btn-group btn-group-padding" role="group" aria-label="...">
					<button type="button" class="btn btn-default" onclick="MyModal.close($('#modalMenu'));">
						<i class="fa fa-window-close-o" aria-hidden="true"></i>
					</button>
					<div class="btn-group" role="group">
						<button type="button" class="btn btn-warning dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
							<i class="fa fa-trash-o" aria-hidden="true"></i>
							<i class="fa fa-caret-down" aria-hidden="true"></i>
						</button>
						<ul class="dropdown-menu color-danger">
							<li class="text-center">
								<a href="javascript:void(0);" onclick="deleteMenu();">
									<i class="fa fa-trash" aria-hidden="true"></i>
								</a>
							</li>
						</ul>
					</div>
					<button type="button" class="btn btn-success" onclick="saveMenu();">
						<i class="fa fa-floppy-o" aria-hidden="true"></i>
					</button>
				</div>
			</div>
		</div>
	</div>
	<my:footer />
</body>
<script data-for="ready">
	$(document).ready(function() {
		$.when(drawTable1(), MyAjax.getSelectOptions($("#menuTyp"), "${CONTEXT_PATH}/common/valuelabel/getValueLabeVOList.json", {
			grcode : "MENU_TYP"
		}), MyAjax.getSelectOptions($("#useYn"), "${CONTEXT_PATH}/common/valuelabel/getValueLabeVOList.json", {
			grcode : "USE_YN"
		}), MyAjax.getSelectOptions($("#power"), "${CONTEXT_PATH}/common/valuelabel/getValueLabeVOList.json", {
			grcode : "MEMBER_TYP"
		}), MyAjax.getSelectOptions($("#parMenuId"), "${CONTEXT_PATH}/sample/admin/menu/getPMenuValueLableVOList.json")).done(function(response) {
			selectList();
		});
	});
</script>
<script data-for="define-table">
	function drawTable1() {
		let table = $('#table1').DataTable({
			dom : 'Bfrtip',
			language : MyDataTables.language,
			pagingType : "full_numbers",
			select : true,
			responsive : true,
			scrollY : '400px',
			fixedColumns : {
				leftColumns : 2
			// 	            ,rightColumns: 1
			},
			buttons : [ {
				text : '<i class="fa fa-plus" aria-hidden="true"></i>',
				titleAttr : '추가',
				action : function(e, dt, node, config) {
					modalAddMenu();
				}
			}, {
				text : '<i class="fa fa-pencil-square-o" aria-hidden="true"></i>',
				titleAttr : '수정',
				action : function(e, dt, node, config) {
					modalModifyMenu();
				}
			}, {
				text : '<i class="fa fa-trash-o" aria-hidden="true"></i>',
				titleAttr : '삭제',
				action : function(e, dt, node, config) {
					deleteMenu();
				}
			}, {
				extend : 'colvis',
				text : '<i class="fa fa-eye" aria-hidden="true"></i>',
				titleAttr : '컬럼선택'
			}, {
				extend : 'excelHtml5',
				exportOptions : {
					columns : ':visible'
				},
				title : '메뉴관리데이터_' + moment().format('YYYYMMDDHHmmss'),
				text : '<i class="fa fa-file-excel-o" aria-hidden="true"></i>xlsx - javascript',
				titleAttr : 'javascript'
			}, {
				text : '<i class="fa fa-file-excel-o" aria-hidden="true"></i>xlsx - java',
				titleAttr : 'java',
				action : function(e, dt, node, config) {
					let dataParam = {};
					MyAjax.downloadFile('${CONTEXT_PATH}/sample/admin/menu/adminMenu.xlsx', dataParam);
				}
			}, {
				text : '<i class="fa fa-file-excel-o" aria-hidden="true"></i>xlsx - 대용량 java',
				titleAttr : '대용량 java',
				action : function(e, dt, node, config) {
					let dataParam = {};
					MyAjax.downloadFile('${CONTEXT_PATH}/sample/admin/menu/adminMenuHugeExcel.xlsx', dataParam);
				}
			}, {
				text : '<i class="fa fa-file-pdf-o" aria-hidden="true"></i>pdf - java',
				titleAttr : 'java',
				action : function(e, dt, node, config) {
					let dataParam = {};
					MyAjax.downloadFile('${CONTEXT_PATH}/sample/admin/menu/adminMenu.pdf', dataParam);
				}
			}, {
				extend : 'print',
				exportOptions : {
					columns : ':visible'
				},
				title : '메뉴관리데이터_' + moment().format('YYYYMMDDHHmmss'),
				text : '<i class="fa fa-print" aria-hidden="true"></i>',
				titleAttr : 'print - table'
			} ],
			columnDefs : [ {
				targets : "_all",
				defaultContent : ""
			}, {
				targets : [ 3, 8, 9 ],
				visible : false
			}, {
				targets : 0,
				width : 100,
				className : "text-center"
			}, {
				targets : 2,
				width : 80,
				className : "text-center",
				render : function(data, type, row) {
					return MyCommon.getLabelFromSelectTag($('#menuTyp'), data);
				}
			}, {
				targets : 4,
				width : 80,
				className : "text-right"
			}, {
				targets : 5,
				render : function(data, type, row) {
					return MyCommon.getLabelFromSelectTag($('#parMenuId'), data);
				}
			}, {
				targets : 6,
				width : 80,
				className : "text-center",
				render : function(data, type, row) {
					return MyCommon.getLabelFromSelectTag($('#useYn'), data);
				}
			}, {
				targets : 7,
				width : 100,
				className : "text-right",
				render : function(data, type, row) {
					return MyCommon.getLabelFromSelectTag($('#power'), data) + " 이상";
				}
			}, {
				targets : 8,
				width : 100
			}, {
				targets : 9,
				width : 120,
				className : "text-center",
				render : function(data, type, row) {
					return moment(data).format("YYYY-MM-DD HH:mm");
				}
			} ],
			columns : [ {
				"data" : "menuId"
			}, {
				"data" : "menuNm"
			}, {
				"data" : "menuTyp"
			}, {
				"data" : "url"
			}, {
				"data" : "dispSeq"
			}, {
				"data" : "parMenuId"
			}, {
				"data" : "useYn"
			}, {
				"data" : "power"
			}, {
				"data" : "updMemberId"
			}, {
				"data" : "updDt"
			} ]
		});
		MyDataTables.drawPrettySearchInput($('#table1'));
	}
</script>
<script>
	function selectList() {
		MyAjax.excute('${CONTEXT_PATH}/sample/admin/menu/getSampleMenuMstVOList.json', {}).done(function(response) {
			$('#table1').DataTable().clear().rows.add(response).draw();
		});
	}
	function modalAddMenu() {
		$('form.form-horizontal')[0].reset();
		$('#modalMenu div.modal-footer i.fa-trash-o').parentsUntil('div.btn-group-padding').filter('div.btn-group').hide();
		MyModal.open($('#modalMenu')).done(function() {
			let selectedRow = $('#table1').DataTable().rows({
				selected : true
			}).data().toArray()[0];
			$('#parMenuId').val((selectedRow && selectedRow.parMenuId) || 10003);
			$('#power').val(300);
		});
	}
	function modalModifyMenu() {
		let selectedRow = $('#table1').DataTable().rows({
			selected : true
		}).data().toArray()[0];
		if (MyCommon.isEmpty(selectedRow)) {
			alert("수정하실 항목을 선택해주세요.");
			return;
		}
		$('form.form-horizontal')[0].reset();
		$('#modalMenu div.modal-footer i.fa-trash-o').parentsUntil('div.btn-group-padding').filter('div.btn-group').show();
		MyModal.open($('#modalMenu')).done(function() {
			$('#menuId').val(selectedRow.menuId);
			$('#menuNm').val(selectedRow.menuNm);
			$('#menuTyp').val(selectedRow.menuTyp);
			$('#url').val(selectedRow.url);
			$('#dispSeq').val(selectedRow.dispSeq);
			$('#useYn').val(selectedRow.useYn);
			$('#parMenuId').val(selectedRow.parMenuId);
			$('#power').val(selectedRow.power);
		});
	}
	function saveMenu() {
		if (MyValidator.validate($('form.form-horizontal'), true) !== null) {
			return;
		}
		let url;
		if ($('#modalMenu div.modal-footer i.fa-trash-o').parentsUntil('div.btn-group-padding').filter('div.btn-group').is(':hidden')) {
			url = '${CONTEXT_PATH}/sample/admin/menu/insertSampleMenuMst.json';
		} else {
			url = '${CONTEXT_PATH}/sample/admin/menu/updateSampleMenuMst.json';
		}
		MyAjax.excute(url, {
			menuId : $('#menuId').val(),
			menuNm : $('#menuNm').val(),
			menuTyp : $('#menuTyp > option:selected').val(),
			url : $('#url').val(),
			dispSeq : $('#dispSeq').val(),
			parMenuId : $('#parMenuId').val(),
			useYn : $('#useYn > option:selected').val(),
			power : $('#power').val()
		}, {
			autoResultFunctionTF : true
		}).done(function(response) {
			if (_.startsWith(response.responseCode, 'S')) {
				MyModal.close($('#modalMenu'));
				selectList();
			}
		});
	}
	function deleteMenu() {
		let selectedRow = $('#table1').DataTable().rows({
			selected : true
		}).data().toArray()[0];
		if (MyCommon.isEmpty(selectedRow)) {
			alert("삭제하실 항목을 선택해주세요.");
			return;
		}
		if (confirm("정말 삭제하시겠습니까?")) {
			MyAjax.excute('${CONTEXT_PATH}/sample/admin/menu/deleteSampleMenuMst.json', {
				menuId : selectedRow.menuId
			}, {
				autoResultFunctionTF : true
			}).done(function(response) {
				if (_.startsWith(response.responseCode, 'S')) {
					MyModal.close($('#modalMenu'));
					selectList();
				}
			});
		}
	}
</script>
</my:html>