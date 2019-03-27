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
    weekMode:'liquid',
    handleWindowResize:false,
    height:532,
    dayClick: function(date, allDay, jsEvent, view) {
    },
    eventClick: function(event, jsEvent, view) {
        $('#updateForm')[0].reset();
        $('#empId').val(event.id);
        $('#empName').val(event.name);
        $('#overTimeStartOld').val(new Date(event.start).Format("yyyy-MM-dd hh:mm:ss"));
        $('#overTimeStart').val(new Date(event.start).Format("yyyy-MM-dd hh:mm:ss"));
        $('#overTimeEnd').val(new Date(event.end).Format("yyyy-MM-dd hh:mm:ss"));
        $('#overHour').val(event.hours);
        $('#overReason').val(event.title);
        $('#overType').val(event.overType);
        $('#myModal1').modal('show');
    }
});

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

$('#datagrid2').datagrid({
    url: ctx + "/hisOver/getByEmpid",
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
            field: 'overTimeStart',
            title: '加班开始时间',
            width: 120,
            align: 'center'
        },
        {
            field: 'overTimeEnd',
            title: '加班结束时间',
            width: 120,
            align: 'center'
        },
        {
            field: 'overHour',
            title: '有效时长(小时)',
            width: 80,
            align: 'center'
        },
        {
            field: 'overType',
            title: '加班类型',
            width: 90,
            align: 'center',
            formatter: function(value,row,index){
                if (row.overType == 1){
                    return '工作日加班';
                } else if (row.overType == 2){
                    return '双休日加班';
                } else if (row.overType == 3){
                    return '节假日加班';
                }
            }
        },
        {
            field: 'overReason',
            title: '加班原因',
            width: 150,
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

function reloadFullCalendar(empId) {
    var restList;
    $.ajax({
        type: "GET",
        url: ctx + "/hisOver/getOverByEmpid?empId="+empId,
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
    elem: '#overTimeStartMin'
    ,type: 'datetime'
    ,theme: DATA_COLOR.BLACK
});
laydate.render({
    elem: '#overTimeStartMax'
    ,type: 'datetime'
    ,theme: DATA_COLOR.BLACK
});

laydate.render({
    elem: '#overTimeStart'
    ,type: 'datetime'
    ,theme: DATA_COLOR.BLACK
});
laydate.render({
    elem: '#overTimeEnd'
    ,type: 'datetime'
    ,theme: DATA_COLOR.BLACK
});
laydate.render({
    elem: '#overTimeStart1'
    ,type: 'datetime'
    ,theme: DATA_COLOR.BLACK
});
laydate.render({
    elem: '#overTimeEnd1'
    ,type: 'datetime'
    ,theme: DATA_COLOR.BLACK
});

$("#searchBtn").click(function () {
    var data = {
        empName: $('#Eq').val()
    };
    $('#datagrid1').treegrid('load', data);
});

$("#searchBtn1").click(function () {
    $("#empId2").val($('#datagrid1').treegrid('getSelected').empId);
    var data = $("#searchOver").serializeObject();
    $('#datagrid2').datagrid('load', data);
});

$("#addBtn").click(function(){
    var row = $('#datagrid1').treegrid('getSelected');
    $('#saveForm')[0].reset();
    $('#empId1').val(row.empId);
    $('#empName1').val(row.empName);
    $('#myModal2').modal('show');
});

$("#clearBtn").click(function () {
    $('#searchOver')[0].reset();
});

$("#applyBtn").click(function () {
    $('#applyModal').modal('show');
});

function updateOnClick(index) {
    $('#updateForm')[0].reset();
    var row = $('#datagrid2').datagrid('getData').rows[index];
    $('#empId').val(row.empId);
    $('#empName').val(row.empName);
    $('#overTimeStartOld').val(new Date(row.overTimeStart).Format("yyyy-MM-dd hh:mm:ss"));
    $('#overTimeStart').val(new Date(row.overTimeStart).Format("yyyy-MM-dd hh:mm:ss"));
    $('#overTimeEnd').val(new Date(row.overTimeEnd).Format("yyyy-MM-dd hh:mm:ss"));
    $('#overHour').val(row.overHour);
    $('#overType').val(row.overType);
    $('#overReason').val(row.overReason);
    $('#myModal1').modal('show');
}

$('#sumbitUpdate').click(function () {
    if($("#overHour").val().length < 1){
        toastr.error('请输入请假时长！');
        return false;
    }
    if($("#overReason").val().length < 1){
        toastr.error('请输入加班原因！');
        return false;
    }
    if(!checkDateTime($("#overTimeStart").val())){
        toastr.error('开始时间日期格式不正确，请检查输入！');
        return false;
    }
    if(!checkDateTime($("#overTimeEnd").val())){
        toastr.error('结束时间日期格式不正确，请检查输入！');
        return false;
    }
    var t1 = $("#overTimeStart").val();
    var t2 = $("#overTimeEnd").val();
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
        data: {"startTime":$('#overTimeStart').val(),"endTime":$('#overTimeEnd').val()},
        async: false,
        success: function (result) {
            if(result == 1){
                toastr.error('您的加班信息中包含工作时间，请检查后重试！');
                re = 1;
            }else if(result == 2){
                toastr.error('开始时间应比结束时间早，请检查后重试！');
                re = 1;
            }
        }
    });
    if(re == 0){
        $.ajax({
            type: "POST",
            url: ctx + "/hisOver/updateHisOver",
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
                toastr.error('保存失败，请检查请假时间是否重复或联系管理员！');
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
                url: ctx + "/hisOver/removeHisOver?empId="+row.empId+"&overTimeStart="+row.overTimeStart,
                dataType: "json",
                success: function (result) {
                    toastr.success('删除'+result.message);
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
    if($("#overReason1").val().length < 1){
        toastr.error('请输入加班原因！');
        return false;
    }
    if(!checkDateTime($("#overTimeStart1").val())){
        toastr.error('开始时间日期格式不正确，请检查输入！');
        return false;
    }
    if(!checkDateTime($("#overTimeEnd1").val())){
        toastr.error('结束时间日期格式不正确，请检查输入！');
        return false;
    }
    var t1 = $("#overTimeStart1").val();
    var t2 = $("#overTimeEnd1").val();
    var d1 = t1.replace(/\-/g, "/");
    var d2 = t2.replace(/\-/g, "/");
    var date1 = new Date(d1);
    var date2 = new Date(d2);
    var ca = parseInt(date2 - date1) / 1000 / 60 / 60;
    if(ca <= 0){
        toastr.error('开始时间应比结束时间早，请检查后重试！');
        return false;
    }
    $("#overHour1").val(toDecimal(ca));
    var re = 0;
    $.ajax({
        type: "POST",
        url: ctx + "/checkDay/getWorkTimeQj",
        dataType: "json",
        data: {"startTime":$('#overTimeStart1').val(),"endTime":$('#overTimeEnd1').val()},
        async: false,
        success: function (result) {
            if(result == 1){
                toastr.error('您的加班信息中包含工作时间，请检查后重试！');
                re = 1;
            }else if(result == 2){
                toastr.error('开始时间应比结束时间早，请检查后重试！');
                re = 1;
            }
        }
    });
    $.ajax({
        type: "POST",
        url: ctx + "/hisOver/checkIsOverLap",
        dataType: "json",
        data: {"empId": $('#empId1').val(),"startTime":$('#overTimeStart1').val(),"endTime":$('#overTimeEnd1').val()},
        async: false,
        success: function (result) {
            if(result == true){
                toastr.error('您的加班时间段重复！');
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
            url: ctx + "/hisOver/saveHisOver",
            dataType: "json",
            data: $("#saveForm").serializeObject(),
            async: false,
            success: function (result) {
                toastr.success('保存'+result.message);
                $('#myModal2').modal('hide');
                reloadFullCalendar($('#empId1').val());
                $('#datagrid2').datagrid('reload');
            },
            error:function (result) {
                toastr.error('保存失败，请联系管理员！');
            }
        });
    }
});

$('#applyModal').on('shown.bs.modal', function (e) {
    $("#chart5").css('display','block');
    loadApply();
});

$('#applyModal').on('hide.bs.modal', function (e) {
    $("#chart5").css('display','none');
    $('#datagrid2').datagrid('reload');
    reloadFullCalendar($('#datagrid1').treegrid('getSelected').empId);
});

$("#applyHisBtn").click(function () {
    $('#applyHisModal').modal('show');
});

function loadApply() {
    $('#applyDatagrid').datagrid({
        url: ctx + "/hisOver/getByEmpidC?isCheck=0",
        idField: 'id',
        striped: true,
        singleSelect: true,
        pageSize: 20,
        border: false,
        pagination: true,
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
                field: 'overType',
                title: '加班类型',
                width: 70,
                align: 'center',
                formatter: function(value,row,index){
                    if (row.overType == 1){
                        return '工作日加班';
                    } else if (row.overType == 2){
                        return '双休日加班';
                    }else if (row.overType == 3){
                        return '节假日加班';
                    }
                }
            },
            {
                field: 'overTimeStart',
                title: '加班开始时间',
                width: 120,
                align: 'center'
            },
            {
                field: 'overTimeEnd',
                title: '加班结束时间',
                width: 120,
                align: 'center'
            },
            {
                field: 'overHour',
                title: '时长(小时)',
                width: 70,
                align: 'center'
            },
            {
                field: 'overReason',
                title: '加班原因',
                width: 120,
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
        title:'未审核加班记录',
        onLoadSuccess:function(data){
            $("a[name='pass']").linkbutton({plain:true,iconCls:'icon-ok'});
            $("a[name='nopass']").linkbutton({plain:true,iconCls:'icon-no'});
            $('#applyDatagrid').datagrid('clearSelections');
        }
    });
};
function pass(index) {
    var row = $('#applyDatagrid').datagrid('getData').rows[index];
    $.ajax({
        type: "GET",
        url: ctx + "/hisOver/updateOverApply?empId="+row.empId+"&overTimeStart="+row.overTimeStart+"&isCheck=1",
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
        url: ctx + "/hisOver/updateOverApply?empId="+row.empId+"&overTimeStart="+row.overTimeStart+"&isCheck=2",
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
        url: ctx + "/hisOver/getByEmpidC?isCheck=3",
        idField: 'id',
        striped: true,
        singleSelect: true,
        pageSize: 20,
        border: false,
        pagination: true,
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
                field: 'overType',
                title: '加班类型',
                width: 70,
                align: 'center',
                formatter: function(value,row,index){
                    if (row.overType == 1){
                        return '工作日加班';
                    } else if (row.overType == 2){
                        return '双休日加班';
                    }else if (row.overType == 3){
                        return '节假日加班';
                    }
                }
            },
            {
                field: 'overTimeStart',
                title: '加班开始时间',
                width: 120,
                align: 'center'
            },
            {
                field: 'overTimeEnd',
                title: '加班结束时间',
                width: 120,
                align: 'center'
            },
            {
                field: 'overHour',
                title: '时长(小时)',
                width: 70,
                align: 'center'
            },
            {
                field: 'overReason',
                title: '加班原因',
                width: 120,
                align: 'center'
            },
            {
                field: 'isCheck',
                title: '是否通过',
                width: 100,
                align: 'center',
                formatter: function(value,row,index){
                    if (row.isCheck == 1){
                        return '通过';
                    } else if(row.isCheck == 2){
                        return '不通过';
                    }
                }
            }
        ]],
        fitColumns: 'true',
        striped: 'true',
        fit:true,
        title:'已审核记录'
    });
};
$('#applyHisModal').on('shown.bs.modal', function (e) {
    $("#chart6").css('display','block');
    loadApplyHis();
});

$('#applyHisModal').on('hide.bs.modal', function (e) {
    $("#chart6").css('display','none');
});

