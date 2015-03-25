package com.Do2.android.bean;

/**
 * 官方信息bean接口
 * @author zhous @Email:657523985@qq.com
 * @Time 2015-3-11 下午14:56
 */
public class OffInfo {
	private int img_url;	//图片url地址
	
	private String title;	//标题名
	
	private String content;	//内容
	
	private int comments;	//评论数
	
	public int getImg_url() {
		return img_url;
	}
	public void setImg_url(int img_url) {
		this.img_url = img_url;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getComments() {
		return comments;
	}
	public void setComments(int comments) {
		this.comments = comments;
	}
	
	
}
