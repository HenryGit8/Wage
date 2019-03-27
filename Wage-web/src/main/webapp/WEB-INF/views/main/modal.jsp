<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- 打开方式模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    模态框（Modal）标题
                </h4>
            </div>
            <div class="modal-body">
               <%--<iframe id="modelform" src="${ctx}\subform.jsp" style="width:100%;height:300px;"></iframe>--%>
               <iframe id="modelform" src="" style="width:100%;height:300px;"></iframe>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消
                </button>
                <button type="button" class="btn btn-primary">
                    确定
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<div class="modal fade" id="accoutSetModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 600px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabe2">
                    修改个人信息
                </h4>
            </div>
            <div class="modal-body">
                <form id="updateForm" method="post" style="margin: 15px">
                    <div class="row">
                        <div class="col-md-4">
                            <label>头像</label>
                            <label style="font-weight:normal;font-size: 10px">(点击图片修改)</label>
                            <img alt="" src="" id="headImg" class="img-responsive img-thumbnail" style="width: 180px;height: 160px;margin-bottom: 20px;cursor:pointer"/>
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
                                    <option value="男">男</option>
                                    <option value="女">女</option>
                                </select>
                            </div>
                        </div>
                        <%--<div class="col-md-8">
                            <div class="form-group">
                                <label>身份证号码：</label>
                                <input id="empIdnum2" name="empIdnum" type="text" class="form-control" placeholder="请输入身份证号码" style="height: 35px;">
                            </div>
                        </div>--%>
                        <div class="col-md-5">
                            <div class="form-group">
                                <label>毕业院校：</label>
                                <input type="text" id="empSchool2" name="empSchool" class="form-control" placeholder="请输入毕业院校" style="height: 35px;">
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="form-group">
                                <label>学历：</label>
                                <select id="empEdu2" name="empEdu" class="form-control" style="height: 35px;" >
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
                        <div class="col-md-8">
                            <div class="form-group">
                                <label>联系电话：</label>
                                <input type="text" id="empTell2" name="empTell" class="form-control" placeholder="联系电话" style="height: 35px;">
                            </div>
                        </div>
                    </div>

                    <div class="row" >
                        <div class="col-md-12">
                            <div class="form-group">
                                <label>现住址：</label>
                                <input type="text" id="empAddress2" name="empAddress" class="form-control" placeholder="请输入现住址" style="height: 35px;">
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
                <button type="button" id="sumbitUpdate" onclick="updateOnClick();" class="btn btn-primary">
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


<div class="modal fade" id="passwordSet" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 400px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="passwordSetLabe">
                    修改密码
                </h4>
            </div>
            <div class="modal-body" style="padding-bottom: 0px">
                <form id="updatePassForm" method="post" style="margin: 15px">
                    <div class="row" >
                        <div class="col-md-12">
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">原密码</span>
                                    <input id="oldPassword" name="oldPassword" type="password" class="form-control" placeholder="请输入原密码">
                                    <%--<input id="oldPassword" data-toggle="password" data-placement="after" class="form-control" type="password" >--%>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row" >
                        <div class="col-md-12">
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">新密码</span>
                                    <input id="newPassword1" name="newPassword1" type="password" class="form-control" placeholder="请输入新密码">
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row" >
                        <div class="col-md-12">
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">确认密码</span>
                                    <input id="newPassword2" type="password" class="form-control" placeholder="请输入确认密码">
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row" >
                        <div class="col-md-12">
                            <div class="form-group">
                                <input  type="hidden" id="questionType" name = "questionType">
                                <div class="input-group">
                                    <div class="input-group-btn">
                                        <button id="qu" type="button" class="btn btn-default
                        dropdown-toggle" data-toggle="dropdown">验证问题
                                            <span class="caret"></span>
                                        </button>
                                        <ul class="dropdown-menu">
                                            <li>
                                                <a href="javascript:void(0);" onclick="changeQuestion('qu','银行卡号','1')">银行卡号</a>
                                            </li>
                                            <%--<li>
                                                <a href="javascript:void(0);" onclick="changeQuestion('qu','工号','2')">工号</a>
                                            </li>--%>
                                            <li>
                                                <a href="javascript:void(0);" onclick="changeQuestion('qu','身份证号码','3')">身份证号码</a>
                                            </li>
                                        </ul>
                                    </div><!-- /btn-group --><%--
                                    <span class="input-group-addon">银行卡号</span>--%>
                                    <input id="questionValue" name="questionValue" type="text" class="form-control" placeholder="请输入问题答案">
                                    <img src="">
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row" >
                        <div class="col-md-8">
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">验证码</span>
                                    <input id="checkCode" name="checkCode" type="text" class="form-control" placeholder="请输入验证码">
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4" style="padding-left: 0px">
                            <div class="input-group">
                                <img alt="" src="" id="checkCodeImg" onclick="getVerify(this);" class="img-responsive img-thumbnail" style="width: 108px;height: 40px;padding: 1px"/>
                            </div>
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
                <button type="button" id="sumbitPassUpdate" onclick="passUpdateOnclick()" class="btn btn-primary">
                    提交更改
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>