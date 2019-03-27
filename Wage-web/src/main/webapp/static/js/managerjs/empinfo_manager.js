$(document).ready(function () {
    new PageInit().init();
    new PageInit1().init();
    toastr.options.positionClass = 'toast-top-right';
    init();
});

$('#infodatagrid').datagrid({
    url: ctx + "/empInfo/getBySearch",
    idField: 'empId',
    striped: true,
    singleSelect: true,
    pageSize: 20,
    border: false,
    pagination: true,
    queryParams: $("#searchForm").serializeObject(),
    columns: [[
        {
            field: 'empId',
            title: '员工编号',
            width: 70,
            align: 'center'
        },
        {
            field: 'empName',
            title: '姓名',
            width: 70,
            align: 'center'
        },
        {
            field: 'empSex',
            title: '性别',
            width: 40,
            align: 'center'
        },
        {
            field: 'empEdu',
            title: '学历',
            width: 80,
            align: 'center'
        },
        {
            field: 'empSchool',
            title: '毕业院校',
            width: 100,
            align: 'center'
        },
        {
            field: 'departName',
            title: '部门',
            width: 80,
            align: 'center'
        },
        {
            field: 'departRand',
            title: '薪资等级',
            width: 80,
            formatter: function(value,row,index){
                if(row.departRand == null){
                    return '';
                }else {
                    return '等级'+row.departRand;
                }
            },
            align: 'center'
        },
        {
            field: 'createTime',
            title: '入职时间',
            width: 100,
            sortable:true,
            align: 'center'
        },
        {
            field: 'createEmpName',
            title: '创建人员',
            width: 80,
            align: 'center'
        },
        {
            field:'operate',title:'操作',align:'center',width:60,
            formatter:function(value, row, index){
                var str = '<a href="javascript:;" onclick="updateOnClick('+index+')" name="update" class="easyui-linkbutton" ></a><a href="javascript:;" onclick="operateOnClick('+row.empId +')" name="delete" class="easyui-linkbutton" ></a>';
                return str;
            }
        }
    ]],
    fitColumns: 'true',
    fit:true,
    striped: 'true',
    onLoadSuccess:function(data){
        $("a[name='update']").linkbutton({plain:true,iconCls:'icon-edit'});
        $("a[name='delete']").linkbutton({plain:true,iconCls:'icon-remove'});
    },
    title:'员工基本信息表'
});

$("#searchBtn").click(function () {
    var data = $("#searchForm").serializeObject();
    $('#infodatagrid').datagrid('load', data);
});

function operateOnClick(id) {
    Ewin.confirm({ message: "确认删除此员工的所有信息吗？" }).on(function (e) {
        if (!e) {
            return;
        }else{
            $.ajax({
                type: "GET",
                url: ctx + "/empInfo/removeInfo?empId="+id,
                dataType: "json",
                success: function (result) {
                    toastr.success('删除'+result.message);
                    $('#infodatagrid').datagrid('reload');
                },
                error:function (result) {
                    toastr.error('删除失败，请联系管理员！');
                }
            });
        }
    });
};

$("#addBtn").click(function () {
    $('#saveForm')[0].reset();
    $('#myModal').modal('show');
});

$("#clearBtn").click(function () {
    $("#searchForm")[0].reset();
});

$("#SearchAllBtn").click(function () {
    $("#searchForm")[0].reset();
    var data = $("#searchForm").serializeObject();
    $('#infodatagrid').datagrid('load', data);
});

$("#sumbitSave").click(function () {
    if (checkform()) {
        $.ajax({
            type: "POST",
            url: ctx + "/empInfo/saveInfo",
            dataType: "json",
            data: $("#saveForm").serializeObject(),
            success: function (result) {
                toastr.success('保存' + result.message);
                $('#infodatagrid').datagrid('reload');
                $('#myModal').modal('hide');
            },
            error: function (result) {
                toastr.error('保存失败，请联系管理员！');
            }
        });
    }
});

