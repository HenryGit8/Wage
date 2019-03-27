/**
 * HisOverHandlerImpl.java 2018/1/17 14:18
 * Copyright ©2018 wondersgroup.com All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.wage.biz.handler;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wage.biz.mybatis.EmpDicBasicInfoMapper;
import com.wage.biz.mybatis.HisOverMapper;
import com.wage.contract.HisOverHandler;
import com.wage.model.HisOver;
import com.wage.model.HisOverKey;
import com.wage.model.dto.FullCalendarDto;
import com.wage.model.ext.HisOverExt;
import com.wage.model.ext.HisOverOExt;
import com.wage.model.ext.HisRestNExt;
import com.wage.util.DateIsOverlapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.*;

/**
 * File：HisOverHandlerImpl.java<br>
 * Title: <br>
 * Description: <br>
 * Company: wondersgroup.com <br>
 * @author 何友池
 * @version 1.0
 */
@Component
public class HisOverHandlerImpl implements HisOverHandler {

    @Autowired
    HisOverMapper hisOverMapper;

    @Autowired
    EmpDicBasicInfoMapper empDicBasicInfoMapper;

    /**
     * 根据员工编号查询加班记录表
     * @param page
     * @param empId
     * @param overTimeStartMin
     * @param overTimeStartMax
     * @return
     * @author 何友池
     */
    @Override
    public PageInfo<HisOverExt> getByEmpid(PageInfo page, Integer empId, Date overTimeStartMin, Date overTimeStartMax){
        PageHelper.startPage(page);
        List<HisOverExt> list = hisOverMapper.getByEmpid(empId, overTimeStartMin, overTimeStartMax);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     * 更新加班记录表
     * @param record
     * @author 何友池
     */
    @Override
    public void updateHisOver(HisOverOExt record){
        hisOverMapper.updateHisOver(record);
    }

    /**
     * 获取加班记录列表
     * @param empId
     * @return
     * @author 何友池
     */
    @Override
    public Map<String,Object> getOverByEmpid(Integer empId){
        List<HisOverExt> list = hisOverMapper.getByEmpid(empId,null,null);
        String name = empDicBasicInfoMapper.selectByPrimaryKey(empId).getEmpName();
        List<FullCalendarDto> resultList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            FullCalendarDto dto = new FullCalendarDto();
            /*Short isAllDay = list.get(i).getAllDay();
            if(isAllDay == 1){
                dto.setAllDay(true);
            }else{
                dto.setAllDay(false);
            }*/
            dto.setStart(list.get(i).getOverTimeStart());
            dto.setEnd(list.get(i).getOverTimeEnd());
            dto.setTitle(list.get(i).getOverReason());
            dto.setId(empId);
            dto.setName(name);
            dto.setHours(list.get(i).getOverHour());
            if(list.get(i).getIsCheck() == 0){
                dto.setColor("#5d5d5d");
            }else if(list.get(i).getIsCheck() == 1){
            }
            dto.setOverType(list.get(i).getOverType());
            resultList.add(i,dto);
        }
        Map<String,Object> result = new HashMap<>();
        result.put("restList",resultList);
        return result;
    }

    /**
     * 保存一条加班信息
     * @param hisOver
     * @author 何友池
     */
    @Override
    public void saveHisOver(HisOver hisOver){
        hisOverMapper.insert(hisOver);
    }

    /**
     * 删除一条加班信息
     * @param key
     * @author 何友池
     */
    @Override
    public void removeHisOver(HisOverKey key){
        hisOverMapper.deleteByPrimaryKey(key);
    }

    /**
     * 根据是否已审核查询
     * @param page
     * @param isCheck
     * @return
     * @author 何友池
     */
    @Override
    public PageInfo<HisOverExt> getByEmpidC(PageInfo page, Short isCheck){
        PageHelper.startPage(page);
        List<HisOverExt> list = hisOverMapper.getByEmpidC(isCheck);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     * 更新加班审核结果
     * @param empId
     * @param overTimeStart
     * @param isCheck
     * @author 何友池
     */
    @Override
    public void updateOverApply(Integer empId,Date overTimeStart,Short isCheck){
        HisOver record = new HisOver();
        record.setIsCheck(isCheck);
        record.setEmpId(empId);
        record.setOverTimeStart(overTimeStart);
        hisOverMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 判断此员工的加班时间是否与数据库重叠
     * @param empId
     * @param startTime
     * @param endTime
     * @return
     * @throws ParseException
     * @author 何友池
     */
    @Override
    public boolean checkIsOverLap(Integer empId,Date startTime,Date endTime) throws ParseException {
        boolean result = false;
        List<HisOverExt> list = hisOverMapper.getByEmpid(empId,null,null);
        for (HisOverExt hisOverExt : list) {
            if(DateIsOverlapUtil.isOverlap(hisOverExt.getOverTimeStart(),hisOverExt.getOverTimeEnd(),startTime, endTime)){
                result = true;
            }
        }
        return result;
    }
}
