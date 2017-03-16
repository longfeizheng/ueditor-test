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
public class ResourceLoader {
    ClassLoader defaultClassLoader;
    ClassLoader systemClassLoader;

    ResourceLoader() {
        try {
            //初始化类加载器
            systemClassLoader = ClassLoader.getSystemClassLoader();
        } catch (SecurityException ignored) {
            // AccessControlException on Google App Engine
        }
    }
    public InputStream getResourceAsStream(String resource) {
        return getResourceAsStream(null, resource);
    }

    public InputStream getResourceAsStream(ClassLoader classLoader, String resource) {
        return getResourceAsStream(resource, getClassLoaders(classLoader));
    }
    //用5个类加载器一个个查找资源，只要其中任何一个找到，就返回
    InputStream getResourceAsStream(String resource, ClassLoader[] classLoader) {
        for (ClassLoader cl : classLoader) {
            if (null != cl) {
                // try to find the resource as passed
                InputStream returnValue = cl.getResourceAsStream(resource);

                // now, some class loaders want this leading "/", so we'll add it and try again if we didn't find the resource
                if (null == returnValue) {
                    returnValue = cl.getResourceAsStream("/" + resource);
                }

                if (null != returnValue) {
                    return returnValue;
                }
            }
        }
        return null;
    }

    private void printProperties(InputStream input) throws IOException {
        Properties properties = new Properties();
        properties.load(input);
        System.out.println(properties.getProperty("name"));
    }

    //一共5个类加载器
    ClassLoader[] getClassLoaders(ClassLoader classLoader) {
        return new ClassLoader[]{
                classLoader,
                defaultClassLoader,
                Thread.currentThread().getContextClassLoader(),
                getClass().getClassLoader(),
                systemClassLoader};
    }
}
