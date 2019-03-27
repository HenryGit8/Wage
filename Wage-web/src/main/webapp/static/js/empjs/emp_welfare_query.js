$(function () {
    loadTab();
    loadWekfare();
});

function loadTab() {
    layui.use('element', function(){
        var element = layui.element;
        //监听Tab切换，以改变地址hash值
        element.on('tab(docDemoTabBrief)', function(){
            if(this.getAttribute('lay-id') == 2){
                loadWelfateApply();
            }
            if(this.getAttribute('lay-id') == 1){
                loadWekfare();
            }
        });
    });
}
window.onresize = function(){
    loadWekfare();
    loadWelfateApply();
}

function loadWekfare() {
    layui.use('table', function(){
        var table = layui.table;
        var tableIns = table.render({
            elem: '#welfareTable',
            url: ctx + "/welfare/getLoginEmpWel",
            page: true, //开启分页
            height: 'full-125',
            size:'sm',
            limit:'20',
            where: $("#searchForm").serializeObject(),
            even:true,
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
                    field: 'effectiveDate',
                    title: '生效日期',
                    width: '18%',
                    align: 'center'
                },
                {
                    field: 'reason',
                    title: '原因',
                    width: '27%',
                    align: 'center'
                },
                {
                    field: 'money',
                    title: '金额(￥)',
                    width: '13%',
                    align: 'center'
                },
                {
                    field: 'type',
                    title: '福利类型',
                    width: '13%',
                    templet: function(d){
                        if (d.type == 1){
                            return '津贴';
                        } else if (d.type == 2){
                            return '补贴';
                        } else if (d.type == 3){
                            return '奖金';
                        } else if (d.type == 4){
                            return '年终奖金';
                        }
                    },
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
        $('#searchBtn').click(function () {
            tableIns.reload({
                where: $("#searchForm").serializeObject()
                ,page: {
                    curr: 1 //重新从第 1 页开始
                }
            });
        });
    });
}
function loadWelfateApply() {
    layui.use('table', function(){
        var table = layui.table;
        var tableIns = table.render({
            elem: '#welfareApplyTable',
            url: ctx + "/welfare/getLoginBySearch",
            page: true, //开启分页
            height: 'full-125',
            size:'sm',
            limit:'20',
            where: $("#searchForm").serializeObject(),
            even:true,
            cols: [[ //表头
                {
                    field: 'empId',
                    title: '员工编号',
                    width: '9%',
                    align: 'center'
                },
                {
                    field: 'empName',
                    title: '姓名',
                    width: '9%',
                    align: 'center'
                },
                {
                    field: 'applyTime',
                    title: '申请日期',
                    width: '14%',
                    align: 'center'
                },
                {
                    field: 'applyReason',
                    title: '申请原因',
                    width: '17%',
                    align: 'center'
                },
                {
                    field: 'welfareTotal',
                    title: '总金额(￥)',
                    width: '9%',
                    align: 'center'
                },
                {
                    field: 'welfareType',
                    title: '福利类型',
                    width: '9%',
                    templet: function (d) {
                        if (d.welfareType == 1) {
                            return '津贴';
                        } else if (d.welfareType == 2) {
                            return '补贴';
                        } else if (d.welfareType == 3){
                            return '奖金';
                        } else if (d.welfareType == 4){
                            return '年终奖金';
                        }
                    },
                    align: 'center'
                },
                {
                    field: 'isAllow',
                    title: '是否批准',
                    width: '9%',
                    templet: function (d) {
                        if (d.isAllow == 1) {
                            return '批准';
                        } else if (d.isAllow == 2) {
                            return '未批准';
                        }else if (d.isAllow == 0) {
                            return '未审批';
                        }
                    },
                    align: 'center'
                },
                {
                    field: 'approverName',
                    title: '审批人员',
                    width: '9%',
                    align: 'center',
                    templet: function (d) {
                        if (d.approverName == null) {
                            return '未审批';
                        }else {
                            return d.approverName;
                        }
                    }
                },
                {
                    field: 'approverTime',
                    title: '审批时间',
                    width: '14%',
                    align: 'center',
                    templet: function (d) {
                        if (d.approverTime == null) {
                            return '未审批';
                        }else {
                            return d.approverTime;
                        }
                    }
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
        $('#searchApplyBtn').click(function () {
            tableIns.reload({
                where: $("#searchApplyForm").serializeObject()
                ,page: {
                    curr: 1 //重新从第 1 页开始
                }
            });
        });
    });
}