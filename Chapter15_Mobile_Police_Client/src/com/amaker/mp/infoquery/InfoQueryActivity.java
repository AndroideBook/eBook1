package com.amaker.mp.infoquery;


import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class InfoQueryActivity extends ListActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String[] menus = { "発琩高", "砆祍ó进琩高", "戈癟琩高", 
				"跋戈癟琩高", "挂戈琩高", "ン琩高" ,"そゅ琩高"};
		setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, menus));
		getListView().setTextFilterEnabled(true);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Intent intent = null;
		switch (position) {
		case 0:
			intent = new Intent(InfoQueryActivity.this,EscapedQueryActivity.class);
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