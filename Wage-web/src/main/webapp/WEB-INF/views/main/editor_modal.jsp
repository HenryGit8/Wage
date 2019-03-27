<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="modal fade" id="noticeListModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 800px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title">
                    公告列表
                </h4>
            </div>
            <div class="modal-body">
                <div class="card" id="chart3" style="width:100%;height: 370px;margin-bottom: 0px">
                    <div id="noticeDatagrid"></div>
                    <div id="noticetb" style="padding:5px">
                        <div id="inputgroup">
                            <form id="searchNoticeForm" method="post">
                                姓名/编号：<input id="empName" type="text" name="empName"
                                             class="easyui-textbox"/>
                                开始时间：<input id="releaseTimeStart" type="text" name="releaseTimeStart"
                                            class="easyui-datebox"/>
                                结束时间：<input id="releaseTimeEnd" type="text" name="releaseTimeEnd"
                                            class="easyui-datebox"/>
                                <a id="searchBtn" href="javascript:void(0);" class="easyui-linkbutton"
                                   iconCls="icon-search" onclick="">查询</a>
                                <a id="searchAllBtn" href="javascript:void(0);" class="easyui-linkbutton"
                                   iconCls="icon-reload" onclick="">全部</a>
                                <a id="addBtn" href="javascript:void(0);" class="easyui-linkbutton"
                                   iconCls="icon-add" onclick="">新增</a>
                            </form>
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
<div class="modal fade" id="fabuModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 700px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabe2">
                    公告发布
                </h4>
            </div>
            <div class="modal-body">
                <div id="div1" class="toolbar">
                </div>
                <div style="padding: 5px 0; color: #ccc">内容</div>
                <div id="div2" class="text" > <!--可使用 min-height 实现编辑区域自动增加高度-->
                    <%--<p id="pbq">请输入内容...</p>--%>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
                <button type="button" id="sumbitNotice" class="btn btn-primary">
                    发布
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>


<div class="modal fade" id="updatefabuModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 700px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabe3">
                    修改公告
                </h4>
            </div>
            <div class="modal-body">
                <input  type="hidden" id="noticeId" name = "id">
                <div id="div3" class="toolbar">
                </div>
                <div style="padding: 5px 0; color: #ccc">内容</div>
                <div id="div4" class="text" > <!--可使用 min-height 实现编辑区域自动增加高度-->
                    <%--<p id="pbq">请输入内容...</p>--%>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
                <button type="button" id="updateNotice" class="btn btn-primary">
                    修改
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>


<div class="modal fade" id="seefabuModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 700px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <input  type="hidden" id="seenoticeId">
                <h4 class="modal-title" id="myModalLabe4">
                    查看公告
                </h4>
            </div>
            <div class="modal-body">
                <div id="div6" class="toolbar">
                </div>
                <div style="padding: 5px 0; color: #ccc">内容</div>
                <div id="div5" class="text" > <!--可使用 min-height 实现编辑区域自动增加高度-->
                    <%--<p id="pbq">请输入内容...</p>--%>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<div class="modal fade" id="authSetModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 300px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title">
                    员工查看权限设置
                </h4>
            </div>
            <div class="modal-body">
                <input  type="hidden" id="authnoticeId">
                <div class="card" id="chart10" style="width:100%;height: 390px;margin-bottom: 0px">
                    <div id="authDatagrid"></div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
                <button type="button" id="sumbitAuth" class="btn btn-primary">
                    保存
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>