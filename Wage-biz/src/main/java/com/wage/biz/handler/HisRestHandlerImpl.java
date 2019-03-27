/**
 * HisRestHandlerImpl.java 2018/1/10 10:11
 * Copyright ©2018 wondersgroup.com All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.wage.biz.handler;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wage.biz.mybatis.EmpDicBasicInfoMapper;
import com.wage.biz.mybatis.HisRestMapper;
import com.wage.biz.mybatis.HisRevokeApplyMapper;
import com.wage.contract.HisRestHandler;
import com.wage.model.HisRest;
import com.wage.model.HisRestKey;
import com.wage.model.HisRevokeApply;
import com.wage.model.dto.FullCalendarDto;
import com.wage.model.ext.*;
import com.wage.util.DateIsOverlapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;

/**
 * File：HisRestHandlerImpl.java<br>
 * Title: <br>
 * Description: <br>
 * Company: wondersgroup.com <br>
 * @author 何友池
 * @version 1.0
 */
@Component
public class HisRestHandlerImpl implements HisRestHandler {

    @Autowired
    HisRestMapper hisRestMapper;

    @Autowired
    EmpDicBasicInfoMapper empDicBasicInfoMapper;

    @Autowired
    HisRevokeApplyMapper hisRevokeApplyMapper;

    /**
     * 根据员工编号查询请假记录
     * @param page
     * @param empId
     * @return
     * @author 何友池
     */
    @Override
    public PageInfo<HisRestNExt> getByEmpid(PageInfo page, Integer empId, Date restStartTimeMin, Date restStartTimeMax){
        PageHelper.startPage(page);
        List<HisRestNExt> list = hisRestMapper.getByEmpid(empId, restStartTimeMin, restStartTimeMax);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     * 获取请假记录列表
     * @param empId
     * @return
     * @author 何友池
     */
    @Override
    public Map<String,Object> getRestByEmpid(Integer empId){
        List<HisRestNExt> list = hisRestMapper.getByEmpid(empId,null,null);
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
            dto.setAllDay(false);
            dto.setStart(list.get(i).getRestStartTime());
            dto.setEnd(list.get(i).getRestEndTime());
            dto.setTitle(list.get(i).getRestReason());
            dto.setId(empId);
            dto.setName(name);
            dto.setHours(new BigDecimal(list.get(i).getRestHour()));
            resultList.add(i,dto);
        }
        Map<String,Object> result = new HashMap<>();
        result.put("restList",resultList);
        return result;
    }

    /**
     * 更新一条请假信息数据
     * @param record
     * @author 何友池
     */
    @Override
    public void updateHisRest(HisRestExt record){
        hisRestMapper.updateHisRest(record);
    }

    /**
     * 删除一条请假记录
     * @param key
     * @author 何友池
     */
    @Override
    public void removeHisRest(HisRestKey key){
        hisRestMapper.deleteByPrimaryKey(key);
    }

    /**
     * 新增一套请假记录
     * @param record
     * @author 何友池
     */
    @Override
    public void saveHisRest(HisRest record){
        hisRestMapper.insert(record);
    }

    /**
     * 查询全部撤回请假申请数据
     * @return
     * @author 何友池
     */
    @Override
    public PageInfo<HisRevokeApplyExt> getRevokeApply(PageInfo page,Short isAllow){
        PageHelper.startPage(page);
        List<HisRevokeApplyExt> list = hisRevokeApplyMapper.findAll( isAllow);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     * 更新审批结果
     * @param isAllow
     * @param id
     * @author 何友池
     */
    @Override
    @Transactional
    public void updateRevokeApply(Short isAllow,Integer id){
        HisRevokeApply record = new HisRevokeApply();
        record.setId(id);
        record.setIsAllow(isAllow);
        hisRevokeApplyMapper.updateByPrimaryKeySelective(record);
        HisRevokeApply result = hisRevokeApplyMapper.selectByPrimaryKey(id);
        if(isAllow == 1){
            HisRestKey key = new HisRestKey();
            key.setEmpId(result.getEmpId());
            key.setRestStartTime(result.getRestTime());
            hisRestMapper.deleteByPrimaryKey(key);
        }
    }

    /**
     * 根据流水号获取员工编号
     * @param id
     * @return
     * @author 何友池
     */
    @Override
    public Integer getEmpId(Integer id){
        return hisRevokeApplyMapper.selectByPrimaryKey(id).getEmpId();
    }

    /**
     * 判断此员工的请假时间是否与数据库重叠
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
        List<HisRestNExt> list = hisRestMapper.getByEmpid(empId,null,null);
        for (HisRestNExt hisRestNExt : list) {
            System.out.println(hisRestNExt.getRestStartTime()+"  "+hisRestNExt.getRestEndTime());
            System.out.println(startTime+"  "+endTime);
            System.out.println("结果为"+DateIsOverlapUtil.isOverlap(hisRestNExt.getRestStartTime(),hisRestNExt.getRestEndTime(),startTime, endTime));
            if(DateIsOverlapUtil.isOverlap(hisRestNExt.getRestStartTime(),hisRestNExt.getRestEndTime(),startTime, endTime)){
                System.out.println("重叠");
                result = true;
            }
        }
        return result;
    }

    /**
     * 提交撤回请假申请
     * @param record
     * @author 何友池
     */
    @Override
    public void saveRestApply(HisRevokeApply record){
        hisRevokeApplyMapper.insert(record);
    }
}
