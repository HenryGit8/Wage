$(function () {
});

laydate.render({
    elem: '#restStartTime'
    , type: 'datetime'
});
laydate.render({
    elem: '#restEndTime'
    , type: 'datetime'
});

$('#submitBtn').click(function () {
    if ($("#restReason").val().length < 1) {
        toastr.error('请输入请假原因！');
        return false;
    }
    if (!checkDateTime($("#restStartTime").val())) {
        toastr.error('开始时间日期格式不正确，请检查输入！');
        return false;
    }
    if (!checkDateTime($("#restEndTime").val())) {
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
    if (ca <= 0) {
        toastr.error('开始时间应比结束时间早，请检查后重试！');
        return false;
    }
    var re = 0;
    $.ajax({
        type: "POST",
        url: ctx + "/checkDay/getWorkTimeQj",
        dataType: "json",
        data: {"startTime": $('#restStartTime').val(), "endTime": $('#restEndTime').val()},
        async: false,
        success: function (result) {
            if (result == 0) {
                toastr.error('您的请假信息中未包含工作时间，请检查后重试！');
                re = 1;
            } else if (result == -1) {
                toastr.error('开始时间应比结束时间早，请检查后重试！');
                re = 1;
            } else {
                $.ajax({
                    type: "POST",
                    url: ctx + "/checkDay/getWorkHours",
                    dataType: "json",
                    data: {"startTime": $('#restStartTime').val(), "endTime": $('#restEndTime').val()},
                    async: false,
                    success: function (result) {
                        $('#restHour').val(result);
                    },
                    error: function (result) {
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
        data: {"empId": $('#empId').val(), "startTime": $('#restStartTime').val(), "endTime": $('#restEndTime').val()},
        async: false,
        success: function (result) {
            if (result == true) {
                toastr.error('您的请假时间段重复！');
                re = 1;
            }
        },
        error: function (result) {
            re = 1;
            toastr.error('系统异常，请联系管理员！');
        }
    });
    if (re == 0) {
        $('#confirmSubmit').modal({
            relatedTarget: this,
            onConfirm: function (options) {
                $.ajax({
                    type: "POST",
                    url: ctx + "/hisRest/saveHisRest",
                    dataType: "json",
                    data: $("#restForm").serializeObject(),
                    async: false,
                    success: function (result) {
                        $('#restStartTime').val('');
                        $('#restEndTime').val('');
                        $('#restReason').val('');
                        $('#restHour').val('');
                        toastr.success('保存' + result.message);
                    },
                    error: function (result) {
                        toastr.error('保存失败，请联系管理员！');
                    }
                });
            },
            onCancel: function () {
                return false;
            }
        });
    }
})

$("#restStartTime,#restEndTime").blur(function () {
    generate();
})

$('#generate').click(function () {
    if ($("#restStartTime").val().length < 1) {
        toastr.error('请输入开始时间！');
        return false;
    }
    if ($("#restEndTime").val().length < 1) {
        toastr.error('请输入结束时间！');
        return false;
    }
    generate();
})

$('#reset').click(function () {
    $('#restStartTime').val('');
    $('#restEndTime').val('');
    $('#restReason').val('');
    $('#restHour').val('');
})

function generate() {
    if ($('#restStartTime').val().length > 1 && $('#restEndTime').val().length > 1) {
        if (!checkDateTime($("#restStartTime").val())) {
            toastr.error('开始时间日期格式不正确，请检查输入！');
            return false;
        }
        if (!checkDateTime($("#restEndTime").val())) {
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
        if (ca <= 0) {
            toastr.error('开始时间应比结束时间早，请检查后重试！');
            return false;
        }
        $.ajax({
            type: "POST",
            url: ctx + "/checkDay/getWorkTimeQj",
            dataType: "json",
            data: {"startTime": $('#restStartTime').val(), "endTime": $('#restEndTime').val()},
            async: false,
            success: function (result) {
                if (result == 0) {
                    toastr.error('您的请假信息中未包含工作时间！');
                    re = 1;
                } else if (result == -1) {
                    toastr.error('开始时间应比结束时间早！');
                    re = 1;
                } else {
                    $.ajax({
                        type: "POST",
                        url: ctx + "/checkDay/getWorkHours",
                        dataType: "json",
                        data: {"startTime": $('#restStartTime').val(), "endTime": $('#restEndTime').val()},
                        success: function (result) {
                            $('#restHour').val(result);
                        },
                        error: function (result) {
                        }
                    });
                }
            }
        })
    }
}