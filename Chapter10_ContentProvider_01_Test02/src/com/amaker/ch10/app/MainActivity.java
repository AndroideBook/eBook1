package com.amaker.ch10.app;

import android.app.Activity;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.amaker.ch10.app.Employees.Employee;

public class MainActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        // �s�W
        insert();
        // �d��
        query();
        // ��s
        update();
        // �d��
        query();
        // �R��
        del();
        // �d��
        query();
    }
    // �R����k
    private void del(){
    	// �R��ID��1���O��
    	Uri uri = ContentUris.withAppendedId(Employee.CONTENT_URI, 1);
    	// ���oContentResolver�A�çR��
    	getContentResolver().delete(uri, null, null);
    }
    // ��s
    private void update(){
    	// ��sID��1���O��
    	Uri uri = ContentUris.withAppendedId(Employee.CONTENT_URI, 1);
    	ContentValues values = new ContentValues();
    	// �s�W���u�O��
    	values.put(Employee.NAME, "hz.guo");
    	values.put(Employee.GENDER, "male");
    	values.put(Employee.AGE,31);
    	// ���oContentResolver�A�ëK�s
		getContentResolver().update(uri, values, null, null);
    }
    // �d��
    private void query(){
    	// �d�߰O���}�C
    	   String[] PROJECTION = new String[] { 
    			   Employee._ID, 		// 0
    			   Employee.NAME, 		// 1
    			   Employee.GENDER, 	// 2
    			   Employee.AGE 		// 3
    	};
    	// �d�ߩҦ��Ƨѿ��T��
		Cursor c = managedQuery(Employee.CONTENT_URI, PROJECTION, null,
				null, Employee.DEFAULT_SORT_ORDER);
		// �P�_��ЬO�_����
		if (c.moveToFirst()) {
			// �M�����
			for (int i = 0; i < c.getCount(); i++) {
				c.moveToPosition(i);
				// ���o�m�W
				String name = c.getString(1);
				String gender = c.getString(2);
				int age = c.getInt(3);
				// ��X��x
				Log.i("emp", name+":"+gender+":"+age);
			}
		}
    }
    // ���J
    private void insert(){
    	// �ŧiUri
    	Uri uri = Employee.CONTENT_URI;
    	// ��Ҥ�ContentValues
    	ContentValues values = new ContentValues();
    	// �K�[���u�T��
    	values.put(Employee.NAME, "amaker");
    	values.put(Employee.GENDER, "male");
    	values.put(Employee.AGE,30);
    	// ���oContentResolver�A�çY�J
    	getContentResolver().insert(uri, values);
    }
}