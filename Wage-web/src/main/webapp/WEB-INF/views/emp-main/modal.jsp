<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="my-modal" class="am-modal am-modal-prompt" tabindex="-1" style="">
    <div class="am-modal-dialog" style="width: 510px">
        <div class="am-modal-hd" id="headText" style="margin-bottom: 20px;font-size: 20px">您是首次登录，请完善您的个人信息
            <a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a>
        </div>
        <div class="am-modal-bd" style="padding: 20px">
            <form id="updateFirst" class="layui-form" action="" lay-filter="test2">
                <input  type="hidden" id="type" value="0">
                <div class="layui-form-item">
                    <div class="layui-form-item" style="margin-bottom: 0px">
                        <img alt="" src="" id="headImg"
                             style="width: 145px;height: 145px;cursor:pointer; border:1px solid rgb(194,194,194)"/>
                        <label style="position: absolute;right: 67px;top:210px;color: white;font-family: '幼圆';font-size: 13px">点击图片修改</label>
                        <div class="layui-input-inline" style="width: 300px">
                            <div class="layui-form-item">
                                <label class="layui-form-label">姓名：</label>
                                <div class="layui-input-inline">
                                    <input type="text" id="empName" name="empName" required lay-verify="required" placeholder="请输入姓名"
                                           autocomplete="off"
                                           class="layui-input" style="width: 215px">
                                </div>
                            </div>

                            <div class="layui-form-item">
                                <label class="layui-form-label">性别：</label>
                                <div class="layui-input-inline">
                                    <input type="radio" id="man" name="empSex" value="男" title="男"  >
                                    <input type="radio" id="woman" name="empSex" value="女" title="女" >
                                </div>
                            </div>

                            <div class="layui-form-item">
                                <label class="layui-form-label">电话：</label>
                                <div class="layui-input-inline">
                                    <input type="text" id="empTell" name="empTell" required lay-verify="required" placeholder="请输入"
                                           autocomplete="off" class="layui-input" style="width: 215px">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">地址：</label>
                        <div class="layui-input-inline" style="width: 80%">
                            <input type="text" id="empAddress" name="empAddress" required lay-verify="required" placeholder="请输入地址"
                                   autocomplete="off" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">学历：</label>
                        <div class="layui-input-inline" style="width: 25%">
                            <select id="empEdu" name="empEdu" lay-verify="required">
                                <option value=""></option>
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

                        <label class="layui-form-label">院校：</label>
                        <div class="layui-input-inline" style="width: 36%">
                            <input type="text" id="empSchool" name="empSchool" required lay-verify="required" placeholder="请输入地址"
                                   autocomplete="off" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item" id="empIdnumFa">
                        <label class="layui-form-label"  style="width: 110px">身份证号：</label>
                        <div class="layui-input-inline" style="width: 74%">
                            <input type="text"  id="empIdnum" name="empIdnum" required lay-verify="required" placeholder="请输入"
                                   autocomplete="off" class="layui-input">
                        </div>
                    </div>
                </div>
            </form>
        </div>
        <div class="am-modal-footer">
            <span class="am-modal-btn" data-am-modal-cancel>取消</span>
            <span id="submitFirst" class="am-modal-btn" data-am-modal-confirm>提交</span>
        </div>
    </div>
</div>

<div class="am-modal am-modal-confirm" tabindex="-1" id="confirmSubmit">
    <div class="am-modal-dialog">
        <div class="am-modal-hd">提交</div>
        <div class="am-modal-bd" style="text-align: center">
            您只有一次修改身份证号码的机会，您确认了吗？
        </div>
        <div class="am-modal-footer">
            <span class="am-modal-btn" data-am-modal-cancel>取消</span>
            <span class="am-modal-btn" data-am-modal-confirm>确定</span>
        </div>
    </div>
</div>

<div id="updateHead" class="am-modal am-modal-prompt" tabindex="-1" style="z-index: 1200">
    <div class="am-modal-dialog" style="width: 400px">
        <div class="am-modal-hd" style="font-size: 20px">修改头像
            <a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a>
        </div>
        <div class="am-modal-bd" style="padding: 20px">
            <div>
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
        <div class="am-modal-footer">
            <span class="am-modal-btn" data-am-modal-confirm>完成</span>
        </div>
    </div>
</div>


