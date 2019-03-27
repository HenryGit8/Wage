$(function () {
    loadHead();
    new PageInit().init();
    reloadNotice();
    checkFrist();
    /*$('#my-modal').modal('open');*/
});

function loadHead() {
    $.ajax({
        type: "POST", //使用post方法访问后台
        dataType: "json", //返回json格式的数据
        url: ctx + "/login/getEmpImgMd", //要访问的后台地址
        success: function (result) {//result为返回的数据
            var img = $('#head');
            img.removeAttr('src');
            img.attr('src', ctx + "/file/downloadFile?md5=" + result);
        }
    });
}

function loginOut() {
    window.location.href = ctx + "/login/doLogout";
}

function openjsp(url) {
    parent.document.getElementById("iframe").src = ctx + "/jump/" + url;
    /*reinitIframe();*/
    $("#nav li a").attr("class", "nav-link tpl-left-nav-link-list");
    $("#nav li ul li a").attr("class", "");
    $("#" + url).attr("class", "nav-link tpl-left-nav-link-list active");
}

function openChiJsp(url) {
    parent.document.getElementById("iframe").src = ctx + "/jump/" + url;
    /*reinitIframe();*/
    $("#nav li a").attr("class", "nav-link tpl-left-nav-link-list");
    $("#nav li ul li a").attr("class", "");
    $("#" + url).attr("class", "active");
}

function openFarJsp(id) {
    $("#nav li a").attr("class", "nav-link tpl-left-nav-link-list");
    $("#nav li ul li a").attr("class", "");
    $("#" + id).attr("class", "nav-link tpl-left-nav-link-list active");
}

function loadInfo() {
    $.ajax({
        type: "POST", //使用post方法访问后台
        dataType: "json", //返回json格式的数据
        url: ctx + "/empInfo/getLoginInfo", //要访问的后台地址
        success: function (result) {//result为返回的数据
            $('#empName').val(result.empName);
            if (result.empSex == '男') {
                $("#man").attr("checked", "checked");
            } else {
                $("#woman").attr("checked", "checked");
            }
            $('#empTell').val(result.empTell);
            $('#empAddress').val(result.empAddress);
            $('#empEdu').val(result.empEdu);
            $('#empSchool').val(result.empSchool);
            $('#empIdnum').val(result.empIdnum);
            var form = layui.form;
            form.render();
        }
    });
    $.ajax({
        type: "POST", //使用post方法访问后台
        dataType: "json", //返回json格式的数据
        url: ctx + "/login/getEmpImgMd", //要访问的后台地址
        success: function (result) {//result为返回的数据
            var img = $('#headImg');
            img.removeAttr('src');
            img.attr('src', ctx + "/file/downloadFile?md5=" + result);
        }
    });
}

$('#my-modal').on('open.modal.amui', function () {
    loadInfo();
});
$('#my-modal').on('closed.modal.amui', function () {
    if ($('#type').val() == 0) {
        $('#confirmSubmit').modal({
            relatedTarget: this,
            onConfirm: function (options) {
                $('#empIdnumFa').hide();
                chageInner('headText', '修改个人信息');
                $('#type').val('1');
            },
            onCancel: function () {
                $('#my-modal').modal('open');
            }
        });
    }
});

function openAcoutModal() {
    $('#empIdnumFa').hide();
    chageInner('headText', '修改个人信息');
    $('#type').val('1');
    $('#my-modal').modal('open');
}

