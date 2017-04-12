package com.amaker.ch12.app;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {
	// 宣告使用到的Button視圖元件
	private Button b1, b2;
	// 宣告使用到的ImageView元件
	private ImageView myImage;
	// 宣告AnimationDrawable
	private AnimationDrawable danceAnimation;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 設定目前視圖佈局
		setContentView(R.layout.main);
		// 實例化視圖元件
		myImage = (ImageView) findViewById(R.id.ImageView01);
		b1 = (Button) findViewById(R.id.Button01);
		b2 = (Button) findViewById(R.id.Button02);
		
		// 取得背景色，為轉換為AnimationDrawable物件
		danceAnimation = (AnimationDrawable) myImage.getBackground();
		
		// 為按鈕添加監聽事件
		b1.setOnClickListener(new OnClickListener() {
			//@Override
			public void onClick(View v) {
				// 開始動畫
				danceAnimation.start();
			}
		});
		// 為按鈕添加監聽事件
		b2.setOnClickListener(new OnClickListener() {
			//@Override
			public void onClick(View v) {
				// 停止動畫
				danceAnimation.stop();
			}
		});

	}
}