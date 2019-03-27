$(function () {
    new PageInit().init();
    loadHead();
    loadedition();
    loadUpdateEdition();
    loadSeeEdition();
    disptime();
});

function openjsp(url) {
    parent.document.getElementById("iframe").src=ctx + "/jump/"+url;
    $("#nav li").attr("class","");
    $("#"+url).attr("class", "active");
}

function goWelcome() {
    parent.document.getElementById("iframe").src=ctx + "/jump/welcome";
    $("#nav li").attr("class","");
}

function openModal() {
    $.ajax({
        type : "POST", //使用post方法访问后台
        dataType : "json", //返回json格式的数据
        url: ctx + "/login/getEmpImgMd", //要访问的后台地址
        success : function(result) {//result为返回的数据
            loadInfoModal();
            var img = $('#headImg');
            img.removeAttr('src');
            img.attr('src', ctx + "/file/downloadFile?md5="+result);
            $('#accoutSetModal').modal('show');
        }
    });
}

function loadHead() {
    $.ajax({
        type : "POST", //使用post方法访问后台
        dataType : "json", //返回json格式的数据
        url: ctx + "/login/getEmpImgMd", //要访问的后台地址
        success : function(result) {//result为返回的数据
            var img = $('#head');
            img.removeAttr('src');
            img.attr('src', ctx + "/file/downloadFile?md5="+result);
        }
    });
}

function loadInfoModal() {
    $.ajax({
        type : "POST", //使用post方法访问后台
        dataType : "json", //返回json格式的数据
        url: ctx + "/login/getLoginInfo", //要访问的后台地址
        success : function(result) {//result为返回的数据
            $("#empName2").val(result.empName);
            $("#empSex2").val(result.empSex);
            $("#empTell2").val(result.empTell);
            $("#empIdnum2").val(result.empIdnum);
            $("#empAddress2").val(result.empAddress);
            $("#empSchool2").val(result.empSchool);
            $("#empEdu2").val(result.empEdu);
        }
    });
}