$("#sumbitUpdate").click(function () {
    if (checkform2()) {
        $.ajax({
            type: "POST",
            url: ctx + "/empInfo/updateInfo",
            dataType: "json",
            data: $("#updateForm").serializeObject(),
            success: function (result) {
                toastr.success('保存'+result.message);
                $('#infodatagrid').datagrid('reload');
                $('#myModal2').modal('hide');
            },
            error:function (result) {
                toastr.error('保存失败，请联系管理员！');
            }
        });
    }
});

function updateOnClick(index) {
    $('#updateForm')[0].reset();
    var row = $('#infodatagrid').datagrid('getData').rows[index];
    $.ajax({
        type : "POST", //使用post方法访问后台
        dataType : "json", //返回json格式的数据
        url: ctx + "/login/getEmpImgMd", //要访问的后台地址
        data : {
            "empId":row.empId
        },
        success : function(result) {//result为返回的数据
            var img = $('#headImg2');
            img.removeAttr('src');
            img.attr('src', ctx + "/file/downloadFile?md5="+result);
        }
    });
    $("#empId2").val(row.empId);
    $("#empName2").val(row.empName);
    $("#empSex2").val(row.empSex);
    $("#empTell2").val(row.empTell);
    $("#empIdnum2").val(row.empIdnum);
    $("#empAddress2").val(row.empAddress);
    $("#empSchool2").val(row.empSchool);
    $("#empEdu2").val(row.empEdu);
    $("#empBankCard2").val(row.empBankCard);
    $("#departId2").val(row.departId);
    $.ajax({
        type : "POST", //使用post方法访问后台
        dataType : "json", //返回json格式的数据
        url: ctx + "/empInfo/getDepartMap", //要访问的后台地址
        data : {
            "departId": row.departId
        },
        success : function(result) {//result为返回的数据
            var randIdList = result["randIdList"];
            $("#departRand2").html("");
            $("#departRand2").append($("<option value=\"\">请选择</option>"));
            for(var i=0; i<randIdList.length; i++){
                $("#departRand2").append($("<option value=\""+randIdList[i]+"\">"+"等级"+randIdList[i]+"</option>"));
            }
            $("#departRand2").val(row.departRand);
        }
    });
    $('#myModal2').modal('show');
};

function checkform() {
    if($("#empName1").val().length < 1){
        toastr.error('请输入姓名！');
        return false;
    }
    if(!/^[\u4E00-\u9FA5]{2,5}$/.test($("#empName1").val())){
        toastr.error('姓名不合法！');
        return false;
    }
    if($("#empSex1").val().length < 1){
        toastr.error('请选择性别！');
        return false;
    }
    if($("#empTell1").val().length < 1){
        toastr.error('请输入联系电话！');
        return false;
    }
    if(!/^1\d{10}$/.test($("#empTell1").val())){
        toastr.error('联系电话必须为11位数字！');
        return false;
    }
    if($("#empIdnum1").val().length < 1){
        toastr.error('请输入身份证号！');
        return false;
    }
    if(!/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/.test($("#empIdnum1").val())){
        toastr.error('身份证号不合法！');
        return false;
    }
    if($("#empAddress1").val().length > 50){
        toastr.error('地址过长！');
        return false;
    }
    if($("#empSchool1").val().length > 20){
        toastr.error('学校名称过长！');
        return false;
    }
    if($("#empBankCard1").val().length < 1){
        toastr.error('请输入银行卡号！');
        return false;
    }
    if(/[^\d]/.test($("#empBankCard1").val())){
        toastr.error('银行卡号必须是数字！');
        return false;
    }else if($("#empBankCard1").val().length > 30){
        toastr.error('银行卡号过长！');
        return false;
    }
    if($("#empBasicPay").val().length < 1){
        toastr.error('请输入基础工资！');
        return false;
    }
    if($("#departId1").val().length < 1){
        toastr.error('请选择部门！');
        return false;
    }
    if($("#departRand1").val().length < 1){
        toastr.error('请选择薪资等级！');
        return false;
    }else {
        return true;
    }

};

