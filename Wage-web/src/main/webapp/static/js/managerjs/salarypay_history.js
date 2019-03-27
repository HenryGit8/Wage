$(document).ready(function () {
    loadPg();
    loadData();
    $("#dd").dialog('close');
});
function loadPg(){
    $('#pg').propertygrid({
        fit:true,
        fitColumns: true,
        showGroup:true,
        showHeader:false,
        border: false,
        scrollbarSize:0
    });
    $('#pg1').propertygrid({
        fit:true,
        fitColumns: true,
        showGroup:true,
        showHeader:false,
        border: false,
        scrollbarSize:0
    });
    $('#pg2').propertygrid({
        fit:true,
        fitColumns: true,
        showHeader:false,
        border: false,
        scrollbarSize:0
    });
}
$('#datagrid').datagrid({
    url: ctx + "/hisSalary/getBySearch",
    idField: 'empId',
    striped: true,
    singleSelect: true,
    pageSize: 20,
    border: false,
    pagination: true,
    columns: [[
        {
            field: 'yearMonth',
            title: '年月',
            width: 70,
            align: 'center'
        },{
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
            field: 'monSal',
            title: '固定工资',
            width: 70,
            align: 'center',
            sortable: true
        },
        {
            field: 'otherSal',
            title: '其他工资',
            width: 70,
            align: 'center',
            sortable: true
        },
        {
            field: 'deductSal',
            title: '扣除工资',
            width: 70,
            align: 'center',
            sortable: true
        },
        {
            field: 'grossPay',
            title: '应发工资',
            width: 70,
            align: 'center',
            sortable: true
        },
        {
            field: 'actualPay',
            title: '实发工资',
            width: 70,
            align: 'center',
            sortable: true
        },
        {
            field: 'empBankCard',
            title: '银行卡号',
            width: 150,
            align: 'center'
        },
        {
            field: 'isGrant',
            title: '是否发放',
            width: 70,
            align: 'center',
            formatter: function(value,row,index){
                if (row.isGrant == 0){
                    return '未发放';
                }else {
                    return '已发放';
                }
            }
        },
        {
            field:'operate1',title:'工资单',align:'center',width:70,
            formatter:function(value, row, index){
                var str = '<a href="javascript:;" onclick="openWageTable('+index+')" name="check"  >查看</a>';
                return str;
            }
        }
    ]],
    fitColumns: 'true',
    striped: 'true',
    fit:true,
    scrollbarSize:16,
    title:'月工资发放记录表',
    onLoadSuccess:function(data) {
        $("a[name='update']").linkbutton({plain: true, iconCls: 'icon-edit'});
    },
    onClickRow:function (index, row) {
        updateData(index);
    }

});

function loadData() {
    insertRow('pg','基础工资','固定工资',0);
    insertRow('pg','岗位工资','固定工资',1);
    insertRow('pg','津贴','其他工资',2);
    insertRow('pg','补贴','其他工资',3);
    insertRow('pg','月奖金','其他工资',4);
    insertRow('pg','年终奖金','其他工资',5);
    insertRow('pg','加班费','其他工资',6);
    insertRow('pg','请假扣除','扣除工资',7);
    insertRow('pg','其他扣除','扣除工资',8);
    insertRow('pg1','养老保险金','个人应缴四金',0);
    insertRow('pg1','医疗保险金','个人应缴四金',1);
    insertRow('pg1','失业保险金','个人应缴四金',2);
    insertRow('pg1','基本住房公积金','个人应缴四金',3);
    insertRow('pg1','养老保险金','单位应缴五险一金',4);
    insertRow('pg1','医疗保险金','单位应缴五险一金',5);
    insertRow('pg1','失业保险金','单位应缴五险一金',6);
    insertRow('pg1','基本住房公积金','单位应缴五险一金',7);
    insertRow('pg1','工伤保险金','单位应缴五险一金',8);
    insertRow('pg1','生育保险金','单位应缴五险一金',9);
    insertRow('pg2','扣除四金后月薪','',0);
    insertRow('pg2','个人所得税','',1);
}

