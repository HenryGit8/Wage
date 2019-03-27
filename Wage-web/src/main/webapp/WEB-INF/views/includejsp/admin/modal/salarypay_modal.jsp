<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="dd" class="easyui-dialog" title="输出excel" style="width:300px;height:215px;display: none;padding: 15px"
     data-options="resizable:true,modal:true,open:false,buttons:'#bb'">
    <label>输出文件夹路径：</label>
    <input id="url" class="easyui-textbox" style="width:100%" data-options="prompt:'如(E:/)'">
    <label style="margin-top: 10px">输出文件名称：</label>
    <input id="name" class="easyui-textbox" style="width:100%" data-options="prompt:'输出文件名称'">
    <div id="bb">
        <a id="print" title="输出为excel" class="easyui-linkbutton" data-options="iconCls:'icon-print'">输出</a>
        <a id="close" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">关闭</a>
    </div>
</div>
<div class="modal fade" id="wageModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 700px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title">
                    工资单
                </h4>
            </div>
            <div class="modal-body">
                <table id="wageTable">
                    <tr>
                        <td>年月</td>
                        <td>编号</td>
                        <td>姓名</td>
                        <td>基础工资</td>
                        <td>岗位工资</td>
                        <td>津贴</td>
                        <td>补贴</td>
                        <td>月奖金</td>
                    </tr>
                    <tbody>
                    <tr>
                        <td id="data1">无数据</td>
                        <td id="data2">无数据</td>
                        <td id="data3">无数据</td>
                        <td id="data4">无数据</td>
                        <td id="data5">无数据</td>
                        <td id="data6">无数据</td>
                        <td id="data7">无数据</td>
                        <td id="data8">无数据</td>
                    </tr>
                    <tr>
                        <td>年终奖金</td>
                        <td>加班费</td>
                        <td>请假扣除</td>
                        <td>其他扣除</td>
                        <td>个人应缴四金</td>
                        <td>单位应缴五险一金</td>
                        <td>个人所得税</td>
                        <td>实发工资</td>
                    </tr>
                    <tr>
                        <td id="data9">无数据</td>
                        <td id="data10">无数据</td>
                        <td id="data11">无数据</td>
                        <td id="data12">无数据</td>
                        <td id="data13">无数据</td>
                        <td id="data14">无数据</td>
                        <td id="data15">无数据</td>
                        <td id="data16">无数据</td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
                <button type="button" id="printExcel" onclick="exportTo();" class="btn btn-primary">
                    打印
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<div class="modal fade" id="updateIsGrantModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 700px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title">
                    工资发放状态更新
                </h4>
            </div>
            <div class="modal-body">
                <div class="card" id="chart5" style="width:100%;height: 345px;display: none;margin-bottom: 0px">
                    <table id="modalDatagrid"></table>
                    <div id="tb" style="padding:5px">
                        <div>
                            发放时间：<input id="grantTime1" type="text" name="grantTime" class="easyui-datebox" data-options="prompt:'为空为当前时间'"/>
                            备注：<input id="remarks1" type="text" name="remarks" class="easyui-textbox" style="width: 60%" data-options="prompt:'备注可为空'"/>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<div class="modal fade" id="chartModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 1000px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title">
                    月数据变化统计图表
                </h4>
            </div>
            <div class="modal-body">
                <div class="card" id="chart7" style="width:100%;height: 100%;margin-bottom: 0px">
                    <div id="container"></div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>


<div class="modal fade" id="totalModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 900px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" style="width: 25%;float: left;margin-top: 5px">
                    月各数据总额统计表
                </h4>
                <select id="yearMonthCho" class="form-control" style="width: 13%;float: right;margin-right: 15px">
                    <option value="">全部</option>
                </select>
            </div>
            <div class="modal-body">
                <table id="totalTable">
                    <tr>
                        <td colspan="5">月工资</td>
                        <td>五险一金(个人)</td>
                    </tr>
                    <tr>
                        <td>固定工资</td>
                        <td>额外工资</td>
                        <td>扣除工资</td>
                        <td>应发工资</td>
                        <td>实发工资</td>
                        <td>个人养老保险金</td>
                    </tr>
                    <tbody>
                    <tr>
                        <td id="total1">无数据</td>
                        <td id="total2">无数据</td>
                        <td id="total3">无数据</td>
                        <td id="total4">无数据</td>
                        <td id="total5">无数据</td>
                        <td id="total6">无数据</td>
                    </tr>
                    <tr>
                        <td colspan="3">五险一金(个人)</td>
                        <td colspan="3">五险一金(单位)</td>
                    </tr>
                    <tr>
                        <td>个人医疗保险金</td>
                        <td>个人失业保险金</td>
                        <td>个人基本住房公积金</td>
                        <td>企业养老保险金</td>
                        <td>企业医疗保险金</td>
                        <td>企业失业保险金</td>
                    </tr>
                    <tr>
                        <td id="total7">无数据</td>
                        <td id="total8">无数据</td>
                        <td id="total9">无数据</td>
                        <td id="total10">无数据</td>
                        <td id="total11">无数据</td>
                        <td id="total12">无数据</td>
                    </tr>
                    <tr>
                        <td colspan="3">五险一金(单位)</td>
                        <td rowspan="2">个人所得税</td>
                        <td rowspan="2">个人应缴五险一金总额</td>
                        <td rowspan="2">企业应缴五险一金总额</td>
                    </tr>
                    <tr>
                        <td>企业基本住房公积金</td>
                        <td>企业工伤保险金</td>
                        <td>企业生育保险金</td>
                    </tr>
                    <tr>
                        <td id="total13">无数据</td>
                        <td id="total14">无数据</td>
                        <td id="total15">无数据</td>
                        <td id="total16">无数据</td>
                        <td id="total17">无数据</td>
                        <td id="total18">无数据</td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
                <button type="button" id="printExcel2" onclick="exportToC();" class="btn btn-primary">
                    打印
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>