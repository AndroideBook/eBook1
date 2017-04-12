package com.amaker.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * @author 郭宏志
 * 測試Intent 的 Category屬性
 */
public class MainActivity extends Activity {
	// 宣告 Button
	private Button b1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 設定目前佈局
        setContentView(R.layout.main);
        // 實例化 Button
        b1 = (Button)findViewById(R.id.Button01);
        // 為Button添加監聽器ん
        b1.setOnClickListener(new OnClickListener() {
			//@Override
			public void onClick(View v) {
				// 實例化Intent
		        Intent i = new Intent();
		        // 添加Action屬性
		        i.setAction(Intent.ACTION_MAIN);
		        // 添加Category屬性
		        i.addCategory(Intent.CATEGORY_HOME);
		        // 啟動Activity
		        startActivity(i);
			}
		});
    }
}