<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="wageModal" class="am-modal am-modal-prompt" tabindex="-1" style="">
    <div class="am-modal-dialog" style="width: 700px">
        <div class="am-modal-hd" id="headText" style="font-size: 20px">工资单
            <a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a>
        </div>
        <div class="am-modal-bd" style="padding: 20px">
            <table id="wageTable">
                <tr>
                    <td>年月</td>
                    <td>编号</td>
                    <td>姓名</td>
                    <td>基础工资</td>
                    <td>岗位工资</td>
                    <td>津贴</td>
                    <td>补贴</td>
                    <td>月奖金</td>
                </tr>
                <tbody>
                <tr>
                    <td id="data1">无数据</td>
                    <td id="data2">无数据</td>
                    <td id="data3">无数据</td>
                    <td id="data4">无数据</td>
                    <td id="data5">无数据</td>
                    <td id="data6">无数据</td>
                    <td id="data7">无数据</td>
                    <td id="data8">无数据</td>
                </tr>
                <tr>
                    <td>年终奖金</td>
                    <td>加班费</td>
                    <td>请假扣除</td>
                    <td>其他扣除</td>
                    <td>个人应缴四金</td>
                    <td>单位应缴五险一金</td>
                    <td>个人所得税</td>
                    <td>实发工资</td>
                </tr>
                <tr>
                    <td id="data9">无数据</td>
                    <td id="data10">无数据</td>
                    <td id="data11">无数据</td>
                    <td id="data12">无数据</td>
                    <td id="data13">无数据</td>
                    <td id="data14">无数据</td>
                    <td id="data15">无数据</td>
                    <td id="data16">无数据</td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="am-modal-footer">
            <span class="am-modal-btn" data-am-modal-cancel>关闭</span>
            <span onclick="exportTo();" class="am-modal-btn" data-am-modal-confirm>打印</span>
        </div>
    </div>
</div>