<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/common/include_js.jsp" %>
<html>
<head>
    <title>月工资变化历史记录</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <jsp:include page="/WEB-INF/views/common/include_css.jsp"/>
    <link rel="stylesheet" href="${ctx}/static/css/admin/salarychance_history.css">
</head>
<body >
<div class="main-panel" style="width: 100%">
    <div class="content">
        <div class="content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-8" id="cardparent">
                        <div class="card" id="chart3" style="width: 28.8%;height: 245px">
                            <table id="datagrid1">
                            </table>
                            <div id="tb" style="padding:5px">
                                <div>
                                    姓名/编号：<input id="Eq" type="text" name="Eq" style="width:93px;height: 26px"
                                                 class="easyui-textbox"/>
                                    <a id="searchBtn" href="javascript:void(0);" class="easyui-linkbutton"
                                       iconCls="icon-search" onclick="">查询</a>
                                    <a id="generateBtn" href="javascript:void(0);" class="easyui-linkbutton"
                                       iconCls="icon-tip" onclick="" style="width: 110px">生成本月数据</a>
                                </div>
                            </div>
                        </div>
                        <div class="card" id="chart4" style="width: 68.8%;height: 245px">
                            <table id="datagrid2"></table>
                        </div>
                        <div class="card" id="chart1" style="width: 48.8%;margin-bottom: 0px;height: 338px">
                            <div id="container"></div>
                        </div>
                        <div class="card" id="chart2" style="width: 48.8%;margin-bottom: 0px;height: 338px">
                            <div id="container1"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/views/common/include_javascript.jsp"/>
<script src="${ctx}/static/js-modules/lib/art-template/3.0.0/template.js"></script>
<script src="${ctx}/static/js-modules/lib/highcharts.js"></script>
<script src="${ctx}/static/js/managerjs/salarychance_history.js"></script>
</body>

</html>
