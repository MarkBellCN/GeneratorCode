package com.ytkj.util;

/**
 * @Author: MarkBell
 * @Description:
 * @Date 2018/4/25
 */
public class StringUtils extends org.springframework.util.StringUtils{

    public static String toUpperCase(String str){
        if(str ==null){
            return null;
        }else {
            return str.toUpperCase();
        }
    }
}
