package com.Do2.android.fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.Do2.android.R;
import com.Do2.android.adapter.ContactsPagerAdapter;
import com.Do2.android.adapter.FragmentTabAdapter;
import com.Do2.android.adapter.GameListAdapter;
import com.Do2.android.adapter.OffListAdapter;
import com.Do2.android.adapter.RecommGalleryAdapter;
import com.Do2.android.adapter.StarListAdapter;
import com.Do2.android.log.Logger;
import com.Do2.android.res.ContRes;
import com.Do2.android.utils.ListHeightUtil;
import com.Do2.android.widget.RollingOneGallery;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 资讯页
 * @author zhous @Email:657523985@qq.com
 * @Date 2014-10-29 
 */
public class ContactsFragment extends Fragment{
	public static String TAG = ContactsFragment.class.getSimpleName();
	/**
	 * Contacts元素
	 */
	//Title元素
	private RelativeLayout menuLayout_1;
	private TextView menuTx_1;
	private RelativeLayout menuLayout_2;
	private TextView menuTx_2;
	private RelativeLayout menuLayout_3;
	private TextView menuTx_3;
	private RelativeLayout menuLayout_4;
	private TextView menuTx_4;
	private RelativeLayout menuLayout_5;
	private TextView menuTx_5;
	//View-recomm-gallery
	private ImageView pointView_1;
	private ImageView pointView_2;
	private ImageView pointView_3;
	private ImageView pointView_4;
	//View-01元素 recomm推荐
	private RollingOneGallery mGallery;
	//View-02元素official官方
	private ListView mOffListView;
	//View-03元素game赛事
	private ListView mGameListView;
	//View-04元素star明星
	private ListView mStarListView;
	/**
	 * 引导线
	 */
	private ImageView lineView;
	/**
	 * 屏幕宽度
	 */
	private int screenWidth;
	/**
	 * 当前item
	 */
	private int currentIndex = 1; 
	/**
	 * 显示内容的View
	 */
	private View mPage1, mPage2, mPage3,mPage4,mPage5;
	private ViewPager mViewPager;
	/**
	 * 存放显示内容的View
	 */
	private List<View> mViews = new ArrayList<View>();
	/**
	 * view1中gallery间隔2s切换计时器
	 */
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View contenctView = inflater.inflate(R.layout.fragment_message, container, false);
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
		menuLayout_1 = (RelativeLayout) view.findViewById(R.id.fragment_msg_menu_01);
		menuTx_1 = (TextView) view.findViewById(R.id.menu_tx_01);
		menuLayout_2 = (RelativeLayout) view.findViewById(R.id.fragment_msg_menu_02);
		menuTx_2 = (TextView) view.findViewById(R.id.menu_tx_02);
		menuLayout_3 = (RelativeLayout) view.findViewById(R.id.fragment_msg_menu_03);
		menuTx_3 = (TextView) view.findViewById(R.id.menu_tx_03);
		menuLayout_4 = (RelativeLayout) view.findViewById(R.id.fragment_msg_menu_04);
		menuTx_4 = (TextView) view.findViewById(R.id.menu_tx_04);
		menuLayout_5 = (RelativeLayout) view.findViewById(R.id.fragment_msg_menu_05);
		menuTx_5 = (TextView) view.findViewById(R.id.menu_tx_05);
		lineView = (ImageView) view.findViewById(R.id.fragment_msg_menu_line);
		mViewPager = (ViewPager) view.findViewById(R.id.fragment_contacts_viewpager);
	}
	
	/**
	 * 初始化
	 */
	private void init(){
		initTabLine();
		initContentView();
		initRequestData();
	}
	
	/**
	 * 事件监听
	 */
	private void setListener(){
		menuLayout_1.setOnClickListener(new MenuListener(0));
		menuLayout_2.setOnClickListener(new MenuListener(1));
		menuLayout_3.setOnClickListener(new MenuListener(2));
		menuLayout_4.setOnClickListener(new MenuListener(3));
		menuLayout_5.setOnClickListener(new MenuListener(4));
	}
	
	/**
	 * 初始化底部内容View
	 */
	private void initContentView(){
		/**
		 * 获取要显示的资讯页内容
		 */
		mPage1 = LayoutInflater.from(getActivity()).inflate
				(R.layout.contacts_page1, null);
		mPage2 = LayoutInflater.from(getActivity()).inflate
				(R.layout.contacts_page2, null);
		mPage3 = LayoutInflater.from(getActivity()).inflate
				(R.layout.contacts_page3, null);
		mPage4 = LayoutInflater.from(getActivity()).inflate
				(R.layout.contacts_page4, null);
		mPage5 = LayoutInflater.from(getActivity()).inflate
				(R.layout.contacts_page5, null);
		/**
		 * 添加View
		 */
		mViews.add(mPage1);
		mViews.add(mPage2);
		mViews.add(mPage3);
		mViews.add(mPage4);
		mViews.add(mPage5);
		/**
		 * 设置适配器
		 */
		mViewPager.setAdapter(new ContactsPagerAdapter(mViews));
		/**
		 * 设置监听
		 */
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				//重置所有Text的字体颜色
				recoverTextColor();
				switch (arg0) {
				case 0:
					menuTx_1.setTextColor(getResources().getColor(R.color.tab_selected_red));
					break;
				case 1:
					menuTx_2.setTextColor(getResources().getColor(R.color.tab_selected_red));
					break;
				case 2:
					menuTx_3.setTextColor(getResources().getColor(R.color.tab_selected_red));
					break;
				case 3:
					menuTx_4.setTextColor(getResources().getColor(R.color.tab_selected_red));
					break;
				case 4:
					menuTx_5.setTextColor(getResources().getColor(R.color.tab_selected_red));
					break;
				default:
					break;
				}
				currentIndex = arg0;
			}
			
			@Override
			public void onPageScrolled(int position, float positionOffset, 
					int positionOffsetPixels) {
				RelativeLayout.LayoutParams lp = (android.widget.RelativeLayout.LayoutParams) lineView
						.getLayoutParams();
				/**
				 * 利用position和currentIndex判断用户的操作是哪一页往哪一页滑动
				 * 然后改变根据positionOffset动态改变TabLine的leftMargin
				 */
				if (currentIndex == 0 && position == 0)// 0->1
				{
					lp.leftMargin = (int) (positionOffset
							* (screenWidth * 1.0 / 5) + currentIndex
							* (screenWidth / 5));
				} else if (currentIndex == 1 && position == 0) // 1->0
				{
					lp.leftMargin = (int) (-(1 - positionOffset)
							* (screenWidth * 1.0 / 5) + currentIndex
							* (screenWidth / 5));
				} else if (currentIndex == 1 && position == 1) // 1->2
				{
					lp.leftMargin = (int) (positionOffset
							* (screenWidth * 1.0 / 5) + currentIndex
							* (screenWidth / 5));
				} else if (currentIndex == 2 && position == 1) // 2->1
				{
					lp.leftMargin = (int) (-(1 - positionOffset)
							* (screenWidth * 1.0 / 5) + currentIndex
							* (screenWidth / 5));
				}else if (currentIndex == 2 && position == 2) {// 2->3 
					lp.leftMargin = (int) (positionOffset
							* (screenWidth * 1.0 / 5) + currentIndex
							* (screenWidth / 5));
				}else if (currentIndex == 3 && position == 2) {// 3->2 
					lp.leftMargin = (int) (-(1 - positionOffset)
							* (screenWidth * 1.0 / 5) + currentIndex
							* (screenWidth / 5));
				}else if (currentIndex == 3 && position == 3) {// 3->4 
					lp.leftMargin = (int) (positionOffset
							* (screenWidth * 1.0 / 5) + currentIndex
							* (screenWidth / 5));
				}else if (currentIndex == 4 && position == 3) {// 4->3 
					lp.leftMargin = (int) (-(1 - positionOffset)
							* (screenWidth * 1.0 / 5) + currentIndex
							* (screenWidth / 5));
				}
				lineView.setLayoutParams(lp);
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				
			}
		});
		//默认第一项
		mViewPager.setCurrentItem(0);
		
	}
	
	/**
	 * 初始化请求数据资源
	 */
	private void initRequestData(){
		initView_One();
		initView_Two();
		initView_Three();
		initView_Four();
	}
	
	/**
	 * 初始化View1
	 */
	@SuppressWarnings("deprecation")
	private void initView_One(){
		mGallery = (RollingOneGallery) mPage1.findViewById(R.id.contacts_recomm_gallery);
		RecommGalleryAdapter adapter = new RecommGalleryAdapter(getActivity(), ContRes.photoGalleryRes());
		mGallery.setAdapter(adapter);
		//取消Gallery非选中阴影
		mGallery.setUnselectedAlpha(100);
		initPointView();
		mGallery.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				recoverPointImageView();
				selectPointImageView(position%4);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
	}
	
	/**
	 * 初始化pointview
	 */
	private void initPointView(){
		pointView_1 = (ImageView) mPage1.findViewById(R.id.recomm_point_01);
		pointView_2 = (ImageView) mPage1.findViewById(R.id.recomm_point_02);
		pointView_3 = (ImageView) mPage1.findViewById(R.id.recomm_point_03);
		pointView_4 = (ImageView) mPage1.findViewById(R.id.recomm_point_04);
	}
	
	/**
	 * 恢复默认pointview显示状态
	 */
	private void recoverPointImageView(){
		pointView_1.setImageResource(R.drawable.layout_01_gallery_unselected);
		pointView_2.setImageResource(R.drawable.layout_01_gallery_unselected);
		pointView_3.setImageResource(R.drawable.layout_01_gallery_unselected);
		pointView_4.setImageResource(R.drawable.layout_01_gallery_unselected);
	}
	
	/**
	 * 选择选中pointview
	 * @param position
	 */
	private void selectPointImageView(int position){
		switch (position) {
		case 0:
			pointView_1.setImageResource(R.drawable.layout_01_gallery_selected);
			break;
		case 1:
			pointView_2.setImageResource(R.drawable.layout_01_gallery_selected);
			break;
		case 2:
			pointView_3.setImageResource(R.drawable.layout_01_gallery_selected);
			break;
		case 3:
			pointView_4.setImageResource(R.drawable.layout_01_gallery_selected);
			break;
		default:
			break;
		}
	}
	
	
	
	/**
	 * 初始化view2
	 */
	private void initView_Two(){
		mOffListView = (ListView) mPage2.findViewById(R.id.official_listview);
		OffListAdapter offAdapter = new OffListAdapter(getActivity(), ContRes.offInfosRes());
		mOffListView.setAdapter(offAdapter);
		ListHeightUtil.setListViewHeight(mOffListView, offAdapter);
	}
	
	private void initView_Three(){
		mGameListView = (ListView) mPage3.findViewById(R.id.game_listview);
		GameListAdapter gameAdapter = new GameListAdapter(getActivity(), ContRes.gamesRes());
		mGameListView.setAdapter(gameAdapter);
		ListHeightUtil.setListViewHeight(mGameListView, gameAdapter);
	}
	
	private void initView_Four(){
		mStarListView = (ListView) mPage4.findViewById(R.id.star_listview);
		StarListAdapter starAdapter = new StarListAdapter(getActivity(), ContRes.starsRes());
		mStarListView.setAdapter(starAdapter);
		ListHeightUtil.setListViewHeight(mStarListView, starAdapter);
	}
	
	/**
	 * 根据屏幕的宽度,初始化tab下红线宽度
	 */
	private void initTabLine(){
		DisplayMetrics outMetrics = new DisplayMetrics();
		getActivity().getWindow().getWindowManager().getDefaultDisplay()
				.getMetrics(outMetrics);
		screenWidth = outMetrics.widthPixels;
		RelativeLayout.LayoutParams lp = (android.widget.RelativeLayout.LayoutParams) lineView
				.getLayoutParams();
		lp.width = screenWidth / 5;
		lineView.setLayoutParams(lp);
	}
	
	/**
	 * 顶部layout点击事件
	 * @author Administrator
	 *
	 */
	private class MenuListener implements OnClickListener{
		private int n;
		public MenuListener(int n){
			this.n = n;
		}
		@Override
		public void onClick(View arg0) {
			mViewPager.setCurrentItem(n);
		}
	}
	
	/**
	 * 重置字体颜色
	 */
	private void recoverTextColor(){
		menuTx_1.setTextColor(getResources().getColor(R.color.black));
		menuTx_2.setTextColor(getResources().getColor(R.color.black));
		menuTx_3.setTextColor(getResources().getColor(R.color.black));
		menuTx_4.setTextColor(getResources().getColor(R.color.black));
		menuTx_5.setTextColor(getResources().getColor(R.color.black));
	}
	
	
}
