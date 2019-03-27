$(document).ready(function () {
    loadSetData();
});
function showDivState(index) {
    $('#input'+index).show();
}
function hideDivState(index) {
    $('#input'+index).hide();
}
function loadSetData() {
    $.ajax({
        type: "POST",
        url: ctx + "/sysSet/getAllSysSet",
        dataType: "json",
        success: function (result) {
            $('#input0').val(result["WORK_DAY"]);
            $('#input1').val(result["WORK_DAY_START_ONE"]);
            $('#input2').val(result["WORK_DAY_END_ONE"]);
            $('#input3').val(result["WORK_DAY_START_TWO"]);
            $('#input4').val(result["WORK_DAY_END_TWO"]);
            $('#input5').val(result["NORMALDAY_OVERTIME_PAY"]);
            $('#input6').val(result["RESTDAY_OVERTIME_PAY"]);
            $('#input7').val(result["HOLIDAY_OVERTIME_PAY"]);
            $('#input8').val(result["THRESHOLD"]);
            $('#input9').val(result["TAX_RATE_0"]);
            $('#input10').val(result["TAX_RATE_1500"]);
            $('#input11').val(result["TAX_RATE_4500"]);
            $('#input12').val(result["TAX_RATE_9000"]);
            $('#input13').val(result["TAX_RATE_35000"]);
            $('#input14').val(result["TAX_RATE_55000"]);
            $('#input15').val(result["TAX_RATE_80000"]);
            $('#input16').val(result["PENSION_INSURANCE"]);
            $('#input17').val(result["MEDICAL_INSURANCE"]);
            $('#input18').val(result["UNEMPLOYMENT_INSURANCE"]);
            $('#input19').val(result["BASIC_HOUSING_PROVIDENT_FUND"]);
            $('#input20').val(result["PENSION_INSURANCE_C"]);
            $('#input21').val(result["MEDICAL_INSURANCE_C"]);
            $('#input22').val(result["UNEMPLOYMENT_INSURANCE_C"]);
            $('#input23').val(result["EMP_INJURY_INSURANCE_C"]);
            $('#input24').val(result["BIRTH_INSURANCE_C"]);
            $('#input25').val(result["BASIC_HOUSING_PROVIDENT_FUND_C"]);
        }
    });
}

$('#cancel').click(function () {
    hideData();
});

$('#save').click(function () {
    Ewin.confirm({ message: "您确定改变设置吗，数据不规范将会导致系统出现错误！" }).on(function (e) {
        if (!e) {
            return;
        }else{
            $('#input26').val("0");
            $('#input27').val(Math.round(1500 * (Number($('#input10').val())-Number($('#input9').val()))+Number($('#input26').val())));
            $('#input28').val(Math.round(4500 * (Number($('#input11').val())-Number($('#input10').val()))+Number($('#input27').val())));
            $('#input29').val(Math.round(9000 * (Number($('#input12').val())-Number($('#input11').val()))+Number($('#input28').val())));
            $('#input30').val(Math.round(35000 * (Number($('#input13').val())-Number($('#input12').val()))+Number($('#input29').val())));
            $('#input31').val(Math.round(55000 * (Number($('#input14').val())-Number($('#input13').val()))+Number($('#input30').val())));
            $('#input32').val(Math.round(80000 * (Number($('#input15').val())-Number($('#input14').val()))+Number($('#input31').val())));
            var result = new Array();
            for (var i = 0; i < 33; i++) {
                var data = new Object();
                switch(i)
                {
                    case 0: data.setName = "WORK_DAY"; break;
                    case 1: data.setName = "WORK_DAY_START_ONE"; break;
                    case 2: data.setName = "WORK_DAY_END_ONE"; break;
                    case 3: data.setName = "WORK_DAY_START_TWO"; break;
                    case 4: data.setName = "WORK_DAY_END_TWO"; break;
                    case 5: data.setName = "NORMALDAY_OVERTIME_PAY"; break;
                    case 6: data.setName = "RESTDAY_OVERTIME_PAY"; break;
                    case 7: data.setName = "HOLIDAY_OVERTIME_PAY"; break;
                    case 8: data.setName = "THRESHOLD"; break;
                    case 9: data.setName = "TAX_RATE_0"; break;
                    case 10: data.setName = "TAX_RATE_1500"; break;
                    case 11: data.setName = "TAX_RATE_4500"; break;
                    case 12: data.setName = "TAX_RATE_9000"; break;
                    case 13: data.setName = "TAX_RATE_35000"; break;
                    case 14: data.setName = "TAX_RATE_55000"; break;
                    case 15: data.setName = "TAX_RATE_80000"; break;
                    case 16: data.setName = "PENSION_INSURANCE"; break;
                    case 17: data.setName = "MEDICAL_INSURANCE"; break;
                    case 18: data.setName = "UNEMPLOYMENT_INSURANCE"; break;
                    case 19: data.setName = "BASIC_HOUSING_PROVIDENT_FUND"; break;
                    case 20: data.setName = "PENSION_INSURANCE_C"; break;
                    case 21: data.setName = "MEDICAL_INSURANCE_C"; break;
                    case 22: data.setName = "UNEMPLOYMENT_INSURANCE_C"; break;
                    case 23: data.setName = "EMP_INJURY_INSURANCE_C"; break;
                    case 24: data.setName = "BIRTH_INSURANCE_C"; break;
                    case 25: data.setName = "BASIC_HOUSING_PROVIDENT_FUND_C"; break;
                    case 26: data.setName = "QUICK_DEDUCT_0"; break;
                    case 27: data.setName = "QUICK_DEDUCT_1500"; break;
                    case 28: data.setName = "QUICK_DEDUCT_4500"; break;
                    case 29: data.setName = "QUICK_DEDUCT_9000"; break;
                    case 30: data.setName = "QUICK_DEDUCT_35000"; break;
                    case 31: data.setName = "QUICK_DEDUCT_55000"; break;
                    case 32: data.setName = "QUICK_DEDUCT_80000"; break;
                    default:
                }
                data.setValue = $('#input'+i).val();
                result.push(data);
            }
            $.ajax({
                type: "POST",
                url: ctx + "/sysSet/updateSet",
                dataType: "json",
                contentType: "application/json",
                data: JSON.stringify(result),
                success: function (result) {
                    toastr.success('保存'+result.message);
                    loadSetData();
                    hideData();
                },
                error:function (result) {
                    toastr.error('保存失败，请联系管理员！');
                }
            });
        }
    });
});
function hideData() {
    for (var i = 0; i < 26; i++) {
        hideDivState(i);
    }
}

