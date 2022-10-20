package com.exploring.plugin_exploring;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class FileUtils {

    /**
     * 将 Asserts 中的目标文件拷贝到 /data/data/files/ 目录下
     * @param context Context
     * @param sourceName String
     */
    public static void extractAssert(Context context, String sourceName) {
        AssetManager assetManager = context.getAssets();
        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            inputStream = assetManager.open(sourceName);
            File extractFile = context.getCodeCacheDir();
            Log.d("Exploring", "extractFileh path = " + extractFile.getPath());
            fileOutputStream  = new FileOutputStream(extractFile.getAbsoluteFile()+  "/" + sourceName);
            byte[] buffer = new byte[1024];
            int count = 0;
            while ((count = inputStream.read(buffer)) > 0) {
                fileOutputStream.write(buffer, 0 ,count);
            }
            fileOutputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeSilently(inputStream);
            closeSilently(fileOutputStream);
        }
    }

    private static void closeSilently(Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch (Throwable e) {
            // ignore
        }
    }
}
