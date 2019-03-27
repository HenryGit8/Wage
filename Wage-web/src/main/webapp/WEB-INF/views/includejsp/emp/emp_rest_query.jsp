<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/common/include_js.jsp" %>
<html>
<head>
    <title>员工系统</title>
    <jsp:include page="/WEB-INF/views/common/emp_include_css.jsp"/>
    <link href="${ctx}/static/css/Frame/fullcalendar.css" rel="stylesheet" />
    <link href="${ctx}/static/css/emp/emp_rest_query.css" rel="stylesheet">
</head>
<body style="overflow: hidden;background: white;">
<div style="width: 100%;height: 100%;padding: 3px;padding-left:15px;padding-right: 15px ">
    <div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief" style="height: 90%">
        <ul class="layui-tab-title" style="margin-bottom: 0px">
            <li class="layui-this" lay-id="1">表格</li>
            <li lay-id="2">图表</li>
        </ul>
        <div class="layui-tab-content" style="height: 100%;padding: 0px">
            <div class="layui-tab-item layui-show">
                <form id="searchForm" class="layui-form" action="">
                    <div style="margin: 10px">
                        请假开始时间：
                        <div class="layui-inline">
                            <input type="text" id="restStartTimeMin" name="restStartTimeMin" placeholder="最小值"
                                   autocomplete="off" class="layui-input">
                        </div>
                        —
                        <div class="layui-inline">
                            <input type="text" id="restStartTimeMax" name="restStartTimeMax" placeholder="最大值"
                                   autocomplete="off" class="layui-input">
                        </div>
                        <button id="searchBtn" type="button" class="layui-btn" lay-submit lay-filter="formDemo"
                                style="margin-left: 8px">搜索
                        </button>
                    </div>
                </form>
                <table class="layui-table" id="restTable" lay-filter="restTable" lay-data="{id: 'idTest'}"></table>
                <script type="text/html" id="barpay">
                    {{#  if(d.isNotMonth == 1){ }}
                        {{#  if(d.isAllow == 1){ }}
                        <a class="layui-btn layui-btn-xs" lay-event="detail">申请撤回</a>
                        {{#  } }}
                        {{#  if(d.isAllow == 0){ }}
                        <p >申请撤回中</p>
                        {{#  } }}
                        {{#  if(d.isAllow == 2){ }}
                        <p >申请被驳回</p>
                        <%--<a class="layui-btn layui-btn-xs" lay-event="detail">重新撤回</a>--%>
                        {{#  } }}
                    {{#  } }}
                    {{#  if(d.isNotMonth != 1){ }}
                         无法撤回
                    {{#  } }}
                </script>
            </div>
            <div class="layui-tab-item">
                <div id='calendar' style="margin: 20px 10px 10px 10px;height: 514px"></div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/views/includejsp/emp/modal/emp_rest_query_modal.jsp"/>
<jsp:include page="/WEB-INF/views/common/emp_include_javascript.jsp"/>
<script src="${ctx}/static/js-modules/lib/fullcalendar.min.js"></script>
<script src="${ctx}/static/js-modules/lib/local/zh-cn.js"></script>
<script type="text/javascript" src="${ctx}/static/js/empjs/emp_rest_query.js"></script>
</body>
</html>

