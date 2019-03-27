$(function () {
    loadTab();
    loadMonSalTable();
    loadYearMonth();
});

function loadTab() {
    layui.use('element', function(){
        var element = layui.element;
        //监听Tab切换，以改变地址hash值
        element.on('tab(docDemoTabBrief)', function(){
            if(this.getAttribute('lay-id') == 2){
                loadFixedSalTable();
            }
            if(this.getAttribute('lay-id') == 1){
                loadMonSalTable();
            }
            if(this.getAttribute('lay-id') == 3){
                loadOtherSalTable();
            }
        });
    });
}
window.onresize = function(){
    loadMonSalTable();
    loadFixedSalTable();
    loadOtherSalTable();
}
function loadMonSalTable() {
    layui.use('table', function(){
        var table = layui.table;
        var tableIns = table.render({
            elem: '#monSalTable',
            url: ctx + "/hisSalary/getLoginData",
            page: true, //开启分页
            height: 'full-125',
            size:'sm',
            where: {
                yearMonth: $('#yearMonth').val()
            },
            limit:'20',
            even:true,
            cols: [[ //表头
                {
                    field: 'yearMonth',
                    title: '年月',
                    width: '7%',
                    align: 'center'
                }/*,
                {
                    field: 'empId',
                    title: '编号',
                    width: '7%',
                    align: 'center'
                }*//*,
                {
                    field: 'empName',
                    title: '姓名',
                    width: '9%',
                    align: 'center'
                }*/,
                {
                    field: 'monSal',
                    title: '固定工资',
                    width: '8%',
                    align: 'center'
                },
                {
                    field: 'otherSal',
                    title: '额外工资',
                    width: '8%',
                    align: 'center'
                },
                {
                    field: 'deductSal',
                    title: '扣除工资',
                    width: '8%',
                    align: 'center'
                },
                {
                    field: 'grossPay',
                    title: '应发工资',
                    width: '8%',
                    align: 'center'
                },
                {
                    field: 'actualPay',
                    title: '实发工资',
                    width: '8%',
                    align: 'center'
                },
                {
                    field: 'isGrant',
                    title: '是否发放',
                    width: '8%',
                    align: 'center',
                    templet: function (d) {
                        if (d.isGrant == 0){
                            return '未发放';
                        }else {
                            return '已发放';
                        }
                    }
                },
                {
                    field: 'grantTime',
                    title: '发放时间',
                    width: '14%',
                    align: 'center'
                },
                {
                    field: 'grantName',
                    title: '发放人员',
                    width: '11%',
                    align: 'center'
                },
                {
                    field: 'remarks',
                    title: '发放备注',
                    width: '12%',
                    align: 'center'
                }, {
                    field: 'payroll',
                    title: '工资单',
                    width: '7%',
                    align: 'center',
                    toolbar: "#barpay"
                }
            ]],
            response: {
                statusName: 'statusCode' //数据状态的字段名称，默认：code
                ,statusCode: 1 //成功的状态码，默认：0
                ,msgName: 'message' //状态信息的字段名称，默认：msg
                ,countName: 'total' //数据总数的字段名称，默认：count
                ,dataName: 'rows' //数据列表的字段名称，默认：data
            },
            request: {
                pageName: 'page' //页码的参数名称，默认：page
                ,limitName: 'rows' //每页数据量的参数名，默认：limit
            }
        });
        table.on('tool(monSalTable)', function(obj){
            var row = obj.data;
            if(obj.event === 'detail'){
                $.ajax({
                    type: "GET",
                    url: ctx + "/empMonSal/getOtherSalOne",
                    data: {
                        "empId":row.empId,
                        "yearMonth":row.yearMonth
                    },
                    dataType: "json",
                    success: function (result) {
                        chageInner("headText",row.yearMonth+"工资单");
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
                        if(row.actualPay == null){
                            chageInner("data16",'未发放');
                        }else {
                            chageInner("data16",row.actualPay);
                        }
                        $('#wageModal').modal('open');
                    }
                });
                /*layer.msg('ID：'+ data.id + ' 的查看操作');*/
            }
        });
        /*$('#searchMon').click(function () {
            tableIns.reload({
                where: { //设定异步数据接口的额外参数，任意设
                    yearMonth: $('#yearMonth').val()
                }
                ,page: {
                    curr: 1 //重新从第 1 页开始
                }
            });
        });*/
        var form = layui.form;
        form.on('select(yearMonth)', function(data){
            tableIns.reload({
                where: { //设定异步数据接口的额外参数，任意设
                    yearMonth: $('#yearMonth').val()
                }
                ,page: {
                    curr: 1 //重新从第 1 页开始
                }
            });
        });
    });
}
function loadYearMonth() {
    $.ajax({
        type : "POST", //使用post方法访问后台
        dataType : "json", //返回json格式的数据
        url: ctx + "/hisSalary/getAllMonth",//要访问的后台地址
        success : function(result) {//result为返回的数据
            var randIdList=new Array()
            randIdList = result;
            $("#yearMonth").html("");
            for(var i=0; i<randIdList.length; i++){
                $("#yearMonth").append($("<option value=\""+randIdList[i].value+"\">"+randIdList[i].text+"</option>"));
            }
            var form = layui.form;
            form.render();
        }
    });
}
function exportTo() {
    var yearMonth=document.getElementById("data1").innerHTML;
    var name=document.getElementById("data3").innerHTML;
    $('#wageTable').tableExport({
        filename: name+'工资单'+yearMonth,
        format: 'xls',
    });
};

