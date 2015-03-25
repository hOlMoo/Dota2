package com.Do2.android.activity;

import com.Do2.android.R;
import com.Do2.android.Settings;
import com.Do2.android.utils.ZHPrefsUtil;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnKeyListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;

/**
 * Logo页
 * @author zhous
 * @Date 2014/10/15 16:32
 */
public class StartActivity extends Activity{
	private final static int GO = 0;
	
	@SuppressLint("HandlerLeak")
	Handler mHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			go();
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start_activity);
		//开始
		start();
	}
	
	private void start(){
		//开启线程
		new ThreadStart().start();
	}
	
	private class ThreadStart extends Thread{
		@Override
		public void run() {
			super.run();
			//停留LOGO页面2s
			try {
				sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Message msg = mHandler.obtainMessage();
			msg.what = GO;
			mHandler.sendMessage(msg);
		}
	}
	
	//判断是否登录
	private void go(){
		boolean isLogin = ZHPrefsUtil.getInstance().getBoolean(Settings.isLogin, false);
		if (!isLogin) 
		{
			startActivity(MainActivity.class);
		}else {
			startActivity(LoginActivity.class);
		}
	}
	
	/**
	 * 跳转
	 * @param c
	 */
	private void startActivity(Class<?> c){
		Intent intent = new Intent(StartActivity.this,c);
		startActivity(intent);
		finish();
	}	
}