$('#toDefault').click(function () {
    Ewin.confirm({ message: "您确定恢复默认设置吗？" }).on(function (e) {
        if (!e) {
            return;
        }else {
            $.ajax({
                type: "POST",
                url: ctx + "/sysSet/updateToDefault",
                dataType: "json",
                contentType: "application/json",
                success: function (result) {
                    toastr.success('还原' + result.message);
                    loadSetData();
                    hideData();
                },
                error: function (result) {
                    toastr.error('还原默认值失败，请联系管理员！');
                }
            });
        }
    });
});

$('#toLoginHis').click(function () {
    $('#loginHisModal').modal('show');
})

$('#loginHisModal').on('shown.bs.modal', function (e) {
    $("#chart1").css('display','block');
    loadLoginHisData();
});

$('#loginHisModal').on('hide.bs.modal', function (e) {
    $("#chart1").css('display','none');
});

function loadLoginHisData() {
    $('#loginHisDatagrid').datagrid({
        url: ctx + "/sysSet/getAllLoginHis",
        idField: 'id',
        singleSelect: true,
        pageSize: 10,
        border: false,
        pagination: true,
        queryParams: $("#searchForm").serializeObject(),
        columns: [[
            {
                field: 'empId',
                title: '员工编号',
                width: 40,
                align: 'center'
            },
            {
                field: 'empName',
                title: '姓名',
                width: 40,
                align: 'center'
            },
            {
                field: 'loginTime',
                title: '登录时间',
                width: 70,
                align: 'center'
            },
            {
                field: 'ip',
                title: '登录IP',
                width: 55,
                align: 'center'
            },
            {
                field: 'place',
                title: '登录地点',
                width: 70,
                align: 'center'
            }
        ]],
        fitColumns: 'true',
        striped: 'true',
        fit:true,
        toolbar: '#tb'
    });
}

$('#searchBtn').click(function () {
    var data = $("#searchForm").serializeObject();
    $('#loginHisDatagrid').datagrid('load', data);
})

$('#clearBtn').click(function () {
    $('#loginTimeStart').datebox('clear');
    $('#loginTimeEnd').datebox('clear');
    $('#empName').textbox('clear');
})

$('#holidaySet').click(function () {
    $('#holidaySetModal').modal('show');
})

$('#holidaySetModal').on('shown.bs.modal', function (e) {
    $("#chart2").css('display','block');
    loadHoliData();
});

$('#holidaySetModal').on('hide.bs.modal', function (e) {
    $("#chart2").css('display','none');
});

function loadHoliData() {
    $('#holidaySetDatagrid').datagrid({
        url: ctx + "/sysSet/getAllHoliday",
        idField: 'id',
        striped: true,
        singleSelect: true,
        pageSize: 10,
        border: false,
        pagination: true,
        scrollbarSize:0,
        columns: [[
            {
                field: 'type',
                title: '类型',
                width: 70,
                align: 'center',
                formatter: function(value,row,index){
                    if (row.type == 0){
                        return '节假日';
                    }else {
                        return '额外工作日';
                    }
                }
            },
            {
                field: 'dateTime',
                title: '时间',
                width: 70,
                align: 'center'
            },
            {
                field:'operate',title:'操作',align:'center',width:70,
                formatter:function(value, row, index){
                    var str = '<a id="delete" href="javascript:;" onclick="deleteHoliday('+index+')" name="delete" class="easyui-linkbutton" >删除</a>';
                    return str;
                }
            }
        ]],
        fitColumns: 'true',
        fit:true,
        toolbar: '#tb1',
        onLoadSuccess:function(data){
            $("a[name='delete']").linkbutton({plain:true,iconCls:'icon-remove'});
        },
    });
}

$("#saveBtn").click(function () {
    if($("#dateTime").val().length < 1){
        toastr.error('请选择日期！');
        return false;
    }else{
        $.ajax({
            type: "POST",
            url: ctx + "/sysSet/saveHoliday",
            dataType: "json",
            data: $("#holiAddForm").serializeObject(),
            success: function (result) {
                toastr.success('保存'+result.message);
                $('#holidaySetDatagrid').datagrid('reload');
            },
            error:function (result) {
                toastr.error('保存失败，请联系管理员！');
            }
        });
    }
});

