<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header class="am-topbar am-topbar-inverse admin-header" style="z-index: 1100">
    <div class="am-topbar-brand">
        <a href="javascript:;" class="tpl-logo" style="height: 30px;width: 190px;padding-top: 3px">
            <img src="${ctx}/static/css/emp/assets/img/emplogo.png" alt="">
        </a>
    </div>
    <div class="am-icon-list tpl-header-nav-hover-ico am-fl am-margin-right">

    </div>

    <button class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only"
            data-am-collapse="{target: '#topbar-collapse'}"><span class="am-sr-only">导航切换</span> <span
            class="am-icon-bars"></span></button>

    <div class="am-collapse am-topbar-collapse" id="topbar-collapse">

        <ul class="am-nav am-nav-pills am-topbar-nav am-topbar-right admin-header-list tpl-header-list">
            <li class="am-dropdown" data-am-dropdown data-am-dropdown-toggle>
                <a class="am-dropdown-toggle tpl-header-list-link" href="javascript:;">
                    <span class="am-icon-bell-o"></span> 公告
                    <c:if test="${noticeCount != 0}">
                        <span class="am-badge tpl-badge-danger am-round" id="noticeCount">${noticeCount}</span>
                    </c:if>
                    </span>
                </a>
                <ul class="am-dropdown-content tpl-dropdown-content" id="content">
                    <li class="tpl-dropdown-content-external">
                        <h3>你共有 <span class="tpl-color-danger" id="noticeCount1">${noticeCount}</span> 条公告未读</h3><a
                            onclick="openNotice();" href="javascript:void(0);">查看全部</a></li>
                    <li id="contexts">
                        <%--<c:forEach var="item" items="${empNotice}" varStatus="s">
                            <c:if test="${s.index == 0}">

                                <a href="javascript:void(0);" onclick="loadSeeEdition('${item.noticeId}');"
                                   class="tpl-dropdown-content-message">
                                <span class="tpl-dropdown-content-photo">
                      <img src="${ctx}/file/downloadFile?md5=${item.empHeadImg}" alt="" id="${item.noticeId}"> </span>
                                    <span class="tpl-dropdown-content-subject">
                      <span class="tpl-dropdown-content-from"> ${item.empName} </span>
                                <span class="tpl-dropdown-content-time">${item.day}天${item.hour}小时${item.min}分钟前 </span>
                                </span>
                                    <span class="tpl-dropdown-content-font"> ${fn:substring(item.content, 0, 40)}......</span>
                                </a>
                            </c:if>
                            <c:if test="${s.index == 1}">
                                <a href="javascript:void(0);" onclick="loadSeeEdition('${item.noticeId}');"
                                   class="tpl-dropdown-content-message">
                                <span class="tpl-dropdown-content-photo">
                      <img src="${ctx}/file/downloadFile?md5=${item.empHeadImg}" alt="" id="${item.noticeId}"> </span>
                                    <span class="tpl-dropdown-content-subject">
                      <span class="tpl-dropdown-content-from"> ${item.empName} </span>
                                <span class="tpl-dropdown-content-time">${item.day}天${item.hour}小时${item.min}分钟前 </span>
                                </span>
                                    <span class="tpl-dropdown-content-font"> ${fn:substring(item.content, 0, 40)}......</span>
                                </a>
                            </c:if>
                        </c:forEach>--%>
                    </li>

                </ul>
            </li>
            <%--<li class="am-hide-sm-only"><a href="javascript:;" id="admin-fullscreen" class="tpl-header-list-link"><span
                    class="am-icon-arrows-alt"></span> <span class="admin-fullText">开启全屏</span></a></li>--%>

            <li class="am-dropdown" data-am-dropdown data-am-dropdown-toggle>
                <a class="am-dropdown-toggle tpl-header-list-link" href="javascript:;">
                    <span class="tpl-header-list-user-nick">${username}</span><span
                        class="tpl-header-list-user-ico"> <img
                        src="" id="head"></span>
                </a>
                <ul class="am-dropdown-content">
                    <li id="accoutSet" onclick="openAcoutModal()" style="cursor:pointer"><a href="javascript: void(0)"><span
                            class="am-icon-bell-o"></span> 个人设置</a></li>
                    <li id="safeSet"  onclick="loadPassSetModal();" style="cursor:pointer"><a href="javascript: void(0)"><span class="am-icon-cog"></span> 密码设置</a></li>
                    <li id="loginHis"  onclick="loadLoginHisModal();" style="cursor:pointer"><a href="javascript: void(0)"><span class="am-icon-bar-chart"></span> 登录记录</a></li>
                    <li id="loginout" onclick="loginOut()" style="cursor:pointer"><a href="javascript: void(0)"><span
                            class="am-icon-power-off"></span> 退出</a></li>
                </ul>
            </li>
            <li><a href="${ctx}/login/doLogout" class="tpl-header-list-link"><span
                    class="am-icon-sign-out tpl-header-list-ico-out-size"></span></a></li>
        </ul>
    </div>
</header>
