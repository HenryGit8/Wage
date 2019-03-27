$(document).ready(function () {

});

var date = new Date();
var d = date.getDate();
var m = date.getMonth();
var y = date.getFullYear();

$('#calendar').fullCalendar({
    timezone: 'local',
    header: {
        left: 'prev,next today',
        center: 'title',
        right: 'month,agendaWeek,agendaDay,listMonth'
    },
    firstDay:1,
    timeFormat: 'H:mm',
    axisFormat: 'H:mm',
    height:532,
    dayClick: function(date, allDay, jsEvent, view) {
    },
    eventClick: function(event, jsEvent, view) {
        $('#updateForm')[0].reset();
        $('#empId').val(event.id);
        $('#empName').val(event.name);
        $('#restStartTimeOld').val(new Date(event.start).Format("yyyy-MM-dd hh:mm:ss"));
        $('#restStartTime').val(new Date(event.start).Format("yyyy-MM-dd hh:mm:ss"));
        $('#restEndTime').val(new Date(event.end).Format("yyyy-MM-dd hh:mm:ss"));
        $('#restHour').val(event.hours);
        $('#myModalLabe2').html('调整请假信息');
        if(event.allDay == true){
            $('#allDay').val("1");
        }else{
            $('#allDay').val("0");
        }
        $('#restReason').val(event.title);
        $('#myModal1').modal('show');
    }
});

$('#datagrid2').datagrid({
    url: ctx + "/hisRest/getByEmpId",
    idField: 'empId',
    striped: true,
    singleSelect: true,
    pageSize: 10,
    border: false,
    pagination: true,
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
            width: 70,
            align: 'center'
        },
        {
            field: 'restStartTime',
            title: '请假开始时间',
            width: 100,
            align: 'center'
        },
        {
            field: 'restEndTime',
            title: '请假结束时间',
            width: 100,
            align: 'center'
        },
        {
            field: 'restHour',
            title: '有效时长(小时)',
            width: 70,
            align: 'center'
        },
        {
            field: 'restReason',
            title: '请假原因',
            width: 120,
            align: 'center'
        },
        {
            field:'operate',title:'操作',align:'center',width:70,
            formatter:function(value, row, index){
                var str = '<a href="javascript:;" onclick="updateOnClick('+index+')" name="update" class="easyui-linkbutton" ></a><a href="javascript:;" onclick="removeOnClick('+index+')" name="remove" class="easyui-linkbutton" ></a>';
                return str;
            }
        }
    ]],
    fitColumns: 'true',
    striped: 'true',
    fit:true,
    toolbar: '#tb1',
    onLoadSuccess:function(data) {
        $("a[name='update']").linkbutton({plain: true, iconCls: 'icon-edit'});
        $("a[name='remove']").linkbutton({plain: true, iconCls: 'icon-remove'});
    }

});

function updateOnClick(index) {
    $('#updateForm')[0].reset();
    var row = $('#datagrid2').datagrid('getData').rows[index];
    $('#empId').val(row.empId);
    $('#empName').val(row.empName);
    $('#restStartTimeOld').val(new Date(row.restStartTime).Format("yyyy-MM-dd hh:mm:ss"));
    $('#restStartTime').val(new Date(row.restStartTime).Format("yyyy-MM-dd hh:mm:ss"));
    $('#restEndTime').val(new Date(row.restEndTime).Format("yyyy-MM-dd hh:mm:ss"));
    $('#restHour').val(row.restHour);
    $('#restReason').val(row.restReason);
    $('#myModal1').modal('show');
}

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
    toolbar: '#tb',
    border: false,
    loadFilter: function (data, parentId) {
        var list = data.rows;
        for (var i = 0; i < list.length; i++) {
            var parent = list[i];
            parent._parentId = parent.parentId;
        }
        return data;
    },
    onClickRow:function (row) {
        if(row.parentId != null) {
            var data = {
                empId: row.empId
            };
            $('#datagrid2').datagrid('load', data);
            reloadFullCalendar(row.empId);
        }
    },
    onLoadSuccess:function(data){
        $('#datagrid1').treegrid("select", 100001);
        var row = $('#datagrid1').treegrid('getSelected');
        var data = {
            empId: row.empId
        };
        $('#datagrid2').datagrid('load', data);
        reloadFullCalendar(row.empId);
    }

});

