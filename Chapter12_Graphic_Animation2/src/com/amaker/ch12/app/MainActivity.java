package com.amaker.ch12.app;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {
	// �ŧi�ϥΨ쪺Button���Ϥ���
	private Button b1, b2;
	// �ŧi�ϥΨ쪺ImageView����
	private ImageView myImage;
	// �ŧiAnimationDrawable
	private AnimationDrawable danceAnimation;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// �]�w�ثe���ϧG��
		setContentView(R.layout.main);
		// ��ҤƵ��Ϥ���
		myImage = (ImageView) findViewById(R.id.ImageView01);
		b1 = (Button) findViewById(R.id.Button01);
		b2 = (Button) findViewById(R.id.Button02);
		
		// ���o�I����A���ഫ��AnimationDrawable����
		danceAnimation = (AnimationDrawable) myImage.getBackground();
		
		// �����s�K�[��ť�ƥ�
		b1.setOnClickListener(new OnClickListener() {
			//@Override
			public void onClick(View v) {
				// �}�l�ʵe
				danceAnimation.start();
			}
		});
		// �����s�K�[��ť�ƥ�
		b2.setOnClickListener(new OnClickListener() {
			//@Override
			public void onClick(View v) {
				// ����ʵe
				danceAnimation.stop();
			}
		});

	}
}