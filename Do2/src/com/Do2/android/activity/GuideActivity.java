package com.Do2.android.activity;

import java.util.ArrayList;
import java.util.List;

import com.Do2.android.R;
import com.Do2.android.widget.RotateDownPageTransformer;
import com.Do2.android.widget.ViewPagerCompat;
import com.Do2.android.widget.ViewPagerCompat.OnPageChangeListener;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ImageView.ScaleType;

/**
 * 引导页
 * @author zhous @Email:657523985@qq.com
 * @Date 2014-11-4 下午14:47
 */
public class GuideActivity extends Activity{
	
	/**
	 * 引导页显示内容的View
	 */
	private View mPage1, mPage2, mPage3, mPage4;
	private ViewPagerCompat mViewPager;
	/**
	 * 存放显示内容的View
	 */
	private List<View> mViews = new ArrayList<View>();
	/**
	 * 中间文字
	 */
	private TextView centerTx;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.guide_activity);
		/**
		 * 获取要显示的引导页内容
		 */
		mPage1 = LayoutInflater.from(this).inflate(
				R.layout.guide_activity_page1, null);
		mPage2 = LayoutInflater.from(this).inflate(
				R.layout.guide_activity_page2, null);
		mPage3 = LayoutInflater.from(this).inflate(
				R.layout.guide_activity_page3, null);
		mPage4 = LayoutInflater.from(this).inflate(
				R.layout.guide_activity_page4, null);
		/**
		 * 添加View
		 */
		mViews.add(mPage1);
		mViews.add(mPage2);
		mViews.add(mPage3);
		mViews.add(mPage4);
		
		findViewById();
		centerTx = (TextView) findViewById(R.id.id_guide_content);
		
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				switch (position) {
				case 0:
					centerTx.setText(getResources().getString(R.string.feihua_01));
					break;
				case 1:
					centerTx.setText(getResources().getString(R.string.feihua_02));
					break;
				case 2:
					centerTx.setText(getResources().getString(R.string.feihua_03));
					break;
				case 3:
					startActivity(new Intent(GuideActivity.this,MainActivity.class));
					finish();
					break;
				default:
					break;
				}
			}
			
			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels) {
				
			}
			
			@Override
			public void onPageScrollStateChanged(int state) {
				
			}
		});
	}
	
	private void findViewById(){
		
		mViewPager = (ViewPagerCompat) findViewById(R.id.id_viewpager);
		mViewPager.setPageTransformer(true, new RotateDownPageTransformer());
		mViewPager.setAdapter(new PagerAdapter()
		{
			@Override
			public Object instantiateItem(ViewGroup container, int position)
			{

				container.addView(mViews.get(position));
				return mViews.get(position);
			}

			@Override
			public void destroyItem(ViewGroup container, int position,
					Object object)
			{

				container.removeView(mViews.get(position));
			}

			@Override
			public boolean isViewFromObject(View view, Object object)
			{
				return view == object;
			}

			@Override
			public int getCount()
			{
				return mViews.size();
			}
		});
		
	}

}
