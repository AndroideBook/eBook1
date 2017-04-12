package com.amaker.provider;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * @author 郭宏志
 * 桌號常數類別
 */
public final class Tables  implements BaseColumns{
		// 授權常數
	    public static final String AUTHORITY = "com.amaker.provider.TABLES";
        // 存取Uri
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/table");
        // 預設排序常數
        public static final String DEFAULT_SORT_ORDER = "num DESC";// 按桌號排序
        // 欄位常數
        public static final String NUM = "num";					// 桌號
        public static final String DESCRIPTION= "description";	// 描述
}
