package com.amaker.ch06.app;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 功能表項目
		String[] menus = { "查看電話訊息", "編輯電話訊息", "顯示撥打電話介面", "直接打電話", "使用瀏覽器", "使用地圖"};
		// 將功能表項目設定為ListView的列表選項
		setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, menus));
		getListView().setTextFilterEnabled(true);
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Intent intent = new Intent();
		Uri uri ;
		String data;
		switch (position) {
		// 查看_id 為1的用戶電話訊息
		case 0:
			data = "content://contacts/people";
			uri = Uri.parse(data);
			intent.setAction(Intent.ACTION_VIEW);
			intent.setData(uri);
			startActivity(intent);
			break;
		// 編輯_id 為1的用戶電話訊息
		case 1:
			data = "content://contacts/people/1";
			uri = Uri.parse(data);
			intent.setAction(Intent.ACTION_EDIT);
			intent.setData(uri);
			startActivity(intent);
			break;
		// 顯示撥打電話介面
		case 2:
			data = "tel:13800138000";
			uri = Uri.parse(data);
			intent.setAction(Intent.ACTION_DIAL);
			intent.setData(uri);
			startActivity(intent);
			break;
		// 直接打電話
		case 3:
			data = "tel:13800138000";
			uri = Uri.parse(data);
			intent.setAction(Intent.ACTION_CALL);
			intent.setData(uri);
			startActivity(intent);
			break;
		// 使用瀏覽器
		case 4:
			data = "http://www.google.com";
			uri = Uri.parse(data);
			intent.setAction(Intent.ACTION_VIEW);
			intent.setData(uri);
			startActivity(intent);
			break;
		// 使用地圖
		case 5:
			data = "geo:39.92,116.46";
			uri = Uri.parse(data);
			intent = new Intent(Intent.ACTION_VIEW, uri);
			startActivity(intent);
			break;
		default:
			break;
		}
	}
}