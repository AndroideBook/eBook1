package com.amaker.provider;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * @author 郭宏志
 * 桌號常數類別
 */
public final class Menus  implements BaseColumns{
		// 授權常數
	    public static final String AUTHORITY = "com.amaker.provider.MENUS";
        // 存取Uri
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/menu1");
        // 預設排序常數
        public static final String DEFAULT_SORT_ORDER = "typeId DESC";// 依桌號排序
        // 欄位常數
        public static final String TYPE_ID = "typeId";			// 類型
        public static final String NAME= "name";				// 名稱
        public static final String PRICE= "price";				// 價格
        public static final String PIC= "pic";					// 圖片
        public static final String REMARK= "remark";			// 備註
}
