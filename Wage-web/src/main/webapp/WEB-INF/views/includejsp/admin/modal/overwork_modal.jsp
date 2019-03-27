<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal fade" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabe2">
                    修改员工加班信息
                </h4>
            </div>
            <div class="modal-body">
                <form id="updateForm" method="post" style="margin: 15px">
                    <div class="row">
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>员工编号：</label>
                                <input type="number"  id="empId" name="empId" class="form-control" readonly="readonly">
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label >员工姓名:</label>
                                <input type="text"  id="empName" class="form-control" readonly="readonly">
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label >时长(小时):</label>
                                <input type="number"  id="overHour" name="overHour" class="form-control"  placeholder="加班时长">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-4">
                            <div class="form-group">
                                <label >加班类型:</label>
                                <select id="overType" name="overType" class="form-control" >
                                    <option value="1">工作日加班</option>
                                    <option value="2">双休日加班</option>
                                    <option value="3">节假日加班</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>加班开始时间：</label>
                                <input type="text"  id="overTimeStart" name="overTimeStart" class="form-control" placeholder="请输入">
                            </div>
                        </div>
                        <input  type="hidden" id="overTimeStartOld" name = "overTimeStartOld">
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>加班结束时间：</label>
                                <input type="text"  id="overTimeEnd" name="overTimeEnd" class="form-control" placeholder="请输入">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group">
                                <label>加班原因：</label>
                                <input type="text"  id="overReason" name="overReason" class="form-control"  placeholder="请输入">
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
                <button type="button" id="sumbitUpdate" class="btn btn-primary">
                    提交更改
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabe3">
                    新增员工加班信息
                </h4>
            </div>
            <div class="modal-body">
                <form id="saveForm" method="post" style="margin: 15px">
                    <div class="row">
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>员工编号：</label>
                                <input type="number"  id="empId1" name="empId" class="form-control" readonly="readonly">
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label >员工姓名:</label>
                                <input type="text"  id="empName1" class="form-control" readonly="readonly">
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label >加班类型:</label>
                                <select id="overType1" name="overType" class="form-control" >
                                    <option value="1">工作日加班</option>
                                    <option value="2">双休日加班</option>
                                    <option value="3">节假日加班</option>
                                </select>
                            </div>
                        </div>
                        <input type="hidden"  id="overHour1" name="overHour">
                    </div>
                    <div class="row">
                        <input  type="hidden" id="isCheck" name = "isCheck" value="0">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label>加班开始时间：</label>
                                <input type="text"  id="overTimeStart1" name="overTimeStart" class="form-control" placeholder="请选择">
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label>加班结束时间：</label>
                                <input type="text"  id="overTimeEnd1" name="overTimeEnd" class="form-control" placeholder="请选择">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group">
                                <label>加班原因：</label>
                                <input type="text"  id="overReason1" name="overReason" class="form-control"  placeholder="请输入">
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
                <button type="button" id="sumbitSave" class="btn btn-primary">
                    提交更改
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<div class="modal fade" id="applyModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width:900px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title">
                    审核员工加班信息
                </h4>
            </div>
            <div class="modal-body">
                <div class="card" id="chart5" style="width:100%;height: 345px;display: none;margin-bottom: 0px">
                    <table id="applyDatagrid"></table>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<div class="modal fade" id="applyHisModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
                    <table id="applyHisDatagrid" ></table>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>