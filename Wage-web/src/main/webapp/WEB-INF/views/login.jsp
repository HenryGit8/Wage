<%--
  Created by IntelliJ IDEA.
  User: Henry
  Date: 2017/12/15
  Time: 8:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <meta charset="utf-8">
    <title>工资信息管理系统登录界面</title>
    <%@ include file="/WEB-INF/views/common/include_js.jsp" %>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- CSS -->
    <jsp:include page="/WEB-INF/views/common/include_css.jsp"/>
    <link rel='stylesheet' href='http://fonts.googleapis.com/css?family=PT+Sans:400,700'>
    <link rel="stylesheet" href="${cssRoot}/reset.css">
    <link rel="stylesheet" href="${cssRoot}/supersized.css">
    <link rel="stylesheet" href="${cssRoot}/shutter.css">
    <link rel="stylesheet" href="${cssRoot}/rain/css/normalize.css">
    <link rel="stylesheet" href="${cssRoot}/rain/css/demo.css">
    <link rel="stylesheet" href="${cssRoot}/rain/css/style1.css">
    <link rel="stylesheet" href="${cssRoot}/style.css">

    <script language="javascript" type="text/javascript">
        $(function () {
            $('.shutter').shutter({
                shutterW: 1000, // 容器宽度
                shutterH: 358, // 容器高度
                isAutoPlay: true, // 是否自动播放
                playInterval: 5000, // 自动播放时间
                curDisplay: 3, // 当前显示页
                fullPage: true // 是否全屏展示
            });
        });
    </script>
</head>

<body style="overflow: hidden">
<div class="demo-1" style="position: absolute;z-index: -999;width: 100%;height: 100%;/*left: -198px*/">
<%--    <div class="container">
        <div class="slideshow">
            <canvas width="1" height="1" id="container" style="position:absolute;left: 0px;top: 0px"></canvas>
        </div>
    </div>--%>
    <div class="shutter">
        <div class="shutter-img">\
            <a href="#" data-shutter-title="Iron Man"><img src="${ctx}/static/img/backgrounds/bg2.jpg" alt="#"></a>
            <a href="#" data-shutter-title="Iron Man"><img src="${ctx}/static/img/backgrounds/bg3.jpg" alt="#"></a>
            <a href="#" data-shutter-title="Iron Man"><img src="${ctx}/static/img/backgrounds/bg5.jpg" alt="#"></a>
            <a href="#" data-shutter-title="Iron Man"><img src="${ctx}/static/img/backgrounds/bg6.jpg" alt="#"></a>
            <a href="#" data-shutter-title="Iron Man"><img src="${ctx}/static/img/backgrounds/2.jpg" alt="#"></a>
            <a href="#" data-shutter-title="Iron Man"><img src="${ctx}/static/img/backgrounds/3.jpg" alt="#"></a>
        </div>
        <ul class="shutter-btn" style="display: none">
            <li class="prev"></li>
            <li class="next"></li>
        </ul>
    </div>
</div>
<div id="title" >
    <img alt="" src="${ctx}/static/img/loginlogo.png" id="logoImg" style="width: 100%;height: 100%;";/>
</div>
<div id="loginFo" class="page-container" style="z-index: 999; margin-right: auto;margin-left: auto;width: 50px;height:50px;margin-top: 30px;display: none">
    <div style="background-color: rgba(255,255,255,0.5);width: 100px;height: 100px;margin-left: auto;margin-right: auto;border-radius:50px;padding: 5px;box-shadow: inset 0 -1px 15px rgba(0,0,0,.075);" >
        <div style="background-color:white;width: 90px;height: 90px;border-radius:45px;overflow:hidden;">
            <img alt="" src="" id="head" style="width: 100%;height: 100%;padding: 0px";/>
        </div>
    </div>
    <%--<h1 id="denglu">登录</h1>--%>
    <form  name="loginForm" id="loginForm" action="" method="post">
        <input type="text" id="username" name="empId" class="username" placeholder="请输入您的员工编号">
        <input type="password" id="password" name="password" class="password" placeholder="请输入您的密码">
        <div class="inputdiv">
            <input type="text" id="inputcode" name="checkCode" class="inputcode" placeholder="验证码">
            <img alt="" src="" id="checkCode" onclick="getVerify(this);" class="img-responsive img-thumbnail" style="width: 108px;height: 42px;padding: 1px;margin-left: 20px"/>
        </div>
        <button id="login" class="login" type="button">登录</button>
        <button id="register" class="register" type="button">新员工注册</button>
        <div class="error"><span>+</span></div>
    </form>
</div>
<div id="registerFo" class="page-container" style="z-index: 999; margin-right: auto;margin-left: auto;margin-top: 30px;width: 50px;height:50px;display: none"  >
    <h1>新员工注册</h1>
    <form role="form" name="registerForm" id="registerForm" action="" method="post">
        <input type="text" id="username1" name="empId" class="username" placeholder="请输入您的员工编号">
        <input type="password" id="password1" name="password" class="password" placeholder="请输入您的密码">
        <input type="password" id="password2" class="password" placeholder="重复您的密码">
        <div class="inputdiv">
            <input type="text" id="inputcode1" name="checkCode" class="inputcode" placeholder="验证码">
            <img alt="" src="" id="checkCode1" onclick="getVerify(this);" class="img-responsive img-thumbnail" style="width: 108px;height: 42px;padding: 1px;margin-left: 20px"/>
        </div>
        <button id="register1" class="login" type="button">注册</button>
        <button id="back" class="register" type="button">返回</button>
        <div class="error"><span>+</span></div>
    </form>
</div>
<%--<div id="particles-js" style="position: absolute;top: 0px;left: 0px;width: 100%;height: 100%;z-index:0"></div>--%>
<jsp:include page="/WEB-INF/views/common/include_javascript.jsp"/>
<!-- Javascript -->
<script src="${jsRoot}/Frame/jquery-1.8.2.min.js"></script>
<script src="${jsModulesRoot}/lib/jquery-validation/1.13.1/jquery.validate.js"></script>
<%--<script src="${jsModulesRoot}/lib/particles/particles.js"></script>
<script src="${jsModulesRoot}/lib/particles/app.js"></script>--%>
<%--<script src="${jsRoot}/Frame/supersized.3.2.7.min.js"></script>
<script src="${jsRoot}/supersized-init.js"></script>--%>
<script src="${jsRoot}/Frame/velocity.js"></script>
<script src="${jsRoot}/Frame/shutter.js"></script>
<script src="${jsRoot}/loginscripts.js"></script>
<%--<script src="${pageContext.request.contextPath}/static/css/rain/js/index.min.js"></script>--%>

</body>
</html>
