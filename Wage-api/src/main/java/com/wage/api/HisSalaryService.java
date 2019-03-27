/**
 * HisSalaryService.java 2018/2/7 14:29
 * Copyright ©2018 wondersgroup.com All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.wage.api;

import com.github.pagehelper.PageInfo;
import com.wage.model.HisSalary;
import com.wage.model.dto.EasyuiComboBoxDto;
import com.wage.model.ext.HisSalaryDataExt;
import com.wage.model.ext.HisSalaryExt;

import java.util.List;
import java.util.Map;

/**
 * File：HisSalaryService.java<br>
 * Title: <br>
 * Description: <br>
 * Company: wondersgroup.com <br>
 * @author 何友池
 * @version 1.0
 */
public interface HisSalaryService {

    /**
     * 根据查询条件查询月工资明细
     * @param page
     * @param hisSalaryDataExt
     * @return
     * @author 何友池
     */
    PageInfo<HisSalaryExt> getBySearch(PageInfo page, HisSalaryDataExt hisSalaryDataExt);

    /**
     * 更新并计算五险一金及税率
     * @param yearMonth
     * @author 何友池
     */
    void updateHisSalary(Integer yearMonth);
    /**
     * 更新一条数据
     * @param record
     * @author 何友池
     */
    void updateData(HisSalary record);
    /**
     * 获取月集合返回easyui下拉框数据
     * @return
     * @author 何友池
     */
    List<EasyuiComboBoxDto> getAllMonth();
    /**
     * 获取部门集合返回easyui下拉框数据
     * @return
     * @author 何友池
     */
    List<EasyuiComboBoxDto> getAllDepart();
    /**
     * 根据查询条件查询月工资明细
     * @param hisSalaryDataExt
     * @return
     * @author 何友池
     */
    List<HisSalaryExt> getListBySearch(HisSalaryDataExt hisSalaryDataExt);
    /**
     * 查询图表数据集
     * @return
     * @author 何友池
     */
    Map<String,Object> getDataChart();
    /**
     * 查询统计表数据
     * @param yearMonth
     * @return
     * @author 何友池
     */
    Map<String,Object> getTotalByMonth(Integer yearMonth);
    /**
     * 插入初始化数据
     * @param yearMonth
     * @author 何友池
     */
    void updateNewDeductOther(Integer yearMonth);
}
