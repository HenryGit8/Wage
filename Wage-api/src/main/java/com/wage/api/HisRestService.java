/**
 * HisRestService.java 2018/1/10 10:10
 * Copyright ©2018 wondersgroup.com All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.wage.api;

import com.github.pagehelper.PageInfo;
import com.wage.model.HisRest;
import com.wage.model.HisRestKey;
import com.wage.model.HisRevokeApply;
import com.wage.model.ext.HisRestExt;
import com.wage.model.ext.HisRestNExt;
import com.wage.model.ext.HisRevokeApplyExt;

import java.util.Date;
import java.util.Map;

/**
 * File：HisRestService.java<br>
 * Title: <br>
 * Description: <br>
 * Company: wondersgroup.com <br>
 * @author 何友池
 * @version 1.0
 */
public interface HisRestService {

    /**
     * 根据员工编号查询请假记录
     * @param page
     * @param empId
     * @return
     * @author 何友池
     */
    PageInfo<HisRestNExt> getByEmpid(PageInfo page, Integer empId, Date restStartTimeMin, Date restStartTimeMax);

    /**
     * 获取请假记录列表
     * @param empId
     * @return
     * @author 何友池
     */
    Map<String,Object> getRestByEmpid(Integer empId);
    /**
     * 更新一条请假信息数据
     * @param record
     * @author 何友池
     */
    void updateHisRest(HisRestExt record);

    /**
     * 删除一条请假记录
     * @param key
     * @author 何友池
     */
    void removeHisRest(HisRestKey key);

    /**
     * 新增一套请假记录
     * @param record
     * @author 何友池
     */
    void saveHisRest(HisRest record);

    /**
     * 查询全部撤回请假申请数据
     * @return
     * @author 何友池
     */
    PageInfo<HisRevokeApplyExt> getRevokeApply(PageInfo page,Short isAllow);

    /**
     * 更新审批结果
     * @param isAllow
     * @param id
     * @author 何友池
     */
    void updateRevokeApply(Short isAllow,Integer id);
    /**
     * 判断此员工的请假时间是否与数据库重叠
     * @param empId
     * @param startTime
     * @param endTime
     * @return
     * @author 何友池
     */
    boolean checkIsOverLap(Integer empId,Date startTime,Date endTime);
    /**
     * 提交撤回请假申请
     * @param record
     * @author 何友池
     */
    void saveRestApply(HisRevokeApply record);
}