function checkform2() {
    if($("#empName2").val().length < 1){
        toastr.error('请输入姓名！');
        return false;
    }
    if(!/^[\u4E00-\u9FA5]{2,5}$/.test($("#empName2").val())){
        toastr.error('姓名不合法！');
        return false;
    }
    if($("#empSex2").val().length < 1){
        toastr.error('请选择性别！');
        return false;
    }
    if($("#empTell2").val().length < 1){
        toastr.error('请输入联系电话！');
        return false;
    }
    if(!/^1\d{10}$/.test($("#empTell2").val())){
        toastr.error('联系电话必须为11位数字！');
        return false;
    }
    if($("#empIdnum2").val().length < 1){
        toastr.error('请输入身份证号！');
        return false;
    }
    if(!/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/.test($("#empIdnum2").val())){
        toastr.error('身份证号不合法！');
        return false;
    }
    if($("#empAddress2").val().length > 50){
        toastr.error('地址过长！');
        return false;
    }
    if($("#empSchool2").val().length > 20){
        toastr.error('学校名称过长！');
        return false;
    }
    if($("#empBankCard2").val().length < 1){
        toastr.error('请输入银行卡号！');
        return false;
    }
    if(/[^\d]/.test($("#empBankCard2").val())){
        toastr.error('银行卡号必须是数字！');
        return false;
    }else if($("#empBankCard2").val().length > 30){
        toastr.error('银行卡号过长！');
        return false;
    }
    if($("#departId2").val().length < 1){
        toastr.error('请选择部门！');
        return false;
    }
    if($("#departRand2").val().length < 1){
        toastr.error('请选择薪资等级！');
        return false;
    }else {
        return true;
    }
};

laydate.render({
    elem: '#datepicker1'
    ,type: 'date'
    ,theme: DATA_COLOR.BLACK
});

laydate.render({
    elem: '#datepicker2'
    ,type: 'date'
    ,theme: DATA_COLOR.BLACK
});

/**
 *初始化页面
 */
function init(){
    $.ajax({
        type : "POST", //使用post方法访问后台
        dataType : "json", //返回json格式的数据
        url: ctx + "/empInfo/getDepartMap", //要访问的后台地址
        success : function(result) {//result为返回的数据
            var departNameList = result["departNameList"];
            var departIdList = result["departIdList"];
            $("#departId1").html("");
            $("#departId1").append($("<option value=\"\">请选择</option>"));
            for(var i=0; i<departIdList.length; i++){
                $("#departId1").append($("<option value=\""+departIdList[i]+"\">"+departNameList[i]+"</option>"));
            }
            $("#departId").html("");
            $("#departId").append($("<option value=\"\">全部</option>"));
            for(var i=0; i<departIdList.length; i++){
                $("#departId").append($("<option value=\""+departIdList[i]+"\">"+departNameList[i]+"</option>"));
            }
            $("#departId2").html("");
            $("#departId2").append($("<option value=\"\">请选择</option>"));
            for(var i=0; i<departIdList.length; i++){
                $("#departId2").append($("<option value=\""+departIdList[i]+"\">"+departNameList[i]+"</option>"));
            }
        }
    });
}
$('#departId').change(function(){
    var p1=$(this).children('option:selected').val();//这就是selected的值
    $.ajax({
        type : "POST", //使用post方法访问后台
        dataType : "json", //返回json格式的数据
        url: ctx + "/empInfo/getDepartMap", //要访问的后台地址
        data : {
            "departId": p1
        },
        success : function(result) {//result为返回的数据
            var randIdList = result["randIdList"];
            $("#departRand").html("");
            $("#departRand").append($("<option value=\"\">全部</option>"));
            for(var i=0; i<randIdList.length; i++){
                $("#departRand").append($("<option value=\""+randIdList[i]+"\">"+"等级"+randIdList[i]+"</option>"));
            }
        }
    });
});
$('#departId1').change(function(){
    var p1=$(this).children('option:selected').val();//这就是selected的值
    $.ajax({
        type : "POST", //使用post方法访问后台
        dataType : "json", //返回json格式的数据
        url: ctx + "/empInfo/getDepartMap", //要访问的后台地址
        data : {
            "departId": p1
        },
        success : function(result) {//result为返回的数据
            var randIdList = result["randIdList"];
            $("#departRand1").html("");
            $("#departRand1").append($("<option value=\"\">请选择</option>"));
            for(var i=0; i<randIdList.length; i++){
                $("#departRand1").append($("<option value=\""+randIdList[i]+"\">"+"等级"+randIdList[i]+"</option>"));
            }
        }
    });
});
$('#departId2').change(function(){
    var p1=$(this).children('option:selected').val();//这就是selected的值
    $.ajax({
        type : "POST", //使用post方法访问后台
        dataType : "json", //返回json格式的数据
        url: ctx + "/empInfo/getDepartMap", //要访问的后台地址
        data : {
            "departId": p1
        },
        success : function(result) {//result为返回的数据
            var randIdList = result["randIdList"];
            $("#departRand2").html("");
            $("#departRand2").append($("<option value=\"\">请选择</option>"));
            for(var i=0; i<randIdList.length; i++){
                $("#departRand2").append($("<option value=\""+randIdList[i]+"\">"+"等级"+randIdList[i]+"</option>"));
            }
        }
    });
});


