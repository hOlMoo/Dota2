package com.Do2.android.activity;


import java.util.ArrayList;
import java.util.List;

import com.Do2.android.DotaApplication;
import com.Do2.android.R;
import com.Do2.android.Values;
import com.Do2.android.adapter.FragmentTabAdapter;
import com.Do2.android.fragment.ChatFragment;
import com.Do2.android.fragment.ContactsFragment;
import com.Do2.android.fragment.MoreFragment;
import com.Do2.android.fragment.MySelfFragment;
import com.Do2.android.log.Logger;
import com.Do2.android.utils.PushUtils;
import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * 主界面
 * 包含fragment切换、推送信息处理接口、底部菜单栏、初始化绑定推送
 * @author zhous @Email:657523985@qq.com
 * @Date 2014-10-23 上午9:56
 */
public class MainActivity extends FragmentActivity {
	
	public static String TAG = MainActivity.class.getSimpleName();
	private RadioGroup rgs;
	private LinearLayout layout;
	private List<Fragment> fragments = new ArrayList<Fragment>();
	private ChatFragment chatFragment;
	private ContactsFragment contactsFragment;
	private MoreFragment findFragment;
	private MySelfFragment moreFragment;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findViewById();
		init();
		//开启百度推送
		autoBindBaiduYunTuiSong();
		
		//引导页
		startActivity(new Intent(this,GuideActivity.class));
	}
	
	@Override
	protected void onResume() {
		super.onResume();
	}
	
	/**
	 * 推送消息接口
	 */
	@Override
	protected void onNewIntent(Intent intent) {
//		super.onNewIntent(intent);
		String result = intent.getStringExtra("result");
		if (result != null) 
		{
			Logger.i(TAG, "result:"+result);
			Logger.i(TAG, "currentTab:"+FragmentTabAdapter.currentTab);
			switch (FragmentTabAdapter.currentTab) {
			case 0:
				if (chatFragment != null && chatFragment.isVisible()) 
				{
					//刷新textview
					chatFragment.updateView(result);
				}
				break;
			case 1:
				
				break;
			case 2:
	
				break;
			case 3:
	
				break;
			default:
				break;
			}
		}
	}
	
	/**
	 * 如果没有绑定百度云,则绑定,并记录在属性文件中
	 */
	private void autoBindBaiduYunTuiSong()  
    {  
        if (!PushUtils.hasBind(getApplicationContext()))  
        {  
            PushManager.startWork(getApplicationContext(),  
                    PushConstants.LOGIN_TYPE_API_KEY,  
                    Values.BAIDU_PUSH_API_KEY);  
        }  
    } 
	
	/**
	 * 绑定UI控件
	 */
	private void findViewById(){
		rgs = (RadioGroup) findViewById(R.id.tabs_rg);
		layout = (LinearLayout) findViewById(R.id.menu_layout_bg);
	}
	
	/**
	 * 初始化全部信息
	 */
	private void init(){
		initTab();
	}
	
	/**
	 * 初始化底部选项卡
	 */
	private void initTab(){
		//初始化fragment
		chatFragment = new ChatFragment();
		contactsFragment = new ContactsFragment();
		findFragment = new MoreFragment();
		moreFragment = new MySelfFragment();
		//添加frament
		fragments.add(chatFragment);
		fragments.add(contactsFragment);
		fragments.add(findFragment);
		fragments.add(moreFragment);
		//适配
		FragmentTabAdapter tabAdapter = new FragmentTabAdapter(this, fragments, R.id.tab_content, rgs);
        tabAdapter.setOnRgsExtraCheckedChangedListener(new FragmentTabAdapter.OnRgsExtraCheckedChangedListener(){
            @Override
            public void OnRgsExtraCheckedChanged(RadioGroup radioGroup, int checkedId, int index) {
            	Logger.i(TAG, "Extra--"+index+"checked!!");
            }
        }); 
	}
	
	public Fragment getCurrentFragment(){
        return fragments.get(FragmentTabAdapter.currentTab);
    }
	
	/**
	 * 返回键退出
	 */
	@Override
	public void onBackPressed() {
		exit();
	}
	/**
	 * 退出时间
	 */
	private long mExitTime;
	/**
	 * 退出间隔
	 */
	private static final int INTERVAL = 2000;
	/**
	 * 判断两次返回时间间隔,小于两秒则退出程序
	 */
	private void exit() {
		if (System.currentTimeMillis() - mExitTime > INTERVAL) {
			Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
			mExitTime = System.currentTimeMillis();
		} else {
			finish();
			DotaApplication.getInstance().exit();
		}
	}

}
