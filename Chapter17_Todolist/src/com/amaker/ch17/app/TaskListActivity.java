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
 * @author ������
 * �Ƨѿ��C�����O�A���Ѹ�����
 */
public class TaskListActivity extends ListActivity {
	
	// ��涵�ر`��
	private static final int NEW = 1;
	private static final int DEL = 2;
	
	// �d�����}�C
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
		// ���oIntent
		final Intent intent = getIntent();
		// �]�wUri
		if (intent.getData() == null) {
			intent.setData(Tasks.CONTENT_URI);
		}
		// ���oListView
		ListView listView = getListView();
		
		// �d�ߩҦ��Ƨѿ���T
		Cursor cursor = managedQuery(getIntent().getData(), PROJECTION, null,
				null, Tasks.DEFAULT_SORT_ORDER);
		// �إ�Adapter
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
				android.R.layout.simple_list_item_2, cursor,
				new String[] {Tasks._ID, Tasks.CONTENT },
				new int[] { android.R.id.text1,android.R.id.text2 });
		// �N�Ƨѿ���T��ܨ�ListView
		setListAdapter(adapter);
		
		
		// ��ListView�K�[�����ƥ��ť��
		listView.setOnItemClickListener(new OnItemClickListener() {
			//@Override
			public void onItemClick(AdapterView<?> av, View v, int position,
					long id) {
				// �z�LID�d�߳Ƨѿ���T
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
					
					// �N�Ƨѿ���T�K�[��Intent
					intent.putExtra("b", b);
					// �ҰʳƧѿ��ԲӸ�TActivity
					intent.setClass(TaskListActivity.this, TaskDetailActivity.class);
					startActivity(intent);
					
				}
			}
		});
		
	}

	// �إ߿ﶵ�\���
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, NEW, 0, "�s�W");
		menu.add(0, DEL, 0, "�R��");
		return true;
	}
	// �ﶵ�\���������k
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case NEW:
			// �ҰʳƧѿ��ԲӸ�TActivity
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