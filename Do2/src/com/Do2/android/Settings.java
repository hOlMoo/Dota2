package com.Do2.android;

import android.text.format.DateUtils;

/**
 * 全局参数类
 * @author zhous @Email:657523985@qq.com
 * @Date 2014-10-23 上午9:50
 */
public class Settings {
	
	/**
	 * 时间参数
	 */
	public static final int flagsDate = DateUtils.FORMAT_SHOW_DATE;		//获取日期 （6月7日）
	
	public static final int flagsTime = DateUtils.FORMAT_SHOW_TIME;		//获取时间（12.20）
	
	/**
	 * 是否登录过
	 */
	public static final String isLogin = "isLogin";
	
	/**
	 * 聊天内容来自,他人/本人
	 */
	public static final int chat_Person_Form = 0; //他人
	public static final int chat_Person_To = 1;	//本人
}
