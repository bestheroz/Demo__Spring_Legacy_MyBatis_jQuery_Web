<%@ page session="true" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/include/include.jsp" %>

<my:html table="YES" modal="YES" validator="YES">
    <body>
    <my:menu/>
    <div class="page-header text-center">
        <h3>
            회원관리
            <small>
                For 관리자
                <i class="fa fa-wrench" aria-hidden="true"></i>
            </small>
        </h3>
    </div>
    <div class="container">
        <div class="panel panel-default">
            <div class="panel-body">
                <table id="table1" class="table table-bordered table-hover table-condensed" data-order='[[ 7, "desc" ]]' data-page-length="10">
                    <thead>
                    <tr>
                        <th>
                            <i class="fa fa-key" aria-hidden="true"></i>
                            &nbsp;회원ID
                        </th>
                        <th>회원명</th>
                        <th>회원종류</th>
                        <th>로그인실패횟수</th>
                        <th>계정잠금여부</th>
                        <th>
                            <i class="fa fa-lock" aria-hidden="true"></i>
                            &nbsp;계정만료일
                        </th>
                        <th>수정자</th>
                        <th>수정일</th>
                    </tr>
                    </thead>
                </table>
            </div>
        </div>
    </div>
    <!-- Modal -->
    <div id="modalMember" class="jmodal" style="display: none;">
        <div class="modal-header">
            <h3>회원 입력</h3>
        </div>
        <div class="modal-body">
            <form class="form-horizontal">
                <div class="form-group">
                    <label for="memberId" class="col-sm-3 control-label">회원ID</label>
                    <div class="col-sm-9">
                        <input type="text" id="memberId" class="form-control" title="회원" maxlength="20"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="memberNm" class="col-sm-3 control-label">회원명</label>
                    <div class="col-sm-9">
                        <input type="text" id="memberNm" class="form-control" title="회원명" maxlength="30"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="memberPw" class="col-sm-3 control-label">비밀번호</label>
                    <div class="col-sm-9">
                        <input type="password" id="memberPw" class="form-control" title="비밀번호" data-parsley-minlength="5" maxlength="20"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="memberPw2" class="col-sm-3 control-label">비밀번호(확인용)</label>
                    <div class="col-sm-9">
                        <input type="password" id="memberPw2" class="form-control" title="비밀번호(확인용)" data-parsley-minlength="5" maxlength="20"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="memberTyp" class="col-sm-3 control-label">회원종류</label>
                    <div class="col-sm-9">
                        <select id="memberTyp" class="form-control" title="회원종류" style="width: 70%" required></select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="loginFailCnt" class="col-sm-3 control-label">로그인 실패 횟수</label>
                    <div class="col-sm-9">
                        <input type="number" id="loginFailCnt" class="form-control" title="비밀번호(확인용)" data-parsley-minlength="1" maxlength="10"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="closeYn" class="col-sm-3 control-label">계정잠금여부</label>
                    <div class="col-sm-9">
                        <select id="closeYn" class="form-control" title="계정잠금여부" style="width: 30%" required></select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="expireDt" class="col-sm-3 control-label">계정사용 만기일</label>
                    <div class="col-sm-9">
                        <input type="text" id="expireDt" class="form-control datetimepicker" title="계정사용 만기일"/>
                    </div>
                </div>
            </form>
        </div>
        <div class="modal-footer">
            <div class="text-center">
                <div class="btn-group btn-group-padding" role="group" aria-label="...">
                    <button type="button" class="btn btn-default" onclick="MyModal.close($('#modalMember'));">
                        <i class="fa fa-window-close-o" aria-hidden="true"></i>
                    </button>
                    <div class="btn-group" role="group">
                        <button type="button" class="btn btn-warning dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <i class="fa fa-trash-o" aria-hidden="true"></i>
                            <i class="fa fa-caret-down" aria-hidden="true"></i>
                        </button>
                        <ul class="dropdown-menu color-danger">
                            <li class="text-center">
                                <a href="javascript:void(0);" onclick="deleteMember();">
                                    <i class="fa fa-trash" aria-hidden="true"></i>
                                </a>
                            </li>
                        </ul>
                    </div>
                    <button type="button" class="btn btn-success" onclick="saveMember();">
                        <i class="fa fa-floppy-o" aria-hidden="true"></i>
                    </button>
                </div>
            </div>
        </div>
    </div>
    <!-- /.modal -->
    <my:footer/>
    </body>
    <script data-for="ready">
        $(document).ready(function () {
            $.when(drawTable1(), MyAjax.getSelectOptions($("#memberTyp"), "${CONTEXT_PATH}/common/valuelabel/getValueLabeVOList.json", {
                grcode: "MEMBER_TYP"
            }), MyAjax.getSelectOptions($("#closeYn"), "${CONTEXT_PATH}/common/valuelabel/getValueLabeVOList.json", {
                grcode: "USE_YN_REVERSE"
            })).done(function (response) {
                selectList();
            });
        });
    </script>
    <script data-for="define-table">
        function drawTable1() {
            let table = $('#table1').DataTable({
                dom: 'Bfrtip',
                lengthChange: false,
                language: MyDataTables.language,
                pagingType: "full_numbers",
                select: true,
                responsive: true,
                scrollY: '400px',
                fixedColumns: {
                    leftColumns: 2
                    // 	            ,rightColumns: 1
                },
                buttons: [{
                    text: '<i class="fa fa-plus" aria-hidden="true"></i>',
                    titleAttr: '추가',
                    action: function (e, dt, node, config) {
                        modalAddMember();
                    }
                }, {
                    text: '<i class="fa fa-pencil-square-o" aria-hidden="true"></i>',
                    titleAttr: '수정',
                    action: function (e, dt, node, config) {
                        modalModifyMember();
                    }
                }, {
                    text: '<i class="fa fa-trash-o" aria-hidden="true"></i>',
                    titleAttr: '삭제',
                    action: function (e, dt, node, config) {
                        deleteMember();
                    }
                }, {
                    extend: 'colvis',
                    text: '<i class="fa fa-eye" aria-hidden="true"></i>',
                    titleAttr: '컬럼선택'
                }, {
                    extend: 'excelHtml5',
                    exportOptions: {
                        columns: ':visible'
                    },
                    title: '회원관리데이터_' + moment().format('YYYYMMDDHHmmss'),
                    text: '<i class="fa fa-file-excel-o" aria-hidden="true"></i>',
                    titleAttr: 'EXCEL'
                }, {
                    extend: 'print',
                    exportOptions: {
                        columns: ':visible'
                    },
                    title: '회원관리데이터_' + moment().format('YYYYMMDDHHmmss'),
                    text: '<i class="fa fa-print" aria-hidden="true"></i>',
                    titleAttr: '프린트'
                }],
                columnDefs: [{
                    targets: "_all",
                    defaultContent: ""
                }, {
                    targets: [6, 7],
                    visible: false
                }, {
                    targets: 2,
                    width: 100,
                    className: "text-center",
                    render: function (data, type, row) {
                        return MyCommon.getLabelFromSelectTag($('#memberTyp'), data);
                    }
                }, {
                    targets: 3,
                    width: 110,
                    className: "text-right"
                }, {
                    targets: 4,
                    width: 100,
                    className: "text-center",
                    render: function (data, type, row) {
                        return MyCommon.getLabelFromSelectTag($('#closeYn'), data);
                    }
                }, {
                    targets: 5,
                    width: 120,
                    className: "text-center",
                    render: function (data, type, row) {
                        return moment(data).format("YYYY-MM-DD HH:mm");
                    }
                }, {
                    targets: 6,
                    width: 100
                }, {
                    targets: 7,
                    width: 120,
                    className: "text-center",
                    render: function (data, type, row) {
                        return moment(data).format("YYYY-MM-DD HH:mm");
                    }
                }],
                columns: [{
                    "data": "memberId"
                }, {
                    "data": "memberNm"
                }, {
                    "data": "memberTyp"
                }, {
                    "data": "loginFailCnt"
                }, {
                    "data": "closeYn"
                }, {
                    "data": "expireDt"
                }, {
                    "data": "updMemberId"
                }, {
                    "data": "updDt"
                }]
            });
            MyDataTables.drawPrettySearchInput($('#table1'));
        }
    </script>
    <script>
        function selectList() {
            MyAjax.excute('${CONTEXT_PATH}/sample/admin/member/getSampleMemberMstVOList.json', {}).done(function (response) {
                $('#table1').DataTable().clear().rows.add(response).draw();
            });
        }

        function saveMember() {
            if (MyValidator.validate($('form.form-horizontal'), true) !== null) {
                return;
            }
            if (!_.isEqual($('#memberPw').val(), $('#memberPw2').val())) {
                alert("입력하신 비밀번호와 비밀번호(확인용)이 서로 같지 않습니다.");
                return;
            }
            let memberPw;
            if (MyCommon.isEmpty(_.trim($('#memberPw').val()))) {
                memberPw = null;
            } else {
                memberPw = CryptoJS.SHA512($('#memberPw').val()).toString();
            }
            let url;
            if ($('#modalMember div.modal-footer i.fa-trash-o').parentsUntil('div.btn-group-padding').filter('div.btn-group').is(':hidden')) {
                url = '${CONTEXT_PATH}/sample/admin/member/insertSampleMemberMst.json';
            } else {
                url = '${CONTEXT_PATH}/sample/admin/member/updateSampleMemberMst.json';
            }
            MyAjax.excute(url, {
                memberId: $('#memberId').val(),
                memberNm: $('#memberNm').val(),
                memberPw: memberPw,
                memberTyp: $('#memberTyp option:selected').val(),
                loginFailCnt: $('#loginFailCnt').val(),
                closeYn: $('#closeYn option:selected').val(),
                expireDt: moment($('#expireDt').data("DateTimePicker").date().format("YYYYMMDD HHmm59")).valueOf()
            }, {
                autoResultFunctionTF: true
            }).done(function (response) {
                if (_.startsWith(response.responseCode, 'S')) {
                    MyModal.close($('#modalMember'));
                    selectList();
                }
            });
        }

        function deleteMember() {
            let selectedRow = $('#table1').DataTable().rows({
                selected: true
            }).data().toArray()[0];
            if (MyCommon.isEmpty(selectedRow)) {
                alert("삭제하실 항목을 선택해주세요.");
                return;
            }
            if (confirm("정말 삭제하시겠습니까?")) {
                MyAjax.excute('${CONTEXT_PATH}/sample/admin/member/deleteSampleMemberMst.json', {
                    memberId: selectedRow.memberId
                }, {
                    autoResultFunctionTF: true
                }).done(function (response) {
                    if (_.startsWith(response.responseCode, 'S')) {
                        MyModal.close($('#modalMember'));
                        selectList();
                    }
                });
            }
        }

        function modalAddMember() {
            $('form.form-horizontal')[0].reset();
            $('#modalMember div.modal-footer i.fa-trash-o').parentsUntil('div.btn-group-padding').filter('div.btn-group').hide();
            MyModal.open($('#modalMember')).done(function () {
                $('#memberId').attr('disabled', false);
                $('#loginFailCnt').val(0);
                $('#expireDt').val(moment().add(1, 'years').format('YYYY-MM-DD 23:59:59')).trigger('change');
            });
        }

        function modalModifyMember() {
            let selectedRow = $('#table1').DataTable().rows({
                selected: true
            }).data().toArray()[0];
            if (MyCommon.isEmpty(selectedRow)) {
                alert("수정하실 항목을 선택해주세요.");
                return;
            }
            $('form.form-horizontal')[0].reset();
            $('#modalMember div.modal-footer i.fa-trash-o').parentsUntil('div.btn-group-padding').filter('div.btn-group').show();
            MyModal.open($('#modalMember')).done(function () {
                $('#memberId').val(selectedRow.memberId).attr('disabled', true);
                $('#memberNm').val(selectedRow.memberNm);
                $('#memberTyp').val(selectedRow.memberTyp);
                $('#loginFailCnt').val(selectedRow.loginFailCnt);
                $('#closeYn').val(selectedRow.closeYn);
                $('#expireDt').val(moment(selectedRow.expireDt).format('YYYY-MM-DD HH:mm')).trigger('change');
            });
        }
    </script>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/3.1.9-1/crypto-js.min.js"></script>
</my:html>
