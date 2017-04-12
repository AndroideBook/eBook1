package com.amaker.test.app;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler; 
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

public class MainActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MyView v = new MyView(this, null);
		setContentView(v);
	}
	//
	class MyView extends View implements Runnable {
		// 圖形目前座標
		private int x = 20, y = 20;
		// 建構子
		public MyView(Context context, AttributeSet attrs) {
			super(context, attrs);
			// 取得焦點
			setFocusable(true);
			// 啟動執行緒
			new Thread(this).start();
		}
		//@Override
		public void run() {
			RefreshHandler mRedrawHandler = new RefreshHandler();
			while (!Thread.currentThread().isInterrupted()) {
				// 透過發送消息更新介面
				Message m = new Message();
				m.what = 0x101;
				mRedrawHandler.sendMessage(m);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		@Override
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);
			// 實例化畫筆
			Paint p = new Paint();
			// 設置畫筆顏色
			p.setColor(Color.GREEN);
			// 畫圓
			canvas.drawCircle(x, y, 10, p);
		}
		
		// 更新介面處理器
		class RefreshHandler extends Handler {
			@Override
			public void handleMessage(Message msg) {
				if (msg.what == 0x101) {
					MyView.this.update();
					MyView.this.invalidate();
				}
				super.handleMessage(msg);
			}
		};
		// 更新座標
		private void update() {
			int h = getHeight();
			y += 5;
			if (y >= h)
				y = 20;
		}
	}

}