function loadFixedSalTable() {
    layui.use('table', function () {
        var table1 = layui.table;
        var tableIns1 = table1.render({
            elem: '#fixedSalTable',
            url: ctx + "/empMonSal/getLoginByEmp",
            size: 'sm',
            even: true,
            cols: [[ //表头
                {
                    field: 'empId',
                    title: '员工编号',
                    width: '14%',
                    align: 'center'
                },
                {
                    field: 'empName',
                    title: '姓名',
                    width: '14%',
                    align: 'center'
                },
                {
                    field: 'empBasicPay',
                    title: '基础工资',
                    width: '14%',
                    align: 'center',
                    sortable: true
                },
                {
                    field: 'empJobSalary',
                    title: '岗位工资',
                    width: '14%',
                    align: 'center',
                    sortable: true
                },
                {
                    field: 'monSal',
                    title: '固定工资总额',
                    width: '15%',
                    align: 'center'
                },
                {
                    field: 'otherSal',
                    title: '额外工资总额',
                    width: '15%',
                    align: 'center',
                    templet: function (d) {
                        if (d.otherSal == null) {
                            return '无记录';
                        } else {
                            return d.otherSal;
                        }
                    }
                },
                {
                    field: 'deductSal',
                    title: '扣除工资总额',
                    width: '14.1%',
                    align: 'center',
                    templet: function (d) {
                        if (d.deductSal == null) {
                            return '无记录';
                        } else {
                            return d.deductSal;
                        }
                    }
                }
            ]],
            response: {
                statusName: 'statusCode' //数据状态的字段名称，默认：code
                , statusCode: 1 //成功的状态码，默认：0
                , msgName: 'message' //状态信息的字段名称，默认：msg
                , countName: 'total' //数据总数的字段名称，默认：count
                , dataName: 'rows' //数据列表的字段名称，默认：data
            },
            request: {
                pageName: 'page' //页码的参数名称，默认：page
                , limitName: 'rows' //每页数据量的参数名，默认：limit
            }
        });
        var table2 = layui.table;
        var tableIns2 = table2.render({
            elem: '#fixedSalLastTable',
            url: ctx + "/empMonSal/getByEmpId",
            size: 'sm',
            page: true, //开启分页
            limit:'20',
            even: true,
            height: 'full-207',
            cols: [[ //表头
                {
                    field: 'changeTime',
                    title: '年月',
                    width: '14%',
                    align: 'center'
                },
                {
                    field: 'empId',
                    title: '员工编号',
                    width: '14%',
                    align: 'center'
                },
                {
                    field: 'empName',
                    title: '姓名',
                    width: '14%',
                    align: 'center'
                },
                {
                    field: 'typeOfWage',
                    title: '固定工资',
                    width: '14%',
                    align: 'center'
                },
                {
                    field: 'otherSal',
                    title: '额外工资',
                    width: '14%',
                    align: 'center'
                },
                {
                    field: 'deductSal',
                    title: '扣除工资',
                    width: '14%',
                    align: 'center'
                },
                {
                    field: 'total',
                    title: '总额（未税）',
                    width: '15%',
                    align: 'center'
                }
            ]],
            response: {
                statusName: 'statusCode' //数据状态的字段名称，默认：code
                , statusCode: 1 //成功的状态码，默认：0
                , msgName: 'message' //状态信息的字段名称，默认：msg
                , countName: 'total' //数据总数的字段名称，默认：count
                , dataName: 'rows' //数据列表的字段名称，默认：data
            },
            request: {
                pageName: 'page' //页码的参数名称，默认：page
                , limitName: 'rows' //每页数据量的参数名，默认：limit
            }
        });
    });
}

function loadOtherSalTable() {
    layui.use('table', function(){
        var table = layui.table;
        var tableIns = table.render({
            elem: '#otherSalTable',
            url: ctx + "/empMonSal/getOtherSal",
            page: true, //开启分页
            height: 'full-107',
            size:'sm',
            where: {
                yearMonth: $('#yearMonth').val()
            },
            limit:'20',
            even:true,
            cols: [[ //表头
                {
                    field: 'yearMonth',
                    title: "年月",
                    width: '9%',
                    align: 'center'
                },
                {
                    field: 'monthAllowance',
                    title: '津贴',
                    width: '10%',
                    align: 'center'
                },
                {
                    field: 'monthSubsidy',
                    title: '补贴',
                    width: '10%',
                    align: 'center'
                },
                {
                    field: 'monthBonus',
                    title: '本月奖金',
                    width: '10%',
                    align: 'center'
                },
                {
                    field: 'overtimePay',
                    title: '加班费',
                    width: '10%',
                    align: 'center'
                },
                {
                    field: 'yearEndBonus',
                    title: '年终奖金',
                    width: '10%',
                    align: 'center'
                },
                {
                    field: 'otherSal',
                    title: '额外工资总额',
                    width: '10%',
                    align: 'center'
                },
                {
                    field: 'restSalary',
                    title: '请假/旷工扣除',
                    width: '10%',
                    align: 'center'
                },
                {
                    field: 'fineSalary',
                    title: '其他扣除',
                    width: '10%',
                    align: 'center'
                },
                {
                    field: 'deductSal',
                    title: '扣除工资总额',
                    width: '10%',
                    align: 'center'
                }
            ]],
            response: {
                statusName: 'statusCode' //数据状态的字段名称，默认：code
                ,statusCode: 1 //成功的状态码，默认：0
                ,msgName: 'message' //状态信息的字段名称，默认：msg
                ,countName: 'total' //数据总数的字段名称，默认：count
                ,dataName: 'rows' //数据列表的字段名称，默认：data
            },
            request: {
                pageName: 'page' //页码的参数名称，默认：page
                ,limitName: 'rows' //每页数据量的参数名，默认：limit
            }
        });
    });
}


