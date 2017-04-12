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
	// 資料庫輔助類別
	private DBHelper dbHelper;
    // Uri工具類別
    private static final UriMatcher sUriMatcher;
    // 查詢、更新條件
    private static final int EMPLOYEE = 1;
    private static final int EMPLOYEE_ID = 2;
    // 查詢記錄結合
    private static HashMap<String, String> empProjectionMap;
    static {
    	// Uri匹配工具類別
        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sUriMatcher.addURI(Employees.AUTHORITY, "employee", EMPLOYEE);
        sUriMatcher.addURI(Employees.AUTHORITY, "employee/#", EMPLOYEE_ID);
        // 實例化查詢列集合
        empProjectionMap = new HashMap<String, String>();
        // 添加查詢記錄
        empProjectionMap.put(Employee._ID, Employee._ID);
        empProjectionMap.put(Employee.NAME, Employee.NAME);
        empProjectionMap.put(Employee.GENDER, Employee.GENDER);
        empProjectionMap.put(Employee.AGE, Employee.AGE);
    }

	// 建立時呼叫
	public boolean onCreate() {
		// 實例化資料庫助類別
		dbHelper = new DBHelper(getContext());
		return true;
	}
	// 添加方法
	public Uri insert(Uri uri, ContentValues values) {
		// 取得資料庫實例
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		// 插入資料，返回列ID
		long rowId = db.insert(DBHelper.EMPLOYEES_TABLE_NAME, Employee.NAME, values);
		// 如果插入成功返回uri
        if (rowId > 0) {
            Uri empUri = ContentUris.withAppendedId(Employee.CONTENT_URI, rowId);
            getContext().getContentResolver().notifyChange(empUri, null);
            return empUri;
        }
		return null;
	}
	// 刪除方法
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// 取得資料庫實例
		SQLiteDatabase db = dbHelper.getWritableDatabase();
        int count;
        switch (sUriMatcher.match(uri)) {
        // 根據指定條件刪除
        case EMPLOYEE:
            count = db.delete(DBHelper.EMPLOYEES_TABLE_NAME, selection, selectionArgs);
            break;
        // 概據指定條件和ID刪除
        case EMPLOYEE_ID:
            String noteId = uri.getPathSegments().get(1);
            count = db.delete(DBHelper.EMPLOYEES_TABLE_NAME, Employee._ID + "=" + noteId
                    + (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs);
            break;

        default:
            throw new IllegalArgumentException("錯誤的 URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
	}

	// 取得類型
	public String getType(Uri uri) {
		return null;
	}

	// 查詢方法
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		 SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
	        switch (sUriMatcher.match(uri)) {
	        // 查詢所有
	        case EMPLOYEE:
	            qb.setTables(DBHelper.EMPLOYEES_TABLE_NAME);
	            qb.setProjectionMap(empProjectionMap);
	            break;
	        // 根據ID查詢
	        case EMPLOYEE_ID:
	            qb.setTables(DBHelper.EMPLOYEES_TABLE_NAME);
	            qb.setProjectionMap(empProjectionMap);
	            qb.appendWhere(Employee._ID + "=" + uri.getPathSegments().get(1));
	            break;
	        default:
	            throw new IllegalArgumentException("Uri錯誤！ " + uri);
	        }

	        // 使用預設排序
	        String orderBy;
	        if (TextUtils.isEmpty(sortOrder)) {
	            orderBy = Employee.DEFAULT_SORT_ORDER;
	        } else {
	            orderBy = sortOrder;
	        }

	        // 取得資料庫實例
	        SQLiteDatabase db = dbHelper.getReadableDatabase();
	        // 返回游標集合
	        Cursor c = qb.query(db, projection, selection, selectionArgs, null, null, orderBy);
	        c.setNotificationUri(getContext().getContentResolver(), uri);
	        return c;
	}

	// 更新方法
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
    	// 取得資料庫實例
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int count;
        switch (sUriMatcher.match(uri)) {
        // 根據指定條件更新
        case EMPLOYEE:
            count = db.update(DBHelper.EMPLOYEES_TABLE_NAME, values, selection, selectionArgs);
            break;
        // 根據指定條件和ID更新
        case EMPLOYEE_ID:
            String noteId = uri.getPathSegments().get(1);
            count = db.update(DBHelper.EMPLOYEES_TABLE_NAME, values, Employee._ID + "=" + noteId
                    + (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs);
            break;
        default:
            throw new IllegalArgumentException("錯誤的 URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
	}

}
