<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/common/include_js.jsp" %>
<html>
<head>
    <title>员工系统</title>
    <jsp:include page="/WEB-INF/views/common/emp_include_css.jsp"/>
    <link href="${ctx}/static/css/emp/emp_wage_query.css" rel="stylesheet">
</head>
<body style="overflow: hidden;background: white;">
<div style="width: 100%;height: 100%;padding: 3px;padding-left:15px;padding-right: 15px ">
    <div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief" style="height: 90%">
        <ul class="layui-tab-title" style="margin-bottom: 0px">
            <li class="layui-this" lay-id="1">月工资发放查询</li>
            <li lay-id="2">固定工资查询</li>
            <li lay-id="3">其他工资详情查询</li>
        </ul>
        <div class="layui-tab-content" style="height: 100%;padding: 0px">
            <div class="layui-tab-item layui-show" >
                <div style="margin: 10px">
                    年月：
                    <div class="layui-inline">
                        <form class="layui-form" action="">
                        <select name="yearMonth" id="yearMonth" lay-verify=""  lay-filter="yearMonth">
                        </select>
                        </form>
                    </div>
                </div>
                <table class="layui-table" id="monSalTable" lay-filter="monSalTable" lay-data="{id: 'idTest'}"></table>
                <script type="text/html" id="barpay">
                    <a class="layui-btn layui-btn-xs" lay-event="detail">查看</a>
                </script>
            </div>
            <div class="layui-tab-item">
                <div style="margin: 10px">
                    本月固定工资：
                </div>
                <table class="layui-table" id="fixedSalTable" lay-filter="fixedSalTable" lay-data="{id: 'fixed'}"></table>
                <div style="margin: 10px">
                    固定工资变化记录：
                </div>
                <table class="layui-table" id="fixedSalLastTable" lay-filter="fixedSalLastTable" lay-data="{id: 'fixedlast'}"></table>
            </div>
            <div class="layui-tab-item">
                <div style="margin: 10px">
                    其他工资变化记录：
                </div>
                <table class="layui-table" id="otherSalTable" lay-filter="otherSalTable" lay-data="{id: 'otherSal'}"></table>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/views/includejsp/emp/modal/emp_wage_query_modal.jsp"/>
<jsp:include page="/WEB-INF/views/common/emp_include_javascript.jsp"/>
<script src="${ctx}/static/js/Frame/tableExporter.js"></script>
<script type="text/javascript" src="${ctx}/static/js/empjs/emp_wage_query.js"></script>
</body>
</html>

