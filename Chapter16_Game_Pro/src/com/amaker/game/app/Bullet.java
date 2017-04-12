package com.amaker.game.app;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import com.amaker.game.Sprite;
/**
 * @author 郭宏志
 * 子彈類別
 */
public class Bullet extends Sprite{
	// 頁框寬和高
	private int frameWidth, frameHeight;
	// 螢幕寬和高
	private int screenWidth, screenHeight;
	// 是否存活
	private boolean isAlive;
	public Bullet(Bitmap Bitmap, int frameWidth, int frameHeight) {
		super(Bitmap, frameWidth, frameHeight);
		this.frameHeight = frameHeight;
		this.frameWidth = frameWidth;
		defineReferencePixel(frameWidth/2, frameHeight/2);
	}
	// 設定螢幕大小
	public void setScreenSize(int screenWidth,int screenHeight){
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
	}
	// 啟動玩家
	public void setAlive(boolean isAlive,int x,int y){
		this.isAlive = isAlive;
		setPosition(x,y);
	}
	
	// 啟動玩家
	public void setAlive(boolean alive){
		this.isAlive = alive;
	}
	// 取得玩家存活狀態
	public boolean getAlive(){
		return this.isAlive;
	}
	// 移動子彈
	public void move(){
		if(isAlive){
			move(0,-3);
		}
		if(getY()<0){
			isAlive = false;
		}
	}
	// 繪製子彈
	public void draw(Canvas c){
		if(isAlive)
		paint(c);
	}
	
}
