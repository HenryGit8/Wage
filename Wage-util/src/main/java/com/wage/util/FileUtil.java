package com.wage.util;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Title: 文件工具类(其它方法推荐 org.apache.commons.io.FileUtils)
 * Description:
 * Copyright: Copyright (c) 2017-03-30 下午3:01
 * Company: wondersgroup.com
 *
 * @author jerry.pan
 * @version 1.0
 */
public class FileUtil {

    private FileUtil() {
        super();
    }

    public static final String CUSTOM_TOP_PATH_PROPERTIES = "file.path";//自定义的文件存放路径的属性名称

    public static final String DEFAULT_TOP_PATH = File.separator + "files";//默认的文件存放路径

    public static final String UPLOAD_TOP_PATH = File.separator + "upload";//上传文件最上层的文件夹路径

    public final static String UPLOAD_TEMP_PATH = File.separator + "temp";//临时文件存放路径

    public final static String UPLOAD_DEFAULT_PATH = File.separator + "default";//文件存放路径

    public final static String SHOW_TEMP_PATH = File.separator + "showTemp";//文件显示的临时路径

    public final static String WEB_INF_CLASSES_PATH = File.separator + "WEB-INF" + File.separator + "classes";

    public final static String URL_SEPARATOR = "/"; //网址路径分隔符“斜杠”

    /**
     * 根据系统分隔符判断系统类型
     *
     * @return
     * @author 潘健雷
     */
    public static boolean isLinux() {
        return File.separator.equals("/");
    }

    /**
     * 获取项目路径,例如：例如"C:/Program/apache-tomcat-7.0.68/webapps/JavaWeb"
     *
     * @return
     * @author jerry.pan
     */
    public static String getProjectPath() {
        //获取classes路径
        //MAC系统，例如（/Users/panjerry/apache-tomcat-7.0.68/JavaWeb/WEB-INF/classes/）
        //Windows系统，例如（/C:/Program/apache-tomcat-7.0.68/webapps/JavaWeb/WEB-INF/classes/）
        String classPath = FileUtil.class.getResource("/").getPath();
        classPath = formatPath(classPath);
        int index = classPath.lastIndexOf(WEB_INF_CLASSES_PATH);
        return classPath.substring(0, index);
    }

    /**
     * 获取项目的本地路径
     *
     * @param request
     * @return
     * @author jerry.pan(潘健雷)
     */
    public static String getContextRealPath(HttpServletRequest request) {
        String contextRealPath;
        if (((HttpServletRequest) request).getSession().getServletContext() == null) {
            contextRealPath = request.getRealPath("");
        } else {
            contextRealPath = ((HttpServletRequest) request).getSession()
                    .getServletContext().getRealPath("");
        }
        return contextRealPath;
    }

    /**
     * 获取存放文件的最上层文件夹的路径
     *
     * @return
     * @author jerry.pan(潘健雷)
     */
    public static String getTopDirPath() {
        String topDirPath = PropertiesUtil.getProperty(CUSTOM_TOP_PATH_PROPERTIES);
        if (StringUtils.isBlank(topDirPath)) {
            //如果为空，那么文件夹设置为当前项目下的路径
            topDirPath = getProjectPath() + DEFAULT_TOP_PATH;
        }
        return topDirPath;
    }

    /**
     * 存放文件是不是在项目中
     *
     * @param
     * @return
     * @author jerry.pan
     */
    public static boolean isInProjectPath() {
        String topDirPath = PropertiesUtil.getProperty(CUSTOM_TOP_PATH_PROPERTIES);
        if (StringUtils.isBlank(topDirPath)) { //如果为空，那么文件夹设置为当前项目下的路径
            return true;
        } else {

            return false;
        }
    }

    /**
     * 获取文件(或文件夹)的相对路径
     *
     * @param path 文件完整的路径
     * @return
     * @author jerry.pan
     */
    public static String getRelativePath(String path) {
        path = formatPath(path);
        String topDirPath = getTopDirPath();
        String relativePath = path.substring(topDirPath.length());
        return relativePath;
    }

    /**
     * 获取上传文件的全路径
     *
     * @param relativePath 上传文件的相对路径，例如："/test/file.txt"或“/test”
     * @return
     * @author jerry.pan
     */
    public static String getPath(String relativePath) {
        relativePath = formatPath(relativePath);
        String topDirPath = getTopDirPath();
        String path = topDirPath + relativePath;
        return path;
    }

