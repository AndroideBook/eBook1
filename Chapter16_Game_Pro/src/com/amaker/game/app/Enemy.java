package com.amaker.game.app;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import com.amaker.game.Sprite;
/**
 * @author ������
 * �ĤH�ϼh���O
 */
public class Enemy extends Sprite {
	// �w�q�ĤH�e�M��
	private int frameWidth, frameHeight;
	// �w�q�ù��e�M��
	private int screenWidth,screenHeight;
	// �P�_�ĤH�O�_�s��
	private boolean isAlive;
	// �ü�
	Random r;
	public Enemy(Bitmap Bitmap, int frameWidth, int frameHeight) {
		super(Bitmap, frameWidth, frameHeight);
		this.frameWidth = frameWidth;
		this.frameHeight = frameHeight;
		defineReferencePixel(frameWidth / 2, frameHeight / 2);
		r = new Random();
	}
	// �]�w�ù����e�M��
	public void setScreenSize(int screenWidth,int screenHeight){
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
	}
	
	// �]�w�ĤH�s��
	public void setAlive(){
		isAlive = true;
		int x = (r.nextInt() & 0x7fffffff)%(screenWidth-frameWidth);
		setPosition(x,0);
	}
	
	public void setAlive(boolean alive){
		this.isAlive = alive;
	}
	// ����
	public void move(){
		if(isAlive){
			move(0, 3);
			if(getY()>screenHeight){
				isAlive=false;
			}
		}
	}
	
	public boolean isAlive(){
		return isAlive;
	}
	// ø�s�ĤH
	public void draw(Canvas canvas){
		if(isAlive){
			paint(canvas);
		}
	}

}
