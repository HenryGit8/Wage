package com.wage.model;

import java.io.Serializable;

public class SysEmpNewSetKey implements Serializable {
    /**
     * 是否滚轮图 数据库字段是：IS_ROLLER_GRAPH  <br>
     */
    private Short isRollerGraph;

    /**
     * 序号 数据库字段是：SERIAL_NUM  <br>
     */
    private Short serialNum;

    /**
     * 获取是否滚轮图
     * @return SYS_EMP_NEW_SET.IS_ROLLER_GRAPH
     */
    public Short getIsRollerGraph() {
        return isRollerGraph;
    }

    /**
     * 设置是否滚轮图
     * @param isRollerGraph 是否滚轮图
     */
    public void setIsRollerGraph(Short isRollerGraph) {
        this.isRollerGraph = isRollerGraph;
    }

    /**
     * 获取序号
     * @return SYS_EMP_NEW_SET.SERIAL_NUM
     */
    public Short getSerialNum() {
        return serialNum;
    }

    /**
     * 设置序号
     * @param serialNum 序号
     */
    public void setSerialNum(Short serialNum) {
        this.serialNum = serialNum;
    }
}