package com.amaker.ch06.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
/**
 * @author 郭宏志
 * 測試Intent Action 屬性
 */
public class MyActivity extends Activity {
	// 宣告TextView
	private TextView tv;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 設定視圖佈局
        setContentView(R.layout.my_layout);
        // 設定Intent物件
        Intent intent = getIntent();
        // 取得Action
        String action = intent.getAction();
        // 取得TextView
        tv = (TextView)findViewById(R.id.TextView01);
        // 設定內容
        tv.setText(action);
    }
}