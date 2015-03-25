package com.Do2.android.fragment;

import java.util.ArrayList;
import java.util.List;

import com.Do2.android.R;
import com.Do2.android.log.Logger;
import com.Do2.android.utils.ListHeightUtil;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * 更多页
 * @author zhous @Email:657523985@qq.com 
 * @Date 2014-10-30 上午11:35
 */
public class MoreFragment extends Fragment{
	public static String TAG = MoreFragment.class.getSimpleName();
	private String[] moreNames = {"战队动态","英雄","物品详细","饰品","历史回顾"};
	private int[] moreIcons = {R.drawable.icon_groud,R.drawable.icon_hero,R.drawable.icon_wupin,
			R.drawable.icon_shipin,R.drawable.icon_history};
	private ListView listView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View contenctView = inflater.inflate(R.layout.fragment_more, container, false);
		findViewById(contenctView);
		init();
		setListener();
		return contenctView;
	}

	@Override
	public void onResume() {
		super.onResume();
	}
	
	@Override
	public void onStop() {
		super.onStop();
	}
	
	/**
	 * 绑定UI控件
	 * @param view
	 */
	private void findViewById(View view){
		listView = (ListView) view.findViewById(R.id.more_listview);
	}
	
	private void init(){
		MoreAdapter adapter = new MoreAdapter(getActivity(), initMoreData());
		listView.setAdapter(adapter);
		ListHeightUtil.setListViewHeight(listView, adapter);
	}
	
	private void setListener(){
		
	}
	
	/**
	 * 资源文件
	 * @return
	 */
	private List<More> initMoreData(){
		try {
			List<More> mores = new ArrayList<MoreFragment.More>();
			for (int i = 0; i < moreNames.length; i++) {
				More more = new More();
				more.setIconView(moreIcons[i]);
				more.setNameTx(moreNames[i]);
				mores.add(more);
			}
			return mores;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	/**
	 * 适配器
	 */
	public class MoreAdapter extends BaseAdapter{
		List<More> mores;
		LayoutInflater inflater;
		public MoreAdapter(Context ctx,List<More> mores){
			this.mores = mores;
			this.inflater = LayoutInflater.from(ctx);
		}
		
		@Override
		public int getCount() {
			return mores.size();
		}

		@Override
		public Object getItem(int arg0) {
			return mores.get(arg0);
		}

		@Override
		public long getItemId(int arg0) {
			return 0;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			ViewHolder holder = null;
			if (arg1 == null) {
				holder = new ViewHolder();
				arg1 = inflater.inflate(R.layout.fragment_more_item, null);
				holder.iconView = (ImageView) arg1.findViewById(R.id.more_item_icon);
				holder.nameTx = (TextView) arg1.findViewById(R.id.more_item_name);
				arg1.setTag(holder);
			}else {
				holder = (ViewHolder) arg1.getTag();
			}
			More more = mores.get(arg0);
			holder.iconView.setImageResource(more.getIconView());
			holder.nameTx.setText(more.getNameTx());
			return arg1;
		}
		
		class ViewHolder{
			ImageView iconView;
			TextView nameTx;
		}

	}
	
	class More{
		int iconView;
		String nameTx;
		public int getIconView() {
			return iconView;
		}
		public void setIconView(int iconView) {
			this.iconView = iconView;
		}
		public String getNameTx() {
			return nameTx;
		}
		public void setNameTx(String nameTx) {
			this.nameTx = nameTx;
		}
		
	}
}
