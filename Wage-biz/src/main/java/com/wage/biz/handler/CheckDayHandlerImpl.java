/**
 * CheckDayHandlerImpl.java 2018/1/22 9:19
 * Copyright ©2018 wondersgroup.com All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.wage.biz.handler;

import com.wage.biz.mybatis.*;
import com.wage.contract.CheckDayHandler;
import com.wage.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * File：CheckDayHandlerImpl.java<br>
 * Title: <br>
 * Description: <br>
 * Company: wondersgroup.com <br>
 * @author 何友池
 * @version 1.0
 */
@Component
public class CheckDayHandlerImpl implements CheckDayHandler {

    @Autowired
    SysHolidayMapper sysHolidayMapper;

    @Autowired
    SysSetMapper sysSetMapper;

    @Autowired
    EmpMonOtherSalMapper empMonOtherSalMapper;

    @Autowired
    HisOverMapper hisOverMapper;

    @Autowired
    EmpMonSalMapper empMonSalMapper;

    @Autowired
    HisRestMapper hisRestMapper;

    @Autowired
    EmpMonDeductSalMapper empMonDeductSalMapper;

    /**
     * 查询节假日(0:工作日,1:周休息日,2:节假日)
     * @return
     * @author 何友池
     */
    @Override
    public Integer getHoliday(Date day){
        Integer result = 1;
        List<SysHoliday> dayList = sysHolidayMapper.findHoliday();
        SysSet sysSet = sysSetMapper.selectByPrimaryKey("WORK_DAY");//查询工作日
        String[] strs=sysSet.getSetValue().split(",");
        SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd");
        String bdate = time.format(day);
        /*验证是否为数据库中的节假日*/
        for (SysHoliday sysHoliday : dayList) {
            if(time.format(sysHoliday.getDateTime()).equals(bdate)){
                result = 2;
            }
        }
        /*验证是否为数据库中的工作日*/
        Calendar cal = Calendar.getInstance();
        cal.setTime(day);
        if(result == 1){
            for(int i = 0; i < strs.length; i++){
                Integer week = cal.get(Calendar.DAY_OF_WEEK)-1;
                if(week == 0){
                    week = 7;
                }
                if(week == Integer.valueOf(strs[i])){
                    result = 0;
                }
            }
        }
        if(result == 1){
            List<SysHoliday> otherDayList = sysHolidayMapper.findWorkday();
            for (SysHoliday sysOtherWorkday : otherDayList) {
                if(bdate.equals(time.format(sysOtherWorkday.getDateTime()))){
                    result = 0;
                }
            }
        }
        return result;
    }

    /**
     * 判断是否为工作时间(0:工作时间,1:周休息日,2:节假日,3:日非工作时间)
     * @param time
     * @return
     * @author 何友池
     */
    @Override
    public Integer getWorkTime(Date time){
        if(getHoliday(time) != 0){
            return getHoliday(time);
        }
        Integer result = 3;
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(time);
        int hour = rightNow.get(Calendar.HOUR_OF_DAY);
        SysSet work_day_start_one = sysSetMapper.selectByPrimaryKey("WORK_DAY_START_ONE");//查询每天工作开始时间一
        SysSet work_day_end_one = sysSetMapper.selectByPrimaryKey("WORK_DAY_END_ONE");//查询每天工作结束时间一
        SysSet work_day_start_two = sysSetMapper.selectByPrimaryKey("WORK_DAY_START_TWO");//查询每天工作开始时间二
        SysSet work_day_end_two = sysSetMapper.selectByPrimaryKey("WORK_DAY_END_TWO");//查询每天工作结束时间二
        int startOne = Integer.valueOf(work_day_start_one.getSetValue());
        int endOne = Integer.valueOf(work_day_end_one.getSetValue());
        int startTwo = Integer.valueOf(work_day_start_two.getSetValue());
        int endTwo = Integer.valueOf(work_day_end_two.getSetValue());
        if((hour>=startOne)&&(hour<endOne)){
            result = 1;
        }else if ((hour>=startTwo)&&(hour<endTwo)){
            result = 1;
        }
        return result;
    }

