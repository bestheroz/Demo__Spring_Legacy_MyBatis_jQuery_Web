;
const MyDataTables = {
	language : {
		"sEmptyTable" : "데이터가 없습니다",
		"sInfo" : "_START_ - _END_ / _TOTAL_",
		"sInfoEmpty" : "0 - 0 / 0",
		"sInfoFiltered" : "(총 _MAX_ 개)",
		"sInfoPostFix" : "",
		"sInfoThousands" : ",",
		"sLengthMenu" : "페이지당 줄수 _MENU_",
		"sLoadingRecords" : "읽는중...",
		"sProcessing" : "처리중...",
		"sSearch" : "검색:",
		"sZeroRecords" : "검색 결과가 없습니다",
		"oPaginate" : {
			"sFirst" : "처음",
			"sLast" : "마지막",
			"sNext" : "다음",
			"sPrevious" : "이전"
		},
		"oAria" : {
			"sSortAscending" : ": 오름차순 정렬",
			"sSortDescending" : ": 내림차순 정렬"
		},
		"decimal" : ",",
		"thousands" : "."
	},
	drawPrettySearchInput : function(targetObj) {
		if (MyCommon.isEmpty(targetObj)) {
			console.warn('MyDataTables.drawPrettySearchInput(targetObj) 잘못된 targetObj 값 에러 ==> ' + targetObj);
			return;
		}
		let $newTargetObj = $('#' + $(targetObj).attr('id') + '_filter');
		$newTargetObj.children('label').append('<div class="input-group"></div>');
		$newTargetObj.find('label > div.input-group').append('<div class="input-group-addon"><i class="fa fa-search" aria-hidden="true"></i></div>');
		$newTargetObj.find('label > div.input-group > div.input-group-addon').after($newTargetObj.find('label > input'));
		$($newTargetObj.children('label').contents()[0]).remove();
	}
};