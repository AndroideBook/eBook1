package com.amaker.ch17.app;

import java.util.Calendar;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.AdapterView.OnItemClickListener;

import com.amaker.ch17.app.TaskList.Tasks;
/**
 * 
 * @author 郭宏志
 * 備忘錄詳細資訊類別
 */
public class TaskDetailActivity extends ListActivity {
	// 備忘錄訊息列表
	private ListView listView = null;
	// 提醒日期
	private int mYear;
	private int mMonth;
	private int mDay;
	// 提醒時間
	private int mHour;
	private int mMinute;
	// 日期顯示TextView
	private TextView dateName, dateDesc;
	// 時間顯示TextView
	private TextView timeName, timeDesc;
	// 提醒內容TextView
	private TextView contentName, contentDesc;
	// 是否開啟提醒
	private int on_off = 0;
	// 是否聲音警告
	private int alarm = 0;
	
	// 顯示日期、時間對話方塊常數
	static final int DATE_DIALOG_ID = 0;
	static final int TIME_DIALOG_ID = 1;
	
	// 保存內容、日期與時間字串
	private String content, date1, time1;
	// 備忘錄ID
	private int id1;
	// 多選框
	private CheckedTextView ctv1, ctv2;
	// 存取佈局實例
	private LayoutInflater li;
	
	// 初始化方法
	private void init(Intent intent) {
		Bundle b = intent.getBundleExtra("b");
		if (b != null) {
			id1 = b.getInt("id");
			content = b.getString("content");
			date1 = b.getString("date1");
			time1 = b.getString("time1");
			on_off = b.getInt("on_off");
			alarm = b.getInt("alarm");

			if (date1 != null && date1.length() > 0) {
				String[] strs = date1.split("/");
				mYear = Integer.parseInt(strs[0]);
				mMonth = Integer.parseInt(strs[1]);
				mDay = Integer.parseInt(strs[2]);
			}

			if (time1 != null && time1.length() > 0) {
				String[] strs = time1.split(":");
				mHour = Integer.parseInt(strs[0]);
				mMinute = Integer.parseInt(strs[1]);
			}
		}

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// 取得ListView
		listView = getListView();
		// 實例化LayoutInflater
		li = getLayoutInflater();
		// 設定ListView Adapter 
		listView.setAdapter(new ViewAdapter());
		// 可多選
		listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

		// 取得Calendar實例
		final Calendar c = Calendar.getInstance();
		// 取得目前日期、時間
		mYear = c.get(Calendar.YEAR);
		mMonth = c.get(Calendar.MONTH);
		mDay = c.get(Calendar.DAY_OF_MONTH);
		mHour = c.get(Calendar.HOUR_OF_DAY);
		mMinute = c.get(Calendar.MINUTE);
		
		// 回應列表單擊事件
		listView.setOnItemClickListener(new OnItemClickListener() {
			//@Override
			public void onItemClick(AdapterView<?> av, View v, int position,
					long id) {

				switch (position) {
				// 設定是否開啟提醒
				case 0:
					ctv1 = (CheckedTextView) v;
					if (ctv1.isChecked()) {
						on_off = 0;
					} else {
						on_off = 1;
					}
					break;
				// 設定提醒日期
				case 1:
					showDialog(DATE_DIALOG_ID);
					break;
				// 設定提醒時間
				case 2:
					showDialog(TIME_DIALOG_ID);
					break;
				// 設定提醒內容
				case 3:
					showDialog1("請輸入內容：");
					break;
				// 設定是否開啟語音提醒
				case 4:
					ctv2 = (CheckedTextView) v;
					if (ctv2.isChecked()) {
						alarm = 0;
						setAlarm(false);
					} else {
						alarm = 1;
						setAlarm(true);
					}
					break;
				default:
					break;
				}
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();
		// 初始化列表
		init(getIntent());
	}
	// ListView Adatper，該類別實作列表的每一項透過自定視圖實現
	class ViewAdapter extends BaseAdapter {
		// 列表內容
		String[] strs = { "是否開啟", "日期", "時間", "內容", "開啟鬧鐘" };
		// 取得列表數量
		//@Override
		public int getCount() {
			return strs.length;
		}
		// 取得列表項目
		//@Override
		public Object getItem(int position) {
			return position;
		}
		// 返回列表ID
		//@Override
		public long getItemId(int position) {
			return position;
		}
		// 取得目前列表項目視圖
		//@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View v = li.inflate(R.layout.item_row, null);
			switch (position) {
			// 是否開啟該筆備忘錄
			case 0:
				ctv1 = (CheckedTextView) li
						.inflate(
								android.R.layout.simple_list_item_multiple_choice,
								null);
				ctv1.setText(strs[position]);
				if (on_off == 0) {
					ctv1.setChecked(false);
				} else {
					ctv1.setChecked(true);
				}
				return ctv1;
			// 提醒日期
			case 1:
				dateName = (TextView) v.findViewById(R.id.name);
				dateDesc = (TextView) v.findViewById(R.id.desc);
				dateName.setText(strs[position]);
				dateDesc.setText(mYear + "/" + mMonth + "/" + mDay);
				return v;
			// 提醒時間
			case 2:
				timeName = (TextView) v.findViewById(R.id.name);
				timeDesc = (TextView) v.findViewById(R.id.desc);
				timeName.setText(strs[position]);
				timeDesc.setText(mHour + ":" + mMinute);
				return v;
			// 提醒內容
			case 3:
				contentName = (TextView) v.findViewById(R.id.name);
				contentDesc = (TextView) v.findViewById(R.id.desc);
				contentName.setText(strs[position]);
				contentDesc.setText(content);
				return v;
			// 是否語音提示
			case 4:
				ctv2 = (CheckedTextView) li
						.inflate(
								android.R.layout.simple_list_item_multiple_choice,
								null);
				ctv2.setText(strs[position]);

				if (alarm == 0) {
					ctv2.setChecked(false);
				} else {
					ctv2.setChecked(true);
				}
				return ctv2;
			default:
				break;
			}

			return null;
		}
	}
	
	// 顯示對話方塊
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		// 顯示日期對話方塊
		case DATE_DIALOG_ID:
			return new DatePickerDialog(this, mDateSetListener, mYear, mMonth,
					mDay);
		// 顯示時間對話方塊
		case TIME_DIALOG_ID:
			return new TimePickerDialog(this, mTimeSetListener, mHour, mMinute,
					false);
		}
		return null;
	}
	
