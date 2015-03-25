package com.Do2.android.utils;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

/**
 * Socrollview套listview
 * 高度正常显示方法
 * @author zhou Email:657523985@qq.com
 * @Date 14-07-25
 * @Time 下午15:05
 */
public class ListHeightUtil {
	/**
	 * @param mListView
	 * @param adapter
	 */
	public static void setListViewHeight(ListView mListView ,BaseAdapter adapter){
		adapter = (BaseAdapter) mListView.getAdapter();
		if (adapter == null) {
			return;
		}
		int mTotalHeight = 0;
		for (int i = 0; i < adapter.getCount(); i++) {
			View mListItem = adapter.getView(i, null, mListView);
			mListItem.measure(0, 0);
			mTotalHeight += mListItem.getMeasuredHeight();
		}
		ViewGroup.LayoutParams mParams = mListView.getLayoutParams();
		mParams.height = mTotalHeight + 
				(mListView.getDividerHeight() * (adapter.getCount() - 1));
		mListView.setLayoutParams(mParams);
	}
}
