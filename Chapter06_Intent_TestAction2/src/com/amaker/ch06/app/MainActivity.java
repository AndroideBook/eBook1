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
	// 宣告Button
	private Button btn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 設定目前佈局視圖
        setContentView(R.layout.main);
        // 實例化Button
        btn = (Button)findViewById(R.id.Button01);
        btn.setOnClickListener(new OnClickListener() {
			//@Override
			public void onClick(View v) {
		        // 建立Intent
		        Intent intent = new Intent();
		        // 設定Intent Action屬性
		        intent.setAction(Intent.ACTION_GET_CONTENT);
		        // 設定Intent Type 屬性
		        intent.setType("vnd.android.cursor.item/phone");
		        // 啟動Activity
		        startActivity(intent);
			}
		});
    }
}