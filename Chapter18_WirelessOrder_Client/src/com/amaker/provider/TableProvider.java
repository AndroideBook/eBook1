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
	// ��Ʈw���U���O
	private DBHelper dbHelper;
    // Uri�u�����O
    private static final UriMatcher sUriMatcher;
    // �d�ߡB��s����
    private static final int TABLES = 1;
    private static final int TABLES_ID = 2;
    // �d����춰�X
    private static HashMap<String, String> tblProjectionMap;
    static {
    	// Uri�ǰt�u�����O
        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sUriMatcher.addURI(Tables.AUTHORITY, "table", TABLES);
        sUriMatcher.addURI(Tables.AUTHORITY, "table/#", TABLES_ID);
        // ��ҤƬd����춰�X
        tblProjectionMap = new HashMap<String, String>();
        // �K�[�d�����
        tblProjectionMap.put(Tables._ID, Tables._ID);
        tblProjectionMap.put(Tables.NUM, Tables.NUM);
        tblProjectionMap.put(Tables.DESCRIPTION, Tables.DESCRIPTION);
    }

	// �إ߮ɩI�s
	public boolean onCreate() {
		// ��ҤƸ�Ʈw���U���O
		dbHelper = new DBHelper(getContext());
		return true;
	}
	// �K�[��k
	public Uri insert(Uri uri, ContentValues values) {
		// ���o��Ʈw���
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		// ���J��ơA��^�CID
		long rowId = db.insert(DBHelper.TABLES_TABLE_NAME,Tables.NUM, values);
		// �p�G�s�W���\��^uri
        if (rowId > 0) {
            Uri empUri = ContentUris.withAppendedId(Tables.CONTENT_URI, rowId);
            getContext().getContentResolver().notifyChange(empUri, null);
            return empUri;
        }
		return null;
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
	        // �d�ߥ���
	        case TABLES:
	            qb.setTables(DBHelper.TABLES_TABLE_NAME);
	            qb.setProjectionMap(tblProjectionMap);
	            break;
	        // �ھ�ID�d��
	        case TABLES_ID:
	            qb.setTables(DBHelper.TABLES_TABLE_NAME);
	            qb.setProjectionMap(tblProjectionMap);
	            qb.appendWhere(Tables._ID + "=" + uri.getPathSegments().get(1));
	            break;
	        default:
	            throw new IllegalArgumentException("Uri���~�I " + uri);
	        }

	        // �ϥιw�]�Ƨ�
	        String orderBy;
	        if (TextUtils.isEmpty(sortOrder)) {
	            orderBy = Tables.DEFAULT_SORT_ORDER;
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
