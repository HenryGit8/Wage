<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>Bootstrap 101 Template</title>

    <!-- Bootstrap -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/Frame/jquery-2.0.3.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/js-modules/bootstrap-3.3.7/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath}/static/css/Frame/bootstrap-datetimepicker.css" rel="stylesheet" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js-modules/bootstrap-3.3.7/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js-modules/lib/bootstrap-datetimepicker.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js-modules/lib/moment-with-locales.js"></script>
    <script src="${pageContext.request.contextPath}/static/js-modules/lib/bootstrap-datetimepicker.zh-CN.js"></script>
</head>
<body>
<div class="row">
    <div class='col-sm-6'>
        <div class="form-group">
            <label>选择开始时间：</label>
            <!--指定 date标记-->
            <div class='input-group date' id='datetimepicker1'>
                <input type='text' class="form-control" />
                <span class="input-group-addon">
                    <span class="glyphicon glyphicon-calendar"></span>
                </span>
            </div>
        </div>
    </div>
    <div class='col-sm-6'>
        <div class="form-group">
            <label>选择结束时间：</label>
            <!--指定 date标记-->
            <div class='input-group date' id='datetimepicker2'>
                <input type='text' class="form-control" />
                <span class="input-group-addon">
                    <span class="glyphicon glyphicon-calendar"></span>
                </span>
            </div>
        </div>
    </div>
</div>
<script>
    $(function () {
        $('#datetimepicker1').datetimepicker({
            format: 'YYYY-MM-DD',
            locale: moment.locale('zh-cn'),
            //minDate: '2016-7-1'
        });
        $('#datetimepicker2').datetimepicker({
            format: 'YYYY-MM-DD',
            locale: moment.locale('zh-cn')
        });
        //动态设置最小值
        picker1.on('dp.change', function (e) {
            picker2.data('DateTimePicker').minDate(e.date);
        });
        //动态设置最大值
        picker2.on('dp.change', function (e) {
            picker1.data('DateTimePicker').maxDate(e.date);
        });
        $("#demo").datetimepicker({
            format: 'yyyy-mm-dd hh:ii'
        });
    });
</script>
</body>
</html>