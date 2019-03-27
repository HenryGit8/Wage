<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal fade" id="loginHisModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 700px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title">
                    登录记录
                </h4>
            </div>
            <div class="modal-body">
                <div class="card" id="chart1" style="width:100%;height: 305px;display: none;margin-bottom: 0px">
                    <div id="loginHisDatagrid"></div>
                    <div id="tb" style="padding:5px">
                        <form id="searchForm" method="post">
                            <div>
                                员工编号/名称：<input id="empName" type="text" name="empName" class="easyui-textbox"
                                               data-options="prompt:'请输入'" style="width: 16%"/>
                                开始时间：<input id="loginTimeStart" type="text" name="loginTimeStart" class="easyui-datebox"
                                            style="width: 17%" data-options="prompt:'请选择'"/>
                                结束时间：<input id="loginTimeEnd" type="text" name="loginTimeEnd" class="easyui-datebox"
                                            style="width: 17%" data-options="prompt:'请选择'"/>
                                <a id="searchBtn" href="javascript:void(0);" class="easyui-linkbutton"
                                   iconCls="icon-search" onclick="">查询</a>
                                <a id="clearBtn" href="javascript:void(0);" class="easyui-linkbutton"
                                   iconCls="icon-clear" onclick="">清空</a>
                            </div>
                        </form>
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

<div class="modal fade" id="holidaySetModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 500px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title">
                    假期设置
                </h4>
            </div>
            <div class="modal-body">
                <div class="card" id="chart2" style="width:100%;height: 370px;display: none;margin-bottom: 0px">
                    <div id="holidaySetDatagrid"></div>
                    <div id="tb1" style="padding:5px">
                        <form id="holiAddForm" method="post">
                            <div>
                                类型：<select id="type" class="easyui-combobox" name="type" style="width: 35%" data-options="panelHeight:50">
                                          <option value="0">节假日</option>
                                          <option value="1">额外工作日</option>
                                     </select>
                                日期：<input id="dateTime" type="text" name="dateTime" class="easyui-datebox"
                                          style="width: 35%" data-options="prompt:'请选择'"/>
                                <a id="saveBtn" href="javascript:void(0);" class="easyui-linkbutton"
                                   iconCls="icon-add" onclick="">添加</a>
                            </div>
                        </form>
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


<div class="modal fade" id="departSetModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 800px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title">
                    部门薪资设置
                </h4>
            </div>
            <div class="modal-body">
                <div class="card" id="chart3" style="width:100%;height: 370px;display: none;margin-bottom: 0px">
                    <div id="departSetDatagrid"></div>
                    <div id="tb2" style="padding:5px">
                        <form id="departAddForm" method="post">
                            <input type="hidden" id="opType">
                            <input type="hidden" id="departId1">
                            <input type="hidden" id="departRand1">
                            <div>
                                部门编号：<input id="departId" type="text" name="departId" class="easyui-numberbox"
                                               data-options="prompt:'请输入',min:200000,max:299999" style="width: 12%"/>
                                部门名称：<input id="departName" type="text" name="departName" class="easyui-textbox"
                                               data-options="prompt:'请输入'" style="width: 13%"/>
                                薪资等级：<input id="departRand" type="text" name="departRand" class="easyui-numberbox"
                                               data-options="prompt:'例:等级3输入3',min:0,max:20,prefix:'等级'" style="width: 13%"/>
                                部门薪资：<input id="departWage" type="text" name="departWage" class="easyui-numberbox"
                                               data-options="prompt:'请输入',min:0,max:800000" style="width: 13%"/>
                                <a id="addDepartBtn" href="javascript:void(0);" class="easyui-linkbutton"
                                   iconCls="icon-save" onclick="">保存</a>
                                <a id="cancelBtn" href="javascript:void(0);" class="easyui-linkbutton"
                                   iconCls="icon-no" onclick="">取消</a>
                            </div>
                        </form>
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


