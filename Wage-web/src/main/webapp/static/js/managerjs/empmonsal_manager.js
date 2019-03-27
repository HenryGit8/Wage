$(document).ready(function () {
    toastr.options.positionClass = 'toast-top-right';
});

$('#datagrid1').datagrid({
    url: ctx + "/empMonSal/getMonSalBySearch",
    idField: 'empId',
    singleSelect: true,
    pageSize: 20,
    border: false,
    pagination: true,
    queryParams: $("#searchMonSal").serializeObject(),
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
            field: 'empBasicPay',
            title: '基础工资',
            width: 70,
            align: 'center',
            sortable:true
        },
        {
            field: 'empJobSalary',
            title: '岗位工资',
            width: 70,
            align: 'center',
            sortable:true
        },
        {
            field: 'monSal',
            title: '固定工资总额',
            width: 70,
            align: 'center',
            sortable:true
        },
        {
            field: 'otherSal',
            title: '额外工资总额',
            width: 70,
            align: 'center',
            sortable:true,
            formatter: function(value,row,index){
                if (row.otherSal == null){
                    return '无记录';
                }else {
                    return row.otherSal;
                }
            }
        },
        {
            field: 'deductSal',
            title: '扣除工资总额',
            width: 70,
            align: 'center',
            sortable:true,
            formatter: function(value,row,index){
                if (row.deductSal == null){
                    return '无记录';
                }else {
                    return row.deductSal;
                }
            }
        },
        {
            field:'operate1',title:'额外/扣除工资明细',align:'center',width:70,
            formatter:function(value, row, index){
                var str = '<a href="javascript:;" onclick="checkOnClick('+index+')" name="check"  >查看明细</a>';
                return str;
            }
        },
        {
            field:'operate',title:'操作',align:'center',width:70,
            formatter:function(value, row, index){
                var str = '<a href="javascript:;" onclick="updateOnClick('+index+')" name="update" class="easyui-linkbutton" >调整</a>';
                return str;
            }
        }
    ]],
    fitColumns: 'true',
    striped: 'true',
    fit:true,
    title:'员工固定工资表',
    toolbar: '#tb',
    onLoadSuccess:function(data){
        $("a[name='update']").linkbutton({plain:true,iconCls:'icon-edit'});
    },
    onDblClickRow:function (index, row) {
        var p = $("#layout").layout("panel", "south")[0].clientWidth;
        if(p == 0){
            $("#layout").layout("expand", 'south');
        }
        var data = {
            empId: row.empId
        };
        $('#datagrid2').datagrid('load', data);
    }
});

function checkOnClick(index) {
    var row = $('#datagrid1').datagrid('getData').rows[index];
    var p = $("#layout").layout("panel", "south")[0].clientWidth;
    if(p == 0){
        $("#layout").layout("expand", 'south');
    }
    var data = {
        empId: row.empId
    };
    $('#datagrid2').datagrid('load', data);
}

$("#searchBtn").click(function () {
    var data = $("#searchMonSal").serializeObject();
    $('#datagrid1').datagrid('load', data);
});

$("#clearBtn").click(function () {
    $("#searchMonSal").form('clear');
});

$("#basicPayAfter,#jobSalaryAfter").blur(function(){
    if($('#basicPayAfter').val() != ''){
        $('#basicPayChance').val(ForDight(Number($('#basicPayAfter').val())-Number($('#basicPayBefore').val()),1));
    }
    if($('#jobSalaryAfter').val() != ''){
        $('#jobSalaryChance').val(ForDight(Number($('#jobSalaryAfter').val())-Number($('#jobSalaryBefore').val()),1));
    }
    if($('#jobSalaryAfter').val() != '' && $('#basicPayAfter').val() != ''){
        $('#totalAfter').val(Number($('#basicPayAfter').val()) + Number($('#jobSalaryAfter').val()));
    }
    if($('#totalAfter').val() != ''){
        $('#totalChance').val(ForDight(Number($('#totalAfter').val())-Number($('#totalBefore').val()),1));
    }
});

function updateOnClick(index) {
    $('#updateForm')[0].reset();
    var row = $('#datagrid1').datagrid('getData').rows[index];
    $('#myModalLabel').html('调整月固定工资' + " — "+row.empName);
    $('#basicPayBefore').val(row.empBasicPay);
    $('#jobSalaryBefore').val(row.empJobSalary);
    $('#totalBefore').val(row.monSal);
    $('#empId').val(row.empId);
    $('#myModal').modal('show');
};

$("#sumbitUpdate").click(function () {
    if($('#basicPayAfter').val() == ''){
        $('#basicPayAfter').val($('#basicPayBefore').val());
    }
    if($('#jobSalaryAfter').val() == ''){
        $('#jobSalaryAfter').val($('#jobSalaryBefore').val());
    }
    if($('#jobSalaryAfter').val() != '' && $('#basicPayAfter').val() != ''){
        $('#totalAfter').val(Number($('#basicPayAfter').val()) + Number($('#jobSalaryAfter').val()));
    }
    $.ajax({
        type: "POST",
        url: ctx + "/empMonSal/updateEmpMonSal",
        dataType: "json",
        data: $("#updateForm").serializeObject(),
        success: function (result) {
            toastr.success('保存'+result.message);
            $('#myModal').modal('hide');
            $('#datagrid1').datagrid('reload');
        },
        error:function (result) {
            toastr.error('保存失败，请联系管理员！');
        }
    });
});

