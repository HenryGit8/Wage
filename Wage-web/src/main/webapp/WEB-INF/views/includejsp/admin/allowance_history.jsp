<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/common/include_js.jsp" %>
<html>
<head>
    <title>津贴记录</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <jsp:include page="/WEB-INF/views/common/include_css.jsp"/>
    <link rel="stylesheet" href="${ctx}/static/css/admin/allowance_history.css">
</head>
<body  class="easyui-layout" id="layout">
<div id="westPanel" data-options="region:'east',title:'人员查询',split:true,hideCollapsedContent:false,noheader:true,collapsed:true,maxWidth:300,minWidth:280, onExpand:function(){openEmp()},onCollapse:function(){turnoffEmp()}"
     style="width: 22%;background:#f7f7f7">
    <div class="content" style="height: 99.5%;width: 100%">
        <div class="container-fluid" style="height: 100%;width: 100%">
            <div class="row" style="height: 100%;width: 100%">
                <div class="col-md-2"  style="padding: 28px;height: 100%;width: 100%;padding-right: 0px">
                    <div class="card" style="height: 100%;width: 100%">
                        <table id="datagrid1">
                        </table>
                        <div id="tb" style="padding:5px">
                            <div>
                                姓名/编号：<input id="Eq" type="text" name="Eq"
                                             class="easyui-textbox"/>
                                <a id="searchBtn" href="javascript:void(0);" class="easyui-linkbutton"
                                   iconCls="icon-search" onclick="">查询</a>
                                <a id="searchAllBtn" href="javascript:void(0);" class="easyui-linkbutton"
                                   iconCls="icon-reload" onclick="">全部</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div data-options="region:'center',title:'center title',noheader:true" style="background:#f7f7f7">
    <div class="content">
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-10" style="padding: 28px;height: 100%;width: 100%;padding-bottom: 0px">
                    <div class="card card-user" style="height: 290px;margin-bottom: 0px">
                        <table id="datagrid2"></table>
                        <div id="tb2" style="padding:5px">
                            <form id="searchWelfare" method="post">
                                <div id="formdiv1">
                                    <input  type="hidden" id="empId3" name = "empId">
                                    <p id="label5">生效时间：</p>
                                    <input id="effectiveDateMin" type="text" name="effectiveDateMin" class="form-control"  placeholder="请选择" />
                                    <p id="label6">至</p>
                                    <input id="effectiveDateMax" type="text" name="effectiveDateMax"  class="form-control"  placeholder="请选择" />
                                    <div class="btn-separator"></div>
                                    <p id="label7">金额：</p>
                                    <div style="width: auto" id="total1">
                                        <input id="moneyMin" type="text" name="moneyMin" />
                                        — <input id="moneyMax" type="text" name="moneyMax"  />
                                    </div>
                                    <div class="btn-separator"></div>
                                    <p id="label8">类型：</p>
                                    <select id="type" class="easyui-combobox" name="type" data-options="panelHeight:116">
                                        <option value="">请选择</option>
                                        <option value="1">津贴</option>
                                        <option value="2">补贴</option>
                                        <option value="3">奖金</option>
                                        <option value="4">年终奖金</option>
                                    </select>
                                    <a id="empIdBtn" title="收起右侧侧边栏" href="javascript:void(0);" class="easyui-linkbutton"
                                       iconCls="icon-redo" onclick="" style="margin-right: 5px;display: none" >收起</a>
                                    <a id="addBtn1" title="批量新增福利" href="javascript:void(0);" class="easyui-linkbutton"
                                       iconCls="icon-add" onclick="" style="margin-right: 5px">新增</a>
                                    <a id="clearBtn1" title="清除查询条件" href="javascript:void(0);" class="easyui-linkbutton"
                                       iconCls="icon-no" onclick="" style="margin-right: 5px">清除</a>
                                    <a id="searchBtn2" title="根据查询条件查询" href="javascript:void(0);" class="easyui-linkbutton"
                                       iconCls="icon-search" onclick="" style="margin-right: 5px">查询</a>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="col-md-10" style="padding: 28px;height: 100%;width: 100%;padding-bottom: 0px">
                    <div class="card card-user" style="margin-bottom: 0px;height: 295px">
                        <table id="datagrid3"></table></div>
                    <div id="tb1" style="padding:5px">
                        <form id="searchAllow" method="post">
                            <div id="formdiv">
                                <input  type="hidden" id="empId2" name = "empId">
                                <input  type="hidden" id="isAllow" name = "isAllow">
                                <p id="label1">申请时间：</p>
                                <input id="applyTimeMin" type="text" name="applyTimeMin" class="form-control"  placeholder="请选择" />
                                <p id="label2">至</p>
                                <input id="applyTimeMax" type="text" name="applyTimeMax"  class="form-control"  placeholder="请选择" />
                                <div class="btn-separator"></div>
                                <p id="label3">金额：</p>
                                <div style="width: auto" id="total">
                                    <input id="welfareTotalMin" type="text" name="welfareTotalMin" />
                                    — <input id="welfareTotalMax" type="text" name="welfareTotalMax"  />
                                </div>
                                <div class="btn-separator"></div>
                                <p id="label4">类型：</p>
                                <select id="welfareType" class="easyui-combobox" name="welfareType" data-options="panelHeight:70">
                                    <option value="">请选择</option>
                                    <option value="1">津贴</option>
                                    <option value="2">补贴</option>
                                </select>
                                <a id="applyHisBtn" title="查看已审批的记录" href="javascript:void(0);" class="easyui-linkbutton"
                                   iconCls="icon-large-smartart" onclick="" style="margin-right: 5px">记录</a>
                                <a id="clearBtn" title="清空查询条件" href="javascript:void(0);" class="easyui-linkbutton"
                                   iconCls="icon-no" onclick="" style="margin-right: 5px">清除</a>
                                <a id="searchBtn1" title="根据查询条件查询" href="javascript:void(0);" class="easyui-linkbutton"
                                   iconCls="icon-search" onclick="" style="margin-right: 5px">查询</a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/views/includejsp/admin/modal/allowance_modal.jsp"/>
<jsp:include page="/WEB-INF/views/common/include_javascript.jsp"/>
<script src="${ctx}/static/js/managerjs/allowance_history.js"></script>
</body>

</html>
