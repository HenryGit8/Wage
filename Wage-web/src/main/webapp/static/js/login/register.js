requirejs(['jquery.validate.ex'], function (_) {

    //完成注册页面
    $('#addRegister').click(function () {
        var validateResult = $("#registerForm").validate({
            errorElement: 'em',//默认的是label
            rules: {
                username: {
                    required: true,
                    remote: {                                          //验证用户名是否存在
                        type: "POST",
                        url: ctx + "/login/remoteUsername",             //servlet
                        cache: false,
                        async: false,
                        data: {
                            username: function () {
                                return $("#username").val();
                            }
                        }
                    }
                },
                password: "required",
                password2: {
                    required: true,
                    equalTo: "#password"
                },
                studentName: "required"
            },
            messages: {
                username: {
                    //required: "<font color='red'>请输入用户名</font>",
                    required: getValidationErrorHtml('请输入用户名'),//为了兼容bootstrap样式,改成这种方式
                    remote: getValidationErrorHtml('用户名已存在')
                },
                password: getValidationErrorHtml('请输入密码'),
                password2: {
                    required: getValidationErrorHtml('请再次输入新密码'),
                    equalTo: getValidationErrorHtml('两次密码输入不一致')
                },
                studentName: getValidationErrorHtml('请输入学生姓名')
            }
        }).form();
        if (validateResult) {
            $.ajax({
                type: "POST",
                url: ctx + "/login/addRegister",
                data: $("#registerForm").serialize(),
                dataType: "json",
                success: function (result) {
                    ajaxReturn(result, function () {
                        message('', '注册成功，即将跳转到登录页', {
                            'type': 'success', 'timeOut': 2000,
                            'onClose': function () {
                                window.location.href = ctx+"/";
                            }
                        });
                    }, function () {
                        message('', '注册失败', {'returnJson': result, 'type': 'error'});
                    });
                }
            });
        }
    });
});



