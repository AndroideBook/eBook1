package com.amaker.ch08.app;

import com.amaker.ch08.app.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
/**
 * 
 * @author 郭宏志
 * 發出廣播
 */
public class MainActivity extends Activity {
	// 定義一個Action常數
	private static final String MY_ACTION = "com.amaker.ch08.action.MY_ACTION";
	// 定義一個Button物件
	private Button btn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 設定目前佈局視圖
        setContentView(R.layout.main);
        btn = (Button)findViewById(R.id.Button01);
        // 為按鈕設定單擊監聽器
        btn.setOnClickListener(new OnClickListener() {
			//@Override
			public void onClick(View v) {
				// 實例化Intent物件
		        Intent intent = new Intent();
		        // 設定Intent action屬性
		        intent.setAction(MY_ACTION);
		        // 為Intent添加附加訊息
		        intent.putExtra("msg", "蕃薯蕃薯，我是芋仔，收到請回答，收到請回答！");
		        // 發出廣播
		        sendBroadcast(intent);
			}
		});
    }
}