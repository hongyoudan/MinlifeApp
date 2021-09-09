package com.example.minlifeapp.api;

public interface TtitCallback {
    //创建两个方法
    //请求成功的回调
    void onSuccess(String res);
    //请求失败的回调
    void onFailure(Exception e);
}