    /**
     * 根据开始结束时间计算工作总时间
     * @param startTime
     * @param endTime
     * @return
     * @throws ParseException
     */
    @Override
    public double getWorkHours(Date startTime,Date endTime) throws ParseException {
        if(!compareDate(startTime,endTime)){
            return -1;
        }
        double allHours = 0;
        /* 从数据库获取工作时间*/
        SysSet work_day_start_one = sysSetMapper.selectByPrimaryKey("WORK_DAY_START_ONE");//查询每天工作开始时间一
        SysSet work_day_end_one = sysSetMapper.selectByPrimaryKey("WORK_DAY_END_ONE");//查询每天工作结束时间一
        SysSet work_day_start_two = sysSetMapper.selectByPrimaryKey("WORK_DAY_START_TWO");//查询每天工作开始时间二
        SysSet work_day_end_two = sysSetMapper.selectByPrimaryKey("WORK_DAY_END_TWO");//查询每天工作结束时间二
        int startOne = Integer.valueOf(work_day_start_one.getSetValue());
        int endOne = Integer.valueOf(work_day_end_one.getSetValue());
        int startTwo = Integer.valueOf(work_day_start_two.getSetValue());
        int endTwo = Integer.valueOf(work_day_end_two.getSetValue());
        /*格式化为日期*/
        DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = dateFormat1.parse(dateFormat1.format(startTime));
        Date endDate = dateFormat1.parse(dateFormat1.format(endTime));
        /*获取小时*/
        Calendar e = Calendar.getInstance();
        e.setTime(startTime);
        int startTimeHours = e.get(Calendar.HOUR_OF_DAY);
        e.setTime(endTime);
        int endTimeHours = e.get(Calendar.HOUR_OF_DAY);
        /*开始结束当天工作小时计算*/
        if(!beginDate.equals(endDate)){
            System.out.println("开始结束当天工作小时计算");
            Calendar d = Calendar.getInstance();
            if(getHoliday(beginDate) ==0){
                Date datetime = startTime;
                int hours = startTimeHours;
                while (hours != 24) {
                    System.out.println("开始正向计算");
                    if((hours>=startOne)&&(hours<endOne)){
                        allHours = allHours + 1;
                    }else if ((hours>=startTwo)&&(hours<endTwo)){
                        allHours = allHours + 1;
                    }
                    d.setTime(datetime);
                    d.add(Calendar.HOUR_OF_DAY, 1); // 日期加1小时
                    datetime = d.getTime();
                    hours = hours+1;
                    System.out.println("时间为"+datetime+"的工作小时数为"+allHours);
                }
            }
            if(getHoliday(endDate) ==0){
                Date datetime1 = endTime;
                int hours1 = endTimeHours-1;
                while (hours1 != -1) {
                    System.out.println("开始反向计算");
                    if((hours1>=startOne)&&(hours1<endOne)){
                        allHours = allHours + 1;
                    }else if ((hours1>=startTwo)&&(hours1<endTwo)){
                        allHours = allHours + 1;
                    }
                    d.setTime(datetime1);
                    d.add(Calendar.HOUR_OF_DAY, -1); // 日期加1小时
                    datetime1 = d.getTime();
                    hours1 = hours1-1;
                    System.out.println(hours1);
                    System.out.println("时间为"+datetime1+"的工作小时数为"+allHours);
                }
            }
        }else if(getHoliday(beginDate) ==0 && beginDate.equals(endDate)){
            System.out.println("开始结束同天时，当天工作小时计算");
            Calendar d = Calendar.getInstance();
            Date datetime = startTime;
            int hours = startTimeHours;
            while (hours != endTimeHours) {
                System.out.println(allHours);
                if((hours>=startOne)&&(hours<endOne)){
                    allHours = allHours + 1;
                }else if ((hours>=startTwo)&&(hours<endTwo)){
                    allHours = allHours + 1;
                }
                d.setTime(datetime);
                d.add(Calendar.HOUR_OF_DAY, 1); // 日期加1小时
                datetime = d.getTime();
                hours = hours+1;
                System.out.println("时间为"+datetime+"的工作小时数为"+allHours);
            }
        }
        /* 定义时间变量*/
        Date date = beginDate;
        Calendar c = Calendar.getInstance();
        /*非开始与结束天工作小时计算*/
        while (!date.equals(endDate)) {
            c.setTime(date);
            c.add(Calendar.DATE, 1); // 日期加1天
            date = c.getTime();
            if(getHoliday(date) == 0 && !date.equals(endDate)){
                allHours = allHours+ (endOne-startOne) + (endTwo-startTwo);
            }
            System.out.println("时间为"+date+"的工作小时数为"+allHours);
        }
        return allHours;
    }

