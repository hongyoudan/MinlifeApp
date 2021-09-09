package com.example.minlifeapp.util;

//封装一个StringUtils工具类判断字符串是否为空
public class StringUtils {

    public static boolean isEmpty(String string){

        if (string == null || string.length() <= 0 || string.equals("")){
            return true;
        }else {
            return false;
        }
    }
}
