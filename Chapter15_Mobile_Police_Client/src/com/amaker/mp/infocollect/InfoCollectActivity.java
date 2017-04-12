package com.amaker.mp.infocollect;


import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class InfoCollectActivity extends ListActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String[] menus = { "機動車違規資訊收集", "治安資訊上報", "社區警務資訊收集", 
				"交通違規資訊收集" , "現場取證筆錄", "現場違規事件處理"};
		setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, menus));
		getListView().setTextFilterEnabled(true);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Intent intent = null;
		switch (position) {
		case 0:
			intent = new Intent(InfoCollectActivity.this,VehicleInfoCollActivity.class);
			startActivity(intent);
			break;
		case 2:

			break;
		case 3:

			break;
		case 4:

			break;
		}
	}
}