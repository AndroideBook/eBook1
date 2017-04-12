package com.amaker.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SecondActivity extends Activity {
    /** Called when the activity is first created. */
	private Button b2;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        
        b2 = (Button) findViewById(R.id.Button02);
        // 回應按鍵事件
        b2.setOnClickListener(new OnClickListener() {
			//@Override
			public void onClick(View v) {
				// 顯示方式宣告Intent，直接啟動SecondActivity
		        Intent intent = new Intent(SecondActivity.this,FirstActivity.class);
		        startActivity(intent);
			}
		});
    }
}