function deleteHoliday(index) {
    var row = $('#holidaySetDatagrid').datagrid('getData').rows[index];
    Ewin.confirm({ message: "确认删除此条信息吗？" }).on(function (e) {
        if (!e) {
            return;
        }else{
            $.ajax({
                type: "POST",
                url: ctx + "/sysSet/deleteHoliday",
                dataType: "json",
                data: {"dateTime":row.dateTime},
                success: function (result) {
                    toastr.success('删除'+result.message);
                    $('#holidaySetDatagrid').datagrid('reload');
                },
                error:function (result) {
                    toastr.error('保存失败，请联系管理员！');
                }
            });
        }
    })
}

$('#departSet').click(function () {
    $('#departSetModal').modal('show');
})

$('#departSetModal').on('shown.bs.modal', function (e) {
    $("#chart3").css('display','block');
    loadDepartData();
});

$('#departSetModal').on('hide.bs.modal', function (e) {
    $("#chart3").css('display','none');
});

function loadDepartData() {
    $('#departSetDatagrid').datagrid({
        url: ctx + "/sysSet/getAllDepart",
        idField: 'departId',
        striped: true,
        singleSelect: true,
        border: false,
        columns: [[
            {
                field: 'departId',
                title: '部门编号',
                width: 70,
                align: 'center'
            },
            {
                field: 'departName',
                title: '部门名称',
                width: 70,
                align: 'center'
            },
            {
                field: 'departRand',
                title: '薪资等级',
                width: 70,
                align: 'center',
                formatter: function(value,row,index){
                    return '等级'+row.departRand;
                }
            },
            {
                field: 'departWage',
                title: '部门薪资(￥)',
                width: 70,
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
        fit:true,
        toolbar: '#tb2',
        onLoadSuccess:function(data) {
            $("a[name='update']").linkbutton({plain: true, iconCls: 'icon-edit'});
            $("a[name='remove']").linkbutton({plain: true, iconCls: 'icon-remove'});
        }
    });
}

function removeOnClick(index) {
    var row = $('#departSetDatagrid').datagrid('getData').rows[index];
    Ewin.confirm({ message: "确认删除此条信息吗？" }).on(function (e) {
        if (!e) {
            return;
        }else{
            $.ajax({
                type: "POST",
                url: ctx + "/sysSet/deleteEmpDepart",
                dataType: "json",
                data: {"departId":row.departId,"departRand":row.departRand},
                success: function (result) {
                    toastr.success('删除'+result.message);
                    $('#departSetDatagrid').datagrid('reload');
                },
                error:function (result) {
                    toastr.error('保存失败，请联系管理员！');
                }
            });
        }
    })
}

function updateOnClick(index) {
    var row = $('#departSetDatagrid').datagrid('getData').rows[index];
    $('#departWage').numberbox('setValue',row.departWage);
    $('#departId').numberbox('disable');
    $('#departName').textbox('disable');
    $('#departRand').numberbox('disable');
    $('#departId').numberbox('setValue','');
    $('#departName').textbox('setValue','');
    $('#departRand').numberbox('setValue','');
    $('#opType').val("1");
    $('#departId1').val(row.departId);
    $('#departRand1').val(row.departRand);
}

$('#addDepartBtn').click(function () {
    if($('#opType').val() == 1){
        if($("#departWage").val().length < 1){
            toastr.error('请输入薪资金额！');
            return false;
        }else {
            $.ajax({
                type: "POST",
                url: ctx + "/sysSet/updateDepart",
                dataType: "json",
                data: {"departId":$('#departId1').val(),"departRand":$('#departRand1').val(),"departWage":$('#departWage').val()},
                success: function (result) {
                    toastr.success('保存' + result.message);
                    $('#opType').val("0");
                    $('#departWage').numberbox('setValue','');
                    $('#departId').numberbox('enable');
                    $('#departName').textbox('enable');
                    $('#departRand').numberbox('enable');
                    $('#departSetDatagrid').datagrid('reload');
                },
                error: function (result) {
                    toastr.error('保存失败，请联系管理员！');
                }
            });
        }
    }else{
        if($("#departId").val().length < 1){
            toastr.error('请输入部门编号！');
            return false;
        }
        if($("#departName").val().length < 1){
            toastr.error('请输入部门名称！');
            return false;
        }
        if($("#departRand").val().length < 1){
            toastr.error('请输入薪资等级！');
            return false;
        }
        if($("#departWage").val().length < 1){
            toastr.error('请输入薪资金额！');
            return false;
        }else{
            $.ajax({
                type: "POST",
                url: ctx + "/sysSet/saveEmpDepart",
                dataType: "json",
                data: $("#departAddForm").serializeObject(),
                success: function (result) {
                    if(result.object == 1){
                        toastr.error('部门编号与薪资等级重复');
                    }else{
                        toastr.success('保存'+result.message);
                        $('#departSetDatagrid').datagrid('reload');
                    }
                },
                error:function (result) {
                    toastr.error('保存失败，请联系管理员！');
                }
            });
        }
    }
})

$('#cancelBtn').click(function () {
    $('#departWage').numberbox('setValue','');
    $('#departId').numberbox('enable');
    $('#departName').textbox('enable');
    $('#departRand').numberbox('enable');
    $('#opType').val("0");
})

$('#adminAuthSet').click(function () {
    $('#adminAuthSetModal').modal('show');
})

$('#adminAuthSetModal').on('shown.bs.modal', function (e) {
    $("#chart4").css('display','block');
    $("#chart5").css('display','block');
    $("#chart6").css('display','block');
    loadAdminAuthData();
});

$('#adminAuthSetModal').on('hide.bs.modal', function (e) {
    $("#chart4").css('display','none');
    $("#chart5").css('display','none');
    $("#chart6").css('display','none');
});

function loadAdminAuthData() {
    $('#adminDatagrid').datagrid({
        url: ctx + "/login/getAdmin",
        idField: 'empId',
        striped: true,
        singleSelect: true,
        scrollbarSize:15,
        border: false,
        columns: [[
            {
                field: 'empId',
                title: '员工编号',
                width: 70,
                align: 'center'
            },
            {
                field: 'empName',
                title: '员工名称',
                width: 70,
                align: 'center'
            }
        ]],
        fitColumns: 'true',
        fit:true,
        title:'管理员信息表',
        onClickRow:function (index, row) {
            var data = {
                empId: row.empId
            };
            $('#matchDatagrid').datagrid('load', data);
            $('#noMatchDatagrid').datagrid('load', data);
        }
    });
    $('#matchDatagrid').datagrid({
        url: ctx + "/login/getMatch",
        idField: 'id',
        striped: true,
        singleSelect: true,
        scrollbarSize:0,
        border: false,
        columns: [[
            {
                field: 'id',
                title: '编号',
                width: 40,
                align: 'center'
            },
            {
                field: 'name',
                title: '模块名称',
                width: 70,
                align: 'center'
            },
            {
                field:'operate',title:'撤销',align:'center',width:40,
                formatter:function(value, row, index){
                    var str = '<a href="javascript:;" onclick="removeAuth('+row.id+')" name="removeAuth" class="easyui-linkbutton" ></a>';
                    return str;
                }
            }
        ]],
        fitColumns: 'true',
        fit:true,
        title:'管理员已赋权限表',
        onLoadSuccess:function(data) {
            $("a[name='removeAuth']").linkbutton({plain: true, iconCls: 'icon-remove'});
        }
    });
    $('#noMatchDatagrid').datagrid({
        url: ctx + "/login/getNoMatch",
        idField: 'id',
        striped: true,
        singleSelect: true,
        scrollbarSize:0,
        border: false,
        columns: [[
            {
                field: 'id',
                title: '编号',
                width: 40,
                align: 'center'
            },
            {
                field: 'name',
                title: '模块名称',
                width: 70,
                align: 'center'
            },
            {
                field:'operate',title:'赋予',align:'center',width:40,
                formatter:function(value, row, index){
                    var str = '<a href="javascript:;" onclick="addAuth('+row.id+')" name="addAuth" class="easyui-linkbutton" ></a>';
                    return str;
                }
            }
        ]],
        fitColumns: 'true',
        fit:true,
        title:'管理员未赋权限表',
        onLoadSuccess:function(data) {
            $("a[name='addAuth']").linkbutton({plain: true, iconCls: 'icon-add'});
        }
    });
}

function addAuth(id) {
    var row = $('#adminDatagrid').datagrid('getSelected');
    $.ajax({
        type: "POST",
        url: ctx + "/login/updateAddAuth",
        dataType: "json",
        data: {"empId":row.empId,"id":id},
        success: function (result) {
            $('#matchDatagrid').datagrid('reload');
            $('#noMatchDatagrid').datagrid('reload');
        },
        error:function (result) {
            toastr.error('系统异常，请联系管理员！');
        }
    });
}

function removeAuth(id) {
    var row = $('#adminDatagrid').datagrid('getSelected');
    $.ajax({
        type: "POST",
        url: ctx + "/login/updateDeleAuth",
        dataType: "json",
        data: {"empId":row.empId,"id":id},
        success: function (result) {
            $('#matchDatagrid').datagrid('reload');
            $('#noMatchDatagrid').datagrid('reload');
        },
        error:function (result) {
            toastr.error('系统异常，请联系管理员！');
        }
    });
}

$('#adminSet').click(function () {
    $('#adminSetModal').modal('show');
})

$('#adminSetModal').on('shown.bs.modal', function (e) {
    $("#chart7").css('display','block');
    $("#chart8").css('display','block');
    loadAdminSetData();
});

$('#adminSetModal').on('hide.bs.modal', function (e) {
    $("#chart7").css('display','none');
    $("#chart8").css('display','none');
});

function loadAdminSetData() {
    $('#adminIDatagrid').datagrid({
        url: ctx + "/login/getAdmin",
        idField: 'empId',
        striped: true,
        singleSelect: true,
        scrollbarSize:15,
        border: false,
        columns: [[
            {
                field: 'empId',
                title: '员工编号',
                width: 70,
                align: 'center'
            },
            {
                field: 'empName',
                title: '员工名称',
                width: 70,
                align: 'center'
            },
            {
                field:'operate',title:'取消任命',align:'center',width:70,
                formatter:function(value, row, index){
                    var str = '<a href="javascript:;" onclick="removeAdmin('+row.empId+')" name="removeAdmin" class="easyui-linkbutton" ></a>';
                    return str;
                }
            }
        ]],
        fitColumns: 'true',
        fit:true,
        title:'已任命管理员',
        onLoadSuccess:function(data) {
            $("a[name='removeAdmin']").linkbutton({plain: true, iconCls: 'icon-remove'});
        }
    });
    $('#adminNDatagrid').datagrid({
        url: ctx + "/login/getNotAdmin",
        idField: 'empId',
        singleSelect: true,
        scrollbarSize:15,
        border: false,
        columns: [[
            {
                field: 'empId',
                title: '员工编号',
                width: 70,
                align: 'center'
            },
            {
                field: 'empName',
                title: '员工名称',
                width: 70,
                align: 'center'
            },
            {
                field:'operate',title:'任命管理员',align:'center',width:70,
                formatter:function(value, row, index){
                    var str = '<a href="javascript:;" onclick="addAdmin('+row.empId+')" name="addAdmin" class="easyui-linkbutton" ></a>';
                    return str;
                }
            }
        ]],
        fitColumns: 'true',
        striped: 'true',
        fit:true,
        title:'未任命已注册人员',
        onLoadSuccess:function(data) {
            $("a[name='addAdmin']").linkbutton({plain: true, iconCls: 'icon-add'});
        }
    });
}

function addAdmin(empId) {
    $.ajax({
        type: "POST",
        url: ctx + "/sysSet/updateAddAdmin",
        dataType: "json",
        data: {"empId":empId},
        success: function (result) {
            $('#adminIDatagrid').datagrid('reload');
            $('#adminNDatagrid').datagrid('reload');
        },
        error:function (result) {
            toastr.error('系统异常，请联系管理员！');
        }
    });
}

function removeAdmin(empId) {
    $.ajax({
        type: "POST",
        url: ctx + "/sysSet/updateRemoveAdmin",
        dataType: "json",
        data: {"empId":empId},
        success: function (result) {
            $('#adminIDatagrid').datagrid('reload');
            $('#adminNDatagrid').datagrid('reload');
        },
        error:function (result) {
            toastr.error('系统异常，请联系管理员！');
        }
    });
}

function addAdmin(empId) {
    $.ajax({
        type: "POST",
        url: ctx + "/sysSet/updateAddAdmin",
        dataType: "json",
        data: {"empId":empId},
        success: function (result) {
            $('#adminIDatagrid').datagrid('reload');
            $('#adminNDatagrid').datagrid('reload');
        },
        error:function (result) {
            toastr.error('系统异常，请联系管理员！');
        }
    });
}

function loadResetEmpPass() {
    $('#resetPassDatagrid').datagrid({
        url: ctx + "/sysSet/getIsRegister",
        idField: 'empId',
        striped: true,
        singleSelect: true,
        scrollbarSize:15,
        border: false,
        pageSize: 10,
        pagination: true,
        queryParams: $("#searchForm1").serializeObject(),
        columns: [[
            {
                field: 'empId',
                title: '员工编号',
                width: 70,
                align: 'center'
            },
            {
                field: 'empName',
                title: '员工名称',
                width: 70,
                align: 'center'
            },
            {
                field:'operate',title:'重置',align:'center',width:70,
                formatter:function(value, row, index){
                    var str = '<a href="javascript:;" onclick="resetPass('+row.empId+')" name="resetPass" class="easyui-linkbutton" ></a>';
                    return str;
                }
            }
        ]],
        fitColumns: 'true',
        fit:true,
        toolbar: '#tb3',
        title:'已注册员工表',
        onLoadSuccess:function(data) {
            $("a[name='resetPass']").linkbutton({plain: true, iconCls: 'icon-reload'});
        }
    });
}

function resetPass(empId) {
    Ewin.confirm({ message: "确认重置此员工密码吗？" }).on(function (e) {
        if (!e) {
            return;
        }else{
            $.ajax({
                type: "POST",
                url: ctx + "/sysSet/updateEmpPass",
                dataType: "json",
                data: {"empId":empId},
                success: function (result) {
                    toastr.success('重置成功，密码为：'+result+'！');
                },
                error:function (result) {
                    toastr.error('系统异常，请联系管理员！');
                }
            });
        }
    })
}

$('#resetEmpPass').click(function () {
    $('#resetPassModal').modal('show');
})

$('#resetPassModal').on('shown.bs.modal', function (e) {
    $("#chart9").css('display','block');
    loadResetEmpPass();
});

/*$('#resetPassModal').on('hide.bs.modal', function (e) {
    $("#chart9").css('display','none');
});*/
$('#searchBtn1').click(function () {
    var data = $("#searchForm1").serializeObject();
    $('#resetPassDatagrid').datagrid('load', data);
})

$('#newsSet').click(function () {
    $('#newsSetModal').modal('show');
})

$('#newsSetModal').on('shown.bs.modal', function (e) {
    $("#chart10").css('display','block');
    loadNewsSetData();
});

function loadNewsSetData() {
    $('#newsSetDatagrid').datagrid({
        url: ctx + "/sysSet/getAllNewsList",
        idField: 'empId',
        striped: true,
        singleSelect: true,
        scrollbarSize:15,
        border: false,
        columns: [[
            {
                field: 'themeText',
                title: '主题文字',
                width: 90,
                align: 'center'
            },
            {
                field: 'newUrl',
                title: '新闻外链接',
                width: 70,
                align: 'center'
            },
            {
                field:'delete',title:'操作',align:'center',width:20,
                formatter:function(value, row, index){
                    var str = '<a href="javascript:;" onclick="updateNews('+index+')" name="updateNews" class="easyui-linkbutton" ></a><a href="javascript:;" onclick="removeNews('+index+')" name="removeNews" class="easyui-linkbutton" ></a>';
                    return str;
                }
            },
            {
                field:'move',title:'上/下移',align:'center',width:20,
                formatter:function(value, row, index){
                    var str = '<a href="javascript:;" onclick="move('+index+')" name="move" class="easyui-linkbutton" > ↑ </a><a href="javascript:;" onclick="down('+index+')" name="down" class="easyui-linkbutton" > ↓ </a>';
                    return str;
                }
            }
        ]],
        fitColumns: 'true',
        fit:true,
        toolbar: '#tb4',
        title:'推荐新闻列表',
        rownumbers:true,
        onLoadSuccess:function(data) {
            loadBtn();
        }
    });
}
function loadBtn() {
    $("a[name='removeNews']").linkbutton({plain: true, iconCls: 'icon-remove'});
    $("a[name='move']").linkbutton({plain: true});
    $("a[name='down']").linkbutton({plain: true});
    $("a[name='updateNews']").linkbutton({plain: true,iconCls: 'icon-edit'});
    $('#newsSetDatagrid').datagrid('fixRowHeight');
}
function removeNews(index) {
    Ewin.confirm({ message: "确认删除此新闻吗？" }).on(function (e) {
        if (!e) {
            return;
        }else{
            $('#newsSetDatagrid').datagrid('deleteRow',index);
        }
    });
}

function move(rowIndex) {
    var rows=$('#newsSetDatagrid').datagrid('getRows');
    var rowlength=rows.length;
    var selectrow = $('#newsSetDatagrid').datagrid('getData').rows[rowIndex];
    if(rowIndex==0){
    }else{
        $('#newsSetDatagrid').datagrid('deleteRow', rowIndex);//删除一行
        rowIndex--;
        $('#newsSetDatagrid').datagrid('insertRow', {
            index:rowIndex,
            row:selectrow
        });
        $('#newsSetDatagrid').datagrid('selectRow', rowIndex);
    }
    loadBtn();
}

function down(rowIndex) {
    var rows=$('#newsSetDatagrid').datagrid('getRows');
    var rowlength=rows.length;
    var selectrow = $('#newsSetDatagrid').datagrid('getData').rows[rowIndex];
    if(rowIndex==rowlength-1){
    }else{
        $('#newsSetDatagrid').datagrid('deleteRow', rowIndex);//删除一行
        rowIndex++;
        $('#newsSetDatagrid').datagrid('insertRow', {
            index:rowIndex,
            row:selectrow
        });
        $('#newsSetDatagrid').datagrid('selectRow', rowIndex);
    }
    loadBtn();
}

$('#addNew').click(function () {
    if($('#themeText').textbox('getValue').length<1){
        toastr.error('主题文字不能为空！');
        return false;
    }
    if($('#themeText').textbox('getValue').length>50){
        toastr.error('最多输入25个汉字，或者50个英文字母！');
        return false;
    }
    var rows=$('#newsSetDatagrid').datagrid('getRows');
    var rowlength=rows.length;
    if(rowlength>6){
        toastr.error('最多添加7条推荐新闻！');
        $('#themeText').textbox('setValue','');
        $('#newUrl').textbox('setValue','');
        return false;
    }else {
        $('#newsSetDatagrid').datagrid('insertRow',{
            row: {
                themeText: $('#themeText').textbox('getValue'),
                newUrl: $('#newUrl').textbox('getValue')
            }
        });
        loadBtn();
        $('#themeText').textbox('setValue','');
        $('#newUrl').textbox('setValue','');
    }
})

$('#saveChange').click(function () {
    var rows=$('#newsSetDatagrid').datagrid('getRows');
    $.ajax({
        type: "POST",
        url: ctx + "/sysSet/saveAllNewNoImg",
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(rows),
        success: function (result) {
            toastr.success('保存'+result.message);
            $('#newsSetModal').modal('hide');
        },
        error:function (result) {
            toastr.error('保存失败，请联系管理员！');
        }
    });
})

function updateNews(index) {
    var row = $('#newsSetDatagrid').datagrid('getData').rows[index];
    $('#index').val(index);
    $('#themeText').textbox('setValue',row.themeText);
    $('#newUrl').textbox('setValue',row.newUrl);
    $('#addNew').hide();
    $('#finishNew').show();
}

$('#finishNew').click(function () {
    if($('#themeText').textbox('getValue').length<1){
        toastr.error('主题文字不能为空！');
        return false;
    }
    if($('#themeText').textbox('getValue').length>50){
        toastr.error('最多输入25个汉字，或者50个英文字母！');
        return false;
    }else {
        $('#finishNew').hide();
        $('#addNew').show();
        $('#newsSetDatagrid').datagrid('updateRow',{
            index: $('#index').val(),
            row: {
                themeText: $('#themeText').textbox('getValue'),
                newUrl: $('#newUrl').textbox('getValue')
            }
        });
        loadBtn();
        $('#themeText').textbox('setValue','');
        $('#newUrl').textbox('setValue','');
    }
})

function loadNewsImgSetData() {
    $('#newsImgSetDatagrid').datagrid({
        url: ctx + "/sysSet/getAllNewsImgList",
        idField: 'empId',
        striped: true,
        singleSelect: true,
        scrollbarSize:15,
        border: false,
        columns: [[
            {
                field: 'themeText',
                title: '主题文字',
                width: 70,
                align: 'center',
                formatter: function(value,row,index){
                    if(row.themeText == null){
                        return "无文字"
                    }else {
                        return row.themeText;
                    }
                }
            },
            {
                field: 'newUrl',
                title: '新闻外链接',
                width: 90,
                align: 'center',
                formatter: function(value,row,index){
                    if(row.newUrl == null){
                        return "无链接"
                    }else {
                        return row.newUrl;
                    }
                }
            },
            {
                field: 'img',
                title: '图片',
                width: 20,
                align: 'center',
                formatter: function(value,row,index){
                    var str = '<a href="javascript:;" onclick="loadNewsImg('+index+')" name="getNewsImg" class="easyui-linkbutton" >修改</a>';
                    if(row.imgUrl == null && row.imgMd == null){
                        str = '<a href="javascript:;" onclick="loadNewsImg('+index+')" name="getAddNewsImg" class="easyui-linkbutton" >添加</a>';
                    }
                    return str;
                }
            },
            {
                field:'delete',title:'操作',align:'center',width:20,
                formatter:function(value, row, index){
                    var str = '<a href="javascript:;" onclick="updateNewsImg('+index+')" name="updateNewsImg" class="easyui-linkbutton" ></a><a href="javascript:;" onclick="removeNewsImg('+index+')" name="removeNewsImg" class="easyui-linkbutton" ></a>';
                    return str;
                }
            },
            {
                field:'move',title:'上/下移',align:'center',width:16,
                formatter:function(value, row, index){
                    var str = '<a href="javascript:;" onclick="moveImg('+index+')" name="moveImg" class="easyui-linkbutton" > ↑ </a><a href="javascript:;" onclick="downImg('+index+')" name="downImg" class="easyui-linkbutton" > ↓ </a>';
                    return str;
                }
            }
        ]],
        fitColumns: 'true',
        fit:true,
        title:'推荐新闻滚轮图列表',
        toolbar: '#tb5',
        rownumbers:true,
        onLoadSuccess:function(data) {
            loadImgBtn();
        }
    });
}

function loadNewsImg(index) {
    $('#index1').val(index);
    var row = $('#newsImgSetDatagrid').datagrid('getData').rows[index];
    var add ;
    if (row.isNetworkImg == 0){
        add = ctx + "/file/downloadFile?md5="+row.imgMd;
    }else {
        add= row.imgUrl;
    }
    var pageInit = new PageInit();
    pageInit.portraitUpload(index,add);
}

function loadImgBtn() {
    $("a[name='removeNewsImg']").linkbutton({plain: true, iconCls: 'icon-remove'});
    $("a[name='moveImg']").linkbutton({plain: true});
    $("a[name='downImg']").linkbutton({plain: true});
    $("a[name='getNewsImg']").linkbutton({plain: true,iconCls: 'icon-search'});
    $("a[name='getAddNewsImg']").linkbutton({plain: true,iconCls: 'icon-add'});
    $("a[name='updateNewsImg']").linkbutton({plain: true,iconCls: 'icon-edit'});
    $('#newsImgSetDatagrid').datagrid('fixRowHeight');
}

$('#newsImgSet').click(function () {
    $('#newsImgSetModal').modal('show');
})

$('#newsImgSetModal').on('shown.bs.modal', function (e) {
    $("#chart11").css('display','block');
    loadNewsImgSetData();
});

function moveImg(rowIndex) {
    var rows=$('#newsImgSetDatagrid').datagrid('getRows');
    var rowlength=rows.length;
    var selectrow = $('#newsImgSetDatagrid').datagrid('getData').rows[rowIndex];
    if(rowIndex==0){
    }else{
        $('#newsImgSetDatagrid').datagrid('deleteRow', rowIndex);//删除一行
        rowIndex--;
        $('#newsImgSetDatagrid').datagrid('insertRow', {
            index:rowIndex,
            row:selectrow
        });
        $('#newsImgSetDatagrid').datagrid('selectRow', rowIndex);
    }
    loadImgBtn();
}

function downImg(rowIndex) {
    var rows=$('#newsImgSetDatagrid').datagrid('getRows');
    var rowlength=rows.length;
    var selectrow = $('#newsImgSetDatagrid').datagrid('getData').rows[rowIndex];
    if(rowIndex==rowlength-1){
    }else{
        $('#newsImgSetDatagrid').datagrid('deleteRow', rowIndex);//删除一行
        rowIndex++;
        $('#newsImgSetDatagrid').datagrid('insertRow', {
            index:rowIndex,
            row:selectrow
        });
        $('#newsImgSetDatagrid').datagrid('selectRow', rowIndex);
    }
    loadImgBtn();
}


function updateNewsImg(index) {
    var row = $('#newsImgSetDatagrid').datagrid('getData').rows[index];
    $('#index1').val(index);
    $('#themeText1').textbox('setValue',row.themeText);
    $('#newUrl1').textbox('setValue',row.newUrl);
    $('#addNewImgs').hide();
    $('#finishNewImgs').show();
}

$('#finishNewImgs').click(function () {
    if($('#themeText1').textbox('getValue').length>150){
        toastr.error('最多输入75个汉字，或者150个英文字母！');
        return false;
    }else {
        $('#finishNewImgs').hide();
        $('#addNewImgs').show();
        $('#newsImgSetDatagrid').datagrid('updateRow',{
            index: $('#index1').val(),
            row: {
                themeText: $('#themeText1').textbox('getValue'),
                newUrl: $('#newUrl1').textbox('getValue')
            }
        });
        loadImgBtn();
        $('#themeText1').textbox('setValue','');
        $('#newUrl1').textbox('setValue','');
    }
})


$('#addNewImgs').click(function () {
    if($('#themeText1').textbox('getValue').length>150){
        toastr.error('最多输入75个汉字，或者150个英文字母！');
        return false;
    }
    var rows=$('#newsImgSetDatagrid').datagrid('getRows');
    var rowlength=rows.length;
    if(rowlength>6){
        toastr.error('最多添加7条图片新闻！');
        $('#themeText1').textbox('setValue','');
        $('#newUrl1').textbox('setValue','');
        return false;
    }else {
        $('#newsImgSetDatagrid').datagrid('insertRow',{
            row: {
                themeText: $('#themeText1').textbox('getValue'),
                newUrl: $('#newUrl1').textbox('getValue')
            }
        });
        loadImgBtn();
        $('#themeText1').textbox('setValue','');
        $('#newUrl1').textbox('setValue','');
    }
})

function removeNewsImg(index) {
    Ewin.confirm({ message: "确认删除此新闻吗？" }).on(function (e) {
        if (!e) {
            return;
        }else{
            $('#newsImgSetDatagrid').datagrid('deleteRow',index);
        }
    });
}




function PageInit() {
    var api = null;
    var _this = this;
    /*this.init = function () {
        $("#headImg1").on('click', this.portraitUpload)
    };*/
    this.portraitUpload = function (index,url) {
        var img = $('#cut-img');
        setModalCenter('newImgModal');
        $('#newImgModal').modal('show');
        img.removeAttr('src');
        img.attr('src', url);
        var fileUp = new FileUpload();
        var portrait = $('#fileUpload');
        var alert = $('#alert');
        fileUp.portrait(portrait, ctx + "/file/uploadFile", _this.getExtraData);
        portrait.on('change', _this.readURL);
        portrait.on('fileuploaderror', function (event, data, msg) {
            alert.removeClass('hidden').html(msg);
            fileUp.fileinput('disable');
            setModalCenter('newImgModal');
        });
        portrait.on('fileclear', function (event) {
            alert.addClass('hidden').html();
            img.removeAttr('src');
            setModalCenter('newImgModal');
        });
        portrait.on('fileloaded', function (event, file, previewId, index, reader) {
            alert.addClass('hidden').html();
            setModalCenter('newImgModal');
        });
        portrait.on('fileuploaded', function (event, data) {
            if (!data.response.status) {
                alert.html(data.response.message).removeClass('hidden');
            }
            $('#newsImgSetDatagrid').datagrid('updateRow',{
                index: index,
                row: {
                    imgUrl:null,
                    imgMd: data.response.object,
                    isNetworkImg: 0
                }
            });
            loadImgBtn();
            setModalCenter('newImgModal');
        })
    };

    this.readURL = function () {
        var img = $('#cut-img');
        var input = $('#fileUpload');
        if (input[0].files && input[0].files[0]) {
            var reader = new FileReader();
            reader.readAsDataURL(input[0].files[0]);
            reader.onload = function (e) {
                img.removeAttr('src');
                img.attr('src', e.target.result);
            };
            if (api != undefined) {
                api.destroy();
            }
        }
    };

    this.getExtraData = function () {
        return {
            sw: $('.jcrop-holder').css('width'),
            sh: $('.jcrop-holder').css('height'),
            x: $('#x').val(),
            y: $('#y').val(),
            w: $('#w').val(),
            h: $('#h').val()
        }
    }
}

function FileUpload() {
    //start
    var header = $("meta[name='_csrf_header']").attr("content");
    var token = $("meta[name='_csrf']").attr("content");
    //end
    this.portrait = function (target, uploadUrl, data) {
        target.fileinput({
            language: 'zh', //设置语言
            maxFileSize:20480,//文件最大容量
            /*uploadExtraData: data,//上传时除了文件以外的其他额外数据*/
            showPreview: false,//隐藏预览
            uploadAsync: true,//ajax同步
            /*dropZoneEnabled: false,//是否显示拖拽区域*/
            uploadUrl: uploadUrl, //上传的地址
            allowedFileExtensions: ['jpg', 'gif', 'png', 'bmp'],//接收的文件后缀
            showUpload: true, //是否显示上传按钮
            showCaption: true,//是否显示标题
            browseClass: "btn btn-primary", //按钮样式
            previewFileIcon: "<i class='glyphicon glyphicon-king'></i>"
        });
    }
}

$('#addNetImg').click(function () {
    $('#addNetImgModal').modal('show');
})

$('#saveNetImg').click(function () {
    var netImg = $("#imgNetUrl").val();
    if(netImg.length<1){
        toastr.error('请输入网络图片地址！');
        return false;
    }
    $('#newsImgSetDatagrid').datagrid('updateRow',{
        index: $("#index1").val(),
        row: {
            imgUrl:netImg,
            imgMd: null,
            isNetworkImg: 1
        }
    });
    loadImgBtn();
    var img = $('#cut-img');
    img.removeAttr('src');
    img.attr('src', netImg);
})

$('#saveImgChange').click(function () {
    var rows=$('#newsImgSetDatagrid').datagrid('getRows');
    $.ajax({
        type: "POST",
        url: ctx + "/sysSet/saveAllNewImg",
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(rows),
        success: function (result) {
            toastr.success('保存'+result.message);
            $('#newsImgSetModal').modal('hide');
        },
        error:function (result) {
            toastr.error('保存失败，请联系管理员！');
        }
    });
})

$('#themeText1').textbox({
    multiline:true
});
$('#newUrl1').textbox({
    multiline:true
});