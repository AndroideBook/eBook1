package com.amaker.provider;

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

public class TableProvider extends ContentProvider{
	// 資料庫輔助類別
	private DBHelper dbHelper;
    // Uri工具類別
    private static final UriMatcher sUriMatcher;
    // 查詢、更新條件
    private static final int TABLES = 1;
    private static final int TABLES_ID = 2;
    // 查詢欄位集合
    private static HashMap<String, String> tblProjectionMap;
    static {
    	// Uri匹配工具類別
        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sUriMatcher.addURI(Tables.AUTHORITY, "table", TABLES);
        sUriMatcher.addURI(Tables.AUTHORITY, "table/#", TABLES_ID);
        // 實例化查詢欄位集合
        tblProjectionMap = new HashMap<String, String>();
        // 添加查詢欄位
        tblProjectionMap.put(Tables._ID, Tables._ID);
        tblProjectionMap.put(Tables.NUM, Tables.NUM);
        tblProjectionMap.put(Tables.DESCRIPTION, Tables.DESCRIPTION);
    }

	// 建立時呼叫
	public boolean onCreate() {
		// 實例化資料庫輔助類別
		dbHelper = new DBHelper(getContext());
		return true;
	}
	// 添加方法
	public Uri insert(Uri uri, ContentValues values) {
		// 取得資料庫實例
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		// 插入資料，返回列ID
		long rowId = db.insert(DBHelper.TABLES_TABLE_NAME,Tables.NUM, values);
		// 如果新增成功返回uri
        if (rowId > 0) {
            Uri empUri = ContentUris.withAppendedId(Tables.CONTENT_URI, rowId);
            getContext().getContentResolver().notifyChange(empUri, null);
            return empUri;
        }
		return null;
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
	        // 查詢全部
	        case TABLES:
	            qb.setTables(DBHelper.TABLES_TABLE_NAME);
	            qb.setProjectionMap(tblProjectionMap);
	            break;
	        // 根據ID查詢
	        case TABLES_ID:
	            qb.setTables(DBHelper.TABLES_TABLE_NAME);
	            qb.setProjectionMap(tblProjectionMap);
	            qb.appendWhere(Tables._ID + "=" + uri.getPathSegments().get(1));
	            break;
	        default:
	            throw new IllegalArgumentException("Uri錯誤！ " + uri);
	        }

	        // 使用預設排序
	        String orderBy;
	        if (TextUtils.isEmpty(sortOrder)) {
	            orderBy = Tables.DEFAULT_SORT_ORDER;
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
	
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		
		return 0;
	}
	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		return 0;
	}
}