function PageInit() {
    var api = null;
    var _this = this;
    this.init = function () {
        $("#headImg1").on('click', this.portraitUpload)
    };

    this.portraitUpload = function () {
        var img = $('#cut-img');
        setModalCenter('imgModal');
        $('#imgModal').modal('show');
        img.removeAttr('src');
        img.attr('src', $('#headImg1')[0].src);
        var fileUp = new FileUpload();
        var portrait = $('#fileUpload');
        var alert = $('#alert');
        fileUp.portrait(portrait, ctx + "/file/uploadFile", _this.getExtraData);
        portrait.on('change', _this.readURL);
        portrait.on('fileuploaderror', function (event, data, msg) {
            setModalCenter('imgModal');
            alert.removeClass('hidden').html(msg);
            fileUp.fileinput('disable');
        });
        portrait.on('fileclear', function (event) {
            alert.addClass('hidden').html();
            img.removeAttr('src');
            setModalCenter('imgModal');
        });
        portrait.on('fileloaded', function (event, file, previewId, index, reader) {
            setModalCenter('imgModal');
            alert.addClass('hidden').html();
        });
        portrait.on('fileuploaded', function (event, data) {
            setModalCenter('imgModal');
            if (!data.response.status) {
                alert.html(data.response.message).removeClass('hidden');
            }
            var img = $('#headImg1');
            img.removeAttr('src');
            img.attr('src', ctx + "/file/downloadFile?md5="+data.response.object);
            $('#headMd1').val(data.response.object);
        })
    };

    this.readURL = function () {
        var img = $('#cut-img');
        var input = $('#fileUpload');
        if (input[0].files && input[0].files[0]) {
            var reader = new FileReader();
            reader.readAsDataURL(input[0].files[0]);
            reader.onload = function (e) {
                img.removeAttr('src');
                img.attr('src', e.target.result);
            };
            if (api != undefined) {
                api.destroy();
            }
        }
    };

    this.getExtraData = function () {
        return {
            sw: $('.jcrop-holder').css('width'),
            sh: $('.jcrop-holder').css('height'),
            x: $('#x').val(),
            y: $('#y').val(),
            w: $('#w').val(),
            h: $('#h').val()
        }
    }
}

