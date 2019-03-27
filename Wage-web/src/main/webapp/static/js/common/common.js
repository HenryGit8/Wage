/**
 * 返回码
 */
var RESULT_STATUSCODE = {
    SUCCESS: 1,
    FAIL: 99999
};

/**
 * 用户类型: 0表示学生，1表示老师
 */
var USER_TYPE = {
    STUDENT: "0",
    TEACHER: "1"
};

/**
 * 页面类型
 */
var PAGE_TYPE = {
    ADD: 'add',
    VIEW: 'view',
    EDIT: 'edit'
};


var DATA_COLOR = {
    BLACK: '#313131',
    WHITE: '#f1f1f1'
};


/**
 * ajax返回处理
 */
function ajaxReturn(result, successFun, failFun) {
    if (result && result.statusCode == RESULT_STATUSCODE.SUCCESS) {
        if (successFun) {
            successFun();
        }
    } else {
        if (failFun) {
            failFun();
        }
    }
}

/**
 * ajax返回处理
 */
function ajaxReturnWithMsg(result, successFun, failFun, successMsg, failMsg) {
    if (result && result.statusCode == RESULT_STATUSCODE.SUCCESS) {
        if (successFun) {
            successFun();
        }
        if (successMsg) {
            messagerShow(successMsg);
        }
    } else {
        if (failFun) {
            failFun();
        }
        if (result && result.statusCode != RESULT_STATUSCODE.SUCCESS
            && result.statusCode != RESULT_STATUSCODE.FAIL) {
            failMsg = result.message;
        }
        if (failMsg) {
            messagerShow(failMsg);
        }
    }
}

/**
 * 一般提示
 */
function messagerShow(msg) {
    $("#myAlert").fadeOut('fast', function(){
        myAl.innerHTML =  msg +"！";
    });
    $("#myAlert").fadeIn('fast');
    setTimeout(function(){
        $("#myAlert").fadeOut('fast');
    },5000);
}


/**
 * 默认提示框(自定义直接调用 toastr.info() 方法)
 * @param title
 * @param content
 * @param options
 *      returnJson 后台返回的对象
 *      onShown 打开后调用函数
 *      onClose 关闭后调用函数
 *      type 类型：success、info、warning、error
 *      timeOut 单位毫秒 默认5000
 */
function message(title, content, options) {
    var thisTitle = title ? title : '';
    var thisContent = content ? content : '';
    var thisOptions = options ? options : {};
    var returnJson = thisOptions.returnJson;
    if (returnJson && returnJson.statusCode && returnJson.statusCode != RESULT_STATUSCODE.SUCCESS
        && returnJson.statusCode != RESULT_STATUSCODE.FAIL && returnJson.message) {
        if (thisContent) {
            thisContent += "：";
        }
        thisContent += returnJson.message;
    }
    requirejs(["toastr"], function (toastr) {
        var toastrOptions = {
            //"closeButton" : true,
            "newestOnTop": false, //默认 true
            "progressBar": true,
            //"timeOut": "5000", //默认5秒
            "timeOut": thisOptions.timeOut ? thisOptions.timeOut : 5000,
            onShown: function () {
                if (thisOptions.onShown) {
                    thisOptions.onShown();
                }
            },
            onHidden: function () {
                if (thisOptions.onClose) {
                    thisOptions.onClose();
                }
            }
        };
        var type = thisOptions.type ? thisOptions.type : 'info';
        if (type == 'success') {
            toastr.success(thisTitle, thisContent, toastrOptions);
        } else if (type == 'info') {
            toastr.info(thisTitle, thisContent, toastrOptions);
        } else if (type == 'warning') {
            toastr.warning(thisTitle, thisContent, toastrOptions);
        } else if (type == 'error') {
            toastr.error(thisTitle, thisContent, toastrOptions);
        }
    });
}

function messageError(content) {
    message('', content, {'type':'error'});
}

/**
 * 添加、修改、查看页面加载时
 * @param pageType 页面类型
 * @param selector 选择器，一般是Form表单
 * @param inSelector 是否只对选择器中的子元素操作
 */
