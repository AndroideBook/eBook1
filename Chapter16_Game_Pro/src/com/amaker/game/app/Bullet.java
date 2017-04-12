package com.amaker.game.app;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import com.amaker.game.Sprite;
/**
 * @author ������
 * �l�u���O
 */
public class Bullet extends Sprite{
	// ���ؼe�M��
	private int frameWidth, frameHeight;
	// �ù��e�M��
	private int screenWidth, screenHeight;
	// �O�_�s��
	private boolean isAlive;
	public Bullet(Bitmap Bitmap, int frameWidth, int frameHeight) {
		super(Bitmap, frameWidth, frameHeight);
		this.frameHeight = frameHeight;
		this.frameWidth = frameWidth;
		defineReferencePixel(frameWidth/2, frameHeight/2);
	}
	// �]�w�ù��j�p
	public void setScreenSize(int screenWidth,int screenHeight){
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
	}
	// �Ұʪ��a
	public void setAlive(boolean isAlive,int x,int y){
		this.isAlive = isAlive;
		setPosition(x,y);
	}
	
	// �Ұʪ��a
	public void setAlive(boolean alive){
		this.isAlive = alive;
	}
	// ���o���a�s�����A
	public boolean getAlive(){
		return this.isAlive;
	}
	// ���ʤl�u
	public void move(){
		if(isAlive){
			move(0,-3);
		}
		if(getY()<0){
			isAlive = false;
		}
	}
	// ø�s�l�u
	public void draw(Canvas c){
		if(isAlive)
		paint(c);
	}
	
}
