$(function () {
    loadOverTable();
    loadTab();
});

function loadTab() {
    layui.use('element', function(){
        var element = layui.element;
        //监听Tab切换，以改变地址hash值
        element.on('tab(docDemoTabBrief)', function(){
            if(this.getAttribute('lay-id') == 2){
                loadFullCalendar();
            }
            if(this.getAttribute('lay-id') == 1){
                loadOverTable();
            }
        });
    });
}

window.onresize = function(){
    loadOverTable();
    loadFullCalendar();
}

function loadOverTable() {
    layui.use('table', function(){
        var table = layui.table;
        var tableIns = table.render({
            elem: '#overTable',
            url: ctx + "/hisOver/getByEmpid",
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
                    width: '12%',
                    align: 'center'
                },
                {
                    field: 'empName',
                    title: '姓名',
                    width: '12%',
                    align: 'center'
                },
                {
                    field: 'overTimeStart',
                    title: '加班开始时间',
                    width: '13%',
                    align: 'center'
                },
                {
                    field: 'overTimeEnd',
                    title: '加班结束时间',
                    width: '13%',
                    align: 'center'
                },
                {
                    field: 'overHour',
                    title: '有效时长(小时)',
                    width: '12%',
                    align: 'center'
                },
                {
                    field: 'overType',
                    title: '加班类型',
                    width: '12%',
                    align: 'center',
                    templet: function(d){
                        if (d.overType == 1){
                            return '工作日加班';
                        } else if (d.overType == 2){
                            return '双休日加班';
                        } else if (d.overType == 3){
                            return '节假日加班';
                        }
                    }
                },
                {
                    field: 'overReason',
                    title: '加班原因',
                    width: '12%',
                    align: 'center'
                },
                {
                    field: 'isCheck',
                    title: '状态',
                    width: '12.5%',
                    align: 'center',
                    templet: function(d){
                        if (d.isCheck == 0){
                            return '未审核';
                        } else if (d.isCheck == 1){
                            return '通过';
                        } else if (d.overType == 2){
                            return '不通过';
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
        $('#searchBtn').click(function () {
            tableIns.reload({
                where: $("#searchForm").serializeObject()
                ,page: {
                    curr: 1 //重新从第 1 页开始
                }
            });
        });

        table.on('tool(restTable)', function(obj){
            var row = obj.data;
            if(obj.event === 'detail'){
                var restStartTime = row.restStartTime;
                layer.confirm('您确定要提交撤回申请吗？', {icon: 3, title:'提示'}, function(index){
                    $.ajax({
                        type : "POST", //使用post方法访问后台
                        dataType : "json", //返回json格式的数据
                        url: ctx + "/hisRest/saveRestApply",//要访问的后台地址
                        data: {
                            "restTime":restStartTime
                        },
                        success : function(result) {//result为返回的数据
                            toastr.success('提交撤回申请成功！');
                            tableIns.reload({
                                where: $("#searchForm").serializeObject()
                            });
                        },
                        error:function (result) {
                            toastr.error('提交失败，系统异常！');
                        }
                    });
                    layer.close(index);
                });
            }
        });
    });
}
laydate.render({
    elem: '#overTimeStartMin'
    ,type: 'datetime'
});
laydate.render({
    elem: '#overTimeStartMax'
    ,type: 'datetime'
});


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
    height:'parent',
    dayClick: function(date, allDay, jsEvent, view) {
    },
    eventClick: function(event, jsEvent, view) {
    }
});

function loadFullCalendar() {
    var restList;
    $.ajax({
        type: "GET",
        url: ctx + "/hisOver/getOverByEmpid",
        dataType: "json",
        success: function (result) {
            restList = result["restList"];
            $('#calendar').fullCalendar('removeEvents');
            $('#calendar').fullCalendar( 'addEventSource', restList);
            $('#calendar').fullCalendar( 'refetchEvents' );
        }
    });
}