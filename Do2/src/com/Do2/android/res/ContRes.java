package com.Do2.android.res;

import java.util.ArrayList;
import java.util.List;

import com.Do2.android.R;
import com.Do2.android.bean.Game;
import com.Do2.android.bean.OffInfo;
import com.Do2.android.bean.Star;

/**
 * 资源类(用于测试)
 * @author zhous @Email：657523985@qq.com
 * @Date 2015-3-11 下午15:44
 */
public class ContRes {
	
	public static List<Integer> photoGalleryRes(){
		List<Integer> dataList = new ArrayList<Integer>();
		dataList.add(R.drawable.recomm_gallery_01);
		dataList.add(R.drawable.recomm_gallery_02);
		dataList.add(R.drawable.recomm_gallery_03);
		dataList.add(R.drawable.recomm_gallery_04);
		return dataList;
	}
	/**
	 * view_01
	 */
	public static int offIcons[] = {R.drawable.official_item_01,R.drawable.official_item_02,R.drawable.official_item_03,
		R.drawable.official_item_04,R.drawable.official_item_05};
	public static String offTitles[] = {"Kunkka","Mirana","Io","Lina","Meepo"};
	public static String offContents[] = {
		"他就是Kunkka，七大洋的统帅。",
		"月神Elune庇护下的长老和高等祭司。",
		"IO是为了保卫族人而战，而不是为了近卫与天灾之间永无止境的仇恨。",
		"Lina Inverse是一位屠龙法师。",
		"狗头神教，你来我就跳。"};
	public static int offComments[] = {23,120,15,72,66};
	
	public static List<OffInfo> offInfosRes(){
		List<OffInfo> offs = new ArrayList<OffInfo>();
		for (int i = 0; i < offIcons.length; i++) {
			OffInfo off = new OffInfo();
			off.setImg_url(offIcons[i]);
			off.setTitle(offTitles[i]);
			off.setContent(offContents[i]);
			off.setComments(offComments[i]);
			offs.add(off);
		}
		return offs;
	}
	/**
	 * view_02
	 */
	public static List<Game> gamesRes(){
		List<Game> games = new ArrayList<Game>();
		for (int i = 0; i < 4; i++) {
			Game game = new Game();
			games.add(game);
		}
		return games;
	}
	/**
	 * view_02
	 */
	public static List<Star> starsRes(){
		List<Star> stars = new ArrayList<Star>();
		for (int i = 0; i < 4; i++) {
			Star star = new Star();
			stars.add(star);
		}
		return stars;
	}
}
