$(document).ready(function () {
});
$('#datagrid2').datagrid({
    url: ctx + "/empMonSal/getByEmpId",
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
            width: 100,
            align: 'center'
        },
        {
            field: 'empName',
            title: '姓名',
            width: 100,
            align: 'center'
        },
        {
            field: 'changeTime',
            title: '年月',
            width: 100,
            align: 'center'
        },
        {
            field: 'typeOfWage',
            title: '固定工资',
            width: 100,
            align: 'center'
        },
        {
            field: 'otherSal',
            title: '额外工资',
            width: 100,
            align: 'center'
        },
        {
            field: 'deductSal',
            title: '扣除工资',
            width: 100,
            align: 'center'
        },
        {
            field: 'total',
            title: '总额（未税）',
            width: 100,
            align: 'center'
        }
    ]],
    fitColumns: 'true',
    striped: 'true',
    fit:true,
    onClickRow:function (index, row) {
        readchart1(row.empId,row.empName,row.changeTime);
    }

});
$('#datagrid1').treegrid({
    title:'人员查询',
    iconCls:'icon-search',
    url:ctx + "/empInfo/getEmpTree",
    method: 'get',
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
        },
        {
            field: 'departRand',
            title: '薪资等级',
            width: 40,
            formatter: function(value,row,index){
                if(row.departRand == null){
                    return '';
                }else {
                    return '等级'+row.departRand;
                }
            },
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
    onClickRow:function ( row) {
        if(row.parentId != null) {
            var data = {
                empId: row.empId
            };
            $('#datagrid2').datagrid('load', data);
            readchart1(row.empId, row.empName, null);
            readchart2(row.empId, row.empName);
        }
    },
    onLoadSuccess:function(data){
        $('#datagrid1').treegrid("select", 100001);
        var row = $('#datagrid1').treegrid('getSelected');
        var data = {
            empId: row.empId
        };
        $('#datagrid2').datagrid('load', data);
        readchart1(row.empId,row.empName,null);
        readchart2(row.empId,row.empName);
    }

});

$("#searchBtn").click(function () {
    var data = {
        empName: $('#Eq').val()
    };
    $('#datagrid1').treegrid('load', data);
});

$("#generateBtn").click(function () {
    $.ajax({
        type: "GET",
        url: ctx + "/empMonSal/saveAllMonSal",
        dataType: "json",
        success: function (result) {
            toastr.success('生成本月数据' + result.message);
            var row = $('#datagrid1').treegrid('getSelected');
            var data = {
                empId: row.empId
            };
            $('#datagrid2').datagrid('load', data);
            readchart1(row.empId,row.empName,null);
            readchart2(row.empId,row.empName);
        },
        error: function (result) {
            toastr.error('生成本月数据失败，请联系管理员！');
        }
    });
});

function readchart1(empId,empName,changeTime) {
    var totalAllResult;
    var monthResult;
    if(changeTime == null){
        $.ajax({
            type: "GET",
            url: ctx + "/empMonSal/getTotal?empId="+empId,
            dataType: "json",
            async: false,
            success: function (result) {
                totalAllResult = result["totalAll"];
            }
        });
    }else{
        $.ajax({
            type: "GET",
            url: ctx + "/empMonSal/getTotal?empId="+empId+"&changeTime="+changeTime,
            dataType: "json",
            async: false,
            success: function (result) {
                totalAllResult = result["totalOneAll"];
            }
        });

    }
    var chart = {
        plotBackgroundColor: null,
        plotBorderWidth: null,
        plotShadow: false
    };
    var title = {
        text: '各工资占比饼图'
    };
    var tooltip = {
        pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
    };
    var plotOptions = {
        pie: {
            allowPointSelect: true,
            cursor: 'pointer',
            dataLabels: {
                enabled: false
            },
            showInLegend: true
        }
    };
    var series= [{
        type: 'pie',
        name: '占比为',
        data: totalAllResult
    }];

    var json = {};
    json.chart = chart;
    json.title = title;
    json.tooltip = tooltip;
    json.series = series;
    json.plotOptions = plotOptions;
    $('#container1').highcharts(json);
}

function readchart2(empId,empName) {
    var basciResult;
    var otherResult;
    var deductResult;
    var monthResult;
    var totalResult;
    $.ajax({
        type: "GET",
        url: ctx + "/empMonSal/getTotal?empId="+empId,
        dataType: "json",
        async: false,
        success: function (result) {
            basciResult = result["basic"];
            otherResult = result["other"];
            deductResult = result["deduct"];
            monthResult = result["month"];
            totalResult = result["total"];
        }
    });
    var title = {
        text: '半年内各类型工资变化记录'
    };
    var xAxis = {
        categories: monthResult
    };
    var yAxis = {
        title: {
            text: '金额 (￥)'
        },
        plotLines: [{
            value: 0,
            width: 1,
            color: '#808080'
        }]
    };
    var labels = {
        items: [{
            html: '总额',
            style: {
                left: '50px',
                top: '18px',
                color: (Highcharts.theme && Highcharts.theme.textColor) || 'black'
            }
        }]
    };
    var series= [{
        type: 'column',
        name: '固定工资',
        data: basciResult
    }, {
        type: 'column',
        name: '额外工资',
        data: otherResult
    }, {
        type: 'column',
        name: '扣除工资',
        data: deductResult
    }, {
        type: 'spline',
        name: '总额',
        data: totalResult,
        marker: {
            lineWidth: 2,
            lineColor: Highcharts.getOptions().colors[3],
            fillColor: 'white'
        }
    }
    ];
    var json = {};
    json.title = title;
    json.xAxis = xAxis;
    json.labels = labels;
    json.series = series;
    $('#container').highcharts(json);
};
