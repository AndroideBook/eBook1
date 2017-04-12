package com.amaker.ch10.app;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * @author 郭宏志
 * 通訊錄常數類別
 */
public final class Employees {
	// 授權常數
    public static final String AUTHORITY = "com.amaker.provider.Employees";
    private Employees() {}
    // 內部類別
    public static final class Employee implements BaseColumns {
    	// 建構子
        private Employee() {}
        // 存取Uri
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/employee");
        // 內容類型
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.amaker.employees";
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.amaker.employees";
        
        // 預設排序常數
        public static final String DEFAULT_SORT_ORDER = "name DESC";	// 按姓名排序
        // 欄位常數
        public static final String NAME = "name";					// 姓名
        public static final String GENDER= "gender";				// 性別
        public static final String AGE = "age"; 					// 年齡
    }
}
