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
	// �ŧi�ϥΨ쪺Button���Ϥ���
	private Button b1, b2, b3, b4;
	// �ŧi�ϥΨ쪺ImageView����
	private ImageView girlImage;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// �]�w�ثe���ϧG��
		setContentView(R.layout.main);
		// ��ҤƵ��Ϥ���
		girlImage = (ImageView) findViewById(R.id.ImageView01);
		b1 = (Button) findViewById(R.id.Button01);
		b2 = (Button) findViewById(R.id.Button02);
		b3 = (Button) findViewById(R.id.Button03);
		b4 = (Button) findViewById(R.id.Button04);
		
		// �����s�K�[��ť�ƥ�
		b1.setOnClickListener(new OnClickListener() {
			//@Override
			public void onClick(View v) {
				// �إ�Sacle(�ؤo)�ܤưʵe
				Animation scaleAnimation = 
					AnimationUtils.loadAnimation(MainActivity.this,R.anim.my_scale);
				// �}�l�ʵe
				girlImage.startAnimation(scaleAnimation);
			}
		});
		// �����s�K�[��ť�ƥ�
		b2.setOnClickListener(new OnClickListener() {
			//@Override
			public void onClick(View v) {
				// �إ�Alpha(����)�ʵe
				Animation alphaAnimation = 
					AnimationUtils.loadAnimation(MainActivity.this,R.anim.my_alpha);
				// �}�l�ʵe
				girlImage.startAnimation(alphaAnimation);
			}
		});
		// �����s�K�[��ť�ƥ�
		b3.setOnClickListener(new OnClickListener() {
			//@Override
			public void onClick(View v) {
				// �إ�translate(��m�ܤ�)�ʵe
				Animation translateAnimation =  
					AnimationUtils.loadAnimation(MainActivity.this,R.anim.my_translate);
				// �}�l�ʵe
				girlImage.startAnimation(translateAnimation);
			}
		});
		// �����s�K�[��ť�ʵe
		b4.setOnClickListener(new OnClickListener() {
			//@Override
			public void onClick(View v) {
				// �إ�rotate(����)�ʵe
				Animation rotateAnimation =
					AnimationUtils.loadAnimation(MainActivity.this,R.anim.my_rotate);
				// �]�w�ʵe����ɶ�
				rotateAnimation.setDuration(3000);
				// �}�l�ʵe
				girlImage.startAnimation(rotateAnimation);
			}
		});
		

	}
}