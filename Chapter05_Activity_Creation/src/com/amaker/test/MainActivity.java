package com.amaker.test;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

// 繼承Activity
public class MainActivity extends Activity {
	// 宣告要使用的元件
	private TextView myTextView;
	private Button myButton;
    // 覆蓋onCreate方法
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 設定目前視圖
        setContentView(R.layout.main);
        // 透過findViewById()方法實例化元件
        myTextView = (TextView) findViewById(R.id.TextView01);
        myButton = (Button) findViewById(R.id.Button01);
    }
}