    /**
     * 判断一个区间是否包含工作时间
     * 0：不包含，1：包含,2：开始时间不大于结束时间
     * @param startTime
     * @param endTime
     * @return
     * @author 何友池
     */
    @Override
    public Integer getWorkTimeQj(Date startTime,Date endTime) throws ParseException {
        //开始时间必须小于结束时间
        if(!compareDate(startTime,endTime)){
            return 2;
        }
        Integer result = 0;
        /* 从数据库获取工作时间*/
        SysSet work_day_start_one = sysSetMapper.selectByPrimaryKey("WORK_DAY_START_ONE");//查询每天工作开始时间一
        SysSet work_day_end_one = sysSetMapper.selectByPrimaryKey("WORK_DAY_END_ONE");//查询每天工作结束时间一
        SysSet work_day_start_two = sysSetMapper.selectByPrimaryKey("WORK_DAY_START_TWO");//查询每天工作开始时间二
        SysSet work_day_end_two = sysSetMapper.selectByPrimaryKey("WORK_DAY_END_TWO");//查询每天工作结束时间二
        int startOne = Integer.valueOf(work_day_start_one.getSetValue());
        int endOne = Integer.valueOf(work_day_end_one.getSetValue());
        int startTwo = Integer.valueOf(work_day_start_two.getSetValue());
        int endTwo = Integer.valueOf(work_day_end_two.getSetValue());
        /*格式化为日期*/
        DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = dateFormat1.parse(dateFormat1.format(startTime));
        Date endDate = dateFormat1.parse(dateFormat1.format(endTime));
        /*获取开始时间小时*/
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(startTime);
        int hour;
        Date time = startTime;
        Date time1 = endTime;
        Calendar e = Calendar.getInstance();
        e.setTime(startTime);
        int startTimeHours = e.get(Calendar.HOUR_OF_DAY);
        e.setTime(endTime);
        int endTimeHours = e.get(Calendar.HOUR_OF_DAY);
        if(getHoliday(startTime) == 0){
            System.out.println("开始进行工作日正向验证");
            Calendar d = Calendar.getInstance();
            /*正向验证*/
            hour = rightNow.get(Calendar.HOUR_OF_DAY);
            if(beginDate.equals(endDate)){
                while (hour <endTimeHours && result!=1) {
                    System.out.println(hour);
                    if((hour>=startOne)&&(hour<endOne)){
                        System.out.println("第"+hour+"个小时为工作时间");
                        result = 1;
                    }else if ((hour>=startTwo)&&(hour<endTwo)){
                        System.out.println("第"+hour+"个小时为工作时间");
                        result = 1;
                    }
                    d.setTime(time);
                    d.add(Calendar.HOUR_OF_DAY, 1); // 日期加1小时
                    time = d.getTime();
                    hour = hour+1;
                }
            }else{
                while (hour<24 && result!=1) {
                    System.out.println(hour);
                    if((hour>=startOne)&&(hour<endOne)){
                        System.out.println("第"+hour+"个小时为工作时间");
                        result = 1;
                    }else if ((hour>=startTwo)&&(hour<endTwo)){
                        System.out.println("第"+hour+"个小时为工作时间");
                        result = 1;
                    }
                    d.setTime(time);
                    d.add(Calendar.HOUR_OF_DAY, 1); // 日期加1小时
                    time = d.getTime();
                    hour = hour+1;
                }
            }
        }
        rightNow.setTime(endTime);
        if(getHoliday(endTime) == 0){
            System.out.println("开始进行工作日反向验证");
            Calendar d = Calendar.getInstance();
            /*反向验证*/
            hour = rightNow.get(Calendar.HOUR_OF_DAY)-1;
            if(beginDate.equals(endDate)){
                while (hour >startTimeHours && result!=1) {
                    System.out.println("-"+hour);
                    if((hour>=startOne)&&(hour<endOne)){
                        System.out.println("第"+hour+"个小时为工作时间");
                        result = 1;
                    }else if ((hour>=startTwo)&&(hour<endTwo)){
                        System.out.println("第"+hour+"个小时为工作时间");
                        result = 1;
                    }
                    d.setTime(time1);
                    d.add(Calendar.HOUR_OF_DAY, -1); // 日期加1小时
                    time1 = d.getTime();
                    hour = hour-1;
                }
            }else {
                while (hour>-1 && result!=1) {
                    System.out.println("-1 "+hour);
                    if((hour>=startOne)&&(hour<endOne)){
                        System.out.println("第"+hour+"个小时为工作时间");
                        result = 1;
                    }else if ((hour>=startTwo)&&(hour<endTwo)){
                        System.out.println("第"+hour+"个小时为工作时间");
                        result = 1;
                    }
                    d.setTime(time1);
                    d.add(Calendar.HOUR_OF_DAY, -1); // 日期加1小时
                    time1 = d.getTime();
                    hour = hour-1;
                }
            }
        }
        /* 开始验证中间日期是否有工作日*/
        Calendar c = Calendar.getInstance();
        Date date = beginDate;
        c.setTime(date);
        c.add(Calendar.DATE, 1); // 日期加1天
        date = c.getTime();
        if(!beginDate.equals(endDate) && result != 1){
            //验证中间是否有工作日
            System.out.println("开始进行假期工作日验证");
            while (!date.equals(endDate) && result!=1) {
                System.out.println(date);
                if(getHoliday(date) == 0){
                    System.out.println(dateFormat1.format(date)+"为工作日");
                    result = 1;
                }
                c.setTime(date);
                c.add(Calendar.DATE, 1); // 日期加1天
                date = c.getTime();
            }
        }
        return result;
    }

