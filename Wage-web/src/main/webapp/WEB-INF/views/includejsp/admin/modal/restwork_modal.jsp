<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal fade" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabe2">
                    修改员工请假信息
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
                                <input type="number"  id="restHour" name="restHour" class="form-control"  placeholder="请假时长">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label>请假开始时间：</label>
                                <input type="text"  id="restStartTime" name="restStartTime" class="form-control" placeholder="请选择">
                            </div>
                        </div>
                        <input  type="hidden" id="restStartTimeOld" name = "restStartTimeOld">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label>请假结束时间：</label>
                                <input type="text"  id="restEndTime" name="restEndTime" class="form-control" placeholder="请选择">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group">
                                <label>请假原因：</label>
                                <input type="text"  id="restReason" name="restReason" class="form-control"  placeholder="请输入">
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

<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabe1">
                    新增员工请假信息
                </h4>
            </div>
            <div class="modal-body">
                <form id="addForm" method="post" style="margin: 15px">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label>员工编号：</label>
                                <input type="number"  id="empId1" name="empId" class="form-control" readonly="readonly">
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label >员工姓名:</label>
                                <input type="text"  id="empName1" class="form-control" readonly="readonly">
                            </div>
                        </div>
                        <input type="hidden"  id="restHour1" name="restHour" >
                        <%--<div class="col-md-3">
                            <div class="form-group">
                                <label >是否全天:</label>
                                <select id="allDay1" name="allDay" class="form-control" >
                                    <option value="1">是</option>
                                    <option value="0">否</option>
                                </select>
                            </div>
                        </div>--%>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label>请假开始时间：</label>
                                <input type="text"  id="restStartTime1" name="restStartTime"  class="form-control" placeholder="请选择">
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label>请假结束时间：</label>
                                <input type="text"  id="restEndTime1" name="restEndTime"  class="form-control" placeholder="请选择">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group">
                                <label>请假原因：</label>
                                <input type="text"  id="restReason1" name="restReason" class="form-control"  placeholder="请输入">
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
                <button type="button" id="sumbitSave" class="btn btn-primary">
                    保存
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<div class="modal fade" id="applyModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width:700px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title">
                    审批员工撤回请假申请
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
    <div class="modal-dialog" style="width:700px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title">
                    审批撤回申请记录
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