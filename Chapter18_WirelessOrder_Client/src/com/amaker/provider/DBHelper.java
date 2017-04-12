package com.amaker.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * 
 * @author ������
 * ��Ʈw�u�����O
 */
public class DBHelper extends SQLiteOpenHelper{
    // ��Ʈw�W�ٱ`��
    private static final String DATABASE_NAME = "Wireless.db";
    // ��Ʈw�����`��
    private static final int DATABASE_VERSION = 2;
    // ��ƪ�W�ٱ`��
    public static final String TABLES_TABLE_NAME = "TableTbl";
    public static final String TABLES_TABLE_NAME2 = "MenuTbl";
    
	//private SQLiteDatabase db;

	// �غc�l
	public DBHelper(Context context) {
		// �إ߸�Ʈw
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// �إ߮ɩI�s
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

	// ������s�ɩI�s
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// �R����ƪ�
		//db.execSQL("DROP TABLE IF EXISTS " + TABLES_TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS " + TABLES_TABLE_NAME2);
        onCreate(db);
	}

}
