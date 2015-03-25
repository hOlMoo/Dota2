package com.Do2.android.activity;

import java.util.ArrayList;
import java.util.List;

import com.Do2.android.R;
import com.Do2.android.Settings;
import com.Do2.android.adapter.ChatContentAdapter;
import com.Do2.android.bean.ChatContent;
import com.Do2.android.utils.ListHeightUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

/**
 * 聊天窗口
 * @author zhous @Email:657523985@qq.com
 * @Date 2014-10-28
 */
public class ChattingActivity extends Activity implements OnClickListener{
	private Button back;
	private Button set;
	private Button send;
	private EditText contentEdit;
	private ListView contentLV;
	private List<ChatContent> contentBeans = null;
	private ChatContentAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chatting_activity);
		findViewById();
		init();
	}
	
	/**
	 * 绑定UI控件
	 */
	private void findViewById(){
		back = (Button) findViewById(R.id.chat_main_back);
		set = (Button) findViewById(R.id.chat_main_person);
		send = (Button) findViewById(R.id.chatting_send);
		contentEdit = (EditText) findViewById(R.id.chatting_edit);
		contentLV = (ListView) findViewById(R.id.chatting_content_listview);
		back.setOnClickListener(this);
		set.setOnClickListener(this);
		send.setOnClickListener(this);
	}
	
	/**
	 * 初始化
	 */
	private void init(){
		initChatContentData();
		adapter = new ChatContentAdapter(this, contentBeans);
		contentLV.setAdapter(adapter);
//		ListHeightUtil.setListViewHeight(contentLV, adapter);
		contentLV.setSelection(contentBeans.size());
	}
	
	private void  initChatContentData(){
		try {
			contentBeans = new ArrayList<ChatContent>();
			for (int i = 0; i < 6; i++) {
				ChatContent bean = new ChatContent();
				bean.setWho(i%2);
				if (i%2 == 0) 
				{
					bean.setFrom_content("啦啦啦啦啦啦啦啦啦啦啦啦");
				}else {
					bean.setTo_content("啊啊啊啊啊啊啊啊啊啊啊啊");
				}
				contentBeans.add(bean);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.chat_main_back:
			finish();
			break;
		case R.id.chat_main_person:
			//跳转
			startActivity(new Intent(ChattingActivity.this,ChattingSetActivity.class));
			break;
		case R.id.chatting_send:
			String contentStr = contentEdit.getText().toString();
			if (contentStr == null || contentStr.trim().equals("")) {
				Toast.makeText(getApplicationContext(), "不能发送空白信息", Toast.LENGTH_SHORT).show();
				return;
			}
			if (contentBeans == null) {
				return;
			}
			ChatContent bean = new ChatContent();
			bean.setWho(Settings.chat_Person_To);
			bean.setTo_content(contentStr);
			contentBeans.add(bean);
			adapter.notifyDataSetChanged();
			contentLV.setSelection(contentBeans.size());
			contentEdit.setText("");
			break;
		default:
			break;
		}
		
	}
}
