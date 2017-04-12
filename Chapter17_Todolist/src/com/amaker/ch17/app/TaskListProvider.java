package com.amaker.ch17.app;

import java.util.HashMap;
import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import com.amaker.ch17.app.TaskList.Tasks;

/**
 * @author ������
 * ���Ѹ�ƺ��@��k
 */
public class TaskListProvider extends ContentProvider {
    // ��Ʈw�W�ٱ`��
    private static final String DATABASE_NAME = "task_list.db";
    // ��Ʈw�����`��
    private static final int DATABASE_VERSION = 1;
    // ��ƪ��W�ٱ`��
    private static final String TASK_LIST_TABLE_NAME = "taskLists";
    // �d����춰�X
    private static HashMap<String, String> sTaskListProjectionMap;
    // �d�ߡB��s����
    private static final int TASKS = 1;
    private static final int TASK_ID = 2;
    // Uri�u�����O
    private static final UriMatcher sUriMatcher;
    // ��Ʈw�u�����O���
    private DatabaseHelper mOpenHelper;
    // �����u�����O�A�إߩζ}�Ҹ�Ʈw�B�إߩΧR����ƪ�
    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        // �إ߸�ƪ�
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + TASK_LIST_TABLE_NAME + " ("
                    + Tasks._ID + " INTEGER PRIMARY KEY,"
                    + Tasks.DATE1 + " TEXT,"
                    + Tasks.TIME1 + " TEXT,"
                    + Tasks.CONTENT + " TEXT,"
                    + Tasks.ON_OFF + " INTEGER,"
                    + Tasks.ALARM + " INTEGER,"
                    + Tasks.CREATED + " TEXT"
                    + ");");
        }
        // �R����ƪ�
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS taskLists");
            onCreate(db);
        }
    }
    
    // �إߩζ}�Ҹ�Ʈw
    @Override
    public boolean onCreate() {
        mOpenHelper = new DatabaseHelper(getContext());
        return true;
    }
    
    // �d��
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
            String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        switch (sUriMatcher.match(uri)) {
        // �d�ߩҦ��u�@
        case TASKS:
            qb.setTables(TASK_LIST_TABLE_NAME);
            qb.setProjectionMap(sTaskListProjectionMap);
            break;
        // �ھ�ID�d��
        case TASK_ID:
            qb.setTables(TASK_LIST_TABLE_NAME);
            qb.setProjectionMap(sTaskListProjectionMap);
            qb.appendWhere(Tasks._ID + "=" + uri.getPathSegments().get(1));
            break;
        default:
            throw new IllegalArgumentException("Uri���~�I " + uri);
        }

        // �ϥιw�]�Ƨ�
        String orderBy;
        if (TextUtils.isEmpty(sortOrder)) {
            orderBy = TaskList.Tasks.DEFAULT_SORT_ORDER;
        } else {
            orderBy = sortOrder;
        }

        // ���o��Ʈw���
        SQLiteDatabase db = mOpenHelper.getReadableDatabase();
        // ��^��ж��X
        Cursor c = qb.query(db, projection, selection, selectionArgs, null, null, orderBy);
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }
    // ���o����
    @Override
    public String getType(Uri uri) {
        switch (sUriMatcher.match(uri)) {
        case TASKS:
            return Tasks.CONTENT_TYPE;
        case TASK_ID:
            return Tasks.CONTENT_ITEM_TYPE;

        default:
            throw new IllegalArgumentException("���~�� URI�I " + uri);
        }
    }
    
    // �O�s���
    @Override
    public Uri insert(Uri uri, ContentValues initialValues) {
        if (sUriMatcher.match(uri) != TASKS) {
            throw new IllegalArgumentException("���~�� URI�I " + uri);
        }
        ContentValues values;
        if (initialValues != null) {
            values = new ContentValues(initialValues);
        } else {
            values = new ContentValues();
        }
        // ���o��Ʈw���
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        // �O�s��ƪ�^���ID
        long rowId = db.insert(TASK_LIST_TABLE_NAME, Tasks.CONTENT, values);
        if (rowId > 0) {
            Uri taskUri = ContentUris.withAppendedId(TaskList.Tasks.CONTENT_URI, rowId);
            getContext().getContentResolver().notifyChange(taskUri, null);
            return taskUri;
        }
        throw new SQLException("���J��ƥ��� " + uri);
    }
    
    // �R�����
    @Override
    public int delete(Uri uri, String where, String[] whereArgs) {
    	// ���o��Ʈw���
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        int count;
        switch (sUriMatcher.match(uri)) {
        // �ھګ��w����R��
        case TASKS:
            count = db.delete(TASK_LIST_TABLE_NAME, where, whereArgs);
            break;
        // �ھګ��w����MID�R��
        case TASK_ID:
            String noteId = uri.getPathSegments().get(1);
            count = db.delete(TASK_LIST_TABLE_NAME, Tasks._ID + "=" + noteId
                    + (!TextUtils.isEmpty(where) ? " AND (" + where + ')' : ""), whereArgs);
            break;

        default:
            throw new IllegalArgumentException("���~�� URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }
    
    // ��s���
    @Override
    public int update(Uri uri, ContentValues values, String where, String[] whereArgs) {
    	// ���o��Ʈw���
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        int count;
        switch (sUriMatcher.match(uri)) {
        // �ھګ��w�����s
        case TASKS:
            count = db.update(TASK_LIST_TABLE_NAME, values, where, whereArgs);
            break;
        // �ھګ��w����MID��s
        case TASK_ID:
            String noteId = uri.getPathSegments().get(1);
            count = db.update(TASK_LIST_TABLE_NAME, values, Tasks._ID + "=" + noteId
                    + (!TextUtils.isEmpty(where) ? " AND (" + where + ')' : ""), whereArgs);
            break;
        default:
            throw new IllegalArgumentException("���~�� URI " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    static {
    	// Uriƥ�ǰt�u�����O
        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sUriMatcher.addURI(TaskList.AUTHORITY, "taskLists", TASKS);
        sUriMatcher.addURI(TaskList.AUTHORITY, "taskLists/#", TASK_ID);
        
        // ��ҤƬd����춰�X
        sTaskListProjectionMap = new HashMap<String, String>();
        // �K�[�d�����
        sTaskListProjectionMap.put(Tasks._ID, Tasks._ID);
        sTaskListProjectionMap.put(Tasks.CONTENT, Tasks.CONTENT);
        sTaskListProjectionMap.put(Tasks.CREATED, Tasks.CREATED);
        
        sTaskListProjectionMap.put(Tasks.ALARM, Tasks.ALARM);
        sTaskListProjectionMap.put(Tasks.DATE1, Tasks.DATE1);
        sTaskListProjectionMap.put(Tasks.TIME1, Tasks.TIME1);
        
        sTaskListProjectionMap.put(Tasks.ON_OFF, Tasks.ON_OFF);
        
    }
}