$(document).ready(function () {
    $('#datagrid1').treegrid({
        title:'人员查询',
        iconCls:'icon-search',
        url:ctx + "/empInfo/getEmpTree",
        method: 'post',
        idField:'empId',
        treeField:'empId',
        showFooter:true,
        columns:[[
            {
                field: 'empId',
                title: '编号',
                width: 50,
                align: 'center'
            },
            {
                field: 'empName',
                title: '名称',
                width: 40,
                align: 'center'
            }
        ]],
        fitColumns: 'true',
        fit:true,
        animate:true,
        border: false,
        loadFilter: function (data, parentId) {
            var list = data.rows;
            for (var i = 0; i < list.length; i++) {
                var parent = list[i];
                parent._parentId = parent.parentId;
            }
            return data;
        },
        toolbar: '#tb',
        onClickRow:function (row) {
            if(row.parentId != null){
                var data = {
                    empId: row.empId,
                    isAllow: 0
                };
                $('#datagrid3').datagrid('load', data);
                var data1 = {
                    empId: row.empId
                };
                $('#datagrid2').datagrid('load', data1);
            }
        }
    });
});


$("#searchBtn").click(function () {
    var data = {
        empName: $('#Eq').val()
    };
    $('#datagrid1').treegrid('load', data);
});

$("#searchAllBtn").click(function () {
    var data = {};
    $('#datagrid1').treegrid('load', data);
    $('#datagrid1').treegrid('clearSelections');
    $('#datagrid2').datagrid('load', data);
    var data = {isAllow: 0};
    $('#datagrid3').datagrid('load', data);
});

$('#datagrid3').datagrid({
    url: ctx + "/welfare/getBySearch",
    idField: 'serialId',
    striped: true,
    singleSelect: true,
    pageSize: 10,
    border: false,
    pagination: true,
    toolbar: '#tb1',
    queryParams: {
        isAllow: 0
    },
    columns: [[
        {
            field: 'empId',
            title: '员工编号',
            width: 70,
            align: 'center'
        },
        {
            field: 'empName',
            title: '姓名',
            width: 70,
            align: 'center'
        },
        {
            field: 'applyTime',
            title: '申请日期',
            width: 100,
            align: 'center',
            sortable:true
        },
        {
            field: 'applyReason',
            title: '申请原因',
            width: 100,
            align: 'center'
        },
        {
            field: 'welfareTotal',
            title: '总金额(￥)',
            width: 70,
            align: 'center',
            sortable:true
        },
        {
            field: 'welfareType',
            title: '福利类型',
            width: 60,
            formatter: function(value,row,index){
                if (row.welfareType == 1){
                    return '津贴';
                } else if (row.welfareType == 2){
                    return '补贴';
                }
            },
            align: 'center',
            sortable:true
        },
        {
            field:'operate',title:'操作',align:'center',width:100,
            formatter:function(value, row, index){
                var str = '<a href="javascript:;" onclick="pass('+index+')" name="pass" id="pass" class="easyui-linkbutton" >通过</a><a href="javascript:;" onclick="nopass('+index+')" name="nopass" id="nopass" class="easyui-linkbutton" >不通过</a>';
                return str;
            }
        }
    ]],
    fitColumns: 'true',
    fit:true,
    onLoadSuccess:function(data){
        $("a[name='pass']").linkbutton({plain:true,iconCls:'icon-ok'});
        $("a[name='nopass']").linkbutton({plain:true,iconCls:'icon-no'});
        $('#applyDatagrid').datagrid('clearSelections');
    },
    title:'未审批福利记录'
});
function loadApply() {
    $('#applyHisDatagrid').datagrid({
        url: ctx + "/welfare/getBySearch",
        idField: 'serialId',
        striped: true,
        singleSelect: true,
        pageSize: 10,
        border: false,
        pagination: true,
        queryParams: {
            isAllow: 3
        },
        columns: [[
            {
                field: 'empId',
                title: '员工编号',
                width: 60,
                align: 'center'
            },
            {
                field: 'empName',
                title: '姓名',
                width: 60,
                align: 'center'
            },
            {
                field: 'applyTime',
                title: '申请日期',
                width: 100,
                align: 'center'
            },
            {
                field: 'applyReason',
                title: '申请原因',
                width: 100,
                align: 'center'
            },
            {
                field: 'welfareTotal',
                title: '总金额(￥)',
                width: 70,
                align: 'center'
            },
            {
                field: 'welfareType',
                title: '福利类型',
                width: 70,
                formatter: function (value, row, index) {
                    if (row.welfareType == 1) {
                        return '津贴';
                    } else if (row.welfareType == 2) {
                        return '补贴';
                    }
                },
                align: 'center'
            },
            {
                field: 'isAllow',
                title: '是否批准',
                width: 60,
                formatter: function (value, row, index) {
                    if (row.isAllow == 1) {
                        return '批准';
                    } else if (row.isAllow == 2) {
                        return '未批准';
                    }
                },
                align: 'center'
            },
            {
                field: 'approverName',
                title: '审批人员',
                width: 60,
                align: 'center'
            },
            {
                field: 'approverTime',
                title: '审批时间',
                width: 100,
                align: 'center'
            }
        ]],
        fitColumns: 'true',
        fit: true,
        title: '已审批福利表'
    });
};