    /**
     * 文件在线浏览地址（例如：图片、视频、文档）
     *
     * @param relativeFilePath 上传文件的相对路径，例如："/test/file.txt"
     * @return
     * @author jerry.pan
     */
    public static String getFileUrl(String relativeFilePath) throws IOException {
        String URLPath;
        //1.如果文件是放在项目下的（即webapps的项目中的），那么直接返回URL
        if (isInProjectPath()) {
            URLPath = DEFAULT_TOP_PATH + relativeFilePath;
        } else {
            //2.如果文件不是存放在项目下的
            //2.1 去文件临时文件夹找，如果找到，则返回url；
            String srcFilePath = getPath(relativeFilePath);
            String destRelativeFilePath = SHOW_TEMP_PATH + relativeFilePath;//例如："\\showTemp\\ebom\\2016-01-01\\file.txt"(这里分隔符有两个斜杠是因为需要转义符)
            String destFilePath = getProjectPath() + destRelativeFilePath;//例如："C:\\tomcat\\webapps\\ebom\\upload\\showTemp\\2016-01\\file.txt"
            File destFile = new File(destFilePath);
            if (destFile.exists()) {
                URLPath = destRelativeFilePath;
            } else {
                //2.2如果没有找到，复制文件到临时文件夹，然后返回URL
                copyFile(srcFilePath, destFilePath);
                URLPath = destRelativeFilePath;
            }
        }
        return formatURLPath(URLPath);
    }


    /**
     * 文件上传(保存到临时文件夹)
     *
     * @param file 上传文件
     * @return 文件保存的相对路径（包括文件名）(相对于文件存放文件夹),例如：/2016-01-01/file.txt
     * @author jerry.pan
     */
    public static String uploadTemp(MultipartFile file) throws IOException {
        String relativDirePath = UPLOAD_TOP_PATH + UPLOAD_TEMP_PATH + File.separator +
                DateUtil.getDateString(new Date(),"yyyy-MM-dd");//例如：/temp/2016-01-01
        String filename = file.getOriginalFilename();  //获得文件名 例如:"file.txt"
        String relativeFilePath = relativDirePath + File.separator + filename;
        relativeFilePath = replaceFileName(relativeFilePath);//替换文件名
        upload(file, relativeFilePath);
        return relativeFilePath;
    }

    /**
     * 文件上传(保存到默认文件夹)
     *
     * @param file 上传文件
     * @return 文件保存的相对路径（包括文件名）(相对于文件存放文件夹),例如：/2016-01-01/file.txt
     * @author jerry.pan
     */
    public static String upload(MultipartFile file) throws IOException {
        String relativDirePath = UPLOAD_TOP_PATH + UPLOAD_DEFAULT_PATH + File.separator +
                DateUtil.getDateString(new Date(),"yyyy-MM-dd");//例如：/default/2016-01-01
        String filename = file.getOriginalFilename();  //获得文件名 例如:"file.txt"
        String relativeFilePath = relativDirePath + File.separator + filename;
        relativeFilePath = replaceFileName(relativeFilePath);//替换文件名
        upload(file, relativeFilePath);
        return relativeFilePath;
    }

    /**
     * 文件上传（保存到指定文件夹）
     *
     * @param file             上传文件
     * @param relativeFilePath 文件保存的相对路径(相对于文件存放文件夹),例如：/2016-01-01/file.txt
     * @return
     * @author jerry.pan
     */
    public static void upload(MultipartFile file, String relativeFilePath) throws IOException {
        String filePath = getPath(relativeFilePath);
        File descFile = new File(filePath);
        FileUtils.copyInputStreamToFile(file.getInputStream(), descFile);
    }

    /**
     * 替换文件名称（后缀名不变）,默认新的文件名称为UUID
     *
     * @param originalFileName 原文件名称(包含后缀名)
     * @return
     * @author jerry.pan
     */
    public static String replaceFileName(String originalFileName) {
        return replaceFileName(originalFileName, RandomUtil.getUUID());
    }

    /**
     * 替换文件名称（后缀名不变）
     *
     * @param originalFileName 原文件名称(包含后缀名) 例如：“/upload/temp/test.doc”
     * @param newFileName      新的文件名称(不包含后缀名) 例如：“newName”
     * @return 新的文件名称 例如：“/upload/temp/newName.doc”
     * @author jerry.pan
     */
    public static String replaceFileName(String originalFileName, String newFileName) {
        //1.截取文件名前面部分路径
        String prefixPath = "";
        //文件名称开始的下标；如果是带路径文件名称，存在分隔符(分隔符的下标+1)，如果不带路径，不存在分隔符，结果是(-1+1)=0，
        int startIndex = originalFileName.lastIndexOf(File.separator) + 1;
        if (startIndex > 0) {
            prefixPath = originalFileName.substring(0, startIndex);
        }
        //2.截取后缀名（包括点）
        //后缀名开始的下标（包括点）
        int lastIndex = originalFileName.lastIndexOf(".");
        String suffixName = "";
        if (lastIndex >= startIndex) {
            suffixName = originalFileName.substring(lastIndex);
        }
        //3.替换
        return prefixPath + newFileName + suffixName;
    }

