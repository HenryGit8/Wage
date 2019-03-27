/**
 * FileManager.java 2018/5/21 16:32
 * Copyright ©2018 wondersgroup.com All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.wage.util;

import com.sun.org.apache.regexp.internal.RE;
import org.csource.fastdfs.*;

import java.io.File;
import org.csource.common.NameValuePair;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static com.wage.util.FileManagerConfig.*;

/**
 * File：FileManager.java<br>
 * Title: 类概要： FastDFS Java客户端工具类<br>
 * Description: <br>
 * Company: wondersgroup.com <br>
 * @author 何友池
 * @version 1.0
 */
public class FileManager extends FileManagerConfig {

    private static final long serialVersionUID = 1L;
    private static TrackerClient trackerClient;
    private static TrackerServer trackerServer;
    private static StorageServer storageServer;
    private static StorageClient storageClient;

    static {
        try {
            String classPath = new File(FileManager.class.getResource("/").getFile()).getCanonicalPath();
            //clientGloble读配置文件
            /*ClassPathResource resource = new ClassPathResource("fdfs_client.conf");
            System.out.println(resource.getClassLoader().getResource("fdfs_client.conf").getPath());
            ClientGlobal.init(resource.getClassLoader().getResource("fdfs_client.conf").getPath());*/
            String fdfsClientConfigFilePath = classPath + File.separator + CLIENT_CONFIG_FILE;
            System.out.println(fdfsClientConfigFilePath);
            ClientGlobal.init(fdfsClientConfigFilePath);

            trackerClient = new TrackerClient();
            trackerServer = trackerClient.getConnection();

            storageClient = new StorageClient(trackerServer, storageServer);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * <strong>方法概要： 文件上传</strong> <br>
     *
     * @param valuePairs
     *            file
     * @return fileAbsolutePath
     * @author 何友池
     */
    public static String upload(FastDFSFile file,NameValuePair[] valuePairs) {
        String[] uploadResults = null;
        try {
            uploadResults = storageClient.upload_file(file.getContent(),file.getExt(), valuePairs);
            System.out.println(uploadResults);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String groupName = uploadResults[0];
        String remoteFileName = uploadResults[1];
        String fileAbsolutePath = groupName + SEPARATOR + remoteFileName;
        return fileAbsolutePath;
    }
    /**
     * <strong>方法概要： 文件下载</strong> <br>
     *
     * @param fileAbsolutePath
     * @return returned value comment here
     * @author 何友池
     */
    public static byte[] download(String fileAbsolutePath) {
        fileAbsolutePath = fileAbsolutePath.replace("'","");
        String[] i = fileAbsolutePath.split(SEPARATOR);
        String filename=fileAbsolutePath.substring(i[0].length()+1);
        byte[] content = null;
        try {
            content = storageClient.download_file(i[0], filename);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return content;
    }

    /**
     * 获取文件名称
     * @param fileAbsolutePath
     * @return
     * @author 何友池
     */
    public static String getFileName(String fileAbsolutePath){
        fileAbsolutePath = fileAbsolutePath.replace("'","");
        String[] i = fileAbsolutePath.split(SEPARATOR);
        String filename=fileAbsolutePath.substring(i[0].length()+1);
        String result = "";
        try {
            result = storageClient.get_metadata(i[0], filename).getClass().getName();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return result;
    }
}
