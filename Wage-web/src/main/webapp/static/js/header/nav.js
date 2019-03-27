/*顶部导航栏使用的*/
//$(document).ready(function(){
//$('[data-submenu]').submenupicker();//激活多级菜单
//$("[data-toggle='popover']").popover();//激活悬浮框
//});

function show() {
    return $('#collapsedShowDiv').html();
}

function showNav() {
    document.getElementById("sidebar").style.marginTop ="60px";
    document.getElementById("centerTitle").style.marginTop ="0px";
    document.getElementById("centerTitle").style.width="1533px";
    $(".navbar-fixed-top").slideDown();
    $('#cc').layout('expand', 'north');
}
function closeNav() {
    document.getElementById("sidebar").style.marginTop ="27px";
    document.getElementById("centerTitle").style.marginTop ="-9px";
    document.getElementById("centerTitle").style.width="1549px";
    $(".navbar-fixed-top").hide();
    $('#cc').layout('collapse', 'north');
}

/*requirejs(['art-template'], function (template) {
    $(document).ready(function () {
        loadMenu();
    });

    /!**
     * 获取菜单
     *!/
    function loadMenu() {
        $.ajax({
            type: "post",
            url: ctx + "/menu",
            dataType: "json",
            success: function (result) {
                ajaxReturnWithMsg(result, function () {
                    var html = template("menuTemplate", result.list);
                    $('#menuLi').html(html);
                }, function () {
                }, '', '获取菜单失败');
            },
            error: function () {
                messagerFail('获取菜单失败');
            }
        });
    }

});*/