$("#searchBtn").click(function () {
    var data = {
        empName: $('#Eq').val()
    };
    $('#datagrid1').treegrid('load', data);
});

$("#searchBtn1").click(function () {
    $("#empId2").val($('#datagrid1').treegrid('getSelected').empId);
    var data = $("#searchRest").serializeObject();
    $('#datagrid2').datagrid('load', data);
});

$("#clearBtn").click(function () {
    $('#searchRest')[0].reset();
});

$("#applyBtn").click(function () {
    $('#applyModal').modal('show');
});

$("#applyHisBtn").click(function () {
    $('#applyHisModal').modal('show');
});

$("#addBtn").click(function(){
    var row = $('#datagrid1').treegrid('getSelected');
    $('#addForm')[0].reset();
    $('#empId1').val(row.empId);
    $('#empName1').val(row.empName);
    $('#addModal').modal('show');
});

function reloadFullCalendar(empId) {
    var restList;
    $.ajax({
        type: "GET",
        url: ctx + "/hisRest/getRestByEmpid?empId="+empId,
        dataType: "json",
        async: false,
        success: function (result) {
            restList = result["restList"];
        }
    });
    $('#calendar').fullCalendar('removeEvents');
    $('#calendar').fullCalendar( 'addEventSource', restList);
    $('#calendar').fullCalendar( 'refetchEvents' );

};

laydate.render({
    elem: '#restStartTimeMin'
    ,type: 'datetime'
    ,theme: DATA_COLOR.BLACK
});
laydate.render({
    elem: '#restStartTimeMax'
    ,type: 'datetime'
    ,theme: DATA_COLOR.BLACK
});
laydate.render({
    elem: '#restStartTime'
    ,type: 'datetime'
    ,theme: DATA_COLOR.BLACK
});
laydate.render({
    elem: '#restEndTime'
    ,type: 'datetime'
    ,theme: DATA_COLOR.BLACK
});
laydate.render({
    elem: '#restStartTime1'
    ,type: 'datetime'
    ,theme: DATA_COLOR.BLACK
});
laydate.render({
    elem: '#restEndTime1'
    ,type: 'datetime'
    ,theme: DATA_COLOR.BLACK
});

$("#sumbitUpdate").click(function () {
    if($("#restHour").val().length < 1){
        toastr.error('请输入请假时长！');
        return false;
    }
    if($("#restReason").val().length < 1){
        toastr.error('请输入请假原因！');
        return false;
    }
    if(!checkDateTime($("#restStartTime").val())){
        toastr.error('开始时间日期格式不正确，请检查输入！');
        return false;
    }
    if(!checkDateTime($("#restEndTime").val())){
        toastr.error('结束时间日期格式不正确，请检查输入！');
        return false;
    }
    var t1 = $("#restStartTime").val();
    var t2 = $("#restEndTime").val();
    var d1 = t1.replace(/\-/g, "/");
    var d2 = t2.replace(/\-/g, "/");
    var date1 = new Date(d1);
    var date2 = new Date(d2);
    var ca = parseInt(date2 - date1) / 1000 / 60 / 60;
    if(ca <= 0){
        toastr.error('开始时间应比结束时间早，请检查后重试！');
        return false;
    }
    var re = 0;
    $.ajax({
        type: "POST",
        url: ctx + "/checkDay/getWorkTimeQj",
        dataType: "json",
        data: {"startTime":$('#restStartTime').val(),"endTime":$('#restEndTime').val()},
        async: false,
        success: function (result) {
            if(result == 0){
                toastr.error('您的请假信息中未包含工作时间，请检查后重试！');
                re = 1;
            }else if(result == -1){
                toastr.error('开始时间应比结束时间早，请检查后重试！');
                re = 1;
            }
        }
    });
    if(re == 0){
        $.ajax({
            type: "POST",
            url: ctx + "/hisRest/updateHisRest",
            dataType: "json",
            data: $("#updateForm").serializeObject(),
            async: false,
            success: function (result) {
                toastr.success('保存'+result.message);
                $('#myModal1').modal('hide');
                reloadFullCalendar($('#empId').val());
                $('#datagrid2').datagrid('reload');
            },
            error:function (result) {
                toastr.error('保存失败，请联系管理员！');
            }
        });
    }
});

