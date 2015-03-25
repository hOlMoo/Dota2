package com.Do2.android.adapter;

import java.util.List;

import com.Do2.android.R;
import com.Do2.android.bean.ChatUser;
import com.Do2.android.bean.Friend;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Friend页listview的适配器
 * @author zhous @Email:657523985@qq.com
 * @Date 2014-10-23
 */
public class FriendAdapter extends BaseAdapter{
	private LayoutInflater inflater;
	private List<Friend> friends;
	
	public FriendAdapter(Context ctx,List<Friend> friends){
		this.friends = friends;
		this.inflater = LayoutInflater.from(ctx);
	}
	
	@Override
	public int getCount() {
		return friends.size();
	}

	@Override
	public Object getItem(int arg0) {
		return friends.get(arg0);
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
			arg1 = inflater.inflate(R.layout.fragment_myself_friend_item, null);
			holder.iconImg = (ImageView) arg1.findViewById(R.id.friend_item_icon);
			holder.titleTx = (TextView) arg1.findViewById(R.id.friend_item_title);
			holder.contentTx = (TextView) arg1.findViewById(R.id.friend_item_content);
			holder.liveTx = (TextView) arg1.findViewById(R.id.friend_item_live);
			arg1.setTag(holder);
		}else {
			holder = (ViewHolder) arg1.getTag();
		}
		Friend friend = friends.get(arg0);
		switch (friend.getIcon()) {
		case 1:
			holder.iconImg.setImageResource(R.drawable.friend_01);
			holder.titleTx.setText("爱斯基摩");
			holder.contentTx.setText("什么叫大胆假设，就是胡扯!");
			break;
		case 2:
			holder.iconImg.setImageResource(R.drawable.friend_02);
			holder.titleTx.setText("又起雾了");
			holder.contentTx.setText("你怎么长高不长脑?");
			break;
		case 3:
			holder.iconImg.setImageResource(R.drawable.friend_03);
			holder.titleTx.setText("天國槍神");
			holder.contentTx.setText("头痛...");
			break;
		case 4:
			holder.iconImg.setImageResource(R.drawable.friend_04);
			holder.titleTx.setText("2货");
			holder.contentTx.setText("你正常是什么样的,正常的样子吗，我不会也..");
			break;
		case 5:
			holder.iconImg.setImageResource(R.drawable.friend_05);
			holder.titleTx.setText("小墩");
			holder.contentTx.setText("你是个胖子不是一天两天了.");
			break;
		default:
			break;
		}
		
		return arg1;
	}
	
	class ViewHolder{
		ImageView iconImg;
		TextView titleTx;
		TextView contentTx;
		TextView liveTx;
		Button deleteBtn;
	}
	
	class ViewHolder_Search{
		RelativeLayout searchLayout;
	}

}