<div class="modal fade" id="adminSetModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 600px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title">
                    管理员设置
                </h4>
            </div>
            <div class="modal-body" id="adatagrids">
                <div class="card" id="chart7">
                    <div id="adminIDatagrid"></div>
                </div>
                <div class="card" id="chart8">
                    <div id="adminNDatagrid"></div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<div class="modal fade" id="adminAuthSetModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 750px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title">
                    管理员权限设置
                </h4>
            </div>
            <div class="modal-body" id="datagrids">
                <div class="card" id="chart4">
                    <div id="adminDatagrid"></div>
                </div>
                <div class="card" id="chart5">
                    <div id="matchDatagrid"></div>
                </div>
                <div class="card" id="chart6">
                    <div id="noMatchDatagrid"></div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<div class="modal fade" id="resetPassModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 450px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title">
                    重置员工密码
                </h4>
            </div>
            <div class="modal-body">
                <div class="card" id="chart9" style="width:100%;height: 355px;display: none;margin-bottom: 0px">
                    <div id="resetPassDatagrid"></div>
                    <div id="tb3" style="padding:5px">
                        <form id="searchForm1" method="post">
                            <div>
                                员工编号/名称：<input id="empName1" type="text" name="empName" class="easyui-textbox"
                                               data-options="prompt:'请输入'" style="width: 36%"/>
                                <a id="searchBtn1" href="javascript:void(0);" class="easyui-linkbutton"
                                   iconCls="icon-search" onclick="">查询</a>
                            </div>
                        </form>
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


<div class="modal fade" id="newsSetModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 850px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title">
                    推荐新闻设置
                </h4>
            </div>
            <div class="modal-body">
                <div class="card" id="chart10" style="width:100%;height: 355px;display: none;margin-bottom: 0px">
                    <div id="newsSetDatagrid"></div>
                    <div id="tb4" style="padding:5px">
                        <div>
                            主题文字：<input id="themeText" type="text" name="themeText" class="easyui-textbox"
                                           data-options="prompt:'请输入25个中文以内'" style="width: 39%"/>
                            新闻外链接：<input id="newUrl" type="text" name="newUrl" class="easyui-textbox"
                                        data-options="prompt:'请输入'" style="width: 37%"/>
                            <input  type="hidden" id="index" >
                            <a id="finishNew" href="javascript:void(0);" class="easyui-linkbutton"
                               iconCls="icon-ok" onclick="" style="display: none" >完成</a>
                            <a id="addNew" href="javascript:void(0);" class="easyui-linkbutton"
                               iconCls="icon-add" onclick="">添加</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消
                </button>
                <button type="button" id="saveChange" class="btn btn-primary">
                    保存
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>


<div class="modal fade" id="newsImgSetModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 850px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title">
                    新闻滚轮图设置
                </h4>
            </div>
            <div class="modal-body">
                <div class="card" id="chart11" style="width:100%;height: 355px;display: none;margin-bottom: 0px">
                    <div id="newsImgSetDatagrid"></div>
                    <div id="tb5" style="padding:5px">
                        <div>
                            主题文字：<input id="themeText1" type="text" name="themeText" class="easyui-textbox"
                                        data-options="prompt:'请输入25个中文以内'" style="width: 49%;"/>
                            新闻外链接：<input id="newUrl1" type="text" name="newUrl" class="easyui-textbox"
                                         data-options="prompt:'请输入'" style="width: 27%"/>
                            <input  type="hidden" id="index1" >
                            <a id="finishNewImgs" href="javascript:void(0);" class="easyui-linkbutton"
                               iconCls="icon-ok" onclick="" style="display: none" >完成</a>
                            <a id="addNewImgs" href="javascript:void(0);" class="easyui-linkbutton"
                               iconCls="icon-add" onclick="">添加</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消
                </button>
                <button type="button" id="saveImgChange" class="btn btn-primary">
                    保存
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>


<div class="modal fade" id="newImgModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width:620px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title">
                    滚轮图修改
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
                <button id="addNetImg" type="button" class="btn btn-default">使用网络图片
                </button>
                <button type="button" class="btn btn-primary" data-dismiss="modal">完成
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<div class="modal fade" id="addNetImgModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width:420px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title">
                    请输入网络图片地址
                </h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12">
                        <input type="text" id="imgNetUrl" class="form-control"  placeholder="请输入网络地址" >
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button id="saveNetImg" type="button" class="btn btn-default" data-dismiss="modal">保存
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>