function updateData(index) {
    $('#remarks').textbox('disable');
    $('#subBtn').linkbutton('disable');
    $('#updateBtn').linkbutton('enable');
    var row = $('#datagrid').datagrid('getData').rows[index];
    $.ajax({
        type: "GET",
        url: ctx + "/empMonSal/getOtherSalOne",
        data: {
            "empId":row.empId,
            "yearMonth":row.yearMonth
        },
        dataType: "json",
        success: function (result) {
            updateRow('pg',result.empBasicPay,'基础工资',0);
            updateRow('pg',result.empJobSalary,'岗位工资',1);
            updateRow('pg',result.monthAllowance,'津贴',2);
            updateRow('pg',result.monthSubsidy,'补贴',3);
            updateRow('pg',result.monthBonus,'月奖金',4);
            updateRow('pg',result.yearEndBonus,'年终奖金',5);
            updateRow('pg',result.overtimePay,'加班费',6);
            updateRow('pg',result.restSalary,'请假扣除',7);
            updateRow('pg',result.fineSalary,'其他扣除',8);
            updateRow('pg1',row.penInsurPay,'养老保险金',0);
            updateRow('pg1',row.medInsurPay,'医疗保险金',1);
            updateRow('pg1',row.unempInsurPay,'失业保险金',2);
            updateRow('pg1',row.basHousProFundPay,'基本住房公积金',3);
            updateRow('pg1',row.penInsurPayC,'养老保险金',4);
            updateRow('pg1',row.medInsurPayC,'医疗保险金',5);
            updateRow('pg1',row.unempInsurPayC,'失业保险金',6);
            updateRow('pg1',row.basHousProFundPayC,'基本住房公积金',7);
            updateRow('pg1',row.empInjuryInsurPayC,'工伤保险金',8);
            updateRow('pg1',row.birthInsurC,'生育保险金',9);
            updateRow('pg2',(row.grossPay-row.pensonPay).toFixed(1),'扣除四金后月薪',0);
            updateRow('pg2',row.personIncomeTax,'个人所得税',1);
            var grantName = row.grantName;
            var grantTime = row.grantTime;
            var remarks = row.remarks;
            if(grantName == null){
                grantName = '未发放';
            }
            if(grantTime == null){
                grantTime = '未发放';
            }
            if(remarks == null){
                remarks = '无备注';
            }
            $('#grantEmpid').textbox('setValue',grantName);
            $('#grantTime').textbox('setValue',grantTime);
            $('#remarks').textbox('setValue',remarks);
        }
    });
}

function insertRow(gridname,name,group,index) {
    $('#'+gridname).propertygrid('insertRow',{
        index: index,
        row: {
            name:name,
            value:'无数据',
            group:group
        }
    });
}

function updateRow(gridname,value,name,index) {
    $('#'+gridname).propertygrid('updateRow',{
        index: index,
        row: {
            value:value
        }
    });
}
$("#updateBtn").click(function () {
    $('#remarks').textbox('enable');
    $('#subBtn').linkbutton('enable');
    $('#updateBtn').linkbutton('disable');
});
$("#subBtn").click(function () {
    var row = $('#datagrid').datagrid('getSelected');
    $.ajax({
        type: "POST",
        url: ctx + "/hisSalary/updateData",
        dataType: "json",
        data: {
            "remarks":$('#remarks').textbox('getValue'),
            "yearMonth":row.yearMonth,
            "empId":row.empId
        },
        success: function (result) {
            toastr.success('保存'+result.message);
            $('#datagrid').datagrid('reload');
            $('#remarks').textbox('disable');
            $('#subBtn').linkbutton('disable');
            $('#updateBtn').linkbutton('enable');
        },
        error:function (result) {
            toastr.error('保存失败，请联系管理员！');
        }
    });
});
$("#btn").click(function () {
    var data = $("#searchForm").serializeObject();
    $('#datagrid').datagrid('load', data);
});
$('#yearMonth').combobox({
    url:ctx + "/hisSalary/getAllMonth",
    valueField:'value',
    textField:'text',
    panelMaxHeight:138
});
$('#departId').combobox({
    url:ctx + "/hisSalary/getAllDepart",
    valueField:'value',
    textField:'text',
    panelMaxHeight:120
});

