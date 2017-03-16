package com.test.ueditor.util;

import java.util.Random;

/**
 * Created on 2017/3/16 0016.
 *
 * @author zlf
 * @since 1.0
 */
public class RandomUtils {
    /**
     * 获取指定位数的随机数
     * @param num
     * @return
     */
    public static String getRandom(int num){
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i < num; i++){
            sb.append(String.valueOf(random.nextInt(10)));
        }
        return sb.toString();
    }
}
