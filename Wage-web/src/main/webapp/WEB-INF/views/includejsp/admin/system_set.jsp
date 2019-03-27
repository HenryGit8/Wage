<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/common/include_js.jsp" %>
<html>
<head>
    <title>系统管理</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <jsp:include page="/WEB-INF/views/common/include_css.jsp"/>
    <link rel="stylesheet" href="${ctx}/static/js-modules/bootstrap-3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/static/css/admin/system_set.css"/>
</head>
<body style="background:#f1f1f1;padding: 35px">
<div id="particles-js" style="position: absolute;top: 0px;left: 0px;width: 100%;height: 100%;z-index:0"></div>
<div style="width:630px;margin-left: auto; margin-right: auto;">
    <div class="panel panel-default">
        <ul class="list-group">
            <li class="list-group-item" style="font-size: 15px;background:#676868 ;color: white">系统设置</li>
        </ul>
    </div>
    <label>工作时间设置</label>
    <div class="panel panel-default">
        <ul class="list-group">
            <li class="list-group-item">每星期工作日(数字逗号分隔)
                <span class="glyphicon glyphicon-pencil" onclick="showDivState(0)"></span>
                <input id="input0" type="text" class="form-control">
            </li>
            <li class="list-group-item">①每日工作开始时间(单位:时)
                <span class="glyphicon glyphicon-pencil" onclick="showDivState(1)"></span>
                <input id="input1" type="text" class="form-control">
            </li>
            <li class="list-group-item">①每日工作结束时间(单位:时)
                <span class="glyphicon glyphicon-pencil" onclick="showDivState(2)"></span>
                <input id="input2" type="text" class="form-control">
            </li>
            <li class="list-group-item">②每日工作开始时间(单位:时)
                <span class="glyphicon glyphicon-pencil" onclick="showDivState(3)"></span>
                <input id="input3" type="text" class="form-control">
            </li>
            <li class="list-group-item">②每日工作结束时间(单位:时)
                <span class="glyphicon glyphicon-pencil" onclick="showDivState(4)"></span>
                <input id="input4" type="text" class="form-control">
            </li>
        </ul>
    </div>

    <label>加班费倍数设置</label>
    <div class="panel panel-default">
        <ul class="list-group">
            <li class="list-group-item">工作日加班费倍数
                <span class="glyphicon glyphicon-pencil" onclick="showDivState(5)"></span>
                <input id="input5" type="text" class="form-control">
            </li>
            <li class="list-group-item">休息日加班费倍数
                <span class="glyphicon glyphicon-pencil" onclick="showDivState(6)"></span>
                <input id="input6" type="text" class="form-control">
            </li>
            <li class="list-group-item">节假日加班费倍数
                <span class="glyphicon glyphicon-pencil" onclick="showDivState(7)"></span>
                <input id="input7" type="text" class="form-control">
            </li>
        </ul>
    </div>

    <label>税率设置</label>
    <div class="panel panel-default">
        <ul class="list-group">
            <li class="list-group-item">起征点(￥)
                <span class="glyphicon glyphicon-pencil" onclick="showDivState(8)"></span>
                <input id="input8" type="text" class="form-control">
            </li>
            <li class="list-group-item">税率0-1500
                <span class="glyphicon glyphicon-pencil" onclick="showDivState(9)"></span>
                <input id="input9" type="text" class="form-control">
            </li>
            <li class="list-group-item">税率1500-4500
                <span class="glyphicon glyphicon-pencil" onclick="showDivState(10)"></span>
                <input id="input10" type="text" class="form-control">
            </li>
            <li class="list-group-item">税率4500-9000
                <span class="glyphicon glyphicon-pencil" onclick="showDivState(11)"></span>
                <input id="input11" type="text" class="form-control">
            </li>
            <li class="list-group-item">税率9000-35000
                <span class="glyphicon glyphicon-pencil" onclick="showDivState(12)"></span>
                <input id="input12" type="text" class="form-control">
            </li>
            <li class="list-group-item">税率35000-55000
                <span class="glyphicon glyphicon-pencil" onclick="showDivState(13)"></span>
                <input id="input13" type="text" class="form-control">
            </li>
            <li class="list-group-item">税率55000-80000
                <span class="glyphicon glyphicon-pencil" onclick="showDivState(14)"></span>
                <input id="input14" type="text" class="form-control">
            </li>
            <li class="list-group-item">税率>80000
                <span class="glyphicon glyphicon-pencil" onclick="showDivState(15)"></span>
                <input id="input15" type="text" class="form-control">
            </li>
        </ul>
    </div>

    <label>五险一金百分比设置(小数形式)</label>
    <div class="panel panel-default">
        <ul class="list-group">
            <li class="list-group-item">养老保险
                <span class="glyphicon glyphicon-pencil" onclick="showDivState(16)"></span>
                <input id="input16" type="text" class="form-control">
            </li>
            <li class="list-group-item">医疗保险
                <span class="glyphicon glyphicon-pencil" onclick="showDivState(17)"></span>
                <input id="input17" type="text" class="form-control">
            </li>
            <li class="list-group-item">失业保险
                <span class="glyphicon glyphicon-pencil" onclick="showDivState(18)"></span>
                <input id="input18" type="text" class="form-control">
            </li>
            <li class="list-group-item">基本住房公积金
                <span class="glyphicon glyphicon-pencil" onclick="showDivState(19)"></span>
                <input id="input19" type="text" class="form-control">
            </li>
            <li class="list-group-item">养老保险(单位)
                <span class="glyphicon glyphicon-pencil" onclick="showDivState(20)"></span>
                <input id="input20" type="text" class="form-control">
            </li>
            <li class="list-group-item">医疗保险(单位)
                <span class="glyphicon glyphicon-pencil" onclick="showDivState(21)"></span>
                <input id="input21" type="text" class="form-control">
            </li>
            <li class="list-group-item">失业保险(单位)
                <span class="glyphicon glyphicon-pencil" onclick="showDivState(22)"></span>
                <input id="input22" type="text" class="form-control">
            </li>
            <li class="list-group-item">工伤保险(单位)
                <span class="glyphicon glyphicon-pencil" onclick="showDivState(23)"></span>
                <input id="input23" type="text" class="form-control">
            </li>
            <li class="list-group-item">生育保险(单位)
                <span class="glyphicon glyphicon-pencil" onclick="showDivState(24)"></span>
                <input id="input24" type="text" class="form-control">
            </li>
            <li class="list-group-item">基本住房公积金(单位)
                <span class="glyphicon glyphicon-pencil" onclick="showDivState(25)"></span>
                <input id="input25" type="text" class="form-control">
            </li>
            <input type="hidden" id="input26">
            <input type="hidden" id="input27">
            <input type="hidden" id="input28">
            <input type="hidden" id="input29">
            <input type="hidden" id="input30">
            <input type="hidden" id="input31">
            <input type="hidden" id="input32">
        </ul>
    </div>

    <label>其他设置</label>
    <div class="panel panel-default">
        <ul class="list-group">
            <li id="holidaySet" class="list-group-item" href="javascript:void(0);" style="cursor:pointer">假期设置
                <span class="glyphicon glyphicon-globe"></span>
            </li>
            <li id="departSet" class="list-group-item" href="javascript:void(0);" style="cursor:pointer">部门薪资设置
                <span class="glyphicon glyphicon-usd"></span>
            </li>
            <li id="adminSet" class="list-group-item" href="javascript:void(0);" style="cursor:pointer">管理员设置
                <span class="glyphicon glyphicon-user"></span>
            </li>
            <li id="adminAuthSet" class="list-group-item" href="javascript:void(0);" style="cursor:pointer">管理员权限设置
                <span class="glyphicon glyphicon-cog"></span>
            </li>
            <li id="newsSet" class="list-group-item" href="javascript:void(0);" style="cursor:pointer">员工推荐新闻设置
                <span class="glyphicon glyphicon-list-alt"></span>
            </li>
            <li id="newsImgSet" class="list-group-item" href="javascript:void(0);" style="cursor:pointer">员工新闻滚轮图设置
                <span class="glyphicon glyphicon-picture"></span>
            </li>
        </ul>
    </div>

    <label>查看</label>
    <div class="panel panel-default">
        <ul class="list-group">
            <li id="toLoginHis" class="list-group-item" href="javascript:void(0);" style="cursor:pointer">登录记录
                <span class="glyphicon glyphicon-eye-open"></span>
            </li>
        </ul>
    </div>

    <label>重置</label>
    <div class="panel panel-default">
        <ul class="list-group">
            <li id="toDefault" class="list-group-item" style="cursor:pointer">将设置还原为默认值(不包含其他设置)
                <span class="glyphicon glyphicon-repeat"></span>
            </li>
            <li id="resetEmpPass" class="list-group-item" href="javascript:void(0);" style="cursor:pointer">重置员工密码
                <span class="glyphicon glyphicon-wrench"></span>
            </li>
        </ul>
    </div>

    <button id="cancel" type="button" class="btn btn-default btn-lg "
            style="height: 40px;font-size: 14px;position: relative;left: 495px;box-shadow:0px 3px  10px 0.1px rgba(0, 0, 0, 0.2)">
        取消
    </button>
    <button id="save" type="button" class="btn btn-primary btn-lg "
            style="height: 40px;font-size: 14px;position: relative;left: 503px;box-shadow:0px 3px  10px 0.1px rgba(0, 0, 0, 0.2)">
        保存
    </button>
</div>
<jsp:include page="/WEB-INF/views/includejsp/admin/modal/system_modal.jsp"/>
<jsp:include page="/WEB-INF/views/common/include_javascript.jsp"/>
<script src="${jsModulesRoot}/lib/particles/particles.js"></script>
<script src="${jsModulesRoot}/lib/particles/appset.js"></script>
<script src="${ctx}/static/js/managerjs/system_set.js"></script>
</body>
</html>

