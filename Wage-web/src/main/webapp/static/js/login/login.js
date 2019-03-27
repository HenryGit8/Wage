requirejs(['jquery.validate.ex'], function (_) {

    var validator = $("#loginForm").validate({
        errorElement:'em',//默认的是label
        rules: {
            username: "required",
            password: "required"
        },
        messages: {//Key is the name of an element
            username: getValidationErrorHtml('请输入用户名'),//为了兼容bootstrap样式,改成这种方式
            password: getValidationErrorHtml('请输入密码')
        }
    });

    $("#submitButton").click(function () {
        if (validator.form()) {
            $.ajax({
                type: "POST",
                url: ctx + "/login/doLogin",
                data: $("#loginForm").serialize(),
                dataType: "json",
                success: function (result) {
                    ajaxReturn(result, function () {
                        window.location.href = ctx + "/login/gotoMain";
                    }, function () {
                        message('', '登录失败', {'returnJson':result,'type':'error'});
                    });
                }
            });
        } else {
            validator.focusInvalid();//focus 错误的输入框
        }
    });

});

function register() {
    window.location.href = ctx + "/login/register";
}



