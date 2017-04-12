package com.amaker.ch13.url;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import com.amaker.ch13.R;
/**
 * @author ������
 * �z�LURL�i������s�u
 */
public class TestURLActivity extends Activity {
	private ImageView imageView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_url);
        imageView = (ImageView)findViewById(R.id.ImageView01);
        
        //String urlStr = "http://192.168.1.101:8080/Chapter_13_Networking_server/upload/zs.jpg";
        String urlStr = "http://10.9.26.31:8080/Chapter_13_Networking_server/upload/zs.jpg";
        
        try {
			URL url = new URL(urlStr);
			// 1. �����ϥ�URL���o��J��y
			//InputStream in = url.openStream();
			
			// 2. ���oURLconnection
			URLConnection conn = url.openConnection();
			InputStream in = conn.getInputStream();
			
			// 3. �p�G�OHTTP��w�i�H�ϥ�HttpURLConnection
			//HttpURLConnection httpConn = (HttpsURLConnection)conn;
			//in = httpConn.getInputStream();
			
			Bitmap bm = BitmapFactory.decodeStream(in);
			
			imageView.setImageBitmap(bm);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}