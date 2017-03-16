package com.test.ueditor.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created on 2017/3/16 0016.
 *
 * @author zlf
 * @since 1.0
 */
public class LoadPropertiesDataUtils {
    private volatile static Properties mProperties;

    static{
        mProperties = new Properties();
        InputStream in = LoadPropertiesDataUtils.class.getResourceAsStream("/applications.properties");
        try{
            mProperties.load(in);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static String getValue(String key){
        if(mProperties == null) return "";
        return mProperties.getProperty(key, "");
    }
}
