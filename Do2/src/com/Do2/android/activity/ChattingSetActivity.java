package com.Do2.android.activity;

import com.Do2.android.R;

import android.R.bool;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

/**
 * 聊天设置页
 * @author zhous @Email:657523985@qq.com
 * @Date 2014-10-30 下午16:46
 */
public class ChattingSetActivity extends Activity implements OnClickListener{
	private Button back;
	private Button titleSet;
	private Button distrubSet;//干扰设置
	private Animation rotateAnimation;
	private boolean isTitleOpen = false;
	private boolean isDistrubOpen = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chattingset_activity);
		findViewById();
		init();
	}
	
	private void findViewById(){
		back = (Button) findViewById(R.id.chattingset_back);
		titleSet = (Button) findViewById(R.id.chattingset_titleset_btn);
		distrubSet = (Button) findViewById(R.id.chattingset_noditurb_btn);
		back.setOnClickListener(this);
		titleSet.setOnClickListener(this);
		distrubSet.setOnClickListener(this);
	}
	
	private void init(){
		rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.button_rotate_anim);
		rotateAnimation.setFillAfter(true);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.chattingset_back:
			finish();
			break;
		case R.id.chattingset_titleset_btn:
			titleSet.startAnimation(rotateAnimation);
			if (isTitleOpen) 
			{
				isTitleOpen = false;
				titleSet.setText("N");
				titleSet.setBackgroundResource(R.drawable.icon_red);
			}else {
				isTitleOpen = true;
				titleSet.setText("Y");
				titleSet.setBackgroundResource(R.drawable.icon_green);
			}
			break;
		case R.id.chattingset_noditurb_btn:
			distrubSet.startAnimation(rotateAnimation);
			if (isDistrubOpen) 
			{
				isDistrubOpen = false;
				distrubSet.setText("N");
				distrubSet.setBackgroundResource(R.drawable.icon_red);
			}else {
				isDistrubOpen = true;
				distrubSet.setText("Y");
				distrubSet.setBackgroundResource(R.drawable.icon_green);
			}
			break;
		default:
			break;
		}
	}
}
