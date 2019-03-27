package com.wage.web.controller;

import com.wage.web.common.JsonResult;
import com.wage.web.constant.DescribableEnum;
import com.wage.web.constant.ParamConstants;
import com.wage.web.handler.FileHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 文件上传下载controller
 * File：FileController.java <br>
 * Title: <br>
 * Description: <br>
 * Company: wondersgroup.com <br>
 * @author shenlx
 * @version 1.0
 */
@Controller
@RequestMapping("/file")
public class FileController {

    @Autowired
	FileHandler fileHandler;
    
    /**
     * 文件上传到mongoDB 
     * @param request
     * @param response
     * @param imgFile  单个文件
     * @return
     * @throws Exception
     * @author shenlx
     */
	@RequestMapping("/uploadFile")
	@ResponseBody
	public Object uploadFile(HttpServletRequest request, HttpServletResponse response,MultipartFile imgFile) throws Exception
	{
	    String md5 = fileHandler.uploadFile(imgFile);
		
        return new JsonResult(DescribableEnum.SUCCESS,md5);
	}
	
	/**
	 * 文件批量上传
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author shenlx
	 */
	@RequestMapping("/uploadFileList")
	@ResponseBody
	public Object uploadFileList(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		
	    List<String> md5s = fileHandler.uploadFileList(multipartRequest.getFiles("files"));
		
        return new JsonResult(DescribableEnum.SUCCESS,md5s);
	}
	
	/**
	 * 文件下载
	 * @param request
	 * @param response
	 * @param md5  文件的MD5值
	 * @return
	 * @throws IOException
	 * @author shenlx
	 */
	@RequestMapping("/downloadFile")
	public ResponseEntity<byte[]> downloadFile(HttpServletRequest request, HttpServletResponse response,String md5) throws IOException
	{
		if(md5 == null || md5.length()<1){
			return fileHandler.downloadFile(ParamConstants.NO_HEAD_MD);
		}else {
			return fileHandler.downloadFile(md5);
		}
	}

	/**
	 * 文件上传到mongoDB
	 * @param request
	 * @param response
	 * @param imgFile  单个文件
	 * @return
	 * @throws Exception
	 * @author shenlx
	 */
	@RequestMapping("/getDownlaodResult")
	@ResponseBody
	public Object getDownlaodResult(HttpServletRequest request, HttpServletResponse response,MultipartFile imgFile) throws Exception
	{
		return fileHandler.getDownlaodResult(imgFile);
	}

	
}
