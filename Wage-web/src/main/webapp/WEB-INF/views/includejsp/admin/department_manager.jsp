<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/common/include_js.jsp" %>
<html>
<head>
    <title>部门管理</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <jsp:include page="/WEB-INF/views/common/include_css.jsp"/>
</head>
<body>
<div class="main-panel" style="width: 100%">
    <div class="content">
        <select id="type1" class="easyui-combobox" name="type" style="width: 30%">
            <option value="0">节假日</option>
            <option value="1">额外工作日</option>
        </select>
    </div>
</div>
<jsp:include page="/WEB-INF/views/common/include_javascript.jsp"/>
</body>

</html>
