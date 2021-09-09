package com.example.minlifeapp.util;

import android.os.Environment;

import java.io.File;

public class FileUtil {

    //获取SD卡的路径
    public static String getSDCardBasePath() {
        return Environment.getExternalStorageDirectory().getPath();
    }

    //获取图片缓存的路径
    public static File getCacheFolder() {
        String path = FileUtil.getSDCardBasePath()
                + Constants.CACHE_FILE_PATH;
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }


}
