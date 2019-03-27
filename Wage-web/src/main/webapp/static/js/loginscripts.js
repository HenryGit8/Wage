jQuery(document).ready(function() {
    $("#checkCode").attr("src",ctx +"/getVerify?"+Math.random());
    $('.page-container form .username, .page-container form .password').keyup(function(){
        $(this).parent().find('.error').fadeOut('fast');
    });
    $("#login").click(function(){
        check();
    })
    $("#myAlert").bind('closed.bs.alert', function () {
        alert("警告消息框被关闭。");
    });
    $("#register").click(function (){
        goregis();
    });
    $("#back").click(function (){
        gologin();
    });
    var img = $('#head');
    img.removeAttr('src');
    img.attr('src', ctx + "/file/downloadFile?md5=37867cd54bfa4b82001110132a99d1e2");

    $('#register1').click(function () {
        checkregis();
    })
    $("#loginFo").animate({
        opacity:'0.9',
        height:'500px',
        width:'500px'
    }).fadeIn('slow');
    gologin();
});
$('#username1,#password1,#password2,#inputcode1').bind('keyup', function(event) {
    if (event.keyCode == "13") {
        //回车执行
        checkregis();
    }
});

$('#username,#password,#inputcode').bind('keyup', function(event) {
    if (event.keyCode == "13") {
        //回车执行
        check();
    }
});
function gologin() {
    $('#loginForm')[0].reset();
    $(".error").fadeOut('fast');
    $("#registerFo").animate({
        opacity:'0.0',
        height:'50px',
        width:'50px'
    }).fadeOut(1000);
    $("#checkCode").attr("src",ctx +"/getVerify?"+Math.random());
    setTimeout(function () {
        $("#registerFo").hide();
        $("#loginFo").show();
        $("#loginFo").animate({
            opacity:'0.9',
            height:'500px',
            width:'500px'
        });/*
        $("#loginFo").fadeIn("slow");*/
    }, 1000);
}
function goregis() {
    $('#registerForm')[0].reset();
    $(".error").fadeOut('fast');
    $("#loginFo").animate({
        opacity:'0.0',
        height:'50px',
        width:'50px'
    }).fadeOut(1000);
    /*$("#loginFo").fadeOut(1000);*/
    $("#checkCode1").attr("src",ctx +"/getVerify?"+Math.random());
    setTimeout(function () {
        $("#loginFo").hide();
        $("#registerFo").show();
        $("#registerFo").animate({
            opacity:'1.0',
            height:'500px',
            width:'500px'
        });
    }, 1000);
}
function check(){
    layer.open({
        type: 2,
        skin: 'layui-layer-demo', //样式类名
        anim: 2,
        shadeClose: false, //开启遮罩关闭
        content: '请稍等，身份信息识别中'
    });
    var myAl = document.getElementById("myAlert");
    var username = $("#username").val();
    var password = $("#password").val();
    if(username == '') {
        $(".error").fadeOut('fast', function(){
            $(".error").css('top', '27px');
        });
        $(".error").fadeIn('fast', function(){
            $(".error").parent().find('.username').focus();
        });
        layer.closeAll();
        toastr.error('用户名不能为空！');
        return false;
    }else if(/[^\d]/.test($("#username").val()) || $("#username").val().trim().length!=6){
        $(".error").fadeOut('fast', function(){
            $(".error").css('top', '27px');
        });
        $(".error").fadeIn('fast', function(){
            $(".error").parent().find('.username').focus();
        });
        layer.closeAll();
        toastr.error('请输入6位数字用户名！');
        return false;
    }
    if(password == '') {
        $(".error").fadeOut('fast', function(){
            $(".error").css('top', '96px');
        });
        $(".error").fadeIn('fast', function(){
            $(".error").parent().find('.password').focus();
        });
        layer.closeAll();
        toastr.error('密码不能为空！');
        return false;
    }else if(!/^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,21}$/.test($("#password").val())){
        $(".error").fadeOut('fast', function(){
            $(".error").css('top', '96px');
        });
        $(".error").fadeIn('fast', function(){
            $(".error").parent().find('.password').focus();
        });
        layer.closeAll();
        toastr.error('密码格式错误！');
        return false;
    }
    var inputCode = $("#inputcode").val();
    if (inputCode.length <= 0)
    {
        $(".error").fadeOut('fast', function(){
            $(".error").css('top', '163px');
        });
        $(".error").fadeIn('fast', function(){
            $(".error").parent().find('#inputcode').focus();
        });
        layer.closeAll();
        toastr.error('请输入验证码！');
        return false;
    }
    else
    {
        $(".error").fadeOut('fast', function(){
            $(".error").css('top', '163px');
        });
        $.ajax({
            type: "POST",
            /*url: ctx + "/login/getLoginMan",*/
            url:ctx + "/login/checkLoginCode",
                data: {
                    "checkCode":$('#inputcode').val()
                },
            dataType: "json",
            success: function (result) {
                if(result == 3){
                    layer.closeAll();
                    toastr.error('验证码错误！');
                }else {
                    $.ajax({
                        type: "POST",
                        url:ctx + "/login/getLoginMan",
                        data:  $("#loginForm").serialize() ,
                        dataType: "json",
                        success: function (result) {
                            if(result == 1){
                                layer.closeAll();
                                window.location.href = ctx+"/gotoMain";
                            }else if(result == 0){
                                layer.closeAll();
                                window.location.href = ctx+"/gotoEmpMain";
                            }else if(result == 2){
                                layer.closeAll();
                                toastr.error('用户名密码错误！');
                            }
                        }
                    });
                }
            }
        });
    }
}