    /**
     * 格式化文件路径：统一分隔符,去除多余的分隔符
     *
     * @param path String 文件路径
     * @return String 标准化的文件路径
     * @author jerry.pan
     */
    public static String formatPath(String path) {
        if (StringUtils.isNotEmpty(path)) {
            //去空格
            String formatPath = path.trim();
            //统一分隔符
            formatPath = StringUtils.replace(formatPath, "\\", File.separator);
            formatPath = StringUtils.replace(formatPath, "/", File.separator);
            //去除多余的分隔符
            formatPath = formatPath.replaceAll("/{2,}", "/");
            return formatPath;
        } else {
            return "";
        }
    }

    /**
     * 把本地路径改为网站路径
     *
     * @param filePath
     * @return
     * @author 潘健雷
     */
    public static String formatURLPath(String filePath) {
        return filePath.replaceAll("\\\\", URL_SEPARATOR);
    }


    /**
     * 通过文件名获取文件的后缀名
     *
     * @param fileFullName 文件名（包括后缀）
     * @return 后缀名
     * @author jerry.pan(潘健雷)
     */
    public static String getFileSuffix(String fileFullName) {
        return getFileSuffix(fileFullName, false);
    }

    /**
     * 通过文件名获取文件的后缀名
     *
     * @param fileFullName 文件名（包括后缀）
     * @param isLowerCase  后缀是否小写
     * @return
     * @author jerry.pan(潘健雷)   2014-4-18下午3:56:42
     */
    public static String getFileSuffix(String fileFullName, boolean isLowerCase) {
        if (StringUtils.isNotEmpty(fileFullName)) {
            int index = fileFullName.lastIndexOf(".");
            if (index >= 0) {
                String suffix = fileFullName.substring(index + 1);// 文件格式
                if (StringUtils.isNotEmpty(suffix)) {
                    if (isLowerCase) {
                        return suffix.toLowerCase();
                    } else {
                        return suffix;
                    }
                }
            }
        }
        return null;
    }

    /**
     * 通过文件名获取文件的后缀名
     *
     * @param fileFullName 文件名（包括后缀）
     * @return 后缀名
     * @author jerry.pan(潘健雷)
     */
    public static String getFileSuffixWithDot(String fileFullName) {
        return getFileSuffixWithDot(fileFullName, false);
    }

    /**
     * 通过文件名获取文件的后缀名
     *
     * @param fileFullName
     * @param isLowerCase
     * @return
     * @author jerry.pan(潘健雷)   2014-4-18下午3:58:40
     */
    public static String getFileSuffixWithDot(String fileFullName,
                                              boolean isLowerCase) {
        String suffix = getFileSuffix(fileFullName, isLowerCase);
        if (StringUtils.isNotEmpty(suffix)) {
            return (".").concat(suffix);
        }
        return null;
    }


    /**
     * 根据文件路径（包含文件名）获取文件所在文件夹路径
     *
     * @param filePath 文件路径(可以是不完整的路径，只要包含文件名，例如“/test/fileName.txt”)
     * @return 获取文件所在文件夹路径（例如：“/test”）
     * @author jerry.pan(潘健雷)
     */
    public static String getFolderPath(String filePath) {
        filePath = formatPath(filePath);
        if (StringUtils.isNotEmpty(filePath)) {
            int index = filePath.lastIndexOf(File.separator);
            if (index >= 0) {
                return filePath.substring(0, index);
            }
        }
        return null;
    }

    /**
     * 根据文件路径获取文件名称
     *
     * @param filePath 文件路径(可以是不完整的路径，只要包含文件名，例如“/test/fileName.txt”)
     * @return fileName 文件名称（例如：“fileName.txt”）
     * @author jerry.pan(潘健雷)
     */
    public static String getFileName(String filePath) {
        filePath = formatPath(filePath);
        if (StringUtils.isNotEmpty(filePath)) {
            int index = filePath.lastIndexOf(File.separator);
            if (index >= 0) {
                String fileName = filePath.substring(index + 1);
                return fileName;
            }
        }
        return null;
    }

