/**
 * FileHandler.java 2018/3/1 16:17
 * Copyright ©2018 wondersgroup.com All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.wage.contract;

import com.wage.model.dto.FileDto;

import java.util.List;

/**
 * File：FileHandler.java<br>
 * Title: <br>
 * Description: <br>
 * Company: wondersgroup.com <br>
 * @author 何友池
 * @version 1.0
 */
public interface FileHandler {
    /**
     * 文件上传
     * @param fileDto
     * @return
     * @author shenlx
     */
    String uploadFile(FileDto fileDto);
    /**
     * 文件批量上传
     * @param fileDtoList
     * @return 文件上传成功后返回的MD5集合
     * @author shenlx
     */
    List<String> uploadFile(List<FileDto> fileDtoList);
    /**
     * 文件下载
     * @param md5 文件对应的MD5值
     * @return
     * @author shenlx
     */
    FileDto downloadFile(String md5);
}