var code;
function createCode() {
    code = "";
    var codeLength = 6; //验证码的长度
    var checkCode = document.getElementById("checkCode");
    var codeChars = new Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
        'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'); //所有候选组成验证码的字符，当然也可以用中文的
    for (var i = 0; i < codeLength; i++)
    {
        var charNum = Math.floor(Math.random() * 52);
        code += codeChars[charNum];
    }
    if (checkCode)
    {
        checkCode.className = "code";
        checkCode.innerHTML = code;
    }
}

$('.shutter').shutter({
    shutterW: 1000, // 容器宽度
    shutterH: 358, // 容器高度
    isAutoPlay: true, // 是否自动播放
    playInterval: 10000, // 自动播放时间
    curDisplay: 3, // 当前显示页
    fullPage: true // 是否全屏展示
});

$('#username').change(function () {
    $.ajax({
        type : "POST", //使用post方法访问后台
        dataType : "json", //返回json格式的数据
        url: ctx + "/login/getEmpImgMd", //要访问的后台地址
        data: {
            "empId":$('#username').val()
        },
        success : function(result) {//result为返回的数据
            var img = $('#head');
            img.removeAttr('src');
            img.attr('src', ctx + "/file/downloadFile?md5="+result);
        }
    });
})
function checkregis(){
    var myAl = document.getElementById("myAlert");
    var username = $("#username").val();
    var password1 = $("#password1").val();
    var password2 = $("#password2").val();
    if(username == '') {
        $(".error").fadeOut('fast', function(){
            $(".error").css('top', '27px');
        });
        $(".error").fadeIn('fast', function(){
            $(".error").parent().find('.username').focus();
        });
        toastr.error('用户名不能为空！');
        return false;
    }else {
        var re = false;
        if(/[^\d]/.test($("#username1").val()) || $("#username1").val().trim().length!=6){
            toastr.error('请输入6位数字用户名！');
            re =  true;
        }
        if(re){
            $(".error").fadeOut('fast', function(){
                $(".error").css('top', '27px');
            });
            $(".error").fadeIn('fast', function(){
                $(".error").parent().find('.username1').focus();
            });
            return false;
        }
    }
    if(password1 == '') {
        $(".error").fadeOut('fast', function(){
            $(".error").css('top', '96px');
        });
        $(".error").fadeIn('fast', function(){
            $(".error").parent().find('.password1').focus();
        });
        toastr.error('密码不能为空！');
        return false;
    }else if(!/^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,21}$/.test($("#password1").val())){
        $(".error").fadeOut('fast', function(){
            $(".error").css('top', '96px');
        });
        $(".error").fadeIn('fast', function(){
            $(".error").parent().find('.password1').focus();
        });
        toastr.error('密码由6-21字母和数字组成！');
        return false;
    }
    if(password2 == '') {
        $(".error").fadeOut('fast', function(){
            $(".error").css('top', '163px');
        });
        $(".error").fadeIn('fast', function(){
            $(".error").parent().find('.password2').focus();
        });
        toastr.error('确认密码不能为空！');
        return false;
    }
    var inputCode = $("#inputcode1").val();
    if (inputCode.length <= 0)
    {
        $(".error").fadeOut('fast', function(){
            $(".error").css('top', '232px');
        });
        $(".error").fadeIn('fast', function(){
            $(".error").parent().find('#inputcode1').focus();
        });
        toastr.error('请输入验证码！');
        return false;
    }
    else if(password1 != password2)
    {
        $(".error").fadeOut('fast', function(){
            $(".error").css('top', '163px');
        });
        toastr.error('两次输入的密码不一致！');
        return false;
    }else {
        $.ajax({
            type: "POST",
            url: ctx + "/login/saveRegister",
            data: $("#registerForm").serialize(),
            dataType: "json",
            success: function (result) {
                if(result == 0){
                    layer.closeAll();
                    toastr.success('注册成功，3秒后返回登录界面！');
                    setTimeout(function(){
                        gologin();
                    },3000);
                    /*msgTitle("您是管理员！");*/
                }else if(result == 1){
                    layer.closeAll();
                    toastr.success('用户名已存在！');
                }else if(result == 2){
                    layer.closeAll();
                    toastr.error('未注册员工表中无此员工信息！');
                }else if(result == 3){
                    layer.closeAll();
                    toastr.error('验证码错误！');
                }
            }
        });
    }
}