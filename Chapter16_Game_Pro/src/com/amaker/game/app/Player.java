package com.amaker.game.app;

import android.graphics.Bitmap;
import com.amaker.game.Sprite;

/**
 * 
 * @author ������
 * ���a��@���O
 */
public class Player extends Sprite{
	// �w�q�ù��e�M��
	private int screenWidth,screenHeight;
	// �w�q���a�e�M��
	private int frameWidth,frameHeight;
	// �w�q���a�O�_�٬���
	private boolean isAlive = false;
	// �w�q���ʤ�V�`�ơA�W�B�U�B���B�k
	public static final int UP = 1;
	public static final int DOWN = 2;
	public static final int LEFT = 3;
	public static final int RIGHT = 4;
	// �w�q���ʳt��
	public static int SPEED = 4;
	
	// �غc�l
	public Player(Bitmap Bitmap,int frameWidth,int frameHeight) {
		super(Bitmap);
		this.frameWidth = frameWidth;
		this.frameHeight = frameHeight;
		defineReferencePixel(frameWidth/2, frameHeight/2);
	}
	// �]�w�ù��j�p
	public void setScreenSize(int screenWidth,int screenHeight){
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
	}
	// �Ұʪ��a
	public void setAlive(boolean isAlive){
		this.isAlive = isAlive;
		setPosition(screenWidth/2, screenHeight/2);
	}
	// ���o���a�s�����A
	public boolean getAlive(){
		return this.isAlive;
	}
	// ���ʪ��a
	public void move(int direction){
		// �V�W����
		if(direction==UP){
			move(0, -SPEED);
			if(getY()<0){
				setPosition(getX(), 0);
			}
		}
		// �V�U����
		if(direction==DOWN){
			move(0, SPEED);
			if(getY()>screenHeight-frameHeight){
				setPosition(getX(), screenHeight-frameHeight);
			}
		}
		
		// �V������
		if(direction==LEFT){
			move(-SPEED, 0);
			if(getX()<0){
				setPosition(0, getY());
			}
		}
		// �V�k����
		if(direction==RIGHT){
			move(SPEED, 0);
			if(getX()>screenWidth-frameWidth){
				setPosition(screenWidth-frameWidth,getY());
			}
		}
	}
	// ��l�ƪ��a
	public void init(){
		SPEED = 4;
		setAlive(true);
	}
}
