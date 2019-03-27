<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/common/include_js.jsp" %>
<html>
<head>
    <title>员工系统</title>
    <jsp:include page="/WEB-INF/views/common/emp_include_css.jsp"/>
    <link href="${ctx}/static/css/emp/emp_welfare_apply.css" rel="stylesheet">
</head>
<body style="overflow: hidden;background: rgb(233, 236, 243)">
<div style="width: 100%;height: 100%;padding: 20px">
    <form id="welfareForm" class="layui-form" action="">
        <h1 style="margin: 20px auto 50px auto;text-align: center;">
            员工福利申请单
        </h1>
        <div class="layui-form-item">
            <label class="layui-form-label">员工编号</label>
            <div class="layui-input-inline" style="width: 28%;margin-left: 10px">
                <input type="text" id="empId" value="${emp.empId}" placeholder="请输入" class="layui-input"  readonly="readonly">
            </div>

            <label class="layui-form-label" style="width: 80px;padding-left: 0px">员工姓名</label>
            <div class="layui-input-inline" style="width: 29%;margin-left: 10px">
                <input type="text" id="empName" value="${emp.empName }" placeholder="请输入" class="layui-input"  readonly="readonly">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">福利类型</label>
            <div class="layui-input-inline" style="width: 28%;margin-left: 10px">
                <select id="welfareType" name="welfareType" lay-filter="overType" placeholder="请选择">
                    <option value="">请选择</option>
                    <option value="1">津贴</option>
                    <option value="2">补贴</option>
                </select>
            </div>

            <label class="layui-form-label" style="width: 80px;padding-left: 0px">总额</label>
            <div class="layui-input-inline" style="width: 29%;margin-left: 10px">
                <input type="number" id="welfareTotal" name="welfareTotal" placeholder="请输入" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">申请原因</label>
            <div class="layui-input-block">
                <textarea id="applyReason" name="applyReason" placeholder="请输入内容" class="layui-textarea" style="width: 96%"></textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button id="submitBtn" type="button" class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
                <button id="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>
<jsp:include page="/WEB-INF/views/includejsp/emp/modal/emp_welfare_apply_modal.jsp"/>
<jsp:include page="/WEB-INF/views/common/emp_include_javascript.jsp"/>
<script type="text/javascript" src="${ctx}/static/js/empjs/emp_welfare_apply.js"></script>
</body>
</html>

