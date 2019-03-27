package com.wage.model;

import java.io.Serializable;

public class ViewAddress implements Serializable {
    /**
     * 编号 数据库字段是：ID  <br>
     */
    private Short id;

    /**
     * 地址 数据库字段是：ADDRESS  <br>
     */
    private String address;

    /**
     * 名称 数据库字段是：NAME  <br>
     */
    private String name;

    /**
     * 获取编号
     * @return VIEW_ADDRESS.ID
     */
    public Short getId() {
        return id;
    }

    /**
     * 设置编号
     * @param id 编号
     */
    public void setId(Short id) {
        this.id = id;
    }

    /**
     * 获取地址
     * @return VIEW_ADDRESS.ADDRESS
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置地址
     * @param address 地址
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * 获取名称
     * @return VIEW_ADDRESS.NAME
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}