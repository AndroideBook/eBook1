package com.amaker.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FirstActivity extends Activity {
    /** Called when the activity is first created. */
	private Button b1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first);
        b1 = (Button) findViewById(R.id.Button01);
        // 回應按鍵事件
        b1.setOnClickListener(new OnClickListener() {
			//@Override
			public void onClick(View v) {
				// 顯示方式宣告Intent，直接啟動SecondActivity
		        Intent intent = new Intent(FirstActivity.this,SecondActivity.class);
		        startActivity(intent);
			}
		});
    }
}