$('#applyModal').on('shown.bs.modal', function (e) {
    $("#chart6").css('display','block');
    loadApply();
});

$('#applyModal').on('hide.bs.modal', function (e) {
    $("#chart6").css('display','none');
});

laydate.render({
    elem: '#applyTimeMin'
    ,type: 'date'
    ,theme: DATA_COLOR.BLACK
});
laydate.render({
    elem: '#applyTimeMax'
    ,type: 'date'
    ,theme: DATA_COLOR.BLACK
});
laydate.render({
    elem: '#effectiveDateMin'
    ,type: 'date'
    ,theme: DATA_COLOR.BLACK
});
laydate.render({
    elem: '#effectiveDateMax'
    ,type: 'date'
    ,theme: DATA_COLOR.BLACK
});
laydate.render({
    elem: '#effectiveDate'
    ,type: 'datetime'
    ,theme: DATA_COLOR.BLACK
});
$('#welfareTotalMin').numberbox({
    min:0,
    precision:2,
    prefix:'￥',
    prompt:'请输入',
    height:25
});
$('#welfareTotalMax').numberbox({
    min:0,
    precision:2,
    prefix:'￥',
    prompt:'请输入',
    height:25
});
$('#moneyMin').numberbox({
    min:0,
    precision:2,
    prefix:'￥',
    prompt:'请输入',
    height:25
});
$('#moneyMax').numberbox({
    min:0,
    precision:2,
    prefix:'￥',
    prompt:'请输入',
    height:25
});
$('#type').combobox({
    height:25,
    prompt:'请选择'
});
$('#welfareType').combobox({
    height:25,
    prompt:'请选择'
});

$("#searchBtn1").click(function () {
    var row = $('#datagrid1').treegrid('getSelected');
    if(row != null){
        $('#empId2').val(row.empId);
    }
    $('#isAllow').val('0');
    var data = $("#searchAllow").serializeObject();
    $('#datagrid3').datagrid('load', data);
});
$("#clearBtn").click(function () {
    $('#searchAllow')[0].reset();
    $('#welfareTotalMin').numberbox('clear');
    $('#welfareTotalMax').numberbox('clear');
    $('#welfareType').numberbox('clear');
});

$("#applyHisBtn").click(function () {
    $('#applyModal').modal('show');
});

$('#searchBtn2').click(function () {
    var row = $('#datagrid1').treegrid('getSelected');
    if(row != null){
        $('#empId3').val(row.empId);
    }
    var data = $("#searchWelfare").serializeObject();
    $('#datagrid2').datagrid('load', data);
});

$("#clearBtn1").click(function () {
    $('#searchWelfare')[0].reset();
    $('#moneyMin').numberbox('clear');
    $('#moneyMax').numberbox('clear');
    $('#type').numberbox('clear');
});

$("#addBtn1").click(function () {
    $('#addModal').modal('show');
});
$("#empIdBtn").click(function () {
    var p = $("#layout").layout("panel", "east")[0].clientWidth;
    if(p != 0){
        $("#layout").layout("collapse", 'east');
    }
});

