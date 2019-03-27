$(function () {
});
$('#submitBtn').click(function () {
    if ($("#welfareType").val().length < 1) {
        toastr.error('请选择福利类型！');
        return false;
    }
    if ($("#welfareTotal").val().length < 1) {
        toastr.error('请输入总额！');
        return false;
    }
    if ($("#applyReason").val().length < 1) {
        toastr.error('请输入申请原因！');
        return false;
    }
    $('#confirmSubmit').modal({
        relatedTarget: this,
        onConfirm: function (options) {
            $.ajax({
                type: "POST",
                url: ctx + "/welfare/saveWelfare",
                dataType: "json",
                data: $("#welfareForm").serializeObject(),
                async: false,
                success: function (result) {
                    $('#welfareType').val('');
                    $('#welfareTotal').val('');
                    $('#applyReason').val('');
                    toastr.success('申请提交' + result.message);
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
})