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
 * @author ������
 * �Ƨѿ��ԲӸ�T���O
 */
public class TaskDetailActivity extends ListActivity {
	// �Ƨѿ��T���C��
	private ListView listView = null;
	// �������
	private int mYear;
	private int mMonth;
	private int mDay;
	// �����ɶ�
	private int mHour;
	private int mMinute;
	// ������TextView
	private TextView dateName, dateDesc;
	// �ɶ����TextView
	private TextView timeName, timeDesc;
	// �������eTextView
	private TextView contentName, contentDesc;
	// �O�_�}�Ҵ���
	private int on_off = 0;
	// �O�_�n��ĵ�i
	private int alarm = 0;
	
	// ��ܤ���B�ɶ���ܤ���`��
	static final int DATE_DIALOG_ID = 0;
	static final int TIME_DIALOG_ID = 1;
	
	// �O�s���e�B����P�ɶ��r��
	private String content, date1, time1;
	// �Ƨѿ�ID
	private int id1;
	// �h���
	private CheckedTextView ctv1, ctv2;
	// �s���G�����
	private LayoutInflater li;
	
	// ��l�Ƥ�k
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
		
		// ���oListView
		listView = getListView();
		// ��Ҥ�LayoutInflater
		li = getLayoutInflater();
		// �]�wListView Adapter 
		listView.setAdapter(new ViewAdapter());
		// �i�h��
		listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

		// ���oCalendar���
		final Calendar c = Calendar.getInstance();
		// ���o�ثe����B�ɶ�
		mYear = c.get(Calendar.YEAR);
		mMonth = c.get(Calendar.MONTH);
		mDay = c.get(Calendar.DAY_OF_MONTH);
		mHour = c.get(Calendar.HOUR_OF_DAY);
		mMinute = c.get(Calendar.MINUTE);
		
		// �^���C������ƥ�
		listView.setOnItemClickListener(new OnItemClickListener() {
			//@Override
			public void onItemClick(AdapterView<?> av, View v, int position,
					long id) {

				switch (position) {
				// �]�w�O�_�}�Ҵ���
				case 0:
					ctv1 = (CheckedTextView) v;
					if (ctv1.isChecked()) {
						on_off = 0;
					} else {
						on_off = 1;
					}
					break;
				// �]�w�������
				case 1:
					showDialog(DATE_DIALOG_ID);
					break;
				// �]�w�����ɶ�
				case 2:
					showDialog(TIME_DIALOG_ID);
					break;
				// �]�w�������e
				case 3:
					showDialog1("�п�J���e�G");
					break;
				// �]�w�O�_�}�һy������
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
		// ��l�ƦC��
		init(getIntent());
	}
	// ListView Adatper�A�����O��@�C���C�@���z�L�۩w���Ϲ�{
	class ViewAdapter extends BaseAdapter {
		// �C���e
		String[] strs = { "�O�_�}��", "���", "�ɶ�", "���e", "�}�Ҿx��" };
		// ���o�C��ƶq
		//@Override
		public int getCount() {
			return strs.length;
		}
		// ���o�C����
		//@Override
		public Object getItem(int position) {
			return position;
		}
		// ��^�C��ID
		//@Override
		public long getItemId(int position) {
			return position;
		}
		// ���o�ثe�C���ص���
		//@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View v = li.inflate(R.layout.item_row, null);
			switch (position) {
			// �O�_�}�Ҹӵ��Ƨѿ�
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
			// �������
			case 1:
				dateName = (TextView) v.findViewById(R.id.name);
				dateDesc = (TextView) v.findViewById(R.id.desc);
				dateName.setText(strs[position]);
				dateDesc.setText(mYear + "/" + mMonth + "/" + mDay);
				return v;
			// �����ɶ�
			case 2:
				timeName = (TextView) v.findViewById(R.id.name);
				timeDesc = (TextView) v.findViewById(R.id.desc);
				timeName.setText(strs[position]);
				timeDesc.setText(mHour + ":" + mMinute);
				return v;
			// �������e
			case 3:
				contentName = (TextView) v.findViewById(R.id.name);
				contentDesc = (TextView) v.findViewById(R.id.desc);
				contentName.setText(strs[position]);
				contentDesc.setText(content);
				return v;
			// �O�_�y������
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
	
	// ��ܹ�ܤ��
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		// ��ܤ����ܤ��
		case DATE_DIALOG_ID:
			return new DatePickerDialog(this, mDateSetListener, mYear, mMonth,
					mDay);
		// ��ܮɶ���ܤ��
		case TIME_DIALOG_ID:
			return new TimePickerDialog(this, mTimeSetListener, mHour, mMinute,
					false);
		}
		return null;
	}
	
	// �]�w�q������
	private void setAlarm(boolean flag) {
		final String BC_ACTION = "com.amaker.ch17.TaskReceiver";
		// ���oAlarmManager���
		final AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
		// ��Ҥ�Intent
		Intent intent = new Intent();
		// �]�wIntent action�ݩ�
		intent.setAction(BC_ACTION);
		intent.putExtra("msg", content);
		// ��Ҥ�PendingIntent
		final PendingIntent pi = PendingIntent.getBroadcast(
				getApplicationContext(), 0, intent, 0);
		// ���o�t�ήɶ�
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
	 * �]�w���ܤ����ܤ��
	 */
	private void showDialog1(String msg) {
		View v = li.inflate(R.layout.item_content, null);
		final EditText contentET = (EditText) v.findViewById(R.id.content);
		contentET.setText(content);
		new AlertDialog.Builder(this).setView(v).setMessage(msg).setCancelable(
				false).setPositiveButton("�T�w",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						content = contentET.getText().toString();
						contentDesc.setText(content);
					}
				}).show();
	}
	// �ɶ���ܹ�ܤ��
	private TimePickerDialog.OnTimeSetListener mTimeSetListener = 
		new TimePickerDialog.OnTimeSetListener() {
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			mHour = hourOfDay;
			mMinute = minute;
			timeDesc.setText(mHour + ":" + mMinute);
		}
	};
	// �����ܹ�ܤ��
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
	// �x�s�έק�Ƨѿ���T
	protected void onPause() {
		super.onPause();
		saveOrUpdate();
	};
	
	// �x�s�έק�Ƨѿ���T
	private void saveOrUpdate() {
		ContentValues values = new ContentValues();

		values.clear();

		values.put(Tasks.CONTENT, contentDesc.getText().toString());
		values.put(Tasks.DATE1, dateDesc.getText().toString());
		values.put(Tasks.TIME1, timeDesc.getText().toString());

		values.put(Tasks.ON_OFF, ctv1.isChecked() ? 1 : 0);
		values.put(Tasks.ALARM, ctv2.isChecked() ? 1 : 0);

		// �ק�
		if (id1 != 0) {
			Uri uri = ContentUris.withAppendedId(Tasks.CONTENT_URI, id1);
			getContentResolver().update(uri, values, null, null);
		// �x�s
		} else {
			Uri uri = TaskList.Tasks.CONTENT_URI;
			getContentResolver().insert(uri, values);
		}

	}

}