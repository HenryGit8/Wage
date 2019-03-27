<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/common/include_js.jsp" %>
<html>
<head>
    <title>后台管理系统</title>
    <jsp:include page="/WEB-INF/views/common/include_css.jsp"/>
    <!--Sidebar-->
    <link href="${ctx}/static/css/sidebar.css" rel="stylesheet">
    <!--Relate to this page-->
    <link href="${ctx}/static/css/index.css" rel="stylesheet">
    <%--<link href="${ctx}/static/css/Frame/bootstrap-wysiwyg/bootstrap-combined.no-icons.min.css" rel="stylesheet">
    <link href="${ctx}/static/css/Frame/bootstrap-wysiwyg/bootstrap-responsive.min.css" rel="stylesheet">
    <link href="http://netdna.bootstrapcdn.com/font-awesome/3.0.2/css/font-awesome.css" rel="stylesheet">
    <link href="${ctx}/static/css/Frame/bootstrap-wysiwyg/wysiwyg-index.css" rel="stylesheet">--%>
    <%--<script type="text/javascript" src="${ctx}/static/js-modules/lib/bootstrap-wysiwyg/bootstrap-wysiwyg.js"></script>
    <script type="text/javascript" src="${ctx}/static/js-modules/lib/bootstrap-wysiwyg/external/jquery.hotkeys.js"></script>--%>
</head>
<body>
<div class="easyui-layout" id="cc" data-options="fit:true">
    <%@ include file="/WEB-INF/views/main/header.jsp" %>
    <div id="wrapper" style="height: 100%">
        <!-- Sidebar -->
        <nav class="navbar navbar-inverse navbar-fixed-top" id="sidebar-wrapper" role="navigation" style="margin-top: 60px">
            <div class="sidebar" id="sidebar" data-color="black" data-image="${ctx}/static/img/sidebar-4.jpg">
                <div class="sidebar-wrapper">
                    <div style="width: 100%;height: 1px;background: white;overflow: hidden;opacity: 0.3"></div>
                    <div class="logo">
                        <a href="javascript:void(0);" onclick="goWelcome()" class="simple-text" >
                            ADMIN
                        </a>
                    </div>
                    <ul class="nav" id="nav" style="position: relative;">
                        <c:forEach var="item" items="${empAuthority}" varStatus="s">
                            <li id="${item.address}">
                                <a href="javascript:void(0);" onclick="openjsp('${item.address}')">
                                    <i class="pe-7s-graph"></i>
                                    <p>${item.name}</p>
                                </a>
                            </li>
                        </c:forEach>
                        <p class="bottomtext" style="bottom: -550px;margin: 27px">登录用户:${username}</p>
                        <%--<p class="bottomtext" style="bottom: -550px;margin: 20px" id="nowTime">现在时间:</p>--%>
                    </ul>
                </div>
            </div>
        </nav>
        <!-- /#sidebar-wrapper -->

        <!-- Page Content -->
        <div id="page-content-wrapper" style="height: 100%;width: auto">
            <button id="anniu" type="button" class="hamburger is-closed animated fadeInLeft" data-toggle="offcanvas" style="margin-top: 670px;opacity:0.5;width: 30px;height: 30px">
                <span class="hamb-top"></span>
                <span class="hamb-middle"></span>
                <span class="hamb-bottom"></span>
            </button>
            <iframe scrolling="auto" frameborder="0" style="height:100%;width:100%" name="name " id="iframe" src="${ctx}/jump/welcome"></iframe>
        </div>
        <!-- /#page-content-wrapper -->

    </div>

</div>
<jsp:include page="/WEB-INF/views/main/modal.jsp"/>
<jsp:include page="/WEB-INF/views/main/editor_modal.jsp"/>
<jsp:include page="/WEB-INF/views/common/include_javascript.jsp"/>
<script type="text/javascript" src="${ctx}/static/js/openstyle/open-style.js"></script>
<script type="text/javascript" src="${ctx}/static/js-modules/lib/show-password/bootstrap-show-password.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js-modules/lib/wangEditor/wangEditor.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js/main/mainjs.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        var trigger = $('.hamburger'),
            overlay = $('.overlay'),
            isClosed = false;
        /*$('#wrapper').toggleClass('toggled');*/
        /*hamburger_cross();*/
        trigger.click(function () {
            hamburger_cross();
        });
        function hamburger_cross() {
            if (isClosed == true) {
                overlay.hide();
                trigger.removeClass('is-open');
                trigger.addClass('is-closed');
                isClosed = false;
            } else {
                overlay.show();
                trigger.removeClass('is-closed');
                trigger.addClass('is-open');
                isClosed = true;
            }
        }
        $('[data-toggle="offcanvas"]').click(function () {
            $('#wrapper').toggleClass('toggled');
        });
    });
</script>
<%--
<script id="portraitUpload" type="text/html">
    <div style="padding: 10px 20px">
        <form role="form" enctype="multipart/form-data" method="post">
            <div class="embed-responsive embed-responsive-16by9">
                <div class="embed-responsive-item pre-scrollable">
                    <img alt="" src="../img/showings.jpg" id="cut-img"
                         class="img-responsive img-thumbnail"/>
                </div>
            </div>
            <div class="white-divider-md"></div>
            <input type="file" name="imgFile" id="fileUpload"/>
            <div class="white-divider-md"></div>
            <div id="alert" class="alert alert-danger hidden" role="alert"></div>
            <input type="hidden" id="x" name="x"/>
            <input type="hidden" id="y" name="y"/>
            <input type="hidden" id="w" name="w"/>
            <input type="hidden" id="h" name="h"/>
        </form>
    </div>
</script>--%>
</body>
</html>

