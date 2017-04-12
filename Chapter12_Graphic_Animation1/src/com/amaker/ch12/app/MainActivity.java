package com.amaker.ch12.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {
	// 宣告使用到的Button視圖元件
	private Button b1, b2, b3, b4;
	// 宣告使用到的ImageView元件
	private ImageView girlImage;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 設定目前視圖佈局
		setContentView(R.layout.main);
		// 實例化視圖元件
		girlImage = (ImageView) findViewById(R.id.ImageView01);
		b1 = (Button) findViewById(R.id.Button01);
		b2 = (Button) findViewById(R.id.Button02);
		b3 = (Button) findViewById(R.id.Button03);
		b4 = (Button) findViewById(R.id.Button04);
		
		// 為按鈕添加監聽事件
		b1.setOnClickListener(new OnClickListener() {
			//@Override
			public void onClick(View v) {
				// 建立Sacle(尺寸)變化動畫
				Animation scaleAnimation = 
					AnimationUtils.loadAnimation(MainActivity.this,R.anim.my_scale);
				// 開始動畫
				girlImage.startAnimation(scaleAnimation);
			}
		});
		// 為按鈕添加監聽事件
		b2.setOnClickListener(new OnClickListener() {
			//@Override
			public void onClick(View v) {
				// 建立Alpha(漸變)動畫
				Animation alphaAnimation = 
					AnimationUtils.loadAnimation(MainActivity.this,R.anim.my_alpha);
				// 開始動畫
				girlImage.startAnimation(alphaAnimation);
			}
		});
		// 為按鈕添加監聽事件
		b3.setOnClickListener(new OnClickListener() {
			//@Override
			public void onClick(View v) {
				// 建立translate(位置變化)動畫
				Animation translateAnimation =  
					AnimationUtils.loadAnimation(MainActivity.this,R.anim.my_translate);
				// 開始動畫
				girlImage.startAnimation(translateAnimation);
			}
		});
		// 為按鈕添加監聽動畫
		b4.setOnClickListener(new OnClickListener() {
			//@Override
			public void onClick(View v) {
				// 建立rotate(旋轉)動畫
				Animation rotateAnimation =
					AnimationUtils.loadAnimation(MainActivity.this,R.anim.my_rotate);
				// 設定動畫持續時間
				rotateAnimation.setDuration(3000);
				// 開始動畫
				girlImage.startAnimation(rotateAnimation);
			}
		});
		

	}
}