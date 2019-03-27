package com.wage.biz.handler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.wage.contract.FileHandler;
import com.wage.model.dto.FileDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import org.csource.common.NameValuePair;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

/**
 * 文件上传下载操作handler实现类
 * File：FileHandlerImpl.java <br>
 * Title: <br>
 * Description: <br>
 * Company: wondersgroup.com <br>
 * @author shenlx
 * @version 1.0
 */
@Component
public class FileHandlerImpl implements FileHandler {

	@Autowired
	private MongoTemplate mongoTemplate;


	/**
	 * 文件上传
	 * @param fileDto
	 * @return
	 * @author shenlx
	 */
	@Override
	public String uploadFile(FileDto fileDto) {

		GridFS gridFS = new GridFS(mongoTemplate.getDb());

		GridFSInputFile gridFSInputFile = gridFS.createFile(fileDto.getFileByte());

		gridFSInputFile.setFilename(fileDto.getFileName());

		gridFSInputFile.save();

		return gridFSInputFile.getMD5();
	}

	/*@Override
	public String uploadFile(FileDto fileDto) {
		// 获取文件后缀名
		String ext = fileDto.getFileName().substring(fileDto.getFileName().lastIndexOf(".")+1);
		FastDFSFile file = new FastDFSFile(fileDto.getFileByte(),ext);
		NameValuePair[] meta_list = new NameValuePair[4];
		meta_list[0] = new NameValuePair("fileName", fileDto.getFileName());
		meta_list[1] = new NameValuePair("fileLength", String.valueOf(fileDto.getFileByte().length));
		meta_list[2] = new NameValuePair("fileExt", ext);
		meta_list[3] = new NameValuePair("fileAuthor", "heyouchi");
		return FileManager.upload(file,meta_list);
	}*/


	/**
	 * 文件批量上传
	 * @param fileDtoList
	 * @return 文件上传成功后返回的MD5集合
	 * @author shenlx
	 */
	@Override
	public List<String> uploadFile(List<FileDto> fileDtoList) {

		List<String> list = new ArrayList<String>();

		for(FileDto fileDto:fileDtoList){

			list.add(uploadFile(fileDto));

		}

		return list;
	}

	/**
	 * 文件下载
	 * @param md5 文件对应的MD5值
	 * @return
	 * @author shenlx
	 */
	@Override
	public FileDto downloadFile(String md5) {
		DBObject query  = new BasicDBObject("md5", md5);
	    GridFS gridFS = new GridFS(mongoTemplate.getDb());
	    GridFSDBFile gridFSDBFile = gridFS.findOne(query);
	    if(null!=gridFSDBFile){
	    	ByteArrayOutputStream baos=new ByteArrayOutputStream();
		    try {
				gridFSDBFile.writeTo(baos);
				baos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		    return new FileDto(baos.toByteArray(),gridFSDBFile.getFilename());
	    }else{
	    	return null;
	    }
	}
	/*@Override
	public FileDto downloadFile(String str) {
		return new FileDto(FileManager.download(str),UUID.randomUUID().toString().replaceAll("-", ""));
	}*/
}
