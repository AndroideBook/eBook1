package com.amaker.ch17.app;

import android.app.ListActivity;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.AdapterView.OnItemClickListener;

import com.amaker.ch17.app.TaskList.Tasks;
/**
 * 
 * @author 郭宏志
 * 備忘錄列表類別，提供資料顯示
 */
public class TaskListActivity extends ListActivity {
	
	// 菜單項目常數
	private static final int NEW = 1;
	private static final int DEL = 2;
	
	// 查詢欄位陣列
	private static final String[] PROJECTION = new String[] { 
			Tasks._ID, 		// 0
			Tasks.CONTENT, 	// 1
			Tasks.CREATED, 	// 2
			Tasks.ALARM, 	// 3
			Tasks.DATE1, 	// 4
			Tasks.TIME1, 	// 5
			Tasks.ON_OFF 	// 6
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 取得Intent
		final Intent intent = getIntent();
		// 設定Uri
		if (intent.getData() == null) {
			intent.setData(Tasks.CONTENT_URI);
		}
		// 取得ListView
		ListView listView = getListView();
		
		// 查詢所有備忘錄資訊
		Cursor cursor = managedQuery(getIntent().getData(), PROJECTION, null,
				null, Tasks.DEFAULT_SORT_ORDER);
		// 建立Adapter
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
				android.R.layout.simple_list_item_2, cursor,
				new String[] {Tasks._ID, Tasks.CONTENT },
				new int[] { android.R.id.text1,android.R.id.text2 });
		// 將備忘錄資訊顯示到ListView
		setListAdapter(adapter);
		
		
		// 為ListView添加單擊事件監聽器
		listView.setOnItemClickListener(new OnItemClickListener() {
			//@Override
			public void onItemClick(AdapterView<?> av, View v, int position,
					long id) {
				// 透過ID查詢備忘錄資訊
				Uri uri = ContentUris.withAppendedId(Tasks.CONTENT_URI, id);
				Cursor cursor = managedQuery(uri, PROJECTION, null,
						null, Tasks.DEFAULT_SORT_ORDER);
				
				if(cursor.moveToNext()){
					int id1 = cursor.getInt(0);
					String content = cursor.getString(1);
					String created = cursor.getString(2);
					int alarm = cursor.getInt(3);
					String date1 = cursor.getString(4);
					String time1 = cursor.getString(5);
					int on_off = cursor.getInt(6);
					Bundle b = new Bundle();
					b.putInt("id", id1);
					b.putString("content", content);
					b.putString("created", created);
					
					b.putInt("alarm", alarm);
					b.putString("date1", date1);
					b.putString("time1", time1);
					
					b.putInt("on_off", on_off);
					
					// 將備忘錄資訊添加到Intent
					intent.putExtra("b", b);
					// 啟動備忘錄詳細資訊Activity
					intent.setClass(TaskListActivity.this, TaskDetailActivity.class);
					startActivity(intent);
					
				}
			}
		});
		
	}

	// 建立選項功能表
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, NEW, 0, "新增");
		menu.add(0, DEL, 0, "刪除");
		return true;
	}
	// 選項功能表單擊方法
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case NEW:
			// 啟動備忘錄詳細資訊Activity
			Intent intent = new Intent();
			intent.setClass(this, TaskDetailActivity.class);
			startActivity(intent);
			return true;
		case DEL:
			return true;
		}
		return false;
	}

}
