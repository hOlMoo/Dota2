package com.Do2.android.cache;

import com.Do2.android.utils.ZHPrefsUtil;


/**
 * 本地文本存、取
 * @author zhou @Email: 657523985@qq.com
 * @Date: 14-07-14
 * @Time: 15:02
 */
public class FileCache {
	
	
	public static void saveFileCache(String url, String message){
		ZHPrefsUtil.getInstance().putString(url, message);
	}
	
	public static String getFileCache(String url){
		return ZHPrefsUtil.getInstance().getString(url);
	}
}
