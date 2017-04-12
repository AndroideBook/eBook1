package com.amaker.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * 
 * @author 郭宏志
 * 資料庫工具類別
 */
public class DBHelper extends SQLiteOpenHelper{
    // 資料庫名稱常數
    private static final String DATABASE_NAME = "Wireless.db";
    // 資料庫版本常數
    private static final int DATABASE_VERSION = 2;
    // 資料表名稱常數
    public static final String TABLES_TABLE_NAME = "TableTbl";
    public static final String TABLES_TABLE_NAME2 = "MenuTbl";
    
	//private SQLiteDatabase db;

	// 建構子
	public DBHelper(Context context) {
		// 建立資料庫
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// 建立時呼叫
	public void onCreate(SQLiteDatabase db) {
		//this.db = db;
		
		//
        db.execSQL("CREATE TABLE " + TABLES_TABLE_NAME + " ("
                + Tables._ID + " INTEGER PRIMARY KEY,"
                + Tables.NUM + " INTEGER,"
                + Tables.DESCRIPTION + " TEXT"
                + ");");
        //
                
        db.execSQL("CREATE TABLE " + TABLES_TABLE_NAME2 + " ("
                + Menus._ID + " INTEGER PRIMARY KEY,"
                + Menus.TYPE_ID + " INTEGER,"
                + Menus.NAME + " TEXT,"
                + Menus.PRICE + " INTEGER,"
                + Menus.PIC + " TEXT,"
                + Menus.REMARK + " TEXT"
                + ");");
        //
	}

	// 版本更新時呼叫
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// 刪除資料表
		//db.execSQL("DROP TABLE IF EXISTS " + TABLES_TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS " + TABLES_TABLE_NAME2);
        onCreate(db);
	}

}