<div id="noticeModal" class="am-modal am-modal-prompt" tabindex="-1"  style="">
    <div class="am-modal-dialog" style="width: 710px">
        <div class="am-modal-hd" id="headText1" style="margin-bottom: 20px;font-size: 20px">公告列表
            <a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a>
        </div>
        <div class="am-modal-bd" style="padding: 0px 20px 20px 20px">
            <table class="layui-table" id="noticeTable" lay-filter="noticeTable" lay-data="{id: 'noticeTable'}"></table>
            <script type="text/html" id="barLook">
                <a class="layui-btn layui-btn-xs" lay-event="detail">查看</a>
            </script>
        </div>
        <div class="am-modal-footer">
            <span class="am-modal-btn" data-am-modal-cancel>关闭</span>
        </div>
    </div>
</div>

<div id="seeNoticeModal" class="am-modal am-modal-prompt" tabindex="-1" style="">
    <div class="am-modal-dialog" style="width: 710px">
        <div class="am-modal-hd" id="headText2" style="margin-bottom: 20px;font-size: 20px">查看公告
            <a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a>
        </div>
        <div class="am-modal-bd" style="padding: 0px 20px 20px 20px;">
            <div style="padding: 5px 0; color: #ccc">内容</div>
            <div id="div5" class="text" > <!--可使用 min-height 实现编辑区域自动增加高度-->
                <%--<p id="pbq">请输入内容...</p>--%>
            </div>
        </div>
        <div class="am-modal-footer">
            <span class="am-modal-btn" data-am-modal-cancel>关闭</span>
        </div>
    </div>
</div>

<div id="passSetModal" class="am-modal am-modal-prompt" tabindex="-1" style="">
    <div class="am-modal-dialog" style="width: 400px">
        <div class="am-modal-hd" id="headText3" style="margin-bottom: 20px;font-size: 20px">修改密码
            <a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a>
        </div>
        <div class="am-modal-bd" style="padding: 0px 30px 20px 30px;">
            <form id="updatePass" class="layui-form layui-form-pane" action="" lay-filter="updatePass">
                <div class="layui-form-item">
                    <label class="layui-form-label">原密码：</label>
                    <div class="layui-input-block" >
                        <input type="password" id="oldPassword" name="oldPassword" required lay-verify="required" placeholder="请输入原密码"
                               autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">新密码：</label>
                    <div class="layui-input-block" >
                        <input type="password" id="newPassword1" name="newPassword1" required lay-verify="required" placeholder="请输入新密码"
                               autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">确认密码：</label>
                    <div class="layui-input-block" >
                        <input type="password" id="newPassword2" required lay-verify="required" placeholder="请输入确认密码"
                               autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-input-inline" style="width: 32.8%;margin-right: 0px">
                        <select id="questionType" name="questionType" lay-verify="required">
                            <option value=""> 验证问题</option>
                            <option value="1">银行卡号</option>
                            <option value="3">身份证号码</option>
                        </select>
                    </div>
                    <div class="layui-input-inline" style="width: 67.4%;margin-right: 0px">
                        <input type="text" id="questionValue" name="questionValue" placeholder="请输入答案" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label" >验证码：</label>
                    <div class="layui-input-block" style="margin-right: 0px">
                        <input type="text" id="checkCode" name="checkCode" style="width: 60%;float: left" required lay-verify="required" placeholder="请输入验证码"
                               autocomplete="off" class="layui-input">
                        <img alt="" src="" id="checkCodeImg" onclick="getVerify(this);" style="width: 91px;height: 38px; border:1px solid rgb(230,230,230);border-left:0px;float: left"/>
                    </div>
                </div>
            </form>

        </div>
        <div class="am-modal-footer">
            <span class="am-modal-btn" data-am-modal-cancel>关闭</span>
            <span id="passSetBtn" onclick="passUpdateOnclick();" class="am-modal-btn" data-am-modal-confirm>提交</span>
        </div>
    </div>
</div>


<div class="am-modal am-modal-confirm" tabindex="-1" id="passSetSubmit">
    <div class="am-modal-dialog">
        <div class="am-modal-hd">提交</div>
        <div class="am-modal-bd" style="text-align: center">
            您确定修改密码吗？
        </div>
        <div class="am-modal-footer">
            <span class="am-modal-btn" data-am-modal-cancel>取消</span>
            <span class="am-modal-btn" data-am-modal-confirm>确定</span>
        </div>
    </div>
</div>


<div id="loginHisModal" class="am-modal am-modal-prompt" tabindex="-1" style="">
    <div class="am-modal-dialog" style="width: 700px">
        <div class="am-modal-hd" id="headText4" style="font-size: 20px">登录记录
            <a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a>
        </div>
        <div class="am-modal-bd" style="padding: 20px">
            <table id="loginHisTable"></table>
        </div>
        <div class="am-modal-footer">
            <span class="am-modal-btn" data-am-modal-cancel>关闭</span>
        </div>
    </div>
</div>