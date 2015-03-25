package com.Do2.android.adapter;

import java.util.List;

import com.Do2.android.R;
import com.Do2.android.Settings;
import com.Do2.android.bean.ChatContent;
import com.Do2.android.widget.CircleImageView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 聊天内容适配器
 * @author zhous @Email:657523985@qq.com
 * @Date 2014-10-31 下午14:19
 */
public class ChatContentAdapter extends BaseAdapter{
	LayoutInflater inflater;
	List<ChatContent> contentBeans;
	public ChatContentAdapter(Context ctx,List<ChatContent> contentBeans){
		this.inflater = LayoutInflater.from(ctx);
		this.contentBeans = contentBeans;
	}
	
	@Override
	public int getCount() {
		return contentBeans.size();
	}

	@Override
	public Object getItem(int arg0) {
		return contentBeans.get(arg0);
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
			arg1 = inflater.inflate(R.layout.chatting_activity_item, null);
			holder.fromLayout = (RelativeLayout) arg1.findViewById(R.id.chatting_item_from_layout);
			holder.fromImg = (CircleImageView) arg1.findViewById(R.id.chatting_item_from_icon);
			holder.fromTx = (TextView) arg1.findViewById(R.id.chatting_item_from_content);
			holder.toLayout = (RelativeLayout) arg1.findViewById(R.id.chatting_item_to_layout);
			holder.toImg = (CircleImageView) arg1.findViewById(R.id.chatting_item_to_icon);
			holder.toTx = (TextView) arg1.findViewById(R.id.chatting_item_to_content);
			arg1.setTag(holder);
		}else {
			holder = (ViewHolder) arg1.getTag();
		}
		ChatContent contentBean = contentBeans.get(arg0);
		if (contentBean.getWho() == Settings.chat_Person_Form) 
		{
			holder.toLayout.setVisibility(View.GONE);
			holder.fromLayout.setVisibility(View.VISIBLE);
			holder.fromTx.setText(contentBean.getFrom_content());
		}else {
			holder.fromLayout.setVisibility(View.GONE);
			holder.toLayout.setVisibility(View.VISIBLE);
			holder.toTx.setText(contentBean.getTo_content());
		}
		return arg1;
	}
	
	class ViewHolder{
		RelativeLayout fromLayout;
		CircleImageView fromImg;
		TextView fromTx;
		RelativeLayout toLayout;
		CircleImageView toImg;
		TextView toTx;
	}
	
}