    /**
     * 根据文件路径获取文件名称
     *
     * @param filePaths 文件路径(可以是不完整的路径，只要包含文件名，例如“/test/fileName.txt”)
     * @return fileNames文件名称（例如：“fileName.txt”）
     * @author jerry.pan(潘健雷)
     */
    public static String[] getFileName(String[] filePaths) {
        if (!ArrayUtils.isEmpty(filePaths)) {
            String[] fileNames = new String[filePaths.length];
            for (int i = 0; i < filePaths.length; i++) {
                String filePath = filePaths[i];
                fileNames[i] = getFileName(filePath);
            }
            return fileNames;
        }
        return null;
    }

    /**
     * 根据文件路径获取文件名称
     *
     * @param filePathList 文件路径(可以是不完整的路径，只要包含文件名，例如“/test/fileName.txt”)
     * @return fileNameList文件名称 文件名称（例如：“fileName.txt”）
     * @author jerry.pan(潘健雷)
     */
    public static List<String> getFileName(List<String> filePathList) {
        String[] paths = new String[filePathList.size()];
        filePathList.toArray(paths);
        String[] fileNames = getFileName(paths);
        return Arrays.asList(fileNames);
    }

    /**
     * 创建不存在的文件夹
     *
     * @param filePath 文件或文件夹路径，如果是文件路径，就创建上级文件夹
     * @author jerry.pan(潘健雷)
     */
    public static void mkdirs(String filePath) {
        filePath = formatPath(filePath);
        File file = new File(filePath);
        if (file.isFile()) {
            file = file.getParentFile();
        }
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    /**
     * 文件写入
     *
     * @param filePath String 文件路径（包括文件名）
     * @param content  byte[] 文件内容
     * @return
     * @author jerry.pan(潘健雷)
     */
    public static void write(String filePath, String content) throws IOException {
        filePath = formatPath(filePath);
        File file = new File(filePath);
        FileUtils.write(file, content);
    }

    /**
     * 复制文件
     *
     * @param srcFilePath  原文件路径
     * @param destFilePath 新文件路径
     * @author jerry.pan(潘健雷)
     */
    private static void copyFile(String srcFilePath, String destFilePath) throws IOException {
        File srcFile = new File(formatPath(srcFilePath));
        File destFile = new File(formatPath(destFilePath));
        FileUtils.copyFile(srcFile, destFile);
    }

    /**
     * 复制文件
     *
     * @param srcDirPath  原文件夹路径
     * @param destDirPath 新文件夹路径
     * @author jerry.pan(潘健雷)
     */
    private static void copyDirectoryToDirectory(String srcDirPath, String destDirPath)
            throws IOException {
        File srcDir = new File(formatPath(srcDirPath));
        File destDir = new File(formatPath(destDirPath));
        FileUtils.copyDirectoryToDirectory(srcDir, destDir);
    }

    /**
     * 移动文件
     *
     * @param srcFilePath  源文件完整路径(包括文件名)
     * @param destFilePath 目的文件完整路径(包括文件名)
     * @author jerry.pan(潘健雷)
     */
    public static void moveFile(String srcFilePath, String destFilePath)
            throws IOException {
        File srcFile = new File(formatPath(srcFilePath));
        File destFile = new File(formatPath(destFilePath));
        FileUtils.moveFile(srcFile, destFile);
    }

    /**
     * 移动文件夹
     *
     * @param srcDirPath  源文件完整路径(包括文件名)
     * @param destDirPath 目的文件完整路径(包括文件名)
     * @author jerry.pan(潘健雷)
     */
    public static void moveDirectoryToDirectory(String srcDirPath, String destDirPath)
            throws IOException {
        File srcFile = new File(formatPath(srcDirPath));
        File destFile = new File(formatPath(destDirPath));
        FileUtils.moveDirectoryToDirectory(srcFile, destFile, Boolean.TRUE);
    }

    /**
     * 删除文件
     *
     * @param filePath String 文件全路径
     * @author jerry.pan(潘健雷)
     */
    public static void deleteFile(String filePath) throws IOException {
        File myDelFile = new File(formatPath(filePath));
        myDelFile.delete();
        FileUtils.deleteQuietly(myDelFile);
    }

    /**
     * 删除文件夹
     *
     * @param dirPath 删除文件夹路劲
     * @author jerry.pan(潘健雷)
     */
    public static void deleteDirectory(String dirPath) throws IOException {
        File dir = new File(formatPath(dirPath));
        FileUtils.deleteDirectory(dir);
    }


    public static void main(String[] args) {

    }
}
