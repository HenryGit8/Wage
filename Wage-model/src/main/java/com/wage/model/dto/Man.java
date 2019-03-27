/**
 * Man.java 2018/2/9 14:02
 * Copyright ©2018 wondersgroup.com All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.wage.model.dto;

/**
 * File：Man.java<br>
 * Title: <br>
 * Description: <br>
 * Company: wondersgroup.com <br>
 * @author 何友池
 * @version 1.0
 */
public class Man {
    private String name;
    private int sex;
    private String idCard;
    private float salary;
    public Man(String name, int sex, String idCard, float salary) {
        super();
        this.name = name;
        this.sex = sex;
        this.idCard = idCard;
        this.salary = salary;
    }

    public Man() {
        super();
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getSex() {
        return sex;
    }
    public void setSex(int sex) {
        this.sex = sex;
    }
    public String getIdCard() {
        return idCard;
    }
    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }
    public float getSalary() {
        return salary;
    }
    public void setSalary(float salary) {
        this.salary = salary;
    }


}