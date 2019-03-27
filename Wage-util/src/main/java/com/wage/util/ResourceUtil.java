package com.wage.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ResourceUtil {
	
    protected Log log = LogFactory.getLog(ResourceUtil.class);

    public static final String fileLoc = "resource.properties";

    private static ResourceUtil instance;

    private Properties props = null;

    public ResourceUtil() {
        init();
    }

    public synchronized static ResourceUtil getInstance() {
        if (instance == null) {
            instance = new ResourceUtil();
        }
        return instance;
    }

    public void init() {
        try {
            props = new Properties();
            InputStream fis = getClass().getClassLoader().getResourceAsStream(fileLoc);
            props.load(fis);
        } catch (FileNotFoundException fileE) {
            fileE.printStackTrace();
            log.error(fileE, fileE);
        } catch (IOException ioE) {
            ioE.printStackTrace();
            log.error(ioE, ioE);
        }
    }

    public String getPropByKey(String key) {
        return props.getProperty(key);
    }
	
}
