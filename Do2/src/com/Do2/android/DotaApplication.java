package com.Do2.android;


import java.io.File;

import com.Do2.android.exception.ZHCrashHandler;
import com.Do2.android.log.Logger;
import com.Do2.android.thread.ThreadPool;
import com.Do2.android.utils.ZHPrefsUtil;
import com.baidu.frontia.FrontiaApplication;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.StorageUtils;


import android.app.Application;
import android.content.Context;
import android.util.Log;
/**
 * 全局初始化类
 * 初始化日志工具
 * 初始化线程池
 * 初始化图片加载器
 * 初始化程序崩溃捕捉处理
 * 初始化SharedPreference
 * FrontiaApplication为百度推送application
 * @Author: zhous  @Email:657523985@qq.com
 * @Date: 14-07-10
 * @Time: 上午10:40
 */
public class DotaApplication extends FrontiaApplication{
	public static String TAG = DotaApplication.class.getSimpleName();
	/**
	 * 单例初始化设置
	 */
	public static DotaApplication instance;
	public static DotaApplication getInstance(){
		if (instance == null) {
			instance = new DotaApplication();
		}
		return instance;
	}
	/**
	 * 退出应用
	 */
	public void exit(){
		android.os.Process.killProcess(android.os.Process.myPid());
		System.exit(0);
	}
	@Override
	public void onCreate() {
		super.onCreate();/**这行代码必须要**/
		
		instance = this;
		initLogger(); // 初始化日志工具
        initThreadPool(); // 初始化线程池
        initImageLoader(this); // 初始化图片加载器
        initCrashHandler(); // 初始化程序崩溃捕捉处理
        initPrefs(); // 初始化SharedPreference
        initInfo();
	}
   /**
	* 初始化日志
	*/
	private void initLogger(){
		Logger.initLogger(null);
	}
    
   /**
    * 初始化线程池
    */
	private void initThreadPool(){
		ThreadPool.initThreadPool();
	}
	
   /**
    * 初始化图片加载器（子类需要重写）
    */
	private void initImageLoader(Context context){
		File cacheDir = StorageUtils.getOwnCacheDirectory(context, "topnews/Cache");//获取到缓存的目录地址
		Log.d("cacheDir", cacheDir.getPath());
		//创建配置ImageLoader(所有的选项都是可选的,只使用那些你真的想定制)，这个可以设定在APPLACATION里面，设置为全局的配置参数
		ImageLoaderConfiguration config = new ImageLoaderConfiguration
				.Builder(context)
				//.memoryCacheExtraOptions(480, 800) // max width, max height，即保存的每个缓存文件的最大长宽
				//.discCacheExtraOptions(480, 800, CompressFormat.JPEG, 75, null) // Can slow ImageLoader, use it carefully (Better don't use it)设置缓存的详细信息，最好不要设置这个
				.threadPoolSize(3)//线程池内加载的数量
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				//.memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024)) // You can pass your own memory cache implementation你可以通过自己的内存缓存实现
				//.memoryCacheSize(2 * 1024 * 1024)  
				///.discCacheSize(50 * 1024 * 1024)  
				.discCacheFileNameGenerator(new Md5FileNameGenerator())//将保存的时候的URI名称用MD5 加密
				//.discCacheFileNameGenerator(new HashCodeFileNameGenerator())//将保存的时候的URI名称用HASHCODE加密
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				//.discCacheFileCount(100) //缓存的File数量
				.discCache(new UnlimitedDiscCache(cacheDir))//自定义缓存路径
				//.defaultDisplayImageOptions(DisplayImageOptions.createSimple())
//				.imageDownloader(new BaseImageDownloader(context, 5 * 1000, 30 * 1000)) // connectTimeout (5 s), readTimeout (30 s)超时时间
				.writeDebugLogs() // Remove for release app
				.build();
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config);//全局初始化此配置
	}
	
   /**
    * 初始化程序崩溃捕捉处理
    */	
	private void initCrashHandler(){
		ZHCrashHandler.init(getApplicationContext());
	}
	
	/**
     * 初始化SharedPreference
     */
	private void initPrefs(){
		ZHPrefsUtil.init(getApplicationContext(), getPackageName()+"_preference", Context.MODE_MULTI_PROCESS);
	}
	
	/**
	 * 初始化全部数据
	 */
	private void initInfo(){
		
	}
	
}