$('#btn1').click(function () {
    clearForm();
});

$('#btn7').click(function () {
    clearForm();
    var data = $("#searchForm").serializeObject();
    $('#datagrid').datagrid('load', data);
});

function clearForm() {
    $('#searchForm')[0].reset();
    $('#empName').textbox('clear');
    $('#yearMonth').combobox('clear');
    $('#departId').combobox('clear');
    $('#monSal').numberbox('clear');
    $('#otherSal').numberbox('clear');
    $('#deductSal').numberbox('clear');
    $('#isGrant').combobox('clear');
    $('#grantEmpid1').textbox('clear');
    $('#grossPayMin').numberbox('clear');
    $('#grossPayMax').numberbox('clear');
    $('#actualPayMin').numberbox('clear');
    $('#actualPayMax').numberbox('clear');
    $('#personIncomeTaxMin').numberbox('clear');
    $('#personIncomeTaxMax').numberbox('clear');
    $('#grantTimeStart').datebox('clear');
    $('#grantTimeEnd').datebox('clear');
}

$('#btn2').click(function () {
    $.ajax({
        type: "GET",
        url: ctx + "/hisSalary/updateHisSalary",
        dataType: "json",
        success: function (result) {
            toastr.success('更新数据' + result.message);
        },
        error: function (result) {
            toastr.error('更新数据失败，请联系管理员！');
        }
    });
})
$('#btn4').click(function () {
    $("#dd").dialog('open');
});
$('#print').click(function () {
    if($('#url').textbox('getValue').length < 1){
        toastr.error('请输入地址！');
        return false;
    }
    if($('#name').textbox('getValue').length < 1){
        toastr.name('请输入文件名称！');
        return false;
    }
    $('#url1').val($('#url').textbox('getValue'));
    $('#name1').val($('#name').textbox('getValue')+'.xls');
    $.ajax({
        type: "POST",
        url: ctx + "/hisSalary/getExcel",
        dataType: "json",
        data: $("#searchForm").serializeObject(),
        success: function (result) {
            if(result.object == 1){
                toastr.error('文件路径不存在');
            }else{
                toastr.success('生成excle' + result.message);
            }
        },
        error: function (result) {
            toastr.error('生成失败，请联系管理员！');
        }
    });
});
$('#close').click(function () {
    $("#dd").dialog('close');
});
function openWageTable(index) {
    var row = $('#datagrid').datagrid('getData').rows[index];
    $.ajax({
        type: "GET",
        url: ctx + "/empMonSal/getOtherSalOne",
        data: {
            "empId":row.empId,
            "yearMonth":row.yearMonth
        },
        dataType: "json",
        success: function (result) {
            chageInner("data1",row.yearMonth);
            chageInner("data2",row.empId);
            chageInner("data3",row.empName);
            chageInner("data4",result.empBasicPay);
            chageInner("data5",result.empJobSalary);
            chageInner("data6",result.monthAllowance);
            chageInner("data7",result.monthSubsidy);
            chageInner("data8",result.monthBonus);
            chageInner("data9",result.yearEndBonus);
            chageInner("data10",result.overtimePay);
            chageInner("data11",result.restSalary);
            chageInner("data12",result.fineSalary);
            chageInner("data13",row.pensonPay);
            chageInner("data14",row.companyPay);
            chageInner("data15",row.personIncomeTax);
            chageInner("data16",row.actualPay);
            $('#wageModal').modal('show');
        }
    });
};

function exportTo() {
    var yearMonth=document.getElementById("data1").innerHTML;
    var name=document.getElementById("data3").innerHTML;
    $('#wageTable').tableExport({
        filename: name+'工资单'+yearMonth,
        format: 'xls',
    });
};

