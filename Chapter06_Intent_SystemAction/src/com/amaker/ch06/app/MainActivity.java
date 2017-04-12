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
		// �\�����
		String[] menus = { "�d�ݹq�ܰT��", "�s��q�ܰT��", "��ܼ����q�ܤ���", "�������q��", "�ϥ��s����", "�ϥΦa��"};
		// �N�\����س]�w��ListView���C��ﶵ
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
		// �d��_id ��1���Τ�q�ܰT��
		case 0:
			data = "content://contacts/people";
			uri = Uri.parse(data);
			intent.setAction(Intent.ACTION_VIEW);
			intent.setData(uri);
			startActivity(intent);
			break;
		// �s��_id ��1���Τ�q�ܰT��
		case 1:
			data = "content://contacts/people/1";
			uri = Uri.parse(data);
			intent.setAction(Intent.ACTION_EDIT);
			intent.setData(uri);
			startActivity(intent);
			break;
		// ��ܼ����q�ܤ���
		case 2:
			data = "tel:13800138000";
			uri = Uri.parse(data);
			intent.setAction(Intent.ACTION_DIAL);
			intent.setData(uri);
			startActivity(intent);
			break;
		// �������q��
		case 3:
			data = "tel:13800138000";
			uri = Uri.parse(data);
			intent.setAction(Intent.ACTION_CALL);
			intent.setData(uri);
			startActivity(intent);
			break;
		// �ϥ��s����
		case 4:
			data = "http://www.google.com";
			uri = Uri.parse(data);
			intent.setAction(Intent.ACTION_VIEW);
			intent.setData(uri);
			startActivity(intent);
			break;
		// �ϥΦa��
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