function removeOnClick(index) {
    var row = $('#datagrid2').datagrid('getData').rows[index];
    Ewin.confirm({ message: "确认要删除选择的数据吗？" }).on(function (e) {
        if (!e) {
            return;
        }else{
            $.ajax({
                type: "GET",
                url: ctx + "/hisRest/removeHisRest?empId="+row.empId+"&restStartTime="+row.restStartTime,
                async: false,
                success: function (result) {
                    toastr.success('删除成功');
                    reloadFullCalendar(row.empId);
                    $('#datagrid2').datagrid('reload');
                },
                error:function (result) {
                    toastr.error('删除失败，请联系管理员！');
                }
            });
        }
    });
};

$("#sumbitSave").click(function () {
    if($("#restReason1").val().length < 1){
        toastr.error('请输入请假原因！');
        return false;
    }
    if(!checkDateTime($("#restStartTime1").val())){
        toastr.error('开始时间日期格式不正确，请检查输入！');
        return false;
    }
    if(!checkDateTime($("#restEndTime1").val())){
        toastr.error('结束时间日期格式不正确，请检查输入！');
        return false;
    }
    var t1 = $("#restStartTime1").val();
    var t2 = $("#restEndTime1").val();
    var d1 = t1.replace(/\-/g, "/");
    var d2 = t2.replace(/\-/g, "/");
    var date1 = new Date(d1);
    var date2 = new Date(d2);
    var ca = parseInt(date2 - date1) / 1000 / 60 / 60;
    if(ca <= 0){
        toastr.error('开始时间应比结束时间早，请检查后重试！');
        return false;
    }
    var re = 0;
    $.ajax({
        type: "POST",
        url: ctx + "/checkDay/getWorkTimeQj",
        dataType: "json",
        data: {"startTime":$('#restStartTime1').val(),"endTime":$('#restEndTime1').val()},
        async: false,
        success: function (result) {
            if(result == 0){
                toastr.error('您的请假信息中未包含工作时间，请检查后重试！');
                re = 1;
            }else if(result == -1){
                toastr.error('开始时间应比结束时间早，请检查后重试！');
                re = 1;
            }else{
                $.ajax({
                    type: "POST",
                    url: ctx + "/checkDay/getWorkHours",
                    dataType: "json",
                    data: {"startTime":$('#restStartTime1').val(),"endTime":$('#restEndTime1').val()},
                    async: false,
                    success: function (result) {
                        $('#restHour1').val(result);
                    },
                    error:function (result) {
                        toastr.error('系统异常，请联系管理员！');
                        re = 1;
                    }
                });
            }
        }
    });
    $.ajax({
        type: "POST",
        url: ctx + "/hisRest/checkIsOverLap",
        dataType: "json",
        data: {"empId": $('#empId1').val(),"startTime":$('#restStartTime1').val(),"endTime":$('#restEndTime1').val()},
        async: false,
        success: function (result) {
            if(result == true){
                toastr.error('您的请假时间段重复！');
                re = 1;
            }
        },
        error:function (result) {
            re = 1;
            toastr.error('系统异常，请联系管理员！');
        }
    });
    if(re == 0){
        $.ajax({
            type: "POST",
            url: ctx + "/hisRest/saveHisRest",
            dataType: "json",
            data: $("#addForm").serializeObject(),
            async: false,
            success: function (result) {
                toastr.success('保存'+result.message);
                $('#addModal').modal('hide');
                reloadFullCalendar($('#empId1').val());
                $('#datagrid2').datagrid('reload');
            },
            error:function (result) {
                toastr.error('保存失败，请联系管理员！');
            }
        });
    }
});


