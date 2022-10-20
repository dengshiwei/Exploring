## 插件化步骤说明

加载流程如下：
1. 从服务器下载插件apk到手机SDCard，为此需要申请SDCard的读写权限。
2. 读取插件apk中的dex，生成对应的DexClassLoader。
3. 使用DexClassLoader的loadClass方法读取插件dex中的任何一个类。

> DexClassLoader 用于从 .jar 和 .apk 文件中包含的 classes.dex 中加载 classes 文件，用于执行那些不用安装在 Application 中的代码。
从 Api26 开始，DexClassLoader 需要使用 application-private 且可写的目录，可以使用 Context.getCodeCacheDir() 或 context.getFileStreamPath()


```java
public DexClassLoader (String dexPath, 
                String optimizedDirectory, 
                String librarySearchPath, 
                ClassLoader parent)
`

- dexPath：指明 jar/apk 文件的路径
- optimizedDirectory：已经过时，在 api26 已经无效
- librarySearchPath：the list of directories containing native libraries, 可为 null
- parent： 父 ClassLoader