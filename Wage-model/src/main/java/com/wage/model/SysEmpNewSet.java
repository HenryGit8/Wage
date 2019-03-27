package com.wage.model;

import java.io.Serializable;

public class SysEmpNewSet extends SysEmpNewSetKey implements Serializable{
    /**
     * 主题文字 数据库字段是：THEME_TEXT  <br>
     */
    private String themeText;

    /**
     * 网络图片地址 数据库字段是：IMG_URL  <br>
     */
    private String imgUrl;

    /**
     * 本地图片MD5码 数据库字段是：IMG_MD  <br>
     */
    private String imgMd;

    /**
     * 是否网络图片 数据库字段是：IS_NETWORK_IMG  <br>
     */
    private Short isNetworkImg;

    /**
     * 链接 数据库字段是：NEW_URL  <br>
     */
    private String newUrl;

    /**
     * 获取主题文字
     * @return SYS_EMP_NEW_SET.THEME_TEXT
     */
    public String getThemeText() {
        return themeText;
    }

    /**
     * 设置主题文字
     * @param themeText 主题文字
     */
    public void setThemeText(String themeText) {
        this.themeText = themeText == null ? null : themeText.trim();
    }

    /**
     * 获取网络图片地址
     * @return SYS_EMP_NEW_SET.IMG_URL
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * 设置网络图片地址
     * @param imgUrl 网络图片地址
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    /**
     * 获取本地图片MD5码
     * @return SYS_EMP_NEW_SET.IMG_MD
     */
    public String getImgMd() {
        return imgMd;
    }

    /**
     * 设置本地图片MD5码
     * @param imgMd 本地图片MD5码
     */
    public void setImgMd(String imgMd) {
        this.imgMd = imgMd == null ? null : imgMd.trim();
    }

    /**
     * 获取是否网络图片
     * @return SYS_EMP_NEW_SET.IS_NETWORK_IMG
     */
    public Short getIsNetworkImg() {
        return isNetworkImg;
    }

    /**
     * 设置是否网络图片
     * @param isNetworkImg 是否网络图片
     */
    public void setIsNetworkImg(Short isNetworkImg) {
        this.isNetworkImg = isNetworkImg;
    }

    /**
     * 获取链接
     * @return SYS_EMP_NEW_SET.NEW_URL
     */
    public String getNewUrl() {
        return newUrl;
    }

    /**
     * 设置链接
     * @param newUrl 链接
     */
    public void setNewUrl(String newUrl) {
        this.newUrl = newUrl == null ? null : newUrl.trim();
    }
}