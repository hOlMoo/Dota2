package com.Do2.android.adapter;

import java.util.List;

import com.Do2.android.R;
import com.Do2.android.bean.OffInfo;
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
public class OffListAdapter extends BaseAdapter{
	public static String TAG = OffListAdapter.class.getSimpleName();
	private List<OffInfo> offInfos;
	private LayoutInflater inflater;
	private Context ctx;
	public OffListAdapter(Context ctx,List<OffInfo> offInfos){
		this.offInfos = offInfos;
		this.ctx = ctx;
		inflater = LayoutInflater.from(ctx);
	}
	
	@Override
	public int getCount() {
		return offInfos.size();
	}

	@Override
	public Object getItem(int arg0) {
		return offInfos.get(arg0);
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
			view = inflater.inflate(R.layout.official_item, null);
			holder.iv = (ImageView) view.findViewById(R.id.off_item_iv);
			holder.titleTx = (TextView) view.findViewById(R.id.off_item_tv_title);
			holder.contentTx = (TextView) view.findViewById(R.id.off_item_tx_content);
			holder.commentsTx = (TextView) view.findViewById(R.id.off_item_tx_comment);
			view.setTag(holder);
		}else {
			holder = (ViewHolder) view.getTag();
		}
		OffInfo info = offInfos.get(position);
		holder.iv.setImageResource(info.getImg_url());
		holder.titleTx.setText(info.getTitle());
		holder.contentTx.setText(info.getContent());
		holder.commentsTx.setText(info.getComments()+"跟帖");
		return view;
	}
	
	private class ViewHolder{
		ImageView iv;
		TextView titleTx;
		TextView contentTx;
		TextView commentsTx;
	}
}
