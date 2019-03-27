<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    调整月固定工资
                </h4>
            </div>
            <div class="modal-body">
                <form id="updateForm" method="post" style="margin: 15px">
                    <div class="row">
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>基础工资：</label>
                                <input type="text" id="basicPayBefore" class="form-control" disabled="true">
                            </div>
                        </div>
                        <div class="col-md-1">
                            <div class="form-group">
                                <label style="margin-top:33px">→</label>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>调整后：</label>
                                <input type="number" id="basicPayAfter" name="empBasicPay" class="form-control">
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="form-group">
                                <label>变化了:</label>
                                <input type="text" id="basicPayChance" class="form-control" disabled="true">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>技能岗位工资：</label>
                                <input type="text" id="jobSalaryBefore" class="form-control" disabled="true">
                            </div>
                        </div>
                        <div class="col-md-1">
                            <div class="form-group">
                                <label style="margin-top:33px">→</label>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label></label>
                                <input type="number" id="jobSalaryAfter" name="empJobSalary" class="form-control">
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="form-group">
                                <label></label>
                                <input type="text" id="jobSalaryChance" class="form-control" disabled="true">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>工资总额（未税）：</label>
                                <input type="text" id="totalBefore" class="form-control" disabled="true">
                            </div>
                        </div>
                        <div class="col-md-1">
                            <div class="form-group">
                                <label style="margin-top:33px">→</label>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label></label>
                                <input type="text" id="totalAfter" name="monSal" class="form-control"
                                       readonly="readonly">
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="form-group">
                                <label></label>
                                <input type="text" id="totalChance" class="form-control" disabled="true">
                            </div>
                        </div>
                    </div>
                    <input type="hidden" id="empId" name="empId">
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
                <h4 class="modal-title" id="myModalLabel1">
                    调整额外工资
                </h4>
            </div>
            <div class="modal-body">
                <form id="chanceForm" method="post" style="margin: 15px">
                    <div class="row">
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>年月：</label>
                                <input type="text" id="yearMonth" name="yearMonth" class="form-control"
                                       readonly="readonly">
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>员工编号：</label>
                                <input type="number" id="empId1" name="empId" class="form-control" readonly="readonly">
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>员工姓名:</label>
                                <input type="text" id="empName1" class="form-control" readonly="readonly">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>津贴：</label>
                                <input type="number" id="monthAllowance" name="monthAllowance" class="form-control">
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>补贴：</label>
                                <input type="number" id="monthSubsidy" name="monthSubsidy" class="form-control">
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>本月奖金:</label>
                                <input type="number" id="monthBonus" name="monthBonus" class="form-control">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>加班费：</label>
                                <input type="number" id="overtimePay" name="overtimePay" class="form-control">
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>年终奖金：</label>
                                <input type="number" id="yearEndBonus" name="yearEndBonus" class="form-control">
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>额外工资总额:</label>
                                <input type="text" id="otherSal" name="otherSal" class="form-control"
                                       readonly="readonly">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>请假/旷工扣除：</label>
                                <input type="number" id="restSalary" name="restSalary" class="form-control">
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>其他扣除：</label>
                                <input type="number" id="fineSalary" name="fineSalary" class="form-control">
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>扣除工资总额:</label>
                                <input type="text" id="deductSal" name="deductSal" class="form-control"
                                       readonly="readonly">
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
                <button type="button" id="sumbitChance" class="btn btn-primary">
                    提交更改
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

