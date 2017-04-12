package com.amaker.ch13;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.amaker.ch13.socket.TestSocketActivity;
import com.amaker.ch13.url.TestURLActivity;
import com.amaker.ch13.webview.TestWebViewActivity;
import com.amaker.ch13.http.LoginActivity;
import com.amaker.ch13.webservice.TestWebServiceActivity;
import com.amaker.ch13.webservice.WeatherActivity;

public class MainActivity extends ListActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    	// 功能表陣列
        String[] items = {"測試 Socket", "測試URL、UrlConnection", "測試 HTTP", 
        		"天氣預報", "Test WebView"};	//"測試 Web Service", 
		// 將功能表陣列設定為ListView的列表項目
		setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, items));
		getListView().setTextFilterEnabled(true);
    }
    
    // 回應功能表項目的單擊事件
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Intent intent = null;
		switch (position) {
		case 0:
			intent = new Intent(MainActivity.this,TestSocketActivity.class);
			startActivity(intent);
			break;
		case 1:
			intent = new Intent(MainActivity.this,TestURLActivity.class);
			startActivity(intent);
			break;
		case 2:
			intent = new Intent(MainActivity.this,LoginActivity.class);
			startActivity(intent);
			break;
		/*
		case 3:
			intent = new Intent(MainActivity.this,TestWebServiceActivity.class);
			startActivity(intent);
			break;
		*/
		case 3:
			intent = new Intent(MainActivity.this,WeatherActivity.class);
			startActivity(intent);
			break;
		case 4:
			intent = new Intent(MainActivity.this,TestWebViewActivity.class);
			startActivity(intent);
			break;
		}
	}
}