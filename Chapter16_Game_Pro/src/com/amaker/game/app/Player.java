package com.amaker.game.app;

import android.graphics.Bitmap;
import com.amaker.game.Sprite;

/**
 * 
 * @author 郭宏志
 * 玩家實作類別
 */
public class Player extends Sprite{
	// 定義螢幕寬和高
	private int screenWidth,screenHeight;
	// 定義玩家寬和高
	private int frameWidth,frameHeight;
	// 定義完家是否還活著
	private boolean isAlive = false;
	// 定義移動方向常數，上、下、左、右
	public static final int UP = 1;
	public static final int DOWN = 2;
	public static final int LEFT = 3;
	public static final int RIGHT = 4;
	// 定義移動速度
	public static int SPEED = 4;
	
	// 建構子
	public Player(Bitmap Bitmap,int frameWidth,int frameHeight) {
		super(Bitmap);
		this.frameWidth = frameWidth;
		this.frameHeight = frameHeight;
		defineReferencePixel(frameWidth/2, frameHeight/2);
	}
	// 設定螢幕大小
	public void setScreenSize(int screenWidth,int screenHeight){
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
	}
	// 啟動玩家
	public void setAlive(boolean isAlive){
		this.isAlive = isAlive;
		setPosition(screenWidth/2, screenHeight/2);
	}
	// 取得玩家存活狀態
	public boolean getAlive(){
		return this.isAlive;
	}
	// 移動玩家
	public void move(int direction){
		// 向上移動
		if(direction==UP){
			move(0, -SPEED);
			if(getY()<0){
				setPosition(getX(), 0);
			}
		}
		// 向下移動
		if(direction==DOWN){
			move(0, SPEED);
			if(getY()>screenHeight-frameHeight){
				setPosition(getX(), screenHeight-frameHeight);
			}
		}
		
		// 向左移動
		if(direction==LEFT){
			move(-SPEED, 0);
			if(getX()<0){
				setPosition(0, getY());
			}
		}
		// 向右移動
		if(direction==RIGHT){
			move(SPEED, 0);
			if(getX()>screenWidth-frameWidth){
				setPosition(screenWidth-frameWidth,getY());
			}
		}
	}
	// 初始化玩家
	public void init(){
		SPEED = 4;
		setAlive(true);
	}
}
