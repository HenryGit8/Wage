package com.wage.service;

import java.util.List;

import com.wage.api.FileService;
import com.wage.contract.FileHandler;
import com.wage.model.dto.FileDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;

/**
 * 文件上传下载操作接口实现类
 * File：FileService.java <br>
 * Title: <br>
 * Description: <br>
 * Company: wondersgroup.com <br>
 * @author shenlx
 * @version 1.0
 */
@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileHandler fileHandler;
    
	/**
	 * 文件上传
	 * @param fileDto
	 * @return
	 * @author shenlx
	 */
	@Override
	public String uploadFile(FileDto fileDto) {
		
		return fileHandler.uploadFile(fileDto);
		
	}
	
	
	/**
	 * 文件批量上传
	 * @param fileDtoList
	 * @return 文件上传成功后返回的MD5集合
	 * @author shenlx
	 */
	@Override
	@Transactional
	public List<String> uploadFile(List<FileDto> fileDtoList) {
		
		return fileHandler.uploadFile(fileDtoList);
	}
	
	/**
	 * 文件下载
	 * @return
	 * @author shenlx
	 */
	@Override
	public FileDto downloadFile(String md5) {
		
		return fileHandler.downloadFile(md5);
		
	}

}
