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
	// 自訂視圖類別
	class MyView extends View{
		// 宣告Bitmap物件
		private Bitmap bm ;
		// 宣告位元圖渲染物件
		private Shader bitmapShader;
		// 宣告線性渲染物件
		private Shader linearGradient;
		// 宣告光速渲染物件
		private Shader radialGradient;
		// 宣告梯度渲染物件
		private Shader sweepGradient;
		// 宣告混合渲染物件
		private Shader composeShader;
		// 宣告畫筆
		private Paint paint;
		// 宣告顏色陣列
		private int[] colors;
		private boolean isFirst = true;
		
		public MyView(Context context) {
			super(context);
			// 取得Bitmap實例
			bm = BitmapFactory.decodeResource(getResources(), R.drawable.girl);
			// 實例化畫筆
			paint = new Paint();
			colors = new int[]{Color.RED,Color.GREEN,Color.BLUE};
			// 實例化圖形渲染物件，x座標方向重複圖形，y座標方向鏡像圖形
			bitmapShader = new BitmapShader(bm, TileMode.REPEAT, TileMode.MIRROR);
			// 實例化線性渲染
			linearGradient = new LinearGradient(0, 0, 100, 100,
					colors, null,  TileMode.REPEAT);
			// 實例化光束渲染
			radialGradient = new RadialGradient(100, 100, 80, 
					colors, null,  TileMode.REPEAT);
			// 實例化梯度渲染
			sweepGradient =  new SweepGradient(200, 200, colors, null);
			
			// 實例化混合渲染
			composeShader = new ComposeShader(linearGradient, radialGradient,PorterDuff.Mode.DARKEN);
			// 取得焦點
			setFocusable(true);
		}
		@Override
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);
			if(isFirst){
				//寫字，用來提示用戶操作
				String content = "按上/下/左/右/中間鍵測試！";
				paint.setColor(Color.BLUE);
				canvas.drawText(content, 0, content.length()-1, 20, 20, paint);
			}else{
				// 全屏畫矩形
				canvas.drawRect(0, 0, getWidth(), getHeight(), paint);
			}
		}
		
		@Override
		public boolean onKeyDown(int keyCode, KeyEvent event) {
			isFirst = false;
			if(keyCode==KeyEvent.KEYCODE_DPAD_UP){
				// 將畫筆渲染設定為點陣圖渲染
				paint.setShader(bitmapShader);
			}
			if(keyCode==KeyEvent.KEYCODE_DPAD_DOWN){
				// 將畫筆渲染設定為線性渲染
				paint.setShader(linearGradient);
			}
			if(keyCode==KeyEvent.KEYCODE_DPAD_LEFT){
				// 將畫筆渲染設定為光束渲染
				paint.setShader(radialGradient);
			}
			if(keyCode==KeyEvent.KEYCODE_DPAD_RIGHT){
				// 將畫筆渲染設定為梯度渲染
				paint.setShader(sweepGradient);
			}
			if(keyCode==KeyEvent.KEYCODE_DPAD_CENTER){
				// 將畫筆渲染設定為混合渲染
				paint.setShader(composeShader);
			}
			// 重繪畫面
			postInvalidate();
			return super.onKeyDown(keyCode, event);
		}
	}
}