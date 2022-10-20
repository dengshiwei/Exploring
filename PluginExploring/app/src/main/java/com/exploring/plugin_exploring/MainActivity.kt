package com.exploring.plugin_exploring

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import dalvik.system.DexClassLoader

class MainActivity : AppCompatActivity() {
    private lateinit var btn_go: Button
    private lateinit var pluginClassLoader: ClassLoader
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Exploring", "Activity onCreate")
        setContentView(R.layout.activity_main)
        btn_go = findViewById(R.id.btn_go)
        init()
        // 2：从 data/data/files 中加载模块
        loadDex()
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
        Log.d("Exploring", "Activity attachBaseContext")
    }

    fun init() {
        btn_go.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                val activityUtils = pluginClassLoader.loadClass("com.exploring.pluginmodule.ActivityUtils")
                val startMainMethod = activityUtils.getDeclaredMethod("goMainActivity", Context::class.java)
                startMainMethod.isAccessible = true
                startMainMethod.invoke(null, this@MainActivity)
            }
        })
    }

    fun loadDex() {
        val extractFile =  "$codeCacheDir/pluginmodule.apk"
        val fileRelease = getDir("dex", Context.MODE_PRIVATE)
        Log.d("Exploring", "dex path $extractFile")
        Log.d("Exploring", "fileRelease path ${fileRelease.absolutePath}")
        pluginClassLoader = DexClassLoader(extractFile, fileRelease.absolutePath, null, classLoader)
    }
}