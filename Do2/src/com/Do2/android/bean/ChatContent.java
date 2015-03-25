package com.Do2.android.bean;

/**
 * 聊天内容bean接口
 */
public class ChatContent {
	
	private int who;		//聊天内容来自,他人/本人
	private String time;	//聊天时间
	private int to_icon; 	//他人聊天头像
	private String to_content;	//他人聊天内容
	private int from_icon;		//本人聊天头像
	private String from_content;	//本人聊天内容
	
	
	public int getWho() {
		return who;
	}
	public void setWho(int who) {
		this.who = who;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getTo_icon() {
		return to_icon;
	}
	public void setTo_icon(int to_icon) {
		this.to_icon = to_icon;
	}
	public String getTo_content() {
		return to_content;
	}
	public void setTo_content(String to_content) {
		this.to_content = to_content;
	}
	public int getFrom_icon() {
		return from_icon;
	}
	public void setFrom_icon(int from_icon) {
		this.from_icon = from_icon;
	}
	public String getFrom_content() {
		return from_content;
	}
	public void setFrom_content(String from_content) {
		this.from_content = from_content;
	}
	
	
}
