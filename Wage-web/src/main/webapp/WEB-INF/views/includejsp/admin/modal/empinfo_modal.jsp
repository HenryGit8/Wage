<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 650px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    新增员工
                </h4>
            </div>
            <div class="modal-body">
                <form id="saveForm" method="post" style="margin: 15px">
                    <input  type="hidden" id="headMd1" name = "headMd">
                    <div class="row">
                        <div class="col-md-4">
                            <label>头像</label>
                            <label style="font-weight:normal;font-size: 10px">(点击图片修改)</label>
                            <img alt="" src="" id="headImg1" class="img-responsive img-thumbnail" style="width: 180px;height: 170px;margin-bottom: 20px;cursor:pointer"/>
                        </div>
                        <div class="col-md-5">
                            <div class="form-group">
                                <label>名字：</label>
                                <input type="text"  id="empName1" name="empName" class="form-control" placeholder="请输入名字" style="height: 35px;">
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="form-group">
                                <label>性别：</label>
                                <select id="empSex1" name="empSex" class="form-control"  style="height: 35px;" >
                                    <option value="">请选择</option>
                                    <option value="男">男</option>
                                    <option value="女">女</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>身份证号码：</label>
                                <input id="empIdnum1" name="empIdnum" type="text" class="form-control" placeholder="请输入身份证号码" style="height: 35px;">
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>联系电话：</label>
                                <input type="text" id="empTell1" name="empTell" class="form-control" placeholder="联系电话" style="height: 35px;">
                            </div>
                        </div>
                        <div class="col-md-8">
                            <div class="form-group">
                                <label>现住址：</label>
                                <input type="text" id="empAddress1" name="empAddress" class="form-control" placeholder="请输入现住址" style="height: 35px;">
                            </div>
                        </div>
                    </div>

                    <div class="row" >
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>毕业院校：</label>
                                <input type="text" id="empSchool1" name="empSchool" class="form-control" placeholder="请输入毕业院校" style="height: 35px;">
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>学历：</label>
                                <select id="empEdu1" name="empEdu" class="form-control" style="height: 35px;" >
                                    <option value="">请选择</option>
                                    <option value="小学">小学</option>
                                    <option value="初中">初中</option>
                                    <option value="中专">中专</option>
                                    <option value="普通高中">普通高中</option>
                                    <option value="专科">专科</option>
                                    <option value="本科">本科</option>
                                    <option value="硕士研究生">硕士研究生</option>
                                    <option value="博士研究生">博士研究生</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>银行卡号：</label>
                                <input type="text" id="empBankCard1" name="empBankCard" class="form-control" placeholder="请输入银行卡号" style="height: 35px;">
                            </div>
                        </div>
                    </div>

                    <div class="row" >
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>基础工资：</label>
                                <input type="number" id="empBasicPay" name="empBasicPay" class="form-control" placeholder="请输入基础工资" style="height: 35px;">
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>部门：</label>
                                <select id="departId1" name="departId" class="form-control" style="height: 35px;" >
                                    <option value="">请选择</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>薪资等级：</label>
                                <select id="departRand1" name="departRand" class="form-control" style="height: 35px;" >
                                    <option value="">请选择</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
                <button type="button" id="sumbitSave" class="btn btn-primary">
                    提交员工信息
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 650px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabe2">
                    修改员工信息
                </h4>
            </div>
            <div class="modal-body">
                <form id="updateForm" method="post" style="margin: 15px">
                    <input  type="hidden" id="empId2" name = "empId">
                    <div class="row">
                        <div class="col-md-4">
                            <label>头像</label>
                            <label style="font-weight:normal;font-size: 10px">(点击图片修改)</label>
                            <img alt="" src="" id="headImg2" class="img-responsive img-thumbnail" style="width: 180px;height: 170px;margin-bottom: 20px;cursor:pointer"/>
                        </div>
                        <div class="col-md-5">
                            <div class="form-group">
                                <label>名字：</label>
                                <input type="text"  id="empName2" name="empName" class="form-control" placeholder="请输入名字" style="height: 35px;">
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="form-group">
                                <label>性别：</label>
                                <select id="empSex2" name="empSex" class="form-control"  style="height: 35px;" >
                                    <option value="">请选择</option>
                                    <option value="男">男</option>
                                    <option value="女">女</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-8">
                            <div class="form-group">
                                <label>身份证号码：</label>
                                <input id="empIdnum2" name="empIdnum" type="text" class="form-control" placeholder="请输入身份证号码" style="height: 35px;">
                            </div>
                        </div>
                        <div class="col-md-8">
                            <div class="form-group">
                                <label>现住址：</label>
                                <input type="text" id="empAddress2" name="empAddress" class="form-control" placeholder="请输入现住址" style="height: 35px;">
                            </div>
                        </div>
                    </div>
                    <div class="row" >
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>联系电话：</label>
                                <input type="text" id="empTell2" name="empTell" class="form-control" placeholder="联系电话" style="height: 35px;">
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>毕业院校：</label>
                                <input type="text" id="empSchool2" name="empSchool" class="form-control" placeholder="请输入毕业院校" style="height: 35px;">
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>学历：</label>
                                <select id="empEdu2" name="empEdu" class="form-control" style="height: 35px;" >
                                    <option value="">请选择</option>
                                    <option value="小学">小学</option>
                                    <option value="初中">初中</option>
                                    <option value="中专">中专</option>
                                    <option value="普通高中">普通高中</option>
                                    <option value="专科">专科</option>
                                    <option value="本科">本科</option>
                                    <option value="硕士研究生">硕士研究生</option>
                                    <option value="博士研究生">博士研究生</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row" >
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>银行卡号：</label>
                                <input type="text" id="empBankCard2" name="empBankCard" class="form-control" placeholder="请输入银行卡号" style="height: 35px;">
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>部门：</label>
                                <select id="departId2" name="departId" class="form-control" style="height: 35px;" >
                                    <option value="">请选择</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>薪资等级：</label>
                                <select id="departRand2" name="departRand" class="form-control" style="height: 35px;" >
                                    <option value="">请选择</option>
                                    <option value="1">等级1</option>
                                    <option value="2">等级2</option>
                                    <option value="3">等级3</option>
                                </select>
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

