package com.wage.web.handler;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wage.api.FileService;
import com.wage.model.dto.FileDto;
import com.wage.model.dto.WangEditorDto;
import com.wage.web.constant.DescribableEnum;
import com.wage.web.exception.BusinessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件上传下载handler
 * File：FileHandler.java <br>
 * Title: <br>
 * Description: <br>
 * Company: wondersgroup.com <br>
 * @author shenlx
 * @version 1.0
 */
@Component
public class FileHandler {

	@Reference
	private FileService fileService;
    
	/**
	 * 文件上传
	 * @param mf  上传的文件
	 * @return
	 * @throws IOException
	 * @author shenlx
	 */
    public String uploadFile(MultipartFile mf) throws IOException{
    	
    	if(mf!=null){
    		
    		String fileName = mf.getOriginalFilename();
    		
    		String md5 =  fileService.uploadFile(new FileDto(mf.getBytes(),fileName));
    		
    		return md5;
    		
    	}else{
    		
    		return "上传的文件不能为空";// 上传的文件不能为空
    	}
    	
    }
    
    
    /**
     * 文件批量上传
     * @param files
     * @return
     * @throws IOException
     * @author shenlx
     */
    public List<String> uploadFileList(List<MultipartFile> files) throws IOException{
    	
    	List<FileDto> list = new ArrayList<FileDto>();
    	
    	if(files.size()>0){
    		
    		for(MultipartFile mf:files){
    			
    			list.add(new FileDto(mf.getBytes(),mf.getOriginalFilename()));
    		}
    		
    		List<String> md5s =  fileService.uploadFile(list);
    		
    		return md5s;
    		
    	}else{

			return null;// 上传的文件不能为空
    	}
    	
    }
    
    /**
     * 文件下载，并附上数据库存的文件名
     * @param md5
     * @return
     * @throws IOException
     * @author shenlx
     */
    public ResponseEntity<byte[]> downloadFile(String md5) throws IOException{
    	
    	FileDto fileDto = fileService.downloadFile(md5);
    	
		HttpHeaders headers = new HttpHeaders();
		
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		
		String fileName = new String(fileDto.getFileName().getBytes("UTF-8"), "ISO8859-1");
		
        headers.add("Content-Disposition","attachment;filename="+fileName);  

        ResponseEntity<byte[]> entity=new ResponseEntity<byte[]>(fileDto.getFileByte(),headers, HttpStatus.OK);
	   
    	return entity;
    	
    }

	/**
	 * 富文本上传图片返回
	 * @param mf
	 * @return
	 * @throws IOException
	 * @author 何友池
	 */
	public WangEditorDto getDownlaodResult(MultipartFile mf) throws IOException{
		String md5 = uploadFile(mf);
		WangEditorDto wangEditorDto = new WangEditorDto();
		wangEditorDto.setErrno(0);
		String[] re=  {"http://localhost:8080/Wage-web/file/downloadFile?md5="+md5};
		wangEditorDto.setData(re);
		return wangEditorDto;
	}
}
