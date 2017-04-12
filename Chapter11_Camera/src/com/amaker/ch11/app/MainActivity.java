package com.amaker.ch11.app;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MainActivity extends Activity {
	// �ŧi��Ӥ�������SurfaceView
	private SurfaceView surfaceView;
	// �ŧi���������SurfaceHolder
	private SurfaceHolder surfaceHolder;
	// �ŧi�Ӭ۾�
	private Camera camera;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        // ��ҤƩ�Ӥ�������
        surfaceView = (SurfaceView)findViewById(R.id.preview);
        // �qSurfaceView�����oSurfaceHolder
        surfaceHolder = surfaceView.getHolder();
        // ��SurfaceHolder �s�W�^�I���
        surfaceHolder.addCallback(surfaceCallback);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }
    
    // �^������ƥ�
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	if(keyCode==KeyEvent.KEYCODE_CAMERA||keyCode==KeyEvent.KEYCODE_SEARCH){
    		takePic();
    		return true;
    	}
    	return super.onKeyDown(keyCode, event);
    }
    // ��Ӥ�k
    private void takePic(){
    	// ����w��
    	camera.stopPreview();
    	// ���
    	camera.takePicture(null,null, pictureCallback);
    }
    // �Ӥ��^�I
    Camera.PictureCallback pictureCallback = new Camera.PictureCallback() {
		//@Override
		public void onPictureTaken(byte[] data, Camera camera) {
			new SavePictureTask().execute(data);
			camera.startPreview();
		}
	};
	
	// �O�s�Ӥ��������O
	class SavePictureTask extends AsyncTask<byte[],String,String>{
		@Override
		protected String doInBackground(byte[]... params) {
			// �إ��ɮ�
			File picture = new File(Environment.getExternalStorageDirectory(),"picture.jpg");
			// �p�G�ɮצs�b�h�R��
			if(picture.exists())picture.delete();
			try {
				// ���o�ɮ׿�X��y
				FileOutputStream  fos = new FileOutputStream(picture.getPath());
				// �g�J���e
				fos.write(params[0]);
				// ��������y
				fos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
	}
    // SurfaceHodler �^�I�C�B�z���}�۾��B�����۾��H�ηӤ��ؤo������
    SurfaceHolder.Callback surfaceCallback = new SurfaceHolder.Callback() {
		//@Override
		public void surfaceCreated(SurfaceHolder holder) {
			// ���}�۾�
			camera = Camera.open();
			try {
				// �]�w�w��
				camera.setPreviewDisplay(holder);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//@Override
		public void surfaceChanged(SurfaceHolder holder, int format, int width,
				int height) {
			// ���o�۾��Ѽ�
			Camera.Parameters parameters = camera.getParameters();
			// �]�w�Ӥ��j�p
			parameters.setPreviewSize(width, height);
			// �]�w�Ӥ��榡
			parameters.setPictureFormat(PixelFormat.JPEG);
			// �]�w�۾��Ѽ�
			camera.setParameters(parameters);
			// �}�l�w��
			camera.startPreview();
		}
		
		//@Override
		public void surfaceDestroyed(SurfaceHolder holder) {
			// ����w��
			camera.stopPreview();
			// ����۾��귽
			camera.release();
			camera = null;
		}
	};
}