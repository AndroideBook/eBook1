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
		// 設定目前視圖佈局
		setContentView(myView);
	}
	// 自訂視圖類別
	class MyView extends View {
		// 點陣圖實例
		private Bitmap bm;
		// Matrix 實例
		private Matrix matrix = new Matrix();
		// 旋轉角度
		private float angle = 0.0f;
		// 寬和高
		private int w, h;
		// 縮放比例
		private float scale = 1.0f;
		// 判斷縮放還是旋轉
		private boolean isScale = false;
		
		// 建構子
		public MyView(Context context) {
			super(context);
			// 取得圖形
			bm = BitmapFactory.decodeResource(this.getResources(),
					R.drawable.girl);
			// 取得寬度
			w = bm.getWidth();
			// 取得高度
			h = bm.getHeight();
			// 讓目前視圖取得焦點
			this.setFocusable(true);
		}

		@Override
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);
			// 重置Matrix
			matrix.reset();
			if (!isScale) {
				// 旋轉Matrix
				matrix.setRotate(angle);
			} else {
				// 縮放Matrix
				matrix.setScale(scale, scale);
			}
			// 根據原始圖形和Matrix建立新視圖
			Bitmap bm2 = Bitmap.createBitmap(bm, 0, 0, w, h, matrix, true);
			// 繪製新視圖
			canvas.drawBitmap(bm2, matrix, null);
		}

		@Override
		public boolean onKeyDown(int keyCode, KeyEvent event) {
			// 向左旋轉
			if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
				isScale = false;
				angle++;
				postInvalidate();
			}
			// 向右旋轉
			if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
				isScale = false;
				angle--;
				postInvalidate();
			}
			// 放大
			if (keyCode == KeyEvent.KEYCODE_DPAD_UP) {
				isScale = true;
				if (scale < 2.0)
					scale += 0.1;
				postInvalidate();
			}
			// 縮小
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