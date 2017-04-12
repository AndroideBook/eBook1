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
 * @author 郭宏志
 * 提供資料維護方法
 */
public class TaskListProvider extends ContentProvider {
    // 資料庫名稱常數
    private static final String DATABASE_NAME = "task_list.db";
    // 資料庫版本常數
    private static final int DATABASE_VERSION = 1;
    // 資料表名稱常數
    private static final String TASK_LIST_TABLE_NAME = "taskLists";
    // 查詢欄位集合
    private static HashMap<String, String> sTaskListProjectionMap;
    // 查詢、更新條件
    private static final int TASKS = 1;
    private static final int TASK_ID = 2;
    // Uri工具類別
    private static final UriMatcher sUriMatcher;
    // 資料庫工具類別實例
    private DatabaseHelper mOpenHelper;
    // 內部工具類別，建立或開啟資料庫、建立或刪除資料表
    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        // 建立資料表
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
        // 刪除資料表
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS taskLists");
            onCreate(db);
        }
    }
    
    // 建立或開啟資料庫
    @Override
    public boolean onCreate() {
        mOpenHelper = new DatabaseHelper(getContext());
        return true;
    }
    
    // 查詢
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
            String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        switch (sUriMatcher.match(uri)) {
        // 查詢所有工作
        case TASKS:
            qb.setTables(TASK_LIST_TABLE_NAME);
            qb.setProjectionMap(sTaskListProjectionMap);
            break;
        // 根據ID查詢
        case TASK_ID:
            qb.setTables(TASK_LIST_TABLE_NAME);
            qb.setProjectionMap(sTaskListProjectionMap);
            qb.appendWhere(Tasks._ID + "=" + uri.getPathSegments().get(1));
            break;
        default:
            throw new IllegalArgumentException("Uri錯誤！ " + uri);
        }

        // 使用預設排序
        String orderBy;
        if (TextUtils.isEmpty(sortOrder)) {
            orderBy = TaskList.Tasks.DEFAULT_SORT_ORDER;
        } else {
            orderBy = sortOrder;
        }

        // 取得資料庫實例
        SQLiteDatabase db = mOpenHelper.getReadableDatabase();
        // 返回游標集合
        Cursor c = qb.query(db, projection, selection, selectionArgs, null, null, orderBy);
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }
    // 取得類型
    @Override
    public String getType(Uri uri) {
        switch (sUriMatcher.match(uri)) {
        case TASKS:
            return Tasks.CONTENT_TYPE;
        case TASK_ID:
            return Tasks.CONTENT_ITEM_TYPE;

        default:
            throw new IllegalArgumentException("錯誤的 URI！ " + uri);
        }
    }
    
    // 保存資料
    @Override
    public Uri insert(Uri uri, ContentValues initialValues) {
        if (sUriMatcher.match(uri) != TASKS) {
            throw new IllegalArgumentException("錯誤的 URI！ " + uri);
        }
        ContentValues values;
        if (initialValues != null) {
            values = new ContentValues(initialValues);
        } else {
            values = new ContentValues();
        }
        // 取得資料庫實例
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        // 保存資料返回欄位ID
        long rowId = db.insert(TASK_LIST_TABLE_NAME, Tasks.CONTENT, values);
        if (rowId > 0) {
            Uri taskUri = ContentUris.withAppendedId(TaskList.Tasks.CONTENT_URI, rowId);
            getContext().getContentResolver().notifyChange(taskUri, null);
            return taskUri;
        }
        throw new SQLException("插入資料失敗 " + uri);
    }
    
    // 刪除資料
    @Override
    public int delete(Uri uri, String where, String[] whereArgs) {
    	// 取得資料庫實例
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        int count;
        switch (sUriMatcher.match(uri)) {
        // 根據指定條件刪除
        case TASKS:
            count = db.delete(TASK_LIST_TABLE_NAME, where, whereArgs);
            break;
        // 根據指定條件和ID刪除
        case TASK_ID:
            String noteId = uri.getPathSegments().get(1);
            count = db.delete(TASK_LIST_TABLE_NAME, Tasks._ID + "=" + noteId
                    + (!TextUtils.isEmpty(where) ? " AND (" + where + ')' : ""), whereArgs);
            break;

        default:
            throw new IllegalArgumentException("錯誤的 URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }
    
    // 更新資料
    @Override
    public int update(Uri uri, ContentValues values, String where, String[] whereArgs) {
    	// 取得資料庫實例
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        int count;
        switch (sUriMatcher.match(uri)) {
        // 根據指定條件更新
        case TASKS:
            count = db.update(TASK_LIST_TABLE_NAME, values, where, whereArgs);
            break;
        // 根據指定條件和ID更新
        case TASK_ID:
            String noteId = uri.getPathSegments().get(1);
            count = db.update(TASK_LIST_TABLE_NAME, values, Tasks._ID + "=" + noteId
                    + (!TextUtils.isEmpty(where) ? " AND (" + where + ')' : ""), whereArgs);
            break;
        default:
            throw new IllegalArgumentException("錯誤的 URI " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    static {
    	// Uriぁ匹配工具類別
        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sUriMatcher.addURI(TaskList.AUTHORITY, "taskLists", TASKS);
        sUriMatcher.addURI(TaskList.AUTHORITY, "taskLists/#", TASK_ID);
        
        // 實例化查詢欄位集合
        sTaskListProjectionMap = new HashMap<String, String>();
        // 添加查詢欄位
        sTaskListProjectionMap.put(Tasks._ID, Tasks._ID);
        sTaskListProjectionMap.put(Tasks.CONTENT, Tasks.CONTENT);
        sTaskListProjectionMap.put(Tasks.CREATED, Tasks.CREATED);
        
        sTaskListProjectionMap.put(Tasks.ALARM, Tasks.ALARM);
        sTaskListProjectionMap.put(Tasks.DATE1, Tasks.DATE1);
        sTaskListProjectionMap.put(Tasks.TIME1, Tasks.TIME1);
        
        sTaskListProjectionMap.put(Tasks.ON_OFF, Tasks.ON_OFF);
        
    }
}
