package com.amaker.ch10.app;

import java.util.HashMap;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

import com.amaker.ch10.app.Employees.Employee;

public class EmployeeProvider extends ContentProvider{
	// ��Ʈw���U���O
	private DBHelper dbHelper;
    // Uri�u�����O
    private static final UriMatcher sUriMatcher;
    // �d�ߡB��s����
    private static final int EMPLOYEE = 1;
    private static final int EMPLOYEE_ID = 2;
    // �d�߰O�����X
    private static HashMap<String, String> empProjectionMap;
    static {
    	// Uri�ǰt�u�����O
        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sUriMatcher.addURI(Employees.AUTHORITY, "employee", EMPLOYEE);
        sUriMatcher.addURI(Employees.AUTHORITY, "employee/#", EMPLOYEE_ID);
        // ��ҤƬd�ߦC���X
        empProjectionMap = new HashMap<String, String>();
        // �K�[�d�߰O��
        empProjectionMap.put(Employee._ID, Employee._ID);
        empProjectionMap.put(Employee.NAME, Employee.NAME);
        empProjectionMap.put(Employee.GENDER, Employee.GENDER);
        empProjectionMap.put(Employee.AGE, Employee.AGE);
    }

	// �إ߮ɩI�s
	public boolean onCreate() {
		// ��ҤƸ�Ʈw�U���O
		dbHelper = new DBHelper(getContext());
		return true;
	}
	// �K�[��k
	public Uri insert(Uri uri, ContentValues values) {
		// ���o��Ʈw���
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		// ���J��ơA��^�CID
		long rowId = db.insert(DBHelper.EMPLOYEES_TABLE_NAME, Employee.NAME, values);
		// �p�G���J���\��^uri
        if (rowId > 0) {
            Uri empUri = ContentUris.withAppendedId(Employee.CONTENT_URI, rowId);
            getContext().getContentResolver().notifyChange(empUri, null);
            return empUri;
        }
		return null;
	}
	// �R����k
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// ���o��Ʈw���
		SQLiteDatabase db = dbHelper.getWritableDatabase();
        int count;
        switch (sUriMatcher.match(uri)) {
        // �ھګ��w����R��
        case EMPLOYEE:
            count = db.delete(DBHelper.EMPLOYEES_TABLE_NAME, selection, selectionArgs);
            break;
        // ���ګ��w����MID�R��
        case EMPLOYEE_ID:
            String noteId = uri.getPathSegments().get(1);
            count = db.delete(DBHelper.EMPLOYEES_TABLE_NAME, Employee._ID + "=" + noteId
                    + (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs);
            break;

        default:
            throw new IllegalArgumentException("���~�� URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
	}

	// ���o����
	public String getType(Uri uri) {
		return null;
	}

	// �d�ߤ�k
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		 SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
	        switch (sUriMatcher.match(uri)) {
	        // �d�ߩҦ�
	        case EMPLOYEE:
	            qb.setTables(DBHelper.EMPLOYEES_TABLE_NAME);
	            qb.setProjectionMap(empProjectionMap);
	            break;
	        // �ھ�ID�d��
	        case EMPLOYEE_ID:
	            qb.setTables(DBHelper.EMPLOYEES_TABLE_NAME);
	            qb.setProjectionMap(empProjectionMap);
	            qb.appendWhere(Employee._ID + "=" + uri.getPathSegments().get(1));
	            break;
	        default:
	            throw new IllegalArgumentException("Uri���~�I " + uri);
	        }

	        // �ϥιw�]�Ƨ�
	        String orderBy;
	        if (TextUtils.isEmpty(sortOrder)) {
	            orderBy = Employee.DEFAULT_SORT_ORDER;
	        } else {
	            orderBy = sortOrder;
	        }

	        // ���o��Ʈw���
	        SQLiteDatabase db = dbHelper.getReadableDatabase();
	        // ��^��ж��X
	        Cursor c = qb.query(db, projection, selection, selectionArgs, null, null, orderBy);
	        c.setNotificationUri(getContext().getContentResolver(), uri);
	        return c;
	}

	// ��s��k
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
    	// ���o��Ʈw���
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int count;
        switch (sUriMatcher.match(uri)) {
        // �ھګ��w�����s
        case EMPLOYEE:
            count = db.update(DBHelper.EMPLOYEES_TABLE_NAME, values, selection, selectionArgs);
            break;
        // �ھګ��w����MID��s
        case EMPLOYEE_ID:
            String noteId = uri.getPathSegments().get(1);
            count = db.update(DBHelper.EMPLOYEES_TABLE_NAME, values, Employee._ID + "=" + noteId
                    + (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs);
            break;
        default:
            throw new IllegalArgumentException("���~�� URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
	}

}
