package com.amaker.ch14.app02;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	// 宣告TextView
	private TextView tv;
	// 宣告Button
	private Button b1,b2;
    // 定位服務管理器實例
    private LocationManager locationManager;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	// 設定目前Activity的介面佈局
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
      	// 定位服務常數
	    String locService = Context.LOCATION_SERVICE;
	    // 透過getSystemService方法取得LocationManager實例
	    locationManager = (LocationManager)getSystemService(locService);
	    // 透過findViewById方法取得TextView實例
	    tv = (TextView) findViewById(R.id.TextView01);
	    
	    // 透過findViewById方法取得Button實例
	    b1 = (Button) findViewById(R.id.Button01);
	    // 透過findViewById方法取得Button實例
	    b2 = (Button) findViewById(R.id.Button02);
	    // 為按鈕添加單擊事件監聽器
	    b1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// 正向編碼
				forward();
			}
		});
	    
	    // 為按鈕添加單擊事件監聽器
	    b2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// 反向編碼
				reverse();
			}
		});
    }
    
    // 正向編碼
    private void forward() {
    	// 實例化Geocoder
    	Geocoder gc = new Geocoder(this, Locale.getDefault());
    	// 地址
    	String address = "台北101";
    	// 位置列表
    	List<Address> locations = null;
    	try {
    		// 取得位置類別
    		locations = gc.getFromLocationName(address, 10);
    		// 如果Locations不為空
    		if(locations.size()>0){
    			// 取得Address實例
    			Address a = locations.get(0);
    			// 取得經緯度
    			double lat = a.getLatitude();
    			double lng = a.getLongitude();
    			// 顯示訊息
    			tv.setText(lat+":"+lng);
    		}
    	} catch (IOException e) {}
    }
    
    
    // 反向編碼
    private void reverse() {
    	// 經度
    	//double lng = 116.46;
    	double lng = 121.564099;	//121.564427;
    	// 緯度
    	//double lat = 39.92;
    	double lat = 25.033408;	//25.033671;
    	// 實例化Geocoder
    	Geocoder gc = new Geocoder(this, Locale.getDefault());
    	// 宣告地址列表
    	List<Address> addresses = null;
    	try {
    		// 取得Address實例列表
    		addresses = gc.getFromLocation(lat, lng, 10);
    		// 宣告StringBuilder，保存位置訊息
    		StringBuilder sb = new StringBuilder();
    		// 判斷addresses是否為空
    		if(addresses.size()>0){
    			// 取得Address
    			Address a = addresses.get(0);
    			for (int i = 0; i < a.getMaxAddressLineIndex(); i++){
    				// 地址
    				sb.append(a.getAddressLine(i)).append("\n");
    				// 地點
    				sb.append(a.getLocality()).append("\n");
    				// 郵遞區號
    				sb.append(a.getPostalCode()).append("\n");
    				// 國家
    				sb.append(a.getCountryName());
    			}
    			tv.setText(sb.toString());
    		}
    	} catch (IOException e) {}
    }
}