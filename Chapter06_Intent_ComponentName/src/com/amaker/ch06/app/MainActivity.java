package com.amaker.ch06.app;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * @author 郭宏志
 * 測試Intent的ComponentName屬性
 */
public class MainActivity extends Activity {
	private Button btn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 設定視圖佈局
        setContentView(R.layout.main);
        // 實例化Button
        btn = (Button)findViewById(R.id.myButton01);
        // 添加單擊監器ん
        btn.setOnClickListener(new OnClickListener() {
			//@Override
			public void onClick(View v) {
				// 實例化元件名稱
				ComponentName cn = new ComponentName(MainActivity.this, "com.amaker.ch06.app1.MyActivity");
				// 實例化Intent
		        Intent intent = new Intent();
		        // 為Intent設定元件名稱屬性
		        intent.setComponent(cn);
		        // 啟動Activity
		        startActivity(intent);
			}
		});
    }
}