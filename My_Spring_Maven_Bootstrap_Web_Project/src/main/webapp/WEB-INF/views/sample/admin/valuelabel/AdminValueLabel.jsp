<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@include file="/WEB-INF/include/include.jsp" %>
<my:html table="YES" modal="YES" validator="YES">
    <body>
    <my:menu/>
    <div class="page-header text-center">
        <h3>
            코드관리
            <small>
                For 관리자
                <i class="fa fa-wrench" aria-hidden="true"></i>
            </small>
        </h3>
    </div>
    <div class="container">
        <div class="panel panel-default">
            <div class="panel-body">
                <div class="row">
                    <div class="col-sm-6">
                        <table id="table1" class="table table-bordered table-hover table-condensed" data-order='[[ 3, "desc" ]]' data-page-length="10">
                            <thead>
                            <tr>
                                <th>
                                    <i class="fa fa-key" aria-hidden="true"></i>
                                    &nbsp;그룹코드
                                </th>
                                <th>그룹코드명</th>
                                <th>수정자</th>
                                <th>수정일</th>
                            </tr>
                            </thead>
                        </table>
                    </div>
                    <div class="col-sm-6">
                        <table id="table2" class="table table-bordered table-hover table-condensed" data-order='[[ 5, "desc" ]]' data-page-length="10">
                            <thead>
                            <tr>
                                <th>
                                    <i class="fa fa-key" aria-hidden="true"></i>
                                    &nbsp;코드
                                </th>
                                <th>코드명</th>
                                <th>사용여부</th>
                                <th>출력순서</th>
                                <th>수정자</th>
                                <th>수정일</th>
                            </tr>
                            </thead>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Modal -->
    <div id="modalValueLabel" class="jmodal" style="display: none;">
        <div class="modal-header">
            <h3>그룹코드 입력</h3>
        </div>
        <div class="modal-body">
            <form id="form1" class="form-horizontal">
                <div class="form-group">
                    <label for="grcode" class="col-sm-3 control-label">그룹코드</label>
                    <div class="col-sm-9">
                        <input type="text" id="grcode" class="form-control" title="그룹코드" maxlength="100"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="grcodeNm" class="col-sm-3 control-label">그룹코드명</label>
                    <div class="col-sm-9">
                        <input type="text" id="grcodeNm" class="form-control" title="그룹코드명" maxlength="300"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="remark1" class="col-sm-3 control-label">비고</label>
                    <div class="col-sm-9">
                        <input type="text" id="remark1" class="form-control" title="비고" maxlength="1000"/>
                    </div>
                </div>
            </form>
        </div>
        <div class="modal-footer">
            <div class="text-center">
                <div class="btn-group btn-group-padding" role="group" aria-label="...">
                    <button type="button" class="btn btn-default" onclick="MyModal.close($('#modalValueLabel'));">
                        <i class="fa fa-window-close-o" aria-hidden="true"></i>
                    </button>
                    <div class="btn-group" role="group">
                        <button type="button" class="btn btn-warning dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <i class="fa fa-trash-o" aria-hidden="true"></i>
                            <i class="fa fa-caret-down" aria-hidden="true"></i>
                        </button>
                        <ul class="dropdown-menu color-danger">
                            <li class="text-center">
                                <a href="javascript:void(0);" onclick="deleteValueLabel();">
                                    <i class="fa fa-trash" aria-hidden="true"></i>
                                </a>
                            </li>
                        </ul>
                    </div>
                    <button type="button" class="btn btn-success" onclick="saveValueLabel();">
                        <i class="fa fa-floppy-o" aria-hidden="true"></i>
                    </button>
                </div>
            </div>
        </div>
    </div>
    <div id="modalValueLabelDet" style="display: none;">
        <div class="modal-header">
            <h3>그룹코드-코드 입력</h3>
        </div>
        <div class="modal-body">
            <form id="form2" class="form-horizontal">
                <div class="form-group">
                    <label for="grcodeDet" class="col-sm-3 control-label">그룹코드</label>
                    <div class="col-sm-9">
                        <input type="text" id="grcodeDet" class="form-control" title="그룹코드" maxlength="100" readonly/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="code" class="col-sm-3 control-label">코드</label>
                    <div class="col-sm-9">
                        <input type="text" id="code" class="form-control" title="코드" maxlength="100"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="codeNm" class="col-sm-3 control-label">코드명</label>
                    <div class="col-sm-9">
                        <input type="text" id="codeNm" class="form-control" title="코드명" maxlength="300"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="useYn" class="col-sm-3 control-label">사용여부</label>
                    <div class="col-sm-9">
                        <select id="useYn" class="form-control" title="사용여부" style="width: 70%" required></select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="dispSeq" class="col-sm-3 control-label">출력순서</label>
                    <div class="col-sm-9">
                        <input type="text" id="dispSeq" class="form-control" title="출력순서" maxlength="10" data-parsley-type="digits"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="remark1Det" class="col-sm-3 control-label">비고</label>
                    <div class="col-sm-9">
                        <input type="text" id="remark1Det" class="form-control" title="비고" maxlength="1000"/>
                    </div>
                </div>
            </form>
        </div>
        <div class="modal-footer">
            <div class="text-center">
                <div class="btn-group btn-group-padding" role="group" aria-label="...">
                    <button type="button" class="btn btn-default" onclick="MyModal.close($('#modalValueLabelDet'));">
                        <i class="fa fa-window-close-o" aria-hidden="true"></i>
                    </button>
                    <div class="btn-group" role="group">
                        <button type="button" class="btn btn-warning dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <i class="fa fa-trash-o" aria-hidden="true"></i>
                            <i class="fa fa-caret-down" aria-hidden="true"></i>
                        </button>
                        <ul class="dropdown-menu color-danger">
                            <li class="text-center">
                                <a href="javascript:void(0);" onclick="deleteValueLabelDet();">
                                    <i class="fa fa-trash" aria-hidden="true"></i>
                                </a>
                            </li>
                        </ul>
                    </div>
                    <button type="button" class="btn btn-success" onclick="saveValueLabelDet();">
                        <i class="fa fa-floppy-o" aria-hidden="true"></i>
                    </button>
                </div>
            </div>
        </div>
    </div>
    <!-- /.modal --><my:footer/>
    </body>
    <script data-for="ready">
        $(document).ready(function () {
            $.when(drawTable1(), drawTable2(), MyAjax.getSelectOptions($("#useYn"), "${CONTEXT_PATH}/common/valuelabel/getValueLabeVOList.json", {
                grcode: "USE_YN"
            })).done(function (response) {
                selectList();
            });
            $('#table2_wrapper > div.dt-buttons > a').addClass('disabled');
            $('#table1').DataTable().on('select', function (e, dt, type, indexes) {
                let rowData = $('#table1').DataTable().rows(indexes).data().toArray()[0];
                $('#table2_wrapper > div.dt-buttons > a').removeClass('disabled');
                selectListDet(rowData.grcode);
            });
            $('#table1').DataTable().on('deselect', function (e, dt, type, indexes) {
                $('#table2_wrapper > div.dt-buttons > a').addClass('disabled');
                $('#table2').DataTable().clear().draw();
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
                        modalAddValueLabel();
                    }
                }, {
                    text: '<i class="fa fa-pencil-square-o" aria-hidden="true"></i>',
                    titleAttr: '수정',
                    action: function (e, dt, node, config) {
                        modalModifyValueLabel();
                    }
                }, {
                    text: '<i class="fa fa-trash-o" aria-hidden="true"></i>',
                    titleAttr: '삭제',
                    action: function (e, dt, node, config) {
                        deleteValueLabel();
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
                    title: '코드관리MST데이터_' + moment().format('YYYYMMDDHHmmss'),
                    text: '<i class="fa fa-file-excel-o" aria-hidden="true"></i>',
                    titleAttr: 'EXCEL'
                }, {
                    extend: 'pdfHtml5',
                    exportOptions: {
                        columns: ':visible'
                    },
                    title: '코드관리MST데이터_' + moment().format('YYYYMMDDHHmmss'),
                    text: '<i class="fa fa-file-pdf-o" aria-hidden="true"></i>',
                    titleAttr: 'PDF'
                }, {
                    extend: 'print',
                    exportOptions: {
                        columns: ':visible'
                    },
                    title: '코드관리MST데이터_' + moment().format('YYYYMMDDHHmmss'),
                    text: '<i class="fa fa-print" aria-hidden="true"></i>',
                    titleAttr: '프린트'
                }],
                columnDefs: [{
                    targets: "_all",
                    defaultContent: ""
                }, {
                    targets: [2, 3],
                    visible: false
                }, {
                    targets: 2,
                    width: 100
                }, {
                    targets: 3,
                    width: 120,
                    className: "text-center",
                    render: function (data, type, row) {
                        return moment(data).format("YYYY-MM-DD HH:mm");
                    }
                }],
                columns: [{
                    "data": "grcode"
                }, {
                    "data": "grcodeNm"
                }, {
                    "data": "updMemberId"
                }, {
                    "data": "updDt"
                }]
            });
            MyDataTables.drawPrettySearchInput($('#table1'));
        }

        function drawTable2() {
            let table = $('#table2').DataTable({
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
                        modalAddValueLabelDet();
                    }
                }, {
                    text: '<i class="fa fa-pencil-square-o" aria-hidden="true"></i>',
                    titleAttr: '수정',
                    action: function (e, dt, node, config) {
                        modalModifyValueLabelDet();
                    }
                }, {
                    text: '<i class="fa fa-trash-o" aria-hidden="true"></i>',
                    titleAttr: '삭제',
                    action: function (e, dt, node, config) {
                        deleteValueLabelDet();
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
                    title: '코드관리DET데이터_' + moment().format('YYYYMMDDHHmmss'),
                    text: '<i class="fa fa-file-excel-o" aria-hidden="true"></i>',
                    titleAttr: 'EXCEL'
                }, {
                    extend: 'pdfHtml5',
                    exportOptions: {
                        columns: ':visible'
                    },
                    title: '코드관리DET데이터_' + moment().format('YYYYMMDDHHmmss'),
                    text: '<i class="fa fa-file-pdf-o" aria-hidden="true"></i>',
                    titleAttr: 'PDF'
                }, {
                    extend: 'print',
                    exportOptions: {
                        columns: ':visible'
                    },
                    title: '코드관리DET데이터_' + moment().format('YYYYMMDDHHmmss'),
                    text: '<i class="fa fa-print" aria-hidden="true"></i>',
                    titleAttr: '프린트'
                }],
                columnDefs: [{
                    targets: "_all",
                    defaultContent: ""
                }, {
                    targets: [4, 5],
                    visible: false
                }, {
                    targets: 2,
                    width: 80,
                    className: "text-center",
                    render: function (data, type, row) {
                        return MyCommon.getLabelFromSelectTag($('#useYn'), data);
                    }
                }, {
                    targets: 3,
                    width: 60,
                    className: "text-right"
                }, {
                    targets: 4,
                    width: 100
                }, {
                    targets: 5,
                    width: 120,
                    className: "text-center",
                    render: function (data, type, row) {
                        return moment(data).format("YYYY-MM-DD HH:mm");
                    }
                }],
                columns: [{
                    "data": "code"
                }, {
                    "data": "codeNm"
                }, {
                    "data": "useYn"
                }, {
                    "data": "dispSeq"
                }, {
                    "data": "updMemberId"
                }, {
                    "data": "updDt"
                }]
            });
            MyDataTables.drawPrettySearchInput($('#table2'));
        }
    </script>
    <script>
        function selectList() {
            MyAjax.excute('${CONTEXT_PATH}/sample/admin/valuelabel/getSampleCodeMstVOList.json', {}).done(function (response) {
                $('#table1').DataTable().clear().rows.add(response).draw();
            });
        }

        function selectListDet(grcode) {
            MyAjax.excute('${CONTEXT_PATH}/sample/admin/valuelabel/getSampleCodeDetVOList.json', {
                grcode: grcode
            }).done(function (response) {
                $('#table2').DataTable().clear().rows.add(response).draw();
            });
        }

        function modalAddValueLabel() {
            $('#form1')[0].reset();
            $('#modalValueLabel div.modal-footer i.fa-trash-o').parentsUntil('div.btn-group-padding').filter('div.btn-group').hide();
            MyModal.open($('#modalValueLabel')).done(function () {
                $('#grcode').prop('readonly', false);
            });
        }

        function modalModifyValueLabel() {
            let selectedRow = $('#table1').DataTable().rows({
                selected: true
            }).data().toArray()[0];
            if (MyCommon.isEmpty(selectedRow)) {
                alert("수정하실 항목을 선택해주세요.");
                return;
            }
            $('#form1')[0].reset();
            $('#modalValueLabel div.modal-footer i.fa-trash-o').parentsUntil('div.btn-group-padding').filter('div.btn-group').show();
            MyModal.open($('#modalValueLabel')).done(function () {
                $('#grcode').val(selectedRow.grcode).prop('readonly', true);
                $('#grcodeNm').val(selectedRow.grcodeNm);
                $('#remark1').val(selectedRow.remark1);
            });
        }

        function saveValueLabel() {
            if (MyValidator.validate($('#form1'), true) !== null) {
                return;
            }
            let url;
            if ($('#modalValueLabel div.modal-footer i.fa-trash-o').parentsUntil('div.btn-group-padding').filter('div.btn-group').is(':hidden')) {
                url = '${CONTEXT_PATH}/sample/admin/valuelabel/insertSampleCodeMst.json';
            } else {
                url = '${CONTEXT_PATH}/sample/admin/valuelabel/updateSampleCodeMst.json';
            }
            MyAjax.excute(url, {
                grcode: $('#grcode').val(),
                grcodeNm: $('#grcodeNm').val(),
                remark1: $('#remark1').val()
            }, {
                autoResultFunctionTF: true
            }).done(function (response) {
                if (_.startsWith(response.responseCode, 'S')) {
                    MyModal.close($('#modalValueLabel'));
                    selectList();
                    $('#table2').DataTable().clear().draw();
                }
            });
        }

        function deleteValueLabel() {
            let selectedRow = $('#table1').DataTable().rows({
                selected: true
            }).data().toArray()[0];
            if (MyCommon.isEmpty(selectedRow)) {
                alert("삭제하실 항목을 선택해주세요.");
                return;
            }
            if (confirm("정말 삭제하시겠습니까?")) {
                MyAjax.excute('${CONTEXT_PATH}/sample/admin/valuelabel/deleteCOMM_CODE.json', {
                    grcode: selectedRow.grcode
                }, {
                    autoResultFunctionTF: true
                }).done(function (response) {
                    if (_.startsWith(response.responseCode, 'S')) {
                        MyModal.close($('#modalValueLabel'));
                        selectList();
                        $('#table2').DataTable().clear().draw();
                    }
                });
            }
        }

        function modalAddValueLabelDet() {
            let selectedRow = $('#table1').DataTable().rows({
                selected: true
            }).data().toArray()[0];
            if (MyCommon.isEmpty(selectedRow)) {
                alert("추가하실 그룹코드 항목을 우선적으로 선택해주세요.");
                return;
            }
            $('#form2')[0].reset();
            $('#modalValueLabelDet div.modal-footer i.fa-trash-o').parentsUntil('div.btn-group-padding').filter('div.btn-group').hide();
            MyModal.open($('#modalValueLabelDet')).done(function () {
                $('#code').prop('readonly', false);
                $('#grcodeDet').val(selectedRow.grcode);
            });
        }

        function modalModifyValueLabelDet() {
            let selectedRow = $('#table2').DataTable().rows({
                selected: true
            }).data().toArray()[0];
            if (MyCommon.isEmpty(selectedRow)) {
                alert("수정하실 항목을 선택해주세요.");
                return;
            }
            let selectedRow1 = $('#table1').DataTable().rows({
                selected: true
            }).data().toArray()[0];
            if (MyCommon.isEmpty(selectedRow1)) {
                alert("추가하실 그룹코드 항목을 우선적으로 선택해주세요.");
                return;
            }
            $('#form2')[0].reset();
            $('#modalValueLabelDet div.modal-footer i.fa-trash-o').parentsUntil('div.btn-group-padding').filter('div.btn-group').show();
            MyModal.open($('#modalValueLabelDet')).done(function () {
                $('#code').prop('readonly', true);
                $('#grcodeDet').val(selectedRow1.grcode);
                $('#code').val(selectedRow.code);
                $('#codeNm').val(selectedRow.codeNm);
                $('#useYn').val(selectedRow.useYn);
                $('#dispSeq').val(selectedRow.dispSeq);
                $('#remark1Det').val(selectedRow.remark1);
            });
        }

        function saveValueLabelDet() {
            if (MyValidator.validate($('#form2'), true) !== null) {
                return;
            }
            let url;
            if ($('#modalValueLabelDet div.modal-footer i.fa-trash-o').parentsUntil('div.btn-group-padding').filter('div.btn-group').is(':hidden')) {
                url = '${CONTEXT_PATH}/sample/admin/valuelabel/insertSampleCodeDet.json';
            } else {
                url = '${CONTEXT_PATH}/sample/admin/valuelabel/updateSampleCodeDet.json';
            }
            MyAjax.excute(url, {
                grcode: $('#grcodeDet').val(),
                code: $('#code').val(),
                codeNm: $('#codeNm').val(),
                useYn: $('#useYn > option:selected').val(),
                dispSeq: $('#dispSeq').val(),
                remark1: $('#remark1Det').val()
            }, {
                autoResultFunctionTF: true
            }).done(function (response) {
                if (_.startsWith(response.responseCode, 'S')) {
                    MyModal.close($('#modalValueLabelDet'));
                    selectListDet($('#grcodeDet').val());
                }
            });
        }

        function deleteValueLabelDet() {
            let selectedRow = $('#table2').DataTable().rows({
                selected: true
            }).data().toArray()[0];
            if (MyCommon.isEmpty(selectedRow)) {
                alert("삭제하실 항목을 선택해주세요.");
                return;
            }

            if (confirm("정말 삭제하시겠습니까?")) {
                MyAjax.excute('${CONTEXT_PATH}/sample/admin/valuelabel/deleteSampleCodeDet.json', {
                    grcode: selectedRow.grcode,
                    code: selectedRow.code
                }, {
                    autoResultFunctionTF: true
                }).done(function (response) {
                    if (_.startsWith(response.responseCode, 'S')) {
                        MyModal.close($('#modalValueLabelDet'));
                        selectListDet(selectedRow.grcode);
                    }
                });
            }
        }
    </script>
</my:html>