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
		// �ϧΥثe�y��
		private int x = 20, y = 20;
		// �غc�l
		public MyView(Context context, AttributeSet attrs) {
			super(context, attrs);
			// ���o�J�I
			setFocusable(true);
			// �Ұʰ����
			new Thread(this).start();
		}
		//@Override
		public void run() {
			RefreshHandler mRedrawHandler = new RefreshHandler();
			while (!Thread.currentThread().isInterrupted()) {
				// �z�L�o�e������s����
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
			// ��ҤƵe��
			Paint p = new Paint();
			// �]�m�e���C��
			p.setColor(Color.GREEN);
			// �e��
			canvas.drawCircle(x, y, 10, p);
		}
		
		// ��s�����B�z��
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
		// ��s�y��
		private void update() {
			int h = getHeight();
			y += 5;
			if (y >= h)
				y = 20;
		}
	}

}