$('#submitFirst').click(function () {
    if ($("#empName").val().length < 1) {
        toastr.error('请输入姓名！');
        return false;
    }
    if (!/^[\u4E00-\u9FA5]{2,5}$/.test($("#empName").val())) {
        toastr.error('姓名不合法！');
        return false;
    }
    if ($('input[name="empSex"]:checked').val().length < 1) {
        toastr.error('请选择性别！');
        return false;
    }
    if ($("#empTell").val().length < 1) {
        toastr.error('请输入联系电话！');
        return false;
    }
    if (!/^1\d{10}$/.test($("#empTell").val())) {
        toastr.error('联系电话必须为11位数字！');
        return false;
    }
    if ($("#empAddress").val().length < 1) {
        toastr.error('请输入联系地址！');
        return false;
    }
    if ($("#empAddress").val().length > 50) {
        toastr.error('地址过长！');
        return false;
    }
    if ($("#empEdu").val().length < 1) {
        toastr.error('请选择学历！');
        return false;
    }
    if ($("#empSchool").val().length < 1) {
        toastr.error('请输入学校！');
        return false;
    }
    if ($("#empSchool").val().length > 20) {
        toastr.error('学校名称过长！');
        return false;
    }
    if ($("#empIdnum").val().length < 1) {
        toastr.error('请输入身份证号！');
        return false;
    }
    if (!/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/.test($("#empIdnum").val())) {
        toastr.error('身份证号不合法！');
        return false;
    } else {
        $.ajax({
            type: "POST",
            url: ctx + "/empInfo/updateLoginInfo",
            dataType: "json",
            data: $("#updateFirst").serializeObject(),
            success: function (result) {
                if ($('#type').val() == 1) {
                    toastr.success('保存' + result.message);
                }
            },
            error: function (result) {
                $('#my-modal').modal('open');
                toastr.error('保存失败，请联系管理员！');
            }
        });
        /*var node=$('#empIdnumFa');
        if(node.is(':hidden')){　　//如果node是隐藏的则显示node元素，否则隐藏
            $.ajax({
                type: "POST",
                url: ctx + "/empInfo/updateLoginInfo",
                dataType: "json",
                data: $("#updateFirst").serializeObject(),
                success: function (result) {
                    toastr.success('保存' + result.message);
                },
                error: function (result) {
                    $('#my-modal').modal('open');
                    toastr.error('保存失败，请联系管理员！');
                }
            });
        }else{
            $('#confirmSubmit').modal({
                relatedTarget: this,
                onConfirm: function (options) {
                    $.ajax({
                        type: "POST",
                        url: ctx + "/empInfo/updateLoginInfo",
                        dataType: "json",
                        data: $("#updateFirst").serializeObject(),
                        success: function (result) {
                            toastr.success('保存' + result.message);
                            $('#type').val('1');
                        },
                        error: function (result) {
                            $('#my-modal').modal('open');
                            toastr.error('保存失败，请联系管理员！');
                        }
                    });

                },
                // closeOnConfirm: false,
                onCancel: function () {
                    $('#my-modal').modal('open');
                    $('#empIdnumFa').show();
                }
            });
        }*/
    }
});

$('#headImg').click(function () {
    $('#updateHead').modal('open');
})

