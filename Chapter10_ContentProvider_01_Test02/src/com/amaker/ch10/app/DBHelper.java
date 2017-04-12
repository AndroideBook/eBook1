package com.amaker.ch10.app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.amaker.ch10.app.Employees.Employee;
/**
 * 
 * @author ������
 * ��Ʈw�u�����O
 */
public class DBHelper extends SQLiteOpenHelper{
    // ��Ʈw�W�ٱ`��
    private static final String DATABASE_NAME = "Employees.db";
    // ��Ʈw�����`��
    private static final int DATABASE_VERSION = 1;
    // ��ƪ�W�ٱ`��
    public static final String EMPLOYEES_TABLE_NAME = "employee";
	// �غc�l
	public DBHelper(Context context) {
		// �إ߸�Ʈw
		super(context, DATABASE_NAME,null, DATABASE_VERSION);
	}

	// �إ߮ɩI�s
	public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + EMPLOYEES_TABLE_NAME + " ("
                + Employee._ID + " INTEGER PRIMARY KEY,"
                + Employee.NAME + " TEXT,"
                + Employee.GENDER + " TEXT,"
                + Employee.AGE + " INTEGER"
                + ");");
	}

	// ������s�ɩI�s
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// �R����ƪ�
		db.execSQL("DROP TABLE IF EXISTS employee");
        onCreate(db);
	}

}