function pass(index) {
    var row = $('#datagrid3').datagrid('getData').rows[index];
    $.ajax({
        type: "GET",
        url: ctx + "/welfare/updateWelfare?serialId="+row.serialId+"&isAllow=1",
        dataType: "json",
        success: function (result) {
            $('#datagrid3').datagrid('reload');
            $('#datagrid2').datagrid('reload');
        },
        error:function (result) {
            toastr.error('系统异常，请联系管理员！');
        }
    });
};

function nopass(index) {
    var row = $('#datagrid3').datagrid('getData').rows[index];
    $.ajax({
        type: "GET",
        url: ctx + "/welfare/updateWelfare?serialId="+row.serialId+"&isAllow=2",
        dataType: "json",
        success: function (result) {
            $('#datagrid3').datagrid('reload');
            $('#datagrid2').datagrid('reload');
        },
        error:function (result) {
            toastr.error('系统异常，请联系管理员！');
        }
    });
};


$('#datagrid2').datagrid({
    url: ctx + "/welfare/getEmpWelBySearch",
    idField: 'id',
    striped: true,
    singleSelect: true,
    pageSize: 10,
    border: false,
    pagination: true,
    toolbar: '#tb2',
    columns: [[
        {
            field: 'empId',
            title: '员工编号',
            width: 70,
            align: 'center'
        },
        {
            field: 'empName',
            title: '姓名',
            width: 70,
            align: 'center'
        },
        {
            field: 'effectiveDate',
            title: '生效日期',
            width: 100,
            align: 'center',
            sortable:true
        },
        {
            field: 'reason',
            title: '原因',
            width: 100,
            align: 'center'
        },
        {
            field: 'money',
            title: '金额(￥)',
            width: 70,
            align: 'center',
            sortable:true
        },
        {
            field: 'type',
            title: '福利类型',
            width: 70,
            formatter: function(value,row,index){
                if (row.type == 1){
                    return '津贴';
                } else if (row.type == 2){
                    return '补贴';
                } else if (row.type == 3){
                    return '奖金';
                } else if (row.type == 4){
                    return '年终奖金';
                }
            },
            align: 'center',
            sortable:true
        },
        {
            field:'operate',title:'操作',align:'center',width:70,
            formatter:function(value, row, index){
                var str = '<a href="javascript:;" onclick="updateOnClick('+index+')" name="update" class="easyui-linkbutton" ></a><a href="javascript:;" onclick="removeOnClick('+row.id+')" name="delete" class="easyui-linkbutton" ></a>';
                return str;
            }
        }
    ]],
    fitColumns: 'true',
    fit:true,
    title:'已生效员工福利表',
    onLoadSuccess:function(data){
        $("a[name='update']").linkbutton({plain:true,iconCls:'icon-edit'});
        $("a[name='delete']").linkbutton({plain:true,iconCls:'icon-remove'});
    }
});

function removeOnClick(id) {
    Ewin.confirm({ message: "确认要删除选择的数据吗？" }).on(function (e) {
        if (!e) {
            return;
        }else{
            $.ajax({
                type: "GET",
                url: ctx + "/welfare/removeEmpWelfare?id="+id,
                dataType: "json",
                success: function (result) {
                    toastr.success('删除'+result.message);
                    $('#datagrid2').datagrid('reload');
                },
                error:function (result) {
                    toastr.error('删除失败，请联系管理员！');
                }
            });
        }
    });
};
function updateOnClick(index) {
    var row = $('#datagrid2').datagrid('getData').rows[index];
    $("#empId").val(row.empId);
    $("#empName").val(row.empName);
    $("#money").val(row.money);
    $("#type1").val(row.type);
    $("#effectiveDate").val(row.effectiveDate);
    $("#reason").val(row.reason);
    $("#id1").val(row.id);
    $('#updateModal').modal('show');
};

function openEmp() {
    $("#empIdBtn").show();
};
function turnoffEmp() {
    $("#empIdBtn").hide();
};