<div class="modal fade" id="imgModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width:420px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title">
                    头像修改
                </h4>
            </div>
            <div class="modal-body">
                <div style="padding: 10px 20px;">
                    <form role="form" enctype="multipart/form-data" method="post">
                        <div class="embed-responsive embed-responsive-16by9" style="height: 330px">
                            <div class="embed-responsive-item">
                                <img alt="" src="" id="cut-img"
                                     class="img-responsive img-thumbnail" style="width: 100%;height: 100%"/>
                            </div>
                        </div>
                        <div class="white-divider-md"></div>
                        <input type="file" name="imgFile" id="fileUpload"/>
                        <div class="white-divider-md"></div>
                        <div id="alert" class="alert alert-danger hidden" role="alert"></div>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>



<div class="modal fade" id="imgModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width:420px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title">
                    头像修改
                </h4>
            </div>
            <div class="modal-body">
                <div style="padding: 10px 20px;">
                    <form role="form" enctype="multipart/form-data" method="post">
                        <div class="embed-responsive embed-responsive-16by9" style="height: 330px">
                            <div class="embed-responsive-item">
                                <img alt="" src="" id="cut-img1"
                                     class="img-responsive img-thumbnail" style="width: 100%;height: 100%"/>
                            </div>
                        </div>
                        <div class="white-divider-md"></div>
                        <input type="file" name="imgFile" id="fileUpload1"/>
                        <div class="white-divider-md"></div>
                        <div id="alert1" class="alert alert-danger hidden" role="alert"></div>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

