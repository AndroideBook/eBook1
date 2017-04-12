package com.amaker.ch06.app;

import com.amaker.ch06.app.R;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
/**
 * 
 * @author 郭宏志
 * Intent Filter 測試
 */
public class MainActivity extends Activity {
	// 宣告Button
	private Button btn;
	//@SuppressWarnings("unused")
	private static final String ACTION1 = "com.amaker.ch07.app.TEST_ACTION1";
	//@SuppressWarnings("unused")
	private static final String ACTION2 = "com.amaker.ch07.app.TEST_ACTION2";
	//@SuppressWarnings("unused")
	private static final String ACTION3 = "com.amaker.ch07.app.TEST_ACTION3";
	
	private static final String CATEGORY1 = "com.amaker.ch07.app.CATEGORY1";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 設定內容佈局
        setContentView(R.layout.main);
        // 實例化按鈕
        btn = (Button)findViewById(R.id.Button01);
        
        String a = Intent.ACTION_VIEW;
        // 添加單擊監聽器
        btn.setOnClickListener(new OnClickListener() {
			//@Override
			public void onClick(View view) {
				  Intent intent = new Intent();
				  //intent.setAction(ACTION1);
				  Uri data = Uri.parse("content://com.amaker.ch06.app/abc");
				  intent.setData(data);
				  
				  intent.addCategory(CATEGORY1);
				  
				  intent.setAction("android.intent.action.VIEW");
				  intent.setData(Uri.parse("http://www.google.com"));
				  
			      startActivity(intent);
			}
		});
    }
}