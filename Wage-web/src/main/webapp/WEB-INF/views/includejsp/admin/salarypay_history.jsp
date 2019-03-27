<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/common/include_js.jsp" %>
<html>
<head>
    <title>工资发放记录</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <jsp:include page="/WEB-INF/views/common/include_css.jsp"/>
    <link href="${ctx}/static/css/admin/salarypay_history.css" rel="stylesheet"/>
</head>
<body>
<div class="main-panel" style="width: 100%">
    <div class="content">
        <div class="content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-8" id="cardparent">
                        <div class="card" id="chart3" style="width: 100%;height: 158px">
                            <div id="p" class="easyui-panel" title="操作"
                                 style="padding:10px;background:#fafafa;"
                                 data-options="fit:true,border:false">
                                <form id="searchForm" method="post">
                                    <input  type="hidden" id="url1" name = "url">
                                    <input  type="hidden" id="name1" name = "name">

                                    <div>
                                        <label>姓名编号：</label>
                                        <input id="empName" name="empName" type="text" class="easyui-textbox"
                                               data-options="prompt:'请输入'"></input>
                                    </div>
                                    <div>
                                        <label>时间年月：</label>
                                        <input id="yearMonth" name="yearMonth" type="text" class="easyui-combobox"
                                               data-options="prompt:'全部'"></input>
                                    </div>
                                    <div>
                                        <label>所在部门：</label>
                                        <input id="departId" name="departId" type="text" class="easyui-combobox"
                                               data-options="prompt:'全部'"></input>
                                    </div>
                                    <div>
                                        <label>固定工资：</label>
                                        <input id="monSal" name="monSal" type="text" class="easyui-numberbox"
                                               data-options="prefix:'￥',prompt:'请输入'"></input>
                                    </div>
                                    <div>
                                        <label>其他工资：</label>
                                        <input id="otherSal" name="otherSal" type="text" class="easyui-numberbox"
                                               data-options="prefix:'￥',prompt:'请输入'"></input>
                                    </div>
                                    <div>
                                        <label>扣除工资：</label>
                                        <input id="deductSal" name="deductSal" type="text" class="easyui-numberbox"
                                               data-options="prefix:'￥',prompt:'请输入'"></input>
                                    </div>
                                    <div>
                                        <label>是否发放：</label>
                                        <select id="isGrant" class="easyui-combobox" name="isGrant" style="width: 97px;"data-options="prompt:'全部',panelHeight:70">
                                            <option value="">全部</option>
                                            <option value="1">是</option>
                                            <option value="0">否</option>
                                        </select>
                                    </div>
                                    <div>
                                        <label>发放人员：</label>
                                        <input id="grantEmpid1" name="grantEmpid" type="text" class="easyui-textbox"
                                               data-options="prompt:'请输入'"></input>
                                    </div>
                                    <div>
                                        <label>应发工资：</label>
                                        <input id="grossPayMin" name="grossPayMin" type="text" class="easyui-numberbox"
                                               style="margin-left: 18px" data-options="prefix:'￥',prompt:'最小值'"></input>
                                        <label style="margin-left: 22px;margin-right: 21px"> —— </label>
                                        <input id="grossPayMax" name="grossPayMax" type="text" class="easyui-numberbox"
                                               data-options="prefix:'￥',prompt:'最大值'"></input>
                                    </div>
                                    <div>
                                        <label>实发工资：</label>
                                        <input id="actualPayMin" name="actualPayMin" type="text"
                                               class="easyui-numberbox" style="margin-left: 18px"
                                               data-options="prefix:'￥',prompt:'最小值'"></input>
                                        <label style="margin-left: 22px;margin-right: 21px"> —— </label>
                                        <input id="actualPayMax" name="actualPayMax" type="text"
                                               class="easyui-numberbox" data-options="prefix:'￥',prompt:'最大值'"></input>
                                    </div>
                                    <div>
                                        <label>个人纳税：</label>
                                        <input id="personIncomeTaxMin" name="personIncomeTaxMin" type="text"
                                               class="easyui-numberbox" style="margin-left: 18px"
                                               data-options="prefix:'￥',prompt:'最小值'"></input>
                                        <label style="margin-left: 22px;margin-right: 21px"> —— </label>
                                        <input id="personIncomeTaxMax" name="personIncomeTaxMax" type="text"
                                               class="easyui-numberbox" data-options="prefix:'￥',prompt:'最大值'"></input>
                                    </div>
                                    <div>
                                        <label>发放时间：</label>
                                        <input id="grantTimeStart" name="grantTimeStart" type="text"
                                               class="easyui-datebox" style="margin-left: 18px"
                                               data-options="prompt:'最小时间'"></input>
                                        <label style="margin-left: 22px;margin-right: 21px"> —— </label>
                                        <input id="grantTimeEnd" name="grantTimeEnd" type="text" class="easyui-datebox"
                                               data-options="prompt:'最大时间'"></input>
                                    </div>
                                    <div id = "btns">
                                        <a id="btn" title="根据查询条件查询"  class="easyui-linkbutton"
                                           data-options="iconCls:'icon-search'">查询</a>
                                        <a id="btn1" title="清空查询条件"  class="easyui-linkbutton"
                                           data-options="iconCls:'icon-no'" >清空</a>
                                        <a id="btn7" title="更新上个月工资信息"  class="easyui-linkbutton"
                                           data-options="iconCls:'icon-ok'">全部</a>
                                        <a id="btn2" title="生成上个月工资信息"  class="easyui-linkbutton"
                                           data-options="iconCls:'icon-reload'">生成</a>
                                        <a id="btn3" title="更新上个月工资发放状态"  class="easyui-linkbutton"
                                           data-options="iconCls:'icon-redo'">发放</a>
                                        <a id="btn4" title="打印表格中的数据"  class="easyui-linkbutton"
                                           data-options="iconCls:'icon-print'">Excel</a>
                                        <a id="btn5" title="查看数据图表"  class="easyui-linkbutton"
                                           data-options="iconCls:'icon-large-shapes'">图表</a>
                                        <a id="btn6" title="查看数据统计表"  class="easyui-linkbutton"
                                           data-options="iconCls:'icon-sum'">统计</a>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <div class="card" id="chart1" style="width: 78.8%;margin-bottom: 0px;height: 425px">
                            <div id="datagrid"></div>
                        </div>
                        <div class="card" id="chart2" style="width: 18.8%;margin-bottom: 0px;height: 425px">
                            <div id="aa" class="easyui-accordion" data-options="fit:true,border:false">
                                <div title="工资明细">
                                    <table id="pg"></table>
                                </div>
                                <div title="五险一金明细">
                                    <table id="pg1"></table>
                                </div>
                                <div title="个人所得税明细">
                                    <table id="pg2"></table>
                                </div>
                                <div title="其他信息" style="padding:10px;">
                                    <div style="margin-bottom: 10px">
                                        <label>发放人员：</label>
                                        <input id="grantEmpid" type="text" class="easyui-textbox" disabled="disabled"
                                               style="width:140px;"> </input>
                                    </div>
                                    <div style="margin-bottom: 10px">
                                        <label>发放时间：</label>
                                        <input id="grantTime" type="text" class="easyui-textbox" disabled="disabled"
                                               style="width:140px;"> </input>
                                    </div>
                                    <label style="float: left;margin-right: 3px">发放备注：</label>
                                    <input id="remarks" disabled="disabled"
                                           style="float: left;width: 138px;height: 100px" type="text"
                                           class="easyui-textbox" data-options="multiline:true"></input>
                                    <div style="margin: 10px;margin-right: 6px">
                                        <a id="subBtn" href="javascript:void(0);" disabled="disabled" class="easyui-linkbutton"
                                           data-options="iconCls:'icon-ok'"
                                           style="width: 66px;float: right;margin-left: 7px">提交</a>
                                        <a id="updateBtn" href="javascript:void(0);" disabled="disabled" class="easyui-linkbutton"
                                           data-options="iconCls:'icon-edit'"
                                           style="width: 66px;float: right">修改</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/views/includejsp/admin/modal/salarypay_modal.jsp"/>
<jsp:include page="/WEB-INF/views/common/include_javascript.jsp"/>
<script src="${ctx}/static/js/Frame/layer.js"></script>
<script src="${ctx}/static/js/Frame/tableExporter.js"></script>
<script src="${ctx}/static/js-modules/lib/highcharts.js"></script>
<script src="${ctx}/static/js/managerjs/salarypay_history.js"></script>
</body>

</html>
