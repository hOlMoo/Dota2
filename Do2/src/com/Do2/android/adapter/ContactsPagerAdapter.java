package com.Do2.android.adapter;

import java.util.List;

import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * 资讯内容Pager适配器
 * @author zhous @Email:657523985@qq.com
 * @Date 2014-11-7 上午9:44
 */
public class ContactsPagerAdapter extends PagerAdapter{
	/**
	 * 存放显示内容的View
	 */
	private List<View> mViews;
	
	public ContactsPagerAdapter(List<View> mViews){
		this.mViews = mViews;
	}
	
	public void destroyItem(View arg0, int arg1, Object arg2) {
		((ViewPager) arg0).removeView(mViews.get(arg1));
	}

	public void finishUpdate(View arg0) {

	}

	public int getCount() {

		return mViews.size();
	}

	public Object instantiateItem(View arg0, int arg1) {
		((ViewPager) arg0).addView(mViews.get(arg1));
		return mViews.get(arg1);

	}

	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

	public void restoreState(Parcelable arg0, ClassLoader arg1) {

	}

	public Parcelable saveState() {
		return null;
	}

	public void startUpdate(View arg0) {

	}
	
}