function loadApply() {
    $('#applyDatagrid').datagrid({
        url: ctx + "/hisRest/getRevokeApply?isAllow=0",
        idField: 'id',
        striped: true,
        singleSelect: true,
        pageSize: 20,
        border: false,
        pagination: true,
        columns: [[
            {
                field: 'id',
                title: '流水号',
                width: 40,
                align: 'center'
            },
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
                field: 'restTime',
                title: '撤回的时间(开始)',
                width: 100,
                align: 'center'
            },
            {
                field: 'applyTime',
                title: '申请时间',
                width: 100,
                align: 'center'
            },
            {
                field:'operate',title:'操作',align:'center',width:120,
                formatter:function(value, row, index){
                    var str = '<a href="javascript:;" onclick="pass('+index+')" name="pass" id="pass" class="easyui-linkbutton" >通过</a><a href="javascript:;" onclick="nopass('+index+')" name="nopass" id="nopass" class="easyui-linkbutton" >不通过</a>';
                    return str;
                }
            }
        ]],
        fitColumns: 'true',
        striped: 'true',
        fit:true,
        title:'撤回申请记录',
        onLoadSuccess:function(data){
            $("a[name='pass']").linkbutton({plain:true,iconCls:'icon-ok'});
            $("a[name='nopass']").linkbutton({plain:true,iconCls:'icon-no'});
            $('#applyDatagrid').datagrid('clearSelections');
        }
    });
}

$('#applyModal').on('shown.bs.modal', function (e) {
    $("#chart5").css('display','block');
    loadApply();
});

$('#applyModal').on('hide.bs.modal', function (e) {
    $("#chart5").css('display','none');
    $('#datagrid2').datagrid('reload');
    reloadFullCalendar($('#datagrid1').treegrid('getSelected').empId);
});

function pass(index) {
    var row = $('#applyDatagrid').datagrid('getData').rows[index];
    $.ajax({
        type: "GET",
        url: ctx + "/hisRest/updateRevokeApply?id="+row.id+"&isAllow=1",
        dataType: "json",
        async: false,
        success: function (result) {
            $('#applyDatagrid').datagrid('reload');
        },
        error:function (result) {
            toastr.error('保存失败，请联系管理员！');
        }
    });
};

function nopass(index) {
    var row = $('#applyDatagrid').datagrid('getData').rows[index];
    $.ajax({
        type: "GET",
        url: ctx + "/hisRest/updateRevokeApply?id="+row.id+"&isAllow=2",
        dataType: "json",
        async: false,
        success: function (result) {
            $('#applyDatagrid').datagrid('reload');
        },
        error:function (result) {
            toastr.error('保存失败，请联系管理员！');
        }
    });
};

function loadApplyHis() {
    $('#applyHisDatagrid').datagrid({
        url: ctx + "/hisRest/getRevokeApply?isAllow=1",
        idField: 'id',
        striped: true,
        singleSelect: true,
        pageSize: 20,
        border: false,
        pagination: true,
        columns: [[
            {
                field: 'id',
                title: '流水号',
                width: 40,
                align: 'center'
            },
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
                field: 'restTime',
                title: '撤回的时间(开始)',
                width: 100,
                align: 'center'
            },
            {
                field: 'applyTime',
                title: '申请时间',
                width: 100,
                align: 'center'
            },
            {
                field: 'isAllow',
                title: '是否通过',
                width: 100,
                align: 'center',
                formatter: function(value,row,index){
                    if (row.isAllow == 1){
                        return '通过';
                    } else if(row.isAllow == 2){
                        return '不通过';
                    }
                }
            }
        ]],
        fitColumns: 'true',
        striped: 'true',
        fit:true,
        title:'撤回申请记录'
    });
}

$('#applyHisModal').on('shown.bs.modal', function (e) {
    $("#chart6").css('display','block');
    loadApplyHis();
});

$('#applyHisModal').on('hide.bs.modal', function (e) {
    $("#chart6").css('display','none');
});
