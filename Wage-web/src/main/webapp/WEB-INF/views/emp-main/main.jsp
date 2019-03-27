<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/common/include_js.jsp" %>
<html>
<head>
    <title>员工系统</title>
    <jsp:include page="/WEB-INF/views/common/emp_include_css.jsp"/>
    <link rel="icon" type="image/png" href="${ctx}/static/css/emp/assets/i/favicon.png">
    <link href="${ctx}/static/css/emp/admin.css" rel="stylesheet">
    <link href="${ctx}/static/css/emp/app.css" rel="stylesheet">
    <link href="${ctx}/static/css/emp/main.css" rel="stylesheet">
</head>
<body data-type="index" style="overflow: hidden">
<%@ include file="/WEB-INF/views/emp-main/header.jsp" %>
<div class="tpl-page-container tpl-page-header-fixed" style="height: 100%">
    <div class="tpl-left-nav tpl-left-nav-hover">
        <div class="tpl-left-nav-title">
            员工列表
        </div>
        <div class="tpl-left-nav-list">
            <ul class="tpl-left-nav-menu" id="nav">
                <li class="tpl-left-nav-item">
                    <a onclick="openjsp('empWelcome')" id="empWelcome" class="nav-link active">
                        <i class="am-icon-home"></i>
                        <span>首页</span>
                    </a>
                </li>
                <li class="tpl-left-nav-item">
                    <a onclick="openjsp('empWageQuery')" id="empWageQuery" class="nav-link tpl-left-nav-link-list">
                        <i class="am-icon-bar-chart"></i>
                        <span>工资查询</span>
                    </a>
                </li>

                <li class="tpl-left-nav-item">
                    <a href="javascript:;" onclick="openFarJsp('rest')" id="rest" class="nav-link tpl-left-nav-link-list">
                        <i class="am-icon-table"></i>
                        <span>请假</span>
                        <i class="am-icon-angle-right tpl-left-nav-more-ico am-fr am-margin-right"></i>
                    </a>
                    <ul class="tpl-left-nav-sub-menu">
                        <li>
                            <a  onclick="openChiJsp('restApply')"  id="restApply">
                                <i class="am-icon-angle-right"></i>
                                <span>请假申请</span>
                                <i class="am-icon-star tpl-left-nav-content-ico am-fr am-margin-right"></i>
                            </a>
                            <a  onclick="openChiJsp('restQuery')"  id="restQuery">
                                <i class="am-icon-angle-right"></i>
                                <span>记录查询</span>
                            </a>
                        </li>
                    </ul>
                </li>

                <li class="tpl-left-nav-item">
                    <a  href="javascript:;" onclick="openFarJsp('over')" id="over"  class="nav-link tpl-left-nav-link-list">
                        <i class="am-icon-wpforms"></i>
                        <span>加班</span>
                        <i class="am-icon-angle-right tpl-left-nav-more-ico am-fr am-margin-right tpl-left-nav-more-ico-rotate"></i>
                    </a>
                    <ul class="tpl-left-nav-sub-menu">
                        <li>
                            <a onclick="openChiJsp('overApply')" id="overApply">
                                <i class="am-icon-angle-right"></i>
                                <span>加班申请</span>
                                <i class="am-icon-star tpl-left-nav-content-ico am-fr am-margin-right"></i>
                            </a>

                            <a onclick="openChiJsp('overQuery')" id="overQuery">
                                <i class="am-icon-angle-right"></i>
                                <span>记录查询</span>
                            </a>
                        </li>
                    </ul>
                </li>

                <li class="tpl-left-nav-item">
                    <a href="javascript:;" onclick="openFarJsp('welfare')" id="welfare" class="nav-link tpl-left-nav-link-list">
                        <i class="am-icon-wpforms"></i>
                        <span>福利</span>
                        <i class="am-icon-angle-right tpl-left-nav-more-ico am-fr am-margin-right tpl-left-nav-more-ico-rotate"></i>
                    </a>
                    <ul class="tpl-left-nav-sub-menu">
                        <li>
                            <a  onclick="openChiJsp('welfareApply')"  id="welfareApply">
                                <i class="am-icon-angle-right"></i>
                                <span>福利申请</span>
                                <i class="am-icon-star tpl-left-nav-content-ico am-fr am-margin-right"></i>
                            </a>

                            <a  onclick="openChiJsp('welfareQuery')"  id="welfareQuery">
                                <i class="am-icon-angle-right"></i>
                                <span>记录查询</span>
                            </a>
                        </li>
                    </ul>
                </li>

               <%-- <li class="tpl-left-nav-item">
                    <a href="login.html" class="nav-link tpl-left-nav-link-list">
                        <i class="am-icon-key"></i>
                        <span>登录</span>

                    </a>
                </li>--%>
            </ul>
        </div>
    </div>


    <div class="tpl-content-wrapper" style="height: 100%;" >
        <iframe scrolling="auto" frameborder="0" style="height:86%;width:100%;margin-bottom: 20px" name="name" id="iframe" src="${ctx}/jump/empWelcome"></iframe>
    </div>
</div>


<%@ include file="/WEB-INF/views/emp-main/modal.jsp" %>
<jsp:include page="/WEB-INF/views/common/emp_include_javascript.jsp"/>
<!--文件上传插件-->
<script src="${pageContext.request.contextPath}/static/js-modules/lib/fileinput/file-input.min.js"></script>
<!--fileinput中文化-->
<script src="${pageContext.request.contextPath}/static/js-modules/lib/fileinput/zh.js"></script>
<script type="text/javascript" src="${ctx}/static/js/empjs/assets/iscroll.js"></script>
<script type="text/javascript" src="${ctx}/static/js/empjs/assets/app.js"></script>
<script type="text/javascript" src="${ctx}/static/js-modules/lib/wangEditor/wangEditor.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js/empjs/mainjs.js"></script>
</body>
</html>

