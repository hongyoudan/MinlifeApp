package com.example.minlifeapp.util;

public class ValidUtils {

    private static DBOpenHelper mDBOpenHelper;

    //判断手机号是否规范
    public static boolean isPhoneValid(String phone) {
        String phone_valid = "^[1]([3-9])[0-9]{9}$";//首位为1, 第二位为3-9, 剩下九位为 0-9, 共11位数字
        return phone.matches(phone_valid);//matches():字符串是否在给定的正则表达式匹配
    }

}