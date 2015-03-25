package com.Do2.android.adapter;

import java.util.List;

import com.Do2.android.R;
import com.Do2.android.bean.Game;
import com.Do2.android.bean.OffInfo;
import com.Do2.android.bean.Star;
import com.Do2.android.log.Logger;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 资讯页类官方模块listview适配器
 * @author zhous @Email:657523985@qq.com
 * @Date 2015-3-11 下午15:59
 */
public class StarListAdapter extends BaseAdapter{
	public static String TAG = StarListAdapter.class.getSimpleName();
	private List<Star> stars;
	private LayoutInflater inflater;
	private Context ctx;
	public StarListAdapter(Context ctx,List<Star> stars){
		this.stars = stars;
		this.ctx = ctx;
		inflater = LayoutInflater.from(ctx);
	}
	
	@Override
	public int getCount() {
		return stars.size();
	}

	@Override
	public Object getItem(int arg0) {
		return stars.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int position, View view, ViewGroup arg2) {
		ViewHolder holder = null;
		if (view == null) {
			holder = new ViewHolder();
			view = inflater.inflate(R.layout.star_item, null);
			view.setTag(holder);
		}else {
			holder = (ViewHolder) view.getTag();
		}
		return view;
	}
	
	private class ViewHolder{
	}
}
