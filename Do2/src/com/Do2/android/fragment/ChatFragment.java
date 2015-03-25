package com.Do2.android.fragment;

import java.util.ArrayList;
import java.util.List;

import zrc.widget.SimpleFooter;
import zrc.widget.SimpleHeader;
import zrc.widget.ZrcListView;
import zrc.widget.ZrcListView.OnItemClickListener;
import zrc.widget.ZrcListView.OnStartListener;

import com.Do2.android.R;
import com.Do2.android.activity.ChattingActivity;
import com.Do2.android.activity.SearchActivity;
import com.Do2.android.adapter.ChatUserAdapter;
import com.Do2.android.bean.ChatUser;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 
 * @author zhous @Email:657523985@qq.com
 * @Date 2014-10-10 @Time  
 *
 */
public class ChatFragment extends Fragment{
	public static String TAG = ChatFragment.class.getSimpleName();
	/**
	 * 上拉加载下拉更多listview,上下滑动有动画
	 */
	private ZrcListView listView;
	/**
	 * listview适配器
	 */
	private ChatUserAdapter adapter;
	/**
	 * 主线程处理器
	 */
	private Handler handler;
	/**
	 * 测试数字
	 */
	private int testNumber = -1;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View contenctView = inflater.inflate(R.layout.fragment_chat,container, false);
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
		listView = (ZrcListView) view.findViewById(R.id.chat_listview);
	}
	
	/**
	 * 初始化listview
	 */
	private void init(){
		initListView();
		adapter = new ChatUserAdapter(getActivity(), requestChatResult());
	    listView.setAdapter(adapter);
	}
	
	/**
	 * 监听事件
	 */
	private void setListener(){
		listView.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(ZrcListView parent, View view, int position, long id) {
				if (position == 0) 
				{
					startActivity(new Intent(getActivity(),SearchActivity.class));
					getActivity().overridePendingTransition(R.anim.topitem_in, R.anim.topitem_out);
				}else 
				{
					startActivity(new Intent(getActivity(),ChattingActivity.class));
				}
			}
		});
	}
	
	/**
	 * 刷新view
	 * @param result
	 */
	public void updateView(String result){
	}
	
	private void initListView(){
		 handler = new Handler();
		// 设置默认偏移量，主要用于实现透明标题栏功能。（可选）
	     float density = getActivity().getResources().getDisplayMetrics().density;
	     listView.setFirstTopOffset((int) (50 * density));

	     // 设置下拉刷新的样式（可选，但如果没有Header则无法下拉刷新）
	     SimpleHeader header = new SimpleHeader(getActivity());
	     header.setTextColor(getResources().getColor(R.color.black_f2));
	     header.setCircleColor(getResources().getColor(R.color.black_f2));
	     listView.setHeadable(header);

	     // 设置加载更多的样式（可选）
	     SimpleFooter footer = new SimpleFooter(getActivity());
	     footer.setCircleColor(0xff33bbee);
	     listView.setFootable(footer);

	     // 设置列表项出现动画（可选）
	     listView.setItemAnimForTopIn(R.anim.topitem_in);
	     listView.setItemAnimForBottomIn(R.anim.bottomitem_in);

	     // 下拉刷新事件回调（可选）
	     listView.setOnRefreshStartListener(new OnStartListener() {
	        @Override
	        public void onStart() {
	            refresh();
	        }
	     });

	     // 加载更多事件回调（可选）
	     listView.setOnLoadMoreStartListener(new OnStartListener() {
	         @Override
	         public void onStart() {
	            loadMore();
	         }
	     });
	        
	}
	
	/**
	 * 自定义的参数（可删）
	 * @return
	 */
	private List<ChatUser> requestChatResult(){
		try {
			List<ChatUser> list = new ArrayList<ChatUser>();
			for (int i = 0; i < 25; i++) {
				ChatUser bean = new ChatUser();
				list.add(bean);
			}
			return list;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	/**
	 * 上拉刷新
	 */
	private void refresh(){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                int rand = (int) (Math.random() * 2); // 随机数模拟成功失败。这里从有数据开始。
                if(rand == 0 || testNumber == -1){
                    adapter.notifyDataSetChanged();
                    
                    listView.setRefreshSuccess("加载成功"); // 通知加载成功
                    listView.startLoadMore(); // 开启LoadingMore功能
                }else{
                    listView.setRefreshFail("加载失败");
                }
            }
        }, 2 * 1000);
    }  
	
	/**
	 * 下拉加载更多
	 */
	private void loadMore(){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
            	listView.stopLoadMore();
                testNumber++;
//                if(testNumber< 8){
//                    adapter.notifyDataSetChanged();
//                    listView.setLoadMoreSuccess();
//                }else{
//                    listView.stopLoadMore();
//                }
            }
        }, 2 * 1000);
    }    
	
}
