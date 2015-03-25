package com.Do2.android.bean;

/**
 * chat聊天bean接口
 * @author zhous @Email:657523985@qq.com
 * @Date 2014-10-23 下午16:08
 */
public class ChatUser {
	
	private String icon; //图标
	
	private String title; //标题
	
	private String content;  //最新内容
	
	private String time;  //最新时间

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
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

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
	
	
}
