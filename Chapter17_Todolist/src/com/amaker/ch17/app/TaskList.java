package com.amaker.ch17.app;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * @author ������
 * �Ƨѿ��`�����O
 */
public final class TaskList {
	// ���v�`��
    public static final String AUTHORITY = "com.amaker.provider.TaskList";
    
    private TaskList() {}
    // �������O
    public static final class Tasks implements BaseColumns {
       
        private Tasks() {}
        // �s��Uri
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/taskLists");
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.amaker.tasklist";
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.amaker.tasklist";
        
        // �w�]�ƧǱ`��
        public static final String DEFAULT_SORT_ORDER = "created DESC";
        // ��L���`��
        public static final String CONTENT = "content";
        public static final String CREATED= "created";
        public static final String DATE1 = "date1";
        public static final String TIME1 = "time1";
        public static final String ON_OFF = "on_off";
        public static final String ALARM = "alarm";

    }
}
