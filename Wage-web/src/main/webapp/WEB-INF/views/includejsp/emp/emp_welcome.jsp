<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/common/include_js.jsp" %>
<html>
<head>
    <title>员工系统</title>
    <jsp:include page="/WEB-INF/views/common/emp_include_css.jsp"/>
    <link href="${ctx}/static/css/emp/admin.css" rel="stylesheet">
    <link href="${ctx}/static/css/emp/app.css" rel="stylesheet">
    <link href="${ctx}/static/css/Frame/slider/css/style.css" rel="stylesheet">
    <link href="${ctx}/static/css/emp/emp_welcome.css" rel="stylesheet">
</head>
<body style="overflow: hidden;">
<div style="width: 100%;height: 100%;padding: 3px">

    <div class="row">
        <div class="am-u-lg-3 am-u-md-6 am-u-sm-12">
            <div class="dashboard-stat blue">
                <div class="visual">
                    <i class="am-icon-comments-o"></i>
                </div>
                <div class="details">
                    <div class="number" style="font-family: 幼圆"> 工资</div>
                    <div class="desc" style="font-family: 幼圆"> 查询本月上月等工资详情</div>
                </div>
                <a class="more" href="${ctx}/jump/empWageQuery"> 进入
                    <i class="m-icon-swapright m-icon-white"></i>
                </a>
            </div>
        </div>
        <div class="am-u-lg-3 am-u-md-6 am-u-sm-12">
            <div class="dashboard-stat red">
                <div class="visual">
                    <i class="am-icon-bar-chart-o"></i>
                </div>
                <div class="details">
                    <div class="number" style="font-family: 幼圆"> 请假</div>
                    <div class="desc" style="font-family: 幼圆"> 请假申请及记录查询</div>
                </div>
                <a class="more" href="${ctx}/jump/restApply"> 进入
                    <i class="m-icon-swapright m-icon-white"></i>
                </a>
            </div>
        </div>
        <div class="am-u-lg-3 am-u-md-6 am-u-sm-12">
            <div class="dashboard-stat green">
                <div class="visual">
                    <i class="am-icon-apple"></i>
                </div>
                <div class="details">
                    <div class="number" style="font-family: 幼圆"> 加班</div>
                    <div class="desc" style="font-family: 幼圆"> 加班申请及加班记录查询</div>
                </div>
                <a class="more" href="${ctx}/jump/overApply"> 进入
                    <i class="m-icon-swapright m-icon-white"></i>
                </a>
            </div>
        </div>
        <div class="am-u-lg-3 am-u-md-6 am-u-sm-12">
            <div class="dashboard-stat purple">
                <div class="visual">
                    <i class="am-icon-android"></i>
                </div>
                <div class="details">
                    <div class="number" style="font-family: 幼圆"> 福利</div>
                    <div class="desc"> 福利申请及福利记录查询</div>
                </div>
                <a class="more" href="${ctx}/jump/welfareApply" style="font-family: 幼圆"> 进入
                    <i class="m-icon-swapright m-icon-white"></i>
                </a>
            </div>
        </div>
    </div>
    <div class="row" style="height: 80%">
        <div class="am-u-md-8 am-u-sm-12 row-mb" style="height: 100%;overflow: hidden">
            <div id="demo01" class="flexslider" style="height: 100%;width: 100%;border-radius:4px;position:relative;">
                <ul class="slides" id="newsImg" style="height: 300px">
                    <li style="height: 100%">
                        <div class="img" style="position:relative;">
                            <%--<img src="${ctx}/static/img/tabImg/tab1.jpg" style="height: auto;width: 100%" alt="" />--%>

                        </div>
                    </li>

                </ul>
            </div><!--flexslider end-->
        </div>
        <div class="am-u-md-4 am-u-sm-12 row-mb" style="height: 100%">
            <div class="tpl-content-scope">
                <div class="note note-info">
                    <h3>欢迎您，员工！
                        <span class="close" data-close="note"></span>
                    </h3>
                    <p> 您在本系统可以进行工资查询、请假申请、加班申请、福利申请等操作。</p>
                    <p><span class="label label-danger">提示:</span> 若系统故障请联系QQ:982907295。
                    </p>
                </div>
            </div>
            <div  style="height: 100%;margin-bottom: 30px;padding: 0px">

                    <div style="height: 100%;width: 100%">
                        <ol class="breadcrumb" style="margin-bottom: 0px">
                            <li><i class="glyphicon glyphicon-home"></i>
                                推荐新闻
                            </li>
                        </ol>
                        <div class="news-liebiao clearfix news-list-xiug" id="news">
                        </div>
                    </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/views/includejsp/emp/modal/emp_welcome_modal.jsp"/>
<jsp:include page="/WEB-INF/views/common/emp_include_javascript.jsp"/>
<script type="text/javascript" src="${ctx}/static/js/empjs/assets/iscroll.js"></script>
<script type="text/javascript" src="${ctx}/static/js/empjs/assets/app.js"></script>
<script type="text/javascript" src="${ctx}/static/js/Frame/slider.js"></script>
<script type="text/javascript" src="${ctx}/static/js/empjs/emp_welcome.js"></script>
</body>
</html>

