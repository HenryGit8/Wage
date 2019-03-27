<%@ page import="com.wage.model.EmpDicBasicInfo" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/common/include_js.jsp" %>
<html>
<head>
    <title>员工系统</title>
    <jsp:include page="/WEB-INF/views/common/emp_include_css.jsp"/>
    <link href="${ctx}/static/css/emp/emp_rest_apply.css" rel="stylesheet">
</head>
<body style="overflow: hidden;background: rgb(233, 236, 243)">
<div style="width: 100%;height: 100%;padding: 20px">
    <form id="restForm" class="layui-form" action="">
        <h1 style="margin: 20px auto 50px auto;text-align: center;">
            员工请假单
        </h1>
        <div class="layui-form-item">
            <label class="layui-form-label">员工编号</label>
            <div class="layui-input-inline" style="width: 28%;margin-left: 10px">
                <input type="text" id="empId" name="empId" value="${emp.empId}" placeholder="请输入" class="layui-input"  readonly="readonly">
            </div>

            <label class="layui-form-label" style="width: 80px;padding-left: 0px">员工姓名</label>
            <div class="layui-input-inline" style="width: 29%;margin-left: 10px">
                <input type="text" id="empName" name="empName" value="${emp.empName }" placeholder="请输入" class="layui-input"  readonly="readonly">
            </div>
        </div>
       <%-- <div class="layui-form-item">
        </div>--%>
        <div class="layui-form-item">
            <label class="layui-form-label">开始时间</label>
            <div class="layui-input-block">
                <input type="text" id="restStartTime" name="restStartTime" placeholder="请选择时间" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">结束时间</label>
            <div class="layui-input-block">
                <input type="text" id="restEndTime" name="restEndTime" placeholder="请选择时间" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">请假理由</label>
            <div class="layui-input-block">
                <textarea id="restReason" name="restReason" placeholder="请输入内容" class="layui-textarea"></textarea>
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">有效时长</label>
            <div class="layui-input-block">
                <input id="restHour" name="restHour" placeholder="自动识别" class="layui-input"  readonly="readonly" style="width: 78%;float: left">
                <%--<button id="generate" class="layui-btn" style="float: left;margin-left: 15px">生成</button>--%>
                <button id="generate" class="layui-btn layui-btn-primary" type="button" style="float: left;margin-left: 15px" lay-submit  lay-filter="formDemo">
                    <i class="layui-icon">&#x1002;</i>
                </button>
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
<jsp:include page="/WEB-INF/views/includejsp/emp/modal/emp_rest_apply_modal.jsp"/>
<jsp:include page="/WEB-INF/views/common/emp_include_javascript.jsp"/>
<script type="text/javascript" src="${ctx}/static/js/empjs/emp_rest_apply.js"></script>
</body>
</html>