function onEditPageLoad(pageType, selector, inSelector) {
    if (pageType && pageType == PAGE_TYPE.VIEW) {
        if (selector) {
            selector.find('input').attr('readonly', true);
            selector.find('.easyui-textbox').textbox('readonly', true);
            selector.find('.easyui-datebox').datebox('readonly', true);
            selector.find('.easyui-combobox').combobox('readonly', true);
            selector.find('input[type="checkbox"]').click(function () {
                return false;
            });
        }
        if (selector && inSelector) {
            selector.find('.js-addShow').hide();
            selector.find('.js-editShow').hide();
            selector.find('.js-viewShow').show();
        } else {
            $('.js-addShow').hide();
            $('.js-editShow').hide();
            $('.js-viewShow').show();
        }
    } else if (pageType && pageType == PAGE_TYPE.EDIT) {
        if (selector) {
            selector.find('input').attr('readonly', false);
            selector.find('.easyui-textbox').textbox('readonly', false);
            selector.find('.easyui-datebox').datebox('readonly', false);
            selector.find('.easyui-combobox').combobox('readonly', false);
        }
        if (selector && inSelector) {
            selector.find('.js-addShow').hide();
            selector.find('.js-viewShow').hide();
            selector.find('.js-editShow').show();
        } else {
            $('.js-addShow').hide();
            $('.js-viewShow').hide();
            $('.js-editShow').show();
        }
    } else if (pageType && pageType == PAGE_TYPE.ADD) {
        if (selector) {
            selector.find('input').attr('readonly', false);
            selector.find('.easyui-textbox').textbox('readonly', false);
            selector.find('.easyui-datebox').datebox('readonly', false);
            selector.find('.easyui-combobox').combobox('readonly', false);
        }
        if (selector && inSelector) {
            selector.find('.js-editShow').hide();
            selector.find('.js-viewShow').hide();
            selector.find('.js-addShow').show();
        } else {
            $('.js-editShow').hide();
            $('.js-viewShow').hide();
            $('.js-addShow').show();
        }
    }
}

/**
 * 验证警告的HTML
 */
function getValidationErrorHtml(msg) {
    var errorHtml = '<div class="alert alert-danger" style="margin: 0;padding-bottom: 0;">' +
        '<button type="button" class="close" data-dismiss="alert" aria-label="Close"></button>' + msg +
        '</div>';
    return errorHtml;
}


function msgTitle(msg) {
    var myAl = document.getElementById("myAlert");
    $("#myAlert").fadeOut('fast', function(){
        myAl.innerHTML = msg;
    });
    $("#myAlert").fadeIn('fast');
    setTimeout(function(){
        $("#myAlert").fadeOut('fast');
    },5000);
}

window.Ewin = function () {
    var html = '<div id="[Id]" class="modal fade" role="dialog" aria-labelledby="modalLabel">' +
        '<div class="modal-dialog modal-sm">' +
        '<div class="modal-content">' +
        '<div class="modal-header">' +
        '<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>' +
        '<h4 class="modal-title" id="modalLabel" style="font-size: 17px">[Title]</h4>' +
        '</div>' +
        '<div class="modal-body">' +
        '<p>[Message]</p>' +
        '</div>' +
        '<div class="modal-footer">' +
        '<button type="button" style="height: 35px;padding-bottom: 0px;padding-top: 0px" class="btn btn-default cancel" data-dismiss="modal">[BtnCancel]</button>' +
        '<button type="button" style="height: 35px;padding-bottom: 0px;padding-top: 0px" class="btn btn-primary ok" data-dismiss="modal">[BtnOk]</button>' +
        '</div>' +
        '</div>' +
        '</div>' +
        '</div>';


    var dialogdHtml = '<div id="[Id]" class="modal fade" role="dialog" aria-labelledby="modalLabel">' +
        '<div class="modal-dialog">' +
        '<div class="modal-content">' +
        '<div class="modal-header">' +
        '<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>' +
        '<h4 class="modal-title" id="modalLabel">[Title]</h4>' +
        '</div>' +
        '<div class="modal-body">' +
        '</div>' +
        '</div>' +
        '</div>' +
        '</div>';
    var reg = new RegExp("\\[([^\\[\\]]*?)\\]", 'igm');
    var generateId = function () {
        var date = new Date();
        return 'mdl' + date.valueOf();
    }
    var init = function (options) {
        options = $.extend({}, {
            title: "操作提示",
            message: "提示内容",
            btnok: "确定",
            btncl: "取消",
            width: 200,
            auto: false
        }, options || {});
        var modalId = generateId();
        var content = html.replace(reg, function (node, key) {
            return {
                Id: modalId,
                Title: options.title,
                Message: options.message,
                BtnOk: options.btnok,
                BtnCancel: options.btncl
            }[key];
        });
        $('body').append(content);
        $('#' + modalId).modal({
            width: options.width,
            backdrop: 'static'
        });
        $('#' + modalId).on('hide.bs.modal', function (e) {
            $('body').find('#' + modalId).remove();
        });
        return modalId;
    }

    return {
        alert: function (options) {
            if (typeof options == 'string') {
                options = {
                    message: options
                };
            }
            var id = init(options);
            var modal = $('#' + id);
            modal.find('.ok').removeClass('btn-success').addClass('btn-primary');
            modal.find('.cancel').hide();

            return {
                id: id,
                on: function (callback) {
                    if (callback && callback instanceof Function) {
                        modal.find('.ok').click(function () { callback(true); });
                    }
                },
                hide: function (callback) {
                    if (callback && callback instanceof Function) {
                        modal.on('hide.bs.modal', function (e) {
                            callback(e);
                        });
                    }
                }
            };
        },
        confirm: function (options) {
            var id = init(options);
            var modal = $('#' + id);
            modal.find('.ok').removeClass('btn-primary').addClass('btn-success');
            modal.find('.cancel').show();
            return {
                id: id,
                on: function (callback) {
                    if (callback && callback instanceof Function) {
                        modal.find('.ok').click(function () { callback(true); });
                        modal.find('.cancel').click(function () { callback(false); });
                    }
                },
                hide: function (callback) {
                    if (callback && callback instanceof Function) {
                        modal.on('hide.bs.modal', function (e) {
                            callback(e);
                        });
                    }
                }
            };
        },
        dialog: function (options) {
            options = $.extend({}, {
                title: 'title',
                url: '',
                width: 800,
                height: 550,
                onReady: function () { },
                onShown: function (e) { }
            }, options || {});
            var modalId = generateId();

            var content = dialogdHtml.replace(reg, function (node, key) {
                return {
                    Id: modalId,
                    Title: options.title
                }[key];
            });
            $('body').append(content);
            var target = $('#' + modalId);
            target.find('.modal-body').load(options.url);
            if (options.onReady())
                options.onReady.call(target);
            target.modal();
            target.on('shown.bs.modal', function (e) {
                if (options.onReady(e))
                    options.onReady.call(target, e);
            });
            target.on('hide.bs.modal', function (e) {
                $('body').find(target).remove();
            });
        }
    }
}();

