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
	// 宣告拍照介面元件SurfaceView
	private SurfaceView surfaceView;
	// 宣告介面控制元件SurfaceHolder
	private SurfaceHolder surfaceHolder;
	// 宣告照相機
	private Camera camera;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        // 實例化拍照介面元件
        surfaceView = (SurfaceView)findViewById(R.id.preview);
        // 從SurfaceView中取得SurfaceHolder
        surfaceHolder = surfaceView.getHolder();
        // 為SurfaceHolder 新增回呼函數
        surfaceHolder.addCallback(surfaceCallback);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }
    
    // 回應按鍵事件
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	if(keyCode==KeyEvent.KEYCODE_CAMERA||keyCode==KeyEvent.KEYCODE_SEARCH){
    		takePic();
    		return true;
    	}
    	return super.onKeyDown(keyCode, event);
    }
    // 拍照方法
    private void takePic(){
    	// 停止預覽
    	camera.stopPreview();
    	// 拍照
    	camera.takePicture(null,null, pictureCallback);
    }
    // 照片回呼
    Camera.PictureCallback pictureCallback = new Camera.PictureCallback() {
		//@Override
		public void onPictureTaken(byte[] data, Camera camera) {
			new SavePictureTask().execute(data);
			camera.startPreview();
		}
	};
	
	// 保存照片任務類別
	class SavePictureTask extends AsyncTask<byte[],String,String>{
		@Override
		protected String doInBackground(byte[]... params) {
			// 建立檔案
			File picture = new File(Environment.getExternalStorageDirectory(),"picture.jpg");
			// 如果檔案存在則刪除
			if(picture.exists())picture.delete();
			try {
				// 取得檔案輸出串流
				FileOutputStream  fos = new FileOutputStream(picture.getPath());
				// 寫入內容
				fos.write(params[0]);
				// 關閉文件串流
				fos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
	}
    // SurfaceHodler 回呼。處理打開相機、關閉相機以及照片尺寸的改變
    SurfaceHolder.Callback surfaceCallback = new SurfaceHolder.Callback() {
		//@Override
		public void surfaceCreated(SurfaceHolder holder) {
			// 打開相機
			camera = Camera.open();
			try {
				// 設定預覽
				camera.setPreviewDisplay(holder);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//@Override
		public void surfaceChanged(SurfaceHolder holder, int format, int width,
				int height) {
			// 取得相機參數
			Camera.Parameters parameters = camera.getParameters();
			// 設定照片大小
			parameters.setPreviewSize(width, height);
			// 設定照片格式
			parameters.setPictureFormat(PixelFormat.JPEG);
			// 設定相機參數
			camera.setParameters(parameters);
			// 開始預覽
			camera.startPreview();
		}
		
		//@Override
		public void surfaceDestroyed(SurfaceHolder holder) {
			// 停止預覽
			camera.stopPreview();
			// 釋放相機資源
			camera.release();
			camera = null;
		}
	};
}