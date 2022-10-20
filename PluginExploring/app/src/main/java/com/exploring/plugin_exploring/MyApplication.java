package com.exploring.plugin_exploring;

import android.app.Application;
import android.content.Context;
import android.util.Log;

public class MyApplication extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        Log.d("Exploring", "Application attachBaseContext");
        try {
            // 插件化第一步：将 assert 中的插件 apk 拷贝到 data/data/files 目录
            FileUtils.extractAssert(base, "pluginmodule.apk");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
