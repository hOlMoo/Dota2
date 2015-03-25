package com.Do2.android.activity;

import java.util.Timer;
import java.util.TimerTask;

import com.Do2.android.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

/**
 * Search搜索页
 * @author zhous @Email:657523985@qq.com
 * @Date 2014-10-28
 */
public class SearchActivity extends Activity{
	private Button cancle;
	private EditText searchEdit;
	private RelativeLayout contentLayout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_activity);
		findViewById();
		init();
		setListener();
	}
	
	private void findViewById(){
		cancle = (Button) findViewById(R.id.search_cancle);
		searchEdit = (EditText) findViewById(R.id.search_title_edit);
		contentLayout = (RelativeLayout) findViewById(R.id.search_content_layout);
	}
	
	private void init(){
		initEditShow();
	}
	
	private void setListener(){
		cancle.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				finish();
				overridePendingTransition(R.anim.topitem_in, R.anim.topitem_out);
			}
		});
		contentLayout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				finish();
				overridePendingTransition(R.anim.topitem_in, R.anim.topitem_out);
			}
		});
	}
	
	@Override
	public void onBackPressed() {
		//判断隐藏软键盘是否弹出
		if (getWindow().getAttributes().softInputMode == WindowManager.LayoutParams.
				SOFT_INPUT_STATE_UNSPECIFIED) 
		{	
			//隐藏软键盘
			getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		}else 
		{
			finish();
			overridePendingTransition(R.anim.topitem_in, R.anim.topitem_out);
		}
	}
	
	/**
	 * 初始化edit直接显示
	 */
	private void initEditShow(){
		searchEdit.setFocusable(true);
		searchEdit.setFocusableInTouchMode(true);
		searchEdit.requestFocus();
		//对于刚跳到一个新的界面就要弹出软键盘的情况上述代码可能由于界面为加载完全而无法弹出软键盘,
		//此时应该适当的延迟弹出软键盘如998毫秒（保证界面的数据加载完成）
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				InputMethodManager inputMethodManager = (InputMethodManager) searchEdit.getContext().
						getSystemService(Context.INPUT_METHOD_SERVICE);
				inputMethodManager.showSoftInput(searchEdit, 0);
			}
		}, 100);
	}
	
	
}
