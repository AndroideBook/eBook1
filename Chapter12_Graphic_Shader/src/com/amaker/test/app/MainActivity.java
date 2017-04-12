package com.amaker.test.app;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.graphics.Shader.TileMode;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

public class MainActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MyView v = new MyView(this);
		setContentView(v);
	}
	// �ۭq�������O
	class MyView extends View{
		// �ŧiBitmap����
		private Bitmap bm ;
		// �ŧi�줸�ϴ�V����
		private Shader bitmapShader;
		// �ŧi�u�ʴ�V����
		private Shader linearGradient;
		// �ŧi���t��V����
		private Shader radialGradient;
		// �ŧi��״�V����
		private Shader sweepGradient;
		// �ŧi�V�X��V����
		private Shader composeShader;
		// �ŧi�e��
		private Paint paint;
		// �ŧi�C��}�C
		private int[] colors;
		private boolean isFirst = true;
		
		public MyView(Context context) {
			super(context);
			// ���oBitmap���
			bm = BitmapFactory.decodeResource(getResources(), R.drawable.girl);
			// ��ҤƵe��
			paint = new Paint();
			colors = new int[]{Color.RED,Color.GREEN,Color.BLUE};
			// ��Ҥƹϧδ�V����Ax�y�Ф�V���ƹϧΡAy�y�Ф�V�蹳�ϧ�
			bitmapShader = new BitmapShader(bm, TileMode.REPEAT, TileMode.MIRROR);
			// ��Ҥƽu�ʴ�V
			linearGradient = new LinearGradient(0, 0, 100, 100,
					colors, null,  TileMode.REPEAT);
			// ��Ҥƥ�����V
			radialGradient = new RadialGradient(100, 100, 80, 
					colors, null,  TileMode.REPEAT);
			// ��ҤƱ�״�V
			sweepGradient =  new SweepGradient(200, 200, colors, null);
			
			// ��ҤƲV�X��V
			composeShader = new ComposeShader(linearGradient, radialGradient,PorterDuff.Mode.DARKEN);
			// ���o�J�I
			setFocusable(true);
		}
		@Override
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);
			if(isFirst){
				//�g�r�A�ΨӴ��ܥΤ�ާ@
				String content = "���W/�U/��/�k/��������աI";
				paint.setColor(Color.BLUE);
				canvas.drawText(content, 0, content.length()-1, 20, 20, paint);
			}else{
				// ���̵e�x��
				canvas.drawRect(0, 0, getWidth(), getHeight(), paint);
			}
		}
		
		@Override
		public boolean onKeyDown(int keyCode, KeyEvent event) {
			isFirst = false;
			if(keyCode==KeyEvent.KEYCODE_DPAD_UP){
				// �N�e����V�]�w���I�}�ϴ�V
				paint.setShader(bitmapShader);
			}
			if(keyCode==KeyEvent.KEYCODE_DPAD_DOWN){
				// �N�e����V�]�w���u�ʴ�V
				paint.setShader(linearGradient);
			}
			if(keyCode==KeyEvent.KEYCODE_DPAD_LEFT){
				// �N�e����V�]�w��������V
				paint.setShader(radialGradient);
			}
			if(keyCode==KeyEvent.KEYCODE_DPAD_RIGHT){
				// �N�e����V�]�w����״�V
				paint.setShader(sweepGradient);
			}
			if(keyCode==KeyEvent.KEYCODE_DPAD_CENTER){
				// �N�e����V�]�w���V�X��V
				paint.setShader(composeShader);
			}
			// ��ø�e��
			postInvalidate();
			return super.onKeyDown(keyCode, event);
		}
	}
}