package com.example.minlifeapp.util;


import android.content.Context;
import android.content.SharedPreferences;

public class SharedUtil {
    private static SharedUtil mUtil; // 声明一个共享参数工具类的实例
    private static SharedPreferences mShared; // 声明一个共享参数的实例
    private SharedPreferences.Editor editor;

    // 通过单例模式获取共享参数工具类的唯一实例
    public static SharedUtil getIntance(Context ctx) {
        if (mUtil == null) {
            mUtil = new SharedUtil();
        }
        // 从share.xml中获取共享参数对象
        mShared = ctx.getSharedPreferences("share", Context.MODE_PRIVATE);
        return mUtil;
    }

    // 把配对信息写入共享参数
    public void writeShared(String key, String value) {
        editor=mShared.edit();
        editor.putString(key, value); // 添加一个指定键名的字符串参数
        editor.apply(); // 提交编辑器中的修改
    }

    public void putBoolean(String key, boolean value) {
        editor=mShared.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    // 根据键名到共享参数中查找对应的值对象
    public String readShared(String key, String defaultValue) {
        return mShared.getString(key, defaultValue);
    }


    public boolean getBoolean(String key, boolean defaultValue) {
        return mShared.getBoolean(key, defaultValue);
    }

}
