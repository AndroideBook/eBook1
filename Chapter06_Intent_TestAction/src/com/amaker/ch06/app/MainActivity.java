package com.amaker.ch06.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
/**
 * 
 * @author 郭宏志
 * 測試Intent Action 屬性
 */
public class MainActivity extends Activity {
	// 定義Action 屬性常數
	public static final String MY_ACTION="com.amaker.ch06.app.MY_ACTION";
	// 宣告Button
	private Button btn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 設定佈局視圖
        setContentView(R.layout.main);
        // 實例化Button
        btn = (Button)findViewById(R.id.Button01);
        btn.setOnClickListener(new OnClickListener() {
			//@Override
			public void onClick(View v) {
				// 實例化Intent
		        Intent intent = new Intent();
		        // 為Intent設定Action屬性
		        intent.setAction(MY_ACTION);
		        // 啟動Activity
		        startActivity(intent);
			}
		});
    }
}