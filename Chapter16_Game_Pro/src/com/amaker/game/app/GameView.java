package com.amaker.game.app;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.View;

/**
 * 
 * @author 郭宏志
 * 遊戲視圖類別
 */
public class GameView extends View implements Runnable{
	// 玩家Bitmap
	private Bitmap playerBitmap;
	// 子彈Bitmap
	private Bitmap bulletBitmap;
	// 敵人Bitmap
	private Bitmap enemyBitmap;
	// 玩家
	private Player player;
	// 子彈陣列
	private Bullet[] bullets = new Bullet[3];
	// 敵人
	private Enemy enemy;
	// Context
	private Context context;
	// 螢幕寬、高
	public int screenWidth,screenHeight;
	// 爆炸圖片
	public Bitmap exploreBitmap;
	// 判斷是否爆炸
	private boolean isExplore;
	
	// 建構子
	public GameView(Context context,int screenWidth,int screenHeight) {
		super(context);
		this.context = context;
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		setFocusable(true);
	}
	// 繪製方法
	@Override
	public void draw(Canvas canvas) {
		// 畫玩家
		player.paint(canvas);
		// 畫子彈
		for (int i = 0; i <bullets.length; i++) {
			bullets[i].draw(canvas);
		}
		// 畫敵人
		enemy.draw(canvas);
		
		if(isExplore){
			canvas.drawBitmap(exploreBitmap, enemy.getY(), enemy.getX(), null);
			isExplore = false;
			enemy.setAlive(false);
		}
		
		super.draw(canvas);
	}
	
	// 鍵盤事件
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_DPAD_UP:
			player.move(Player.UP);
			break;
		case KeyEvent.KEYCODE_DPAD_DOWN:
			player.move(Player.DOWN);
			break;
		case KeyEvent.KEYCODE_DPAD_LEFT:
			player.move(Player.LEFT);
			break;
		case KeyEvent.KEYCODE_DPAD_RIGHT:
			player.move(Player.RIGHT);
			break;
		case KeyEvent.KEYCODE_DPAD_CENTER:
			for (int i = 0; i < bullets.length; i++) {
				if(bullets[i].getAlive()==false){
					bullets[i].setAlive(true, 
							player.getRefPixelX()-bullets[i].getWidth()/2,
							player.getRefPixelY()-player.getHeight());
					break;
				}
			}
			break;
		}
		return super.onKeyDown(keyCode, event);
	}

	// 執行緒方法實作
	public void run() {
		load();
		init();
		while(true){
			if(!enemy.isAlive()){
				enemy.setAlive();
			}
			tick();
			postInvalidate();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
		}
	}
	
	// 載入資源
	public void load(){
		// 取得玩家圖片
		playerBitmap = GameHelper.getBitmap(context,R.drawable.player);
		// 實例化玩家
		player = new Player(playerBitmap,playerBitmap.getWidth(),playerBitmap.getHeight());
		// 設定螢幕寬和高
		player.setScreenSize(screenWidth, screenHeight);
		// 取得子彈圖片
		bulletBitmap = GameHelper.getBitmap(context,R.drawable.bullet);
		
		exploreBitmap = GameHelper.getBitmap(context,R.drawable.explored);
		for(int i=0;i<bullets.length;i++){
			// 實例化子彈
			bullets[i] = new Bullet(bulletBitmap,bulletBitmap.getWidth(),bulletBitmap.getHeight());
			// 設定螢幕寬和高
			bullets[i].setScreenSize(screenWidth, screenHeight);
		}
		// 取得敵人圖片
		enemyBitmap = GameHelper.getBitmap(context, R.drawable.e6);
		// 實例化敵人
		enemy = new Enemy(enemyBitmap,enemyBitmap.getWidth(),enemyBitmap.getHeight());
		// 設定螢幕寬和高
		enemy.setScreenSize(screenWidth, screenHeight);
	}
	
	// 初始化
	public void init(){
		// 初始化玩家
		player.init();
		for (int i = 0; i < bullets.length; i++) {
			bullets[i].setAlive(false);
		}
	}
	
	// 啟動執行緒
	public void start(){
		new Thread(this).start();
	}
	
	public void tick(){
		for (int i = 0; i < bullets.length; i++) {
			bullets[i].move();
			if(bullets[i].collidesWith(enemy, false)){
				isExplore = true;
			}
		}
		enemy.move();
		
	}
}
