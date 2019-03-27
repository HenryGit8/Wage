<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/common/include_js.jsp" %>
<html>
<head>
    <title>月工资管理</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <jsp:include page="/WEB-INF/views/common/include_css.jsp"/>
    <link rel="stylesheet" href="${ctx}/static/css/admin/empmonsal_manager.css">
</head>
<body class="easyui-layout" id="layout">
<div data-options="region:'center',title:'center title',noheader:true" style="background:#f7f7f7">
    <div class="content" style="height: 100%">
        <div class="container-fluid" style="height: 100%">
            <div class="row" style="height: 100%">
                <div class="col-md-8" id="cardparent" style="padding: 28px;height: 100%">
                    <div class="card" style="height: 100%;margin-bottom: 23px">
                        <table id="datagrid1"></table>
                        <div id="tb" style="padding:5px;height: 38px">
                            <form id="searchMonSal" method="post">
                                <div style="width: 192px;float: left">
                                    员工姓名：<input id="empName" class="easyui-textbox" type="text" name="empName"
                                                style="width:124px"/>
                                    <div class="btn-separator"></div>
                                </div>
                                <div style="width: 275px;float: left;margin-left: 5px">
                                    基本工资：<input id="basicPayStart" class="easyui-numberspinner" type="text"
                                                name="basicPayStart"
                                                style="width:94px;left: 30px"
                                                data-options="prefix:'￥',prompt:'最小值'"/>
                                    — <input id="basicPayEnd" class="easyui-numberspinner" type="text"
                                             name="basicPayEnd"
                                             style="width:94px;left: 30px"
                                             data-options="prefix:'￥',prompt:'最大值'"/>
                                    <div class="btn-separator"></div>
                                </div>
                                <div style="width: 311px;float: left;margin-left: 5px">
                                    岗位或技能工资：<input id="jobSalaryStart" class="easyui-numberspinner" type="text"
                                                   name="jobSalaryStart"
                                                   style="width:94px;left: 30px"
                                                   data-options="prefix:'￥',prompt:'最小值'"/>
                                    — <input id="jobSalaryEnd" class="easyui-numberspinner" type="text"
                                             name="jobSalaryEnd"
                                             style="width:94px;left: 30px"
                                             data-options="prefix:'￥',prompt:'最大值'"/>
                                    <div class="btn-separator"></div>
                                </div>
                                <div style="width: 275px;float: left;margin-left: 5px">
                                    工资总额：<input id="totalStart" class="easyui-numberspinner" type="text"
                                                name="totalStart"
                                                style="width:94px;left: 30px"
                                                data-options="prefix:'￥',prompt:'最小值'"/>
                                    — <input id="totalEnd" class="easyui-numberspinner" type="text" name="totalEnd"
                                             style="width:94px;left: 30px"
                                             data-options="prefix:'￥',prompt:'最大值'"/>
                                    <div class="btn-separator"></div>
                                </div>
                                <div style="width: 114px;float: left;margin-left: 5px">
                                    <a id="searchBtn" href="javascript:void(0);" class="easyui-linkbutton"
                                       iconCls="icon-search" onclick="">查询</a>
                                    <a id="clearBtn" href="javascript:void(0);" class="easyui-linkbutton"
                                       iconCls="icon-no" onclick="" style="margin-left: 2px">清空</a>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="sourthPanel" data-options="region:'south',title:'其他工资',split:true,collapsed:true"
     style="height:50%;background:#f7f7f7">
    <div class="content" style="height: 99%">
        <div class="container-fluid" style="height: 100%">
            <div class="row" style="height: 100%">
                <div class="col-md-8" id="cardparent1" style="padding: 28px;height: 100%;width: 100%">
                    <div class="card" id="chart1" style="height: 100%;width: 100%">
                        <table id="datagrid2"></table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/views/includejsp/admin/modal/empmonsal_modal.jsp"/>
<jsp:include page="/WEB-INF/views/common/include_javascript.jsp"/>
<script src="${ctx}/static/js/managerjs/empmonsal_manager.js"></script>
</body>

</html>