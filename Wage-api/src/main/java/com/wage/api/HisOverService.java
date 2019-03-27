/**
 * HisOverService.java 2018/1/17 14:17
 * Copyright ©2018 wondersgroup.com All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.wage.api;

import com.github.pagehelper.PageInfo;
import com.wage.model.HisOver;
import com.wage.model.HisOverKey;
import com.wage.model.ext.HisOverExt;
import com.wage.model.ext.HisOverOExt;

import java.util.Date;
import java.util.Map;

/**
 * File：HisOverService.java<br>
 * Title: <br>
 * Description: <br>
 * Company: wondersgroup.com <br>
 * @author 何友池
 * @version 1.0
 */
public interface HisOverService {
    /**
     * 根据员工编号查询加班记录表
     * @param page
     * @param empId
     * @param overTimeStartMin
     * @param overTimeStartMax
     * @return
     * @author 何友池
     */
    PageInfo<HisOverExt> getByEmpid(PageInfo page, Integer empId, Date overTimeStartMin, Date overTimeStartMax);
    /**
     * 更新加班记录表
     * @param record
     * @author 何友池
     */
    void updateHisOver(HisOverOExt record);
    /**
     * 获取加班记录列表
     * @param empId
     * @return
     * @author 何友池
     */
    Map<String,Object> getOverByEmpid(Integer empId);
    /**
     * 保存一条加班信息
     * @param hisOver
     * @author 何友池
     */
    void saveHisOver(HisOver hisOver);
    /**
     * 删除一条加班信息
     * @param key
     * @author 何友池
     */
    void removeHisOver(HisOverKey key);
    /**
     * 根据是否已审核查询
     * @param page
     * @param isCheck
     * @return
     * @author 何友池
     */
    PageInfo<HisOverExt> getByEmpidC(PageInfo page, Short isCheck);
    /**
     * 更新加班审核结果
     * @param empId
     * @param overTimeStart
     * @param isCheck
     * @author 何友池
     */
    void updateOverApply(Integer empId,Date overTimeStart,Short isCheck);
    /**
     * 判断此员工的加班时间是否与数据库重叠
     * @param empId
     * @param startTime
     * @param endTime
     * @return
     * @author 何友池
     */
    boolean checkIsOverLap(Integer empId,Date startTime,Date endTime);
}
