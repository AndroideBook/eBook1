package com.amaker.ch12.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
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
				Animation scaleAnimation = new ScaleAnimation(0f, 1f, 0f, 1f,
						Animation.RELATIVE_TO_SELF, 0.5f,
						Animation.RELATIVE_TO_SELF, 0.5f);
				// �]�w�ʵe����ɶ�
				scaleAnimation.setDuration(3000);
				// �}�l�ʵe
				girlImage.startAnimation(scaleAnimation);
			}
		});
		// �����s�K�[��ť�ƥ�
		b2.setOnClickListener(new OnClickListener() {
			//@Override
			public void onClick(View v) {
				// �إ�Alpha(����)�ʵe
				Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
				// �]�w�ʵe����ɶ�
				alphaAnimation.setDuration(3000);
				// �}�l�ʵe
				girlImage.startAnimation(alphaAnimation);
			}
		});
		// �����s�K�[��ť�ƥ�
		b3.setOnClickListener(new OnClickListener() {
			//@Override
			public void onClick(View v) {
				// �إ�translate(��m�ܤ�)�ʤ�
				Animation translateAnimation = new TranslateAnimation(10, 100,
						10, 100);
				// �]�w�ʵe����ɶ�
				translateAnimation.setDuration(3000);
				// �}�l�ʵe
				girlImage.startAnimation(translateAnimation);
			}
		});
		// �����s�K�[��ť�ƥ�
		b4.setOnClickListener(new OnClickListener() {
			//@Override
			public void onClick(View v) {
				// �إ�rotate(����)�ʵe
				Animation rotateAnimation = new RotateAnimation(0f, +360f,
						Animation.RELATIVE_TO_SELF, 0.5f,
						Animation.RELATIVE_TO_SELF, 0.5f);
				// �]�w�ʵe����ɶ�
				rotateAnimation.setDuration(3000);
				// �}�l�ʵe
				girlImage.startAnimation(rotateAnimation);
			}
		});
		

	}
}