function PageInit() {
    var api = null;
    var _this = this;
    this.init = function () {
        $("#headImg").on('click', this.portraitUpload)
    };

    this.portraitUpload = function () {
        var img = $('#cut-img');
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
        });
        portrait.on('fileloaded', function (event, file, previewId, index, reader) {
            alert.addClass('hidden').html();
        });
        portrait.on('fileuploaded', function (event, data) {
            if (!data.response.status) {
                alert.html(data.response.message).removeClass('hidden');
            }
            $.ajax({
                type: "POST", //使用post方法访问后台
                dataType: "json", //返回json格式的数据
                url: ctx + "/login/saveEmpImgMd", //要访问的后台地址
                data: {
                    "empHeadImg": data.response.object
                },
                success: function (result) {//result为返回的数据
                    var img = $('#headImg');
                    img.removeAttr('src');
                    img.attr('src', ctx + "/file/downloadFile?md5=" + data.response.object);
                    var img1 = $('#head');
                    img1.removeAttr('src');
                    img1.attr('src', ctx + "/file/downloadFile?md5=" + data.response.object);
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
            maxFileSize: 20480,//文件最大容量
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

function loadNotice() {
    layui.use('table', function () {
        var table = layui.table;
        var tableIns = table.render({
            elem: '#noticeTable',
            url: ctx + "/login/getEmpNotice",
            page: true, //开启分页
            height: '500',
            size: 'sm',
            limit: '20',
            where: $("#searchForm").serializeObject(),
            even: true,
            skin:'line',
            cols: [[ //表头
                {
                    field: 'empName',
                    title: '发布者',
                    width: '17%',
                    align: 'center'
                }, {
                    field: 'content',
                    title: '内容',
                    width: '28%',
                    align: 'center'
                }, {
                    field: 'timeAgo',
                    title: '多久之前',
                    width: '26%',
                    align: 'center',
                    templet: function (d) {
                        return d.day + '天' + d.hour + '小时' + d.min + '分钟以前';
                    }
                }, {
                    field: 'isRead',
                    title: '是否已读',
                    width: '13%',
                    align: 'center',
                    templet: function (d) {
                        if (d.isRead == 0) {
                            return '未读';
                        } else if (d.isRead == 1) {
                            return '已读';
                        }
                    }
                },
                {
                    field: 'lookUp',
                    title: '查看详情',
                    width: '13%',
                    align: 'center',
                    toolbar: "#barLook"
                }
            ]],
            response: {
                statusName: 'statusCode' //数据状态的字段名称，默认：code
                , statusCode: 1 //成功的状态码，默认：0
                , msgName: 'message' //状态信息的字段名称，默认：msg
                , countName: 'total' //数据总数的字段名称，默认：count
                , dataName: 'rows' //数据列表的字段名称，默认：data
            },
            request: {
                pageName: 'page' //页码的参数名称，默认：page
                , limitName: 'rows' //每页数据量的参数名，默认：limit
            }
        });

        table.on('tool(noticeTable)', function (obj) {
            var row = obj.data;
            if (obj.event === 'detail') {
                loadSeeEdition(row.noticeId);
            }
        });
    });
}

function openNotice() {
    $('#noticeModal').modal('open');
}

$('#noticeModal').on('opened.modal.amui', function () {
    loadNotice();
});

$('#noticeModal').on('closed.modal.amui', function () {
    reloadNotice();
});
$('#seeNoticeModal').on('closed.modal.amui', function () {
    reloadNotice();
    loadNotice();
});

function loadSeeEdition(noticeid) {
    var E = window.wangEditor;
    var editor = new E('#div5'); // 两个参数也可以传入 elem 对象，class 选择器
    editor.create();
    editor.$textElem.attr('contenteditable', false);
    editor.txt.html('');
    $.ajax({
        type: "POST",
        url: ctx + "/login/getOneNotice",
        dataType: "json",
        data: {
            "id": noticeid
        },
        success: function (result) {
            editor.txt.html(result.content);
            $('#seeNoticeModal').modal('open');
            $.ajax({
                type: "POST",
                url: ctx + "/login/updateIsReadNotice",
                dataType: "json",
                data: {
                    "noticeId": noticeid
                },
                success: function (result) {
                },
                error: function (result) {
                }
            });
        },
        error: function (result) {
            toastr.error('系统异常，请联系管理员！');
        }
    });
}

function reloadNoticeCount() {
    $.ajax({
        type: "POST",
        url: ctx + "/login/getNoRead",
        dataType: "json",
        success: function (result) {
            if (result == 0) {
                $("#noticeCount").remove();
                chageInner('noticeCount1', result)
            } else {
                chageInner('noticeCount', result);
                chageInner('noticeCount1', result);
            }
        },
        error: function (result) {
        }
    });
}

function reloadNotice() {
    var noticeCount;
    $.ajax({
        type: "POST",
        url: ctx + "/login/getNoRead",
        dataType: "json",
        success: function (result) {
            noticeCount = result;
            if (noticeCount != 0) {
                chageInner('noticeCount', noticeCount);
                chageInner('noticeCount1', noticeCount);
                $.ajax({
                    type: "POST",
                    url: ctx + "/login/getNotice",
                    dataType: "json",
                    success: function (result) {
                        var list = result;
                        $("#contexts").html("");
                        for (var i = 0; i < list.length && i < 3; i++) {
                            var data = list[i];
                            var html = "<a href=\"javascript:void(0);\" onclick=\"loadSeeEdition(" + data.noticeId + ");\" class=\"tpl-dropdown-content-message\">\n" +
                                "                                <span class=\"tpl-dropdown-content-photo\">\n" +
                                "                      <img src=" + ctx + "/file/downloadFile?md5=" + data.empHeadImg + " alt=\"\" id=" + data.noticeId + "> </span>\n" +
                                "                                    <span class=\"tpl-dropdown-content-subject\">\n" +
                                "                      <span class=\"tpl-dropdown-content-from\"> " + data.empName + "</span>\n" +
                                "                                <span class=\"tpl-dropdown-content-time\">" + data.day + "天" + data.hour + "小时" + data.min + "分钟前 </span>\n" +
                                "                                </span>\n" +
                                "                                    <span class=\"tpl-dropdown-content-font\"> " + data.content.substr(0, 40) + "....." + "</span>\n" +
                                "                                </a>";
                            $("#contexts").append(html);
                        }
                    },
                    error: function (result) {
                    }
                });
            } else {
                $("#contexts").html("");
                $("#noticeCount").remove();
                chageInner('noticeCount1', noticeCount)
            }
        },
        error: function (result) {
        }
    });
}

function checkFrist() {
    $.ajax({
        type: "POST",
        url: ctx + "/login/getLoginCount",
        dataType: "json",
        success: function (result) {
            if (result < 2) {
                $('#my-modal').modal('open');
            }
        },
        error: function (result) {
        }
    });
}

function loadPassSetModal() {
    $("#checkCodeImg").attr("src", ctx + "/getVerify?" + Math.random());
    $('#passSetModal').modal('open');
}


function passUpdateOnclick() {
    $('#passSetSubmit').modal({
        relatedTarget: this,
        onConfirm: function (options) {
            if ($("#oldPassword").val().length < 1) {
                toastr.error('请输入原密码！');
                $('#passSetModal').modal('open');
                return false;
            }
            if ($("#newPassword1").val().length < 1) {
                toastr.error('请输入新密码！');
                $('#passSetModal').modal('open');
                return false;
            }
            if ($("#newPassword2").val().length < 1) {
                toastr.error('请输入确认密码！');
                $('#passSetModal').modal('open');
                return false;
            }
            if ($("#questionType").val().length < 1) {
                toastr.error('请选择验证问题！');
                $('#passSetModal').modal('open');
                return false;
            }
            if ($("#questionValue").val().length < 1) {
                toastr.error('请输入验证问题答案！');
                $('#passSetModal').modal('open');
                return false;
            }
            if ($("#checkCode").val().length < 1) {
                toastr.error('请输入验证码！');
                $('#passSetModal').modal('open');
                return false;
            }
            if (!/^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,21}$/.test($("#newPassword1").val())) {
                toastr.error('新密码格式错误！');
                $('#passSetModal').modal('open');
                return false;
            }
            if ($("#newPassword1").val() != $("#newPassword2").val()) {
                toastr.error('两次输入的密码不一致！');
                $('#passSetModal').modal('open');
                return false;
            } else {
                $.ajax({
                    type: "POST",
                    url: ctx + "/login/updatePassword",
                    dataType: "json",
                    data: $("#updatePass").serializeObject(),
                    success: function (result) {
                        if (result == 0) {
                            toastr.success('修改成功！');
                            clearPassForm();
                        } else if (result == 1) {
                            toastr.error('原密码错误！');
                            $('#passSetModal').modal('open');
                        } else if (result == 2) {
                            toastr.error('问题答案错误！');
                            $('#passSetModal').modal('open');
                        } else if (result == 3) {
                            toastr.error('验证码错误！');
                            $('#passSetModal').modal('open');
                        }
                    },
                    error: function (result) {
                        $('#passSetModal').modal('open');
                        toastr.error('系统异常，请联系管理员！');
                    }
                });
            }
        },
        onCancel: function () {
            $('#passSetModal').modal('open');
        }
    });

}

function clearPassForm() {
    $("#oldPassword").val("");
    $("#newPassword1").val("");
    $("#newPassword2").val("");
    $("#questionType").val("");
    $("#questionValue").val("");
    $("#checkCode").val("");
}

function loadLoginHisModal() {
    $('#loginHisModal').modal('open');
}

function loadTable() {
    layui.use('table', function(){
        var table = layui.table;

        //第一个实例
        table.render({
            elem: '#loginHisTable',
            height: 307,
            url: ctx + "/sysSet/getLoginHisByEmpId",
            page: true, //开启分页
            size:'sm',
            skin:'nob',
            even:true,
            cols: [[ //表头
                {
                    field: 'empId',
                    title: '编号',
                    width: '12%',
                    align: 'center'
                },
                {
                    field: 'empName',
                    title: '姓名',
                    width: '11%',
                    align: 'center'
                },
                {
                    field: 'loginTime',
                    title: '登录时间',
                    width: '24%',
                    align: 'center'
                },
                {
                    field: 'ip',
                    title: '登录IP',
                    width: '20%',
                    align: 'center'
                },
                {
                    field: 'place',
                    title: '登录地点',
                    width: '30%',
                    align: 'center'
                }/*, {
                    field: 'right',
                    title: '操作',
                    width: '27%',
                    align: 'center',
                    toolbar: "#barDemo"
                }*//*,
            {
                field: 'empSchool',
                title: '毕业院校',
                width: '20%',
                align: 'center'
            },
            {
                field: 'departName',
                title: '部门',
                width: '15%',
                align: 'center'
            }*/
            ]],
            response: {
                statusName: 'statusCode' //数据状态的字段名称，默认：code
                ,statusCode: 1 //成功的状态码，默认：0
                ,msgName: 'message' //状态信息的字段名称，默认：msg
                ,countName: 'total' //数据总数的字段名称，默认：count
                ,dataName: 'rows' //数据列表的字段名称，默认：data
            },
            request: {
                pageName: 'page' //页码的参数名称，默认：page
                ,limitName: 'rows' //每页数据量的参数名，默认：limit
            }
        });

    });
}
$('#loginHisModal').on('opened.modal.amui', function () {
    loadTable();
});