$('#datagrid2').datagrid({
    url: ctx + "/empMonSal/getOtherSal",
    idField: 'empId',
    striped: true,
    singleSelect: true,
    pageSize: 10,
    border: false,
    pagination: true,
    columns: [
        [
            {
                field: 'yearMonth',
                title: "年月",
                width: 70,
                colspan: 1,
                rowspan:2,
                align: 'center',
                sortable: true
            },
            {
                title: "月额外工资",
                colspan: 6
            },
            {
                title: "月扣除工资",
                colspan: 3
            },
            {
                field: 'operate1',
                title: '操作',
                align: 'center',
                width: 70,
                colspan: 1,
                rowspan:2,
                formatter: function (value, row, index) {
                    var str = '<a href="javascript:;" onclick="chanceOnClick(' + index + ')" name="chance1" class="easyui-linkbutton" >修改</a>';
                    return str;
                }
            }
        ], [
            {
                field: 'monthAllowance',
                title: '津贴',
                width: 70,
                align: 'center',
                sortable: true
            },
            {
                field: 'monthSubsidy',
                title: '补贴',
                width: 70,
                align: 'center',
                sortable: true
            },
            {
                field: 'monthBonus',
                title: '本月奖金',
                width: 70,
                align: 'center',
                sortable: true
            },
            {
                field: 'overtimePay',
                title: '加班费',
                width: 70,
                align: 'center',
                sortable: true
            },
            {
                field: 'yearEndBonus',
                title: '年终奖金',
                width: 70,
                align: 'center',
                sortable: true
            },
            {
                field: 'otherSal',
                title: '额外工资总额',
                width: 70,
                align: 'center',
                sortable: true
            },
            {
                field: 'restSalary',
                title: '请假/旷工扣除',
                width: 70,
                align: 'center',
                sortable: true
            },
            {
                field: 'fineSalary',
                title: '其他扣除',
                width: 70,
                align: 'center',
                sortable: true
            },
            {
                field: 'deductSal',
                title: '扣除工资总额',
                width: 70,
                align: 'center',
                sortable: true
            }
        ]],
    fitColumns: 'true',
    striped: 'true',
    fit: true,
    onLoadSuccess: function (data) {
        $("a[name='chance1']").linkbutton({plain: true, iconCls: 'icon-edit'});
    }
});

$('#empName').textbox({
    labelPosition: 'left',
    prompt: '请输入名字',
    labelAlign: 'left',
    labelWidth:60
});

function chanceOnClick(index) {
    var row = $('#datagrid2').datagrid('getData').rows[index];
    var myDate = new Date();
    var year = myDate.getFullYear();
    var month = Number(myDate.getMonth())+1;
    if(month.toString().length == 1){
        month = "0"+String(month)
    }
    var yearMonth = String(year)+month;
    if(row.yearMonth == yearMonth){
        $('#monthAllowance').attr("disabled","disabled");
        $('#monthSubsidy').attr("disabled","disabled");
        $('#monthBonus').attr("disabled","disabled");
        $('#overtimePay').attr("disabled","disabled");
        $('#yearEndBonus').attr("disabled","disabled");
        $('#restSalary').attr("disabled","disabled");
    }else{
        $('#monthAllowance').removeAttr("disabled");
        $('#monthSubsidy').removeAttr("disabled");
        $('#monthBonus').removeAttr("disabled");
        $('#overtimePay').removeAttr("disabled");
        $('#yearEndBonus').removeAttr("disabled");
        $('#restSalary').removeAttr("disabled");
    }
    $('#chanceForm')[0].reset();
    $('#yearMonth').val(row.yearMonth);
    $('#empId1').val(row.empId);
    $('#empName1').val(row.empName);
    $('#monthAllowance').val(row.monthAllowance);
    $('#monthSubsidy').val(row.monthSubsidy);
    $('#monthBonus').val(row.monthBonus);
    $('#overtimePay').val(row.overtimePay);
    $('#yearEndBonus').val(row.yearEndBonus);
    $('#otherSal').val(row.otherSal);
    $('#restSalary').val(row.restSalary);
    $('#fineSalary').val(row.fineSalary);
    $('#deductSal').val(row.deductSal);
    $('#myModal2').modal('show');
};

$("#monthAllowance,#monthSubsidy,#monthBonus,#overtimePay,#yearEndBonus").blur(function(){
    fillAll();
});

$("#restSalary,#fineSalary").blur(function(){
    fillDeduct();
});

function fillAll() {
    fillForm('monthAllowance');
    fillForm('monthSubsidy');
    fillForm('monthBonus');
    fillForm('overtimePay');
    fillForm('yearEndBonus');
    $('#otherSal').val(Number($('#monthAllowance').val()) + Number($('#monthSubsidy').val()) + Number($('#monthBonus').val()) + Number($('#overtimePay').val()) + Number($('#yearEndBonus').val()));
};

function fillDeduct() {
    fillForm('restSalary');
    fillForm('fineSalary');
    $('#deductSal').val(Number($('#restSalary').val()) + Number($('#fineSalary').val()));
};

function fillForm(str) {
    if($('#'+str).val() == ''){
        $('#'+str).val('0');
    }
};

$('#sumbitChance').click(function () {
    fillAll();
    fillDeduct();
    $.ajax({
        type: "POST",
        url: ctx + "/empMonSal/updateOtherSal",
        dataType: "json",
        data: $("#chanceForm").serializeObject(),
        async: false,
        success: function (result) {
            toastr.success('保存'+result.message);
            $('#myModal2').modal('hide');
            $('#datagrid2').datagrid('reload');
        },
        error:function (result) {
            toastr.error('保存失败，请联系管理员！');
        }
    });
});
