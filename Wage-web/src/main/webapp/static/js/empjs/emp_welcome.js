$(function () {

});


function loadGunLun() {
    $('#demo01').flexslider({
        animation: "slide",
        direction:"horizontal",
        easing:"swing"
    });
}


/*

$('.imc').mouseover(function(){
    $(this).animate({opacity:'1'});
});

$('.imc').mouseout(function(){
    $(this).animate({opacity:'0.0'});
});
*/

function loadNewImg() {
    $.ajax({
        type: "POST",
        url: ctx + "/sysSet/getAllNewImg",
        dataType: "json",
        success: function (result) {
            var list = result;
            $("#newsImg").html("");
            for (var i = 0; i < list.length && i < 7; i++) {
                var data = list[i];
                var img;
                if(data.isNetworkImg == 1){
                    img = data.imgUrl;
                }else {
                    img = ctx + "/file/downloadFile?md5="+data.imgMd;
                }
                var html;
                if(data.newUrl == null){
                    html = "\n" +
                        "<li style=\"height: 100%\">\n" +
                        "    <div class=\"img\" style=\"position:relative;\">\n" +
                        "        <img src="+img+" style=\"height: 460px;width: 100%\" alt=\"\" />\n" +
                        "        \n" +
                        "    </div>\n" +
                        "</li>";
                }else {
                    html = "<li  style=\"height: 100%\">\n" +
                        "    <div class=\"img\" style=\"position:relative;\">\n" +
                        "        <a href=" + data.newUrl + " target=\"_blank\">\n" +
                        "            <img src=" + img + " height=\"460px\" width=\"100%\" alt=\"\" />\n" +
                        "            <div class=\"imc\" style=\"width: 100%;height: 20%;position:absolute;left:0px;bottom:0px;z-index: 10000;\" >\n" +
                        "                <div style=\"width: 100%;height: 100%;background-color: rgba(0,0,0,0.4);padding: 15px\">\n" +
                        "                    <a href=" + data.newUrl + " target=\"_blank\" style=\"font-size: 16px;font-family: 新宋体;color: rgb(232,241,240);\" >\n" +
                        "                        " + data.themeText + "" +
                        "                    </a>\n" +
                        "                </div>\n" +
                        "            </div>\n" +
                        "        </a>\n" +
                        "    </div>\n" +
                        "</li>";
                }
                $("#newsImg").append(html);
            }
            loadGunLun();
        },
        error: function (result) {
        }
    });
}

function loadNew() {
    $.ajax({
        type: "POST",
        url: ctx + "/sysSet/getAllNewNoImg",
        dataType: "json",
        success: function (result) {
            var list = result;
            $("#news").html("");
            for (var i = 0; i < list.length && i < 7; i++) {
                var data = list[i];
                var html = "<div class=\"row clearfix news-xq\">\n" +
                    "    <div class=\"col-md-12\">\n" +
                    "        <p>\n" +
                    "            <a href="+data.newUrl+" target=\"_blank\">"+data.themeText+"</a>\n" +
                    "        </p>\n" +
                    "    </div>\n" +
                    "</div>";
                $("#news").append(html);
            }
        },
        error: function (result) {
        }
    });
}
loadNewImg();
loadNew();
/*
window.onresize = function(){
    loadTable();
}*/
