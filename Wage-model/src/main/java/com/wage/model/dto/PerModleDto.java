/**
 * PerModleDto.java 2018/1/5 15:57
 * Copyright ©2018 wondersgroup.com All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.wage.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * File：PerModleDto.java<br>
 * Title: <br>
 * Description: <br>
 * Company: wondersgroup.com <br>
 * @author 何友池
 * @version 1.0
 */
public class PerModleDto implements Serializable {

    private String name;

    private BigDecimal y;

    private boolean sliced ;

    private boolean selected;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getY() {
        return y;
    }

    public void setY(BigDecimal y) {
        this.y = y;
    }

    public boolean isSliced() {
        return sliced;
    }

    public void setSliced(boolean sliced) {
        this.sliced = sliced;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public String toString() {
        return "PerModleDto{" +
                "name='" + name + '\'' +
                ", y=" + y +
                ", sliced=" + sliced +
                ", selected=" + selected +
                '}';
    }
}
