<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/common/include_js.jsp" %>
<html>
<head>
    <title>欢迎光临</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <jsp:include page="/WEB-INF/views/common/include_css.jsp"/>
    <style>
        #icon
        {
            background:url(${pageContext.request.contextPath}/static/img/backgrounds/begin.png);
            background-size:200px 40px;
            background-repeat:no-repeat;
            height: 50px;
            width: 200px;
            position: absolute;
            bottom: 0px;
            left: 72px;
        }
    </style>
</head>
<body style="background:url(${pageContext.request.contextPath}/static/img/backgrounds/bg.jpg) no-repeat center">
<div id="icon" style="display:none"> </div>
<jsp:include page="/WEB-INF/views/common/include_javascript.jsp"/>
</body>
<script>
    $(document).ready(function () {
        msgTitle();
        function msgTitle(msg) {
            setTimeout(function(){
                $("#icon").fadeIn('slow');
            },3000);
            setTimeout(function(){
                $("#icon").fadeOut('slow');
            },3700);
            setTimeout(function(){
                $("#icon").fadeIn('slow');
            },4400);
            setTimeout(function(){
                $("#icon").fadeOut('slow');
            },5100);
            setTimeout(function(){
                $("#icon").fadeIn('slow');
            },5800);
            setTimeout(function(){
                $("#icon").fadeOut('slow');
            },6500);
            setTimeout(function(){
                $("#icon").fadeIn('slow');
            },7200);
            setTimeout(function(){
                $("#icon").fadeOut('slow');
            },7900);
            setTimeout(function(){
                $("#icon").fadeIn('slow');
            },8600);
            setTimeout(function(){
                $("#icon").fadeOut('slow');
            },12000);
        }
        $(window).resize(function() {
            $("#icon").fadeOut('fast');
        });
        /*function foadout() {
            alert();
            $("#icon").fadeOut('fast');
        }*/
    });
</script>
</html>