// 对Date的扩展，将 Date 转化为指定格式的String
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
// 例子：
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18
Date.prototype.Format = function (fmt) { //author: meizz
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
};


function checkDateTime(str) {
    if(!/^(?:19|20)[0-9][0-9]-(?:(?:0[1-9])|(?:1[0-2]))-(?:(?:[0-2][1-9])|(?:[1-3][0-1])) (?:(?:[0-2][0-3])|(?:[0-1][0-9])):[0-5][0-9]:[0-5][0-9]$/.test(str)){
        return false;
    }else{
        return true;
    }
};

function FormatDate (strTime) {
    var date = new Date(strTime);
    return date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
}
//保留两位小数
//功能：将浮点数四舍五入，取小数点后2位
function toDecimal(x) {
    var f = parseFloat(x);
    if (isNaN(f)) {
        return;
    }
    f = Math.round(x*100)/100;
    return f;
}

function setDateToNull(str) {
    if($('#'+str).val() == ""){
        $('#'+str).val(null);
    }
}

function convert(rows){
    function exists(rows, parentId){
        for(var i=0; i<rows.length; i++){
            if (rows[i].id == parentId) return true;
        }
        return false;
    }

    var nodes = [];
    // get the top level nodes
    for(var i=0; i<rows.length; i++){
        var row = rows[i];
        if (!exists(rows, row.parentId)){
            nodes.push({
                id:row.id,
                text:row.name
            });
        }
    }

    var toDo = [];
    for(var i=0; i<nodes.length; i++){
        toDo.push(nodes[i]);
    }
    while(toDo.length){
        var node = toDo.shift();	// the parent node
        // get the children nodes
        for(var i=0; i<rows.length; i++){
            var row = rows[i];
            if (row.parentId == node.id){
                var child = {id:row.id,text:row.name};
                if (node.children){
                    node.children.push(child);
                } else {
                    node.children = [child];
                }
                toDo.push(child);
            }
        }
    }
    return nodes;
}

function chageInner(text,data) {
    document.getElementById(text).innerHTML = data;
}

function ForDight(Dight,How){
    var Dight = Math.round (Dight*Math.pow(10,How))/Math.pow(10,How);
    return Dight;

};

function onLoad() {
    layer.open({
        type: 2,
        skin: 'layui-layer-demo', //样式类名
        anim: 2,
        shadeClose: false, //开启遮罩关闭
        content: '加载中...'
    });
}
function finishload() {
    layer.closeAll();
}

function setModalCenter(id) {
    // 关键代码，如没将modal设置为 block，则$modala_dialog.height() 为零
    $('#'+id).css('display', 'block');
    var modalHeight=$(window).height() / 2 - $('#'+id+' .modal-dialog').height() / 2;
    $('#'+id).find('.modal-dialog').css({
        'margin-top': modalHeight
    });
}
/**
 * 获取本地IP地址
 */
function getLocalIPAddress()
{
    var obj = null;
    var rslt = "127.0.0.1";
    try
    {
        obj = new ActiveXObject("rcbdyctl.Setting");
        if (!isNull(obj.GetIPAddress))
        {
            rslt = obj.GetIPAddress;
        }
        obj = null;
    }
    catch(e)
    {
        //异常发生
    }

    return rslt;
}


//获取验证码
function getVerify(obj){
    obj.src = ctx +"/getVerify?"+Math.random();
}