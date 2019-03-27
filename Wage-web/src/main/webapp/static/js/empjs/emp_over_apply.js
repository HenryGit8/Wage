$(function () {
});
laydate.render({
    elem: '#overTimeStart'
    , type: 'datetime'
});
laydate.render({
    elem: '#overTimeEnd'
    , type: 'datetime'
});

$('#submitBtn').click(function () {
    if ($("#overReason").val().length < 1) {
        toastr.error('请输入加班原因！');
        return false;
    }
    if (!checkDateTime($("#overTimeStart").val())) {
        toastr.error('开始时间日期格式不正确，请检查输入！');
        return false;
    }
    if (!checkDateTime($("#overTimeEnd").val())) {
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
    if (ca <= 0) {
        toastr.error('开始时间应比结束时间早，请检查后重试！');
        return false;
    }
    $("#overHour").val(toDecimal(ca));
    var re = 0;
    $.ajax({
        type: "POST",
        url: ctx + "/checkDay/getWorkTimeQj",
        dataType: "json",
        data: {"startTime": $('#overTimeStart').val(), "endTime": $('#overTimeEnd').val()},
        async: false,
        success: function (result) {
            if (result == 1) {
                toastr.error('您的加班信息中包含工作时间，请检查后重试！');
                re = 1;
            } else if (result == 2) {
                toastr.error('开始时间应比结束时间早，请检查后重试！');
                re = 1;
            }
        }
    });
    $.ajax({
        type: "POST",
        url: ctx + "/hisOver/checkIsOverLap",
        dataType: "json",
        data: {"empId": $('#empId').val(), "startTime": $('#overTimeStart').val(), "endTime": $('#overTimeEnd').val()},
        async: false,
        success: function (result) {
            if (result == true) {
                toastr.error('您的加班时间段重复！');
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
                    url: ctx + "/hisOver/saveHisOver",
                    dataType: "json",
                    data: $("#overForm").serializeObject(),
                    async: false,
                    success: function (result) {
                        $('#overTimeStart').val('');
                        $('#overTimeEnd').val('');
                        $('#overReason').val('');
                        $('#overHour').val('');
                        $('#overType').val('');
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


function generate() {
    if ($('#overTimeStart').val().length > 1 && $('#overTimeEnd').val().length > 1) {
        if (!checkDateTime($("#overTimeStart").val())) {
            toastr.error('开始时间日期格式不正确，请检查输入！');
            return false;
        }
        if (!checkDateTime($("#overTimeEnd").val())) {
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
        if (ca <= 0) {
            toastr.error('开始时间应比结束时间早，请检查后重试！');
            return false;
        }
        $.ajax({
            type: "POST",
            url: ctx + "/checkDay/getWorkTimeQj",
            dataType: "json",
            data: {"startTime": $('#overTimeStart').val(), "endTime": $('#overTimeEnd').val()},
            success: function (result) {
                if (result == 1) {
                    toastr.error('您的加班信息中包含工作时间，请检查后重试！');
                } else {
                    $.ajax({
                        type: "POST",
                        url: ctx + "/hisOver/checkIsOverLap",
                        dataType: "json",
                        data: {
                            "empId": $('#empId').val(),
                            "startTime": $('#overTimeStart').val(),
                            "endTime": $('#overTimeEnd').val()
                        },
                        success: function (result) {
                            if (result == true) {
                                toastr.error('您的加班时间段重复！');
                            } else {
                                $("#overHour").val(toDecimal(ca));
                            }
                        },
                        error: function (result) {
                            toastr.error('系统异常，请联系管理员！');
                        }
                    });
                }
            }
        });
    }
}

$('#reset').click(function () {
    $('#overTimeStart').val('');
    $('#overTimeEnd').val('');
    $('#overReason').val('');
    $('#overHour').val('');
    $('#overType').val('');
})

$("#overTimeStart,#overTimeEnd").blur(function () {
    generate();
})

$('#generate').click(function () {
    if ($("#overTimeStart").val().length < 1) {
        toastr.error('请输入开始时间！');
        return false;
    }
    if ($("#overTimeEnd").val().length < 1) {
        toastr.error('请输入结束时间！');
        return false;
    }
    generate();
})