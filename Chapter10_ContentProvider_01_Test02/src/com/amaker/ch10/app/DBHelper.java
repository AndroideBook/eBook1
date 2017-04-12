package com.amaker.ch10.app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.amaker.ch10.app.Employees.Employee;
/**
 * 
 * @author 郭宏志
 * 資料庫工具類別
 */
public class DBHelper extends SQLiteOpenHelper{
    // 資料庫名稱常數
    private static final String DATABASE_NAME = "Employees.db";
    // 資料庫版本常數
    private static final int DATABASE_VERSION = 1;
    // 資料表名稱常數
    public static final String EMPLOYEES_TABLE_NAME = "employee";
	// 建構子
	public DBHelper(Context context) {
		// 建立資料庫
		super(context, DATABASE_NAME,null, DATABASE_VERSION);
	}

	// 建立時呼叫
	public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + EMPLOYEES_TABLE_NAME + " ("
                + Employee._ID + " INTEGER PRIMARY KEY,"
                + Employee.NAME + " TEXT,"
                + Employee.GENDER + " TEXT,"
                + Employee.AGE + " INTEGER"
                + ");");
	}

	// 版本更新時呼叫
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// 刪除資料表
		db.execSQL("DROP TABLE IF EXISTS employee");
        onCreate(db);
	}

}