    /**
     * 比较是否开始日期小于结束日期
     * @param startTime
     * @param endTime
     * @return
     * @author 何友池
     */
    public boolean compareDate(Date startTime,Date endTime){
        if(startTime.compareTo(endTime)<0){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 判断一个区间是否包含非工作时间
     * 0：不包含，1：包含,2：开始时间不大于结束时间
     * @param startTime
     * @param endTime
     * @return
     * @author 何友池
     */
    @Override
    public Integer getWorkTimeQjN(Date startTime,Date endTime) throws ParseException {
        //开始时间必须小于结束时间
        if(!compareDate(startTime,endTime)){
            return 2;
        }
        Integer result = 0;
        /* 从数据库获取工作时间*/
        SysSet work_day_start_one = sysSetMapper.selectByPrimaryKey("WORK_DAY_START_ONE");//查询每天工作开始时间一
        SysSet work_day_end_one = sysSetMapper.selectByPrimaryKey("WORK_DAY_END_ONE");//查询每天工作结束时间一
        SysSet work_day_start_two = sysSetMapper.selectByPrimaryKey("WORK_DAY_START_TWO");//查询每天工作开始时间二
        SysSet work_day_end_two = sysSetMapper.selectByPrimaryKey("WORK_DAY_END_TWO");//查询每天工作结束时间二
        int startOne = Integer.valueOf(work_day_start_one.getSetValue());
        int endOne = Integer.valueOf(work_day_end_one.getSetValue());
        int startTwo = Integer.valueOf(work_day_start_two.getSetValue());
        int endTwo = Integer.valueOf(work_day_end_two.getSetValue());
        /*格式化为日期*/
        DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = dateFormat1.parse(dateFormat1.format(startTime));
        Date endDate = dateFormat1.parse(dateFormat1.format(endTime));
        /*获取开始时间小时*/
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(startTime);
        int hour;
        Date time = startTime;
        Date time1 = endTime;
        Calendar e = Calendar.getInstance();
        e.setTime(startTime);
        int startTimeHours = e.get(Calendar.HOUR_OF_DAY);
        e.setTime(endTime);
        int endTimeHours = e.get(Calendar.HOUR_OF_DAY);
        if(getHoliday(startTime) == 0){
            System.out.println("开始进行非工作日正向验证");
            Calendar d = Calendar.getInstance();
            /*正向验证*/
            hour = rightNow.get(Calendar.HOUR_OF_DAY);
            if(beginDate.equals(endDate)){
                while (hour !=endTimeHours && result!=1) {
                    System.out.println(hour);
                    if((hour<startOne)&&(hour>0)){
                        System.out.println("第"+hour+"个小时为非工作时间");
                        result = 1;
                    }else if((hour<startTwo)&&(hour>endOne)){
                        System.out.println("第"+hour+"个小时为非工作时间");
                        result = 1;
                    }else if ((hour<24)&&(hour>endTwo)){
                        System.out.println("第"+hour+"个小时为非工作时间");
                        result = 1;
                    }
                    d.setTime(time);
                    d.add(Calendar.HOUR_OF_DAY, 1); // 日期加1小时
                    time = d.getTime();
                    hour = hour+1;
                }
            }else{
                while (hour!=24 && result!=1) {
                    System.out.println(hour);
                    if((hour<startOne)&&(hour>=0)){
                        System.out.println("第"+hour+"个小时为非工作时间");
                        result = 1;
                    }else if((hour<startTwo)&&(hour>endOne)){
                        System.out.println("第"+hour+"个小时为非工作时间");
                        result = 1;
                    }else if ((hour<24)&&(hour>endTwo)){
                        System.out.println("第"+hour+"个小时为非工作时间");
                        result = 1;
                    }
                    d.setTime(time);
                    d.add(Calendar.HOUR_OF_DAY, 1); // 日期加1小时
                    time = d.getTime();
                    hour = hour+1;
                }
            }
        }
        rightNow.setTime(endTime);
        if(getHoliday(endTime) == 0){
            System.out.println("开始进行非工作日反向验证");
            Calendar d = Calendar.getInstance();
            /*反向验证*/
            hour = rightNow.get(Calendar.HOUR_OF_DAY);
            if(beginDate.equals(endDate)){
                while (hour !=startTimeHours && result!=1) {
                    System.out.println("-"+hour);
                    if((hour<startOne)&&(hour>=0)){
                        System.out.println("第"+hour+"个小时为非工作时间");
                        result = 1;
                    }else if((hour<startTwo)&&(hour>endOne)){
                        System.out.println("第"+hour+"个小时为非工作时间");
                        result = 1;
                    }else if ((hour<24)&&(hour>endTwo)){
                        System.out.println("第"+hour+"个小时为非工作时间");
                        result = 1;
                    }
                    d.setTime(time1);
                    d.add(Calendar.HOUR_OF_DAY, -1); // 日期加1小时
                    time1 = d.getTime();
                    hour = hour-1;
                }
            }else {
                while (hour!=0 && result!=1) {
                    System.out.println("-1 "+hour);
                    if((hour<startOne)&&(hour>=0)){
                        System.out.println("第"+hour+"个小时为非工作时间");
                        result = 1;
                    }else if((hour<startTwo)&&(hour>endOne)){
                        System.out.println("第"+hour+"个小时为非工作时间");
                        result = 1;
                    }else if ((hour<24)&&(hour>endTwo)){
                        System.out.println("第"+hour+"个小时为非工作时间");
                        result = 1;
                    }
                    d.setTime(time1);
                    d.add(Calendar.HOUR_OF_DAY, -1); // 日期加1小时
                    time1 = d.getTime();
                    hour = hour-1;
                }
            }
        }
        /* 开始验证中间日期是否有工作日*/
        Calendar c = Calendar.getInstance();
        Date date = beginDate;
        c.setTime(date);
        c.add(Calendar.DATE, 1); // 日期加1天
        date = c.getTime();
        if(!beginDate.equals(endDate) && result != 1){
            //验证中间是否有工作日
            System.out.println("开始进行假期非工作日验证");
            while (!date.equals(endDate) && result!=1) {
                System.out.println(date);
                if(getHoliday(date) != 0){
                    System.out.println(dateFormat1.format(date)+"为非工作日");
                    result = 1;
                }
                c.setTime(date);
                c.add(Calendar.DATE, 1); // 日期加1天
                date = c.getTime();
            }
        }
        return result;
    }

    /**
     * 获取每月出账日
     * @return
     * @author 何友池
     */
    @Override
    public Integer getOutDay(){
        SysSet outOfAccountDay = sysSetMapper.selectByPrimaryKey("OUT_OF_ACCOUNT_DAY");
        return Integer.parseInt(outOfAccountDay.getSetValue());
    }

    /**
     * 加班后更新一条员工记录
     * @param empId
     * @throws ParseException
     * @author 何友池
     */
    @Override
    public void updateOverWage(Integer empId) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
        Integer yearMonth = Integer.parseInt(sdf.format(new Date()));//本月
        List<HisOver> overWage = hisOverMapper.findThisMonthData(empId);
        BigDecimal normalDat = new BigDecimal(sysSetMapper.selectByPrimaryKey("NORMALDAY_OVERTIME_PAY").getSetValue());
        BigDecimal restDat = new BigDecimal(sysSetMapper.selectByPrimaryKey("RESTDAY_OVERTIME_PAY").getSetValue());
        BigDecimal HoliDayDat = new BigDecimal(sysSetMapper.selectByPrimaryKey("HOLIDAY_OVERTIME_PAY").getSetValue());
        /*开始计算加班时间*/
        BigDecimal overtimePay = new BigDecimal(0);
        Date monthStart = sdf1.parse(sdf1.format(new Date()).substring(0,6)+"01");//本月第一天
        Calendar date = Calendar.getInstance();
        date.setTime(monthStart);
        date.add(date.MONTH, 1);
        Date nextMonthStart = date.getTime();//下月第一天
        Integer nextyearMonth = Integer.parseInt(sdf.format(nextMonthStart));//下个月
        date.setTime(monthStart);
        date.add(date.MONTH, -1);
        Date lastMonthStart = date.getTime();//上月第一天
        date.setTime(nextMonthStart);
        date.add(date.HOUR, -1);
        Date monthEnd = date.getTime();//这个月最后一天晚上
        Integer lastyearMonth = Integer.parseInt(sdf.format(lastMonthStart));//上个月
        double workAllHours = getWorkHours(monthStart,monthEnd);//本月总工作时间
        Integer total = empMonSalMapper.selectTotal(empId);
        BigDecimal everyHours = new BigDecimal(total).divide(new BigDecimal(workAllHours),1,RoundingMode.HALF_UP);//每小时工作费用
        for (HisOver hisOver : overWage) {
            Integer startTime = Integer.parseInt(sdf.format(hisOver.getOverTimeStart()));
            Integer endTime = Integer.parseInt(sdf.format(hisOver.getOverTimeEnd()));
            System.out.println(everyHours+"  "+normalDat+" "+overtimePay);
            if(!startTime.equals(yearMonth) && endTime.equals(yearMonth)){
                BigDecimal hour = new BigDecimal(getWorkHours(monthStart,hisOver.getOverTimeEnd()));
                if(hisOver.getOverType() == 1){
                    overtimePay = hour.multiply(everyHours).multiply(normalDat).add(overtimePay).setScale(1, RoundingMode.HALF_UP);
                }else if(hisOver.getOverType() == 2){
                    overtimePay = hour.multiply(everyHours).multiply(restDat).add(overtimePay).setScale(1, RoundingMode.HALF_UP);
                }else {
                    overtimePay = hour.multiply(everyHours).multiply(HoliDayDat).add(overtimePay).setScale(1, RoundingMode.HALF_UP);
                }
            }else if(startTime.equals(yearMonth) && !endTime.equals(yearMonth)){
                BigDecimal hour = new BigDecimal(getWorkHours(hisOver.getOverTimeStart(),nextMonthStart));
                if(hisOver.getOverType() == 1){
                    overtimePay = hour.multiply(everyHours).multiply(normalDat).add(overtimePay).setScale(1, RoundingMode.HALF_UP);
                }else if(hisOver.getOverType() == 2){
                    overtimePay = hour.multiply(everyHours).multiply(restDat).add(overtimePay).setScale(1, RoundingMode.HALF_UP);
                }else {
                    overtimePay = hour.multiply(everyHours).multiply(HoliDayDat).add(overtimePay).setScale(1, RoundingMode.HALF_UP);
                }
            }else if(!startTime.equals(yearMonth) && !endTime.equals(yearMonth) && startTime.equals(lastyearMonth) && endTime.equals(nextyearMonth)){
                if(hisOver.getOverType() == 1){
                    overtimePay = new BigDecimal(workAllHours).multiply(everyHours).multiply(normalDat).add(overtimePay).setScale(1, RoundingMode.HALF_UP);
                }else if(hisOver.getOverType() == 2){
                    overtimePay = new BigDecimal(workAllHours).multiply(everyHours).multiply(restDat).add(overtimePay).setScale(1, RoundingMode.HALF_UP);
                }else {
                    overtimePay = new BigDecimal(workAllHours).multiply(everyHours).multiply(HoliDayDat).add(overtimePay).setScale(1, RoundingMode.HALF_UP);
                }
            }else{
                if(hisOver.getOverType() == 1){
                    overtimePay = new BigDecimal(hisOver.getOverHour().doubleValue()).multiply(everyHours).multiply(normalDat).add(overtimePay).setScale(1, RoundingMode.HALF_UP);
                }else if(hisOver.getOverType() == 2){
                    overtimePay = new BigDecimal(hisOver.getOverHour().doubleValue()).multiply(everyHours).multiply(restDat).add(overtimePay).setScale(1, RoundingMode.HALF_UP);
                }else {
                    overtimePay = new BigDecimal(hisOver.getOverHour().doubleValue()).multiply(everyHours).multiply(HoliDayDat).add(overtimePay).setScale(1, RoundingMode.HALF_UP);
                }
            }
        }
        EmpMonOtherSal record = new EmpMonOtherSal();
        record.setEmpId(empId);
        record.setYearMonth(yearMonth);
        record.setOvertimePay(overtimePay);
        if(empMonOtherSalMapper.selectByPrimaryKey(record) != null){
            empMonOtherSalMapper.updateByPrimaryKeySelective(record);
        }else {
            record.setMonthAllowance(new BigDecimal(0));
            record.setMonthBonus(new BigDecimal(0));
            record.setMonthSubsidy(new BigDecimal(0));
            record.setOtherSal(overtimePay);
            record.setYearEndBonus(new BigDecimal(0));
            empMonOtherSalMapper.insert(record);
        }
        empMonOtherSalMapper.updateOtherTotal(empId);
    }

    /**
     * 更新扣除工资表中本员工本月数据
     * @param empId
     * @throws ParseException
     * @author 何友池
     */
    @Override
    public void updateRestWage(Integer empId) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
        Integer yearMonth = Integer.parseInt(sdf.format(new Date()));//本月
        List<HisRest> restList = hisRestMapper.findThisMonthData(empId);
        Date monthStart = sdf1.parse(sdf1.format(new Date()).substring(0,6)+"01");//本月第一天
        Calendar date = Calendar.getInstance();
        date.setTime(monthStart);
        date.add(date.MONTH, 1);
        Date nextMonthStart = date.getTime();//下月第一天
        Integer nextyearMonth = Integer.parseInt(sdf.format(nextMonthStart));//下个月
        date.setTime(monthStart);
        date.add(date.MONTH, -1);
        Date lastMonthStart = date.getTime();//上月第一天
        date.setTime(nextMonthStart);
        date.add(date.HOUR, -1);
        Date monthEnd = date.getTime();//这个月最后一天晚上
        Integer lastyearMonth = Integer.parseInt(sdf.format(lastMonthStart));//上个月
        double workAllHours = getWorkHours(monthStart,monthEnd);//本月总工作时间
        Integer total = empMonSalMapper.selectTotal(empId);
        BigDecimal everyHours = new BigDecimal(total).divide(new BigDecimal(workAllHours),1,RoundingMode.HALF_UP);//每小时工作费用
        BigDecimal restTimePay = new BigDecimal(0);
        for (HisRest hisRest : restList) {
            Integer startTime = Integer.parseInt(sdf.format(hisRest.getRestStartTime()));
            Integer endTime = Integer.parseInt(sdf.format(hisRest.getRestEndTime()));
            if(!startTime.equals(yearMonth) && endTime.equals(yearMonth)){
                BigDecimal hour = new BigDecimal(getHours(monthStart,hisRest.getRestEndTime()));
                restTimePay = hour.multiply(everyHours).add(restTimePay).setScale(1, RoundingMode.HALF_UP);
            }else if(startTime.equals(yearMonth) && !endTime.equals(yearMonth)){
                BigDecimal hour = new BigDecimal(getHours(hisRest.getRestStartTime(),nextMonthStart));
                restTimePay = hour.multiply(everyHours).add(restTimePay).setScale(1, RoundingMode.HALF_UP);
            }else if(!startTime.equals(yearMonth) && !endTime.equals(yearMonth) && startTime.equals(lastyearMonth) && endTime.equals(nextyearMonth)){
                restTimePay = new BigDecimal(workAllHours).multiply(everyHours).add(restTimePay).setScale(1, RoundingMode.HALF_UP);
            }else{
                restTimePay = new BigDecimal(hisRest.getRestHour()).multiply(everyHours).add(restTimePay).setScale(1, RoundingMode.HALF_UP);
            }
        }
        EmpMonDeductSal record = new EmpMonDeductSal();
        record.setEmpId(empId);
        record.setYearMonth(yearMonth);
        record.setRestSalary(restTimePay);
        if(empMonDeductSalMapper.selectByPrimaryKey(record) != null){
            empMonDeductSalMapper.updateByPrimaryKeySelective(record);
        }else {
            record.setFineSalary(new BigDecimal(0));
            record.setDeductSal(new BigDecimal(0));
            empMonDeductSalMapper.insert(record);
        }
        empMonDeductSalMapper.updateTotal(empId);
    }

    /**
     * 根据两个日期获取小时差值
     * @param d1
     * @param d2
     * @return
     * @author 何友池
     */
    public double getHours(Date d1,Date d2){
        try
        {
            long diff = d1.getTime() - d2.getTime();
            return diff / (1000 * 60 * 60);
        }
        catch (Exception e)
        {
            return 0;
        }
    }
}
