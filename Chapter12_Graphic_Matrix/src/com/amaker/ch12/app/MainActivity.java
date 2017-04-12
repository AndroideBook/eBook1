package com.amaker.ch12.app;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

public class MainActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MyView myView = new MyView(MainActivity.this);
		// �]�w�ثe���ϧG��
		setContentView(myView);
	}
	// �ۭq�������O
	class MyView extends View {
		// �I�}�Ϲ��
		private Bitmap bm;
		// Matrix ���
		private Matrix matrix = new Matrix();
		// ���ਤ��
		private float angle = 0.0f;
		// �e�M��
		private int w, h;
		// �Y����
		private float scale = 1.0f;
		// �P�_�Y���٬O����
		private boolean isScale = false;
		
		// �غc�l
		public MyView(Context context) {
			super(context);
			// ���o�ϧ�
			bm = BitmapFactory.decodeResource(this.getResources(),
					R.drawable.girl);
			// ���o�e��
			w = bm.getWidth();
			// ���o����
			h = bm.getHeight();
			// ���ثe���Ϩ��o�J�I
			this.setFocusable(true);
		}

		@Override
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);
			// ���mMatrix
			matrix.reset();
			if (!isScale) {
				// ����Matrix
				matrix.setRotate(angle);
			} else {
				// �Y��Matrix
				matrix.setScale(scale, scale);
			}
			// �ھڭ�l�ϧΩMMatrix�إ߷s����
			Bitmap bm2 = Bitmap.createBitmap(bm, 0, 0, w, h, matrix, true);
			// ø�s�s����
			canvas.drawBitmap(bm2, matrix, null);
		}

		@Override
		public boolean onKeyDown(int keyCode, KeyEvent event) {
			// �V������
			if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
				isScale = false;
				angle++;
				postInvalidate();
			}
			// �V�k����
			if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
				isScale = false;
				angle--;
				postInvalidate();
			}
			// ��j
			if (keyCode == KeyEvent.KEYCODE_DPAD_UP) {
				isScale = true;
				if (scale < 2.0)
					scale += 0.1;
				postInvalidate();
			}
			// �Y�p
			if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN) {
				isScale = true;
				if (scale > 0.5)
					scale -= 0.1;
				postInvalidate();
			}

			return super.onKeyDown(keyCode, event);
		}
	}
}