	// 設定通知提示
	private void setAlarm(boolean flag) {
		final String BC_ACTION = "com.amaker.ch17.TaskReceiver";
		// 取得AlarmManager實例
		final AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
		// 實例化Intent
		Intent intent = new Intent();
		// 設定Intent action屬性
		intent.setAction(BC_ACTION);
		intent.putExtra("msg", content);
		// 實例化PendingIntent
		final PendingIntent pi = PendingIntent.getBroadcast(
				getApplicationContext(), 0, intent, 0);
		// 取得系統時間
		final long time1 = System.currentTimeMillis();
		Calendar c = Calendar.getInstance();
		c.set(mYear, mMonth, mDay, mHour, mMinute);
		long time2 = c.getTimeInMillis();
		if (flag&&(time2-time1)>0&&on_off==1){
			am.set(AlarmManager.RTC_WAKEUP, time2, pi);
		}else{
			am.cancel(pi);
		}
	}

	/*
	 * 設定提示日期對話方塊
	 */
	private void showDialog1(String msg) {
		View v = li.inflate(R.layout.item_content, null);
		final EditText contentET = (EditText) v.findViewById(R.id.content);
		contentET.setText(content);
		new AlertDialog.Builder(this).setView(v).setMessage(msg).setCancelable(
				false).setPositiveButton("確定",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						content = contentET.getText().toString();
						contentDesc.setText(content);
					}
				}).show();
	}
	// 時間選擇對話方塊
	private TimePickerDialog.OnTimeSetListener mTimeSetListener = 
		new TimePickerDialog.OnTimeSetListener() {
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			mHour = hourOfDay;
			mMinute = minute;
			timeDesc.setText(mHour + ":" + mMinute);
		}
	};
	// 日期選擇對話方塊
	private DatePickerDialog.OnDateSetListener mDateSetListener = 
		new DatePickerDialog.OnDateSetListener() {
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			mYear = year;
			mMonth = monthOfYear;
			mDay = dayOfMonth;
			dateDesc.setText(mYear + "/" + mMonth + "/" + mDay);
		}
	};
	// 儲存或修改備忘錄資訊
	protected void onPause() {
		super.onPause();
		saveOrUpdate();
	};
	
	// 儲存或修改備忘錄資訊
	private void saveOrUpdate() {
		ContentValues values = new ContentValues();

		values.clear();

		values.put(Tasks.CONTENT, contentDesc.getText().toString());
		values.put(Tasks.DATE1, dateDesc.getText().toString());
		values.put(Tasks.TIME1, timeDesc.getText().toString());

		values.put(Tasks.ON_OFF, ctv1.isChecked() ? 1 : 0);
		values.put(Tasks.ALARM, ctv2.isChecked() ? 1 : 0);

		// 修改
		if (id1 != 0) {
			Uri uri = ContentUris.withAppendedId(Tasks.CONTENT_URI, id1);
			getContentResolver().update(uri, values, null, null);
		// 儲存
		} else {
			Uri uri = TaskList.Tasks.CONTENT_URI;
			getContentResolver().insert(uri, values);
		}

	}

}