$("#sumbitUpdate").click(function () {
    if($("#money").val().length < 1){
        toastr.error('请输入金额！');
        return false;
    }
    if( Number($("#money").val()) <0){
        toastr.error('金额不能小于0！');
        return false;
    }
    if($("#type1").val().length < 1){
        toastr.error('请选择类型！');
        return false;
    }
    if(!checkDateTime($("#effectiveDate").val())){
        toastr.error('生效时间格式不正确，请检查输入！');
        return false;
    }
    if($("#reason").val().length < 1){
        toastr.error('请输入原因！');
        return false;
    }else {
        $.ajax({
            type: "POST",
            url: ctx + "/welfare/updateEmpWelfare",
            dataType: "json",
            data: $("#updateForm").serializeObject(),
            success: function (result) {
                toastr.success('保存'+result.message);
                $('#datagrid2').datagrid('reload');
                $('#updateModal').modal('hide');
            },
            error:function (result) {
                toastr.error('保存失败，请联系管理员！');
            }
        });
    }
});


$('#addModal').on('shown.bs.modal', function (e) {
    $("#chart7").css('display','block');
    loadAdd();
    $('#welfareType1').combobox('clear');
    $('#money1').numberbox('clear');
    $('#effectiveDate1').datebox('clear');
    $('#reason1').textbox('clear');
    $('#empDatagrid').treegrid('unselectAll');
});

$('#addModal').on('hide.bs.modal', function (e) {
    $("#chart7").css('display','none');
    $('#datagrid2').datagrid('reload');
});

function loadAdd() {
    $('#empDatagrid').treegrid({
        iconCls:'icon-search',
        url:ctx + "/empInfo/getEmpTree",
        method: 'post',
        idField:'empId',
        treeField:'empId',
        showFooter:true,
        columns:[[
            {
                title: '序号',
                field: 'id',
                align: 'center',
                checkbox: true
            },
            {
                field: 'empId',
                title: '编号',
                width: 50,
                align: 'center'
            },
            {
                field: 'empName',
                title: '名称',
                width: 40,
                align: 'center'
            }
        ]],
        fitColumns: 'true',
        fit:true,
        animate:true,
        border: false,
        toolbar: '#emptb',
        singleSelect:false,
        loadFilter: function (data, parentId) {
            var list = data.rows;
            for (var i = 0; i < list.length; i++) {
                var parent = list[i];
                parent._parentId = parent.parentId;
            }
            return data;
        },
        onCheck : function(row){
            var id = row.empId;
            var ch = $(this).treegrid('getChildren', id);
            for (var i = 0; i < ch.length; i++) {
                $(this).treegrid('select', ch[i].empId);
            }
        },
        onUncheck : function(row){
            var id = row.empId;
            var ch = $(this).treegrid('getChildren', id);
            for (var i = 0; i < ch.length; i++) {
                $(this).treegrid('unselect', ch[i].empId);
            }
        }
    });
};
$("#addSupBtn").click(function () {
    if($("#welfareType1").val() == null){
        toastr.error('请选择福利类型！');
        return false;
    }
    if($("#money1").val().length < 1){
        toastr.error('请输入福利金额！');
        return false;
    }
    if($("#effectiveDate1").val().length < 1){
        toastr.error('请选择日期！');
        return false;
    }
    if($("#reason1").val().length < 1){
        toastr.error('请输入原因！');
        return false;
    }
    var type = $('#welfareType1').val();
    var money = $('#money1').val();
    var effectiveDate = $('#effectiveDate1').val();
    var reason = $('#reason1').val();
    var rows = $('#empDatagrid').treegrid('getSelections');
    if(rows.length < 1){
        toastr.error('请至少选择一名员工！');
        return false;
    }
    var result = new Array();
    for (var i = 0; i < rows.length; i++) {
        if(rows[i].children == null){
            var data = rows[i];
            data.type = type;
            data.money = money;
            data.effectiveDate = effectiveDate;
            data.reason = reason;
            result.push(data);
        }
    }
    $.ajax({
        type: "POST",
        url: ctx + "/welfare/saveEmpWelfareList",
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(result),
        success: function (result) {
            toastr.success('保存'+result.message);
        },
        error:function (result) {
            toastr.error('保存失败，请联系管理员！');
        }
    });
});

$("#seaBtn").click(function () {
    var data = {
        empName: $('#sq').val()
    };
    $('#empDatagrid').treegrid('load', data);
});

$('#clearSupBtn').click(function () {
    $('#addForm')[0].reset();
    $('#welfareType1').combobox('clear');
    $('#money1').numberbox('clear');
    $('#effectiveDate1').datebox('clear');
    $('#reason1').textbox('clear');
});