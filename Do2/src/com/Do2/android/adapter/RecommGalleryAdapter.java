package com.Do2.android.adapter;

import java.util.List;

import com.Do2.android.R;
import com.Do2.android.log.Logger;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

/**
 * 推荐画廊适配器
 * @author zhous @Email:657523985@qq.com
 * @Date 2014-11-7 上午11:47
 */
public class RecommGalleryAdapter extends BaseAdapter{
	public static String TAG = RecommGalleryAdapter.class.getSimpleName();
	private List<Integer> photoResId;
	private LayoutInflater inflater;
	public RecommGalleryAdapter(Context ctx,List<Integer> photoResId){
		this.inflater = LayoutInflater.from(ctx);
		this.photoResId = photoResId;
	}
	
	@Override
	public int getCount() {
		return Integer.MAX_VALUE;
	}

	@Override
	public Object getItem(int arg0) {
		return photoResId.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(int position, View view, ViewGroup arg2) {
		ViewHolder holder = null;
		if (view == null) {
			holder = new ViewHolder();
			view = inflater.inflate(R.layout.recomm_gallery, null);
			holder.img = (ImageView) view.findViewById(R.id.recomm_gallery_img);
			view.setTag(holder);
		}else {
			holder = (ViewHolder) view.getTag();
		}
		int resId = photoResId.get(position%4);
		holder.img.setImageResource(resId);
		return view;
	}
	
	class ViewHolder{
		ImageView img;
	}
}
