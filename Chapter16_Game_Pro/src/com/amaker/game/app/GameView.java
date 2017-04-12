package com.amaker.game.app;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.View;

/**
 * 
 * @author ������
 * �C���������O
 */
public class GameView extends View implements Runnable{
	// ���aBitmap
	private Bitmap playerBitmap;
	// �l�uBitmap
	private Bitmap bulletBitmap;
	// �ĤHBitmap
	private Bitmap enemyBitmap;
	// ���a
	private Player player;
	// �l�u�}�C
	private Bullet[] bullets = new Bullet[3];
	// �ĤH
	private Enemy enemy;
	// Context
	private Context context;
	// �ù��e�B��
	public int screenWidth,screenHeight;
	// �z���Ϥ�
	public Bitmap exploreBitmap;
	// �P�_�O�_�z��
	private boolean isExplore;
	
	// �غc�l
	public GameView(Context context,int screenWidth,int screenHeight) {
		super(context);
		this.context = context;
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		setFocusable(true);
	}
	// ø�s��k
	@Override
	public void draw(Canvas canvas) {
		// �e���a
		player.paint(canvas);
		// �e�l�u
		for (int i = 0; i <bullets.length; i++) {
			bullets[i].draw(canvas);
		}
		// �e�ĤH
		enemy.draw(canvas);
		
		if(isExplore){
			canvas.drawBitmap(exploreBitmap, enemy.getY(), enemy.getX(), null);
			isExplore = false;
			enemy.setAlive(false);
		}
		
		super.draw(canvas);
	}
	
	// ��L�ƥ�
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

	// �������k��@
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
	
	// ���J�귽
	public void load(){
		// ���o���a�Ϥ�
		playerBitmap = GameHelper.getBitmap(context,R.drawable.player);
		// ��Ҥƪ��a
		player = new Player(playerBitmap,playerBitmap.getWidth(),playerBitmap.getHeight());
		// �]�w�ù��e�M��
		player.setScreenSize(screenWidth, screenHeight);
		// ���o�l�u�Ϥ�
		bulletBitmap = GameHelper.getBitmap(context,R.drawable.bullet);
		
		exploreBitmap = GameHelper.getBitmap(context,R.drawable.explored);
		for(int i=0;i<bullets.length;i++){
			// ��ҤƤl�u
			bullets[i] = new Bullet(bulletBitmap,bulletBitmap.getWidth(),bulletBitmap.getHeight());
			// �]�w�ù��e�M��
			bullets[i].setScreenSize(screenWidth, screenHeight);
		}
		// ���o�ĤH�Ϥ�
		enemyBitmap = GameHelper.getBitmap(context, R.drawable.e6);
		// ��ҤƼĤH
		enemy = new Enemy(enemyBitmap,enemyBitmap.getWidth(),enemyBitmap.getHeight());
		// �]�w�ù��e�M��
		enemy.setScreenSize(screenWidth, screenHeight);
	}
	
	// ��l��
	public void init(){
		// ��l�ƪ��a
		player.init();
		for (int i = 0; i < bullets.length; i++) {
			bullets[i].setAlive(false);
		}
	}
	
	// �Ұʰ����
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
