package com.Do2.android.adapter;

import java.util.List;

import com.Do2.android.R;
import com.Do2.android.bean.ChatUser;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * chat页listview的适配器
 * @author zhous @Email:657523985@qq.com
 * @Date 2014-10-23
 */
public class ChatUserAdapter extends BaseAdapter{
	private LayoutInflater inflater;
	private List<ChatUser> chatUsers;
	
	public ChatUserAdapter(Context ctx,List<ChatUser> chatUsers){
		this.chatUsers = chatUsers;
		this.inflater = LayoutInflater.from(ctx);
	}
	
	@Override
	public int getCount() {
		return chatUsers.size() + 1;
	}

	@Override
	public Object getItem(int arg0) {
		return chatUsers.get(arg0);
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
			arg1 = inflater.inflate(R.layout.fragment_chat_item, null);
			holder.iconImg = (ImageView) arg1.findViewById(R.id.chat_item_icon);
			holder.titleTx = (TextView) arg1.findViewById(R.id.chat_item_title);
			holder.contentTx = (TextView) arg1.findViewById(R.id.chat_item_content);
			holder.timeTx = (TextView) arg1.findViewById(R.id.chat_item_time);	
			holder.searchLayout = (RelativeLayout) arg1.findViewById(R.id.chat_item_search_layout);
			holder.normalLayout = (LinearLayout) arg1.findViewById(R.id.chat_item_normal_layout);
			arg1.setTag(holder);
		}else {
			holder = (ViewHolder) arg1.getTag();
		}	
		if (arg0 == 0) 
		{
			holder.searchLayout.setVisibility(View.VISIBLE);
			holder.normalLayout.setVisibility(View.GONE);
		}else {
			holder.searchLayout.setVisibility(View.GONE);
			holder.normalLayout.setVisibility(View.VISIBLE);
		}	
		
		return arg1;
	}
	
	class ViewHolder{
		ImageView iconImg;
		TextView titleTx;
		TextView contentTx;
		TextView timeTx;
		RelativeLayout searchLayout;
		LinearLayout normalLayout;
	}

}
