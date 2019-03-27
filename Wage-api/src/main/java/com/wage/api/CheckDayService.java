/**
 * CheckDayService.java 2018/1/22 10:54
 * Copyright ©2018 wondersgroup.com All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.wage.api;

import java.util.Date;

/**
 * File：CheckDayService.java<br>
 * Title: <br>
 * Description: <br>
 * Company: wondersgroup.com <br>
 * @author 何友池
 * @version 1.0
 */
public interface CheckDayService {
    /**
     * 查询节假日(0:工作日,1:公司周休息日,2:节假日)
     * @return
     * @author 何友池
     */
    Integer getHoliday(Date day);
    /**
     * 判断是否为工作时间(0:不为工作时间,1:是工作时间)
     * @param time
     * @return
     * @author 何友池
     */
    Integer getWorkTime(Date time);
    /**
     * 判断一个区间是否包含工作时间
     * 0：不包含，1：包含,2：开始时间不大于结束时间
     * @param startTime
     * @param endTime
     * @return
     * @author 何友池
     */
    Integer getWorkTimeQj(Date startTime,Date endTime);
    /**
     * 判断一个区间是否包含非工作时间
     * 0：不包含，1：包含,2：开始时间不大于结束时间
     * @param startTime
     * @param endTime
     * @return
     * @author 何友池
     */
    Integer getWorkTimeQjN(Date startTime,Date endTime);
    /**
     * 根据开始结束时间计算工作总时间
     * @param startTime
     * @param endTime
     * @return
     */
    double getWorkHours(Date startTime,Date endTime);
}
