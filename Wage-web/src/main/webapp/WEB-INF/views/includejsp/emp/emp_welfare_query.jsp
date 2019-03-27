<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/common/include_js.jsp" %>
<html>
<head>
    <title>员工系统</title>
    <jsp:include page="/WEB-INF/views/common/emp_include_css.jsp"/>
    <link href="${ctx}/static/css/emp/emp_welfare_query.css" rel="stylesheet">
</head>
<body style="overflow: hidden;background: white;">
<div style="width: 100%;height: 100%;padding: 3px;padding-left:15px;padding-right: 15px ">
    <div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief" style="height: 90%">
        <ul class="layui-tab-title" style="margin-bottom: 0px">
            <li class="layui-this" lay-id="1">已生效福利</li>
            <li lay-id="2">申请福利记录</li>
        </ul>
        <div class="layui-tab-content" style="height: 100%;padding: 0px">
            <div class="layui-tab-item layui-show" >
                <form id="searchForm" class="layui-form" action="">
                    <div style="margin: 10px">
                        金额：
                        <div class="layui-inline">
                            <input type="number" id="moneyMin" name="moneyMin" placeholder="最小值"
                                   autocomplete="off" class="layui-input">
                        </div>
                        —
                        <div class="layui-inline">
                            <input type="number" id="moneyMax" name="moneyMax" placeholder="最大值"
                                   autocomplete="off" class="layui-input">
                        </div>
                        类型：
                        <div class="layui-inline">
                            <select id="type" name="type" lay-filter="overType" placeholder="全部">
                                <option value="">全部</option>
                                <option value="1">津贴</option>
                                <option value="2">补贴</option>
                                <option value="3">奖金</option>
                                <option value="4">年终奖金</option>
                            </select>
                        </div>
                        <button id="searchBtn" type="button" class="layui-btn" lay-submit lay-filter="formDemo"
                                style="margin-left: 8px">搜索
                        </button>
                    </div>
                </form>
                <table class="layui-table" id="welfareTable" lay-filter="welfareTable" lay-data="{id: 'welfareTable'}"></table>
            </div>
            <div class="layui-tab-item">
                <form id="searchApplyForm" class="layui-form" action="">
                    <div style="margin: 10px">
                        金额：
                        <div class="layui-inline">
                            <input type="number" id="welfareTotalMin" name="welfareTotalMin" placeholder="最小值"
                                   autocomplete="off" class="layui-input">
                        </div>
                        —
                        <div class="layui-inline">
                            <input type="number" id="welfareTotalMax" name="welfareTotalMax" placeholder="最大值"
                                   autocomplete="off" class="layui-input">
                        </div>
                        类型：
                        <div class="layui-inline">
                            <select id="welfareType1" name="welfareType" lay-filter="overType" placeholder="全部">
                                <option value="">全部</option>
                                <option value="1">津贴</option>
                                <option value="2">补贴</option>
                            </select>
                        </div>
                        <button id="searchApplyBtn" type="button" class="layui-btn" lay-submit lay-filter="formDemo"
                                style="margin-left: 8px">搜索
                        </button>
                    </div>
                </form>
                <table class="layui-table" id="welfareApplyTable" lay-filter="welfareApplyTable" lay-data="{id: 'welfareApplyTable'}"></table>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/views/includejsp/emp/modal/emp_wage_query_modal.jsp"/>
<jsp:include page="/WEB-INF/views/common/emp_include_javascript.jsp"/>
<script type="text/javascript" src="${ctx}/static/js/empjs/emp_welfare_query.js"></script>
</body>
</html>