function loadModal() {
    $('#modalDatagrid').datagrid({
        url: ctx + "/hisSalary/getIsGrant",
        idField: 'empId',
        striped: true,
        singleSelect: true,
        pageSize: 20,
        border: false,
        pagination: true,
        columns: [[
            {
                field: 'yearMonth',
                title: '年月',
                width: 70,
                align: 'center'
            },{
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
                field: 'grossPay',
                title: '应发工资',
                width: 70,
                align: 'center'
            },
            {
                field: 'actualPay',
                title: '实发工资',
                width: 70,
                align: 'center'
            },
            {
                field: 'empBankCard',
                title: '银行卡号',
                width: 150,
                align: 'center'
            },
            {
                field:'operate',title:'操作',align:'center',width:120,
                formatter:function(value, row, index){
                    var str = '<a href="javascript:;" onclick="pass('+index+')" name="pass" id="pass" class="easyui-linkbutton" >发放完成</a>';
                    return str;
                }
            }
        ]],
        fitColumns: 'true',
        striped: 'true',
        fit:true,
        toolbar: '#tb',
        scrollbarSize:16,
        title:'月工资发放',
        onLoadSuccess:function(data){
            $("a[name='pass']").linkbutton({plain:true,iconCls:'icon-ok'});
        }
    })
}

$('#btn3').click(function () {
    $('#updateIsGrantModal').modal('show');
});

$('#updateIsGrantModal').on('shown.bs.modal', function (e) {
    $("#chart5").css('display','block');
    loadModal();
});

$('#updateIsGrantModal').on('hide.bs.modal', function (e) {
    $("#chart5").css('display','none');
    $('#datagrid').datagrid('reload');
});

function pass(index) {
    var row = $('#modalDatagrid').datagrid('getData').rows[index];
    $.ajax({
        type: "POST",
        url: ctx + "/hisSalary/updateIsGrant",
        dataType: "json",
        data: {
            "empId":row.empId,
            "yearMonth":row.yearMonth,
            "isGrant":1,
            "grantTime":$('#grantTime1').val(),
            "remarks":$('#remarks1').val()
        },
        async: false,
        success: function (result) {
            $('#modalDatagrid').datagrid('reload');
            toastr.success('更新发放状态'+result.message);
        },
        error:function (result) {
            toastr.error('保存失败，请联系管理员！');
        }
    });
};

$('#btn5').click(function () {
    $('#chartModal').modal('show');
});

$('#chartModal').on('shown.bs.modal', function (e) {
    loadchart();
});

function loadchart() {
    var yearMonthList;
    var monSalSumList;
    var otherSalSumList;
    var deductSalSumList;
    var grossPaySumList;
    var actualPaySumList;
    var peiListData;
    $.ajax({
        type: "GET",
        url: ctx + "/hisSalary/getDataChart",
        dataType: "json",
        async: false,
        success: function (result) {
            yearMonthList = result["yearMonthList"];
            monSalSumList = result["monSalSumList"];
            otherSalSumList = result["otherSalSumList"];
            deductSalSumList = result["deductSalSumList"];
            grossPaySumList = result["grossPaySumList"];
            actualPaySumList = result["actualPaySumList"];
            peiListData = result["peiListData"];
        }
    });
    var title = {
        text: '半年内工资发放总额变化'
    };
    var xAxis = {
        categories: yearMonthList
    };
    var labels = {
        items: [{
            html: '本月各工资占比',
            style: {
                left: '50px',
                top: '18px',
                color: (Highcharts.theme && Highcharts.theme.textColor) || 'black'
            }
        }]
    };
    var series= [{
        type: 'column',
        name: '固定工资总额',
        data: monSalSumList
    }, {
        type: 'column',
        name: '额外工资总额',
        data: otherSalSumList
    }, {
        type: 'column',
        name: '扣除工资总额',
        data: deductSalSumList
    }, {
        type: 'column',
        name: '应发工资总额',
        data: grossPaySumList
    }, {
        type: 'spline',
        name: '实发工资总额',
        data: actualPaySumList,
        marker: {
            lineWidth: 2,
            lineColor: Highcharts.getOptions().colors[3],
            fillColor: 'white'
        }
    }, {
        type: 'pie',
        name: '占比',
        data: peiListData,
        center: [100, 80],
        size: 130,
        showInLegend: true,
        dataLabels: {
            enabled: false
        }
    }
    ];

    var json = {};
    json.title = title;
    json.xAxis = xAxis;
    json.labels = labels;
    json.series = series;
    $('#container').highcharts(json);
}

