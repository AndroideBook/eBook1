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
        // 新增
        insert();
        // 查詢
        query();
        // 更新
        update();
        // 查詢
        query();
        // 刪除
        del();
        // 查詢
        query();
    }
    // 刪除方法
    private void del(){
    	// 刪除ID為1的記錄
    	Uri uri = ContentUris.withAppendedId(Employee.CONTENT_URI, 1);
    	// 取得ContentResolver，並刪除
    	getContentResolver().delete(uri, null, null);
    }
    // 更新
    private void update(){
    	// 更新ID為1的記錄
    	Uri uri = ContentUris.withAppendedId(Employee.CONTENT_URI, 1);
    	ContentValues values = new ContentValues();
    	// 新增員工記錄
    	values.put(Employee.NAME, "hz.guo");
    	values.put(Employee.GENDER, "male");
    	values.put(Employee.AGE,31);
    	// 取得ContentResolver，並便新
		getContentResolver().update(uri, values, null, null);
    }
    // 查詢
    private void query(){
    	// 查詢記錄陣列
    	   String[] PROJECTION = new String[] { 
    			   Employee._ID, 		// 0
    			   Employee.NAME, 		// 1
    			   Employee.GENDER, 	// 2
    			   Employee.AGE 		// 3
    	};
    	// 查詢所有備忘錄訊息
		Cursor c = managedQuery(Employee.CONTENT_URI, PROJECTION, null,
				null, Employee.DEFAULT_SORT_ORDER);
		// 判斷游標是否為空
		if (c.moveToFirst()) {
			// 遍覽游標
			for (int i = 0; i < c.getCount(); i++) {
				c.moveToPosition(i);
				// 取得姓名
				String name = c.getString(1);
				String gender = c.getString(2);
				int age = c.getInt(3);
				// 輸出日誌
				Log.i("emp", name+":"+gender+":"+age);
			}
		}
    }
    // 插入
    private void insert(){
    	// 宣告Uri
    	Uri uri = Employee.CONTENT_URI;
    	// 實例化ContentValues
    	ContentValues values = new ContentValues();
    	// 添加員工訊息
    	values.put(Employee.NAME, "amaker");
    	values.put(Employee.GENDER, "male");
    	values.put(Employee.AGE,30);
    	// 取得ContentResolver，並即入
    	getContentResolver().insert(uri, values);
    }
}