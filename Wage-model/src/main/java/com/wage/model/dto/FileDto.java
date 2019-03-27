/**
 * FileDto.java 2018/3/1 16:18
 * Copyright ©2018 wondersgroup.com All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.wage.model.dto;

import java.io.Serializable;

/**
 * File：FileDto.java<br>
 * Title: <br>
 * Description: <br>
 * Company: wondersgroup.com <br>
 * @author 何友池
 * @version 1.0
 */
public class FileDto implements Serializable {
    private byte[] fileByte;
    private String fileName;

    public FileDto() {
    }

    public FileDto(byte[] fileByte, String fileName) {
        this.fileByte = fileByte;
        this.fileName = fileName;
    }

    public byte[] getFileByte() {
        return this.fileByte;
    }

    public void setFileByte(byte[] fileByte) {
        this.fileByte = fileByte;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
