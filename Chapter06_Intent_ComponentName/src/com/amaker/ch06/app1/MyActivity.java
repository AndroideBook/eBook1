package com.amaker.ch06.app1;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.amaker.ch06.app.R;

/**
 * @author 郭宏志
 * 測試Intent的ComponentName屬性
 */
public class MyActivity extends Activity {
	// 宣加TextView
	private TextView tv;
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	// 設定視圖佈局
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_layout);
        // 取得Intent
        Intent intent = this.getIntent();
        // 取得元件名稱物件
        ComponentName cn = intent.getComponent();
        // 取得套件名稱
        String packageName = cn.getPackageName();
        // 取得類別名稱
        String className = cn.getClassName();
        // 實例化TextView
        tv = (TextView)findViewById(R.id.TextView01);
        // 顯示
        tv.setText("套件名稱："+packageName+"\n"+"類別名稱："+className);
    }
}