<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/common/include_js.jsp" %>
<html>
<head>
    <title>加班记录</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <jsp:include page="/WEB-INF/views/common/include_css.jsp"/>
    <link href="${ctx}/static/css/Frame/fullcalendar.css" rel="stylesheet" />
    <link href="${ctx}/static/css/admin/overwork_history.css" rel="stylesheet" />
</head>
<body class="easyui-layout" id="layout" >
<div id="northPanel" data-options="region:'north',title:'加班记录查询',split:true,
    hideCollapsedContent:false" style="height:50%;background:#f7f7f7" >
    <div class="content" style="height: 99%">
        <div class="container-fluid" style="height: 100%">
            <div class="row" style="height: 100%">
                <div class="col-md-8" id="cardparent1">
                    <div class="card" id="chart3">
                        <table id="datagrid1">
                        </table>
                        <div id="tb" style="padding:5px">
                            <div>
                                姓名/编号：<input id="Eq" type="text" name="Eq" class="easyui-textbox" />
                                <a id="searchBtn" href="javascript:void(0);" class="easyui-linkbutton"
                                   iconCls="icon-search" onclick="">查询</a>
                            </div>
                        </div>
                    </div>
                    <div class="card" id="chart4">
                        <table id="datagrid2"></table>
                        <div id="tb1">
                            <form id="searchOver" method="post">
                                <input  type="hidden" id="empId2" name = "empId">
                                <div style="width: 452px;float: left;margin-left: 5px" id="select">
                                    <p id="label1">加班开始时间：</p>
                                    <input id="overTimeStartMin" type="text" name="overTimeStartMin" class="form-control"  placeholder="请选择" />
                                    <p id="label2">至</p>
                                    <input id="overTimeStartMax" type="text" name="overTimeStartMax"  class="form-control"  placeholder="请选择" />
                                    <div class="btn-separator"></div>
                                </div>
                                <div id="north">
                                    <a id="applyHisBtn" title="查看审批记录" href="javascript:void(0);" class="easyui-linkbutton"
                                       iconCls="icon-large-smartart" onclick="">记录</a>
                                    <a id="applyBtn" title="审批用户提交的加班信息" href="javascript:void(0);" class="easyui-linkbutton"
                                       iconCls="icon-redo" onclick="">审批</a>
                                    <a id="addBtn" title="新增一条加班信息" href="javascript:void(0);" class="easyui-linkbutton"
                                       iconCls="icon-add" onclick="">新增</a>
                                    <a id="clearBtn" title="清空查询条件" href="javascript:void(0);" class="easyui-linkbutton"
                                       iconCls="icon-no" onclick="">清空</a>
                                    <a id="searchBtn1" title="根据加班时间区间查询" href="javascript:void(0);" class="easyui-linkbutton"
                                       iconCls="icon-search" onclick="">查询</a>
                                    <%--<a id="calendarBtn" href="javascript:void(0);" class="easyui-linkbutton"
                                       iconCls="icon-large-shapes" onclick="" style="margin-left: 2px">图表</a>--%>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div data-options="region:'center',title:'center title',noheader:true" style="background:#f7f7f7">
    <div class="content" style="height: 100%">
        <div class="container-fluid" style="height: 100%">
            <div class="row" style="height: 100%">
                <div class="col-md-8" id="cardparent" style="padding: 28px;height: 100%;width: 100%">
                    <div class="card" style="height: 585px;padding: 30px">
                        <div id='calendar' style="width:100%;"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/views/includejsp/admin/modal/overwork_modal.jsp"/>
<jsp:include page="/WEB-INF/views/common/include_javascript.jsp"/>
<script src="${ctx}/static/js-modules/lib/fullcalendar.min.js"></script>
<script src="${ctx}/static/js-modules/lib/local/zh-cn.js"></script>
<script src="${ctx}/static/js/managerjs/overwork_history.js"></script>
</body>

</html>
