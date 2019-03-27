<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/common/include_js.jsp" %>
<html>
<head>
    <title>人员管理</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <jsp:include page="/WEB-INF/views/common/include_css.jsp"/>
    <link rel="stylesheet" href="${ctx}/static/css/admin/empinfo_manager.css">
</head>
<body >
<div class="main-panel" style="width: 100%">
    <div class="content">
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-8" id="cardparent">
                    <div class="card">
                        <div class="content">
                            <form  id="searchForm" method="post">
                                <input  type="hidden" id="empId" name = "empId">
                                <div class="row">
                                    <div class="col-md-2">
                                        <div class="form-group">
                                            <label>名字：</label>
                                            <input type="text"  id="empName" name="empName" class="form-control" placeholder="请输入名字">
                                        </div>
                                    </div>
                                    <div class="col-md-2">
                                        <div class="form-group">
                                            <label>性别：</label>
                                            <select id="empSex" name="empSex" class="form-control"  >
                                                <option value="">全部</option>
                                                <option value="男">男</option>
                                                <option value="女">女</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-md-2">
                                        <div class="form-group">
                                            <label>毕业院校：</label>
                                            <input type="text" id="empSchool" name="empSchool" class="form-control" placeholder="请输入毕业院校">
                                        </div>
                                    </div>
                                    <div class="col-md-2">
                                        <div class="form-group">
                                            <label>学历：</label>
                                            <select id="empEdu" name="empEdu" class="form-control" >
                                                <option value="">全部</option>
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

                                    <div class='col-sm-2'>
                                        <div class="form-group" >
                                            <label>入职时间：</label>
                                            <!--指定 date标记-->
                                            <input type="text" id="datepicker1" name="startTime" class="form-control" placeholder="请选择开始时间"></p>
                                        </div>
                                    </div>
                                    <div class='col-sm-2'>
                                        <div class="form-group">
                                            <!--指定 date标记-->
                                            <input type="text" id="datepicker2" name="endTime"  class="form-control" placeholder="请选择结束时间" style="margin-top: 23px;"></p>
                                        </div>
                                    </div>
                                </div>

                                <div class="row" >

                                    <div class="col-md-2">
                                        <div class="form-group">
                                            <label>创建人员：</label>
                                            <input type="text" id="createEmpid" name="createEmpname" class="form-control" placeholder="请输入创建人员">
                                        </div>
                                    </div>
                                    <div class="col-md-2">
                                        <div class="form-group">
                                            <label>部门：</label>
                                            <select id="departId" name="departId" class="form-control" >
                                                <option value="">全部</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-md-2">
                                        <div class="form-group">
                                            <label>薪资等级：</label>
                                            <select id="departRand" name="departRand" class="form-control" >
                                                <option value="">全部</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-md-2">
                                    </div>
                                    <div class="col-md-1">
                                        <button type="button" id="searchBtn" class="btn btn-info btn-fill pull-right" >
                                            搜索
                                        </button>
                                    </div>
                                    <div class="col-md-1">
                                        <button type="button" id="addBtn"  class="btn btn-info btn-fill pull-right" >
                                            新增
                                        </button>
                                    </div>
                                    <div class="col-md-1">
                                        <button type="button" id="clearBtn"  class="btn btn-info btn-fill pull-right" >
                                            清空
                                        </button>
                                    </div>
                                    <div class="col-md-1">
                                        <button type="button" id="SearchAllBtn"  class="btn btn-info btn-fill pull-right">
                                            全部
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>

                    <div class="card" style="height: 397px;margin-bottom: 0px" >
                        <table id="infodatagrid"></table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/views/includejsp/admin/modal/empinfo_modal.jsp"/>
<jsp:include page="/WEB-INF/views/common/include_javascript.jsp"/>
<script src="${ctx}/static/js-modules/lib/art-template/3.0.0/template.js"></script>
<script src="${ctx}/static/js/managerjs/empinfo_manager.js"></script>
</body>

</html>