function FileUpload() {
    //start
    var header = $("meta[name='_csrf_header']").attr("content");
    var token = $("meta[name='_csrf']").attr("content");
    //end
    this.portrait = function (target, uploadUrl, data) {
        target.fileinput({
            language: 'zh', //设置语言
            maxFileSize:20480,//文件最大容量
            /*uploadExtraData: data,//上传时除了文件以外的其他额外数据*/
            showPreview: false,//隐藏预览
            uploadAsync: true,//ajax同步
            /*dropZoneEnabled: false,//是否显示拖拽区域*/
            uploadUrl: uploadUrl, //上传的地址
            allowedFileExtensions: ['jpg', 'gif', 'png', 'bmp'],//接收的文件后缀
            showUpload: true, //是否显示上传按钮
            showCaption: true,//是否显示标题
            browseClass: "btn btn-primary", //按钮样式
            previewFileIcon: "<i class='glyphicon glyphicon-king'></i>"
        });
    }
}

function PageInit1() {
    var api = null;
    var _this = this;
    this.init = function () {
        $("#headImg2").on('click', this.portraitUpload)
    };

    this.portraitUpload = function () {
        var img = $('#cut-img1');
        setModalCenter('imgModal1');
        $('#imgModal1').modal('show');
        img.removeAttr('src');
        img.attr('src', $('#headImg2')[0].src);
        var fileUp = new FileUpload1();
        var portrait = $('#fileUpload1');
        var alert = $('#alert1');
        fileUp.portrait(portrait, ctx + "/file/uploadFile", _this.getExtraData);
        portrait.on('change', _this.readURL);
        portrait.on('fileuploaderror', function (event, data, msg) {
            alert.removeClass('hidden').html(msg);
            fileUp.fileinput('disable');
        });
        portrait.on('fileclear', function (event) {
            alert.addClass('hidden').html();
            img.removeAttr('src');
            setModalCenter('imgModal1');
        });
        portrait.on('fileloaded', function (event, file, previewId, index, reader) {
            setModalCenter('imgModal1');
            alert.addClass('hidden').html();
        });
        portrait.on('fileuploaded', function (event, data) {
            setModalCenter('imgModal1');
            if (!data.response.status) {
                alert.html(data.response.message).removeClass('hidden');
            }
            $.ajax({
                type : "POST", //使用post方法访问后台
                dataType : "json", //返回json格式的数据
                url: ctx + "/login/saveEmpImgMd", //要访问的后台地址
                data : {
                    "empHeadImg": data.response.object,
                    "empId":$('#empId2').val()
                },
                success : function(result) {//result为返回的数据
                    var img = $('#headImg2');
                    img.removeAttr('src');
                    img.attr('src', ctx + "/file/downloadFile?md5="+data.response.object);
                }
            });
        })
    };

    this.readURL = function () {
        var img = $('#cut-img1');
        var input = $('#fileUpload1');
        if (input[0].files && input[0].files[0]) {
            var reader = new FileReader();
            reader.readAsDataURL(input[0].files[0]);
            reader.onload = function (e) {
                img.removeAttr('src');
                img.attr('src', e.target.result);
            };
            if (api != undefined) {
                api.destroy();
            }
        }
    };

    this.getExtraData = function () {
        return {
            sw: $('.jcrop-holder').css('width'),
            sh: $('.jcrop-holder').css('height'),
            x: $('#x').val(),
            y: $('#y').val(),
            w: $('#w').val(),
            h: $('#h').val()
        }
    }
}

function FileUpload1() {
    //start
    var header = $("meta[name='_csrf_header']").attr("content");
    var token = $("meta[name='_csrf']").attr("content");
    //end
    this.portrait = function (target, uploadUrl, data) {
        target.fileinput({
            language: 'zh', //设置语言
            maxFileSize:20480,//文件最大容量
            /*uploadExtraData: data,//上传时除了文件以外的其他额外数据*/
            showPreview: false,//隐藏预览
            uploadAsync: true,//ajax同步
            /*dropZoneEnabled: false,//是否显示拖拽区域*/
            uploadUrl: uploadUrl, //上传的地址
            allowedFileExtensions: ['jpg', 'gif', 'png', 'bmp'],//接收的文件后缀
            showUpload: true, //是否显示上传按钮
            showCaption: true,//是否显示标题
            browseClass: "btn btn-primary", //按钮样式
            previewFileIcon: "<i class='glyphicon glyphicon-king'></i>"
        });
    }
}

