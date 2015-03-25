package com.Do2.android.fragment;

import java.util.ArrayList;
import java.util.List;

import com.Do2.android.R;
import com.Do2.android.adapter.FriendAdapter;
import com.Do2.android.bean.Friend;
import com.Do2.android.log.Logger;
import com.Do2.android.utils.ListHeightUtil;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class MySelfFragment extends Fragment{
	public static String TAG = MySelfFragment.class.getSimpleName();
	private ListView friendListView;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View contenctView = inflater.inflate(R.layout.fragment_myself, container, false);
		findViewById(contenctView);
		init();
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
	 */
	private void findViewById(View view){
		friendListView = (ListView) view.findViewById(R.id.myself_friend_listview);
	}
	
	/**
	 * 初始化
	 */
	private void init(){
		FriendAdapter adapter = new FriendAdapter(getActivity(), friendDate());
		friendListView.setAdapter(adapter);
		ListHeightUtil.setListViewHeight(friendListView, adapter);
	}
	
	private List<Friend> friendDate(){
		try {
			List<Friend> list = new ArrayList<Friend>();
			for (int i = 0; i < 5; i++) {
				Friend bean = new Friend();
				bean.setIcon(i + 1);
				list.add(bean);
			}
			return list;
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}
	
	
}

