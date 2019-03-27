<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal fade" id="applyModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width:900px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title">
                    审批记录
                </h4>
            </div>
            <div class="modal-body">
                <div class="card" id="chart6" style="width:100%;height: 345px;display: none;margin-bottom: 0px">
                    <table id="applyHisDatagrid"></table>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<div class="modal fade" id="updateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    修改员工福利
                </h4>
            </div>
            <div class="modal-body">
                <form id="updateForm" method="post" style="margin: 15px">
                    <div class="row">
                        <input type="hidden" id="id1" name="id">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label>员工编号：</label>
                                <input type="number" id="empId" name="empId" class="form-control" readonly="readonly">
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label>员工姓名:</label>
                                <input type="text" id="empName" class="form-control" readonly="readonly">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label>金额(￥)：</label>
                                <input type="number" id="money" name="money" class="form-control" placeholder="请输入">
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label>福利类型:</label>
                                <select id="type1" name="type" class="form-control">
                                    <option value="">请选择</option>
                                    <option value="1">津贴</option>
                                    <option value="2">补贴</option>
                                    <option value="3">奖金</option>
                                    <option value="4">年终奖金</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label>生效时间：</label>
                                <input type="text" id="effectiveDate" name="effectiveDate" class="form-control"
                                       placeholder="请选择">
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label>原因：</label>
                                <input type="text" id="reason" name="reason" class="form-control" placeholder="请输入">
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
                <button type="button" id="sumbitUpdate" class="btn btn-primary">
                    提交修改信息
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="addLabel">
                    新增员工福利
                </h4>
            </div>
            <div class="modal-body" style="height: 375px">
                <div class="card" id="chart7"
                     style="width:276px;height: 345px;display: none;margin-bottom: 0px;float: right;">
                    <table id="empDatagrid"></table>
                    <div id="emptb" style="padding:5px">
                        <div>
                            姓名/编号：<input id="sq" type="text" name="sq" class="easyui-textbox"/>
                            <a id="seaBtn" href="javascript:void(0);" class="easyui-linkbutton"
                               iconCls="icon-search" onclick="">查询</a>
                        </div>
                    </div>
                </div>
                <div class="card" id="chart8" style="width:276px;height: 345px;margin-bottom: 0px;float: left;">
                    <form id="addForm" method="post">
                        <div style="padding: 5px;margin: 10px;margin-top: 15px">
                            <label>福利类型：</label>
                            <select id="welfareType1" class="easyui-combobox" name="type" style="width:180px;" data-options="panelHeight:96">
                                <option value="1">津贴</option>
                                <option value="2">补贴</option>
                                <option value="3">奖金</option>
                                <option value="4">年终奖金</option>
                            </select>
                        </div>
                        <div style="padding: 5px;margin: 10px">
                            <label>福利金额：</label>
                            <input id="money1" type="text" class="easyui-numberbox" name="money" style="width:180px;" data-options="min:0,precision:2,prefix:'￥'"></input>
                        </div>
                        <div style="padding: 5px;margin: 10px">
                            <label>生效日期：</label>
                            <input id="effectiveDate1" type="text" class="easyui-datebox" name="effectiveDate" style="width:180px;"> </input>
                        </div>
                        <div style="padding: 5px;margin: 10px;height: 110px">
                            <label style="float: left;margin-right: 3px">福利原因：</label>
                            <input id="reason1" style="float: left" type="text" class="easyui-textbox" data-options="multiline:true" name="reason"></input>
                        </div>
                        <div style="padding: 5px;margin: 10px;position: absolute;bottom:15px">
                            <a id="addSupBtn" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-add'"
                               style="width: 121px">提交</a>
                            <a id="clearSupBtn" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-reload'"
                               style="width: 121px">重置</a>
                        </div>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">完成
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>