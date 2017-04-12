package com.amaker.ch08.app;

import com.amaker.ch08.app.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
/**
 * @author 郭宏志
 * 測試廣播和通知
 */
public class MainActivity extends Activity {
	// 宣告Button
	private Button btn;
	// 定義Broadcast Receiver action
	private static final String MY_ACTION = "com.amaker.ch08.app.MY_ACTION";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 設定目前佈局視圖
        setContentView(R.layout.main);
        // 實例化Button
        btn = (Button)findViewById(R.id.Button01);
        // 添加事件監聽器
        btn.setOnClickListener(listener);
    }
    // 建立事件監聽器
    private OnClickListener listener = new OnClickListener() {
		//@Override
		public void onClick(View v) {
			// 實例化Intent
			Intent intent = new Intent();
			// 設定Intent action屬性
			intent.setAction(MY_ACTION);
			// 發出通知
			sendBroadcast(intent);
		}
	};
}