$('#btn6').click(function () {
    $.ajax({
        type : "POST", //使用post方法访问后台
        dataType : "json", //返回json格式的数据
        url: ctx + "/hisSalary/getTotalByMonth",
        async: false,
        success : function(result) {//result为返回的数据
            var list = result["monthsData"];
            $("#yearMonthCho").html("");
            for(var i=0; i<list.length; i++){
                $("#yearMonthCho").append($("<option value=\""+list[i]+"\">"+list[i]+"</option>"));
            }
            $("#yearMonthCho").val(list[0]);
        }
    });
    var totalData;
    $.ajax({
        type: "GET",
        url: ctx + "/hisSalary/getTotalByMonth",
        dataType: "json",
        async: false,
        data: {"yearMonth":$("#yearMonthCho").val()},
        success: function (result) {
            totalData = result["totalData"];
            chageInner("total1",totalData.monSal);
            chageInner("total2",totalData.otherSal);
            chageInner("total3",totalData.deductSal);
            chageInner("total4",totalData.grossPay);
            chageInner("total5",totalData.actualPay);
            chageInner("total6",totalData.penInsurPay);
            chageInner("total7",totalData.medInsurPay);
            chageInner("total8",totalData.unempInsurPay);
            chageInner("total9",totalData.basHousProFundPay);
            chageInner("total10",totalData.penInsurPayC);
            chageInner("total11",totalData.medInsurPayC);
            chageInner("total12",totalData.unempInsurPayC);
            chageInner("total13",totalData.basHousProFundPayC);
            chageInner("total14",totalData.empInjuryInsurPayC);
            chageInner("total15",totalData.birthInsurC);
            chageInner("total16",totalData.personIncomeTax);
            chageInner("total17",totalData.pensonPay);
            chageInner("total18",totalData.companyPay);
        }
    });
    $('#totalModal').modal('show');
});

$('#yearMonthCho').change(function(){
    var p1=$(this).children('option:selected').val();//这就是selected的值
    $.ajax({
        type : "POST", //使用post方法访问后台
        dataType : "json", //返回json格式的数据
        url: ctx + "/hisSalary/getTotalByMonth",
        data : {
            "yearMonth": p1
        },
        success: function (result) {
            totalData = result["totalData"];
            chageInner("total1",totalData.monSal);
            chageInner("total2",totalData.otherSal);
            chageInner("total3",totalData.deductSal);
            chageInner("total4",totalData.grossPay);
            chageInner("total5",totalData.actualPay);
            chageInner("total6",totalData.penInsurPay);
            chageInner("total7",totalData.medInsurPay);
            chageInner("total8",totalData.unempInsurPay);
            chageInner("total9",totalData.basHousProFundPay);
            chageInner("total10",totalData.penInsurPayC);
            chageInner("total11",totalData.medInsurPayC);
            chageInner("total12",totalData.unempInsurPayC);
            chageInner("total13",totalData.basHousProFundPayC);
            chageInner("total14",totalData.empInjuryInsurPayC);
            chageInner("total15",totalData.birthInsurC);
            chageInner("total16",totalData.personIncomeTax);
            chageInner("total17",totalData.pensonPay);
            chageInner("total18",totalData.companyPay);
        }
    });
});

function exportToC() {
    var yearMonth=$("#yearMonthCho").val();
    $('#totalTable').tableExport({
        filename: yearMonth+'各工资统计表',
        format: 'xls',
    });
};