function PageInit() {
    var api = null;
    var _this = this;
    this.init = function () {
        $("#headImg").on('click', this.portraitUpload)
    };

    this.portraitUpload = function () {
        var img = $('#cut-img');
        setModalCenter('imgModal');
        $('#imgModal').modal('show');
        img.removeAttr('src');
        img.attr('src', $('#headImg')[0].src);
        var fileUp = new FileUpload();
        var portrait = $('#fileUpload');
        var alert = $('#alert');
        fileUp.portrait(portrait, ctx + "/file/uploadFile", _this.getExtraData);
        portrait.on('change', _this.readURL);
        portrait.on('fileuploaderror', function (event, data, msg) {
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
            $.ajax({
                type : "POST", //使用post方法访问后台
                dataType : "json", //返回json格式的数据
                url: ctx + "/login/saveEmpImgMd", //要访问的后台地址
                data : {
                    "empHeadImg": data.response.object
                },
                success : function(result) {//result为返回的数据
                    var img = $('#headImg');
                    img.removeAttr('src');
                    img.attr('src', ctx + "/file/downloadFile?md5="+data.response.object);
                    var img1 = $('#head');
                    img1.removeAttr('src');
                    img1.attr('src', ctx + "/file/downloadFile?md5="+data.response.object);
                }
            });
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

function refresh() {
    window.location.reload();
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
function updateOnClick() {
    if (checkform2()) {
        $.ajax({
            type: "POST",
            url: ctx + "/empInfo/updateLoginInfo",
            dataType: "json",
            data: $("#updateForm").serializeObject(),
            success: function (result) {
                toastr.success('保存'+result.message);
                $('#accoutSetModal').modal('hide');
            },
            error:function (result) {
                toastr.error('保存失败，请联系管理员！');
            }
        });
    }
}

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
    }else {
        return true;
    }
};

function openPassModal() {
    setModalCenter('passwordSet');
    $("#checkCodeImg").attr("src",ctx +"/getVerify?"+Math.random());
    $('#passwordSet').modal('show');
}


/*
//校验验证码
function checkSum(){
    var inputStr = $(".check_input").val();
    if(inputStr!=null && inputStr!=""){
        inputStr = inputStr.toUpperCase();//将输入的字母全部转换成大写
        $.ajax({
            url : "checkVerify",
            data: {inputStr:inputStr},
            success : function(datas) {
                if(datas == "T"){
                    submitForm();//提交表单
                }else{
                    $(".check_input").val("");
                    $(".warn_text").text("验证码输入错误！");
                    $(".login_form_warn").css("display","block");
                }
            }
        });
    }else{
        $(".warn_text").text("请输入验证码");
        $(".login_form_warn").css("display","block");
    }
}*/


function passUpdateOnclick() {
    if($("#oldPassword").val().length < 1){
        toastr.error('请输入原密码！');
        return false;
    }
    if($("#newPassword1").val().length < 1){
        toastr.error('请输入新密码！');
        return false;
    }
    if($("#newPassword2").val().length < 1){
        toastr.error('请输入确认密码！');
        return false;
    }
    if($("#questionType").val().length < 1){
        toastr.error('请选择验证问题！');
        return false;
    }
    if($("#questionValue").val().length < 1){
        toastr.error('请输入验证问题答案！');
        return false;
    }
    if($("#checkCode").val().length < 1){
        toastr.error('请输入验证码！');
        return false;
    }
    if(!/^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,21}$/.test($("#newPassword1").val())){
        toastr.error('新密码格式错误！');
        return false;
    }
    if($("#newPassword1").val() != $("#newPassword2").val()){
        toastr.error('两次输入的密码不一致！');
        return false;
    }else {
        $.ajax({
            type: "POST",
            url: ctx + "/login/updatePassword",
            dataType: "json",
            data: $("#updatePassForm").serializeObject(),
            success: function (result) {
                if(result == 0){
                    toastr.success('修改成功！');
                    $('#passwordSet').modal('hide');
                }else if(result == 1){
                    toastr.error('原密码错误！');
                }else if(result == 2){
                    toastr.error('问题答案错误！');
                }else if(result == 3){
                    toastr.error('验证码错误！');
                }
            },
            error:function (result) {
                toastr.error('系统异常，请联系管理员！');
            }
        });
    }
}

function changeQuestion(id,text,va) {
    chageInner(id,text);
    $("#questionType").val(va);
}

function clearPassForm() {
    $("#oldPassword").val("");
    $("#newPassword1").val("");
    $("#newPassword2").val("");
    $("#questionType").val("");
    $("#questionValue").val("");
    $("#checkCode").val("");
}


function openNoticeModal() {
    setModalCenter('fabuModal');
    $('#fabuModal').modal('show');
}

function loadedition() {
    var E = window.wangEditor;
    var editor = new E('#div1', '#div2') ; // 两个参数也可以传入 elem 对象，class 选择器
    editor.customConfig.uploadImgServer = ctx + "/file/getDownlaodResult";
    editor.customConfig.uploadImgMaxSize = 20 * 1024 * 1024;
    editor.customConfig.uploadImgMaxLength = 5;
    editor.customConfig.uploadImgHooks = {
        before: function (xhr, editor, files) {
            // 图片上传之前触发
            // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象，files 是选择的图片文件

            // 如果返回的结果是 {prevent: true, msg: 'xxxx'} 则表示用户放弃上传
            // return {
            //     prevent: true,
            //     msg: '放弃上传'
            // }
        },
        success: function (xhr, editor, result) {
            // 图片上传并返回结果，图片插入成功之后触发
            // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象，result 是服务器端返回的结果
        },
        fail: function (xhr, editor, result) {
            toastr.error('图片插入错误！');
            // 图片上传并返回结果，但图片插入错误时触发
            // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象，result 是服务器端返回的结果
        },
        error: function (xhr, editor) {
            toastr.error('图片上传出错！');
            // 图片上传出错时触发
            // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象
        },
        timeout: function (xhr, editor) {
            toastr.error('图片上传超时！');
            // 图片上传超时时触发
            // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象
        },

        // 如果服务器端返回的不是 {errno:0, data: [...]} 这种格式，可使用该配置
        // （但是，服务器端返回的必须是一个 JSON 格式字符串！！！否则会报错）
        customInsert: function (insertImg, result, editor) {
            // 图片上传并返回结果，自定义插入图片的事件（而不是编辑器自动插入图片！！！）
            // insertImg 是插入图片的函数，editor 是编辑器对象，result 是服务器端返回的结果

            // 举例：假如上传图片成功后，服务器端返回的是 {url:'....'} 这种格式，即可这样插入图片：
            var url = result.data[0];
            insertImg(url)

            // result 必须是一个 JSON 格式字符串！！！否则报错
        }
    };
    editor.customConfig.customAlert = function (info) {
        toastr.error('上传图片出错！');
    };
    editor.customConfig.uploadFileName = 'imgFile';
    editor.create();
    $('#fabuModal').on('hide.bs.modal', function (e) {
        editor.txt.html('')
    });
    /*var content;
    $.ajax({
        type: "POST",
        url: ctx + "/login/getOneNotice",
        dataType: "json",
        data: {
            "id": 1
        },
        success: function (result) {
            content = result.content;
            editor.txt.html(content);
        },
        error:function (result) {
            toastr.error('系统异常，请联系管理员！');
        }
    });*/
    editor.txt.html('')
    document.getElementById('sumbitNotice').addEventListener('click', function () {
        $.ajax({
            type: "POST",
            url: ctx + "/login/saveNotice",
            dataType: "json",
            data: {
                "content": editor.txt.html()
            },
            success: function (result) {
                $('#noticeDatagrid').datagrid('reload');
                toastr.success('发布成功！');
                $('#fabuModal').modal('hide');
            },
            error:function (result) {
                toastr.error('您输入的字段长度不得高于4000！');
            }
        });
    })
}

$('#passwordSet').on('hide.bs.modal', function (e) {
    clearPassForm();
});
$('#noticeListModal').on('shown.bs.modal', function (e) {
    loadNoticeList();
});
function loadNoticeList() {
    $('#noticeDatagrid').datagrid({
        url: ctx + "/login/getAllNoticeByEmpId",
        idField: 'empId',
        striped: true,
        singleSelect: true,
        scrollbarSize:15,
        border: false,
        pagination: true,
        pageSize: 10,
        columns: [[
            {
                field: 'empId',
                title: '员工编号',
                width: 70,
                align: 'center'
            },
            {
                field: 'empName',
                title: '员工名称',
                width: 70,
                align: 'center'
            },
            {
                field: 'releaseTime',
                title: '发布时间',
                width: 90,
                align: 'center'
            },
            {
                field: 'content',
                title: '内容',
                width: 140,
                align: 'center',
                formatter: function(value,row,index){
                    var dd=row.content.replace(/<\/?.+?>/g,"");
                    var dds=dd.replace(/ /g,"");
                    return dds.substr(0, 15)+"...";
                }
            },
            {
                field:'operate',title:'操作',align:'center',width:70,
                formatter:function(value, row, index){
                    if(row.isThisEmpS == 1){
                        var str = '<a href="javascript:;" onclick="updateNoticeOnClick('+row.id+')" title="更新公告" name="update" class="easyui-linkbutton" ></a><a href="javascript:;" onclick="removeNoticeOnClick('+row.id+')" title="删除公告" name="remove" class="easyui-linkbutton" ></a>';
                        return str;
                    }else{
                        var str = '<a href="javascript:;" onclick="seeNoticeOnClick('+row.id+')" id="seeNotice" title="查看公告" name="seeNotice" class="easyui-linkbutton" ></a>';
                        return str;
                    }
                }
            },
            {
                field:'auth',title:'权限设置',align:'center',width:70,
                formatter:function(value, row, index){
                    if(row.isThisEmpS == 1) {
                        var str = '<a href="javascript:;" onclick="authSetOnclick(' + row.id + ')" title="设置员工查看权限" name="authSet" class="easyui-linkbutton" >设置</a>';
                        return str;
                    }else{
                        return "无法设置";
                    }
                }
            }
        ]],
        fitColumns: 'true',
        striped: 'true',
        fit:true,
        title:'公告列表',
        toolbar: '#noticetb',
        onLoadSuccess:function(data) {
            $("a[name='update']").linkbutton({plain: true, iconCls: 'icon-edit'});
            $("a[name='remove']").linkbutton({plain: true, iconCls: 'icon-remove'});
            $("a[name='seeNotice']").linkbutton({plain: true, iconCls: 'icon-redo'});
            $("a[name='authSet']").linkbutton({plain: true, iconCls: 'icon-tip'});
        }
    });
}

function openNoticeListModal() {
    setModalCenter('noticeListModal');
    $('#noticeListModal').modal('show');
}

$('#searchBtn').click(function () {
    var data = $("#searchNoticeForm").serializeObject();
    $('#noticeDatagrid').datagrid('load', data);
})
$('#searchAllBtn').click(function () {
    $('#empName').textbox('clear');
    $('#releaseTimeStart').datebox('clear');
    $('#releaseTimeEnd').datebox('clear');
    var data = {};
    $('#noticeDatagrid').datagrid('load', data);
})
$('#addBtn').click(function () {
    openNoticeModal();
})
/*
$('#fabuModal').on('hide.bs.modal', function (e) {
    $('#noticeDatagrid').datagrid('reload');
});*/
function updateNoticeOnClick(id) {
    $('#noticeId').val(id);
    setModalCenter('updatefabuModal');
    $('#updatefabuModal').modal('show');
}
function removeNoticeOnClick(id) {
    Ewin.confirm({ message: "确认删除此条公告吗？" }).on(function (e) {
        if (!e) {
            return;
        }else{
            $.ajax({
                type: "GET",
                url: ctx + "/login/removeNotice?id="+id,
                dataType: "json",
                success: function (result) {
                    toastr.success('删除'+result.message);
                    $('#noticeDatagrid').datagrid('reload');
                },
                error:function (result) {
                    toastr.error('删除失败，请联系管理员！');
                }
            });
        }
    });
}
function loadUpdateEdition() {
    var E = window.wangEditor;
    var editor = new E('#div3', '#div4') ; // 两个参数也可以传入 elem 对象，class 选择器
    editor.customConfig.uploadImgServer = ctx + "/file/getDownlaodResult";
    editor.customConfig.uploadImgMaxSize = 20 * 1024 * 1024;
    editor.customConfig.uploadImgMaxLength = 5;
    editor.customConfig.uploadImgHooks = {
        before: function (xhr, editor, files) {
            // 图片上传之前触发
            // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象，files 是选择的图片文件

            // 如果返回的结果是 {prevent: true, msg: 'xxxx'} 则表示用户放弃上传
            // return {
            //     prevent: true,
            //     msg: '放弃上传'
            // }
        },
        success: function (xhr, editor, result) {
            // 图片上传并返回结果，图片插入成功之后触发
            // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象，result 是服务器端返回的结果
        },
        fail: function (xhr, editor, result) {
            toastr.error('图片插入错误！');
            // 图片上传并返回结果，但图片插入错误时触发
            // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象，result 是服务器端返回的结果
        },
        error: function (xhr, editor) {
            toastr.error('图片上传出错！');
            // 图片上传出错时触发
            // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象
        },
        timeout: function (xhr, editor) {
            toastr.error('图片上传超时！');
            // 图片上传超时时触发
            // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象
        },

        // 如果服务器端返回的不是 {errno:0, data: [...]} 这种格式，可使用该配置
        // （但是，服务器端返回的必须是一个 JSON 格式字符串！！！否则会报错）
        customInsert: function (insertImg, result, editor) {
            // 图片上传并返回结果，自定义插入图片的事件（而不是编辑器自动插入图片！！！）
            // insertImg 是插入图片的函数，editor 是编辑器对象，result 是服务器端返回的结果

            // 举例：假如上传图片成功后，服务器端返回的是 {url:'....'} 这种格式，即可这样插入图片：
            var url = result.data[0];
            insertImg(url)

            // result 必须是一个 JSON 格式字符串！！！否则报错
        }
    };
    editor.customConfig.customAlert = function (info) {
        toastr.error('上传图片出错！');
    };
    editor.customConfig.uploadFileName = 'imgFile';
    editor.create();
    $('#updatefabuModal').on('hide.bs.modal', function (e) {
        editor.txt.html('')
    });

    $('#updatefabuModal').on('shown.bs.modal', function (e) {
        $.ajax({
            type: "POST",
            url: ctx + "/login/getOneNotice",
            dataType: "json",
            data: {
                "id": $('#noticeId').val()
            },
            success: function (result) {
                editor.txt.html(result.content);
            },
            error:function (result) {
                toastr.error('系统异常，请联系管理员！');
            }
        });
    });
    /*var content;
    $.ajax({
        type: "POST",
        url: ctx + "/login/getOneNotice",
        dataType: "json",
        data: {
            "id": 1
        },
        success: function (result) {
            content = result.content;
            editor.txt.html(content);
        },
        error:function (result) {
            toastr.error('系统异常，请联系管理员！');
        }
    });*/
    editor.txt.html('');
    document.getElementById('updateNotice').addEventListener('click', function () {
        $.ajax({
            type: "POST",
            url: ctx + "/login/updateNotice",
            dataType: "json",
            data: {
                "content": editor.txt.html(),
                "id":$('#noticeId').val()
            },
            success: function (result) {
                $('#noticeDatagrid').datagrid('reload');
                toastr.success('修改成功！');
                $('#updatefabuModal').modal('hide');
            },
            error:function (result) {
                toastr.error('系统异常，请联系管理员！');
            }
        });
    })

}

function seeNoticeOnClick(id) {
    $('#seenoticeId').val(id);
    setModalCenter('seefabuModal');
    $('#seefabuModal').modal('show');
}

function loadSeeEdition() {
    var E = window.wangEditor;
    var editor = new E('#div6','#div5') ; // 两个参数也可以传入 elem 对象，class 选择器
    editor.create();
    editor.$textElem.attr('contenteditable', false);
    $('#seefabuModal').on('hide.bs.modal', function (e) {
        editor.txt.html('')
    });
    $('#seefabuModal').on('shown.bs.modal', function (e) {
        $.ajax({
            type: "POST",
            url: ctx + "/login/getOneNotice",
            dataType: "json",
            data: {
                "id": $('#seenoticeId').val()
            },
            success: function (result) {
                editor.txt.html(result.content);
            },
            error:function (result) {
                toastr.error('系统异常，请联系管理员！');
            }
        });
    })
}
function loadAuthData() {
    $('#authDatagrid').treegrid({
        iconCls:'icon-search',
        url:ctx + "/login/getNotAdminTree",
        method: 'post',
        idField:'empId',
        treeField:'empId',
        showFooter:true,
        columns:[[
            {
                title: '序号',
                field: 'id',
                align: 'center',
                checkbox: true
            },
            {
                field: 'empId',
                title: '编号',
                width: 50,
                align: 'center'
            },
            {
                field: 'empName',
                title: '名称',
                width: 40,
                align: 'center'
            }
        ]],
        fitColumns: 'true',
        fit:true,
        animate:true,
        border: false,
        toolbar: '#emptb',
        singleSelect:false,
        loadFilter: function (data, parentId) {
            var list = data.rows;
            for (var i = 0; i < list.length; i++) {
                var parent = list[i];
                parent._parentId = parent.parentId;
            }
            return data;
        },
        onCheck : function(row){
            var id = row.empId;
            var ch = $(this).treegrid('getChildren', id);
            for (var i = 0; i < ch.length; i++) {
                $(this).treegrid('select', ch[i].empId);
            }
        },
        onUncheck : function(row){
            var id = row.empId;
            var ch = $(this).treegrid('getChildren', id);
            for (var i = 0; i < ch.length; i++) {
                $(this).treegrid('unselect', ch[i].empId);
            }
        }
    });
}

function authSetOnclick(id) {
    $('#authnoticeId').val(id);
    setModalCenter('authSetModal');
    $('#authSetModal').modal('show');
}
$('#authSetModal').on('shown.bs.modal', function (e) {
    loadAuthData();
});

$('#sumbitAuth').click(function () {
    var rows = $('#authDatagrid').treegrid('getSelections');
    if(rows.length < 1){
        toastr.error('请至少选择一名员工！');
        return false;
    }
    var noticeId = $('#authnoticeId').val();
    var result = new Array();
    for (var i = 0; i < rows.length; i++) {
        if(rows[i].children == null){
            var data = rows[i];
            data.noticeId = noticeId;
            result.push(data);
        }
    }
    $.ajax({
        type: "POST",
        url: ctx + "/login/saveEmpNoticList",
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(result),
        success: function (result) {
            toastr.success('设置'+result.message);
            $('#authSetModal').modal('hide');
        },
        error:function (result) {
            toastr.error('设置失败，请联系管理员！');
        }
    });
})
$('#authSetModal').on('hide.bs.modal', function (e) {
    $('#authDatagrid').treegrid('unselectAll');
});

function disptime(){
    var today = new Date(); //获得当前时间
    var hh = today.getHours();  //获得小时、分钟、秒
    var mm = today.getMinutes();
    var ss = today.getSeconds();
    /*设置div的内容为当前时间*/
    document.getElementById("nowTime").innerHTML="现在时间:"+hh+":"+mm+":"+ss;
    /*
      使用setTimeout在函数disptime()体内再次调用setTimeout
      设置定时器每隔1秒（1000毫秒），调用函数disptime()执行，刷新时钟显示
    */
    var myTime=setTimeout("disptime()",1000);
}